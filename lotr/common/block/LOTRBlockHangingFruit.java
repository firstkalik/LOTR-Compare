/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class LOTRBlockHangingFruit
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] fruitIcons;
    private String[] fruitSides = new String[]{"top", "side", "bottom"};

    public LOTRBlockHangingFruit() {
        super(Material.plants);
        this.setTickRandomly(true);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 0) {
            return this.fruitIcons[2];
        }
        if (i == 1) {
            return this.fruitIcons[0];
        }
        return this.fruitIcons[1];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.fruitIcons = new IIcon[3];
        for (int i = 0; i < 3; ++i) {
            this.fruitIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.fruitSides[i]);
        }
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (!this.canBlockStay(world, i, j, k)) {
            this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
            world.setBlockToAir(i, j, k);
        }
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
            world.setBlockToAir(i, j, k);
        }
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k);
        ForgeDirection dir = ForgeDirection.getOrientation((int)(l + 2));
        Block block = world.getBlock(i + dir.offsetX, j, k + dir.offsetZ);
        return block.isWood((IBlockAccess)world, i + dir.offsetX, j, k + dir.offsetZ);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        this.setBlockBoundsBasedOnState((IBlockAccess)world, i, j, k);
        return super.getCollisionBoundingBoxFromPool(world, i, j, k);
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
        this.setBlockBoundsBasedOnState((IBlockAccess)world, i, j, k);
        return super.getSelectedBoundingBoxFromPool(world, i, j, k);
    }
}

