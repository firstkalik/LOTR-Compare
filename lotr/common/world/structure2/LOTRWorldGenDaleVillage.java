/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.structure2.LOTRWorldGenDaleBakery;
import lotr.common.world.structure2.LOTRWorldGenDaleHouse;
import lotr.common.world.structure2.LOTRWorldGenDaleSmithy;
import lotr.common.world.structure2.LOTRWorldGenDaleVillageTower;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRWorldGenDaleVillage
extends LOTRWorldGenStructureBase2 {
    public LOTRWorldGenDaleVillage(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        this.setOriginAndRotation(world, i, j, k, rotation, 0);
        this.setupRandomBlocks(random);
        LOTRWorldGenDaleVillageTower tower = new LOTRWorldGenDaleVillageTower(this.notifyChanges);
        tower.restrictions = true;
        int i1 = 0;
        int k1 = -3;
        int j1 = this.getTopBlock(world, i1, k1);
        int r = 0;
        if (!tower.generateWithSetRotation(world, random, this.getX(i1, k1), this.getY(j1), this.getZ(i1, k1), (this.getRotationMode() + r) % 4)) {
            return false;
        }
        int smithies = MathHelper.getRandomIntegerInRange((Random)random, (int)0, (int)1);
        for (int l = 0; l < smithies; ++l) {
            LOTRWorldGenDaleSmithy smithy = new LOTRWorldGenDaleSmithy(this.notifyChanges);
            smithy.restrictions = true;
            this.tryGenerateHouse(world, random, smithy);
        }
        int bakeries = MathHelper.getRandomIntegerInRange((Random)random, (int)0, (int)1);
        for (int l = 0; l < bakeries; ++l) {
            LOTRWorldGenDaleBakery bakery = new LOTRWorldGenDaleBakery(this.notifyChanges);
            bakery.restrictions = true;
            this.tryGenerateHouse(world, random, bakery);
        }
        int houses = MathHelper.getRandomIntegerInRange((Random)random, (int)2, (int)5);
        for (int l = 0; l < houses; ++l) {
            LOTRWorldGenDaleHouse house = new LOTRWorldGenDaleHouse(this.notifyChanges);
            house.restrictions = true;
            this.tryGenerateHouse(world, random, house);
        }
        return true;
    }

    private boolean tryGenerateHouse(World world, Random random, LOTRWorldGenStructureBase2 structure) {
        int attempts = 8;
        for (int l = 0; l < attempts; ++l) {
            int i1 = 0;
            int k1 = 0;
            int r = random.nextInt(4);
            if (r == 0) {
                i1 = MathHelper.getRandomIntegerInRange((Random)random, (int)-16, (int)16);
                k1 = MathHelper.getRandomIntegerInRange((Random)random, (int)7, (int)10);
            } else if (r == 1) {
                k1 = MathHelper.getRandomIntegerInRange((Random)random, (int)-16, (int)16);
                i1 = -MathHelper.getRandomIntegerInRange((Random)random, (int)7, (int)10);
            } else if (r == 2) {
                i1 = MathHelper.getRandomIntegerInRange((Random)random, (int)-16, (int)16);
                k1 = -MathHelper.getRandomIntegerInRange((Random)random, (int)7, (int)10);
            } else if (r == 3) {
                k1 = MathHelper.getRandomIntegerInRange((Random)random, (int)-16, (int)16);
                i1 = MathHelper.getRandomIntegerInRange((Random)random, (int)7, (int)10);
            }
            int j1 = this.getTopBlock(world, i1, k1);
            if (!structure.generateWithSetRotation(world, random, this.getX(i1, k1), this.getY(j1), this.getZ(i1, k1), (this.getRotationMode() + r) % 4)) continue;
            return true;
        }
        return false;
    }
}

