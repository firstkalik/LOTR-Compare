/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemBattleaxe
extends LOTRItemSword {
    private float efficiencyOnProperMaterial;

    public LOTRItemBattleaxe(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemBattleaxe(Item.ToolMaterial material) {
        super(material);
        this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
        this.setHarvestLevel("axe", material.getHarvestLevel());
        this.lotrWeaponDamage += 2.0f;
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

    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int i, int j, int k, EntityLivingBase entity) {
        if ((double)block.getBlockHardness(world, i, j, k) != 0.0) {
            itemstack.damageItem(1, entity);
        }
        return true;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.none;
    }
}

