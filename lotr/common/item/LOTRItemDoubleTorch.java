/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.block.LOTRBlockDoubleTorch;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRItemDoubleTorch
extends Item {
    private Block torchBlock;

    public LOTRItemDoubleTorch(Block block) {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.torchBlock = block;
        ((LOTRBlockDoubleTorch)this.torchBlock).torchItem = this;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float f, float f1, float f2) {
        Block block = world.getBlock(i, j, k);
        if (block == Blocks.snow_layer) {
            side = 1;
        } else if (!block.isReplaceable((IBlockAccess)world, i, j, k)) {
            if (side == 0) {
                --j;
            }
            if (side == 1) {
                ++j;
            }
            if (side == 2) {
                --k;
            }
            if (side == 3) {
                ++k;
            }
            if (side == 4) {
                --i;
            }
            if (side == 5) {
                ++i;
            }
        }
        if (!entityplayer.canPlayerEdit(i, j, k, side, itemstack) || !entityplayer.canPlayerEdit(i, j + 1, k, side, itemstack)) {
            return false;
        }
        if (!world.canPlaceEntityOnSide(block, i, j, k, false, side, null, itemstack) || !world.canPlaceEntityOnSide(block, i, j + 1, k, false, side, null, itemstack)) {
            return false;
        }
        if (!LOTRBlockDoubleTorch.canPlaceTorchOn(world, i, j - 1, k)) {
            return false;
        }
        if (!world.isRemote) {
            world.setBlock(i, j, k, this.torchBlock, 0, 2);
            world.setBlock(i, j + 1, k, this.torchBlock, 1, 2);
            Block.SoundType stepSound = this.torchBlock.stepSound;
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, stepSound.func_150496_b(), (stepSound.getVolume() + 1.0f) / 2.0f, stepSound.getPitch() * 0.8f);
            --itemstack.stackSize;
        }
        return true;
    }
}

