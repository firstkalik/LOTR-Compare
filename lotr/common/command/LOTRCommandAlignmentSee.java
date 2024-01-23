/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.PlayerNotFoundException
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.PlayerProfileCache
 *  net.minecraft.server.management.ServerConfigurationManager
 */
package lotr.common.command;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import java.util.UUID;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.network.LOTRPacketAlignmentSee;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;

public class LOTRCommandAlignmentSee
extends CommandBase {
    public String getCommandName() {
        return "alignmentsee";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.alignmentsee.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1) {
            String username = args[0];
            GameProfile profile = null;
            EntityPlayerMP entityplayer = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
            profile = entityplayer != null ? entityplayer.getGameProfile() : MinecraftServer.getServer().func_152358_ax().func_152655_a(username);
            if (profile == null || profile.getId() == null) {
                throw new PlayerNotFoundException("commands.lotr.alignmentsee.noPlayer", new Object[]{username});
            }
            if (sender instanceof EntityPlayerMP) {
                LOTRPlayerData playerData = LOTRLevelData.getData(profile.getId());
                LOTRPacketAlignmentSee packet = new LOTRPacketAlignmentSee(username, playerData);
                LOTRPacketHandler.networkWrapper.sendTo((IMessage)packet, (EntityPlayerMP)sender);
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandAlignmentSee.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return i == 0;
    }
}

