/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockFlower;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import lotr.common.world.biome.LOTRBiomeGenMorgulVale;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBlockMorgulFlower
extends LOTRBlockFlower {
    public LOTRBlockMorgulFlower() {
        float f = 0.125f;
        this.setFlowerBounds(f, 0.0f, f, 1.0f - f, 0.8f, 1.0f - f);
        this.setTickRandomly(true);
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return super.canBlockStay(world, i, j, k) || LOTRBiomeGenMordor.isSurfaceMordorBlock(world, i, j - 1, k);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        super.updateTick(world, i, j, k, random);
        if (!world.isRemote && world.getBiomeGenForCoords(i, k) instanceof LOTRBiomeGenMorgulVale) {
            double range = 5.0;
            AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1)).expand(range, range, range);
            List entities = world.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
            for (Object obj : entities) {
                EntityLivingBase entity = (EntityLivingBase)obj;
                if (!this.isEntityVulnerable(entity)) continue;
                int dur = 200;
                entity.addPotionEffect(new PotionEffect(Potion.confusion.id, dur));
            }
        }
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        EntityLivingBase living;
        super.onEntityCollidedWithBlock(world, i, j, k, entity);
        if (!world.isRemote && entity instanceof EntityLivingBase && this.isEntityVulnerable(living = (EntityLivingBase)entity)) {
            int dur = 100;
            living.addPotionEffect(new PotionEffect(Potion.poison.id, dur));
            living.addPotionEffect(new PotionEffect(Potion.blindness.id, dur * 2));
        }
    }

    private boolean isEntityVulnerable(EntityLivingBase entity) {
        if (LOTRMod.getNPCFaction((Entity)entity) == LOTRFaction.MORDOR) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            if (entityplayer.capabilities.isCreativeMode) {
                return false;
            }
            float alignment = LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.MORDOR);
            float max = 250.0f;
            if (alignment >= 250.0f) {
                return false;
            }
            if (alignment > 0.0f) {
                float f = alignment / max;
                f = 1.0f - f;
                return entity.getRNG().nextFloat() < f;
            }
            return true;
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (random.nextInt(4) == 0) {
            double d = (float)i + MathHelper.randomFloatClamp((Random)random, (float)0.1f, (float)0.9f);
            double d1 = (float)j + MathHelper.randomFloatClamp((Random)random, (float)0.5f, (float)0.75f);
            double d2 = (float)k + MathHelper.randomFloatClamp((Random)random, (float)0.1f, (float)0.9f);
            if (random.nextBoolean()) {
                LOTRMod.proxy.spawnParticle("morgulWater", d, d1, d2, 0.0, 0.0, 0.0);
            } else {
                LOTRMod.proxy.spawnParticle("whiteSmoke", d, d1, d2, 0.0, 0.0, 0.0);
            }
        }
    }
}

