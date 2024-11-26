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
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class CommandSetAlcoholTolerance
extends CommandBase {
    public String getCommandName() {
        return "setAlcoholTolerance";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "command.setAlcoholTolerance.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        int tolerance;
        if (args.length != 2) {
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("command.setAlcoholTolerance.invalidArgs", new Object[0]));
            return;
        }
        String playerName = args[0];
        EntityPlayerMP player = CommandSetAlcoholTolerance.getPlayer((ICommandSender)sender, (String)playerName);
        if (player == null) {
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("command.setAlcoholTolerance.playerNotFound", new Object[]{playerName}));
            return;
        }
        try {
            tolerance = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            sender.addChatMessage((IChatComponent)new ChatComponentTranslation("command.setAlcoholTolerance.invalidValue", new Object[0]));
            return;
        }
        LOTRLevelData.getData((EntityPlayer)player).setAlcoholTolerance(tolerance);
        sender.addChatMessage((IChatComponent)new ChatComponentTranslation("command.setAlcoholTolerance.success", new Object[]{playerName, tolerance}));
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? CommandSetAlcoholTolerance.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames()) : null;
    }
}

