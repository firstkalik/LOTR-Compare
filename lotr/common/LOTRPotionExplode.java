/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTRPotionExplode
extends Potion {
    public static float explosionSize = 0.5f;
    public static int delayTicks = 20;

    public LOTRPotionExplode() {
        super(35, false, 52377);
        this.setPotionName("potion.lotr.explosion");
        this.setIconIndex(0, 5);
    }

    public boolean isInstant() {
        return true;
    }

    public void affectEntity(EntityLivingBase thrower, EntityLivingBase entity, int amplifier, double potency) {
        float strength = (float)(amplifier + 1) * explosionSize * (float)potency;
        World world = entity.worldObj;
        if (!world.isRemote) {
            world.createExplosion((Entity)thrower, entity.posX, entity.posY, entity.posZ, strength, true);
            entity.hurtResistantTime = 0;
            entity.attackEntityFrom(DamageSource.generic, 2.0f);
        }
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        float strength = (float)(amplifier + 1) * explosionSize;
        World world = entity.worldObj;
        if (!world.isRemote) {
            world.createExplosion((Entity)entity, entity.posX, entity.posY, entity.posZ, strength, true);
            entity.hurtResistantTime = 0;
            entity.attackEntityFrom(DamageSource.generic, 2.0f);
        }
    }

    public boolean isReady(int tick, int level) {
        int freq = 1 >> level;
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

