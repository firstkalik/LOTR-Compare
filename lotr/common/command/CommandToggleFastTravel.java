/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CommandToggleFastTravel
extends CommandBase {
    public String getCommandName() {
        return "togglefasttravel";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/togglefasttravel <player> [enable/disable]";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.addChatMessage((IChatComponent)new ChatComponentText("Usage: " + this.getCommandUsage(sender)));
            return;
        }
        String playerName = args[0];
        EntityPlayerMP player = CommandToggleFastTravel.getPlayer((ICommandSender)sender, (String)playerName);
        if (player == null) {
            sender.addChatMessage((IChatComponent)new ChatComponentText("Player not found: " + playerName));
            return;
        }
        if (args.length >= 2) {
            String toggleArg = args[1];
            if (toggleArg.equalsIgnoreCase("enable")) {
                this.enableFastTravel((EntityPlayer)player);
                sender.addChatMessage((IChatComponent)new ChatComponentText("Fast travel enabled for player: " + playerName));
            } else if (toggleArg.equalsIgnoreCase("disable")) {
                this.disableFastTravel((EntityPlayer)player);
                sender.addChatMessage((IChatComponent)new ChatComponentText("Fast travel disabled for player: " + playerName));
            } else {
                sender.addChatMessage((IChatComponent)new ChatComponentText("Invalid argument: " + toggleArg));
            }
        } else {
            sender.addChatMessage((IChatComponent)new ChatComponentText("Usage: " + this.getCommandUsage(sender)));
        }
    }

    private void enableFastTravel(EntityPlayer player) {
    }

    private void disableFastTravel(EntityPlayer player) {
    }
}

