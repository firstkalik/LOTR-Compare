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
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStiffbeard;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.structure2.LOTRWorldGenDwarvenMineEntranceRuined2;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsHouseStiffbeard;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsSmithy;
import lotr.common.world.structure2.LOTRWorldGenRedMountainsSmithy2;
import lotr.common.world.structure2.LOTRWorldGenRuinedRedDwarvenTower;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenRedMountainsFoothillsStiffbeard
extends LOTRBiomeGenRedMountainsStiffbeard {
    public LOTRBiomeGenRedMountainsFoothillsStiffbeard(int i, boolean major) {
        super(i, major);
        this.decorator.biomeGemFactor = 1.8f;
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedRedDwarvenTower(false), 100);
        this.decorator.addRandomStructure(new LOTRWorldGenRedMountainsSmithy(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenDwarvenMineEntranceRuined2(false), 200);
        this.decorator.addRandomStructure(new LOTRWorldGenRedMountainsSmithy2(false), 200);
        this.invasionSpawns.addInvasion(LOTRInvasions.RHUN, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = 100 + random.nextInt(80);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenRedMountainsHouseStiffbeard(false).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterRedMountains;
    }
}

