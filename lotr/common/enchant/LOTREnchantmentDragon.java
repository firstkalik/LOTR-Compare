/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantmentProtectionSpecial;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTREnchantmentDragon
extends LOTREnchantmentProtectionSpecial {
    public LOTREnchantmentDragon(String s, int level) {
        super(s, level);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.nightVision.desc", (Object[])new Object[]{this.formatAdditiveInt(this.calcIntEffectDuration())});
    }

    public void applyEffect(EntityLivingBase entity) {
        if (!entity.worldObj.isRemote) {
            int duration = this.calcIntEffectDuration();
            entity.addPotionEffect(new PotionEffect(Potion.nightVision.id, duration));
        }
    }

    protected int calcIntEffectDuration() {
        return 200 + this.protectLevel * 100;
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    protected boolean protectsAgainst(DamageSource var1) {
        return false;
    }

    @Override
    protected int calcIntProtection() {
        return 0;
    }
}

