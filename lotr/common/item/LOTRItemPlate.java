/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemReed
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IRegistry
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.block.LOTRBlockPlate;
import lotr.common.dispenser.LOTRDispensePlate;
import lotr.common.entity.projectile.LOTREntityPlate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IRegistry;
import net.minecraft.world.World;

public class LOTRItemPlate
extends ItemReed {
    public Block plateBlock;

    public LOTRItemPlate(Block block) {
        super(block);
        this.plateBlock = block;
        ((LOTRBlockPlate)this.plateBlock).setPlateItem((Item)this);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
        BlockDispenser.dispenseBehaviorRegistry.putObject((Object)this, (Object)new LOTRDispensePlate(this.plateBlock));
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        LOTREntityPlate plate = new LOTREntityPlate(world, this.plateBlock, (EntityLivingBase)entityplayer);
        world.playSoundAtEntity((Entity)entityplayer, "random.bow", 1.0f, 1.0f / (itemRand.nextFloat() * 0.4f + 1.2f) + 0.25f);
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity)plate);
        }
        if (!entityplayer.capabilities.isCreativeMode) {
            --itemstack.stackSize;
        }
        return itemstack;
    }

    public boolean isValidArmor(ItemStack itemstack, int armorType, Entity entity) {
        return armorType == 0;
    }
}

