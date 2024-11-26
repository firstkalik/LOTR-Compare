/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCustomPotion;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class LOTRPotionCurse
extends LOTRCustomPotion {
    public LOTRPotionCurse(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(id, true, potionColor, tex, namePot);
        this.setPotionName("potion.lotr.curse");
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int getStatusIconIndex() {
        return 0;
    }
}

