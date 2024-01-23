/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class LOTRBlockFenceGate
extends BlockFenceGate {
    private Block plankBlock;
    private int plankMeta;

    public LOTRBlockFenceGate(Block block, int meta) {
        this.plankBlock = block;
        this.plankMeta = meta;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
        this.setHardness(2.0f);
        this.setResistance(5.0f);
        this.setStepSound(soundTypeWood);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.plankBlock.getIcon(i, this.plankMeta);
    }
}

