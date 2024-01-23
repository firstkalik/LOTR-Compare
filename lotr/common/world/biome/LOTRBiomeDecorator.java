/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.util.WeightedRandom$Item
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenCactus
 *  net.minecraft.world.gen.feature.WorldGenDeadBush
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenMelon
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenPumpkin
 *  net.minecraft.world.gen.feature.WorldGenReed
 *  net.minecraft.world.gen.feature.WorldGenVines
 *  net.minecraft.world.gen.feature.WorldGenWaterlily
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.LOTRChunkProvider;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBerryBush;
import lotr.common.world.feature.LOTRWorldGenBiomeFlowers;
import lotr.common.world.feature.LOTRWorldGenBushes;
import lotr.common.world.feature.LOTRWorldGenCaveCobwebs;
import lotr.common.world.feature.LOTRWorldGenCorn;
import lotr.common.world.feature.LOTRWorldGenFallenLeaves;
import lotr.common.world.feature.LOTRWorldGenLogs;
import lotr.common.world.feature.LOTRWorldGenReeds;
import lotr.common.world.feature.LOTRWorldGenSand;
import lotr.common.world.feature.LOTRWorldGenStalactites;
import lotr.common.world.feature.LOTRWorldGenStreams;
import lotr.common.world.feature.LOTRWorldGenSurfaceGravel;
import lotr.common.world.feature.LOTRWorldGenTrollHoard;
import lotr.common.world.map.LOTRRoads;
import lotr.common.world.structure.LOTRWorldGenMarshHut;
import lotr.common.world.structure.LOTRWorldGenOrcDungeon;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import lotr.common.world.structure2.LOTRWorldGenGrukHouse;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import lotr.common.world.structure2.LOTRWorldGenTicketBooth;
import lotr.common.world.village.LOTRVillageGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeDecorator {
    private World worldObj;
    private Random rand;
    private int chunkX;
    private int chunkZ;
    private LOTRBiome biome;
    private List<OreGenerant> biomeSoils = new ArrayList<OreGenerant>();
    private List<OreGenerant> biomeOres = new ArrayList<OreGenerant>();
    private List<OreGenerant> biomeGems = new ArrayList<OreGenerant>();
    public float biomeOreFactor = 1.0f;
    public float biomeGemFactor = 0.5f;
    protected WorldGenerator clayGen = new LOTRWorldGenSand(Blocks.clay, 5, 1);
    private WorldGenerator sandGen = new LOTRWorldGenSand((Block)Blocks.sand, 7, 2);
    private WorldGenerator whiteSandGen = new LOTRWorldGenSand(LOTRMod.whiteSand, 7, 2);
    private WorldGenerator quagmireGen = new LOTRWorldGenSand(LOTRMod.quagmire, 7, 2);
    private WorldGenerator surfaceGravelGen = new LOTRWorldGenSurfaceGravel();
    private WorldGenerator flowerGen = new LOTRWorldGenBiomeFlowers();
    private WorldGenerator logGen = new LOTRWorldGenLogs();
    private WorldGenerator mushroomBrownGen = new WorldGenFlowers((Block)Blocks.brown_mushroom);
    private WorldGenerator mushroomRedGen = new WorldGenFlowers((Block)Blocks.red_mushroom);
    private WorldGenerator caneGen = new WorldGenReed();
    private WorldGenerator reedGen = new LOTRWorldGenReeds(LOTRMod.reeds);
    private WorldGenerator dryReedGen = new LOTRWorldGenReeds(LOTRMod.driedReeds);
    private WorldGenerator cornGen = new LOTRWorldGenCorn();
    private WorldGenerator pumpkinGen = new WorldGenPumpkin();
    private WorldGenerator waterlilyGen = new WorldGenWaterlily();
    private WorldGenerator cobwebGen = new LOTRWorldGenCaveCobwebs();
    private WorldGenerator stalactiteGen = new LOTRWorldGenStalactites();
    private WorldGenerator vinesGen = new WorldGenVines();
    private WorldGenerator cactusGen = new WorldGenCactus();
    private WorldGenerator melonGen = new WorldGenMelon();
    public int sandPerChunk = 4;
    public int clayPerChunk = 3;
    public int quagmirePerChunk = 0;
    public int treesPerChunk = 0;
    public int willowPerChunk = 0;
    public int logsPerChunk = 0;
    public int vinesPerChunk = 0;
    public int flowersPerChunk = 2;
    public int doubleFlowersPerChunk = 0;
    public int grassPerChunk = 1;
    public int doubleGrassPerChunk = 0;
    public boolean enableFern = false;
    public boolean enableSpecialGrasses = true;
    public int deadBushPerChunk = 0;
    public int waterlilyPerChunk = 0;
    public int mushroomsPerChunk = 0;
    public boolean enableRandomMushroom = true;
    public int canePerChunk = 0;
    public int reedPerChunk = 1;
    public float dryReedChance = 0.1f;
    public int cornPerChunk = 0;
    public int cactiPerChunk = 0;
    public float melonPerChunk = 0.0f;
    public boolean generateWater = true;
    public boolean generateLava = true;
    public boolean generateCobwebs = true;
    public boolean generateAthelas = false;
    public boolean whiteSand = false;
    private int treeClusterSize;
    private int treeClusterChance = -1;
    private WorldGenerator orcDungeonGen = new LOTRWorldGenOrcDungeon(false);
    private WorldGenerator trollHoardGen = new LOTRWorldGenTrollHoard();
    public boolean generateOrcDungeon = false;
    public boolean generateTrollHoard = false;
    private List<LOTRTreeType.WeightedTreeType> treeTypes = new ArrayList<LOTRTreeType.WeightedTreeType>();
    private Random structureRand = new Random();
    private List<RandomStructure> randomStructures = new ArrayList<RandomStructure>();
    private List<LOTRVillageGen> villages = new ArrayList<LOTRVillageGen>();

    public void addSoil(WorldGenerator gen, float f, int min, int max) {
        this.biomeSoils.add(new OreGenerant(gen, f, min, max));
    }

    public void addOre(WorldGenerator gen, float f, int min, int max) {
        this.biomeOres.add(new OreGenerant(gen, f, min, max));
    }

    public void addGem(WorldGenerator gen, float f, int min, int max) {
        this.biomeGems.add(new OreGenerant(gen, f, min, max));
    }

    public void clearOres() {
        this.biomeSoils.clear();
        this.biomeOres.clear();
        this.biomeGems.clear();
    }

    private void addDefaultOres() {
        this.addSoil((WorldGenerator)new WorldGenMinable(Blocks.dirt, 32), 40.0f, 0, 256);
        this.addSoil((WorldGenerator)new WorldGenMinable(Blocks.gravel, 32), 20.0f, 0, 256);
        this.addOre((WorldGenerator)new WorldGenMinable(Blocks.coal_ore, 16), 40.0f, 0, 128);
        this.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreCopper, 8), 16.0f, 0, 128);
        this.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreTin, 8), 16.0f, 0, 128);
        this.addOre((WorldGenerator)new WorldGenMinable(Blocks.iron_ore, 8), 20.0f, 0, 64);
        this.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreSulfur, 8), 2.0f, 0, 64);
        this.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreSaltpeter, 8), 2.0f, 0, 64);
        this.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreSalt, 12), 2.0f, 0, 64);
        this.addOre((WorldGenerator)new WorldGenMinable(Blocks.gold_ore, 8), 2.0f, 0, 32);
        this.addOre((WorldGenerator)new WorldGenMinable(LOTRMod.oreSilver, 8), 3.0f, 0, 32);
        this.addGem((WorldGenerator)new WorldGenMinable(LOTRMod.oreGem, 1, 6, Blocks.stone), 2.0f, 0, 64);
        this.addGem((WorldGenerator)new WorldGenMinable(LOTRMod.oreGem, 0, 6, Blocks.stone), 2.0f, 0, 64);
        this.addGem((WorldGenerator)new WorldGenMinable(LOTRMod.oreGem, 4, 5, Blocks.stone), 1.5f, 0, 48);
        this.addGem((WorldGenerator)new WorldGenMinable(LOTRMod.oreGem, 6, 5, Blocks.stone), 1.5f, 0, 48);
        this.addGem((WorldGenerator)new WorldGenMinable(LOTRMod.oreGem, 2, 4, Blocks.stone), 1.0f, 0, 32);
        this.addGem((WorldGenerator)new WorldGenMinable(LOTRMod.oreGem, 3, 4, Blocks.stone), 1.0f, 0, 32);
        this.addGem((WorldGenerator)new WorldGenMinable(LOTRMod.oreGem, 7, 4, Blocks.stone), 0.75f, 0, 24);
        this.addGem((WorldGenerator)new WorldGenMinable(LOTRMod.oreGem, 5, 4, Blocks.stone), 0.5f, 0, 16);
    }

    public LOTRBiomeDecorator(LOTRBiome lotrbiome) {
        this.biome = lotrbiome;
        this.addDefaultOres();
    }

    public void addTree(LOTRTreeType type, int weight) {
        this.treeTypes.add(new LOTRTreeType.WeightedTreeType(type, weight));
    }

    public void clearTrees() {
        this.treeTypes.clear();
    }

    public LOTRTreeType getRandomTree(Random random) {
        if (this.treeTypes.isEmpty()) {
            return LOTRTreeType.OAK;
        }
        WeightedRandom.Item item = WeightedRandom.getRandomItem((Random)random, this.treeTypes);
        return ((LOTRTreeType.WeightedTreeType)item).treeType;
    }

    public LOTRTreeType getRandomTreeForVariant(Random random, LOTRBiomeVariant variant) {
        if (variant.treeTypes.isEmpty()) {
            return this.getRandomTree(random);
        }
        float f = variant.variantTreeChance;
        if (random.nextFloat() < f) {
            return variant.getRandomTree(random);
        }
        return this.getRandomTree(random);
    }

    public void genTree(World world, Random random, int i, int j, int k) {
        WorldGenAbstractTree treeGen = this.biome.getTreeGen(world, random, i, j, k);
        treeGen.generate(world, random, i, j, k);
    }

    public void setTreeCluster(int size, int chance) {
        this.treeClusterSize = size;
        this.treeClusterChance = chance;
    }

    public void resetTreeCluster() {
        this.setTreeCluster(0, -1);
    }

    public void addRandomStructure(WorldGenerator structure, int chunkChance) {
        this.randomStructures.add(new RandomStructure(structure, chunkChance));
    }

    public void clearRandomStructures() {
        this.randomStructures.clear();
    }

    public void addVillage(LOTRVillageGen village) {
        this.villages.add(village);
    }

    public void clearVillages() {
        this.villages.clear();
    }

    public void checkForVillages(World world, int i, int k, LOTRChunkProvider.ChunkFlags chunkFlags) {
        chunkFlags.isVillage = false;
        chunkFlags.isFlatVillage = false;
        for (LOTRVillageGen village : this.villages) {
            List<LOTRVillageGen.AbstractInstance<?>> instances = village.getNearbyVillagesAtPosition(world, i, k);
            if (instances.isEmpty()) continue;
            chunkFlags.isVillage = true;
            for (LOTRVillageGen.AbstractInstance<?> inst : instances) {
                if (!inst.isFlat()) continue;
                chunkFlags.isFlatVillage = true;
            }
        }
    }

    public boolean anyFixedVillagesAt(World world, int i, int k) {
        for (LOTRVillageGen village : this.villages) {
            if (!village.anyFixedVillagesAt(world, i, k)) continue;
            return true;
        }
        return false;
    }

    public int getVariantTreesPerChunk(LOTRBiomeVariant variant) {
        int trees = this.treesPerChunk;
        if (variant.treeFactor > 1.0f) {
            trees = Math.max(trees, 1);
        }
        trees = Math.round((float)trees * variant.treeFactor);
        return trees;
    }

    public void decorate(World world, Random random, int i, int k) {
        this.worldObj = world;
        this.rand = random;
        this.chunkX = i;
        this.chunkZ = k;
        this.decorate();
    }

    private void decorate() {
        int i;
        int k;
        int l;
        int i2;
        int k2;
        int l2;
        int cluster;
        int l3;
        int k3;
        int l4;
        int j;
        int k4;
        WorldGenerator house;
        int k5;
        int j2;
        int l5;
        int j3;
        int k6;
        int j4;
        int l6;
        float reciprocalTreeFactor;
        int i3;
        int i4;
        int l7;
        int j5;
        int k7;
        int i5;
        LOTRBiomeVariant biomeVariant = ((LOTRWorldChunkManager)this.worldObj.getWorldChunkManager()).getBiomeVariantAt(this.chunkX + 8, this.chunkZ + 8);
        this.generateOres();
        biomeVariant.decorateVariant(this.worldObj, this.rand, this.chunkX, this.chunkZ, this.biome);
        if (this.rand.nextBoolean() && this.generateCobwebs) {
            i5 = this.chunkX + this.rand.nextInt(16) + 8;
            int j6 = this.rand.nextInt(60);
            k2 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.cobwebGen.generate(this.worldObj, this.rand, i5, j6, k2);
        }
        for (l7 = 0; l7 < 3; ++l7) {
            i2 = this.chunkX + this.rand.nextInt(16) + 8;
            j3 = this.rand.nextInt(60);
            int k8 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.stalactiteGen.generate(this.worldObj, this.rand, i2, j3, k8);
        }
        for (l7 = 0; l7 < this.quagmirePerChunk; ++l7) {
            i2 = this.chunkX + this.rand.nextInt(16) + 8;
            k2 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.quagmireGen.generate(this.worldObj, this.rand, i2, this.worldObj.getTopSolidOrLiquidBlock(i2, k2), k2);
        }
        for (l7 = 0; l7 < this.sandPerChunk; ++l7) {
            i2 = this.chunkX + this.rand.nextInt(16) + 8;
            k2 = this.chunkZ + this.rand.nextInt(16) + 8;
            WorldGenerator biomeSandGenerator = this.sandGen;
            if (this.whiteSand) {
                biomeSandGenerator = this.whiteSandGen;
            }
            biomeSandGenerator.generate(this.worldObj, this.rand, i2, this.worldObj.getTopSolidOrLiquidBlock(i2, k2), k2);
        }
        for (l7 = 0; l7 < this.clayPerChunk; ++l7) {
            i2 = this.chunkX + this.rand.nextInt(16) + 8;
            k2 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.clayGen.generate(this.worldObj, this.rand, i2, this.worldObj.getTopSolidOrLiquidBlock(i2, k2), k2);
        }
        if (this.rand.nextInt(60) == 0) {
            i5 = this.chunkX + this.rand.nextInt(16) + 8;
            k7 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.surfaceGravelGen.generate(this.worldObj, this.rand, i5, 0, k7);
        }
        if (!biomeVariant.disableStructures && Math.abs(this.chunkX) > 32 && Math.abs(this.chunkZ) > 32) {
            boolean roadNear;
            long seed = (long)(this.chunkX * 1879267) ^ (long)this.chunkZ * 67209689L;
            seed = seed * seed * 5829687L + seed * 2876L;
            this.structureRand.setSeed(seed);
            boolean bl = roadNear = LOTRRoads.isRoadNear(this.chunkX + 8, this.chunkZ + 8, 16) >= 0.0f;
            if (!roadNear) {
                for (RandomStructure randomstructure : this.randomStructures) {
                    if (this.structureRand.nextInt(randomstructure.chunkChance) != 0) continue;
                    int i6 = this.chunkX + this.rand.nextInt(16) + 8;
                    k3 = this.chunkZ + this.rand.nextInt(16) + 8;
                    j5 = this.worldObj.getTopSolidOrLiquidBlock(i6, k3);
                    randomstructure.structureGen.generate(this.worldObj, this.rand, i6, j5, k3);
                }
            }
            for (LOTRVillageGen village : this.villages) {
                village.generateInChunk(this.worldObj, this.chunkX, this.chunkZ);
            }
        }
        if (LOTRWorldGenMarshHut.generatesAt(this.worldObj, this.chunkX, this.chunkZ)) {
            i5 = this.chunkX + 8;
            k7 = this.chunkZ + 8;
            j3 = this.worldObj.getTopSolidOrLiquidBlock(i5, k7);
            house = new LOTRWorldGenMarshHut();
            house.restrictions = false;
            house.generate(this.worldObj, this.rand, i5, j3, k7);
        }
        if (LOTRWorldGenGrukHouse.generatesAt(this.worldObj, this.chunkX, this.chunkZ)) {
            i5 = this.chunkX + 8;
            k7 = this.chunkZ + 8;
            j3 = this.worldObj.getTopSolidOrLiquidBlock(i5, k7);
            house = new LOTRWorldGenGrukHouse(false);
            house.restrictions = false;
            house.generateWithSetRotation(this.worldObj, this.rand, i5, j3, k7, 2);
        }
        if (LOTRWorldGenTicketBooth.generatesAt(this.worldObj, this.chunkX, this.chunkZ)) {
            i5 = this.chunkX + 8;
            k7 = this.chunkZ + 8;
            j3 = this.worldObj.getTopSolidOrLiquidBlock(i5, k7);
            LOTRWorldGenTicketBooth booth = new LOTRWorldGenTicketBooth(false);
            booth.restrictions = false;
            ((LOTRWorldGenStructureBase2)booth).generateWithSetRotation(this.worldObj, this.rand, i5, j3, k7, 3);
        }
        int trees = this.getVariantTreesPerChunk(biomeVariant);
        if (this.rand.nextFloat() < this.biome.getTreeIncreaseChance() * biomeVariant.treeFactor) {
            ++trees;
        }
        if ((cluster = Math.round((float)this.treeClusterChance * (reciprocalTreeFactor = 1.0f / Math.max(biomeVariant.treeFactor, 0.001f)))) > 0) {
            Random chunkRand = new Random();
            long seed = (long)(this.chunkX / this.treeClusterSize * 3129871) ^ (long)(this.chunkZ / this.treeClusterSize) * 116129781L;
            seed = seed * seed * 42317861L + seed * 11L;
            chunkRand.setSeed(seed);
            if (chunkRand.nextInt(cluster) == 0) {
                trees += 6 + this.rand.nextInt(5);
            }
        }
        for (l4 = 0; l4 < trees; ++l4) {
            int i7 = this.chunkX + this.rand.nextInt(16) + 8;
            k = this.chunkZ + this.rand.nextInt(16) + 8;
            WorldGenAbstractTree treeGen = this.getRandomTreeForVariant(this.rand, biomeVariant).create(false, this.rand);
            treeGen.generate(this.worldObj, this.rand, i7, this.worldObj.getHeightValue(i7, k), k);
        }
        for (l4 = 0; l4 < this.willowPerChunk; ++l4) {
            int i8 = this.chunkX + this.rand.nextInt(16) + 8;
            k = this.chunkZ + this.rand.nextInt(16) + 8;
            WorldGenAbstractTree treeGen = LOTRTreeType.WILLOW_WATER.create(false, this.rand);
            treeGen.generate(this.worldObj, this.rand, i8, this.worldObj.getHeightValue(i8, k), k);
        }
        if (trees > 0) {
            float fallenLeaves = (float)trees / 2.0f;
            int fallenLeavesI = (int)fallenLeaves;
            float fallenLeavesR = fallenLeaves - (float)fallenLeavesI;
            if (this.rand.nextFloat() < fallenLeavesR) {
                ++fallenLeavesI;
            }
            l = 0;
            while ((float)l < fallenLeaves) {
                i3 = this.chunkX + this.rand.nextInt(16) + 8;
                k6 = this.chunkZ + this.rand.nextInt(16) + 8;
                new LOTRWorldGenFallenLeaves().generate(this.worldObj, this.rand, i3, this.worldObj.getTopSolidOrLiquidBlock(i3, k6), k6);
                ++l;
            }
        }
        if (trees > 0) {
            float bushes = (float)trees / 3.0f;
            int bushesI = (int)bushes;
            float bushesR = bushes - (float)bushesI;
            if (this.rand.nextFloat() < bushesR) {
                ++bushesI;
            }
            l = 0;
            while ((float)l < bushes) {
                i3 = this.chunkX + this.rand.nextInt(16) + 8;
                k6 = this.chunkZ + this.rand.nextInt(16) + 8;
                new LOTRWorldGenBushes().generate(this.worldObj, this.rand, i3, this.worldObj.getTopSolidOrLiquidBlock(i3, k6), k6);
                ++l;
            }
        }
        for (l6 = 0; l6 < this.logsPerChunk; ++l6) {
            int i9 = this.chunkX + this.rand.nextInt(16) + 8;
            int k9 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.logGen.generate(this.worldObj, this.rand, i9, this.worldObj.getHeightValue(i9, k9), k9);
        }
        for (l6 = 0; l6 < this.vinesPerChunk; ++l6) {
            int i10 = this.chunkX + this.rand.nextInt(16) + 8;
            int j7 = 64;
            k3 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.vinesGen.generate(this.worldObj, this.rand, i10, j7, k3);
        }
        int flowers = this.flowersPerChunk;
        flowers = Math.round((float)flowers * biomeVariant.flowerFactor);
        for (int l8 = 0; l8 < flowers; ++l8) {
            int i11 = this.chunkX + this.rand.nextInt(16) + 8;
            int j8 = this.rand.nextInt(128);
            int k10 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.flowerGen.generate(this.worldObj, this.rand, i11, j8, k10);
        }
        int doubleFlowers = this.doubleFlowersPerChunk;
        doubleFlowers = Math.round((float)doubleFlowers * biomeVariant.flowerFactor);
        for (int l9 = 0; l9 < doubleFlowers; ++l9) {
            int i12 = this.chunkX + this.rand.nextInt(16) + 8;
            j5 = this.rand.nextInt(128);
            k6 = this.chunkZ + this.rand.nextInt(16) + 8;
            WorldGenerator doubleFlowerGen = this.biome.getRandomWorldGenForDoubleFlower(this.rand);
            doubleFlowerGen.generate(this.worldObj, this.rand, i12, j5, k6);
        }
        int grasses = this.grassPerChunk;
        grasses = Math.round((float)grasses * biomeVariant.grassFactor);
        for (l = 0; l < grasses; ++l) {
            i3 = this.chunkX + this.rand.nextInt(16) + 8;
            j = this.rand.nextInt(128);
            int k11 = this.chunkZ + this.rand.nextInt(16) + 8;
            WorldGenerator grassGen = this.biome.getRandomWorldGenForGrass(this.rand);
            grassGen.generate(this.worldObj, this.rand, i3, j, k11);
        }
        int doubleGrasses = this.doubleGrassPerChunk;
        doubleGrasses = Math.round((float)doubleGrasses * biomeVariant.grassFactor);
        for (l5 = 0; l5 < doubleGrasses; ++l5) {
            i = this.chunkX + this.rand.nextInt(16) + 8;
            int j9 = this.rand.nextInt(128);
            int k12 = this.chunkZ + this.rand.nextInt(16) + 8;
            WorldGenerator grassGen = this.biome.getRandomWorldGenForDoubleGrass(this.rand);
            grassGen.generate(this.worldObj, this.rand, i, j9, k12);
        }
        for (l5 = 0; l5 < this.deadBushPerChunk; ++l5) {
            i = this.chunkX + this.rand.nextInt(16) + 8;
            int j10 = this.rand.nextInt(128);
            int k13 = this.chunkZ + this.rand.nextInt(16) + 8;
            new WorldGenDeadBush((Block)Blocks.deadbush).generate(this.worldObj, this.rand, i, j10, k13);
        }
        for (l5 = 0; l5 < this.waterlilyPerChunk; ++l5) {
            int j11;
            i = this.chunkX + this.rand.nextInt(16) + 8;
            int k14 = this.chunkZ + this.rand.nextInt(16) + 8;
            for (j11 = this.rand.nextInt(128); j11 > 0 && this.worldObj.getBlock(i, j11 - 1, k14) == Blocks.air; --j11) {
            }
            this.waterlilyGen.generate(this.worldObj, this.rand, i, j11, k14);
        }
        for (l5 = 0; l5 < this.mushroomsPerChunk; ++l5) {
            if (this.rand.nextInt(4) == 0) {
                i = this.chunkX + this.rand.nextInt(16) + 8;
                int k15 = this.chunkZ + this.rand.nextInt(16) + 8;
                int j12 = this.worldObj.getHeightValue(i, k15);
                this.mushroomBrownGen.generate(this.worldObj, this.rand, i, j12, k15);
            }
            if (this.rand.nextInt(8) != 0) continue;
            i = this.chunkX + this.rand.nextInt(16) + 8;
            k5 = this.chunkZ + this.rand.nextInt(16) + 8;
            j2 = this.worldObj.getHeightValue(i, k5);
            this.mushroomRedGen.generate(this.worldObj, this.rand, i, j2, k5);
        }
        if (this.enableRandomMushroom) {
            if (this.rand.nextInt(4) == 0) {
                i3 = this.chunkX + this.rand.nextInt(16) + 8;
                j = this.rand.nextInt(128);
                int k16 = this.chunkZ + this.rand.nextInt(16) + 8;
                this.mushroomBrownGen.generate(this.worldObj, this.rand, i3, j, k16);
            }
            if (this.rand.nextInt(8) == 0) {
                i3 = this.chunkX + this.rand.nextInt(16) + 8;
                j = this.rand.nextInt(128);
                k5 = this.chunkZ + this.rand.nextInt(16) + 8;
                this.mushroomRedGen.generate(this.worldObj, this.rand, i3, j, k5);
            }
        }
        for (l5 = 0; l5 < this.canePerChunk; ++l5) {
            i = this.chunkX + this.rand.nextInt(16) + 8;
            j4 = this.rand.nextInt(128);
            int k17 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.caneGen.generate(this.worldObj, this.rand, i, j4, k17);
        }
        for (l5 = 0; l5 < 10; ++l5) {
            i = this.chunkX + this.rand.nextInt(16) + 8;
            j4 = this.rand.nextInt(128);
            int k18 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.caneGen.generate(this.worldObj, this.rand, i, j4, k18);
        }
        for (l5 = 0; l5 < this.reedPerChunk; ++l5) {
            int j13;
            i = this.chunkX + this.rand.nextInt(16) + 8;
            k5 = this.chunkZ + this.rand.nextInt(16) + 8;
            for (j13 = this.rand.nextInt(128); j13 > 0 && this.worldObj.getBlock(i, j13 - 1, k5) == Blocks.air; --j13) {
            }
            if (this.rand.nextFloat() < this.dryReedChance) {
                this.dryReedGen.generate(this.worldObj, this.rand, i, j13, k5);
                continue;
            }
            this.reedGen.generate(this.worldObj, this.rand, i, j13, k5);
        }
        for (l5 = 0; l5 < this.cornPerChunk; ++l5) {
            i = this.chunkX + this.rand.nextInt(16) + 8;
            j4 = this.rand.nextInt(128);
            int k19 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.cornGen.generate(this.worldObj, this.rand, i, j4, k19);
        }
        for (l5 = 0; l5 < this.cactiPerChunk; ++l5) {
            i = this.chunkX + this.rand.nextInt(16) + 8;
            j4 = this.rand.nextInt(128);
            int k20 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.cactusGen.generate(this.worldObj, this.rand, i, j4, k20);
        }
        if (this.melonPerChunk > 0.0f) {
            int melonInt = MathHelper.floor_double((double)this.melonPerChunk);
            float melonF = this.melonPerChunk - (float)melonInt;
            for (l3 = 0; l3 < melonInt; ++l3) {
                int i13 = this.chunkX + this.rand.nextInt(16) + 8;
                int k21 = this.chunkZ + this.rand.nextInt(16) + 8;
                int j14 = this.worldObj.getHeightValue(i13, k21);
                this.melonGen.generate(this.worldObj, this.rand, i13, j14, k21);
            }
            if (this.rand.nextFloat() < melonF) {
                i4 = this.chunkX + this.rand.nextInt(16) + 8;
                int k22 = this.chunkZ + this.rand.nextInt(16) + 8;
                int j15 = this.worldObj.getHeightValue(i4, k22);
                this.melonGen.generate(this.worldObj, this.rand, i4, j15, k22);
            }
        }
        if (this.flowersPerChunk > 0 && this.rand.nextInt(32) == 0) {
            i3 = this.chunkX + this.rand.nextInt(16) + 8;
            j = this.rand.nextInt(128);
            k5 = this.chunkZ + this.rand.nextInt(16) + 8;
            this.pumpkinGen.generate(this.worldObj, this.rand, i3, j, k5);
        }
        if (this.flowersPerChunk > 0 && this.rand.nextInt(4) == 0) {
            i3 = this.chunkX + this.rand.nextInt(16) + 8;
            j = this.rand.nextInt(128);
            k5 = this.chunkZ + this.rand.nextInt(16) + 8;
            new LOTRWorldGenBerryBush().generate(this.worldObj, this.rand, i3, j, k5);
        }
        if (this.generateAthelas && this.rand.nextInt(30) == 0) {
            i3 = this.chunkX + this.rand.nextInt(16) + 8;
            j = this.rand.nextInt(128);
            k5 = this.chunkZ + this.rand.nextInt(16) + 8;
            new WorldGenFlowers(LOTRMod.athelas).generate(this.worldObj, this.rand, i3, j, k5);
        }
        if (this.generateWater) {
            int k23;
            LOTRWorldGenStreams waterGen = new LOTRWorldGenStreams((Block)Blocks.flowing_water);
            for (l2 = 0; l2 < 50; ++l2) {
                i4 = this.chunkX + this.rand.nextInt(16) + 8;
                j2 = this.rand.nextInt(this.rand.nextInt(120) + 8);
                k23 = this.chunkZ + this.rand.nextInt(16) + 8;
                waterGen.generate(this.worldObj, this.rand, i4, j2, k23);
            }
            if (this.biome.rootHeight > 1.0f) {
                for (l2 = 0; l2 < 50; ++l2) {
                    i4 = this.chunkX + this.rand.nextInt(16) + 8;
                    j2 = 100 + this.rand.nextInt(150);
                    k23 = this.chunkZ + this.rand.nextInt(16) + 8;
                    waterGen.generate(this.worldObj, this.rand, i4, j2, k23);
                }
            }
        }
        if (this.generateLava) {
            LOTRWorldGenStreams lavaGen = new LOTRWorldGenStreams((Block)Blocks.flowing_lava);
            int lava = 20;
            if (this.biome instanceof LOTRBiomeGenMordor) {
                lava = 50;
            }
            for (l3 = 0; l3 < lava; ++l3) {
                int i14 = this.chunkX + this.rand.nextInt(16) + 8;
                int j16 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(112) + 8) + 8);
                int k24 = this.chunkZ + this.rand.nextInt(16) + 8;
                lavaGen.generate(this.worldObj, this.rand, i14, j16, k24);
            }
        }
        if (this.generateOrcDungeon) {
            for (l5 = 0; l5 < 6; ++l5) {
                i = this.chunkX + this.rand.nextInt(16) + 8;
                j4 = this.rand.nextInt(128);
                k4 = this.chunkZ + this.rand.nextInt(16) + 8;
                this.orcDungeonGen.generate(this.worldObj, this.rand, i, j4, k4);
            }
        }
        if (this.generateTrollHoard) {
            for (l5 = 0; l5 < 2; ++l5) {
                i = this.chunkX + this.rand.nextInt(16) + 8;
                j4 = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)36, (int)90);
                k4 = this.chunkZ + this.rand.nextInt(16) + 8;
                this.trollHoardGen.generate(this.worldObj, this.rand, i, j4, k4);
            }
        }
        if (biomeVariant.boulderGen != null && this.rand.nextInt(biomeVariant.boulderChance) == 0) {
            int boulders = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)1, (int)biomeVariant.boulderMax);
            for (l2 = 0; l2 < boulders; ++l2) {
                i4 = this.chunkX + this.rand.nextInt(16) + 8;
                k4 = this.chunkZ + this.rand.nextInt(16) + 8;
                biomeVariant.boulderGen.generate(this.worldObj, this.rand, i4, this.worldObj.getHeightValue(i4, k4), k4);
            }
        }
    }

    private void generateOres() {
        float f;
        for (OreGenerant soil : this.biomeSoils) {
            this.genStandardOre(soil.oreChance, soil.oreGen, soil.minHeight, soil.maxHeight);
        }
        for (OreGenerant ore : this.biomeOres) {
            f = ore.oreChance * this.biomeOreFactor;
            this.genStandardOre(f, ore.oreGen, ore.minHeight, ore.maxHeight);
        }
        for (OreGenerant gem : this.biomeGems) {
            f = gem.oreChance * this.biomeGemFactor;
            this.genStandardOre(f, gem.oreGen, gem.minHeight, gem.maxHeight);
        }
    }

    private void genStandardOre(float ores, WorldGenerator oreGen, int minHeight, int maxHeight) {
        while (ores > 0.0f) {
            boolean generate = ores >= 1.0f ? true : this.rand.nextFloat() < ores;
            ores -= 1.0f;
            if (!generate) continue;
            int i = this.chunkX + this.rand.nextInt(16);
            int j = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)minHeight, (int)maxHeight);
            int k = this.chunkZ + this.rand.nextInt(16);
            oreGen.generate(this.worldObj, this.rand, i, j, k);
        }
    }

    private class RandomStructure {
        public WorldGenerator structureGen;
        public int chunkChance;

        public RandomStructure(WorldGenerator w, int i) {
            this.structureGen = w;
            this.chunkChance = i;
        }
    }

    private class OreGenerant {
        private WorldGenerator oreGen;
        private float oreChance;
        private int minHeight;
        private int maxHeight;

        public OreGenerant(WorldGenerator gen, float f, int min, int max) {
            this.oreGen = gen;
            this.oreChance = f;
            this.minHeight = min;
            this.maxHeight = max;
        }
    }

}

