/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenKelp
extends WorldGenerator {
    private final int minTreeHeight;
    private final int maxTreeHeight;
    private final Block block;
    private final int meta;
    private int tries;

    public LOTRWorldGenKelp(Block block, int meta, int minTreeHeight, int maxTreeHeight) {
        super(false);
        this.block = block;
        this.meta = meta;
        this.minTreeHeight = minTreeHeight;
        this.maxTreeHeight = maxTreeHeight;
        this.tries = 24;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        int l1 = MathHelper.getRandomIntegerInRange((Random)random, (int)this.minTreeHeight, (int)this.maxTreeHeight);
        for (int l = 0; l < this.tries; ++l) {
            int j1;
            int k1;
            int i1 = x - random.nextInt(6) + random.nextInt(6);
            if (!this.block.canBlockStay(world, i1, j1 = world.getTopSolidOrLiquidBlock(i1, k1 = z - random.nextInt(6) + random.nextInt(6)), k1)) continue;
            for (int j2 = 0; j2 < l1 && world.getBlock(i1, j1 + j2 + 2, k1) == Blocks.water; ++j2) {
                world.setBlock(i1, j1 + j2, k1, this.block, this.meta, 2);
            }
        }
        return true;
    }
}

