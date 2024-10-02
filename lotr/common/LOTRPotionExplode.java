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
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCustomPotion;
import lotr.common.LOTRMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTRPotionExplode
extends LOTRCustomPotion {
    public static float explosionSize = 0.5f;
    public static int delayTicks = 20;

    public LOTRPotionExplode(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(35, isBadEffect, 4720135, tex, namePot);
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
            world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "random.explode", 1.0f, 1.0f);
            this.spawnExplosionParticles(world, entity.posX, entity.posY, entity.posZ);
            entity.hurtResistantTime = 0;
            entity.attackEntityFrom(DamageSource.generic, 2.0f);
        }
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        float strength = (float)(amplifier + 1) * explosionSize;
        World world = entity.worldObj;
        if (!world.isRemote) {
            world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "random.explode", 1.0f, 1.0f);
            this.spawnExplosionParticles(world, entity.posX, entity.posY, entity.posZ);
            entity.hurtResistantTime = 0;
            entity.attackEntityFrom(DamageSource.generic, 2.0f);
        }
    }

    private void spawnExplosionParticles(World world, double x, double y, double z) {
        double offsetY;
        int i;
        double offsetX;
        double offsetZ;
        for (i = 0; i < 50; ++i) {
            offsetX = world.rand.nextGaussian() * 0.02;
            offsetY = world.rand.nextGaussian() * 0.02;
            offsetZ = world.rand.nextGaussian() * 0.02;
            world.spawnParticle("explode", x, y, z, offsetX, offsetY, offsetZ);
        }
        for (i = 0; i < 100; ++i) {
            offsetX = world.rand.nextGaussian() * 0.02;
            offsetY = world.rand.nextGaussian() * 0.02;
            offsetZ = world.rand.nextGaussian() * 0.02;
            world.spawnParticle("largesmoke", x, y, z, offsetX, offsetY, offsetZ);
        }
    }

    public boolean isReady(int tick, int level) {
        int freq = 1 >> level;
        return freq > 0 ? tick % freq == 0 : true;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        LOTRMod.proxy.renderCustomPotionEffect(x, y, effect, mc);
    }
}

