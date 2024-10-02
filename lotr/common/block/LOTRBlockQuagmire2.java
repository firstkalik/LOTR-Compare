/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTRBlockQuagmire2
extends Block {
    public LOTRBlockQuagmire2() {
        super(Material.sand);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        entity.setInWeb();
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack currentItem = player.getCurrentEquippedItem();
        if (currentItem != null && currentItem.getItem() == Items.bucket && world.getBlock(x, y, z) == this) {
            if (!world.isRemote) {
                world.setBlockToAir(x, y, z);
                if (!player.capabilities.isCreativeMode) {
                    --currentItem.stackSize;
                    if (currentItem.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(LOTRMod.bucketSand));
                    } else {
                        player.inventory.addItemStackToInventory(new ItemStack(LOTRMod.bucketSand));
                    }
                    ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
                }
            }
            return true;
        }
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }
}

