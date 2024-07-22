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

public class LOTREnchantmentMeleeReach
extends LOTREnchantment {
    public final float reachFactor;

    public LOTREnchantmentMeleeReach(String s, float reach) {
        super(s, LOTREnchantmentType.MELEE);
        this.reachFactor = reach;
        this.setValueModifier(this.reachFactor);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.meleeReach.desc", (Object[])new Object[]{this.formatMultiplicative(this.reachFactor)});
    }

    @Override
    public boolean isBeneficial() {
        return this.reachFactor >= 1.0f;
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        if (super.canApply(itemstack, considering)) {
            float f;
            float reach = LOTRWeaponStats.getMeleeReachFactor(itemstack);
            reach *= this.reachFactor;
            return f <= LOTRWeaponStats.MAX_MODIFIABLE_REACH;
        }
        return false;
    }
}

