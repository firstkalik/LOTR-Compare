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
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenLebennin;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.structure2.LOTRWorldGenRottenHouse;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenAnduinMouth
extends LOTRBiomeGenLebennin {
    public LOTRBiomeGenAnduinMouth(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        this.clearBiomeVariants();
        this.variantChance = 1.0f;
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFrog.class, 8, 1, 3));
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_SWAMP);
        this.decorator.sandPerChunk = 0;
        this.decorator.quagmirePerChunk = 2;
        this.decorator.treesPerChunk = 0;
        this.decorator.willowPerChunk = 1;
        this.decorator.logsPerChunk = 1;
        this.decorator.flowersPerChunk = 5;
        this.decorator.grassPerChunk = 10;
        this.decorator.doubleGrassPerChunk = 10;
        this.decorator.enableFern = true;
        this.decorator.waterlilyPerChunk = 5;
        this.decorator.canePerChunk = 10;
        this.decorator.reedPerChunk = 4;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.OAK, 200);
        this.decorator.addTree(LOTRTreeType.OAK_SWAMP, 500);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 300);
        this.decorator.addTree(LOTRTreeType.BIRCH, 100);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 100);
        this.registerSwampFlowers();
        this.decorator.clearRandomStructures();
        this.decorator.addRandomStructure(new LOTRWorldGenRottenHouse(false), 500);
        this.decorator.clearVillages();
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterAnduinMouth;
    }
}

