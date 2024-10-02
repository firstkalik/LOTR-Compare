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
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
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
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class LOTRBlockSeaWeed
extends Block
implements IPlantable {
    public LOTRBlockSeaWeed() {
        super(Material.water);
        this.setBlockBounds(0.125f, 0.0f, 0.125f, 0.875f, 1.0f, 0.875f);
        this.setHardness(0.0f);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        Block blockBelow = world.getBlock(i, j - 1, k);
        return (blockBelow == Blocks.dirt || blockBelow == Blocks.sand) && world.getBlockMetadata(i, j - 1, k) == 0 && world.getBlock(i, j + 1, k) == Blocks.water;
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return this.canPlaceBlockAt(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        this.checkCanStay(world, i, j, k);
    }

    private boolean checkCanStay(World world, int i, int j, int k) {
        if (!this.canBlockStay(world, i, j, k)) {
            int meta = world.getBlockMetadata(i, j, k);
            this.dropBlockAsItem(world, i, j, k, meta, 0);
            world.setBlockToAir(i, j, k);
            return false;
        }
        return true;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.blockIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon(this.getTextureName());
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random rand) {
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return EnumPlantType.Plains;
    }

    public Block getPlant(IBlockAccess world, int i, int j, int k) {
        return this;
    }

    public int getPlantMetadata(IBlockAccess world, int i, int j, int k) {
        return world.getBlockMetadata(i, j, k);
    }
}

