/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenWindMountains;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.structure2.LOTRWorldGenWindDwarvenTower;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenWindMountainsFoothills
extends LOTRBiomeGenWindMountains {
    public LOTRBiomeGenWindMountainsFoothills(int i, boolean major) {
        super(i, major);
        this.biomeTerrain.resetXZScale();
        this.biomeTerrain.resetHeightStretchFactor();
        this.decorator.biomeGemFactor = 1.0f;
        this.decorator.addRandomStructure(new LOTRWorldGenWindDwarvenTower(false), 300);
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
    }
}

