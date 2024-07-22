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

public class LOTRPotionFrostResistance
extends Potion {
    public LOTRPotionFrostResistance() {
        super(31, false, 5149641);
        this.setPotionName("potion.lotr.frostResistance");
        this.setIconIndex(0, 1);
    }

    public void performEffect(EntityLivingBase entity, int level) {
    }

    public boolean isReady(int tick, int level) {
        int freq = 5 >> level;
        return freq > 0 ? tick % freq == 0 : true;
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

