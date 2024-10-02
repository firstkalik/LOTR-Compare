/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityFrog;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenFarHarad;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenFarHaradMangrove
extends LOTRBiomeGenFarHarad {
    public LOTRBiomeGenFarHaradMangrove(int i, boolean major) {
        super(i, major);
        this.topBlock = Blocks.sand;
        this.spawnableWaterCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFrog.class, 8, 1, 3));
        this.decorator.addSoil((WorldGenerator)new WorldGenMinable(Blocks.dirt, 1, 60, (Block)Blocks.sand), 12.0f, 60, 90);
        this.decorator.quagmirePerChunk = 1;
        this.decorator.treesPerChunk = 5;
        this.decorator.vinesPerChunk = 20;
        this.decorator.grassPerChunk = 8;
        this.decorator.enableFern = true;
        this.decorator.waterlilyPerChunk = 3;
        this.decorator.addTree(LOTRTreeType.MANGROVE, 1000);
        this.decorator.addTree(LOTRTreeType.ACACIA, 10);
        this.decorator.addTree(LOTRTreeType.OAK_DESERT, 5);
        this.registerSwampFlowers();
        this.biomeColors.setGrass(10466679);
        this.biomeColors.setFoliage(6715206);
        this.biomeColors.setWater(5985085);
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.FAR_HARAD.getSubregion("mangrove");
    }

    @Override
    public boolean getEnableRiver() {
        return true;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int l;
        int i1;
        super.decorate(world, random, i, k);
        for (l = 0; l < 2; ++l) {
            int k1;
            int j1;
            i1 = i + random.nextInt(16);
            if (!world.getBlock(i1, (j1 = world.getTopSolidOrLiquidBlock(i1, k1 = k + random.nextInt(16))) - 1, k1).isOpaqueCube() || world.getBlock(i1, j1, k1).getMaterial() != Material.water) continue;
            this.decorator.genTree(world, random, i1, j1, k1);
        }
        for (l = 0; l < 20; ++l) {
            int k1;
            int j1;
            i1 = i + random.nextInt(16);
            if (!world.getBlock(i1, j1 = 50 + random.nextInt(15), k1 = k + random.nextInt(16)).isOpaqueCube() || world.getBlock(i1, j1 + 1, k1).getMaterial() != Material.water) continue;
            int height = 2 + random.nextInt(3);
            for (int j2 = j1; j2 <= j1 + height; ++j2) {
                world.setBlock(i1, j2, k1, LOTRMod.wood3, 3, 2);
            }
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.15f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.4f;
    }
}

