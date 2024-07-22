/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.FoodStats
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRFaction;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class LOTRItemMorgulShroom4
extends Item {
    public LOTRItemMorgulShroom4() {
        this.setMaxDamage(0);
        this.setMaxStackSize(64);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        --itemstack.stackSize;
        if (LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.HIGH_ELF) > -1.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.WOOD_ELF) > -1.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.AVARI) > -1.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.LOTHLORIEN) > -1.0f) {
            entityplayer.getFoodStats().addStats(5, 1.0f);
        } else if (!world.isRemote) {
            entityplayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 480));
            entityplayer.getFoodStats().addStats(3, 0.5f);
        }
        if (!world.isRemote && this == LOTRMod.lembasNugget) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.eatlembasNugget);
        }
        if (LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.HIGH_ELF) > 1000.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.WOOD_ELF) > 1000.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.AVARI) > 1000.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.LOTHLORIEN) > 1000.0f) {
            entityplayer.getFoodStats().addStats(8, 2.0f);
        }
        world.playSoundAtEntity((Entity)entityplayer, "random.burp", 0.5f, world.rand.nextFloat() * 0.1f + 0.9f);
        return itemstack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 30;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.eat;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.canEat(false)) {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }
}

