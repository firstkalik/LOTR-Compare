/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenUnderwaterElvenRuin
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenUnderwaterElvenRuin(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        if (this.restrictions && world.getBlock(i, j, k).getMaterial() != Material.water) {
            return false;
        }
        --j;
        int width = 3 + random.nextInt(3);
        int rotation = random.nextInt(4);
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
        }
        switch (rotation) {
            case 0: {
                k += width + 1;
                break;
            }
            case 1: {
                i -= width + 1;
                break;
            }
            case 2: {
                k -= width + 1;
                break;
            }
            case 3: {
                i += width + 1;
            }
        }
        if (this.restrictions) {
            int minHeight = j + 1;
            int maxHeight = j + 1;
            for (int i1 = i - width; i1 <= i + width; ++i1) {
                for (int k1 = k - width; k1 <= k + width; ++k1) {
                    j1 = world.getTopSolidOrLiquidBlock(i1, k1);
                    if (world.getBlock(i1, j1, k1).getMaterial() != Material.water) {
                        return false;
                    }
                    Block block = world.getBlock(i1, j1 - 1, k1);
                    if (block != Blocks.dirt && block != Blocks.sand && block != Blocks.clay) {
                        return false;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (j1 >= minHeight) continue;
                    minHeight = j1;
                }
            }
            if (Math.abs(maxHeight - minHeight) > 5) {
                return false;
            }
        }
        for (int i1 = i - width - 3; i1 <= i + width + 3; ++i1) {
            for (int k1 = k - width - 3; k1 <= k + width + 3; ++k1) {
                int i2 = Math.abs(i1 - i);
                int k2 = Math.abs(k1 - k);
                if ((i2 > width || k2 > width) && random.nextInt(Math.max(1, i2 + k2)) != 0) continue;
                j1 = world.getTopSolidOrLiquidBlock(i1, k1);
                this.placeRandomBrick(world, random, i1, j1, k1);
                this.setGrassToDirt(world, i1, j1 - 1, k1);
                if (random.nextInt(5) == 0) {
                    this.placeRandomBrick(world, random, i1, j1 + 1, k1);
                    if (random.nextInt(4) == 0) {
                        this.placeRandomBrick(world, random, i1, j1 + 2, k1);
                        if (random.nextInt(3) == 0) {
                            this.placeRandomBrick(world, random, i1, j1 + 3, k1);
                        }
                    }
                }
                if ((i2 != width || k2 != width) && random.nextInt(20) != 0) continue;
                int height = 2 + random.nextInt(4);
                for (int j2 = j1; j2 < j1 + height; ++j2) {
                    this.placeRandomPillar(world, random, i1, j2, k1);
                }
            }
        }
        int j12 = world.getTopSolidOrLiquidBlock(i, k);
        this.setBlockAndNotifyAdequately(world, i, j12, k, LOTRMod.seaLantern, 0);
        this.setBlockAndNotifyAdequately(world, i, j12 + 1, k, LOTRMod.chestStone, 0);
        LOTRChestContents.fillChest(world, random, i, j12 + 1, k, LOTRChestContents.UNDERWATER_ELVEN_RUIN);
        return true;
    }

    private void placeRandomBrick(World world, Random random, int i, int j, int k) {
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
        if (random.nextInt(3) == 0) {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.pillar, 11);
        } else {
            this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.pillar, 10);
        }
    }
}

