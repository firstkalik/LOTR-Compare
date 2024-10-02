/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 */
package lotr.common.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;

public class WorldGenLimitedFlowers
extends WorldGenFlowers {
    private Block flowerBlock;
    private int flowerMeta;

    public WorldGenLimitedFlowers(Block block, int meta) {
        super(block);
        this.flowerBlock = block;
        this.flowerMeta = meta;
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        int numberOfFlowers = 1 + rand.nextInt(2);
        for (int i = 0; i < numberOfFlowers; ++i) {
            int posY;
            int posZ;
            int posX = x + rand.nextInt(8) - rand.nextInt(8);
            if (!world.isAirBlock(posX, posY = y + rand.nextInt(4) - rand.nextInt(4), posZ = z + rand.nextInt(8) - rand.nextInt(8)) || world.provider.hasNoSky && posY >= 255 || !this.flowerBlock.canBlockStay(world, posX, posY, posZ)) continue;
            world.setBlock(posX, posY, posZ, this.flowerBlock, this.flowerMeta, 2);
        }
        return true;
    }
}

