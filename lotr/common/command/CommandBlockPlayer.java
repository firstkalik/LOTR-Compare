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
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package lotr.common.command;

import java.util.ArrayList;
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
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class CommandBlockPlayer
extends CommandBase {
    public String getCommandName() {
        return "blockplayer";
    }

    public String getCommandUsage(ICommandSender sender) {
        return StatCollector.translateToLocal((String)"command.blockplayer.usage");
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 2) {
            throw new WrongUsageException(StatCollector.translateToLocal((String)"command.blockplayer.usage"), new Object[0]);
        }
        String action = args[0];
        EntityPlayerMP targetPlayer = CommandBlockPlayer.getPlayer((ICommandSender)sender, (String)args[1]);
        if (targetPlayer == null) {
            throw new PlayerNotFoundException();
        }
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)targetPlayer);
        switch (action.toLowerCase()) {
            case "add": {
                playerData.setBlocked(true);
                sender.addChatMessage((IChatComponent)new ChatComponentTranslation("command.blockplayer.add", new Object[]{this.formatPlayerName(targetPlayer.getDisplayName())}));
                break;
            }
            case "remove": {
                playerData.setBlocked(false);
                sender.addChatMessage((IChatComponent)new ChatComponentTranslation("command.blockplayer.remove", new Object[]{this.formatPlayerName(targetPlayer.getDisplayName())}));
                break;
            }
            default: {
                throw new WrongUsageException(StatCollector.translateToLocal((String)"command.blockplayer.usage"), new Object[0]);
            }
        }
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

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandBlockPlayer.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"add", "remove"});
        }
        if (args.length == 2 && ("add".equalsIgnoreCase(args[0]) || "remove".equalsIgnoreCase(args[0]))) {
            ArrayList<String> playerNames = new ArrayList<String>();
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if (!(obj instanceof EntityPlayerMP)) continue;
                playerNames.add(((EntityPlayerMP)obj).getCommandSenderName());
            }
            return CommandBlockPlayer.getListOfStringsMatchingLastWord((String[])args, (String[])playerNames.toArray(new String[0]));
        }
        return null;
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    private String formatPlayerName(String playerName) {
        return (Object)EnumChatFormatting.YELLOW + playerName + (Object)EnumChatFormatting.RESET;
    }
}

