/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import lotr.common.LOTRDamage;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentBane;
import lotr.common.enchant.LOTREnchantmentType;
import lotr.common.item.LOTRItemBalrogWhip;
import lotr.common.item.LOTRWeaponStats;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentWeaponSpecial2
extends LOTREnchantment {
    private boolean compatibleBane = true;
    private boolean compatibleOtherSpecial = false;

    public LOTREnchantmentWeaponSpecial2(String s) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.RANGED_LAUNCHER});
        this.setValueModifier(3.0f);
        this.setBypassAnvilLimit();
    }

    public LOTREnchantmentWeaponSpecial2 setIncompatibleBane() {
        this.compatibleBane = false;
        return this;
    }

    public LOTREnchantmentWeaponSpecial2 setCompatibleOtherSpecial() {
        this.compatibleOtherSpecial = true;
        return this;
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        if (LOTRWeaponStats.isMeleeWeapon(itemstack)) {
            return StatCollector.translateToLocalFormatted((String)("lotr.enchant." + this.enchantName + ".desc.melee"), (Object[])new Object[0]);
        }
        return StatCollector.translateToLocalFormatted((String)("lotr.enchant." + this.enchantName + ".desc.ranged"), (Object[])new Object[0]);
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        if (super.canApply(itemstack, considering)) {
            Item item = itemstack.getItem();
            return !(item instanceof LOTRItemBalrogWhip) || this != LOTREnchantment.fire && this != LOTREnchantment.chill && this != LOTREnchantment.wither;
        }
        return false;
    }

    @Override
    public boolean isCompatibleWith(LOTREnchantment other) {
        if (!this.compatibleBane && other instanceof LOTREnchantmentBane) {
            return false;
        }
        return this.compatibleOtherSpecial || !(other instanceof LOTREnchantmentWeaponSpecial2) || ((LOTREnchantmentWeaponSpecial2)other).compatibleOtherSpecial;
    }

    public static int getFireAmount() {
        return 2;
    }

    public static void doPoisonAttack(EntityLivingBase entity) {
        if (entity instanceof EntityPlayerMP) {
            LOTRDamage.doFrostDamage((EntityPlayerMP)entity);
        }
        int duration = 2;
        entity.addPotionEffect(new PotionEffect(Potion.wither.id, duration * 20, 1));
    }

    public static void doChillAttack(EntityLivingBase entity) {
        if (entity instanceof EntityPlayerMP) {
            LOTRDamage.doFrostDamage((EntityPlayerMP)entity);
        }
        int duration = 5;
        entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration * 20, 1));
        LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.CHILLING, (Entity)entity);
        LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)entity, 64.0));
    }
}

