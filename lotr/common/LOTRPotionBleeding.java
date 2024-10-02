/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lotr.common.LOTRCustomPotion;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTRPotionBleeding
extends LOTRCustomPotion {
    public static int damageInterval = 60;
    public static int damageAmount = 1;
    private Map<EntityLivingBase, Double[]> lastPositions = new HashMap<EntityLivingBase, Double[]>();
    private Map<EntityLivingBase, Integer> jumpCd = new HashMap<EntityLivingBase, Integer>();

    public LOTRPotionBleeding(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(36, true, 4720135, tex, namePot);
        this.setPotionName("potion.lotr.bleeding");
        this.setIconIndex(0, 3);
        this.setEffectiveness(1.0);
    }

    public boolean isReady(int tick, int level) {
        int freq = damageInterval >> level;
        return freq > 0 && tick % freq == 0;
    }

    public boolean isJumping(EntityLivingBase entity) {
        if (!this.jumpCd.containsKey((Object)entity) || this.jumpCd.get((Object)entity) <= 0) {
            this.jumpCd.put(entity, 10);
            return entity.motionY > 0.0 && !entity.onGround;
        }
        this.jumpCd.put(entity, this.jumpCd.get((Object)entity) - 1);
        return false;
    }

    public boolean isMoving(EntityLivingBase entity) {
        double threshold = 0.005;
        Double[] lastPos = this.lastPositions.get((Object)entity);
        if (lastPos == null) {
            lastPos = new Double[]{entity.posX, entity.posY, entity.posZ};
            this.lastPositions.put(entity, lastPos);
            return false;
        }
        double deltaX = Math.abs(entity.posX - lastPos[0]);
        double deltaY = Math.abs(entity.posY - lastPos[1]);
        double deltaZ = Math.abs(entity.posZ - lastPos[2]);
        this.lastPositions.put(entity, new Double[]{entity.posX, entity.posY, entity.posZ});
        return deltaX > threshold || deltaY > threshold || deltaZ > threshold;
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        float halfHeart;
        float currentHealth = entity.getHealth();
        int effectLevel = amplifier + 1;
        float red = 0.8f;
        float green = 0.0f;
        float blue = 0.0f;
        if (effectLevel >= 2) {
            red = 0.5f;
        }
        if (effectLevel >= 3) {
            red = 1.0f;
            green = 0.0f;
            blue = 0.0f;
        }
        if (currentHealth > (halfHeart = entity.getMaxHealth() / 10.0f) && currentHealth > 1.0f) {
            World world = entity.worldObj;
            if (this.isJumping(entity) || entity.isSprinting()) {
                entity.attackEntityFrom(DamageSource.generic, (float)(damageAmount * 2));
            } else {
                entity.attackEntityFrom(DamageSource.generic, (float)damageAmount);
            }
            if (!world.isRemote) {
                LOTRPacketParticles packet = new LOTRPacketParticles("reddust", 10, entity.posX, entity.posY + (double)entity.height / 2.0, entity.posZ, 0.0, 0.1, 0.0);
                LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)entity, 64.0));
            } else {
                for (int i = 0; i < 20; ++i) {
                    double d0 = entity.posX + (world.rand.nextDouble() - 0.5) * (double)entity.width;
                    double d1 = entity.posY + world.rand.nextDouble() * (double)entity.height - 0.25;
                    double d2 = entity.posZ + (world.rand.nextDouble() - 0.5) * (double)entity.width;
                    world.spawnParticle("reddust", d0, d1, d2, (double)red, (double)green, (double)blue);
                }
            }
        }
    }

    @Override
    public int getStatusIconIndex() {
        return 0;
    }
}

