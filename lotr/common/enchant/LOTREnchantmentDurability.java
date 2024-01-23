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

public class LOTREnchantmentDurability
extends LOTREnchantment {
    public final float durabilityFactor;

    public LOTREnchantmentDurability(String s, float f) {
        super(s, LOTREnchantmentType.BREAKABLE);
        this.durabilityFactor = f;
        this.setValueModifier(this.durabilityFactor);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.durable.desc", (Object[])new Object[]{this.formatMultiplicative(this.durabilityFactor)});
    }

    @Override
    public boolean isBeneficial() {
        return this.durabilityFactor >= 1.0f;
    }
}

