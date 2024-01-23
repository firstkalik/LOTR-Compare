/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentRangedDamage
extends LOTREnchantment {
    public final float damageFactor;

    public LOTREnchantmentRangedDamage(String s, float damage) {
        super(s, LOTREnchantmentType.RANGED_LAUNCHER);
        this.damageFactor = damage;
        if (this.damageFactor > 1.0f) {
            this.setValueModifier(this.damageFactor * 2.0f);
        } else {
            this.setValueModifier(this.damageFactor);
        }
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.rangedDamage.desc", (Object[])new Object[]{this.formatMultiplicative(this.damageFactor)});
    }

    @Override
    public boolean isBeneficial() {
        return this.damageFactor >= 1.0f;
    }
}

