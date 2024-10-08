/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class LOTRWorldGenNumenorRuin
extends LOTRWorldGenStructureBase2 {
    public LOTRWorldGenNumenorRuin(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int i1;
        int k1;
        int width = 3 + random.nextInt(3);
        this.setOriginAndRotation(world, i, j, k, rotation, width + 1);
        for (i1 = -width; i1 <= width; ++i1) {
            for (k1 = -width; k1 <= width; ++k1) {
                int j1;
                if (Math.abs(i1) == width || Math.abs(k1) == width) {
                    j1 = 0;
                    while (!this.isOpaque(world, i1, j1, k1) && this.getY(j1) >= 0) {
                        this.placeRandomBrick(world, random, i1, j1, k1);
                        this.setGrassToDirt(world, i1, j1 - 1, k1);
                        --j1;
                    }
                    continue;
                }
                this.setBlockAndMetadata(world, i1, 0, k1, (Block)Blocks.grass, 0);
                j1 = -1;
                while (!this.isOpaque(world, i1, j1, k1) && this.getY(j1) >= 0) {
                    this.setBlockAndMetadata(world, i1, j1, k1, Blocks.dirt, 0);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                    --j1;
                }
            }
        }
        if (random.nextBoolean()) {
            LOTRTreeType.OAK_LARGE.create(this.notifyChanges, random).generate(world, random, this.originX, this.originY + 1, this.originZ);
        } else {
            LOTRTreeType.BEECH_LARGE.create(this.notifyChanges, random).generate(world, random, this.originX, this.originY + 1, this.originZ);
        }
        for (i1 = -width; i1 <= width; ++i1) {
            for (k1 = -width; k1 <= width; ++k1) {
                if (Math.abs(i1) != width && Math.abs(k1) != width) continue;
                int height = width * 2 + random.nextInt(8);
                for (int j1 = 1; j1 < height; ++j1) {
                    this.placeRandomBrick(world, random, i1, j1, k1);
                }
            }
        }
        this.setAir(world, 0, 1, -width);
        this.setAir(world, 0, 2, -width);
        int ruins = 10 + random.nextInt(20);
        for (int l = 0; l < ruins; ++l) {
            int k12;
            int j1;
            int i12 = -width * 2 + random.nextInt(width * 2 + 1);
            Block block = this.getBlock(world, i12, (j1 = this.getTopBlock(world, i12, k12 = -width * 2 + random.nextInt(width * 2 + 1))) - 1, k12);
            if (block != Blocks.grass && block != Blocks.dirt && block != Blocks.stone) continue;
            int l1 = random.nextInt(3);
            if (l1 == 0) {
                this.setBlockAndMetadata(world, i12, j1 - 1, k12, Blocks.gravel, 0);
                continue;
            }
            if (l1 == 1) {
                this.placeRandomBrick(world, random, i12, j1 - 1, k12);
                continue;
            }
            if (l1 != 2) continue;
            int height = 1 + random.nextInt(3);
            for (int j2 = j1; j2 < j1 + height && !this.isOpaque(world, i12, j2, k12); ++j2) {
                this.placeRandomBrick(world, random, i12, j2, k12);
            }
        }
        return true;
    }

    private void placeRandomBrick(World world, Random random, int i, int j, int k) {
        int l = random.nextInt(5);
        if (l == 0) {
            this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 0);
        } else if (l == 1) {
            this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 1);
        } else if (l == 2) {
            this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 2);
        } else if (l == 3) {
            this.setBlockAndMetadata(world, i, j, k, Blocks.cobblestone, 0);
        } else if (l == 4) {
            this.setBlockAndMetadata(world, i, j, k, Blocks.mossy_cobblestone, 0);
        }
    }
}

