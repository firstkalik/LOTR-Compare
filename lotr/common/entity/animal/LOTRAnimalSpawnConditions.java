/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.entity.animal;

import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.variant.LOTRBiomeVariant;

public interface LOTRAnimalSpawnConditions {
    public boolean canWorldGenSpawnAt(int var1, int var2, int var3, LOTRBiome var4, LOTRBiomeVariant var5);
}

