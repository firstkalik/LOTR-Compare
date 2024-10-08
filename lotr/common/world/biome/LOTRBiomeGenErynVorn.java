/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package lotr.common.world.biome;

import java.util.List;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenEriador;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenErynVorn
extends LOTRBiomeGenEriador {
    public LOTRBiomeGenErynVorn(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 8));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 10, 1, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFox.class, 4, 1, 4));
        this.clearBiomeVariants();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_FOREST);
        this.decorator.treesPerChunk = 10;
        this.decorator.flowersPerChunk = 4;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.doubleGrassPerChunk = 2;
        this.decorator.addTree(LOTRTreeType.PINE, 1000);
        this.decorator.addTree(LOTRTreeType.FIR, 200);
        this.decorator.addTree(LOTRTreeType.SPRUCE, 200);
        this.registerForestFlowers();
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.5f;
    }
}

