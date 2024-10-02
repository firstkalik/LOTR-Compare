/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class CommandResetHiredNPCs
extends CommandBase {
    public String getCommandName() {
        return "resetHiredNPCs";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/resetHiredNPCs <player>";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.resetHiredNPCs.usage", new Object[0]));
            return;
        }
        EntityPlayerMP targetPlayer = CommandResetHiredNPCs.getPlayer((ICommandSender)sender, (String)args[0]);
        if (targetPlayer == null) {
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.resetHiredNPCs.playerNotFound", new Object[0]));
            return;
        }
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)targetPlayer);
        sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.resetHiredNPCs.success", new Object[]{targetPlayer.getDisplayName()}));
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandResetHiredNPCs.getListOfStringsMatchingLastWord((String[])args, (String[])this.getPlayerNames());
        }
        return null;
    }

    private String[] getPlayerNames() {
        return MinecraftServer.getServer().getAllUsernames();
    }
}

