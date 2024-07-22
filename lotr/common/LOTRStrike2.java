/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTRStrike2
extends CommandBase {
    public String getCommandName() {
        return "blow";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/blow <player>";
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new CommandException(this.getCommandUsage(sender), new Object[0]);
        }
        String playerName = args[0];
        EntityPlayerMP player = LOTRStrike2.getPlayer((ICommandSender)sender, (String)playerName);
        if (player == null) {
            throw new CommandException("Player not found: " + playerName, new Object[0]);
        }
        sender.addChatMessage((IChatComponent)new ChatComponentText("Triggering instant explosion for player: " + playerName));
        this.triggerInstantExplosion((EntityPlayer)player);
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    private void triggerInstantExplosion(EntityPlayer player) {
        World world = player.worldObj;
        float explosionSize = 4.0f;
        world.createExplosion((Entity)player, player.posX, player.posY, player.posZ, explosionSize, true);
        player.setHealth(0.0f);
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return this.getListOfPlayerUsernames();
        }
        return null;
    }

    private List<String> getListOfPlayerUsernames() {
        ArrayList<String> usernames = new ArrayList<String>();
        for (Object player : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            usernames.add(((EntityPlayer)player).getCommandSenderName());
        }
        return usernames;
    }
}

