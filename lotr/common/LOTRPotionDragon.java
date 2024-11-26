/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
package lotr.common;

import lotr.common.LOTRCustomPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class LOTRPotionDragon
extends LOTRCustomPotion {
    public LOTRPotionDragon(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(50, false, 8003769, tex, namePot);
        this.setPotionName("potion.lotr.dragon");
        this.setEffectiveness(1.0);
    }

    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
    }

    @Override
    public int getStatusIconIndex() {
        return 0;
    }
}

