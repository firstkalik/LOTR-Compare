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
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class LOTRBlockDriedKelp
extends Block {
    protected IIcon blockTop;
    protected IIcon blockBottom;

    public LOTRBlockDriedKelp() {
        super(Material.carpet);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(0.5f);
        this.setStepSound(soundTypeGrass);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockTop = iconregister.registerIcon(this.getTextureName() + "_top");
        this.blockBottom = iconregister.registerIcon(this.getTextureName() + "_bottom");
        this.blockIcon = iconregister.registerIcon(this.getTextureName() + "_side");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 0 ? this.blockBottom : (side == 1 ? this.blockTop : this.blockIcon);
    }
}

