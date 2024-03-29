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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenSand
extends WorldGenerator {
    private Block sandBlock;
    private int radius;
    private int heightRadius;

    public LOTRWorldGenSand(Block b, int r, int hr) {
        this.sandBlock = b;
        this.radius = r;
        this.heightRadius = hr;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        boolean mid = false;
        boolean adj = false;
        int waterCheck = 1;
        for (int i1 = -waterCheck; i1 <= waterCheck; ++i1) {
            for (int k1 = -waterCheck; k1 <= waterCheck; ++k1) {
                int i2 = i + i1;
                int k2 = k + k1;
                if (world.getBlock(i2, j, k2).getMaterial() != Material.water) continue;
                if (i1 == 0 && k1 == 0) {
                    mid = true;
                    continue;
                }
                adj = true;
            }
        }
        if (!mid || !adj) {
            return false;
        }
        BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
        int r = random.nextInt(this.radius - 2) + 2;
        int hr = this.heightRadius;
        for (int i1 = i - r; i1 <= i + r; ++i1) {
            for (int k1 = k - r; k1 <= k + r; ++k1) {
                int i2 = i1 - i;
                int k2 = k1 - k;
                if (i2 * i2 + k2 * k2 > r * r) continue;
                for (int j1 = j - hr; j1 <= j + hr; ++j1) {
                    Block block = world.getBlock(i1, j1, k1);
                    if (block != biome.topBlock && block != biome.fillerBlock || random.nextInt(3) == 0) continue;
                    world.setBlock(i1, j1, k1, this.sandBlock, 0, 2);
                }
            }
        }
        return true;
    }
}

