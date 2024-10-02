/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenGreyMountains;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenGreyMountainsFoothills
extends LOTRBiomeGenGreyMountains {
    public LOTRBiomeGenGreyMountainsFoothills(int i, boolean major) {
        super(i, major);
        this.decorator.biomeGemFactor = 0.75f;
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 4, 1, 4));
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
    }
}

