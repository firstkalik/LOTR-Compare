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
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.FoodStats
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.entity.projectile.LOTREntitySmokeRing;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemRadaghastRing
extends Item {
    public LOTRItemRadaghastRing() {
        this.setMaxStackSize(1);
        this.setMaxDamage(300);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int timeLeft) {
        int useDuration = this.getMaxItemUseDuration(itemstack) - timeLeft;
        if (useDuration >= 20) {
            itemstack.damageItem(1, (EntityLivingBase)entityplayer);
            if (!world.isRemote) {
                if (entityplayer.canEat(false)) {
                    entityplayer.getFoodStats().addStats(2, 0.3f);
                }
                LOTREntitySmokeRing smoke = new LOTREntitySmokeRing(world, (EntityLivingBase)entityplayer);
                int color = LOTRItemRadaghastRing.getNextSmokeColor(itemstack);
                smoke.setSmokeColour(color);
                world.spawnEntityInWorld((Entity)smoke);
            }
            world.playSoundAtEntity((Entity)entityplayer, "lotr:item.puff", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
            float chance = 0.15f;
            if (world.rand.nextFloat() < chance) {
                if (!world.isRemote) {
                    entityplayer.addPotionEffect(new PotionEffect(9, 120, 9));
                    entityplayer.addPotionEffect(new PotionEffect(47, 120, 0));
                }
                world.playSoundAtEntity((Entity)entityplayer, "lotr:item.gimli", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
            }
        }
    }

    public static int getNextSmokeColor(ItemStack itemstack) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        int currentColor = itemstack.getTagCompound().getInteger("SmokeColour");
        int nextColor = (currentColor + 1) % 17;
        itemstack.getTagCompound().setInteger("SmokeColour", nextColor);
        return nextColor;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("SmokeColour")) {
            int color = itemstack.getTagCompound().getInteger("SmokeColour");
            String colorName = StatCollector.translateToLocal((String)("item.lotr:hobbitPipe.subtitle." + color));
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
        list.add((Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"lotr.ring.ready", (Object[])new Object[0]));
    }
}

