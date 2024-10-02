/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common;

import lotr.common.LOTRCustomPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTRPotionInfection
extends LOTRCustomPotion {
    public LOTRPotionInfection(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(id, isBadEffect, potionColor, tex, namePot);
        this.setPotionName("potion.lotr.infection");
        this.setEffectiveness(1.0);
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (entity.worldObj.getWorldTime() % 80L == 0L) {
            entity.attackEntityFrom(DamageSource.magic, 1.0f);
        }
    }

    public boolean isReady(int tick, int level) {
        return true;
    }

    @Override
    public int getStatusIconIndex() {
        return 0;
    }
}

