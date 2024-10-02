/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package lotr.common.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class PositionGenFix {
    private static Vec3 tempVector = Vec3.createVectorHelper((double)0.0, (double)0.0, (double)0.0);

    public static Vec3 findRandomTargetBlockTowards(EntityCreature creature, int horizontalRange, int yRange, Vec3 goal) {
        goal.xCoord -= creature.posX;
        goal.yCoord -= creature.posY;
        goal.zCoord -= creature.posZ;
        return PositionGenFix.findRandomTargetBlock(creature, horizontalRange, yRange, tempVector, false);
    }

    public static Vec3 findRandomTargetBlockAwayFrom(EntityCreature creature, int horizontalRange, int yRange, Vec3 avoid) {
        PositionGenFix.tempVector.xCoord = creature.posX - avoid.xCoord;
        PositionGenFix.tempVector.yCoord = creature.posY - avoid.yCoord;
        PositionGenFix.tempVector.zCoord = creature.posZ - avoid.zCoord;
        return PositionGenFix.findRandomTargetBlock(creature, horizontalRange, yRange, tempVector, true);
    }

    private static Vec3 findRandomTargetBlock(EntityCreature creature, int horizontalRange, int yRange, Vec3 towards, boolean homeBuffer) {
        double distance;
        double d1;
        Random random = creature.getRNG();
        boolean found = false;
        int x = 0;
        int y = 0;
        int z = 0;
        boolean nearHome = creature.hasHome() ? (distance = (double)(creature.getHomePosition().getDistanceSquared(MathHelper.floor_double((double)creature.posX), MathHelper.floor_double((double)creature.posY), MathHelper.floor_double((double)creature.posZ)) + 4.0f)) < (d1 = (double)(creature.func_110174_bM() + (float)horizontalRange)) * d1 : false;
        float homeRange = Math.max(homeBuffer ? 8.0f : 0.0f, creature.func_110174_bM());
        for (int trys = 0; trys < 10; ++trys) {
            int tempX = random.nextInt(2 * horizontalRange) - horizontalRange;
            int tempY = random.nextInt(2 * yRange) - yRange;
            int tempZ = random.nextInt(2 * horizontalRange) - horizontalRange;
            if (towards != null && !((double)tempX * towards.xCoord + (double)tempZ * towards.zCoord >= 0.0)) continue;
            tempX += MathHelper.floor_double((double)creature.posX);
            tempY += MathHelper.floor_double((double)creature.posY);
            tempZ += MathHelper.floor_double((double)creature.posZ);
            if (nearHome && !(creature.getHomePosition().getDistanceSquared(tempX, tempY, tempZ) < homeRange)) continue;
            x = tempX;
            y = tempY;
            z = tempZ;
            found = true;
            break;
        }
        return found ? Vec3.createVectorHelper((double)x, (double)y, (double)z) : null;
    }
}

