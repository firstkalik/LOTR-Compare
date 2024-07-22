/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStonefoot;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsHouseStonefoot;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRBiomeGenRedMountainsFoothillsStonefoot
extends LOTRBiomeGenRedMountainsStonefoot {
    public LOTRBiomeGenRedMountainsFoothillsStonefoot(int i, boolean major) {
        super(i, major);
        this.decorator.biomeGemFactor = 1.5f;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = 90 + random.nextInt(80);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenRedMountainsHouseStonefoot(false).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterRedMountains;
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
    }
}

