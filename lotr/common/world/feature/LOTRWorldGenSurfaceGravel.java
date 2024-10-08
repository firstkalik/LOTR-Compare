/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenSurfaceGravel
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        int r = MathHelper.getRandomIntegerInRange((Random)random, (int)2, (int)8);
        int chance = MathHelper.getRandomIntegerInRange((Random)random, (int)3, (int)9);
        for (int i1 = -r; i1 <= r; ++i1) {
            for (int k1 = -r; k1 <= r; ++k1) {
                Block surfBlock;
                int surfMeta;
                int i2 = i + i1;
                int k2 = k + k1;
                int d = i1 * i1 + k1 * k1;
                if (d >= r * r || random.nextInt(chance) != 0) continue;
                if (random.nextInt(100) < 15) {
                    surfBlock = LOTRMod.susGravel;
                    surfMeta = 0;
                } else {
                    surfBlock = Blocks.gravel;
                    surfMeta = 0;
                }
                int j1 = world.getTopSolidOrLiquidBlock(i2, k2) - 1;
                Block block = world.getBlock(i2, j1, k2);
                Material mt = block.getMaterial();
                if (!block.isOpaqueCube() || mt != Material.ground && mt != Material.grass) continue;
                world.setBlock(i2, j1, k2, surfBlock, surfMeta, 2);
            }
        }
        return true;
    }
}

