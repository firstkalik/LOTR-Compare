/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IRegistry
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.dispenser.LOTRDispenseConker;
import lotr.common.entity.projectile.LOTREntityConker;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IRegistry;
import net.minecraft.world.World;

public class LOTRItemConker
extends Item {
    public LOTRItemConker() {
        this.setMaxStackSize(16);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
        BlockDispenser.dispenseBehaviorRegistry.putObject((Object)this, (Object)new LOTRDispenseConker());
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity)new LOTREntityConker(world, (EntityLivingBase)entityplayer));
            world.playSoundAtEntity((Entity)entityplayer, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
            if (!entityplayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
        }
        return itemstack;
    }
}

