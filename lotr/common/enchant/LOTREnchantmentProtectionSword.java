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

import java.util.HashSet;
import java.util.Set;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentProtectionSpecial;
import lotr.common.item.LOTRItemAlatarStaff;
import lotr.common.item.LOTRItemBalrogWhip;
import lotr.common.item.LOTRItemBattleaxe;
import lotr.common.item.LOTRItemGandalfStaffGrey;
import lotr.common.item.LOTRItemGandalfStaffWhite;
import lotr.common.item.LOTRItemHammer;
import lotr.common.item.LOTRItemPallandoStaff;
import lotr.common.item.LOTRItemPolearm;
import lotr.common.item.LOTRItemRadaghastStaff;
import lotr.common.item.LOTRItemSarumanStaff;
import lotr.common.item.LOTRItemSpear;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtectionSword
extends LOTREnchantmentProtectionSpecial {
    public static Set<Class<? extends LOTRItemSword>> sword = new HashSet<Class<? extends LOTRItemSword>>();

    public LOTREnchantmentProtectionSword(String s) {
        super(s, 1);
        this.setValueModifier(2.0f);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.protectSword.desc", (Object[])new Object[]{this.formatAdditiveInt(this.calcIntProtection())});
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        if (super.canApply(itemstack, considering)) {
            Item item = itemstack.getItem();
            return item instanceof ItemArmor && ((ItemArmor)item).getArmorMaterial() == LOTRMaterial.GALVORN.toArmorMaterial() || item instanceof ItemArmor && ((ItemArmor)item).getArmorMaterial() == LOTRMaterial.GONDOLIN.toArmorMaterial() || item instanceof ItemArmor && ((ItemArmor)item).getArmorMaterial() == LOTRMaterial.MITHRIL_ELVEN.toArmorMaterial();
        }
        return false;
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        ItemStack weapon;
        Entity attacker = source.getEntity();
        Entity entity = source.getSourceOfDamage();
        return attacker instanceof EntityLivingBase && attacker == entity && (weapon = ((EntityLivingBase)attacker).getHeldItem()) != null && weapon.getItem() instanceof LOTRItemSword && !sword.contains(weapon.getItem().getClass());
    }

    @Override
    protected int calcIntProtection() {
        return 4;
    }

    static {
        sword.add(LOTRItemAlatarStaff.class);
        sword.add(LOTRItemPallandoStaff.class);
        sword.add(LOTRItemRadaghastStaff.class);
        sword.add(LOTRItemSarumanStaff.class);
        sword.add(LOTRItemGandalfStaffGrey.class);
        sword.add(LOTRItemGandalfStaffWhite.class);
        sword.add(LOTRItemBalrogWhip.class);
        sword.add(LOTRItemBattleaxe.class);
        sword.add(LOTRItemHammer.class);
        sword.add(LOTRItemPolearm.class);
        sword.add(LOTRItemSpear.class);
    }
}

