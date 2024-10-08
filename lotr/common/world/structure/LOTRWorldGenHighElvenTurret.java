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

public class LOTRWorldGenHighElvenTurret
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenHighElvenTurret(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int j13;
        Block block;
        int i1;
        int k1;
        int k12;
        if (this.restrictions && (block = world.getBlock(i, j - 1, k)) != Blocks.grass && block != Blocks.dirt && block != Blocks.stone) {
            return false;
        }
        --j;
        int rotation = random.nextInt(4);
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
                for (j1 = j; !(j1 != j && LOTRMod.isOpaque(world, i1, j1, k12) || j1 < 0); --j1) {
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, LOTRMod.brick3, 2);
                    this.setGrassToDirt(world, i1, j1 - 1, k12);
                }
                for (j1 = j + 1; j1 <= j + 7; ++j1) {
                    if (Math.abs(i1 - i) == 4 || Math.abs(k12 - k) == 4) {
                        this.setBlockAndNotifyAdequately(world, i1, j1, k12, LOTRMod.brick3, 2);
                        continue;
                    }
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, Blocks.air, 0);
                }
            }
        }
        for (i1 = i - 3; i1 <= i + 3; ++i1) {
            for (k12 = k - 3; k12 <= k + 3; ++k12) {
                if (Math.abs(i1 - i) % 2 == Math.abs(k12 - k) % 2) {
                    this.setBlockAndNotifyAdequately(world, i1, j, k12, LOTRMod.pillar, 10);
                    continue;
                }
                this.setBlockAndNotifyAdequately(world, i1, j, k12, (Block)Blocks.double_stone_slab, 0);
            }
        }
        for (j13 = j + 1; j13 <= j + 7; ++j13) {
            this.setBlockAndNotifyAdequately(world, i - 3, j13, k - 3, LOTRMod.pillar, 10);
            this.setBlockAndNotifyAdequately(world, i - 3, j13, k + 3, LOTRMod.pillar, 10);
            this.setBlockAndNotifyAdequately(world, i + 3, j13, k - 3, LOTRMod.pillar, 10);
            this.setBlockAndNotifyAdequately(world, i + 3, j13, k + 3, LOTRMod.pillar, 10);
        }
        for (i1 = i - 4; i1 <= i + 4; ++i1) {
            this.setBlockAndNotifyAdequately(world, i1, j + 7, k - 4, LOTRMod.stairsHighElvenBrick, 2);
            this.setBlockAndNotifyAdequately(world, i1, j + 7, k + 4, LOTRMod.stairsHighElvenBrick, 3);
        }
        for (k1 = k - 3; k1 <= k + 3; ++k1) {
            this.setBlockAndNotifyAdequately(world, i - 4, j + 7, k1, LOTRMod.stairsHighElvenBrick, 0);
            this.setBlockAndNotifyAdequately(world, i + 4, j + 7, k1, LOTRMod.stairsHighElvenBrick, 1);
        }
        for (i1 = i - 3; i1 <= i + 3; ++i1) {
            for (k12 = k - 3; k12 <= k + 3; ++k12) {
                for (j1 = j + 7; j1 <= j + 15; ++j1) {
                    if (Math.abs(i1 - i) == 3 || Math.abs(k12 - k) == 3) {
                        if (j1 - j >= 10 && j1 - j <= 14 && Math.abs(i1 - i) >= 3 && Math.abs(k12 - k) >= 3) continue;
                        this.setBlockAndNotifyAdequately(world, i1, j1, k12, LOTRMod.brick3, 2);
                        continue;
                    }
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, Blocks.air, 0);
                }
            }
        }
        for (i1 = i - 4; i1 <= i + 4; ++i1) {
            for (k12 = k - 4; k12 <= k + 4; ++k12) {
                for (j1 = j + 16; j1 <= j + 18; ++j1) {
                    if (j1 - j == 16 || Math.abs(i1 - i) == 4 || Math.abs(k12 - k) == 4) {
                        if (j1 - j == 18 && (Math.abs(i1 - i) % 2 == 1 || Math.abs(k12 - k) % 2 == 1)) {
                            this.setBlockAndNotifyAdequately(world, i1, j1, k12, Blocks.air, 0);
                            continue;
                        }
                        this.setBlockAndNotifyAdequately(world, i1, j1, k12, LOTRMod.brick3, 2);
                        continue;
                    }
                    this.setBlockAndNotifyAdequately(world, i1, j1, k12, Blocks.air, 0);
                }
            }
        }
        for (i1 = i - 4; i1 <= i + 4; ++i1) {
            this.setBlockAndNotifyAdequately(world, i1, j + 16, k - 4, LOTRMod.stairsHighElvenBrick, 6);
            this.setBlockAndNotifyAdequately(world, i1, j + 16, k + 4, LOTRMod.stairsHighElvenBrick, 7);
        }
        for (k1 = k - 3; k1 <= k + 3; ++k1) {
            this.setBlockAndNotifyAdequately(world, i - 4, j + 16, k1, LOTRMod.stairsHighElvenBrick, 4);
            this.setBlockAndNotifyAdequately(world, i + 4, j + 16, k1, LOTRMod.stairsHighElvenBrick, 5);
        }
        if (rotation == 0) {
            for (i1 = i - 1; i1 <= i + 1; ++i1) {
                this.setBlockAndNotifyAdequately(world, i1, j, k - 5, (Block)Blocks.double_stone_slab, 0);
                this.setBlockAndNotifyAdequately(world, i1, j, k - 4, (Block)Blocks.double_stone_slab, 0);
            }
            for (j13 = j + 1; j13 <= j + 2; ++j13) {
                this.setBlockAndNotifyAdequately(world, i - 1, j13, k - 5, LOTRMod.brick3, 2);
                this.setBlockAndNotifyAdequately(world, i, j13, k - 5, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i, j13, k - 4, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i + 1, j13, k - 5, LOTRMod.brick3, 2);
            }
            this.setBlockAndNotifyAdequately(world, i - 1, j + 3, k - 5, LOTRMod.stairsHighElvenBrick, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 3, k - 5, LOTRMod.brick3, 2);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 3, k - 5, LOTRMod.stairsHighElvenBrick, 1);
            for (i1 = i + 1; i1 <= i + 2; ++i1) {
                int j12;
                for (j12 = j + 1; j12 <= j + 7; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k + 3, LOTRMod.brick3, 2);
                }
                for (j12 = j + 1; j12 <= j + 16; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k + 2, Blocks.ladder, 2);
                }
            }
        } else if (rotation == 1) {
            for (k1 = k - 1; k1 <= k + 1; ++k1) {
                this.setBlockAndNotifyAdequately(world, i + 5, j, k1, (Block)Blocks.double_stone_slab, 0);
                this.setBlockAndNotifyAdequately(world, i + 4, j, k1, (Block)Blocks.double_stone_slab, 0);
            }
            for (j13 = j + 1; j13 <= j + 2; ++j13) {
                this.setBlockAndNotifyAdequately(world, i + 5, j13, k - 1, LOTRMod.brick3, 2);
                this.setBlockAndNotifyAdequately(world, i + 5, j13, k, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i + 4, j13, k, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i + 5, j13, k + 1, LOTRMod.brick3, 2);
            }
            this.setBlockAndNotifyAdequately(world, i + 5, j + 3, k - 1, LOTRMod.stairsHighElvenBrick, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 3, k, LOTRMod.brick3, 2);
            this.setBlockAndNotifyAdequately(world, i + 5, j + 3, k + 1, LOTRMod.stairsHighElvenBrick, 3);
            for (k1 = k - 1; k1 >= k - 2; --k1) {
                int j12;
                for (j12 = j + 1; j12 <= j + 7; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i - 3, j12, k1, LOTRMod.brick3, 2);
                }
                for (j12 = j + 1; j12 <= j + 16; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i - 2, j12, k1, Blocks.ladder, 5);
                }
            }
        } else if (rotation == 2) {
            for (i1 = i - 1; i1 <= i + 1; ++i1) {
                this.setBlockAndNotifyAdequately(world, i1, j, k + 5, (Block)Blocks.double_stone_slab, 0);
                this.setBlockAndNotifyAdequately(world, i1, j, k + 4, (Block)Blocks.double_stone_slab, 0);
            }
            for (j13 = j + 1; j13 <= j + 2; ++j13) {
                this.setBlockAndNotifyAdequately(world, i - 1, j13, k + 5, LOTRMod.brick3, 2);
                this.setBlockAndNotifyAdequately(world, i, j13, k + 5, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i, j13, k + 4, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i + 1, j13, k + 5, LOTRMod.brick3, 2);
            }
            this.setBlockAndNotifyAdequately(world, i - 1, j + 3, k + 5, LOTRMod.stairsHighElvenBrick, 0);
            this.setBlockAndNotifyAdequately(world, i, j + 3, k + 5, LOTRMod.brick3, 2);
            this.setBlockAndNotifyAdequately(world, i + 1, j + 3, k + 5, LOTRMod.stairsHighElvenBrick, 1);
            for (i1 = i - 1; i1 >= i - 2; --i1) {
                int j12;
                for (j12 = j + 1; j12 <= j + 7; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k - 3, LOTRMod.brick3, 2);
                }
                for (j12 = j + 1; j12 <= j + 16; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k - 2, Blocks.ladder, 3);
                }
            }
        } else if (rotation == 3) {
            for (k1 = k - 1; k1 <= k + 1; ++k1) {
                this.setBlockAndNotifyAdequately(world, i - 5, j, k1, (Block)Blocks.double_stone_slab, 0);
                this.setBlockAndNotifyAdequately(world, i - 4, j, k1, (Block)Blocks.double_stone_slab, 0);
            }
            for (j13 = j + 1; j13 <= j + 2; ++j13) {
                this.setBlockAndNotifyAdequately(world, i - 5, j13, k - 1, LOTRMod.brick3, 2);
                this.setBlockAndNotifyAdequately(world, i - 5, j13, k, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i - 4, j13, k, Blocks.air, 0);
                this.setBlockAndNotifyAdequately(world, i - 5, j13, k + 1, LOTRMod.brick3, 2);
            }
            this.setBlockAndNotifyAdequately(world, i - 5, j + 3, k - 1, LOTRMod.stairsHighElvenBrick, 2);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 3, k, LOTRMod.brick3, 2);
            this.setBlockAndNotifyAdequately(world, i - 5, j + 3, k + 1, LOTRMod.stairsHighElvenBrick, 3);
            for (k1 = k + 1; k1 <= k + 2; ++k1) {
                int j12;
                for (j12 = j + 1; j12 <= j + 7; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i + 3, j12, k1, LOTRMod.brick3, 2);
                }
                for (j12 = j + 1; j12 <= j + 16; ++j12) {
                    this.setBlockAndNotifyAdequately(world, i + 2, j12, k1, Blocks.ladder, 4);
                }
            }
        }
        this.setBlockAndNotifyAdequately(world, i - 3, j + 3, k, LOTRMod.highElvenTorch, 1);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 3, k, LOTRMod.highElvenTorch, 2);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k - 3, LOTRMod.highElvenTorch, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 3, k + 3, LOTRMod.highElvenTorch, 4);
        this.setBlockAndNotifyAdequately(world, i - 3, j + 18, k, LOTRMod.highElvenTorch, 1);
        this.setBlockAndNotifyAdequately(world, i + 3, j + 18, k, LOTRMod.highElvenTorch, 2);
        this.setBlockAndNotifyAdequately(world, i, j + 18, k - 3, LOTRMod.highElvenTorch, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 18, k + 3, LOTRMod.highElvenTorch, 4);
        return true;
    }
}

