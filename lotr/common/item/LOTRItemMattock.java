/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import lotr.common.item.LOTRItemPickaxe;
import lotr.common.item.LOTRMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemMattock
extends LOTRItemPickaxe {
    private float efficiencyOnProperMaterial;

    public LOTRItemMattock(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemMattock(Item.ToolMaterial material) {
        super(material);
        this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
        this.setHarvestLevel("axe", material.getHarvestLevel());
    }

    public float func_150893_a(ItemStack itemstack, Block block) {
        float f = super.func_150893_a(itemstack, block);
        if (f == 1.0f && block != null && (block.getMaterial() == Material.wood || block.getMaterial() == Material.plants || block.getMaterial() == Material.vine)) {
            return this.efficiencyOnProperMaterial;
        }
        if (block == Blocks.melon_block) {
            return 10.0f;
        }
        if (block == Blocks.pumpkin) {
            return 10.0f;
        }
        return f;
    }
}

