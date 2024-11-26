/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityDecoratedPot;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockDecoratedPot
extends Block
implements ITileEntityProvider {
    @SideOnly(value=Side.CLIENT)
    private IIcon blockIcon;

    public LOTRBlockDecoratedPot() {
        super(Material.carpet);
        this.setHardness(0.0f);
        this.setStepSound(Block.soundTypeGlass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 1.0f, 0.9375f);
    }

    @SideOnly(value=Side.CLIENT)
    public String getItemIconName() {
        return this.getTextureName();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon(this.getTextureName());
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return true;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityDecoratedPot();
    }

    public int getRenderType() {
        return LOTRMod.proxy.getDecoratedPotRenderID();
    }

    public boolean canPlaceTorchOnTop(World world, int i, int j, int k) {
        return true;
    }

    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        return true;
    }
}

