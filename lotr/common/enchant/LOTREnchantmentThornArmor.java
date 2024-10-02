/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package lotr.common.enchant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentArmorBase;
import lotr.common.enchant.LOTREnchantmentType;
import net.minecraft.item.ItemStack;

public class LOTREnchantmentThornArmor
extends LOTREnchantmentArmorBase {
    private List<LOTREnchantment> incEnchants = new ArrayList<LOTREnchantment>();
    private String desc;

    protected LOTREnchantmentThornArmor(String s, String desc) {
        super(s, desc, new LOTREnchantmentType[]{LOTREnchantmentType.ARMOR});
        this.desc = desc;
        this.setBypassAnvilLimit();
    }

    @Override
    public String getDescription(ItemStack var1) {
        return this.desc;
    }

    @Override
    public boolean isCompatibleWith(LOTREnchantment other) {
        if (this.incEnchants == null || this.incEnchants.isEmpty()) {
            return true;
        }
        return !this.incEnchants.contains(other);
    }

    @Override
    public void setIncompatibleEnchants(LOTREnchantment[] enchants) {
        this.incEnchants.addAll(Arrays.asList(enchants));
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }
}

