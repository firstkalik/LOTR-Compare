/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Direction
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.block.LOTRBlockBarier2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class LOTRBlockLichen3
extends Block
implements IShearable {
    public LOTRBlockLichen3() {
        super(Material.vine);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setStepSound(soundTypeLadder);
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public int getRenderType() {
        return 20;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        boolean attached;
        int metadata = world.getBlockMetadata(x, y, z);
        float minX = 1.0f;
        float minY = 1.0f;
        float minZ = 1.0f;
        float maxX = 0.0f;
        float maxY = 0.0f;
        float maxZ = 0.0f;
        boolean bl = attached = metadata > 0;
        if ((metadata & 2) != 0) {
            maxX = Math.max(maxX, 0.0625f);
            minX = 0.0f;
            attached = true;
        }
        if ((metadata & 8) != 0) {
            minX = Math.min(minX, 0.9375f);
            attached = true;
        }
        if ((metadata & 4) != 0) {
            maxZ = Math.max(maxZ, 0.0625f);
            minZ = 0.0f;
            attached = true;
        }
        if ((metadata & 1) != 0) {
            minZ = Math.min(minZ, 0.9375f);
            attached = true;
        }
        if (!attached && this.canAttachTo(world.getBlock(x, y + 1, z))) {
            minY = Math.min(minY, 0.9375f);
            attached = true;
        }
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        return true;
    }

    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        switch (side) {
            case 1: {
                return this.canAttachTo(world.getBlock(x, y + 1, z));
            }
            case 2: {
                return this.canAttachTo(world.getBlock(x, y, z + 1));
            }
            case 3: {
                return this.canAttachTo(world.getBlock(x, y, z - 1));
            }
            case 4: {
                return this.canAttachTo(world.getBlock(x + 1, y, z));
            }
            case 5: {
                return this.canAttachTo(world.getBlock(x - 1, y, z));
            }
        }
        return false;
    }

    private boolean canAttachTo(Block block) {
        return block.isOpaqueCube() && block.getMaterial().blocksMovement() || block instanceof LOTRBlockBarier2;
    }

    private boolean updateLichenState(World world, int x, int y, int z) {
        int metadata;
        int newMetadata = metadata = world.getBlockMetadata(x, y, z);
        if (metadata > 0) {
            for (int i = 0; i <= 3; ++i) {
                int mask = 1 << i;
                if ((metadata & mask) == 0 || this.canAttachTo(world.getBlock(x + Direction.offsetX[i], y, z + Direction.offsetZ[i])) || world.getBlock(x, y + 1, z) == this && (world.getBlockMetadata(x, y + 1, z) & mask) != 0) continue;
                newMetadata &= ~mask;
            }
        }
        if (newMetadata == 0 && !this.canAttachTo(world.getBlock(x, y + 1, z))) {
            return false;
        }
        if (newMetadata != metadata) {
            world.setBlockMetadataWithNotify(x, y, z, newMetadata, 2);
        }
        return true;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote && !this.updateLichenState(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        switch (side) {
            case 2: {
                return 1;
            }
            case 3: {
                return 4;
            }
            case 4: {
                return 8;
            }
            case 5: {
                return 2;
            }
        }
        return metadata;
    }

    public Item getItemDropped(int metadata, Random random, int fortune) {
        return null;
    }

    public int quantityDropped(Random random) {
        return 0;
    }

    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        super.harvestBlock(world, player, x, y, z, metadata);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack((Block)this, 1));
        return drops;
    }
}

