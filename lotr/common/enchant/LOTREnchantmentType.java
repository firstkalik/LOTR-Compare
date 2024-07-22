/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemShears
 *  net.minecraft.item.ItemStack
 */
package lotr.common.enchant;

import java.util.Set;
import lotr.common.item.LOTRItemBlowgun;
import lotr.common.item.LOTRItemCommandSword;
import lotr.common.item.LOTRItemCrossbow;
import lotr.common.item.LOTRItemThrowingAxe;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;

public enum LOTREnchantmentType {
    BREAKABLE,
    UNBREAKABLE,
    ARMOR,
    ARMOR_FEET,
    ARMOR_LEGS,
    ARMOR_BODY,
    ARMOR_HEAD,
    MELEE,
    TOOL,
    SHEARS,
    RANGED,
    RANGED_LAUNCHER,
    THROWING_AXE,
    FISHING;


    public boolean canApply(ItemStack itemstack, boolean considering) {
        Item item = itemstack.getItem();
        if (this == BREAKABLE && item.isDamageable()) {
            return true;
        }
        if (this == UNBREAKABLE && item.isDamageable()) {
            return false;
        }
        if (item instanceof ItemArmor && ((ItemArmor)item).damageReduceAmount > 0) {
            if (this == ARMOR) {
                return true;
            }
            ItemArmor itemarmor = (ItemArmor)item;
            int armorType = itemarmor.armorType;
            if (armorType == 0) {
                return this == ARMOR_HEAD;
            }
            if (armorType == 1) {
                return this == ARMOR_BODY;
            }
            if (armorType == 1) {
                return this == ARMOR_BODY;
            }
            if (armorType == 2) {
                return this == ARMOR_LEGS;
            }
            if (armorType == 3) {
                return this == ARMOR_FEET;
            }
        }
        if (this == MELEE && LOTRWeaponStats.isMeleeWeapon(itemstack) && !(item instanceof LOTRItemCommandSword)) {
            return true;
        }
        if (this == TOOL && !item.getToolClasses(itemstack).isEmpty()) {
            return true;
        }
        if (this == SHEARS && item instanceof ItemShears) {
            return true;
        }
        if (this == RANGED && LOTRWeaponStats.isRangedWeapon(itemstack)) {
            return true;
        }
        if (this == RANGED_LAUNCHER && (item instanceof ItemBow || item instanceof LOTRItemCrossbow || item instanceof LOTRItemBlowgun)) {
            return true;
        }
        if (this == THROWING_AXE && item instanceof LOTRItemThrowingAxe) {
            return true;
        }
        return this == FISHING && item instanceof ItemFishingRod;
    }
}

