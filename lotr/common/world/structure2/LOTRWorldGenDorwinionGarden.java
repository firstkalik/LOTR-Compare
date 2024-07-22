/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenDorwinionGarden
extends LOTRWorldGenStructureBase2 {
    public LOTRWorldGenDorwinionGarden(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        this.setOriginAndRotation(world, i, j, k, rotation, 7);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            int maxEdgeHeight = 0;
            for (int i1 = -7; i1 <= 7; ++i1) {
                for (int k1 = -7; k1 <= 7; ++k1) {
                    int j1 = this.getTopBlock(world, i1, k1);
                    Block block = this.getBlock(world, i1, j1 - 1, k1);
                    if (block != Blocks.grass) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight > 4) {
                        return false;
                    }
                    if (Math.abs(i1) != 7 && Math.abs(k1) != 7 || j1 <= maxEdgeHeight) continue;
                    maxEdgeHeight = j1;
                }
            }
            this.originY = this.getY(maxEdgeHeight);
        }
        int r = 25;
        int h = 4;
        int gardenR = 6;
        int leafR = 7;
        for (int i1 = -r; i1 <= r; ++i1) {
            for (int k1 = -r; k1 <= r; ++k1) {
                int j1;
                int j12;
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k1);
                boolean within = false;
                for (j12 = 0; j12 >= -h; --j12) {
                    int j2 = j12 + r - 2;
                    int d = i2 * i2 + j2 * j2 + k2 * k2;
                    if (d >= r * r) continue;
                    boolean grass = !this.isOpaque(world, i1, j12 + 1, k1);
                    this.setBlockAndMetadata(world, i1, j12, k1, (Block)(grass ? Blocks.grass : Blocks.dirt), 0);
                    this.setGrassToDirt(world, i1, j12 - 1, k1);
                    within = true;
                }
                if (!within) continue;
                j12 = -h - 1;
                while (!this.isOpaque(world, i1, j12, k1) && this.getY(j12) >= 0) {
                    this.setBlockAndMetadata(world, i1, j12, k1, Blocks.dirt, 0);
                    this.setGrassToDirt(world, i1, j12 - 1, k1);
                    --j12;
                }
                int dh = i2 * i2 + k2 * k2;
                if (dh < gardenR * gardenR) {
                    this.setBlockAndMetadata(world, i1, 0, k1, LOTRMod.brick5, 2);
                } else if (dh < leafR * leafR) {
                    this.setBlockAndMetadata(world, i1, 1, k1, LOTRMod.leaves6, 6);
                } else if (random.nextInt(5) == 0) {
                    j1 = this.getTopBlock(world, i1, k1);
                    this.plantFlower(world, random, i1, j1, k1);
                }
                if (i2 == 6 && k2 == 6) {
                    this.setGrassToDirt(world, i1, 0, k1);
                    this.setBlockAndMetadata(world, i1, 1, k1, LOTRMod.brick5, 2);
                    this.setBlockAndMetadata(world, i1, 2, k1, LOTRMod.brick5, 2);
                    this.setBlockAndMetadata(world, i1, 3, k1, LOTRMod.wall3, 10);
                    this.setBlockAndMetadata(world, i1, 4, k1, Blocks.torch, 5);
                }
                if (i2 == gardenR && k2 <= 1 || k2 == gardenR && i2 <= 1) {
                    this.setBlockAndMetadata(world, i1, 0, k1, Blocks.stained_hardened_clay, 0);
                    this.setAir(world, i1, 1, k1);
                }
                if (i2 == gardenR - 1 && k2 == 2 || k2 == gardenR - 1 && i2 == 2) {
                    this.setGrassToDirt(world, i1, 0, k1);
                    this.setBlockAndMetadata(world, i1, 1, k1, LOTRMod.brick5, 2);
                    this.setBlockAndMetadata(world, i1, 2, k1, LOTRMod.slabSingle9, 7);
                }
                if (i2 == 3 && k2 <= 2 || k2 == 3 && i2 <= 2) {
                    this.setBlockAndMetadata(world, i1, 0, k1, Blocks.stained_hardened_clay, 0);
                }
                if (i2 == 2 && k2 == 2) {
                    this.setBlockAndMetadata(world, i1, 1, k1, LOTRMod.slabSingle9, 7);
                }
                if (i2 == 2 && k2 <= 1 || k2 == 2 && i2 <= 1) {
                    for (j1 = -1; j1 <= 1; ++j1) {
                        this.setBlockAndMetadata(world, i1, j1, k1, LOTRMod.brick5, 2);
                    }
                }
                if (i2 > 1 || k2 > 1) continue;
                this.setBlockAndMetadata(world, i1, -2, k1, LOTRMod.brick5, 2);
                for (j1 = -1; j1 <= 1; ++j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, Blocks.water, 0);
                }
            }
        }
        return true;
    }
}

