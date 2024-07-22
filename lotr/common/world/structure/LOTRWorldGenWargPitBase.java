/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class LOTRWorldGenWargPitBase
extends LOTRWorldGenStructureBase {
    protected Block wallBlock;
    protected int wallMeta;
    protected Block groundBlock;
    protected int groundMeta;

    public LOTRWorldGenWargPitBase(boolean flag) {
        super(flag);
    }

    protected abstract boolean canGenerateAt(World var1, int var2, int var3, int var4);

    protected abstract LOTREntityNPC getOrc(World var1);

    protected abstract LOTREntityNPC getWarg(World var1);

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int j12;
        int i11;
        int k1;
        if (this.restrictions && !this.canGenerateAt(world, i, j, k)) {
            return false;
        }
        int rotation = random.nextInt(4);
        int radius = 8;
        int radiusPlusOne = radius + 1;
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
            switch (rotation) {
                case 0: {
                    k += radiusPlusOne;
                    break;
                }
                case 1: {
                    i -= radiusPlusOne;
                    break;
                }
                case 2: {
                    k -= radiusPlusOne;
                    break;
                }
                case 3: {
                    i += radiusPlusOne;
                }
            }
        }
        if (this.restrictions) {
            int minHeight = j;
            int maxHeight = j;
            for (int m = i - radiusPlusOne; m <= i + radiusPlusOne; ++m) {
                for (int k12 = k - radiusPlusOne; k12 <= k + radiusPlusOne; ++k12) {
                    int i2 = m - i;
                    int k2 = k12 - k;
                    if (i2 * i2 + k2 * k2 > radiusPlusOne * radiusPlusOne) continue;
                    int j13 = world.getHeightValue(m, k12);
                    if (!this.canGenerateAt(world, m, j13, k12)) {
                        return false;
                    }
                    if (j13 < minHeight) {
                        minHeight = j13;
                    }
                    if (j13 <= maxHeight) continue;
                    maxHeight = j13;
                }
            }
            if (maxHeight - minHeight > 5) {
                return false;
            }
            j = maxHeight;
        }
        int base = j - 8;
        int wallThresholdMin = (int)(((double)radius - 0.25) * ((double)radius - 0.25));
        int wallThresholdMax = radiusPlusOne * radiusPlusOne;
        for (int i1 = i - radiusPlusOne; i1 <= i + radiusPlusOne; ++i1) {
            for (int k13 = k - radiusPlusOne; k13 <= k + radiusPlusOne; ++k13) {
                int i2 = i1 - i;
                int k2 = k13 - k;
                int distSq = i2 * i2 + k2 * k2;
                if (distSq >= wallThresholdMax) continue;
                if (distSq >= wallThresholdMin) {
                    this.setBlockAndNotifyAdequately(world, i1, j + 3, k13, LOTRMod.fence, 3);
                    this.setBlockAndNotifyAdequately(world, i1, j + 2, k13, LOTRMod.planks, 3);
                    for (j1 = j + 1; j1 >= base && j1 >= 0; --j1) {
                        this.setBlockAndNotifyAdequately(world, i1, j1, k13, this.wallBlock, this.wallMeta);
                    }
                    continue;
                }
                for (j1 = j + 3; j1 >= base && j1 >= 0; --j1) {
                    if (j1 == base) {
                        this.setBlockAndNotifyAdequately(world, i1, j1, k13, this.groundBlock, this.groundMeta);
                        continue;
                    }
                    this.setBlockAndNotifyAdequately(world, i1, j1, k13, Blocks.air, 0);
                }
            }
        }
        int base1 = j - 3;
        int wallThresholdMin1 = (int)(((double)radius - 0.25) * ((double)radius - 0.25));
        int wallThresholdMax1 = radiusPlusOne * radiusPlusOne;
        for (i11 = i - radiusPlusOne; i11 <= i + radiusPlusOne; ++i11) {
            for (k1 = k - radiusPlusOne; k1 <= k + radiusPlusOne; ++k1) {
                int j14;
                int i2 = i11 - i;
                int k2 = k1 - k;
                int distSq = i2 * i2 + k2 * k2;
                if (distSq >= wallThresholdMax) continue;
                if (distSq >= wallThresholdMin) {
                    this.setBlockAndNotifyAdequately(world, i11, j + 2, k1, LOTRMod.planks, 3);
                    for (j14 = j + 1; j14 >= base && j14 >= 0; --j14) {
                        this.setBlockAndNotifyAdequately(world, i11, j14, k1, this.wallBlock, this.wallMeta);
                    }
                    continue;
                }
                for (j14 = j + 3; j14 >= base && j14 >= 0; --j14) {
                    if (j14 == base) {
                        this.setBlockAndNotifyAdequately(world, i11, j14, k1, this.groundBlock, this.groundMeta);
                        continue;
                    }
                    this.setBlockAndNotifyAdequately(world, i11, j14, k1, Blocks.air, 0);
                }
            }
        }
        this.placeFenceAndTorch(world, i - 1, j - 3, k - radius + 1);
        this.placeFenceAndTorch(world, i + 1, j - 3, k - radius + 1);
        this.placeFenceAndTorch(world, i - 1, j - 3, k + radius - 1);
        this.placeFenceAndTorch(world, i + 1, j - 3, k + radius - 1);
        this.placeFenceAndTorch(world, i - radius + 1, j - 3, k - 1);
        this.placeFenceAndTorch(world, i - radius + 1, j - 3, k + 1);
        this.placeFenceAndTorch(world, i + radius - 1, j - 3, k - 1);
        this.placeFenceAndTorch(world, i + radius - 1, j - 3, k + 1);
        if (rotation == 0) {
            k1 = k - radius;
            this.setBlockAndNotifyAdequately(world, i, j + 3, k1, LOTRMod.fenceGateCharred, 0);
            for (j12 = j + 2; !LOTRMod.isOpaque(world, i, j12, k1 - 1) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i, j12, k1 - 1, LOTRMod.burntLadder, 2);
            }
            for (j12 = j + 2; !LOTRMod.isOpaque(world, i, j12, k1 + 1) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i, j12, k1 + 1, LOTRMod.burntLadder, 3);
            }
            this.setBlockAndNotifyAdequately(world, i, base + 1, k + radius - 1, LOTRMod.chestStone, 0);
            LOTRChestContents.fillChest(world, random, i, base + 1, k + radius - 1, LOTRChestContents.WARG_PIT);
        }
        if (rotation == 1) {
            i11 = i + radius;
            this.setBlockAndNotifyAdequately(world, i11, j + 3, k, LOTRMod.fenceGateCharred, 1);
            for (j1 = j + 2; !LOTRMod.isOpaque(world, i11 - 1, j1, k) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i11 - 1, j1, k, LOTRMod.burntLadder, 4);
            }
            for (j1 = j + 2; !LOTRMod.isOpaque(world, i11 + 1, j1, k) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i11 + 1, j1, k, LOTRMod.burntLadder, 5);
            }
            this.setBlockAndNotifyAdequately(world, i - radius + 1, base + 1, k, LOTRMod.chestStone, 0);
            LOTRChestContents.fillChest(world, random, i - radius + 1, base + 1, k, LOTRChestContents.WARG_PIT);
        }
        if (rotation == 2) {
            k1 = k + radius;
            this.setBlockAndNotifyAdequately(world, i, j + 3, k1, LOTRMod.fenceGateCharred, 2);
            for (j12 = j + 2; !LOTRMod.isOpaque(world, i, j12, k1 - 1) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i, j12, k1 - 1, LOTRMod.burntLadder, 2);
            }
            for (j12 = j + 2; !LOTRMod.isOpaque(world, i, j12, k1 + 1) && j12 >= 0; --j12) {
                this.setBlockAndNotifyAdequately(world, i, j12, k1 + 1, LOTRMod.burntLadder, 3);
            }
            this.setBlockAndNotifyAdequately(world, i, base + 1, k - radius + 1, LOTRMod.chestStone, 0);
            LOTRChestContents.fillChest(world, random, i, base + 1, k - radius + 1, LOTRChestContents.WARG_PIT);
        }
        if (rotation == 3) {
            i11 = i - radius;
            this.setBlockAndNotifyAdequately(world, i11, j + 3, k, LOTRMod.fenceGateCharred, 3);
            for (j1 = j + 2; !LOTRMod.isOpaque(world, i11 - 1, j1, k) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i11 - 1, j1, k, LOTRMod.burntLadder, 4);
            }
            for (j1 = j + 2; !LOTRMod.isOpaque(world, i11 + 1, j1, k) && j1 >= 0; --j1) {
                this.setBlockAndNotifyAdequately(world, i11 + 1, j1, k, LOTRMod.burntLadder, 5);
            }
            this.setBlockAndNotifyAdequately(world, i + radius - 1, base + 1, k, LOTRMod.chestStone, 0);
            LOTRChestContents.fillChest(world, random, i + radius - 1, base + 1, k, LOTRChestContents.WARG_PIT);
        }
        int wargs = 2 + random.nextInt(5);
        for (int l = 0; l < wargs; ++l) {
            LOTREntityNPC warg = this.getWarg(world);
            warg.setLocationAndAngles((double)i + 0.5, (double)(base + 1), (double)k + 0.5, 0.0f, 0.0f);
            warg.isNPCPersistent = true;
            warg.onSpawnWithEgg(null);
            warg.setHomeArea(i, base, k, 8);
            world.spawnEntityInWorld((Entity)warg);
        }
        LOTREntityNPC orc = this.getOrc(world);
        orc.setLocationAndAngles((double)i + 0.5, (double)(base + 1), (double)k + 0.5, 0.0f, 0.0f);
        orc.isNPCPersistent = true;
        orc.onSpawnWithEgg(null);
        orc.setCurrentItemOrArmor(0, new ItemStack(Items.lead));
        orc.setHomeArea(i, base, k, 8);
        world.spawnEntityInWorld((Entity)orc);
        return true;
    }

    private void placeFenceAndTorch(World world, int i, int j, int k) {
        this.setBlockAndNotifyAdequately(world, i, j, k, LOTRMod.fence, 3);
        this.placeOrcTorch(world, i, j + 1, k);
    }
}

