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
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.block.LOTRBlockSlabBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class LOTRBlockSlabWool
extends LOTRBlockSlabBase {
    public LOTRBlockSlabWool(boolean flag) {
        super(flag, Material.cloth, 8);
        this.setStepSound(Block.soundTypeCloth);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return Blocks.wool.getIcon(i, j &= 7);
    }

    public static class LOTRBlockSlabWool2
    extends LOTRBlockSlabWool {
        public LOTRBlockSlabWool2(boolean flag) {
            super(flag);
        }

        @SideOnly(value=Side.CLIENT)
        @Override
        public void registerBlockIcons(IIconRegister iconRegister) {
        }

        @SideOnly(value=Side.CLIENT)
        @Override
        public IIcon getIcon(int i, int j) {
            return Blocks.wool.getIcon(i, (j &= 7) + 8);
        }
    }

}

