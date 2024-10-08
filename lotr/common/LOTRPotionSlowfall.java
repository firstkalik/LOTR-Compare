/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCustomPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class LOTRPotionSlowfall
extends LOTRCustomPotion {
    public static double maxSpeed = 0.4;

    public LOTRPotionSlowfall(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(33, false, 4720135, tex, namePot);
        this.setPotionName("potion.lotr.slowfall");
        this.setIconIndex(0, 4);
    }

    public boolean isReady(int tick, int level) {
        int freq = 5 >> level;
        return freq > 0 && tick % freq == 0;
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        double maxMotion = -maxSpeed / (double)(amplifier + 1);
        if (entity.motionY < maxMotion) {
            entity.addVelocity(0.0, maxMotion - entity.motionY, 0.0);
            entity.velocityChanged = true;
        }
        if (entity.fallDistance > 0.0f && entity.isPotionActive((Potion)this)) {
            entity.fallDistance = 0.0f;
        }
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int getStatusIconIndex() {
        return 0;
    }
}

