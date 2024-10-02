/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBucket
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRItemBucketSnow
extends ItemBucket {
    public LOTRItemBucketSnow(Block containedBlock) {
        super(containedBlock);
        this.setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        boolean isSnowdrift;
        Block clickedBlock = world.getBlock(x, y, z);
        boolean canReplace = clickedBlock.isReplaceable((IBlockAccess)world, x, y, z);
        boolean isVegetation = clickedBlock instanceof BlockBush;
        boolean bl = isSnowdrift = clickedBlock == LOTRMod.snowdrift;
        if (isSnowdrift) {
            int newX = x;
            int newY = y;
            int newZ = z;
            switch (side) {
                case 0: {
                    --newY;
                    break;
                }
                case 1: {
                    ++newY;
                    break;
                }
                case 2: {
                    --newZ;
                    break;
                }
                case 3: {
                    ++newZ;
                    break;
                }
                case 4: {
                    --newX;
                    break;
                }
                case 5: {
                    ++newX;
                }
            }
            if (world.isAirBlock(newX, newY, newZ) || world.getBlock(newX, newY, newZ).isReplaceable((IBlockAccess)world, newX, newY, newZ)) {
                if (!world.isRemote) {
                    world.setBlock(newX, newY, newZ, LOTRMod.snowdrift);
                    if (!player.capabilities.isCreativeMode) {
                        --itemStack.stackSize;
                        if (itemStack.stackSize <= 0) {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
                        } else {
                            player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
                        }
                    }
                }
                return true;
            }
            return false;
        }
        if (isVegetation || canReplace) {
            if (!world.isRemote) {
                if (isVegetation) {
                    world.setBlockToAir(x, y, z);
                }
                world.setBlock(x, y, z, LOTRMod.snowdrift);
                if (!player.capabilities.isCreativeMode) {
                    --itemStack.stackSize;
                    if (itemStack.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
                    } else {
                        player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
                    }
                }
            }
            return true;
        }
        return false;
    }
}

