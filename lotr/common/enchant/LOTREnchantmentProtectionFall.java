/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantmentProtectionSpecial;
import lotr.common.enchant.LOTREnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtectionFall
extends LOTREnchantmentProtectionSpecial {
    public LOTREnchantmentProtectionFall(String s, int level) {
        super(s, LOTREnchantmentType.ARMOR_FEET, level);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.protectFall.desc", (Object[])new Object[]{this.formatAdditiveInt(this.calcIntProtection())});
    }

    @Override
    protected boolean isCompatibleWithOtherProtection() {
        return true;
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        return source == DamageSource.fall;
    }

    @Override
    public boolean isBeneficial() {
        return this.protectLevel >= 0;
    }

    @Override
    protected int calcIntProtection() {
        float f = (float)this.protectLevel * (float)(this.protectLevel + 1) / 2.0f;
        return 3 + MathHelper.floor_float((float)f);
    }
}

