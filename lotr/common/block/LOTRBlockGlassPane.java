/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import lotr.common.block.LOTRBlockGlass;
import lotr.common.block.LOTRBlockPane;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockGlassPane
extends LOTRBlockPane {
    public LOTRBlockGlassPane() {
        super("lotr:glass", "lotr:glass_pane_top", Material.glass, false);
        this.setHardness(0.3f);
        this.setStepSound(Block.soundTypeGlass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public boolean canPaneConnectTo(IBlockAccess world, int i, int j, int k, ForgeDirection dir) {
        return super.canPaneConnectTo(world, i, j, k, dir) || world.getBlock(i, j, k) instanceof LOTRBlockGlass;
    }
}

