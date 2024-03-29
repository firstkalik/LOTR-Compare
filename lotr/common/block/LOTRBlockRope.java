/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockLadder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockRope
extends LOTRBlockLadder {
    private boolean canRetract;

    public LOTRBlockRope(boolean flag) {
        this.setHardness(0.4f);
        this.setStepSound(Block.soundTypeCloth);
        this.canRetract = flag;
    }

    @SideOnly(value=Side.CLIENT)
    public String getItemIconName() {
        return this.getTextureName();
    }

    public int getRenderType() {
        return LOTRMod.proxy.getRopeRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int i, int j, int k, int side) {
        if (side == 0 || side == 1) {
            Block block = world.getBlock(i, j, k);
            return block != this && !block.isOpaqueCube();
        }
        return true;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return world.getBlock(i, j + 1, k) == this || super.canPlaceBlockAt(world, i, j, k);
    }

    public int onBlockPlaced(World world, int i, int j, int k, int side, float hitX, float hitY, float hitZ, int meta) {
        int placeMeta = super.onBlockPlaced(world, i, j, k, side, hitX, hitY, hitZ, meta);
        if (placeMeta == 0 && world.getBlock(i, j + 1, k) == this) {
            placeMeta = world.getBlockMetadata(i, j + 1, k);
        }
        return placeMeta;
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return this.canPlaceBlockAt(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (world.getBlock(i, j + 1, k) != this) {
            super.onNeighborBlockChange(world, i, j, k, block);
        }
    }

    private boolean canExtendRopeWithMetadata(World world, int i, int j, int k, int meta) {
        if (world.getBlock(i, j + 1, k) == this) {
            return true;
        }
        if (meta == 2) {
            return world.isSideSolid(i, j, k + 1, ForgeDirection.NORTH);
        }
        if (meta == 3) {
            return world.isSideSolid(i, j, k - 1, ForgeDirection.SOUTH);
        }
        if (meta == 4) {
            return world.isSideSolid(i + 1, j, k, ForgeDirection.WEST);
        }
        if (meta == 5) {
            return world.isSideSolid(i - 1, j, k, ForgeDirection.EAST);
        }
        return false;
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        boolean lookingUpOrDown = entityplayer.rotationPitch <= 0.0f;
        int lookDir = lookingUpOrDown ? 1 : -1;
        ItemStack itemstack = entityplayer.getHeldItem();
        if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock((Block)this)) {
            int j1;
            Block block;
            for (j1 = j; j1 >= 0 && j1 < world.getHeight() && (block = world.getBlock(i, j1, k)) == this; j1 += lookDir) {
            }
            if (j1 >= 0 && j1 < world.getHeight()) {
                int thisMeta;
                block = world.getBlock(i, j1, k);
                if (this.canPlaceBlockOnSide(world, i, j1, k, side) && block.isReplaceable((IBlockAccess)world, i, j1, k) && !block.getMaterial().isLiquid() && this.canExtendRopeWithMetadata(world, i, j1, k, thisMeta = world.getBlockMetadata(i, j, k))) {
                    world.setBlock(i, j1, k, (Block)this, thisMeta, 3);
                    world.playSoundEffect((double)((float)i + 0.5f), (double)((float)j1 + 0.5f), (double)((float)k + 0.5f), this.stepSound.func_150496_b(), (this.stepSound.getVolume() + 1.0f) / 2.0f, this.stepSound.getPitch() * 0.8f);
                    if (!entityplayer.capabilities.isCreativeMode) {
                        --itemstack.stackSize;
                    }
                    if (itemstack.stackSize <= 0) {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                    return true;
                }
            }
        } else if (!entityplayer.isOnLadder() && this.canRetract) {
            if (!world.isRemote) {
                boolean invAdded = false;
                for (int j1 = j; j1 >= 0 && j1 < world.getHeight(); j1 += lookDir) {
                    Block block = world.getBlock(i, j1, k);
                    int meta = world.getBlockMetadata(i, j1, k);
                    if (block != this) break;
                    if (!entityplayer.capabilities.isCreativeMode) {
                        ArrayList drops = block.getDrops(world, i, j1, k, meta, 0);
                        for (ItemStack drop : drops) {
                            if (entityplayer.inventory.addItemStackToInventory(drop)) {
                                invAdded = true;
                                continue;
                            }
                            entityplayer.dropPlayerItemWithRandomChoice(drop, false);
                        }
                    }
                    world.setBlockToAir(i, j1, k);
                    world.playSoundEffect((double)((float)i + 0.5f), (double)((float)j1 + 0.5f), (double)((float)k + 0.5f), this.stepSound.getBreakSound(), (this.stepSound.getVolume() + 1.0f) / 2.0f, this.stepSound.getPitch() * 0.8f);
                }
                if (invAdded) {
                    ((EntityPlayerMP)entityplayer).openContainer.detectAndSendChanges();
                }
            }
            return true;
        }
        return false;
    }
}

