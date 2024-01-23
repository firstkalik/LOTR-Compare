/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantmentProtectionSpecial;
import lotr.common.item.LOTRMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtectionRanged
extends LOTREnchantmentProtectionSpecial {
    public LOTREnchantmentProtectionRanged(String s, int level) {
        super(s, level);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.protectRanged.desc", (Object[])new Object[]{this.formatAdditiveInt(this.calcIntProtection())});
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        if (super.canApply(itemstack, considering)) {
            Item item = itemstack.getItem();
            return !(item instanceof ItemArmor) || ((ItemArmor)item).getArmorMaterial() != LOTRMaterial.GALVORN.toArmorMaterial();
        }
        return false;
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        return source.isProjectile();
    }

    @Override
    protected int calcIntProtection() {
        return this.protectLevel;
    }
}

