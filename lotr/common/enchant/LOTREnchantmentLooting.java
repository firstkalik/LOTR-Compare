/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentSilkTouch;
import lotr.common.enchant.LOTREnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentLooting
extends LOTREnchantment {
    public final int lootLevel;

    public LOTREnchantmentLooting(String s, int level) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.TOOL, LOTREnchantmentType.MELEE});
        this.lootLevel = level;
        this.setValueModifier(1.0f + (float)this.lootLevel);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.looting.desc", (Object[])new Object[]{this.formatAdditiveInt(this.lootLevel)});
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public boolean isCompatibleWith(LOTREnchantment other) {
        return super.isCompatibleWith(other) && !(other instanceof LOTREnchantmentSilkTouch);
    }
}

