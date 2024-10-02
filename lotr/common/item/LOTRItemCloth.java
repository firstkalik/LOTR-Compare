/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPotions;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LOTRItemCloth
extends Item {
    public LOTRItemCloth() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
        this.maxStackSize = 16;
    }

    public ItemStack onEaten(ItemStack item, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            --item.stackSize;
        }
        if (!world.isRemote) {
            player.removePotionEffect(36);
        }
        player.removePotionEffect(41);
        player.addPotionEffect(new PotionEffect(10, 600, 0));
        world.playSoundAtEntity((Entity)player, "lotr:bandage", 1.0f, world.rand.nextFloat() * 0.1f + 0.9f);
        LOTRLevelData.getData(player).addAchievement(LOTRAchievement.useBandage);
        return item;
    }

    public int getMaxItemUseDuration(ItemStack item) {
        return 30;
    }

    public EnumAction getItemUseAction(ItemStack item) {
        return EnumAction.bow;
    }

    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
        Potion brokenEffect = Potion.potionTypes[41];
        if (player.getActivePotionEffect(LOTRPotions.blood) != null || player.getActivePotionEffect(brokenEffect) != null) {
            player.setItemInUse(item, this.getMaxItemUseDuration(item));
        }
        return item;
    }
}

