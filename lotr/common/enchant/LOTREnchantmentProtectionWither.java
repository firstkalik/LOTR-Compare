/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantmentProtectionSpecial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtectionWither
extends LOTREnchantmentProtectionSpecial {
    public LOTREnchantmentProtectionWither(String s, int level) {
        super(s, level);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.protectWither.desc", (Object[])new Object[]{this.formatAdditiveInt(this.calcIntProtection())});
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        return source == DamageSource.magic || source == DamageSource.wither;
    }

    @Override
    public boolean isBeneficial() {
        return this.protectLevel >= 0;
    }

    @Override
    protected int calcIntProtection() {
        return 1 + this.protectLevel;
    }
}
