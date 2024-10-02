/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.List;
import java.util.Random;
import lotr.client.LOTRClientProxy;
import lotr.common.LOTRConfig;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Lantern
extends Block {
    public static final int META_ON_GROUND = 0;
    public static final int META_HANGING = 1;
    protected static final Vec3 bottomSutainingPoint = Vec3.createVectorHelper((double)0.5, (double)0.03125, (double)0.5);
    protected static final Vec3 topSutainingPoint = Vec3.createVectorHelper((double)0.5, (double)0.96875, (double)0.5);

    public Lantern() {
        super(Material.iron);
        this.setResistance(3.5f);
        this.setHardness(3.5f);
        this.setLightLevel(1.0f);
        this.setHarvestLevel("pickaxe", 0);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public int getMobilityFlag() {
        return 1;
    }

    private static boolean canSustainLanternOnTop(World world, int x, int y, int z) {
        if (World.doesBlockHaveSolidTopSurface((IBlockAccess)world, (int)x, (int)y, (int)z)) {
            return true;
        }
        if (world.getBlock(x, y, z).canPlaceTorchOnTop(world, x, y, z)) {
            return true;
        }
        Block block = world.getBlock(x, y, z);
        block.setBlockBoundsBasedOnState((IBlockAccess)world, x, y, z);
        AxisAlignedBB aabb = block.getCollisionBoundingBoxFromPool(world, x, y, z);
        return aabb != null && aabb.isVecInside(topSutainingPoint.addVector((double)x, (double)y, (double)z));
    }

    protected static boolean canSustainLanternOnBottom(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        block.setBlockBoundsBasedOnState((IBlockAccess)world, x, y, z);
        AxisAlignedBB aabb = block.getCollisionBoundingBoxFromPool(world, x, y, z);
        return aabb != null && aabb.isVecInside(bottomSutainingPoint.addVector((double)x, (double)y, (double)z));
    }

    protected static boolean canLanternStayAt(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 1) {
            return world.getBlock(x, y + 1, z).isSideSolid((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN) || Lantern.canSustainLanternOnBottom(world, x, y + 1, z);
        }
        return world.getBlock(x, y - 1, z).isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP) || Lantern.canSustainLanternOnTop(world, x, y - 1, z);
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.getBlock(x, y + 1, z).isSideSolid((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN) || Lantern.canSustainLanternOnBottom(world, x, y + 1, z) || world.getBlock(x, y - 1, z).isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP) || Lantern.canSustainLanternOnTop(world, x, y - 1, z);
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        if (world.getBlock(x, y + 1, z).isSideSolid((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN) || Lantern.canSustainLanternOnBottom(world, x, y + 1, z)) {
            if (side == 0) {
                return 1;
            }
            if (!world.getBlock(x, y - 1, z).isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP) && !Lantern.canSustainLanternOnTop(world, x, y - 1, z)) {
                return 1;
            }
        }
        return 0;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        if (!Lantern.canLanternStayAt(world, x, y, z)) {
            if (!world.isRemote) {
                ItemStack itemstack = new ItemStack((Block)this, 1, world.getBlockMetadata(x, y, z));
                float f = 0.7f;
                double xd = (double)(world.rand.nextFloat() * 0.7f) + 0.15000000596046448;
                double yd = (double)(world.rand.nextFloat() * 0.7f) + 0.15000000596046448;
                double zd = (double)(world.rand.nextFloat() * 0.7f) + 0.15000000596046448;
                EntityItem entityitem = new EntityItem(world, (double)x + xd, (double)y + yd, (double)z + zd, itemstack);
                entityitem.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld((Entity)entityitem);
            }
            world.setBlockToAir(x, y, z);
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
        if (world.getBlockMetadata(x, y, z) == 1) {
            Lantern.setBlockBoundsWithState(this, 1, true);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            Lantern.setBlockBoundsWithState(this, 1, false);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        } else {
            Lantern.setBlockBoundsWithState(this, 0, true);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            Lantern.setBlockBoundsWithState(this, 0, false);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
        Lantern.setBlockBoundsWithState(this, 0, true);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        Lantern.setBlockBoundsWithState(this, world.getBlockMetadata(x, y, z), true);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        Lantern.setBlockBoundsWithState(this, world.getBlockMetadata(x, y, z), true);
    }

    public void setBlockBoundsForItemRender() {
        Lantern.setBlockBoundsWithState(this, 0, true);
    }

    public static void setBlockBoundsWithState(Block block, int meta, boolean isBody) {
        if (meta == 1) {
            if (isBody) {
                block.setBlockBounds(0.3125f, 0.0625f, 0.3125f, 0.6875f, 0.5f, 0.6875f);
            } else {
                block.setBlockBounds(0.375f, 0.5f, 0.375f, 0.625f, 0.625f, 0.625f);
            }
        } else if (isBody) {
            block.setBlockBounds(0.3125f, 0.0f, 0.3125f, 0.6875f, 0.4375f, 0.6875f);
        } else {
            block.setBlockBounds(0.375f, 0.4375f, 0.375f, 0.625f, 0.5625f, 0.625f);
        }
    }

    public int getRenderType() {
        if (LOTRConfig.renderspecial) {
            if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
                return LOTRClientProxy.LanternMallornModel;
            }
            return super.getRenderType();
        }
        return -1;
    }
}

