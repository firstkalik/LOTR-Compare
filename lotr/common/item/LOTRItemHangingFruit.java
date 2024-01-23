/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.item;

import lotr.common.item.LOTRItemFood;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRItemHangingFruit
extends LOTRItemFood {
    private Block fruitBlock;

    public LOTRItemHangingFruit(int i, float f, boolean flag, Block block) {
        super(i, f, flag);
        this.fruitBlock = block;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float hitX, float hitY, float hitZ) {
        if (world.getBlock(i, j, k).isWood((IBlockAccess)world, i, j, k)) {
            if (side == 0 || side == 1) {
                return false;
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
            if (world.isAirBlock(i, j, k)) {
                int meta = ForgeDirection.getOrientation((int)(side - 2)).getOpposite().ordinal();
                world.setBlock(i, j, k, this.fruitBlock, meta, 3);
                if (!entityplayer.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                }
            }
            return true;
        }
        return false;
    }
}

