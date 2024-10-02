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

public class LOTRPotionProtection
extends LOTRCustomPotion {
    public LOTRPotionProtection(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(id, isBadEffect, potionColor, tex, namePot);
        this.setPotionName("potion.lotr.protection");
    }

    @Override
    public int getStatusIconIndex() {
        return 0;
    }
}

