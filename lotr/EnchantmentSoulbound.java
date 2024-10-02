/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.util.StatCollector
 */
package lotr;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentSoulbound
extends Enchantment {
    public EnchantmentSoulbound(int ID, int rarity) {
        super(ID, rarity, EnumEnchantmentType.all);
        this.setName("lotr.soulbound");
    }

    public String getName() {
        return StatCollector.translateToLocal((String)("enchantment." + this.name));
    }

    public boolean isAllowedOnBooks() {
        return super.isAllowedOnBooks();
    }
}

