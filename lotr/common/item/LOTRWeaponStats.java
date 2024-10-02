/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.ISpecialArmor
 */
package lotr.common.item;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.item.LOTRItemBalrogWhip;
import lotr.common.item.LOTRItemBattleaxe;
import lotr.common.item.LOTRItemBlowgun;
import lotr.common.item.LOTRItemBow;
import lotr.common.item.LOTRItemCrossbow;
import lotr.common.item.LOTRItemDagger;
import lotr.common.item.LOTRItemDagger2;
import lotr.common.item.LOTRItemDagger3;
import lotr.common.item.LOTRItemDagger4;
import lotr.common.item.LOTRItemDagger5;
import lotr.common.item.LOTRItemHammer;
import lotr.common.item.LOTRItemHammerUlfang;
import lotr.common.item.LOTRItemLance;
import lotr.common.item.LOTRItemPolearm;
import lotr.common.item.LOTRItemPolearmLong;
import lotr.common.item.LOTRItemSpear;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRItemThrowingAxe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ISpecialArmor;

public class LOTRWeaponStats {
    private static int basePlayerMeleeTime = 15;
    private static int baseMobMeleeTime = 15;
    private static Map meleeSpeed = new HashMap();
    private static Map meleeReach = new HashMap();
    private static Map meleeExtraKnockback = new HashMap();
    public static float MAX_MODIFIABLE_REACH;
    public static float MAX_MODIFIABLE_SPEED;
    public static int MAX_MODIFIABLE_KNOCKBACK;

    public static boolean isMeleeWeapon(ItemStack itemstack) {
        Multimap weaponAttributes;
        if (itemstack != null && (weaponAttributes = itemstack.getAttributeModifiers()) != null) {
            for (Object obj : weaponAttributes.entries()) {
                Map.Entry e = (Map.Entry)obj;
                AttributeModifier mod = (AttributeModifier)e.getValue();
                if (mod.getID() != LOTRItemSword.accessWeaponDamageModifier()) continue;
                return true;
            }
        }
        return false;
    }

    public static float getMeleeDamageBonus(ItemStack itemstack) {
        Multimap weaponAttributes;
        float damage = 0.0f;
        if (itemstack != null && (weaponAttributes = itemstack.getAttributeModifiers()) != null) {
            for (Object obj : weaponAttributes.entries()) {
                Map.Entry e = (Map.Entry)obj;
                AttributeModifier mod = (AttributeModifier)e.getValue();
                if (mod.getID() != LOTRItemSword.accessWeaponDamageModifier()) continue;
                damage = (float)((double)damage + mod.getAmount());
                damage += EnchantmentHelper.func_152377_a((ItemStack)itemstack, (EnumCreatureAttribute)EnumCreatureAttribute.UNDEFINED);
            }
        }
        return damage;
    }

    public static void registerMeleeSpeed(Object obj, float f) {
        meleeSpeed.put(obj, Float.valueOf(f));
    }

    public static void registerMeleeReach(Object obj, float f) {
        meleeReach.put(obj, Float.valueOf(f));
    }

    public static void registerMeleeExtraKnockback(Object obj, int i) {
        meleeExtraKnockback.put(obj, i);
    }

    private static Object getClassOrItemProperty(ItemStack itemstack, Map propertyMap) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            if (propertyMap.containsKey((Object)item)) {
                return propertyMap.get((Object)item);
            }
            Class<?> itemClass = item.getClass();
            do {
                if (propertyMap.containsKey(itemClass)) {
                    return propertyMap.get(itemClass);
                }
                if (itemClass == Item.class) break;
                itemClass = itemClass.getSuperclass();
            } while (true);
        }
        return null;
    }

    public static int getAttackTimePlayer(ItemStack itemstack) {
        return LOTRWeaponStats.getAttackTimeWithBase(itemstack, basePlayerMeleeTime);
    }

    public static int getAttackTimeMob(ItemStack itemstack) {
        return LOTRWeaponStats.getAttackTimeWithBase(itemstack, baseMobMeleeTime);
    }

    public static int getAttackTimeWithBase(ItemStack itemstack, int baseTime) {
        float time = baseTime;
        Float factor = (Float)LOTRWeaponStats.getClassOrItemProperty(itemstack, meleeSpeed);
        if (factor != null) {
            time /= factor.floatValue();
        }
        time /= LOTREnchantmentHelper.calcMeleeSpeedFactor(itemstack);
        time = Math.max(time, 1.0f);
        return Math.round(time);
    }

    public static float getMeleeSpeed(ItemStack itemstack) {
        int base = basePlayerMeleeTime;
        return 1.0f / ((float)LOTRWeaponStats.getAttackTimeWithBase(itemstack, base) / (float)base);
    }

    public static float getMeleeReachFactor(ItemStack itemstack) {
        float reach = 1.0f;
        Float factor = (Float)LOTRWeaponStats.getClassOrItemProperty(itemstack, meleeReach);
        if (factor != null) {
            reach *= factor.floatValue();
        }
        return reach *= LOTREnchantmentHelper.calcMeleeReachFactor(itemstack);
    }

    public static float getMeleeReachDistance(EntityPlayer entityplayer) {
        float reach = 3.0f;
        reach *= LOTRWeaponStats.getMeleeReachFactor(entityplayer.getHeldItem());
        if (entityplayer.capabilities.isCreativeMode) {
            reach = (float)((double)reach + 3.0);
        }
        return reach;
    }

    public static float getMeleeExtraLookWidth() {
        return 1.0f;
    }

    public static int getBaseExtraKnockback(ItemStack itemstack) {
        int kb = 0;
        Integer extra = (Integer)LOTRWeaponStats.getClassOrItemProperty(itemstack, meleeExtraKnockback);
        if (extra != null) {
            kb = extra;
        }
        return kb;
    }

    public static int getTotalKnockback(ItemStack itemstack) {
        return EnchantmentHelper.getEnchantmentLevel((int)Enchantment.knockback.effectId, (ItemStack)itemstack) + LOTRWeaponStats.getBaseExtraKnockback(itemstack) + LOTREnchantmentHelper.calcExtraKnockback(itemstack);
    }

    public static boolean isPoisoned(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof LOTRItemDagger && ((LOTRItemDagger)item).getDaggerEffect() == LOTRItemDagger.DaggerEffect.POISON;
        }
        return false;
    }

    public static boolean isPoisoned2(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof LOTRItemDagger2 && ((LOTRItemDagger2)item).getDaggerEffect() == LOTRItemDagger2.DaggerEffect.POISON;
        }
        return false;
    }

    public static boolean isBlood(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof LOTRItemDagger && ((LOTRItemDagger)item).getDaggerEffect() == LOTRItemDagger.DaggerEffect.BLOOD;
        }
        return false;
    }

    public static boolean isBlood1(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof LOTRItemDagger && ((LOTRItemDagger)item).getDaggerEffect() == LOTRItemDagger.DaggerEffect.BLOOD1;
        }
        return false;
    }

    public static boolean isPoisoned3(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof LOTRItemDagger3 && ((LOTRItemDagger3)item).getDaggerEffect() == LOTRItemDagger3.DaggerEffect.POISON;
        }
        return false;
    }

    public static boolean isPoisoned4(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof LOTRItemDagger4 && ((LOTRItemDagger4)item).getDaggerEffect() == LOTRItemDagger4.DaggerEffect.POISON;
        }
        return false;
    }

    public static boolean isPoisoned5(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof LOTRItemDagger5 && ((LOTRItemDagger5)item).getDaggerEffect() == LOTRItemDagger5.DaggerEffect.POISON;
        }
        return false;
    }

    public static boolean isPoisoned1(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof LOTRItemDagger && ((LOTRItemDagger)item).getDaggerEffect() == LOTRItemDagger.DaggerEffect.WITHER;
        }
        return false;
    }

    public static boolean isRangedWeapon(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            return item instanceof ItemBow || item instanceof LOTRItemSpear || item instanceof LOTRItemBlowgun || item instanceof LOTRItemThrowingAxe;
        }
        return false;
    }

    public static float getRangedSpeed(ItemStack itemstack) {
        int base = 20;
        int time = 0;
        if (itemstack != null) {
            Item item = itemstack.getItem();
            if (item instanceof LOTRItemCrossbow) {
                time = ((LOTRItemCrossbow)item).getMaxDrawTime();
            } else if (item instanceof LOTRItemBow) {
                time = ((LOTRItemBow)item).getMaxDrawTime();
            } else if (item == Items.bow) {
                time = 20;
            }
            if (item instanceof LOTRItemSpear) {
                time = ((LOTRItemSpear)item).getMaxDrawTime();
            }
            if (item instanceof LOTRItemBlowgun) {
                time = ((LOTRItemBlowgun)item).getMaxDrawTime();
            }
        }
        if (time > 0) {
            return 1.0f / ((float)time / (float)base);
        }
        return 0.0f;
    }

    public static float getRangedDamageFactor(ItemStack itemstack, boolean launchSpeedOnly) {
        float baseArrowFactor = 2.0f;
        float weaponFactor = 0.0f;
        if (itemstack != null) {
            Item item = itemstack.getItem();
            if (item instanceof LOTRItemCrossbow) {
                weaponFactor = baseArrowFactor * (float)((LOTRItemCrossbow)item).boltDamageFactor;
                weaponFactor *= LOTREnchantmentHelper.calcRangedDamageFactor(itemstack);
                if (!launchSpeedOnly) {
                    int power = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.power.effectId, (ItemStack)itemstack);
                    if (power > 0) {
                        weaponFactor = (float)((double)weaponFactor + ((double)power * 0.5 + 0.5));
                    }
                    weaponFactor *= 2.0f;
                }
            } else if (item instanceof ItemBow) {
                int power;
                weaponFactor = baseArrowFactor;
                if (item instanceof LOTRItemBow) {
                    weaponFactor *= (float)((LOTRItemBow)item).arrowDamageFactor;
                }
                weaponFactor *= LOTREnchantmentHelper.calcRangedDamageFactor(itemstack);
                if (!launchSpeedOnly && (power = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.power.effectId, (ItemStack)itemstack)) > 0) {
                    weaponFactor = (float)((double)weaponFactor + ((double)power * 0.5 + 0.5));
                }
            } else if (item instanceof LOTRItemBlowgun) {
                weaponFactor = baseArrowFactor;
                if (!launchSpeedOnly) {
                    weaponFactor *= 1.0f / baseArrowFactor;
                }
                weaponFactor *= LOTREnchantmentHelper.calcRangedDamageFactor(itemstack);
            } else if (item instanceof LOTRItemSpear) {
                weaponFactor = ((LOTRItemSpear)item).getRangedDamageMultiplier(itemstack, null, null);
            } else if (item instanceof LOTRItemThrowingAxe) {
                weaponFactor = ((LOTRItemThrowingAxe)item).getRangedDamageMultiplier(itemstack, null, null);
            }
        }
        if (weaponFactor > 0.0f) {
            return weaponFactor / baseArrowFactor;
        }
        return 0.0f;
    }

    public static int getRangedKnockback(ItemStack itemstack) {
        if (LOTRWeaponStats.isMeleeWeapon(itemstack) || itemstack != null && itemstack.getItem() instanceof LOTRItemThrowingAxe) {
            return LOTRWeaponStats.getTotalKnockback(itemstack);
        }
        return EnchantmentHelper.getEnchantmentLevel((int)Enchantment.punch.effectId, (ItemStack)itemstack) + LOTREnchantmentHelper.calcRangedKnockback(itemstack);
    }

    public static int getArmorProtection(ItemStack itemstack) {
        Item item;
        if (itemstack != null && (item = itemstack.getItem()) instanceof ItemArmor) {
            ItemArmor armor = (ItemArmor)item;
            int i = armor.damageReduceAmount;
            return i += LOTREnchantmentHelper.calcCommonArmorProtection(itemstack);
        }
        return 0;
    }

    public static int getTotalArmorValue(EntityPlayer entityplayer) {
        int protection = 0;
        for (int i = 0; i < entityplayer.inventory.armorInventory.length; ++i) {
            ItemStack stack = entityplayer.inventory.armorInventory[i];
            if (stack != null && stack.getItem() instanceof ISpecialArmor) {
                protection += ((ISpecialArmor)stack.getItem()).getArmorDisplay(entityplayer, stack, i);
                continue;
            }
            if (stack == null || !(stack.getItem() instanceof ItemArmor)) continue;
            protection += LOTRWeaponStats.getArmorProtection(stack);
        }
        return protection;
    }

    static {
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemDagger.class, 1.5f);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemDagger3.class, 0.667f);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemSpear.class, 0.833f);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemPolearm.class, 0.667f);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemPolearmLong.class, 0.5f);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemLance.class, 0.5f);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemBattleaxe.class, 0.75f);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemHammer.class, 0.667f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemDagger.class, 0.75f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemSpear.class, 1.5f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemDagger3.class, 1.0f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemPolearm.class, 1.0f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemPolearmLong.class, 2.0f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemLance.class, 2.0f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemBalrogWhip.class, 1.5f);
        LOTRWeaponStats.registerMeleeExtraKnockback(LOTRItemHammer.class, 1);
        LOTRWeaponStats.registerMeleeExtraKnockback(LOTRItemHammerUlfang.class, 2);
        LOTRWeaponStats.registerMeleeExtraKnockback(LOTRItemDagger3.class, 1);
        LOTRWeaponStats.registerMeleeExtraKnockback(LOTRItemLance.class, 1);
        MAX_MODIFIABLE_REACH = 2.0f;
        MAX_MODIFIABLE_SPEED = 1.5f;
        MAX_MODIFIABLE_KNOCKBACK = 3;
    }
}

