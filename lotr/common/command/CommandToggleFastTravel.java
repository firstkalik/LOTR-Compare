/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.PlayerNotFoundException
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class CommandToggleFastTravel
extends CommandBase {
    public String getCommandName() {
        return "togglefasttravel";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/togglefasttravel <player>";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            throw new WrongUsageException("Usage: /togglefasttravel <player>", new Object[0]);
        }
        EntityPlayerMP targetPlayer = CommandToggleFastTravel.getPlayer((ICommandSender)sender, (String)args[0]);
        if (targetPlayer == null) {
            throw new PlayerNotFoundException();
        }
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)targetPlayer);
        boolean newState = !playerData.isFastTravelDisabled();
        playerData.setFastTravelDisabled(newState);
        String formattedPlayerName = (Object)EnumChatFormatting.YELLOW + targetPlayer.getDisplayName() + (Object)EnumChatFormatting.RESET;
        ChatComponentTranslation message = new ChatComponentTranslation(newState ? "lotr.fasttravel.disabled.set" : "lotr.fasttravel.disabled.unset", new Object[]{formattedPlayerName});
        sender.addChatMessage((IChatComponent)message);
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? CommandToggleFastTravel.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames()) : null;
    }
}

