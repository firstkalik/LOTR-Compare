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

public class LOTREnchantmentKnockback
extends LOTREnchantment {
    public final int knockback;

    public LOTREnchantmentKnockback(String s, int i) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.MELEE, LOTREnchantmentType.THROWING_AXE});
        this.knockback = i;
        this.setValueModifier((float)(this.knockback + 2) / 2.0f);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.knockback.desc", (Object[])new Object[]{this.formatAdditiveInt(this.knockback)});
    }

    @Override
    public boolean isBeneficial() {
        return this.knockback >= 0;
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        return super.canApply(itemstack, considering) && LOTRWeaponStats.getBaseExtraKnockback(itemstack) + this.knockback <= LOTRWeaponStats.MAX_MODIFIABLE_KNOCKBACK;
    }
}

