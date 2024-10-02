/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.Direction
 *  net.minecraft.util.Facing
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenLichen
extends WorldGenerator {
    private void generateLichen(World world, Random random, int i, int j, int k) {
        int l = i;
        int i1 = k;
        while (j < 120) {
            if (world.isAirBlock(i, j, k)) {
                for (int j1 = 2; j1 <= 5; ++j1) {
                    if (!LOTRMod.lichen.canPlaceBlockOnSide(world, i, j, k, j1)) continue;
                    world.setBlock(i, j, k, LOTRMod.lichen, 1 << Direction.facingToDirection[Facing.oppositeSide[j1]], 2);
                    break;
                }
            } else {
                i = l + random.nextInt(4) - random.nextInt(4);
                k = i1 + random.nextInt(4) - random.nextInt(4);
            }
            ++j;
        }
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        LichenGeneratorThread generatorThread = new LichenGeneratorThread(world, random, i, j, k);
        generatorThread.start();
        return true;
    }

    private class LichenGeneratorThread
    extends Thread {
        private World world;
        private Random random;
        private int i;
        private int j;
        private int k;

        public LichenGeneratorThread(World world, Random random, int i, int j, int k) {
            this.world = world;
            this.random = random;
            this.i = i;
            this.j = j;
            this.k = k;
        }

        @Override
        public void run() {
            LOTRWorldGenLichen.this.generateLichen(this.world, this.random, this.i, this.j, this.k);
        }
    }

}

