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

public class LOTRRandomEnchantment
extends LOTREnchantment {
    public LOTRRandomEnchantment(String s) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.AXE});
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)("lotr.enchant." + this.enchantName + ".desc"), (Object[])new Object[0]);
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}

