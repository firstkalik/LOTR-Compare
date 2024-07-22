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
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentMeleeSpeed
extends LOTREnchantment {
    public final float speedFactor;

    public LOTREnchantmentMeleeSpeed(String s, float speed) {
        super(s, LOTREnchantmentType.MELEE);
        this.speedFactor = speed;
        this.setValueModifier(this.speedFactor);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.meleeSpeed.desc", (Object[])new Object[]{this.formatMultiplicative(this.speedFactor)});
    }

    @Override
    public boolean isBeneficial() {
        return this.speedFactor >= 1.0f;
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        if (super.canApply(itemstack, considering)) {
            float f;
            float speed = LOTRWeaponStats.getMeleeSpeed(itemstack);
            speed *= this.speedFactor;
            return f <= LOTRWeaponStats.MAX_MODIFIABLE_SPEED;
        }
        return false;
    }
}

