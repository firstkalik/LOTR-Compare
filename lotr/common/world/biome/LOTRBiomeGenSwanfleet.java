/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.entity.animal.LOTREntityFrog;
import lotr.common.entity.animal.LOTREntitySwan;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenEriador;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.structure2.LOTRWorldGenRottenHouse;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenSwanfleet
extends LOTRBiomeGenEriador {
    public LOTRBiomeGenSwanfleet(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFrog.class, 8, 1, 3));
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntitySwan.class, 20, 4, 8));
        this.clearBiomeVariants();
        this.variantChance = 1.0f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_SWAMP);
        this.decorator.sandPerChunk = 0;
        this.decorator.quagmirePerChunk = 1;
        this.decorator.treesPerChunk = 0;
        this.decorator.willowPerChunk = 1;
        this.decorator.logsPerChunk = 1;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 8;
        this.decorator.enableFern = true;
        this.decorator.waterlilyPerChunk = 4;
        this.decorator.canePerChunk = 10;
        this.decorator.reedPerChunk = 3;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.OAK_TALL, 500);
        this.decorator.addTree(LOTRTreeType.OAK_SWAMP, 500);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 300);
        this.decorator.addTree(LOTRTreeType.BIRCH, 200);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 100);
        this.registerSwampFlowers();
        this.decorator.addRandomStructure(new LOTRWorldGenRottenHouse(false), 400);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.invasionSpawns.clearInvasions();
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD_WARG, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterSwanfleet;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ERIADOR.getSubregion("swanfleet");
    }
}

