/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package lotr.common;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class LOTRStrike
extends CommandBase {
    public String getCommandName() {
        return "strike";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/strike <player>";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRStrike.getListOfStringsMatchingLastWord((String[])args, (String[])this.getPlayerNames(sender));
        }
        return null;
    }

    private String[] getPlayerNames(ICommandSender sender) {
        ArrayList<String> playerNames = new ArrayList<String>();
        for (Object player : sender.getEntityWorld().playerEntities) {
            playerNames.add(((EntityPlayer)player).getCommandSenderName());
        }
        return playerNames.toArray(new String[0]);
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.addChatMessage((IChatComponent)new ChatComponentText("Usage: " + this.getCommandUsage(sender)));
            return;
        }
        String playerName = args[0];
        EntityPlayerMP player = LOTRStrike.getPlayer((ICommandSender)sender, (String)playerName);
        if (player != null) {
            EntityLightningBolt lightning = new EntityLightningBolt(player.worldObj, player.posX, player.posY, player.posZ);
            player.worldObj.addWeatherEffect((Entity)lightning);
            sender.addChatMessage((IChatComponent)new ChatComponentText("Player " + playerName + " has been struck by lightning!"));
        } else {
            sender.addChatMessage((IChatComponent)new ChatComponentText("Player " + playerName + " not found."));
        }
    }
}

