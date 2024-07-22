/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class LOTRPotionBleeding
extends Potion {
    public static int damageInterval = 60;
    public static int damageAmount = 1;

    public LOTRPotionBleeding() {
        super(36, true, 4720135);
        this.setPotionName("potion.lotr.bleeding");
        this.setIconIndex(0, 3);
        this.setEffectiveness(1.0);
    }

    public boolean isReady(int tick, int level) {
        int freq = damageInterval >> level;
        return freq > 0 && tick % freq == 0;
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        float halfHeart;
        float currentHealth = entity.getHealth();
        if (currentHealth > (halfHeart = entity.getMaxHealth() / 10.0f) && currentHealth > 1.0f) {
            entity.attackEntityFrom(DamageSource.generic, (float)damageAmount);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasStatusIcon() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        LOTRMod.proxy.renderCustomPotionEffect(x, y, effect, mc);
    }
}

