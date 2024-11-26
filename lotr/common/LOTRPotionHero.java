/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
package lotr.common;

import lotr.common.LOTRCustomPotion;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class LOTRPotionHero
extends LOTRCustomPotion {
    public LOTRPotionHero(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(id, false, potionColor, tex, namePot);
        this.setPotionName("potion.lotr.hero");
    }

    @Override
    public int getStatusIconIndex() {
        return 0;
    }
}

