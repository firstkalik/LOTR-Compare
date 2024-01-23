/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentLooting;
import lotr.common.enchant.LOTREnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentSilkTouch
extends LOTREnchantment {
    public LOTREnchantmentSilkTouch(String s) {
        super(s, LOTREnchantmentType.TOOL);
        this.setValueModifier(3.0f);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)("lotr.enchant." + this.enchantName + ".desc"), (Object[])new Object[0]);
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public boolean isCompatibleWith(LOTREnchantment other) {
        return super.isCompatibleWith(other) && !(other instanceof LOTREnchantmentLooting);
    }
}

