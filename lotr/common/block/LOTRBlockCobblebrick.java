/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.client.render.LOTRConnectedTextures;
import lotr.common.block.LOTRBlockBrickBase;
import lotr.common.block.LOTRConnectedBlock;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class LOTRBlockCobblebrick
extends LOTRBlockBrickBase
implements LOTRConnectedBlock {
    public LOTRBlockCobblebrick() {
        this.setBrickNames("cob");
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        this.brickIcons = new IIcon[this.brickNames.length];
        for (int i = 0; i < this.brickNames.length; ++i) {
            LOTRConnectedTextures.registerConnectedIcons(iconregister, this, i, false);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
        return LOTRConnectedTextures.getConnectedIconBlock(this, world, i, j, k, side, false);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        boolean[][] arrarrbl;
        if (i == 0 || i == 1) {
            boolean[][] arrarrbl2 = new boolean[3][];
            arrarrbl2[0] = new boolean[]{false, false, false};
            arrarrbl2[1] = new boolean[]{false, true, false};
            arrarrbl = arrarrbl2;
            arrarrbl2[2] = new boolean[]{false, false, false};
        } else {
            boolean[][] arrarrbl3 = new boolean[3][];
            arrarrbl3[0] = new boolean[]{false, true, false};
            arrarrbl3[1] = new boolean[]{false, true, false};
            arrarrbl = arrarrbl3;
            arrarrbl3[2] = new boolean[]{false, true, false};
        }
        boolean[][] adjacentFlags = arrarrbl;
        return LOTRConnectedTextures.getConnectedIconItem(this, j, adjacentFlags);
    }

    @Override
    public String getConnectedName(int meta) {
        return this.textureName + "_" + this.brickNames[meta];
    }

    @Override
    public boolean areBlocksConnected(IBlockAccess world, int i, int j, int k, int i1, int j1, int k1) {
        return LOTRConnectedBlock.Checks.matchBlockAndMeta(this, world, i, j, k, i1, j1, k1);
    }
}

