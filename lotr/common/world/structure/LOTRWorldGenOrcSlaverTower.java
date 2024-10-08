/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityMordorOrc;
import lotr.common.entity.npc.LOTREntityMordorOrcSlaver;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.world.biome.LOTRBiomeGenNurn;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRWorldGenOrcSlaverTower
extends LOTRWorldGenStructureBase {
    public LOTRWorldGenOrcSlaverTower(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        int j1;
        int k1;
        int i1;
        int j12;
        if (this.restrictions && (world.getBlock(i, j - 1, k) != Blocks.grass || !(world.getBiomeGenForCoords(i, k) instanceof LOTRBiomeGenNurn))) {
            return false;
        }
        int height = 5 + random.nextInt(4);
        j += height;
        int rotation = random.nextInt(4);
        if (!this.restrictions && this.usingPlayer != null) {
            rotation = this.usingPlayerRotation();
        }
        switch (rotation) {
            case 0: {
                ++k;
                break;
            }
            case 1: {
                --i;
                break;
            }
            case 2: {
                --k;
                break;
            }
            case 3: {
                ++i;
            }
        }
        if (this.restrictions) {
            for (i1 = i - 3; i1 <= i + 3; ++i1) {
                for (k1 = k - 3; k1 <= k + 3; ++k1) {
                    j12 = world.getHeightValue(i1, k1) - 1;
                    Block l = world.getBlock(i1, j12, k1);
                    if (l == Blocks.grass) continue;
                    return false;
                }
            }
        }
        for (i1 = i - 3; i1 <= i + 3; ++i1) {
            for (k1 = k - 3; k1 <= k + 3; ++k1) {
                this.setBlockAndNotifyAdequately(world, i1, j, k1, LOTRMod.planks, 3);
                this.setBlockAndNotifyAdequately(world, i1, j + 6, k1, LOTRMod.planks, 3);
                if (Math.abs(i1 - i) != 3 && Math.abs(k1 - k) != 3) continue;
                this.setBlockAndNotifyAdequately(world, i1, j + 1, k1, LOTRMod.fence, 3);
                this.setBlockAndNotifyAdequately(world, i1, j + 5, k1, LOTRMod.fence, 3);
                this.setBlockAndNotifyAdequately(world, i1, j + 7, k1, LOTRMod.fence, 3);
            }
        }
        for (i1 = i - 3; i1 <= i + 3; i1 += 6) {
            for (k1 = k - 3; k1 <= k + 3; k1 += 6) {
                for (j12 = j + 5; !(j12 < j && LOTRMod.isOpaque(world, i1, j12, k1) || j12 < 0); --j12) {
                    this.setBlockAndNotifyAdequately(world, i1, j12, k1, LOTRMod.wood, 3);
                    this.setGrassToDirt(world, i1, j12 - 1, k1);
                }
            }
        }
        for (j1 = j + 2; j1 <= j + 4; ++j1) {
            this.setBlockAndNotifyAdequately(world, i - 2, j1, k - 3, LOTRMod.fence, 3);
            this.setBlockAndNotifyAdequately(world, i - 2, j1, k + 3, LOTRMod.fence, 3);
            this.setBlockAndNotifyAdequately(world, i + 2, j1, k - 3, LOTRMod.fence, 3);
            this.setBlockAndNotifyAdequately(world, i + 2, j1, k + 3, LOTRMod.fence, 3);
            this.setBlockAndNotifyAdequately(world, i - 3, j1, k - 2, LOTRMod.fence, 3);
            this.setBlockAndNotifyAdequately(world, i + 3, j1, k - 2, LOTRMod.fence, 3);
            this.setBlockAndNotifyAdequately(world, i - 3, j1, k + 2, LOTRMod.fence, 3);
            this.setBlockAndNotifyAdequately(world, i + 3, j1, k + 2, LOTRMod.fence, 3);
        }
        for (j1 = j + 11; !(j1 < j && LOTRMod.isOpaque(world, i, j1, k) || j1 < 0); --j1) {
            this.setBlockAndNotifyAdequately(world, i, j1, k, LOTRMod.wood, 3);
            this.setGrassToDirt(world, i, j1 - 1, k);
            if (j1 > j + 6) continue;
            this.setBlockAndNotifyAdequately(world, i, j1, k - 1, Blocks.ladder, 2);
        }
        this.setBlockAndNotifyAdequately(world, i, j + 1, k - 1, LOTRMod.trapdoorCharred, 0);
        this.setBlockAndNotifyAdequately(world, i, j + 7, k - 1, LOTRMod.trapdoorCharred, 0);
        this.placeOrcTorch(world, i - 3, j + 8, k - 3);
        this.placeOrcTorch(world, i - 3, j + 8, k + 3);
        this.placeOrcTorch(world, i + 3, j + 8, k - 3);
        this.placeOrcTorch(world, i + 3, j + 8, k + 3);
        this.setBlockAndNotifyAdequately(world, i, j + 12, k, LOTRMod.fence, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 13, k, LOTRMod.fence, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 12, k - 1, LOTRMod.fence, 3);
        this.setBlockAndNotifyAdequately(world, i, j + 12, k + 1, LOTRMod.fence, 3);
        this.setBlockAndNotifyAdequately(world, i - 1, j + 12, k, LOTRMod.fence, 3);
        this.setBlockAndNotifyAdequately(world, i + 1, j + 12, k, LOTRMod.fence, 3);
        this.placeOrcTorch(world, i, j + 14, k);
        this.placeOrcTorch(world, i, j + 13, k - 1);
        this.placeOrcTorch(world, i, j + 13, k + 1);
        this.placeOrcTorch(world, i - 1, j + 13, k);
        this.placeOrcTorch(world, i + 1, j + 13, k);
        LOTREntityMordorOrcSlaver slaver = new LOTREntityMordorOrcSlaver(world);
        slaver.setLocationAndAngles((double)i + 1.5, (double)(j + 7), (double)k + 1.5, 0.0f, 0.0f);
        slaver.onSpawnWithEgg(null);
        world.spawnEntityInWorld((Entity)slaver);
        slaver.setHomeArea(i, j + 6, k, 12);
        int orcs = 2 + random.nextInt(3);
        for (int l = 0; l < orcs; ++l) {
            LOTREntityMordorOrc orc = new LOTREntityMordorOrc(world);
            orc.setLocationAndAngles((double)i + 1.5, (double)(j + 1), (double)k + 1.5, 0.0f, 0.0f);
            ((LOTREntityOrc)orc).onSpawnWithEgg(null);
            orc.isNPCPersistent = true;
            world.spawnEntityInWorld((Entity)orc);
            orc.setHomeArea(i, j + 1, k, 8);
        }
        return true;
    }
}

