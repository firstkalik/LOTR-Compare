/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.quest.IPickpocketable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class LOTRItemCoin
extends Item {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] coinIcons;
    public static int[] values = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};

    public LOTRItemCoin() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
    }

    private static int getSingleItemValue(ItemStack itemstack, boolean allowStolen) {
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemCoin) {
            if (!allowStolen && IPickpocketable.Helper.isPickpocketed(itemstack)) {
                return 0;
            }
            int i = itemstack.getItemDamage();
            if (i >= values.length) {
                i = 0;
            }
            return values[i];
        }
        return 0;
    }

    public static int getStackValue(ItemStack itemstack, boolean allowStolen) {
        if (itemstack == null) {
            return 0;
        }
        return LOTRItemCoin.getSingleItemValue(itemstack, allowStolen) * itemstack.stackSize;
    }

    public static int getInventoryValue(EntityPlayer entityplayer, boolean allowStolen) {
        int coins = 0;
        for (ItemStack itemstack : entityplayer.inventory.mainInventory) {
            coins += LOTRItemCoin.getStackValue(itemstack, allowStolen);
        }
        return coins += LOTRItemCoin.getStackValue(entityplayer.inventory.getItemStack(), allowStolen);
    }

    public static int getContainerValue(IInventory inv, boolean allowStolen) {
        if (inv instanceof InventoryPlayer) {
            return LOTRItemCoin.getInventoryValue(((InventoryPlayer)inv).player, allowStolen);
        }
        int coins = 0;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            coins += LOTRItemCoin.getStackValue(itemstack, allowStolen);
        }
        return coins;
    }

    public static void takeCoins(int coins, EntityPlayer entityplayer) {
        ItemStack itemstack;
        ItemStack coin;
        int value;
        ItemStack is;
        int slot;
        int i;
        InventoryPlayer inv = entityplayer.inventory;
        int invValue = LOTRItemCoin.getInventoryValue(entityplayer, false);
        if (invValue < coins) {
            FMLLog.warning((String)("Attempted to take " + coins + " coins from player " + entityplayer.getCommandSenderName() + " who has only " + invValue), (Object[])new Object[0]);
        }
        int initCoins = coins;
        block0: for (i = values.length - 1; i >= 0; --i) {
            value = values[i];
            if (value > initCoins) continue;
            coin = new ItemStack(LOTRMod.silverCoin, 1, i);
            for (slot = -1; slot < inv.mainInventory.length; ++slot) {
                while ((itemstack = slot == -1 ? inv.getItemStack() : inv.mainInventory[slot]) != null && itemstack.isItemEqual(coin)) {
                    if (slot == -1) {
                        is = inv.getItemStack();
                        if (is != null) {
                            --is.stackSize;
                            if (is.stackSize <= 0) {
                                inv.setItemStack(null);
                            }
                        }
                    } else {
                        inv.decrStackSize(slot, 1);
                    }
                    if ((coins -= value) < value) continue block0;
                }
            }
        }
        if (coins > 0) {
            for (i = 0; i < values.length; ++i) {
                if (i == 0) continue;
                value = values[i];
                coin = new ItemStack(LOTRMod.silverCoin, 1, i);
                block4: for (slot = -1; slot < inv.mainInventory.length; ++slot) {
                    while ((itemstack = slot == -1 ? inv.getItemStack() : inv.mainInventory[slot]) != null && itemstack.isItemEqual(coin)) {
                        if (slot == -1) {
                            is = inv.getItemStack();
                            if (is != null) {
                                --is.stackSize;
                                if (is.stackSize <= 0) {
                                    inv.setItemStack(null);
                                }
                            }
                        } else {
                            inv.decrStackSize(slot, 1);
                        }
                        if ((coins -= value) < 0) break block4;
                    }
                }
                if (coins < 0) break;
            }
        }
        if (coins < 0) {
            LOTRItemCoin.giveCoins(-coins, entityplayer);
        }
    }

    public static void giveCoins(int coins, EntityPlayer entityplayer) {
        int value;
        int i;
        ItemStack coin;
        InventoryPlayer inv = entityplayer.inventory;
        if (coins <= 0) {
            FMLLog.warning((String)("Attempted to give a non-positive value of coins " + coins + " to player " + entityplayer.getCommandSenderName()), (Object[])new Object[0]);
        }
        for (i = values.length - 1; i >= 0; --i) {
            value = values[i];
            coin = new ItemStack(LOTRMod.silverCoin, 1, i);
            while (coins >= value && inv.addItemStackToInventory(coin.copy())) {
                coins -= value;
            }
        }
        if (coins > 0) {
            for (i = values.length - 1; i >= 0; --i) {
                value = values[i];
                coin = new ItemStack(LOTRMod.silverCoin, 1, i);
                while (coins >= value) {
                    entityplayer.dropPlayerItemWithRandomChoice(coin.copy(), false);
                    coins -= value;
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int i) {
        if (i >= this.coinIcons.length) {
            i = 0;
        }
        return this.coinIcons[i];
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        int i = itemstack.getItemDamage();
        if (i >= values.length) {
            i = 0;
        }
        return super.getUnlocalizedName() + "." + values[i];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        this.coinIcons = new IIcon[values.length];
        for (int i = 0; i < values.length; ++i) {
            this.coinIcons[i] = iconregister.registerIcon(this.getIconString() + "_" + values[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean advanced) {
        int i = itemstack.getItemDamage();
        if (i >= values.length) {
            i = 0;
        }
        String localizedText = StatCollector.translateToLocal((String)"item.lotrCoin.number");
        list.add((Object)EnumChatFormatting.GRAY + String.format(localizedText, (Object)EnumChatFormatting.YELLOW + String.valueOf(values[i]) + (Object)EnumChatFormatting.GRAY));
        int stackValue = LOTRItemCoin.getStackValue(itemstack, false);
        String stackValueText = StatCollector.translateToLocal((String)"item.lotrCoin.total");
        list.add((Object)EnumChatFormatting.GRAY + String.format(stackValueText, (Object)EnumChatFormatting.GOLD + String.valueOf(stackValue) + (Object)EnumChatFormatting.GRAY));
    }

    public static int getMetaIndexForCost(int cost) {
        for (int i = values.length - 1; i >= 0; --i) {
            if (cost < values[i]) continue;
            return i;
        }
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j < values.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
}

