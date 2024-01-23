/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.block.LOTRBlockBed;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRItemBed
extends Item {
    private LOTRBlockBed theBedBlock;

    public LOTRItemBed(Block block) {
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
        this.theBedBlock = (LOTRBlockBed)block;
        this.theBedBlock.bedItem = this;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float f, float f1, float f2) {
        if (world.isRemote) {
            return true;
        }
        if (side != 1) {
            return false;
        }
        ++j;
        int i1 = MathHelper.floor_double((double)((double)(entityplayer.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        int b0 = 0;
        int b1 = 0;
        if (i1 == 0) {
            b1 = 1;
        }
        if (i1 == 1) {
            b0 = -1;
        }
        if (i1 == 2) {
            b1 = -1;
        }
        if (i1 == 3) {
            b0 = 1;
        }
        if (entityplayer.canPlayerEdit(i, j, k, side, itemstack) && entityplayer.canPlayerEdit(i + b0, j, k + b1, side, itemstack)) {
            if (world.isAirBlock(i, j, k) && world.isAirBlock(i + b0, j, k + b1) && world.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP) && world.getBlock(i + b0, j - 1, k + b1).isSideSolid((IBlockAccess)world, i + b0, j - 1, k + b1, ForgeDirection.UP)) {
                world.setBlock(i, j, k, (Block)this.theBedBlock, i1, 3);
                if (world.getBlock(i, j, k) == this.theBedBlock) {
                    world.setBlock(i + b0, j, k + b1, (Block)this.theBedBlock, i1 + 8, 3);
                }
                world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, this.theBedBlock.stepSound.func_150496_b(), (this.theBedBlock.stepSound.getVolume() + 1.0f) / 2.0f, this.theBedBlock.stepSound.getPitch() * 0.8f);
                --itemstack.stackSize;
                return true;
            }
            return false;
        }
        return false;
    }
}

