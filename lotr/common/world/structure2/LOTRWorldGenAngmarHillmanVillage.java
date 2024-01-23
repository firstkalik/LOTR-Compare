/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.structure2.LOTRWorldGenAngmarHillmanChieftainHouse;
import lotr.common.world.structure2.LOTRWorldGenAngmarHillmanHouse;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRWorldGenAngmarHillmanVillage
extends LOTRWorldGenStructureBase2 {
    private static int VILLAGE_SIZE = 16;

    public LOTRWorldGenAngmarHillmanVillage(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int l;
        LOTRWorldGenStructureBase2 structure;
        this.setOriginAndRotation(world, i, j, k, rotation, 0);
        if (this.restrictions) {
            boolean suitableSpawn = true;
            BiomeGenBase biome = world.getBiomeGenForCoords(this.originX, this.originZ);
            if (!suitableSpawn) {
                return false;
            }
        }
        int houses = MathHelper.getRandomIntegerInRange((Random)random, (int)3, (int)6);
        int chiefainHouses = MathHelper.getRandomIntegerInRange((Random)random, (int)0, (int)1);
        for (l = 0; l < chiefainHouses; ++l) {
            structure = new LOTRWorldGenAngmarHillmanChieftainHouse(this.notifyChanges);
            this.attemptHouseSpawn(structure, world, random);
        }
        for (l = 0; l < houses; ++l) {
            structure = new LOTRWorldGenAngmarHillmanHouse(this.notifyChanges);
            this.attemptHouseSpawn(structure, world, random);
        }
        return true;
    }

    private boolean attemptHouseSpawn(LOTRWorldGenStructureBase2 structure, World world, Random random) {
        structure.restrictions = this.restrictions;
        structure.usingPlayer = this.usingPlayer;
        for (int l = 0; l < 16; ++l) {
            int rotation;
            int x = MathHelper.getRandomIntegerInRange((Random)random, (int)(-VILLAGE_SIZE), (int)VILLAGE_SIZE);
            int z = MathHelper.getRandomIntegerInRange((Random)random, (int)(-VILLAGE_SIZE), (int)VILLAGE_SIZE);
            int spawnX = this.getX(x, z);
            int spawnZ = this.getZ(x, z);
            int spawnY = this.getY(this.getTopBlock(world, x, z));
            if (!structure.generateWithSetRotation(world, random, spawnX, spawnY, spawnZ, rotation = random.nextInt(4))) continue;
            return true;
        }
        return false;
    }
}

