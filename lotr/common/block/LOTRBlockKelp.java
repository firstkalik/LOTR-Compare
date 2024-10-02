/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockKelp
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon blockIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon itemIcon;

    public LOTRBlockKelp() {
        super(Material.water);
        float f = 0.375f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 1.0f, 0.5f + f);
        this.setHardness(0.0f);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        for (int i = 1; i <= 8; ++i) {
            if (world.getBlock(x, y + i, z) != LOTRMod.kelp) continue;
            world.func_147480_a(x, y + i, z, true);
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        return this.canPlaceBlockAt(world, x, y, z);
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block below = world.getBlock(x, y - 1, z);
        return below == this || below == Blocks.sand || below == Blocks.dirt || below == Blocks.clay;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public int getRenderType() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return this.blockIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return this.itemIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName());
        this.itemIcon = iconRegister.registerIcon(this.getTextureName());
    }

    @SideOnly(value=Side.CLIENT)
    public String getItemIconName() {
        return this.getTextureName();
    }
}

