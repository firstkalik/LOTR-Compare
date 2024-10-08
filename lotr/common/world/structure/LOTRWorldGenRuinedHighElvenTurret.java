/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenRuinedHighElvenTurret
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenRuinedHighElvenTurret(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        block47: {
            int k1;
            int rotation;
            int j12;
            block49: {
                int i1;
                block48: {
                    block46: {
                        Block block;
                        int k12;
                        int j13;
                        if (this.restrictions && (block = world.getBlock(i, j - 1, k)) != Blocks.grass && block != Blocks.dirt && block != Blocks.stone) {
                            return false;
                        }
                        --j;
                        rotation = random.nextInt(4);
                        if (!this.restrictions && this.usingPlayer != null) {
                            rotation = this.usingPlayerRotation();
                        }
                        switch (rotation) {
                            case 0: {
                                k += 6;
                                break;
                            }
                            case 1: {
                                i -= 6;
                                break;
                            }
                            case 2: {
                                k -= 6;
                                break;
                            }
                            case 3: {
                                i += 6;
                            }
                        }
                        for (i1 = i - 4; i1 <= i + 4; ++i1) {
                            for (k12 = k - 4; k12 <= k + 4; ++k12) {
                                for (j13 = j; !(j13 != j && LOTRMod.isOpaque(world, i1, j13, k12) || j13 < 0); --j13) {
                                    this.placeRandomBrick(world, random, i1, j13, k12);
                                    this.setGrassToDirt(world, i1, j13 - 1, k12);
                                }
                                for (j13 = j + 1; j13 <= j + 7; ++j13) {
                                    if (Math.abs(i1 - i) == 4 || Math.abs(k12 - k) == 4) {
                                        this.placeRandomBrick(world, random, i1, j13, k12);
                                        continue;
                                    }
                                    this.setBlockAndNotifyAdequately(world, i1, j13, k12, Blocks.air, 0);
                                }
                            }
                        }
                        for (i1 = i - 3; i1 <= i + 3; ++i1) {
                            for (k12 = k - 3; k12 <= k + 3; ++k12) {
                                if (Math.abs(i1 - i) % 2 == Math.abs(k12 - k) % 2) {
                                    this.placeRandomPillar(world, random, i1, j, k12);
                                    continue;
                                }
                                this.setBlockAndNotifyAdequately(world, i1, j, k12, (Block)Blocks.double_stone_slab, 0);
                            }
                        }
                        for (j12 = j + 1; j12 <= j + 7; ++j12) {
                            this.placeRandomPillar(world, random, i - 3, j12, k - 3);
                            this.placeRandomPillar(world, random, i - 3, j12, k + 3);
                            this.placeRandomPillar(world, random, i + 3, j12, k - 3);
                            this.placeRandomPillar(world, random, i + 3, j12, k + 3);
                        }
                        for (i1 = i - 4; i1 <= i + 4; ++i1) {
                            this.placeRandomStairs(world, random, i1, j + 7, k - 4, 2);
                            this.placeRandomStairs(world, random, i1, j + 7, k + 4, 3);
                        }
                        for (k1 = k - 3; k1 <= k + 3; ++k1) {
                            this.placeRandomStairs(world, random, i - 4, j + 7, k1, 0);
                            this.placeRandomStairs(world, random, i + 4, j + 7, k1, 1);
                        }
                        for (i1 = i - 3; i1 <= i + 3; ++i1) {
                            for (k12 = k - 3; k12 <= k + 3; ++k12) {
                                for (j13 = j + 7; j13 <= j + 15; ++j13) {
                                    if (Math.abs(i1 - i) == 3 || Math.abs(k12 - k) == 3) {
                                        if (j13 - j >= 10 && j13 - j <= 14 && Math.abs(i1 - i) >= 3 && Math.abs(k12 - k) >= 3) continue;
                                        this.placeRandomBrick(world, random, i1, j13, k12);
                                        continue;
                                    }
                                    this.setBlockAndNotifyAdequately(world, i1, j13, k12, Blocks.air, 0);
                                }
                            }
                        }
                        for (i1 = i - 4; i1 <= i + 4; ++i1) {
                            for (k12 = k - 4; k12 <= k + 4; ++k12) {
                                for (j13 = j + 16; j13 <= j + 18; ++j13) {
                                    if (j13 - j == 16 || Math.abs(i1 - i) == 4 || Math.abs(k12 - k) == 4) {
                                        if (j13 - j == 18 && (Math.abs(i1 - i) % 2 == 1 || Math.abs(k12 - k) % 2 == 1)) {
                                            this.setBlockAndNotifyAdequately(world, i1, j13, k12, Blocks.air, 0);
                                            continue;
                                        }
                                        this.placeRandomBrick(world, random, i1, j13, k12);
                                        continue;
                                    }
                                    this.setBlockAndNotifyAdequately(world, i1, j13, k12, Blocks.air, 0);
                                }
                            }
                        }
                        for (i1 = i - 4; i1 <= i + 4; ++i1) {
                            this.placeRandomStairs(world, random, i1, j + 16, k - 4, 6);
                            this.placeRandomStairs(world, random, i1, j + 16, k + 4, 7);
                        }
                        for (k1 = k - 3; k1 <= k + 3; ++k1) {
                            this.placeRandomStairs(world, random, i - 4, j + 16, k1, 4);
                            this.placeRandomStairs(world, random, i + 4, j + 16, k1, 5);
                        }
                        if (rotation != 0) break block46;
                        for (i1 = i - 1; i1 <= i + 1; ++i1) {
                            this.setBlockAndNotifyAdequately(world, i1, j, k - 5, (Block)Blocks.double_stone_slab, 0);
                            this.setBlockAndNotifyAdequately(world, i1, j, k - 4, (Block)Blocks.double_stone_slab, 0);
                        }
                        for (j12 = j + 1; j12 <= j + 2; ++j12) {
                            this.placeRandomBrick(world, random, i - 1, j12, k - 5);
                            this.setBlockAndNotifyAdequately(world, i, j12, k - 5, Blocks.air, 0);
                            this.setBlockAndNotifyAdequately(world, i, j12, k - 4, Blocks.air, 0);
                            this.placeRandomBrick(world, random, i + 1, j12, k - 5);
                        }
                        this.placeRandomStairs(world, random, i - 1, j + 3, k - 5, 0);
                        this.placeRandomBrick(world, random, i, j + 3, k - 5);
                        this.placeRandomStairs(world, random, i + 1, j + 3, k - 5, 1);
                        for (i1 = i + 1; i1 <= i + 2; ++i1) {
                            for (int j1 = j + 1; j1 <= j + 7; ++j1) {
                                this.placeRandomBrick(world, random, i1, j1, k + 3);
                            }
                        }
                        break block47;
                    }
                    if (rotation != 1) break block48;
                    for (k1 = k - 1; k1 <= k + 1; ++k1) {
                        this.setBlockAndNotifyAdequately(world, i + 5, j, k1, (Block)Blocks.double_stone_slab, 0);
                        this.setBlockAndNotifyAdequately(world, i + 4, j, k1, (Block)Blocks.double_stone_slab, 0);
                    }
                    for (j12 = j + 1; j12 <= j + 2; ++j12) {
                        this.placeRandomBrick(world, random, i + 5, j12, k - 1);
                        this.setBlockAndNotifyAdequately(world, i + 5, j12, k, Blocks.air, 0);
                        this.setBlockAndNotifyAdequately(world, i + 4, j12, k, Blocks.air, 0);
                        this.placeRandomBrick(world, random, i + 5, j12, k + 1);
                    }
                    this.placeRandomStairs(world, random, i + 5, j + 3, k - 1, 2);
                    this.placeRandomBrick(world, random, i + 5, j + 3, k);
                    this.placeRandomStairs(world, random, i + 5, j + 3, k + 1, 3);
                    for (k1 = k - 1; k1 >= k - 2; --k1) {
                        for (int j1 = j + 1; j1 <= j + 7; ++j1) {
                            this.placeRandomBrick(world, random, i - 3, j1, k1);
                        }
                    }
                    break block47;
                }
                if (rotation != 2) break block49;
                for (i1 = i - 1; i1 <= i + 1; ++i1) {
                    this.setBlockAndNotifyAdequately(world, i1, j, k + 5, (Block)Blocks.double_stone_slab, 0);
                    this.setBlockAndNotifyAdequately(world, i1, j, k + 4, (Block)Blocks.double_stone_slab, 0);
                }
                for (j12 = j + 1; j12 <= j + 2; ++j12) {
                    this.placeRandomBrick(world, random, i - 1, j12, k + 5);
                    this.setBlockAndNotifyAdequately(world, i, j12, k + 5, Blocks.air, 0);
                    this.setBlockAndNotifyAdequately(world, i, j12, k + 4, Blocks.air, 0);
                    this.placeRandomBrick(world, random, i + 1, j12, k + 5);
                }
                this.placeRandomStairs(world, random, i - 1, j + 3, k + 5, 0);
                this.placeRandomBrick(world, random, i, j + 3, k + 5);
                this.placeRandomStairs(world, random, i + 1, j + 3, k + 5, 1);
                for (i1 = i - 1; i1 >= i - 2; --i1) {
                    for (int j1 = j + 1; j1 <= j + 7; ++j1) {
                        this.placeRandomBrick(world, random, i1, j1, k - 3);
                    }
                }
                break block47;
            }
            if (rotation != 3) break block47;
            for (k1 = k - 1; k1 <= k + 1; ++k1) {
                this.setBlockAndNotifyAdequately(world, i - 5, j, k1, (Block)Blocks.double_stone_slab, 0);
                this.setBlockAndNotifyAdequately(world, i - 4, j, k1, (Block)Blocks.double_stone_slab, 0);
            }
            for (j12 = j + 1; j12 <= j + 2; ++j12) {
                this.placeRandomBrick(world, random, i - 5, j12, k - 1);
                this.setBlockAndNotifyAdequately(world, i - 5, j12, k, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i - 4, j12, k, Blocks.air, 0);
                this.placeRandomBrick(world, random, i - 5, j12, k + 1);
            }
            this.placeRandomStairs(world, random, i - 5, j + 3, k - 1, 2);
            this.placeRandomBrick(world, random, i - 5, j + 3, k);
            this.placeRandomStairs(world, random, i - 5, j + 3, k + 1, 3);
            for (k1 = k + 1; k1 <= k + 2; ++k1) {
                for (int j1 = j + 1; j1 <= j + 7; ++j1) {
                    this.placeRandomBrick(world, random, i + 3, j1, k1);
                }
            }
        }
        return true;
    }

    private void placeRandomBrick(World world, Random random, int i, int j, int k) {
        if (random.nextInt(20) == 0) {
            return;
        }
        int l = random.nextInt(3);
        switch (l) {
            case 0: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick3, 2);
                break;
            }
            case 1: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick3, 3);
                break;
            }
            case 2: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.brick3, 4);
            }
        }
    }

    private void placeRandomPillar(World world, Random random, int i, int j, int k) {
        if (random.nextInt(8) == 0) {
            return;
        }
        if (random.nextInt(3) == 0) {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.pillar, 11);
        } else {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.pillar, 10);
        }
    }

    private void placeRandomStairs(World world, Random random, int i, int j, int k, int meta) {
        if (random.nextInt(8) == 0) {
            return;
        }
        int l = random.nextInt(3);
        switch (l) {
            case 0: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.stairsHighElvenBrick, meta);
                break;
            }
            case 1: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.stairsHighElvenBrickMossy, meta);
                break;
            }
            case 2: {
                this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.stairsHighElvenBrickCracked, meta);
            }
        }
    }
}

