/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentType;
import lotr.common.item.LOTRMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtection
extends LOTREnchantment {
    public final int protectLevel;

    public LOTREnchantmentProtection(String s, int level) {
        this(s, LOTREnchantmentType.ARMOR, level);
    }

    public LOTREnchantmentProtection(String s, LOTREnchantmentType type, int level) {
        super(s, type);
        this.protectLevel = level;
        if (this.protectLevel >= 0) {
            this.setValueModifier((2.0f + (float)this.protectLevel) / 2.0f);
        } else {
            this.setValueModifier((4.0f + (float)this.protectLevel) / 4.0f);
        }
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.protect.desc", (Object[])new Object[]{this.formatAdditiveInt(this.protectLevel)});
    }

    @Override
    public boolean isBeneficial() {
        return this.protectLevel >= 0;
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        if (super.canApply(itemstack, considering)) {
            Item item = itemstack.getItem();
            if (item instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor)item;
                if (armor.getArmorMaterial() == LOTRMaterial.GALVORN.toArmorMaterial()) {
                    return false;
                }
                int prot = armor.damageReduceAmount;
                int total = prot + this.protectLevel;
                if (total > 0) {
                    if (considering) {
                        return true;
                    }
                    return total <= LOTRMaterial.MITHRIL.toArmorMaterial().getDamageReductionAmount(armor.armorType);
                }
                return false;
            }
            return true;
        }
        return false;
    }
}

