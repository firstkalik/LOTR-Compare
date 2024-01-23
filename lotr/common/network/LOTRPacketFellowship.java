/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.PlayerProfileCache
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraftforge.common.UsernameCache
 *  org.apache.commons.lang3.StringUtils
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRTitle;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.UsernameCache;
import org.apache.commons.lang3.StringUtils;

public class LOTRPacketFellowship
implements IMessage {
    private UUID fellowshipID;
    private boolean isInvite;
    private String fellowshipName;
    private ItemStack fellowshipIcon;
    private boolean isOwned;
    private boolean isAdminned;
    private List<String> playerNames = new ArrayList<String>();
    private Map<String, LOTRTitle.PlayerTitle> titleMap = new HashMap<String, LOTRTitle.PlayerTitle>();
    private Set<String> adminNames = new HashSet<String>();
    private boolean preventPVP;
    private boolean preventHiredFF;
    private boolean showMapLocations;

    public LOTRPacketFellowship() {
    }

    public LOTRPacketFellowship(LOTRPlayerData playerData, LOTRFellowship fs, boolean invite) {
        this.fellowshipID = fs.getFellowshipID();
        this.isInvite = invite;
        this.fellowshipName = fs.getName();
        this.fellowshipIcon = fs.getIcon();
        UUID thisPlayer = playerData.getPlayerUUID();
        this.isOwned = fs.isOwner(thisPlayer);
        this.isAdminned = fs.isAdmin(thisPlayer);
        List<UUID> playerIDs = fs.getAllPlayerUUIDs();
        for (UUID player : playerIDs) {
            String username = LOTRPacketFellowship.getPlayerUsername(player);
            this.playerNames.add(username);
            LOTRPlayerData data = LOTRLevelData.getData(player);
            LOTRTitle.PlayerTitle title = data.getPlayerTitle();
            if (title != null) {
                this.titleMap.put(username, title);
            }
            if (!fs.isAdmin(player)) continue;
            this.adminNames.add(username);
        }
        this.preventPVP = fs.getPreventPVP();
        this.preventHiredFF = fs.getPreventHiredFriendlyFire();
        this.showMapLocations = fs.getShowMapLocations();
    }

    public static String getPlayerUsername(UUID player) {
        GameProfile profile = MinecraftServer.getServer().func_152358_ax().func_152652_a(player);
        if (profile == null || StringUtils.isBlank((CharSequence)profile.getName())) {
            String name = UsernameCache.getLastKnownUsername((UUID)player);
            if (name != null) {
                profile = new GameProfile(player, name);
            } else {
                profile = new GameProfile(player, "");
                MinecraftServer.getServer().func_147130_as().fillProfileProperties(profile, true);
            }
        }
        String username = profile.getName();
        return username;
    }

    public void toBytes(ByteBuf data) {
        data.writeLong(this.fellowshipID.getMostSignificantBits());
        data.writeLong(this.fellowshipID.getLeastSignificantBits());
        data.writeBoolean(this.isInvite);
        byte[] fsNameBytes = this.fellowshipName.getBytes(Charsets.UTF_8);
        data.writeByte(fsNameBytes.length);
        data.writeBytes(fsNameBytes);
        NBTTagCompound iconData = new NBTTagCompound();
        if (this.fellowshipIcon != null) {
            this.fellowshipIcon.writeToNBT(iconData);
        }
        try {
            new PacketBuffer(data).writeNBTTagCompoundToBuffer(iconData);
        }
        catch (IOException e) {
            FMLLog.severe((String)"LOTR: Error writing fellowship data", (Object[])new Object[0]);
            e.printStackTrace();
        }
        data.writeBoolean(this.isOwned);
        data.writeBoolean(this.isAdminned);
        for (String username : this.playerNames) {
            byte[] usernameBytes = username.getBytes(Charsets.UTF_8);
            data.writeByte(usernameBytes.length);
            data.writeBytes(usernameBytes);
            LOTRTitle.PlayerTitle title = this.titleMap.get(username);
            if (title != null) {
                data.writeShort(title.getTitle().titleID);
                data.writeByte((int)title.getColor().getFormattingCode());
            } else {
                data.writeShort(-1);
            }
            boolean admin = this.adminNames.contains(username);
            data.writeBoolean(admin);
        }
        data.writeByte(-1);
        data.writeBoolean(this.preventPVP);
        data.writeBoolean(this.preventHiredFF);
        data.writeBoolean(this.showMapLocations);
    }

    public void fromBytes(ByteBuf data) {
        this.fellowshipID = new UUID(data.readLong(), data.readLong());
        this.isInvite = data.readBoolean();
        byte fsNameLength = data.readByte();
        ByteBuf fsNameBytes = data.readBytes((int)fsNameLength);
        this.fellowshipName = fsNameBytes.toString(Charsets.UTF_8);
        NBTTagCompound iconData = new NBTTagCompound();
        try {
            iconData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
        }
        catch (IOException e) {
            FMLLog.severe((String)"LOTR: Error reading fellowship data", (Object[])new Object[0]);
            e.printStackTrace();
        }
        this.fellowshipIcon = ItemStack.loadItemStackFromNBT((NBTTagCompound)iconData);
        this.isOwned = data.readBoolean();
        this.isAdminned = data.readBoolean();
        byte usernameLength = 0;
        while ((usernameLength = data.readByte()) >= 0) {
            boolean admin;
            ByteBuf usernameBytes = data.readBytes((int)usernameLength);
            String username = usernameBytes.toString(Charsets.UTF_8);
            this.playerNames.add(username);
            short titleID = data.readShort();
            if (titleID >= 0) {
                byte colorID = data.readByte();
                LOTRTitle title = LOTRTitle.forID(titleID);
                EnumChatFormatting color = LOTRTitle.PlayerTitle.colorForID(colorID);
                if (title != null && color != null) {
                    LOTRTitle.PlayerTitle playerTitle = new LOTRTitle.PlayerTitle(title, color);
                    this.titleMap.put(username, playerTitle);
                }
            }
            if (!(admin = data.readBoolean())) continue;
            this.adminNames.add(username);
        }
        this.preventPVP = data.readBoolean();
        this.preventHiredFF = data.readBoolean();
        this.showMapLocations = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowship, IMessage> {
        public IMessage onMessage(LOTRPacketFellowship packet, MessageContext context) {
            LOTRFellowshipClient fellowship = new LOTRFellowshipClient(packet.fellowshipID, packet.fellowshipName, packet.isOwned, packet.isAdminned, packet.playerNames);
            fellowship.setTitles(packet.titleMap);
            fellowship.setAdmins(packet.adminNames);
            fellowship.setIcon(packet.fellowshipIcon);
            fellowship.setPreventPVP(packet.preventPVP);
            fellowship.setPreventHiredFriendlyFire(packet.preventHiredFF);
            fellowship.setShowMapLocations(packet.showMapLocations);
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            if (packet.isInvite) {
                LOTRLevelData.getData(entityplayer).addOrUpdateClientFellowshipInvite(fellowship);
            } else {
                LOTRLevelData.getData(entityplayer).addOrUpdateClientFellowship(fellowship);
            }
            return null;
        }
    }

}

