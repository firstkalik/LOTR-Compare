/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.List;
import lotr.common.util.LOTRPlayerMoneyData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class CommandCheckBalance
extends CommandBase {
    public String getCommandName() {
        return "checkcoinbalance";
    }

    public String getCommandUsage(ICommandSender sender) {
        return StatCollector.translateToLocal((String)"checkbalance.usage");
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            throw new WrongUsageException(StatCollector.translateToLocal((String)"checkbalance.usage"), new Object[0]);
        }
        String playerName = args[0];
        EntityPlayerMP player = this.getPlayerByName(playerName);
        if (player == null) {
            ChatComponentText message = new ChatComponentText(String.format(StatCollector.translateToLocal((String)"checkbalance.playerNotFound"), playerName));
            sender.addChatMessage((IChatComponent)message);
            return;
        }
        LOTRPlayerMoneyData data = LOTRPlayerMoneyData.of((EntityPlayer)player);
        int balance = data.money;
        ChatComponentText nameComponent = new ChatComponentText(player.getCommandSenderName());
        nameComponent.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW));
        ChatComponentText message = new ChatComponentText(String.format(StatCollector.translateToLocal((String)"checkbalance.balanceMessage"), nameComponent.getFormattedText(), balance));
        sender.addChatMessage((IChatComponent)message);
    }

    private EntityPlayerMP getPlayerByName(String playerName) {
        MinecraftServer server = MinecraftServer.getServer();
        for (Object obj : server.getConfigurationManager().playerEntityList) {
            EntityPlayerMP player;
            if (!(obj instanceof EntityPlayerMP) || !(player = (EntityPlayerMP)obj).getCommandSenderName().equalsIgnoreCase(playerName)) continue;
            return player;
        }
        return null;
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            ArrayList<String> playerNames = new ArrayList<String>();
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if (!(obj instanceof EntityPlayerMP)) continue;
                playerNames.add(((EntityPlayerMP)obj).getCommandSenderName());
            }
            return CommandCheckBalance.getListOfStringsMatchingLastWord((String[])args, (String[])playerNames.toArray(new String[0]));
        }
        return null;
    }
}

