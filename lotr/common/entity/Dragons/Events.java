/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.world.ChunkEvent
 *  net.minecraftforge.event.world.ChunkEvent$Load
 *  net.minecraftforge.event.world.ChunkEvent$Unload
 */
package lotr.common.entity.Dragons;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import lotr.common.entity.Dragons.entity.LOTREntityDragonAlpha;
import lotr.common.entity.Dragons.entity.LOTREntityDragonAnkalagon;
import lotr.common.entity.Dragons.entity.LOTREntityDragonHunter;
import lotr.common.entity.Dragons.entity.LOTREntityDragonScout;
import lotr.common.world.biome.LOTRBiomeGenErebor;
import lotr.common.world.biome.LOTRBiomeGenGreyMountains;
import lotr.common.world.biome.LOTRBiomeGenIronHills;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsBlacklock;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsIronfist;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStiffbeard;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStonefoot;
import lotr.common.world.biome.LOTRBiomeGenWindMountains;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.ChunkEvent;

public class Events {
    private boolean isDragonSpawnBiome(BiomeGenBase biome) {
        return biome instanceof LOTRBiomeGenGreyMountains || biome instanceof LOTRBiomeGenWindMountains || biome instanceof LOTRBiomeGenRedMountainsBlacklock || biome instanceof LOTRBiomeGenRedMountainsIronfist || biome instanceof LOTRBiomeGenIronHills || biome instanceof LOTRBiomeGenErebor || biome instanceof LOTRBiomeGenRedMountainsStiffbeard || biome instanceof LOTRBiomeGenRedMountainsStonefoot;
    }

    private boolean isPlayerNotInCreativeNear(ChunkEvent.Load event) {
        List players = event.world.playerEntities;
        for (EntityPlayer player : players) {
            if (player.capabilities.isCreativeMode || !(player.getDistanceSq((double)event.getChunk().getChunkCoordIntPair().getCenterXPos(), (double)(event.world.getActualHeight() / 2), (double)event.getChunk().getChunkCoordIntPair().getCenterZPosition()) < 65536.0)) continue;
            return true;
        }
        return false;
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onChunkUnloadEvent(ChunkEvent.Unload event) {
        for (List l : event.getChunk().entityLists) {
            for (Entity el : l) {
                if (!(el instanceof LOTREntityDragonAnkalagon) && !(el instanceof LOTREntityDragonAlpha) && !(el instanceof LOTREntityDragonHunter) && !(el instanceof LOTREntityDragonScout)) continue;
                el.setDead();
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onChunkLoadEvent(ChunkEvent.Load event) {
        int hunterselect;
        Block block;
        int alphaselect;
        int ankalagonselect;
        int mid;
        BiomeGenBase biome = event.world.getBiomeGenForCoords(event.getChunk().getChunkCoordIntPair().getCenterXPos(), event.getChunk().getChunkCoordIntPair().getCenterZPosition());
        if (!this.isDragonSpawnBiome(biome)) {
            return;
        }
        if (!this.isPlayerNotInCreativeNear(event)) {
            return;
        }
        int scoutselect = (int)(1.0 + Math.random() * 1538.0);
        if (scoutselect == 1) {
            mid = event.world.getActualHeight() / 2;
            block = event.world.getBlock(event.getChunk().getChunkCoordIntPair().getCenterXPos(), mid, event.getChunk().getChunkCoordIntPair().getCenterZPosition());
            if (block != null && block.getMaterial() == Material.air) {
                LOTREntityDragonScout entityDragonScout = new LOTREntityDragonScout(event.world);
                if (!entityDragonScout.worldObj.isRemote) {
                    entityDragonScout.setPosition((double)event.getChunk().getChunkCoordIntPair().getCenterXPos(), (double)mid, (double)event.getChunk().getChunkCoordIntPair().getCenterZPosition());
                    event.world.spawnEntityInWorld((Entity)entityDragonScout);
                }
            }
        }
        if ((hunterselect = (int)(1.0 + Math.random() * 2205.0)) == 1) {
            mid = event.world.getActualHeight() / 2;
            block = event.world.getBlock(event.getChunk().getChunkCoordIntPair().getCenterXPos(), mid, event.getChunk().getChunkCoordIntPair().getCenterZPosition());
            if (block != null && block.getMaterial() == Material.air) {
                LOTREntityDragonHunter entityDragonHunter = new LOTREntityDragonHunter(event.world);
                if (!entityDragonHunter.worldObj.isRemote) {
                    entityDragonHunter.setPosition((double)event.getChunk().getChunkCoordIntPair().getCenterXPos(), (double)mid, (double)event.getChunk().getChunkCoordIntPair().getCenterZPosition());
                    event.world.spawnEntityInWorld((Entity)entityDragonHunter);
                }
            }
        }
        if ((alphaselect = (int)(1.0 + Math.random() * 2352.0)) == 21) {
            mid = event.world.getActualHeight() / 2;
            block = event.world.getBlock(event.getChunk().getChunkCoordIntPair().getCenterXPos(), mid, event.getChunk().getChunkCoordIntPair().getCenterZPosition());
            if (block != null && block.getMaterial() == Material.air) {
                LOTREntityDragonAlpha entityDragonAlpha = new LOTREntityDragonAlpha(event.world);
                if (!entityDragonAlpha.worldObj.isRemote) {
                    entityDragonAlpha.setPosition((double)event.getChunk().getChunkCoordIntPair().getCenterXPos(), (double)mid, (double)event.getChunk().getChunkCoordIntPair().getCenterZPosition());
                    event.world.spawnEntityInWorld((Entity)entityDragonAlpha);
                }
            }
        }
        if ((ankalagonselect = (int)(1.0 + Math.random() * 2777.0)) == 3) {
            mid = event.world.getActualHeight() / 2;
            block = event.world.getBlock(event.getChunk().getChunkCoordIntPair().getCenterXPos(), mid, event.getChunk().getChunkCoordIntPair().getCenterZPosition());
            if (block != null && block.getMaterial() == Material.air) {
                LOTREntityDragonAnkalagon entityDragonAnkalagon = new LOTREntityDragonAnkalagon(event.world);
                if (!entityDragonAnkalagon.worldObj.isRemote) {
                    entityDragonAnkalagon.setPosition((double)event.getChunk().getChunkCoordIntPair().getCenterXPos(), (double)mid, (double)event.getChunk().getChunkCoordIntPair().getCenterZPosition());
                    event.world.spawnEntityInWorld((Entity)entityDragonAnkalagon);
                }
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onEntityDeathEvent(LivingDeathEvent event) {
        if (event.entity instanceof LOTREntityDragonAlpha || event.entity instanceof LOTREntityDragonAnkalagon || event.entity instanceof LOTREntityDragonHunter || event.entity instanceof LOTREntityDragonScout) {
            event.setCanceled(true);
        }
    }
}

