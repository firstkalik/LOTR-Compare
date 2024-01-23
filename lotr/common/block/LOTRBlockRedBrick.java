/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.block.LOTRBlockBrickBase;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockRedBrick
extends LOTRBlockBrickBase {
    public LOTRBlockRedBrick() {
        this.setBrickNames("mossy", "cracked");
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(2.0f);
        this.setResistance(10.0f);
    }
}

