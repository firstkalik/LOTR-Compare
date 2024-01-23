/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 */
package lotr.common.world.biome.variant;

import java.util.Random;
import lotr.common.util.LOTRFunctions;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class LOTRBiomeVariantDunes
extends LOTRBiomeVariant {
    private static NoiseGeneratorPerlin duneWaveNoise = new NoiseGeneratorPerlin(new Random(305620668206968L), 1);
    private static NoiseGeneratorPerlin duneHeightNoise = new NoiseGeneratorPerlin(new Random(5729475867259682L), 1);

    public LOTRBiomeVariantDunes(int i, String s) {
        super(i, s, LOTRBiomeVariant.VariantScale.ALL);
        this.setTemperatureRainfall(0.1f, -0.1f);
    }

    @Override
    public float getHeightBoostAt(int i, int k) {
        return (float)this.getDuneHeightAt(i, k) / 22.0f;
    }

    private int getDuneHeightAt(int i, int k) {
        double d1 = duneWaveNoise.func_151601_a((double)i * 0.02, (double)k * 0.02);
        double d2 = duneWaveNoise.func_151601_a((double)i * 0.7, (double)k * 0.7);
        double d3 = d1 * 0.9 + d2 * 0.1;
        d3 = MathHelper.clamp_double((double)d3, (double)-1.0, (double)1.0);
        int maxDuneHeight = 12;
        int duneHeight = Math.round(LOTRFunctions.normalisedSin(((float)i + (float)(d3 *= 15.0)) * 0.09f) * (float)maxDuneHeight);
        return duneHeight;
    }
}

