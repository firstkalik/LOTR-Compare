/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditRhun;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure2.LOTRWorldGenBurntHouse;
import lotr.common.world.structure2.LOTRWorldGenRhunhouseruined;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBiomeGenWastelandRhun
extends LOTRBiome {
    protected static NoiseGeneratorPerlin noiseDirt = new NoiseGeneratorPerlin(new Random(47684796930956L), 1);
    protected static NoiseGeneratorPerlin noiseStone = new NoiseGeneratorPerlin(new Random(8894086030764L), 1);
    protected static NoiseGeneratorPerlin noiseSnow = new NoiseGeneratorPerlin(new Random(2490309256000602L), 1);
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 3);

    public LOTRBiomeGenWastelandRhun(int i, boolean major) {
        super(i, major);
        this.setEnableSnow();
        this.spawnableCreatureList.clear();
        this.spawnableLOTRAmbientList.clear();
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_ORCS, 10).setSpawnChance(1000), LOTRBiomeSpawnList.entry(LOTRSpawnList.DURMETH_WARGS, 5).setSpawnChance(1000)};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACKLOCK, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.STIFFBEARD, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.STONEFOOT, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.IRONFIST, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer61 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.AVARI_ELF_WARRIORS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer61);
        this.variantChance = 0.2f;
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_SPRUCE);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK_SPRUCE);
        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 1;
        this.decorator.doubleGrassPerChunk = 2;
        this.decorator.generateOrcDungeon = true;
        this.decorator.addTree(LOTRTreeType.SPRUCE_DEAD, 100);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenBurntHouse(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenRhunhouseruined(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 500);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_UNCOMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.AVARI_ELF, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.IRONFIST, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.STIFFBEARD, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.BLACKLOCK, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.STONEFOOT, LOTREventSpawner.EventChance.RARE);
        this.setBanditEntityClass(LOTREntityBanditRhun.class);
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.RHUN;
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterWastelandRhun;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.RHUN.getSubregion("tundra");
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        Block topBlock_pre = this.topBlock;
        int topBlockMeta_pre = this.topBlockMeta;
        Block fillerBlock_pre = this.fillerBlock;
        int fillerBlockMeta_pre = this.fillerBlockMeta;
        double d1 = noiseDirt.func_151601_a((double)i * 0.07, (double)k * 0.07);
        double d2 = noiseDirt.func_151601_a((double)i * 0.3, (double)k * 0.3);
        double d3 = noiseStone.func_151601_a((double)i * 0.07, (double)k * 0.07);
        if (d3 + noiseStone.func_151601_a((double)i * 0.3, (double)k * 0.3) > 1.2) {
            this.topBlock = Blocks.dirt;
            this.topBlockMeta = 0;
            this.fillerBlock = this.topBlock;
            this.fillerBlockMeta = this.topBlockMeta;
        } else if (d1 + d2 > 0.9) {
            this.topBlock = Blocks.dirt;
            this.topBlockMeta = 2;
        }
        super.generateBiomeTerrain(world, random, blocks, meta, i, k, stoneNoise, height, variant);
        this.topBlock = topBlock_pre;
        this.topBlockMeta = topBlockMeta_pre;
        this.fillerBlock = fillerBlock_pre;
        this.fillerBlockMeta = fillerBlockMeta_pre;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        if (random.nextInt(2) == 0) {
            int i1 = i + random.nextInt(16) + 8;
            int k1 = k + random.nextInt(16) + 8;
            int j1 = world.getHeightValue(i1, k1);
            int bushes = 4 + random.nextInt(20);
            for (int l = 0; l < bushes; ++l) {
                int i2 = i1 + MathHelper.getRandomIntegerInRange((Random)random, (int)-4, (int)4);
                int k2 = k1 + MathHelper.getRandomIntegerInRange((Random)random, (int)-4, (int)4);
                int j2 = j1 + MathHelper.getRandomIntegerInRange((Random)random, (int)-1, (int)1);
                Block below = world.getBlock(i2, j2 - 1, k2);
                Block block = world.getBlock(i2, j2, k2);
                if (!below.canSustainPlant((IBlockAccess)world, i2, j2 - 1, k2, ForgeDirection.UP, (IPlantable)Blocks.sapling) || block.getMaterial().isLiquid() || !block.isReplaceable((IBlockAccess)world, i2, j2, k2)) continue;
                BlockLeaves leafBlock = Blocks.leaves;
                int leafMeta = 1;
                if (random.nextInt(3) == 0) {
                    leafBlock = LOTRMod.leaves3;
                    leafMeta = 0;
                } else if (random.nextInt(3) == 0) {
                    leafBlock = LOTRMod.leaves2;
                    leafMeta = 1;
                }
                world.setBlock(i2, j2, k2, (Block)leafBlock, leafMeta | 4, 2);
            }
        }
        if (random.nextInt(40) == 0) {
            int boulders = 1 + random.nextInt(4);
            for (int l = 0; l < boulders; ++l) {
                int i1 = i + random.nextInt(16) + 8;
                int k1 = k + random.nextInt(16) + 8;
                this.boulderGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
            }
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.04f;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int i, int j, int k) {
        int color1 = 10708034;
        int color2 = 13747522;
        double d1 = biomeTerrainNoise.func_151601_a((double)i * 0.002, (double)k * 0.002);
        double d2 = biomeTerrainNoise.func_151601_a((double)i * 0.04, (double)k * 0.04);
        float noise = (float)MathHelper.clamp_double((double)(d1 + (d2 *= 0.4)), (double)-2.0, (double)2.0);
        noise += 2.0f;
        noise /= 4.0f;
        float[] rgb1 = new Color(color1).getColorComponents(null);
        float[] rgb2 = new Color(color2).getColorComponents(null);
        float[] rgbNoise = new float[rgb1.length];
        for (int l = 0; l < rgbNoise.length; ++l) {
            rgbNoise[l] = rgb1[l] + (rgb2[l] - rgb1[l]) * noise;
        }
        return new Color(rgbNoise[0], rgbNoise[1], rgbNoise[2]).getRGB();
    }

    public static boolean isTundraSnowy(int i, int k) {
        double d;
        double d2;
        double d1 = noiseSnow.func_151601_a((double)i * 0.002, (double)k * 0.002);
        double d22 = noiseSnow.func_151601_a((double)i * 0.05, (double)k * 0.05);
        double d3 = noiseSnow.func_151601_a((double)i * 0.3, (double)k * 0.3);
        d22 *= 0.3;
        d3 *= 0.3;
        return d1 + d + d2 > 0.8;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.02f;
    }
}

