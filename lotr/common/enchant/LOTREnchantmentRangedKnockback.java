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

public class LOTREnchantmentRangedKnockback
extends LOTREnchantment {
    public final int knockback;

    public LOTREnchantmentRangedKnockback(String s, int i) {
        super(s, LOTREnchantmentType.RANGED_LAUNCHER);
        this.knockback = i;
        this.setValueModifier((float)(this.knockback + 2) / 2.0f);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.rangedKnockback.desc", (Object[])new Object[]{this.formatAdditiveInt(this.knockback)});
    }

    @Override
    public boolean isBeneficial() {
        return this.knockback >= 0;
    }
}

