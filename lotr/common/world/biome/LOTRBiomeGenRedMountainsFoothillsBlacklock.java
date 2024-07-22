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
import lotr.common.LOTRAchievement;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsBlacklock;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.structure2.LOTRWorldGenRedDwarvenTower2;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsHouseBlacklock;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsSmithy;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenRedMountainsFoothillsBlacklock
extends LOTRBiomeGenRedMountainsBlacklock {
    public LOTRBiomeGenRedMountainsFoothillsBlacklock(int i, boolean major) {
        super(i, major);
        this.decorator.biomeGemFactor = 1.8f;
        this.decorator.addRandomStructure(new LOTRWorldGenRedDwarvenTower2(false), 100);
        this.decorator.addRandomStructure(new LOTRWorldGenRedMountainsSmithy(false), 100);
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = 70 + random.nextInt(80);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenRedMountainsHouseBlacklock(false).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterRedMountains;
    }
}

