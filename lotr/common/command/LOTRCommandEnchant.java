/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.List;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class LOTRCommandEnchant
extends CommandBase {
    public String getCommandName() {
        return "lotrEnchant";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.lotrEnchant.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 2) {
            EntityPlayerMP entityplayer = LOTRCommandEnchant.getPlayer((ICommandSender)sender, (String)args[0]);
            ItemStack itemstack = entityplayer.getHeldItem();
            if (itemstack == null) {
                throw new WrongUsageException("commands.lotr.lotrEnchant.noItem", new Object[0]);
            }
            String option = args[1];
            if (option.equals("add") && args.length >= 3) {
                String enchName = args[2];
                LOTREnchantment ench = LOTREnchantment.getEnchantmentByName(enchName);
                if (ench != null) {
                    if (!LOTREnchantmentHelper.hasEnchant(itemstack, ench) && ench.canApply(itemstack, false) && LOTREnchantmentHelper.checkEnchantCompatible(itemstack, ench)) {
                        LOTREnchantmentHelper.setHasEnchant(itemstack, ench);
                        LOTRCommandEnchant.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrEnchant.add", (Object[])new Object[]{enchName, entityplayer.getCommandSenderName(), itemstack.getDisplayName()});
                        return;
                    }
                    throw new WrongUsageException("commands.lotr.lotrEnchant.cannotAdd", new Object[]{enchName, itemstack.getDisplayName()});
                }
                throw new WrongUsageException("commands.lotr.lotrEnchant.unknown", new Object[]{enchName});
            }
            if (option.equals("remove") && args.length >= 3) {
                String enchName = args[2];
                LOTREnchantment ench = LOTREnchantment.getEnchantmentByName(enchName);
                if (ench != null) {
                    if (LOTREnchantmentHelper.hasEnchant(itemstack, ench)) {
                        LOTREnchantmentHelper.removeEnchant(itemstack, ench);
                        LOTRCommandEnchant.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrEnchant.remove", (Object[])new Object[]{enchName, entityplayer.getCommandSenderName(), itemstack.getDisplayName()});
                        return;
                    }
                    throw new WrongUsageException("commands.lotr.lotrEnchant.cannotRemove", new Object[]{enchName, itemstack.getDisplayName()});
                }
                throw new WrongUsageException("commands.lotr.lotrEnchant.unknown", new Object[]{enchName});
            }
            if (option.equals("clear")) {
                LOTREnchantmentHelper.clearEnchantsAndProgress(itemstack);
                LOTRCommandEnchant.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrEnchant.clear", (Object[])new Object[]{entityplayer.getCommandSenderName(), itemstack.getDisplayName()});
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandEnchant.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames());
        }
        if (args.length == 2) {
            return LOTRCommandEnchant.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"add", "remove", "clear"});
        }
        if (args.length == 3) {
            ItemStack itemstack;
            EntityPlayerMP entityplayer;
            if (args[1].equals("add")) {
                EntityPlayerMP entityplayer2 = LOTRCommandEnchant.getPlayer((ICommandSender)sender, (String)args[0]);
                ItemStack itemstack2 = entityplayer2.getHeldItem();
                if (itemstack2 != null) {
                    ArrayList<String> enchNames = new ArrayList<String>();
                    for (LOTREnchantment ench : LOTREnchantment.allEnchantments) {
                        if (LOTREnchantmentHelper.hasEnchant(itemstack2, ench) || !ench.canApply(itemstack2, false) || !LOTREnchantmentHelper.checkEnchantCompatible(itemstack2, ench)) continue;
                        enchNames.add(ench.enchantName);
                    }
                    return LOTRCommandEnchant.getListOfStringsMatchingLastWord((String[])args, (String[])enchNames.toArray(new String[0]));
                }
            } else if (args[1].equals("remove") && (itemstack = (entityplayer = LOTRCommandEnchant.getPlayer((ICommandSender)sender, (String)args[0])).getHeldItem()) != null) {
                ArrayList<String> enchNames = new ArrayList<String>();
                for (LOTREnchantment ench : LOTREnchantment.allEnchantments) {
                    if (!LOTREnchantmentHelper.hasEnchant(itemstack, ench)) continue;
                    enchNames.add(ench.enchantName);
                }
                return LOTRCommandEnchant.getListOfStringsMatchingLastWord((String[])args, (String[])enchNames.toArray(new String[0]));
            }
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return i == 1;
    }
}

