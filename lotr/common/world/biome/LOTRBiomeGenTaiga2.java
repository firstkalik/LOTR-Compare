/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.entity.animal.LOTREntityDeer2;
import lotr.common.entity.animal.LOTREntityElk;
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.entity.animal.LOTREntityPolarBear;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenTundra;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenTaiga2
extends LOTRBiomeGenTundra {
    public LOTRBiomeGenTaiga2(int i, boolean major) {
        super(i, major);
        this.clearBiomeVariants();
        this.topBlock = Blocks.snow;
        this.variantChance = 0.75f;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 10, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer2.class, 10, 4, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityElk.class, 10, 4, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityPolarBear.class, 10, 1, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 4, 1, 4));
        this.spawnableLOTRAmbientList.clear();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_SPRUCE);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_PINE);
        this.decorator.treesPerChunk = 2;
        this.decorator.flowersPerChunk = 2;
        this.decorator.lichenPerChunk = 0;
        this.decorator.lichenPerChunk2 = 0;
        this.decorator.grassPerChunk = 4;
        this.decorator.doubleGrassPerChunk = 2;
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.SPRUCE, 200);
        this.decorator.addTree(LOTRTreeType.SPRUCE_THIN, 100);
        this.decorator.addTree(LOTRTreeType.SPRUCE_DEAD, 50);
        this.decorator.addTree(LOTRTreeType.FIR, 200);
        this.decorator.addTree(LOTRTreeType.PINE, 200);
        this.registerTaigaFlowers();
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.5f;
    }
}

