/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockLavaCauldron
extends BlockCauldron {
    public BlockLavaCauldron() {
        this.setHardness(2.0f);
        this.setStepSound(soundTypeMetal);
        this.setBlockName("lavaCauldron");
        this.setBlockTextureName("minecraft:cauldron");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.inventory.getCurrentItem();
        if (itemstack == null) {
            return true;
        }
        if (itemstack.getItem() == Items.lava_bucket) {
            if (!world.isRemote && world.getBlockMetadata(x, y, z) < 3) {
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
                if (!player.capabilities.isCreativeMode) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
                }
            }
            return true;
        }
        if (itemstack.getItem() == Items.bucket) {
            int metadata;
            if (!world.isRemote && (metadata = world.getBlockMetadata(x, y, z)) == 3) {
                world.setBlockMetadataWithNotify(x, y, z, 0, 2);
                if (!player.capabilities.isCreativeMode) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.lava_bucket));
                }
            }
            return true;
        }
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }
}

