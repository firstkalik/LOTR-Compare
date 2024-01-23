/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockGlass
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockGlass
extends BlockGlass {
    private boolean thirdParam = false;

    public LOTRBlockGlass() {
        super(Material.glass, false);
        this.setHardness(0.3f);
        this.setStepSound(Block.soundTypeGlass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int i, int j, int k, int side) {
        Block block = world.getBlock(i, j, k);
        if (world.getBlockMetadata(i, j, k) != world.getBlockMetadata(i - Facing.offsetsXForSide[side], j - Facing.offsetsYForSide[side], k - Facing.offsetsZForSide[side])) {
            return true;
        }
        if (block == this) {
            return false;
        }
        return !this.thirdParam && block == this ? false : super.shouldSideBeRendered(world, i, j, k, side);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon(this.getTextureName());
    }

    public boolean canPlaceTorchOnTop(World world, int i, int j, int k) {
        return true;
    }
}

