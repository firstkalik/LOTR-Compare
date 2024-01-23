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

public class LOTREnchantmentToolSpeed
extends LOTREnchantment {
    public final float speedFactor;

    public LOTREnchantmentToolSpeed(String s, float speed) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.TOOL, LOTREnchantmentType.SHEARS});
        this.speedFactor = speed;
        this.setValueModifier(this.speedFactor);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.toolSpeed.desc", (Object[])new Object[]{this.formatMultiplicative(this.speedFactor)});
    }

    @Override
    public boolean isBeneficial() {
        return this.speedFactor >= 1.0f;
    }
}

