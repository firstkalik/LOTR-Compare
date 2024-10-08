/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenBoulder
extends WorldGenerator {
    private Block id;
    private int meta;
    private int minWidth;
    private int maxWidth;
    private int heightCheck = 3;

    public LOTRWorldGenBoulder(Block i, int j, int k, int l) {
        super(false);
        this.id = i;
        this.meta = j;
        this.minWidth = k;
        this.maxWidth = l;
    }

    public LOTRWorldGenBoulder setHeightCheck(int i) {
        this.heightCheck = i;
        return this;
    }

    public boolean generate(World world, Random random, int i, int j, int k) {
        world.getBiomeGenForCoords(i, k);
        if (!LOTRWorldGenStructureBase2.isSurfaceStatic(world, i, j - 1, k)) {
            return false;
        }
        int boulderWidth = MathHelper.getRandomIntegerInRange((Random)random, (int)this.minWidth, (int)this.maxWidth);
        int highestHeight = j;
        int lowestHeight = j;
        for (int i1 = i - boulderWidth; i1 <= i + boulderWidth; ++i1) {
            for (int k1 = k - boulderWidth; k1 <= k + boulderWidth; ++k1) {
                int heightValue = world.getHeightValue(i1, k1);
                if (!LOTRWorldGenStructureBase2.isSurfaceStatic(world, i1, heightValue - 1, k1)) {
                    return false;
                }
                if (heightValue > highestHeight) {
                    highestHeight = heightValue;
                }
                if (heightValue >= lowestHeight) continue;
                lowestHeight = heightValue;
            }
        }
        if (highestHeight - lowestHeight > this.heightCheck) {
            return false;
        }
        int spheres = 1 + random.nextInt(boulderWidth + 1);
        for (int l = 0; l < spheres; ++l) {
            int posX = i + MathHelper.getRandomIntegerInRange((Random)random, (int)(-boulderWidth), (int)boulderWidth);
            int posZ = k + MathHelper.getRandomIntegerInRange((Random)random, (int)(-boulderWidth), (int)boulderWidth);
            int posY = world.getTopSolidOrLiquidBlock(posX, posZ);
            int sphereWidth = MathHelper.getRandomIntegerInRange((Random)random, (int)this.minWidth, (int)this.maxWidth);
            for (int i1 = posX - sphereWidth; i1 <= posX + sphereWidth; ++i1) {
                for (int j1 = posY - sphereWidth; j1 <= posY + sphereWidth; ++j1) {
                    for (int k1 = posZ - sphereWidth; k1 <= posZ + sphereWidth; ++k1) {
                        int j3;
                        int i2 = i1 - posX;
                        int j2 = j1 - posY;
                        int k2 = k1 - posZ;
                        int dist = i2 * i2 + j2 * j2 + k2 * k2;
                        if (dist >= sphereWidth * sphereWidth && (dist >= (sphereWidth + 1) * (sphereWidth + 1) || random.nextInt(3) != 0)) continue;
                        for (j3 = j1; j3 >= 0 && !world.getBlock(i1, j3 - 1, k1).isOpaqueCube(); --j3) {
                        }
                        this.setBlockAndNotifyAdequately(world, i1, j3, k1, this.id, this.meta);
                        world.getBlock(i1, j3 - 1, k1).onPlantGrow(world, i1, j3 - 1, k1, i1, j3 - 1, k1);
                    }
                }
            }
        }
        return true;
    }
}

