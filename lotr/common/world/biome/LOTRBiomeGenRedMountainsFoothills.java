/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import addon.drealm.entity.animal.DREntityMountainGoat;
import addon.drealm.feature.DRWorldGenDoubleFlower;
import java.util.List;
import java.util.Random;
import lotr.common.entity.animal.LOTREntityWildBoar;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenRedMountains;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenRedMountainsFoothills
extends LOTRBiomeGenRedMountains {
    public LOTRBiomeGenRedMountainsFoothills(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(DREntityMountainGoat.class, 10, 2, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityWildBoar.class, 10, 4, 4));
        this.decorator.biomeGemFactor = 1.0f;
        this.decorator.doubleFlowersPerChunk = 1;
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
    }

    @Override
    public WorldGenerator getRandomWorldGenForDoubleFlower(Random random) {
        DRWorldGenDoubleFlower doubleFlowerGen = new DRWorldGenDoubleFlower();
        doubleFlowerGen.setFlowerType(0);
        return doubleFlowerGen;
    }
}

