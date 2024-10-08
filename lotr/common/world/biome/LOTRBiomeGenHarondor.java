/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenRuinedGondorTower;
import lotr.common.world.structure2.LOTRWorldGenGondorObelisk;
import lotr.common.world.structure2.LOTRWorldGenHarnedorTower;
import lotr.common.world.structure2.LOTRWorldGenMumakSkeleton;
import lotr.common.world.structure2.LOTRWorldGenNearHaradDesertCamp;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.village.LOTRVillageGen;
import lotr.common.world.village.LOTRVillageGenHarnedor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenHarondor
extends LOTRBiome {
    protected static NoiseGeneratorPerlin noiseDirt = new NoiseGeneratorPerlin(new Random(52809698208569676L), 1);
    protected static NoiseGeneratorPerlin noiseSand = new NoiseGeneratorPerlin(new Random(474929905950956L), 1);
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 3);

    public LOTRBiomeGenHarondor(int i, boolean major) {
        super(i, major);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_ITHILIEN, 10).setSpawnChance(500)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDOR_WARRIORS, 75).setSpawnChance(500), LOTRBiomeSpawnList.entry(LOTRSpawnList.SOUTHRON_WARRIORS, 20).setSpawnChance(500), LOTRBiomeSpawnList.entry(LOTRSpawnList.NOMAD_WARRIORS, 5).setSpawnChance(500)};
        this.npcSpawnList.newFactionList(90, 0.0f).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_ITHILIEN, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 5), LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 5).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.LOSSARNACH_SOLDIERS, 2).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.LEBENNIN_SOLDIERS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HARNEDOR_WARRIORS, 75), LOTRBiomeSpawnList.entry(LOTRSpawnList.SOUTHRON_WARRIORS, 20), LOTRBiomeSpawnList.entry(LOTRSpawnList.NOMAD_WARRIORS, 5)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ROHIRRIM_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 3).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer7 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_WARRIORS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer7);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer8 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORWAITH_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer8);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer9 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HALF_TROLLS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer9);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE_BARREN);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
        this.addBiomeVariant(LOTRBiomeVariant.SHRUBLAND_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_SCRUBLAND);
        this.addBiomeVariant(LOTRBiomeVariant.WASTELAND);
        this.decorator.addOre((WorldGenerator)new WorldGenMinable(Blocks.lapis_ore, 6), 1.0f, 0, 48);
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.flowersPerChunk = 4;
        this.decorator.cactiPerChunk = 1;
        this.decorator.deadBushPerChunk = 1;
        this.decorator.addTree(LOTRTreeType.OAK_DESERT, 1000);
        this.decorator.addTree(LOTRTreeType.CEDAR, 250);
        this.decorator.addTree(LOTRTreeType.LEMON, 5);
        this.decorator.addTree(LOTRTreeType.ORANGE, 5);
        this.decorator.addTree(LOTRTreeType.LIME, 5);
        this.decorator.addTree(LOTRTreeType.OLIVE, 5);
        this.decorator.addTree(LOTRTreeType.OLIVE_LARGE, 5);
        this.decorator.addTree(LOTRTreeType.ALMOND, 5);
        this.decorator.addTree(LOTRTreeType.PLUM, 5);
        this.decorator.addRandomStructure(new LOTRWorldGenNearHaradDesertCamp(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 600);
        this.decorator.addRandomStructure(new LOTRWorldGenGondorObelisk(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedGondorTower(false), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenHarnedorTower(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenMumakSkeleton(false), 6000);
        this.decorator.addVillage(new LOTRVillageGenHarnedor(this, 0.1f).setRuined());
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GONDOR, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GONDOR_ITHILIEN, LOTREventSpawner.EventChance.COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GONDOR_DOL_AMROTH, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR_BLACK_URUK, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.NEAR_HARAD_HARNEDOR, LOTREventSpawner.EventChance.COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.NEAR_HARAD_COAST, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.UMBAR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.UMBAR_CORSAIR, LOTREventSpawner.EventChance.UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.NEAR_HARAD_GULF, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterHarondor;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.HARONDOR;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.NEAR_HARAD.getSubregion("harondor");
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.GONDOR_MIX.setRepair(0.6f);
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
        int k1;
        int l;
        int i1;
        super.decorate(world, random, i, k);
        if (random.nextInt(16) == 0) {
            int boulders = 1 + random.nextInt(4);
            for (l = 0; l < boulders; ++l) {
                i1 = i + random.nextInt(16) + 8;
                k1 = k + random.nextInt(16) + 8;
                this.boulderGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
            }
        }
        if (random.nextInt(8) == 0) {
            int trees = 1 + random.nextInt(8);
            for (l = 0; l < trees; ++l) {
                i1 = i + random.nextInt(8) + 8;
                k1 = k + random.nextInt(8) + 8;
                int j1 = world.getHeightValue(i1, k1);
                this.decorator.genTree(world, random, i1, j1, k1);
            }
        }
    }

    @Override
    public LOTRBiome.GrassBlockAndMeta getRandomGrass(Random random) {
        return random.nextInt(3) == 0 ? new LOTRBiome.GrassBlockAndMeta(LOTRMod.aridGrass, 0) : super.getRandomGrass(random);
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.1f;
    }
}

