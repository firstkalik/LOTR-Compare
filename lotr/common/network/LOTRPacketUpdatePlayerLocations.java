/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.network.PacketBuffer
 */
package lotr.common.network;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.PacketBuffer;

public class LOTRPacketUpdatePlayerLocations
implements IMessage {
    private List<PlayerLocationInfo> playerLocations = new ArrayList<PlayerLocationInfo>();
    private static Map<UUID, byte[]> cachedProfileBytes = new HashMap<UUID, byte[]>();

    public void addPlayerLocation(GameProfile profile, double x, double z) {
        if (profile.isComplete()) {
            this.playerLocations.add(new PlayerLocationInfo(profile, x, z));
        }
    }

    public void toBytes(ByteBuf data) {
        int players = this.playerLocations.size();
        data.writeShort(players);
        for (PlayerLocationInfo player : this.playerLocations) {
            GameProfile profile = player.playerProfile;
            UUID playerID = profile.getId();
            byte[] profileBytes = null;
            if (cachedProfileBytes.containsKey(playerID)) {
                profileBytes = cachedProfileBytes.get(playerID);
            } else {
                ByteBuf tempBuf = Unpooled.buffer();
                try {
                    NBTTagCompound profileData = new NBTTagCompound();
                    NBTUtil.func_152460_a((NBTTagCompound)profileData, (GameProfile)profile);
                    new PacketBuffer(tempBuf).writeNBTTagCompoundToBuffer(profileData);
                    byte[] tempBytes = tempBuf.array();
                    profileBytes = Arrays.copyOf(tempBytes, tempBytes.length);
                    cachedProfileBytes.put(playerID, profileBytes);
                }
                catch (IOException e) {
                    FMLLog.severe((String)"Error writing player profile data", (Object[])new Object[0]);
                    e.printStackTrace();
                }
            }
            if (profileBytes == null) {
                data.writeShort(-1);
                continue;
            }
            byte[] copied = Arrays.copyOf(profileBytes, profileBytes.length);
            data.writeShort(copied.length);
            data.writeBytes(copied);
            data.writeDouble(player.posX);
            data.writeDouble(player.posZ);
        }
    }

    public void fromBytes(ByteBuf data) {
        this.playerLocations.clear();
        int players = data.readShort();
        for (int l = 0; l < players; ++l) {
            short len = data.readShort();
            if (len < 0) continue;
            ByteBuf profileBytes = data.readBytes((int)len);
            GameProfile profile = null;
            try {
                NBTTagCompound profileData = new PacketBuffer(profileBytes).readNBTTagCompoundFromBuffer();
                profile = NBTUtil.func_152459_a((NBTTagCompound)profileData);
            }
            catch (IOException e) {
                FMLLog.severe((String)"Error reading player profile data", (Object[])new Object[0]);
                e.printStackTrace();
            }
            double posX = data.readDouble();
            double posZ = data.readDouble();
            if (profile == null) continue;
            PlayerLocationInfo playerInfo = new PlayerLocationInfo(profile, posX, posZ);
            this.playerLocations.add(playerInfo);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketUpdatePlayerLocations, IMessage> {
        public IMessage onMessage(LOTRPacketUpdatePlayerLocations packet, MessageContext context) {
            LOTRMod.proxy.clearMapPlayerLocations();
            for (PlayerLocationInfo info : packet.playerLocations) {
                LOTRMod.proxy.addMapPlayerLocation(info.playerProfile, info.posX, info.posZ);
            }
            return null;
        }
    }

    private static class PlayerLocationInfo {
        public final GameProfile playerProfile;
        public final double posX;
        public final double posZ;

        public PlayerLocationInfo(GameProfile profile, double x, double z) {
            this.playerProfile = profile;
            this.posX = x;
            this.posZ = z;
        }
    }

}

