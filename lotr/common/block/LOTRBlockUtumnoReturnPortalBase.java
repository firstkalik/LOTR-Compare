/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
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

public class LOTRBlockUtumnoReturnPortalBase
extends Block {
    public static int MAX_SACRIFICE = 15;
    public static int RANGE = 5;
    @SideOnly(value=Side.CLIENT)
    private IIcon topIcon;

    public LOTRBlockUtumnoReturnPortalBase() {
        super(Material.circuits);
        this.setHardness(-1.0f);
        this.setResistance(Float.MAX_VALUE);
        this.setStepSound(Block.soundTypeStone);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 1) {
            return this.topIcon;
        }
        return super.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        super.registerBlockIcons(iconregister);
        this.topIcon = iconregister.registerIcon(this.getTextureName() + "_top");
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        this.setBlockBoundsBasedOnState((IBlockAccess)world, i, j, k);
        return super.getCollisionBoundingBoxFromPool(world, i, j, k);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        this.setBlockBoundsMeta(meta);
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBoundsMeta(0);
    }

    private void setBlockBoundsMeta(int meta) {
        float f = (float)meta / (float)MAX_SACRIFICE;
        float f1 = 0.0625f;
        float f2 = f1 + (1.0f - f1) * f;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, f2, 1.0f);
    }

    public int getLightValue(IBlockAccess world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        float f = (float)meta / (float)MAX_SACRIFICE;
        float f1 = 0.5f;
        float f2 = f1 + (1.0f - f1) * f;
        return (int)(f2 *= 16.0f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int quantityDropped(Random random) {
        return 0;
    }
}

