/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class CommandDamage
extends CommandBase {
    public String getCommandName() {
        return "damage";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/damage <player> <amount> [damageType]";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        int damageAmount;
        if (args.length < 2) {
            sender.addChatMessage((IChatComponent)new ChatComponentText("\u043f\u0457\u0405cUsage: " + this.getCommandUsage(sender)));
            return;
        }
        EntityPlayerMP targetPlayer = CommandDamage.getPlayer((ICommandSender)sender, (String)args[0]);
        if (targetPlayer == null) {
            sender.addChatMessage((IChatComponent)new ChatComponentText("\u043f\u0457\u0405cPlayer not found."));
            return;
        }
        try {
            damageAmount = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            sender.addChatMessage((IChatComponent)new ChatComponentText("\u043f\u0457\u0405cDamage amount must be a number."));
            return;
        }
        String damageType = args.length >= 3 ? args[2] : "generic";
        targetPlayer.attackEntityFrom(this.getDamageSource(damageType, sender), (float)damageAmount);
        sender.addChatMessage((IChatComponent)new ChatComponentText("\u043f\u0457\u0405aDealt " + damageAmount + " damage to " + targetPlayer.getDisplayName() + " with type '" + damageType + "'."));
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandDamage.getListOfStringsFromIterableMatchingLastWord((String[])args, this.getPlayerNames());
        }
        if (args.length == 3) {
            return CommandDamage.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"generic", "fire", "magic", "explosion"});
        }
        return null;
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    private DamageSource getDamageSource(String type, ICommandSender sender) {
        switch (type) {
            case "fire": {
                return DamageSource.inFire;
            }
            case "magic": {
                return DamageSource.magic;
            }
        }
        return DamageSource.generic;
    }

    private List<String> getPlayerNames() {
        ArrayList<String> playerNames = new ArrayList<String>();
        for (Object playerObj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            EntityPlayer player = (EntityPlayer)playerObj;
            playerNames.add(player.getCommandSenderName());
        }
        return playerNames;
    }
}

