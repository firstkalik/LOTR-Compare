/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import lotr.common.LOTRAchievement;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenLoneLands;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenRuinedDunedainTower;
import lotr.common.world.structure2.LOTRWorldGenBurntHouse;
import lotr.common.world.structure2.LOTRWorldGenRangerCamp;
import lotr.common.world.structure2.LOTRWorldGenRangerWatchtower;
import lotr.common.world.structure2.LOTRWorldGenRottenHouse;
import lotr.common.world.structure2.LOTRWorldGenRuinedHouse;
import lotr.common.world.structure2.LOTRWorldGenSmallStoneRuin;
import lotr.common.world.structure2.LOTRWorldGenStoneRuin;
import lotr.common.world.village.LOTRVillageGen;
import lotr.common.world.village.LOTRVillageGenDunedain;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenAngle
extends LOTRBiomeGenLoneLands {
    public LOTRBiomeGenAngle(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_NORTH, 10).setSpawnChance(200)};
        this.npcSpawnList.newFactionList(500, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_NORTH, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 1).setConquestThreshold(100.0f)};
        this.npcSpawnList.newFactionList(1).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_ORCS, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_WARGS, 2), LOTRBiomeSpawnList.entry(LOTRSpawnList.ANGMAR_HILLMEN, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        this.clearBiomeVariants();
        this.variantChance = 0.3f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_SCRUBLAND);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST, 1.0f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT, 1.0f);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST, 1.0f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LARCH, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_PINE, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_ASPEN, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_MAPLE, 0.2f);
        this.decorator.addTree(LOTRTreeType.OAK_SHRUB, 800);
        this.registerPlainsFlowers();
        this.decorator.clearVillages();
        this.decorator.addVillage(new LOTRVillageGenDunedain(this, 0.7f));
        this.decorator.clearRandomStructures();
        this.decorator.addRandomStructure(new LOTRWorldGenRangerCamp(false), 500);
        this.decorator.addRandomStructure(new LOTRWorldGenRangerWatchtower(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedDunedainTower(false), 800);
        this.decorator.addRandomStructure(new LOTRWorldGenRuinedHouse(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenBurntHouse(false), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenRottenHouse(false), 4000);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.STONE(1, 4), 400);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.ARNOR(1, 4), 400);
        this.decorator.addRandomStructure(new LOTRWorldGenSmallStoneRuin(false), 400);
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.invasionSpawns.clearInvasions();
        this.invasionSpawns.addInvasion(LOTRInvasions.RANGER_NORTH, LOTREventSpawner.EventChance.COMMON);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD_WARG, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.ANGMAR_HILLMEN, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterAngle;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.ERIADOR.getSubregion("angle");
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.5f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.2f;
    }
}

