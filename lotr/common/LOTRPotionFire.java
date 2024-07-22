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
 *  net.minecraft.util.MathHelper
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
import net.minecraft.util.MathHelper;

public class LOTRPotionFire
extends Potion {
    public static float fireDuration = 10.0f;

    public LOTRPotionFire() {
        super(34, false, 7829367);
        this.setPotionName("potion.lotr.fire");
        this.setIconIndex(0, 4);
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        int duration = MathHelper.ceiling_float_int((float)((float)(amplifier + 1) * fireDuration));
        entity.setFire(duration);
    }

    public boolean isInstant() {
        return true;
    }

    public void affectEntity(EntityLivingBase thrower, EntityLivingBase entity, int amplifier, double potency) {
        int duration = MathHelper.ceiling_double_int((double)((double)(amplifier + 1) * (double)fireDuration * potency));
        entity.setFire(duration);
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

