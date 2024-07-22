/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.PlayerNotFoundException
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lotr.common.LOTRLore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class LOTRCommandLore
extends CommandBase {
    public String getCommandName() {
        return "lotrLore";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.lotrLore.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length <= 1) {
            throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
        }
        if (args[0].equals("add")) {
            EntityPlayerMP entityplayer;
            if (args.length >= 3) {
                entityplayer = LOTRCommandLore.getPlayer((ICommandSender)sender, (String)args[2]);
            } else {
                entityplayer = LOTRCommandLore.getCommandSenderAsPlayer((ICommandSender)sender);
                if (entityplayer == null) {
                    throw new PlayerNotFoundException();
                }
            }
            String loreName = args[1];
            String loreNameNew = loreName.replace("_", " ");
            LOTRLore lore = LOTRLore.getLoreAsTitle(loreNameNew);
            if (lore == null) {
                throw new CommandException("commands.lotr.lotrLore.unknown", new Object[]{loreName});
            }
            ItemStack lore1 = lore.createLoreBook(entityplayer.getRNG());
            if (!entityplayer.inventory.addItemStackToInventory(lore1)) {
                entityplayer.worldObj.spawnEntityInWorld((Entity)new EntityItem(entityplayer.worldObj, entityplayer.posX + 0.5, entityplayer.posY + 1.5, entityplayer.posZ + 0.5, lore1));
            } else if (entityplayer instanceof EntityPlayerMP) {
                entityplayer.sendContainerToPlayer(entityplayer.inventoryContainer);
            }
            this.notifyCommandListener(sender, "commands.lotr.lotrLore.give " + entityplayer.getDisplayName() + " " + lore.loreTitle);
            return;
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandLore.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"add"});
        }
        if (args.length == 2) {
            Set<String> loreNames = LOTRLore.loreAndNames.keySet();
            ArrayList<String> listNew = new ArrayList<String>();
            for (String name : loreNames) {
                listNew.add(name.replace(" ", "_"));
            }
            return LOTRCommandLore.getListOfStringsMatchingLastWord((String[])args, (String[])listNew.toArray(new String[0]));
        }
        if (args.length == 3) {
            return LOTRCommandLore.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return index == 2;
    }

    public void notifyCommandListener(ICommandSender sender, String message) {
        sender.addChatMessage((IChatComponent)new ChatComponentText(message));
    }
}

