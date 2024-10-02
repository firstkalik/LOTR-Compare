/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenLastDesert;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class LOTRBiomeGenRhunSemiDesert
extends LOTRBiomeGenLastDesert {
    public LOTRBiomeGenRhunSemiDesert(int i, boolean major) {
        super(i, major);
        this.clearBiomeVariants();
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE_BARREN);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.SHRUBLAND_OAK);
        this.decorator.clearTrees();
        this.decorator.addTree(LOTRTreeType.OAK_DEAD, 500);
        this.decorator.addTree(LOTRTreeType.OAK_DESERT, 500);
        this.decorator.grassPerChunk = 5;
        this.decorator.doubleGrassPerChunk = 0;
        this.decorator.cactiPerChunk = 1;
        this.decorator.deadBushPerChunk = 1;
        this.decorator.clearRandomStructures();
        this.decorator.clearVillages();
        this.addFlower(LOTRMod.miniCactus, 0, 2);
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        double d2;
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = biomeTerrainNoise.func_151601_a((double)i * 0.08, (double)k * 0.08);
        if (d1 + (d2 = biomeTerrainNoise.func_151601_a((double)i * 0.3, (double)k * 0.3)) > 0.3) {
            this.topBlock = Blocks.dirt;
            this.topBlockMeta = 1;
            this.fillerBlock = this.topBlock;
            this.fillerBlockMeta = this.topBlockMeta;
        }
        super.generateBiomeTerrain(world, random, blocks, meta, i, k, stoneNoise, height, variant);
        this.topBlock = topBlock_pre;
        this.topBlockMeta = topBlockMeta_pre;
        this.fillerBlock = fillerBlock_pre;
        this.fillerBlockMeta = fillerBlockMeta_pre;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int j1;
        int k1;
        int i1;
        super.decorate(world, random, i, k);
        if (random.nextInt(20) == 0 && world.getBlock(i1 = i + random.nextInt(16) + 8, j1 = world.getHeightValue(i1, k1 = k + random.nextInt(16) + 8) - 1, k1) == Blocks.sand) {
            world.setBlock(i1, j1, k1, Blocks.dirt);
            LOTRTreeType treeType = LOTRTreeType.OAK_DESERT;
            WorldGenAbstractTree tree = treeType.create(false, random);
            if (!tree.generate(world, random, i1, j1 + 1, k1)) {
                world.setBlock(i1, j1, k1, (Block)Blocks.sand);
            }
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.05f;
    }

    @Override
    public LOTRBiome.GrassBlockAndMeta getRandomGrass(Random random) {
        return new LOTRBiome.GrassBlockAndMeta(LOTRMod.aridGrass, 0);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterRhunSemiDesert;
    }
}

