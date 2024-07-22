/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenBlueMountains;
import lotr.common.world.biome.LOTRBiomeGenDurmethMountains;
import lotr.common.world.biome.LOTRBiomeGenGondor;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsBlacklock;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsIronfist;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStiffbeard;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStonefoot;
import lotr.common.world.biome.LOTRBiomeGenRohan;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenStalactites
extends WorldGenerator {
    private Block stalactiteBlock;

    public LOTRWorldGenStalactites() {
        this(LOTRMod.stalactite);
    }

    public LOTRWorldGenStalactites(Block block) {
        this.stalactiteBlock = block;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 64; ++l) {
            int k1;
            int j1;
            int i1 = i - random.nextInt(8) + random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = j - random.nextInt(4) + random.nextInt(4), k1 = k - random.nextInt(8) + random.nextInt(8))) continue;
            Block above = world.getBlock(i1, j1 + 1, k1);
            Block below = world.getBlock(i1, j1 - 1, k1);
            LOTRBiome biome = (LOTRBiome)world.getBiomeGenForCoords(i1, k1);
            if (biome instanceof LOTRBiomeGenMordor) {
                this.stalactiteBlock = LOTRMod.stalactiteMordor;
            }
            if (biome instanceof LOTRBiomeGenGondor) {
                this.stalactiteBlock = LOTRMod.stalactiteGondor;
            }
            if (biome instanceof LOTRBiomeGenRohan) {
                this.stalactiteBlock = LOTRMod.stalactiteRohan;
            }
            if (biome instanceof LOTRBiomeGenDurmethMountains) {
                this.stalactiteBlock = LOTRMod.stalactiteSarnkaran;
            }
            if (biome instanceof LOTRBiomeGenRedMountainsIronfist) {
                this.stalactiteBlock = LOTRMod.stalactiteSarnkaran;
            }
            if (biome instanceof LOTRBiomeGenRedMountainsBlacklock) {
                this.stalactiteBlock = LOTRMod.stalactiteSarnkaran;
            }
            if (biome instanceof LOTRBiomeGenRedMountainsStiffbeard) {
                this.stalactiteBlock = LOTRMod.stalactiteSarnkaran;
            }
            if (biome instanceof LOTRBiomeGenRedMountainsStonefoot) {
                this.stalactiteBlock = LOTRMod.stalactiteSarnkaran;
            }
            if (biome instanceof LOTRBiomeGenBlueMountains) {
                this.stalactiteBlock = LOTRMod.stalactiteSarluin;
            }
            if (above.isOpaqueCube() && above.getMaterial() == Material.rock) {
                world.setBlock(i1, j1, k1, this.stalactiteBlock, 0, 2);
                continue;
            }
            if (!below.isOpaqueCube() || below.getMaterial() != Material.rock) continue;
            world.setBlock(i1, j1, k1, this.stalactiteBlock, 1, 2);
        }
        return true;
    }
}

