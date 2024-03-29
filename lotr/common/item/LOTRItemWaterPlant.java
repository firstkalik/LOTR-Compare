/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package lotr.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTRItemWaterPlant
extends ItemBlock {
    public LOTRItemWaterPlant(Block block) {
        super(block);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        return LOTRItemWaterPlant.tryPlaceWaterPlant(this, itemstack, world, entityplayer, this.getMovingObjectPositionFromPlayer(world, entityplayer, true));
    }

    public static ItemStack tryPlaceWaterPlant(ItemBlock itemblock, ItemStack itemstack, World world, EntityPlayer entityplayer, MovingObjectPosition targetBlock) {
        if (targetBlock == null) {
            return itemstack;
        }
        if (targetBlock.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int i = targetBlock.blockX;
            int j = targetBlock.blockY;
            int k = targetBlock.blockZ;
            if (!world.canMineBlock(entityplayer, i, j, k)) {
                return itemstack;
            }
            if (!entityplayer.canPlayerEdit(i, j, k, targetBlock.sideHit, itemstack)) {
                return itemstack;
            }
            Block block = itemblock.field_150939_a;
            int meta = itemblock.getMetadata(itemstack.getItemDamage());
            if (world.getBlock(i, j, k).getMaterial() == Material.water && world.getBlockMetadata(i, j, k) == 0 && world.isAirBlock(i, j + 1, k) && block.canPlaceBlockAt(world, i, j + 1, k) && itemblock.placeBlockAt(itemstack, entityplayer, world, i, j + 1, k, 1, 0.5f, 0.5f, 0.5f, meta)) {
                Block.SoundType stepsound = block.stepSound;
                world.playSoundEffect((double)((float)i + 0.5f), (double)((float)j + 0.5f), (double)((float)k + 0.5f), stepsound.func_150496_b(), (stepsound.getVolume() + 1.0f) / 2.0f, stepsound.getPitch() * 0.8f);
                if (!entityplayer.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                }
            }
        }
        return itemstack;
    }
}

