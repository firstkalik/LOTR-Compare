/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.util.WeightedRandom$Item
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  org.apache.commons.lang3.ArrayUtils
 */
package lotr.common.world.biome.variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.variant.LOTRBiomeVariantDeadForest;
import lotr.common.world.biome.variant.LOTRBiomeVariantDenseForest;
import lotr.common.world.biome.variant.LOTRBiomeVariantDunes;
import lotr.common.world.biome.variant.LOTRBiomeVariantForest;
import lotr.common.world.biome.variant.LOTRBiomeVariantOrchard;
import lotr.common.world.biome.variant.LOTRBiomeVariantScrubland;
import lotr.common.world.biome.variant.LOTRBiomeVariantWasteland;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.commons.lang3.ArrayUtils;

public class LOTRBiomeVariant {
    private static LOTRBiomeVariant[] allVariants = new LOTRBiomeVariant[256];
    public static LOTRBiomeVariant STANDARD = new LOTRBiomeVariant(0, "standard", VariantScale.ALL);
    public static LOTRBiomeVariant FLOWERS = new LOTRBiomeVariant(1, "flowers", VariantScale.SMALL).setFlowers(10.0f);
    public static LOTRBiomeVariant FOREST = new LOTRBiomeVariantForest(2, "forest");
    public static LOTRBiomeVariant FOREST_LIGHT = new LOTRBiomeVariant(3, "forest_light", VariantScale.ALL).setTemperatureRainfall(0.0f, 0.2f).setTrees(3.0f).setGrass(2.0f);
    public static LOTRBiomeVariant STEPPE = new LOTRBiomeVariant(4, "steppe", VariantScale.LARGE).setTemperatureRainfall(0.0f, -0.1f).setHeight(0.0f, 0.1f).setTrees(0.01f).setGrass(3.0f).setFlowers(0.8f);
    public static LOTRBiomeVariant STEPPE_BARREN = new LOTRBiomeVariant(5, "steppe_barren", VariantScale.LARGE).setTemperatureRainfall(0.1f, -0.2f).setHeight(0.0f, 0.1f).setTrees(0.01f).setGrass(0.2f).setFlowers(0.4f);
    public static LOTRBiomeVariant HILLS = new LOTRBiomeVariant(6, "hills", VariantScale.ALL).setTemperatureRainfall(-0.1f, -0.1f).setHeight(0.5f, 1.5f).setGrass(0.5f);
    public static LOTRBiomeVariant HILLS_FOREST = new LOTRBiomeVariant(7, "hills_forest", VariantScale.ALL).setTemperatureRainfall(-0.1f, 0.0f).setHeight(0.5f, 1.5f).setTrees(3.0f);
    public static LOTRBiomeVariant MOUNTAIN = new LOTRBiomeVariant(8, "mountain", VariantScale.ALL).setTemperatureRainfall(-0.1f, -0.2f).setHeight(1.2f, 3.0f).setFlowers(0.8f);
    public static LOTRBiomeVariant CLEARING = new LOTRBiomeVariant(9, "clearing", VariantScale.SMALL).setHeight(0.0f, 0.5f).setTrees(0.0f).setGrass(2.0f).setFlowers(3.0f);
    public static LOTRBiomeVariant DENSEFOREST_OAK = new LOTRBiomeVariantDenseForest(10, "denseForest_oak").addTreeTypes(0.5f, new Object[]{LOTRTreeType.OAK_LARGE, 600, LOTRTreeType.OAK_PARTY, 100});
    public static LOTRBiomeVariant DENSEFOREST_SPRUCE = new LOTRBiomeVariantDenseForest(11, "denseForest_spruce").addTreeTypes(0.5f, new Object[]{LOTRTreeType.SPRUCE_MEGA, 100});
    public static LOTRBiomeVariant DENSEFOREST_OAK_SPRUCE = new LOTRBiomeVariantDenseForest(12, "denseForest_oak_spruce").addTreeTypes(0.5f, new Object[]{LOTRTreeType.OAK_LARGE, 600, LOTRTreeType.OAK_PARTY, 200, LOTRTreeType.SPRUCE_MEGA, 200});
    public static LOTRBiomeVariant DEADFOREST_OAK = new LOTRBiomeVariantDeadForest(13, "deadForest_oak").addTreeTypes(0.5f, new Object[]{LOTRTreeType.OAK_DEAD, 100});
    public static LOTRBiomeVariant DEADFOREST_SPRUCE = new LOTRBiomeVariantDeadForest(14, "deadForest_spruce").addTreeTypes(0.5f, new Object[]{LOTRTreeType.SPRUCE_DEAD, 100});
    public static LOTRBiomeVariant DEADFOREST_OAK_SPRUCE = new LOTRBiomeVariantDeadForest(15, "deadForest_oak_spruce").addTreeTypes(0.5f, new Object[]{LOTRTreeType.OAK_DEAD, 100, LOTRTreeType.SPRUCE_DEAD, 100});
    public static LOTRBiomeVariant SHRUBLAND_OAK = new LOTRBiomeVariant(16, "shrubland_oak", VariantScale.ALL).setTemperatureRainfall(0.0f, 0.3f).setTrees(6.0f).addTreeTypes(0.7f, new Object[]{LOTRTreeType.OAK_SHRUB, 100});
    public static LOTRBiomeVariant DENSEFOREST_BIRCH = new LOTRBiomeVariantDenseForest(17, "denseForest_birch").addTreeTypes(0.5f, new Object[]{LOTRTreeType.BIRCH_LARGE, 600, LOTRTreeType.BIRCH_PARTY, 100});
    public static LOTRBiomeVariant SWAMP_LOWLAND = new LOTRBiomeVariant(18, "swampLowland", VariantScale.SMALL).setHeight(-0.12f, 0.2f).setTrees(0.5f).setGrass(5.0f).setMarsh();
    public static LOTRBiomeVariant SWAMP_UPLAND = new LOTRBiomeVariant(19, "swampUpland", VariantScale.SMALL).setHeight(0.12f, 1.0f).setTrees(6.0f).setGrass(5.0f);
    public static LOTRBiomeVariant SAVANNAH_BAOBAB = new LOTRBiomeVariant(20, "savannahBaobab", VariantScale.LARGE).setHeight(0.0f, 0.5f).setTemperatureRainfall(0.0f, 0.2f).setTrees(1.5f).setGrass(0.5f).addTreeTypes(0.6f, new Object[]{LOTRTreeType.BAOBAB, 100});
    public static LOTRBiomeVariant LAKE = new LOTRBiomeVariant(21, "lake", VariantScale.NONE).setAbsoluteHeight(-0.5f, 0.05f);
    public static LOTRBiomeVariant DENSEFOREST_LEBETHRON = new LOTRBiomeVariantDenseForest(22, "denseForest_lebethron").addTreeTypes(0.5f, new Object[]{LOTRTreeType.LEBETHRON_LARGE, 600, LOTRTreeType.LEBETHRON_PARTY, 100});
    public static LOTRBiomeVariant BOULDERS_RED = new LOTRBiomeVariant(23, "boulders_red", VariantScale.LARGE).setBoulders(new LOTRWorldGenBoulder(LOTRMod.redSandstone, 1, 1, 3), 2, 4);
    public static LOTRBiomeVariant BOULDERS_ROHAN = new LOTRBiomeVariant(24, "boulders_rohan", VariantScale.LARGE).setBoulders(new LOTRWorldGenBoulder(LOTRMod.rock, 2, 1, 3), 2, 4);
    public static LOTRBiomeVariant JUNGLE_DENSE = new LOTRBiomeVariant(25, "jungle_dense", VariantScale.LARGE).setTemperatureRainfall(0.1f, 0.1f).setTrees(2.0f).addTreeTypes(0.6f, new Object[]{LOTRTreeType.JUNGLE_FANGORN, 1000, LOTRTreeType.MAHOGANY_FANGORN, 500});
    public static LOTRBiomeVariant VINEYARD = new LOTRBiomeVariant(26, "vineyard", VariantScale.SMALL).setHeight(0.0f, 0.5f).setTrees(0.0f).setGrass(0.5f).setFlowers(0.0f).disableStructuresVillages();
    public static LOTRBiomeVariant FOREST_ASPEN = new LOTRBiomeVariantForest(27, "forest_aspen").addTreeTypes(0.8f, new Object[]{LOTRTreeType.ASPEN, 1000, LOTRTreeType.ASPEN_LARGE, 50});
    public static LOTRBiomeVariant FOREST_BIRCH = new LOTRBiomeVariantForest(28, "forest_birch").addTreeTypes(0.8f, new Object[]{LOTRTreeType.BIRCH, 1000, LOTRTreeType.BIRCH_LARGE, 150});
    public static LOTRBiomeVariant FOREST_BEECH = new LOTRBiomeVariantForest(29, "forest_beech").addTreeTypes(0.8f, new Object[]{LOTRTreeType.BEECH, 1000, LOTRTreeType.BEECH_LARGE, 150});
    public static LOTRBiomeVariant FOREST_MAPLE = new LOTRBiomeVariantForest(30, "forest_maple").addTreeTypes(0.8f, new Object[]{LOTRTreeType.MAPLE, 1000, LOTRTreeType.MAPLE_LARGE, 150});
    public static LOTRBiomeVariant FOREST_LARCH = new LOTRBiomeVariantForest(31, "forest_larch").addTreeTypes(0.8f, new Object[]{LOTRTreeType.LARCH, 1000});
    public static LOTRBiomeVariant FOREST_PINE = new LOTRBiomeVariantForest(32, "forest_pine").addTreeTypes(0.8f, new Object[]{LOTRTreeType.PINE, 1000});
    public static LOTRBiomeVariant ORCHARD_SHIRE = new LOTRBiomeVariantOrchard(33, "orchard_shire").addTreeTypes(1.0f, new Object[]{LOTRTreeType.APPLE, 100, LOTRTreeType.PEAR, 100, LOTRTreeType.CHERRY, 10});
    public static LOTRBiomeVariant ORCHARD_APPLE_PEAR = new LOTRBiomeVariantOrchard(34, "orchard_apple_pear").addTreeTypes(1.0f, new Object[]{LOTRTreeType.APPLE, 100, LOTRTreeType.PEAR, 100});
    public static LOTRBiomeVariant ORCHARD_ORANGE = new LOTRBiomeVariantOrchard(35, "orchard_orange").addTreeTypes(1.0f, new Object[]{LOTRTreeType.ORANGE, 100});
    public static LOTRBiomeVariant ORCHARD_LEMON = new LOTRBiomeVariantOrchard(36, "orchard_lemon").addTreeTypes(1.0f, new Object[]{LOTRTreeType.LEMON, 100});
    public static LOTRBiomeVariant ORCHARD_LIME = new LOTRBiomeVariantOrchard(37, "orchard_lime").addTreeTypes(1.0f, new Object[]{LOTRTreeType.LIME, 100});
    public static LOTRBiomeVariant ORCHARD_ALMOND = new LOTRBiomeVariantOrchard(38, "orchard_almond").addTreeTypes(1.0f, new Object[]{LOTRTreeType.ALMOND, 100});
    public static LOTRBiomeVariant ORCHARD_OLIVE = new LOTRBiomeVariantOrchard(39, "orchard_olive").addTreeTypes(1.0f, new Object[]{LOTRTreeType.OLIVE, 100});
    public static LOTRBiomeVariant ORCHARD_PLUM = new LOTRBiomeVariantOrchard(40, "orchard_plum").addTreeTypes(1.0f, new Object[]{LOTRTreeType.PLUM, 100});
    public static LOTRBiomeVariant RIVER = new LOTRBiomeVariant(41, "river", VariantScale.NONE).setAbsoluteHeight(-0.5f, 0.05f).setTemperatureRainfall(0.0f, 0.3f);
    public static LOTRBiomeVariant SCRUBLAND = new LOTRBiomeVariantScrubland(42, "scrubland", Blocks.stone).setHeight(0.0f, 0.8f);
    public static LOTRBiomeVariant HILLS_SCRUBLAND = new LOTRBiomeVariantScrubland(43, "hills_scrubland", Blocks.stone).setHeight(0.5f, 2.0f);
    public static LOTRBiomeVariant WASTELAND = new LOTRBiomeVariantWasteland(44, "wasteland", Blocks.stone).setHeight(0.0f, 0.5f);
    public static LOTRBiomeVariant ORCHARD_DATE = new LOTRBiomeVariantOrchard(45, "orchard_date").addTreeTypes(1.0f, new Object[]{LOTRTreeType.DATE_PALM, 100});
    public static LOTRBiomeVariant DENSEFOREST_DARK_OAK = new LOTRBiomeVariantDenseForest(46, "denseForest_darkOak").addTreeTypes(0.5f, new Object[]{LOTRTreeType.DARK_OAK, 600, LOTRTreeType.DARK_OAK_PARTY, 100});
    public static LOTRBiomeVariant ORCHARD_POMEGRANATE = new LOTRBiomeVariantOrchard(47, "orchard_pomegranate").addTreeTypes(1.0f, new Object[]{LOTRTreeType.POMEGRANATE, 100});
    public static LOTRBiomeVariant DUNES = new LOTRBiomeVariantDunes(48, "dunes");
    public static LOTRBiomeVariant SCRUBLAND_SAND = new LOTRBiomeVariantScrubland(49, "scrubland_sand", Blocks.sandstone).setHeight(0.0f, 0.8f);
    public static LOTRBiomeVariant HILLS_SCRUBLAND_SAND = new LOTRBiomeVariantScrubland(50, "hills_scrubland_sand", Blocks.sandstone).setHeight(0.5f, 2.0f);
    public static LOTRBiomeVariant WASTELAND_SAND = new LOTRBiomeVariantWasteland(51, "wasteland_sand", Blocks.sandstone).setHeight(0.0f, 0.5f);
    public static LOTRBiomeVariant[] SET_NORMAL = new LOTRBiomeVariant[]{FLOWERS, FOREST, FOREST_LIGHT, STEPPE, STEPPE_BARREN, HILLS, HILLS_FOREST};
    public static LOTRBiomeVariant[] SET_NORMAL_OAK = (LOTRBiomeVariant[])ArrayUtils.addAll((Object[])SET_NORMAL, (Object[])new LOTRBiomeVariant[]{DENSEFOREST_OAK, DEADFOREST_OAK, SHRUBLAND_OAK});
    public static LOTRBiomeVariant[] SET_NORMAL_SPRUCE = (LOTRBiomeVariant[])ArrayUtils.addAll((Object[])SET_NORMAL, (Object[])new LOTRBiomeVariant[]{DENSEFOREST_SPRUCE, DEADFOREST_SPRUCE});
    public static LOTRBiomeVariant[] SET_NORMAL_OAK_SPRUCE = (LOTRBiomeVariant[])ArrayUtils.addAll((Object[])SET_NORMAL, (Object[])new LOTRBiomeVariant[]{DENSEFOREST_OAK, DEADFOREST_OAK, SHRUBLAND_OAK, DENSEFOREST_SPRUCE, DEADFOREST_SPRUCE, DENSEFOREST_OAK_SPRUCE, DEADFOREST_OAK_SPRUCE});
    public static LOTRBiomeVariant[] SET_NORMAL_NOSTEPPE = (LOTRBiomeVariant[])ArrayUtils.removeElements((Object[])SET_NORMAL, (Object[])new LOTRBiomeVariant[]{STEPPE, STEPPE_BARREN});
    public static LOTRBiomeVariant[] SET_NORMAL_OAK_NOSTEPPE = (LOTRBiomeVariant[])ArrayUtils.removeElements((Object[])SET_NORMAL_OAK, (Object[])new LOTRBiomeVariant[]{STEPPE, STEPPE_BARREN});
    public static LOTRBiomeVariant[] SET_FOREST = new LOTRBiomeVariant[]{FLOWERS, HILLS, CLEARING};
    public static LOTRBiomeVariant[] SET_MOUNTAINS = new LOTRBiomeVariant[]{FOREST, FOREST_LIGHT};
    public static LOTRBiomeVariant[] SET_SWAMP = new LOTRBiomeVariant[]{SWAMP_LOWLAND, SWAMP_LOWLAND, SWAMP_LOWLAND, SWAMP_UPLAND};
    public static LOTRBiomeVariant VariantGenst = new LOTRBiomeVariant(52, "VariantGenst", VariantScale.LARGE).setTemperatureRainfall(0.0f, -0.1f).setHeight(0.0f, 0.1f).setTrees(0.01f).setGrass(3.0f).setFlowers(0.8f);
    public static NoiseGeneratorPerlin marshNoise = new NoiseGeneratorPerlin(new Random(444L), 1);
    public static NoiseGeneratorPerlin podzolNoise = new NoiseGeneratorPerlin(new Random(58052L), 1);
    public final int variantID;
    public final String variantName;
    public final VariantScale variantScale;
    public float tempBoost = 0.0f;
    public float rainBoost = 0.0f;
    public boolean absoluteHeight = false;
    public float absoluteHeightLevel = 0.0f;
    private float heightBoost = 0.0f;
    public float hillFactor = 1.0f;
    public float treeFactor = 1.0f;
    public float grassFactor = 1.0f;
    public float flowerFactor = 1.0f;
    public boolean hasMarsh = false;
    public boolean disableStructures = false;
    public boolean disableVillages = false;
    public List<LOTRTreeType.WeightedTreeType> treeTypes = new ArrayList<LOTRTreeType.WeightedTreeType>();
    public float variantTreeChance = 0.0f;
    public WorldGenerator boulderGen;
    public int boulderChance = 0;
    public int boulderMax = 1;

    public LOTRBiomeVariant(int i, String s, VariantScale scale) {
        if (allVariants[i] != null) {
            throw new IllegalArgumentException("LOTR Biome variant already exists at index " + i);
        }
        this.variantID = i;
        LOTRBiomeVariant.allVariants[i] = this;
        this.variantName = s;
        this.variantScale = scale;
    }

    public static LOTRBiomeVariant getVariantForID(int i) {
        LOTRBiomeVariant variant = allVariants[i];
        if (variant == null) {
            return STANDARD;
        }
        return variant;
    }

    protected LOTRBiomeVariant setTemperatureRainfall(float temp, float rain) {
        this.tempBoost = temp;
        this.rainBoost = rain;
        return this;
    }

    protected LOTRBiomeVariant setHeight(float height, float hills) {
        this.heightBoost = height;
        this.hillFactor = hills;
        return this;
    }

    protected LOTRBiomeVariant setAbsoluteHeight(float height, float hills) {
        this.absoluteHeight = true;
        this.absoluteHeightLevel = height;
        float f = height;
        f -= 2.0f;
        this.heightBoost = f += 0.2f;
        this.hillFactor = hills;
        return this;
    }

    public float getHeightBoostAt(int i, int k) {
        return this.heightBoost;
    }

    protected LOTRBiomeVariant setTrees(float f) {
        this.treeFactor = f;
        return this;
    }

    protected LOTRBiomeVariant setGrass(float f) {
        this.grassFactor = f;
        return this;
    }

    protected LOTRBiomeVariant setFlowers(float f) {
        this.flowerFactor = f;
        return this;
    }

    protected LOTRBiomeVariant addTreeTypes(float f, Object ... trees) {
        this.variantTreeChance = f;
        for (int i = 0; i < trees.length / 2; ++i) {
            Object obj1 = trees[i * 2];
            Object obj2 = trees[i * 2 + 1];
            this.treeTypes.add(new LOTRTreeType.WeightedTreeType((LOTRTreeType)((Object)obj1), (Integer)obj2));
        }
        return this;
    }

    public LOTRTreeType getRandomTree(Random random) {
        WeightedRandom.Item item = WeightedRandom.getRandomItem((Random)random, this.treeTypes);
        return ((LOTRTreeType.WeightedTreeType)item).treeType;
    }

    protected LOTRBiomeVariant setMarsh() {
        this.hasMarsh = true;
        return this;
    }

    protected LOTRBiomeVariant disableVillages() {
        this.disableVillages = true;
        return this;
    }

    protected LOTRBiomeVariant disableStructuresVillages() {
        this.disableStructures = true;
        this.disableVillages = true;
        return this;
    }

    protected LOTRBiomeVariant setBoulders(WorldGenerator boulder, int chance, int num) {
        if (num < 1) {
            throw new IllegalArgumentException("n must be > 1");
        }
        this.boulderGen = boulder;
        this.boulderChance = chance;
        this.boulderMax = num;
        return this;
    }

    public void generateVariantTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int height, LOTRBiome biome) {
    }

    public void decorateVariant(World world, Random random, int i, int k, LOTRBiome biome) {
    }

    public static enum VariantScale {
        LARGE,
        SMALL,
        ALL,
        NONE;

    }

}

