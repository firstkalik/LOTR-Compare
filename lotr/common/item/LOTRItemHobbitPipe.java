/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.FoodStats
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.projectile.LOTREntitySmokeRing;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemHobbitPipe
extends Item {
    public static final int MAGIC_COLOR = 16;

    public LOTRItemHobbitPipe() {
        this.setMaxDamage(300);
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.inventory.hasItem(LOTRMod.pipeweed) || entityplayer.capabilities.isCreativeMode) {
            itemstack.damageItem(1, (EntityLivingBase)entityplayer);
            if (!entityplayer.capabilities.isCreativeMode) {
                entityplayer.inventory.consumeInventoryItem(LOTRMod.pipeweed);
            }
            if (entityplayer.canEat(false)) {
                entityplayer.getFoodStats().addStats(2, 0.3f);
            }
            if (!world.isRemote) {
                LOTREntitySmokeRing smoke = new LOTREntitySmokeRing(world, (EntityLivingBase)entityplayer);
                int color = LOTRItemHobbitPipe.getSmokeColor(itemstack);
                smoke.setSmokeColour(color);
                world.spawnEntityInWorld((Entity)smoke);
                if (color == 16) {
                    LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.useMagicPipe);
                }
            }
            world.playSoundAtEntity((Entity)entityplayer, "lotr:item.puff", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        }
        return itemstack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.inventory.hasItem(LOTRMod.pipeweed) || entityplayer.capabilities.isCreativeMode) {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }

    public static int getSmokeColor(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("SmokeColour")) {
            return itemstack.getTagCompound().getInteger("SmokeColour");
        }
        return 0;
    }

    public static void setSmokeColor(ItemStack itemstack, int i) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setInteger("SmokeColour", i);
    }

    public static boolean isPipeDyed(ItemStack itemstack) {
        int color = LOTRItemHobbitPipe.getSmokeColor(itemstack);
        return color != 0 && color != 16;
    }

    public static void removePipeDye(ItemStack itemstack) {
        LOTRItemHobbitPipe.setSmokeColor(itemstack, 0);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        int color = itemstack.getTagCompound() != null ? itemstack.getTagCompound().getInteger("SmokeColour") : 0;
        String colorName = StatCollector.translateToLocal((String)(this.getUnlocalizedName() + ".subtitle." + color));
        String coloredText = "";
        switch (color) {
            case 0: {
                coloredText = "\u00a7f" + colorName;
                break;
            }
            case 1: {
                coloredText = "\u00a76" + colorName;
                break;
            }
            case 2: {
                coloredText = "\u00a7d" + colorName;
                break;
            }
            case 3: {
                coloredText = "\u00a7b" + colorName;
                break;
            }
            case 4: {
                coloredText = "\u00a7e" + colorName;
                break;
            }
            case 5: {
                coloredText = "\u00a7a" + colorName;
                break;
            }
            case 6: {
                coloredText = "\u00a75" + colorName;
                break;
            }
            case 7: {
                coloredText = "\u00a78" + colorName;
                break;
            }
            case 8: {
                coloredText = "\u00a77" + colorName;
                break;
            }
            case 9: {
                coloredText = "\u00a79" + colorName;
                break;
            }
            case 10: {
                coloredText = "\u00a7d" + colorName;
                break;
            }
            case 11: {
                coloredText = "\u00a71" + colorName;
                break;
            }
            case 12: {
                coloredText = "\u00a76" + colorName;
                break;
            }
            case 13: {
                coloredText = "\u00a72" + colorName;
                break;
            }
            case 14: {
                coloredText = "\u00a7c" + colorName;
                break;
            }
            case 15: {
                coloredText = "\u00a78" + colorName;
                break;
            }
            case 16: {
                coloredText = "\u00a7e" + colorName;
                break;
            }
            default: {
                coloredText = "\u00a7f" + colorName;
            }
        }
        list.add(coloredText);
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= 16; ++i) {
            ItemStack itemstack = new ItemStack((Item)this);
            LOTRItemHobbitPipe.setSmokeColor(itemstack, i);
            list.add(itemstack);
        }
    }
}

