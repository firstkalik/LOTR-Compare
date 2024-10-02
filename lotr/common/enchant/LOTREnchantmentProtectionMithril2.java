/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.enchant.LOTREnchantmentProtectionSpecial;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtectionMithril2
extends LOTREnchantmentProtectionSpecial {
    public LOTREnchantmentProtectionMithril2(String s) {
        super(s, 1);
        this.setValueModifier(2.0f);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.protectMithril.desc", (Object[])new Object[]{this.formatAdditiveInt(this.calcIntProtection())});
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        if (super.canApply(itemstack, considering)) {
            Item item = itemstack.getItem();
            return item instanceof ItemArmor && ((ItemArmor)item).getArmorMaterial() == LOTRMaterial.MORIA.toArmorMaterial() || item instanceof ItemArmor && ((ItemArmor)item).getArmorMaterial() == LOTRMaterial.MITHRILD.toArmorMaterial() || item instanceof ItemArmor && ((ItemArmor)item).getArmorMaterial() == LOTRMaterial.BILBO.toArmorMaterial();
        }
        return false;
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        ItemStack weapon;
        Entity attacker = source.getEntity();
        Entity entity = source.getSourceOfDamage();
        if (attacker instanceof EntityLivingBase && attacker == entity && (weapon = ((EntityLivingBase)attacker).getHeldItem()) != null) {
            ItemStack weaponBase = weapon.copy();
            LOTREnchantmentHelper.clearEnchants(weaponBase);
            float range = LOTRWeaponStats.getMeleeReachFactor(weaponBase);
            if (range >= 1.3f) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected int calcIntProtection() {
        return 8;
    }
}

