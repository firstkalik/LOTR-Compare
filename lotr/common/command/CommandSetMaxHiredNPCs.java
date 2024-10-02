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

public class CommandSetMaxHiredNPCs
extends CommandBase {
    public String getCommandName() {
        return "setMaxHiredNPCs";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/setMaxHiredNPCs <player> [amount]";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1 || args.length > 2) {
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.setMaxHiredNPCs.usage", new Object[0]));
            return;
        }
        EntityPlayerMP targetPlayer = CommandSetMaxHiredNPCs.getPlayer((ICommandSender)sender, (String)args[0]);
        if (targetPlayer == null) {
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.setMaxHiredNPCs.playerNotFound", new Object[0]));
            return;
        }
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)targetPlayer);
        if (args.length == 1) {
            playerData.setCustomMaxHiredNPCs(0);
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.setMaxHiredNPCs.reset", new Object[]{targetPlayer.getDisplayName()}));
        } else {
            int amount;
            try {
                amount = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.setMaxHiredNPCs.invalidAmount", new Object[0]));
                return;
            }
            playerData.setCustomMaxHiredNPCs(amount);
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.setMaxHiredNPCs.success", new Object[]{targetPlayer.getDisplayName(), amount}));
        }
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandSetMaxHiredNPCs.getListOfStringsMatchingLastWord((String[])args, (String[])this.getPlayerNames());
        }
        return null;
    }

    private String[] getPlayerNames() {
        return MinecraftServer.getServer().getAllUsernames();
    }
}

