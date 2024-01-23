/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.biome.LOTRBiomeGenEttenmoors;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenTrollHoard
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        int k1;
        int i1;
        int l;
        int j1;
        int height = world.getTopSolidOrLiquidBlock(i, k);
        int maxCaveHeight = height - 4;
        int chests = 2 + random.nextInt(5);
        int chestsGenerated = 0;
        block0: for (l = 0; l < 64; ++l) {
            i1 = i + MathHelper.getRandomIntegerInRange((Random)random, (int)-3, (int)3);
            j1 = j + MathHelper.getRandomIntegerInRange((Random)random, (int)-3, (int)3);
            k1 = k + MathHelper.getRandomIntegerInRange((Random)random, (int)-3, (int)3);
            if (j1 > maxCaveHeight || !world.isAirBlock(i1, j1, k1) || world.getBlock(i1, j1 - 1, k1) != Blocks.stone) continue;
            Block treasureBlock = random.nextInt(5) == 0 ? LOTRMod.treasureCopper : (random.nextBoolean() ? LOTRMod.treasureSilver : LOTRMod.treasureGold);
            int top = j1 + random.nextInt(3);
            for (int j2 = j1; j2 <= top; ++j2) {
                int treasureMeta = 7;
                if (j2 == top) {
                    treasureMeta = random.nextInt(7);
                }
                if (!world.isAirBlock(i1, j2, k1)) continue block0;
                this.setBlockAndNotifyAdequately(world, i1, j2, k1, treasureBlock, treasureMeta);
            }
        }
        for (l = 0; l < 48; ++l) {
            i1 = i + MathHelper.getRandomIntegerInRange((Random)random, (int)-8, (int)8);
            j1 = j + MathHelper.getRandomIntegerInRange((Random)random, (int)-2, (int)2);
            k1 = k + MathHelper.getRandomIntegerInRange((Random)random, (int)-8, (int)8);
            if (j1 > maxCaveHeight || !world.isAirBlock(i1, j1, k1) || world.getBlock(i1, j1 - 1, k1) != Blocks.stone) continue;
            this.setBlockAndNotifyAdequately(world, i1, j1, k1, (Block)Blocks.chest, 0);
            LOTRChestContents.fillChest(world, random, i1, j1, k1, LOTRChestContents.TROLL_HOARD);
            if (world.getBiomeGenForCoords(i1, k1) instanceof LOTRBiomeGenEttenmoors && random.nextInt(5) == 0) {
                LOTRChestContents.fillChest(world, random, i1, j1, k1, LOTRChestContents.TROLL_HOARD_ETTENMOORS);
            }
            if (++chestsGenerated >= chests) break;
        }
        return chestsGenerated > 0;
    }
}

