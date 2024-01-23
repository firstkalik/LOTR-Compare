/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.entity.projectile.LOTREntityPebble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemSling
extends Item {
    public LOTRItemSling() {
        this.setMaxStackSize(1);
        this.setMaxDamage(250);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.inventory.hasItem(LOTRMod.pebble) || entityplayer.capabilities.isCreativeMode) {
            itemstack.damageItem(1, (EntityLivingBase)entityplayer);
            if (!entityplayer.capabilities.isCreativeMode) {
                entityplayer.inventory.consumeInventoryItem(LOTRMod.pebble);
            }
            world.playSoundAtEntity((Entity)entityplayer, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
            if (!world.isRemote) {
                world.spawnEntityInWorld((Entity)new LOTREntityPebble(world, (EntityLivingBase)entityplayer).setSling());
            }
        }
        return itemstack;
    }

    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == Items.leather ? true : super.getIsRepairable(itemstack, repairItem);
    }
}

