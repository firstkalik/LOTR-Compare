/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockBerryBush2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenBerryBush2
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        Block bush = LOTRMod.berryBush2;
        LOTRBlockBerryBush2.BushType bushType = LOTRBlockBerryBush2.BushType.randomType(random);
        int bushMeta = bushType.bushMeta;
        bushMeta = LOTRBlockBerryBush2.setHasBerries(bushMeta, true);
        if (bushType.poisonous && random.nextInt(2) != 0) {
            return false;
        }
        for (int l = 0; l < 12; ++l) {
            int i1 = i - random.nextInt(4) + random.nextInt(4);
            int j1 = j - random.nextInt(2) + random.nextInt(2);
            int k1 = k - random.nextInt(4) + random.nextInt(4);
            Block below = world.getBlock(i1, j1 - 1, k1);
            Block block = world.getBlock(i1, j1, k1);
            if (!below.canSustainPlant((IBlockAccess)world, i1, j1 - 1, k1, ForgeDirection.UP, (IPlantable)Blocks.sapling) || block.getMaterial().isLiquid() || !block.isReplaceable((IBlockAccess)world, i1, j1, k1)) continue;
            world.setBlock(i1, j1, k1, bush, bushMeta, 2);
        }
        return true;
    }
}

