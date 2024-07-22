/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.command.LOTRCommandFellowship;
import lotr.common.fellowship.LOTRFellowship;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class LOTRCommandFellowshipMessage
extends CommandBase {
    public String getCommandName() {
        return "fmsg";
    }

    public List getCommandAliases() {
        return Arrays.asList("fchat");
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        if (sender instanceof EntityPlayer) {
            return true;
        }
        return super.canCommandSenderUseCommand(sender);
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.fmsg.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP entityplayer = CommandBase.getCommandSenderAsPlayer((ICommandSender)sender);
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
        if (args.length >= 1) {
            if (args[0].equals("bind") && args.length >= 2) {
                String fsName = (args = LOTRCommandFellowship.fixArgsForFellowship(args, 1, false))[1];
                LOTRFellowship fellowship = playerData.getFellowshipByName(fsName);
                if (fellowship != null && !fellowship.isDisbanded() && fellowship.containsPlayer(entityplayer.getUniqueID())) {
                    playerData.setChatBoundFellowship(fellowship);
                    ChatComponentTranslation notif = new ChatComponentTranslation("commands.lotr.fmsg.bind", new Object[]{fellowship.getName()});
                    notif.getChatStyle().setColor(EnumChatFormatting.GRAY);
                    notif.getChatStyle().setItalic(Boolean.valueOf(true));
                    sender.addChatMessage((IChatComponent)notif);
                    return;
                }
                throw new WrongUsageException("commands.lotr.fmsg.notFound", new Object[]{fsName});
            }
            if (args[0].equals("unbind")) {
                LOTRFellowship preBoundFellowship = playerData.getChatBoundFellowship();
                playerData.setChatBoundFellowshipID(null);
                ChatComponentTranslation notif = new ChatComponentTranslation("commands.lotr.fmsg.unbind", new Object[]{preBoundFellowship.getName()});
                notif.getChatStyle().setColor(EnumChatFormatting.GRAY);
                notif.getChatStyle().setItalic(Boolean.valueOf(true));
                sender.addChatMessage((IChatComponent)notif);
                return;
            }
            LOTRFellowship fellowship = null;
            int msgStartIndex = 0;
            if (args[0].startsWith("\"")) {
                String fsName = (args = LOTRCommandFellowship.fixArgsForFellowship(args, 0, false))[0];
                fellowship = playerData.getFellowshipByName(fsName);
                if (fellowship == null) {
                    throw new WrongUsageException("commands.lotr.fmsg.notFound", new Object[]{fsName});
                }
                msgStartIndex = 1;
            }
            if (fellowship == null) {
                fellowship = playerData.getChatBoundFellowship();
                if (fellowship == null) {
                    throw new WrongUsageException("commands.lotr.fmsg.boundNone", new Object[0]);
                }
                if (fellowship.isDisbanded() || !fellowship.containsPlayer(entityplayer.getUniqueID())) {
                    throw new WrongUsageException("commands.lotr.fmsg.boundNotMember", new Object[]{fellowship.getName()});
                }
            }
            if (fellowship != null) {
                IChatComponent message = CommandBase.func_147176_a((ICommandSender)sender, (String[])args, (int)msgStartIndex, (boolean)false);
                fellowship.sendFellowshipMessage(entityplayer, message.getUnformattedText());
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)CommandBase.getCommandSenderAsPlayer((ICommandSender)sender));
        String[] argsOriginal = Arrays.copyOf(args, args.length);
        if (args.length >= 2 && args[0].equals("bind")) {
            args = LOTRCommandFellowship.fixArgsForFellowship(args, 1, true);
            return LOTRCommandFellowship.listFellowshipsMatchingLastWord(args, argsOriginal, 1, playerData, false);
        }
        if (args.length >= 1) {
            args = LOTRCommandFellowship.fixArgsForFellowship(args, 0, true);
            ArrayList<String> matches = new ArrayList<String>();
            if (args.length == 1 && !argsOriginal[0].startsWith("\"")) {
                matches.addAll(CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"bind", "unbind"}));
            }
            matches.addAll(LOTRCommandFellowship.listFellowshipsMatchingLastWord(args, argsOriginal, 0, playerData, false));
            return matches;
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}

