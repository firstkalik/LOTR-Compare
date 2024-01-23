/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPressurePlate
 *  net.minecraft.block.BlockPressurePlate$Sensitivity
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockPressurePlate
extends BlockPressurePlate {
    public LOTRBlockPressurePlate(String iconPath, Material material, BlockPressurePlate.Sensitivity triggerType) {
        super(iconPath, material, triggerType);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }
}

