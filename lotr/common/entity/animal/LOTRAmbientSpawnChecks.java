/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.animal;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRAmbientSpawnChecks {
    public static boolean canSpawn(EntityLiving entity, int xzRange, int yRange, int attempts, int required, Material ... materials) {
        int k;
        int j;
        World world = entity.worldObj;
        Random rand = entity.getRNG();
        int i = MathHelper.floor_double((double)entity.posX);
        Block below = world.getBlock(i, (j = MathHelper.floor_double((double)entity.posY)) - 1, k = MathHelper.floor_double((double)entity.posZ));
        if (below == world.getBiomeGenForCoords((int)i, (int)k).topBlock) {
            int light2 = world.getBlockLightValue(i, j, k);
            if (j >= 62 && light2 >= rand.nextInt(8) || light2 >= 8) {
                List<Material> validMaterials = Arrays.asList(materials);
                int counted = 0;
                for (int l = 0; l < attempts; ++l) {
                    int i1 = i + rand.nextInt(xzRange) - rand.nextInt(xzRange);
                    int k1 = k + rand.nextInt(xzRange) - rand.nextInt(xzRange);
                    int j1 = j + rand.nextInt(yRange) - rand.nextInt(yRange);
                    if (!world.blockExists(i1, j1, k1) || !validMaterials.contains((Object)world.getBlock(i1, j1, k1).getMaterial()) || ++counted <= required) continue;
                    return true;
                }
            }
        }
        return false;
    }
}

