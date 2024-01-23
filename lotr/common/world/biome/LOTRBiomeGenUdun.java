/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import lotr.common.world.structure2.LOTRWorldGenMordorCamp;
import lotr.common.world.structure2.LOTRWorldGenMordorWargPit;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenUdun
extends LOTRBiomeGenMordor {
    public LOTRBiomeGenUdun(int i, boolean major) {
        super(i, major);
        this.biomeColors.setSky(6837327);
        this.biomeColors.setClouds(4797229);
        this.biomeColors.setFog(4996410);
        this.decorator.addRandomStructure(new LOTRWorldGenMordorCamp(false), 20);
        this.decorator.addRandomStructure(new LOTRWorldGenMordorWargPit(false), 100);
    }
}

