/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentBane;
import lotr.common.enchant.LOTREnchantmentType;
import lotr.common.item.LOTRItemAxe;
import lotr.common.item.LOTRItemMattock;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentWeaponSpecial4
extends LOTREnchantment {
    private boolean compatibleBane = true;
    private boolean compatibleOtherSpecial = false;

    public LOTREnchantmentWeaponSpecial4(String s) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.AXE});
        this.setValueModifier(3.0f);
        this.setBypassAnvilLimit();
    }

    public LOTREnchantmentWeaponSpecial4 setCompatibleOtherSpecial() {
        this.compatibleOtherSpecial = true;
        return this;
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        if (LOTRWeaponStats.isMeleeWeapon(itemstack)) {
            return StatCollector.translateToLocalFormatted((String)("lotr.enchant." + this.enchantName + ".desc"), (Object[])new Object[0]);
        }
        return StatCollector.translateToLocalFormatted((String)("lotr.enchant." + this.enchantName + ".desc.ranged"), (Object[])new Object[0]);
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        return itemstack.getItem() instanceof LOTRItemAxe || itemstack.getItem() instanceof LOTRItemMattock || itemstack.getItem() instanceof ItemAxe;
    }

    @Override
    public boolean isCompatibleWith(LOTREnchantment other) {
        if (!this.compatibleBane && other instanceof LOTREnchantmentBane) {
            return false;
        }
        return this.compatibleOtherSpecial || !(other instanceof LOTREnchantmentWeaponSpecial4) || ((LOTREnchantmentWeaponSpecial4)other).compatibleOtherSpecial;
    }
}

