/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenLamedon;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTRSpawnList;

public class LOTRBiomeGenLamedonHills
extends LOTRBiomeGenLamedon {
    public LOTRBiomeGenLamedonHills(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.LAMEDON_HILLMEN, 10).setSpawnChance(50), LOTRBiomeSpawnList.entry(LOTRSpawnList.LAMEDON_SOLDIERS, 3).setConquestOnly()};
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_HAI, 10), LOTRBiomeSpawnList.entry(LOTRSpawnList.URUK_WARGS, 2).setConquestThreshold(50.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 20), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_BOMBARDIERS, 1), LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 4).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 1).setConquestThreshold(50.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 2).setConquestThreshold(100.0f), LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 1).setConquestThreshold(200.0f)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[]{LOTRBiomeSpawnList.entry(LOTRSpawnList.HALF_TROLLS, 10)};
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        this.clearBiomeVariants();
        this.variantChance = 0.5f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_MOUNTAINS);
        this.decorator.treesPerChunk = 1;
        this.biomeColors.resetGrass();
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    public int spawnCountMultiplier() {
        return super.spawnCountMultiplier() * 2;
    }
}

