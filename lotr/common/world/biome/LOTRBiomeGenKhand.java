/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.world.biome;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityCamel;
import lotr.common.entity.npc.LOTREntityNearHaradMerchant;
import lotr.common.entity.npc.LOTREntityNomadMerchant;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class LOTRBiomeGenKhand
extends LOTRBiome {
    private static NoiseGeneratorPerlin noiseDirt = new NoiseGeneratorPerlin(new Random(3869098386927266L), 1);
    private static NoiseGeneratorPerlin noiseSand = new NoiseGeneratorPerlin(new Random(92726978206783582L), 1);

    public LOTRBiomeGenKhand(int i, boolean major) {
        super(i, major);
        this.setDisableRain();
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityCamel.class, 8, 2, 6));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.NOMAD_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
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
        this.addFlower(LOTRMod.miniCactus, 0, 2);
        this.registerTravellingTrader(LOTREntityScrapTrader.class);
        this.registerTravellingTrader(LOTREntityNomadMerchant.class);
        this.registerTravellingTrader(LOTREntityNearHaradMerchant.class);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.WIND, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        double d;
        double d2;
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = noiseDirt.func_151601_a((double)i * 0.09, (double)k * 0.09);
        double d22 = noiseDirt.func_151601_a((double)i * 0.4, (double)k * 0.4);
        double d3 = noiseSand.func_151601_a((double)i * 0.002, (double)k * 0.002);
        double d4 = noiseSand.func_151601_a((double)i * 0.09, (double)k * 0.09);
        double d5 = noiseSand.func_151601_a((double)i * 0.4, (double)k * 0.4);
        d4 *= 0.5;
        d5 *= 0.5;
        if (d3 + d + d2 > 0.7) {
            this.topBlock = Blocks.sand;
            this.topBlockMeta = 0;
            this.fillerBlock = this.topBlock;
            this.fillerBlockMeta = this.topBlockMeta;
        } else if (d1 + d22 > 0.3) {
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
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterKhand;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.KHAND;
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.PATH;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.NEAR_HARAD.getSubregion("desert");
    }
}

