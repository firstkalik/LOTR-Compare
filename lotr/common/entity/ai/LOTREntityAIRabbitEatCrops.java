/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityRabbit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntityAIRabbitEatCrops
extends EntityAIBase {
    private LOTREntityRabbit theRabbit;
    private double xPos;
    private double yPos;
    private double zPos;
    private double moveSpeed;
    private World theWorld;
    private int pathingTick;
    private int eatingTick;
    private int rePathDelay;

    public LOTREntityAIRabbitEatCrops(LOTREntityRabbit rabbit, double d) {
        this.theRabbit = rabbit;
        this.moveSpeed = d;
        this.theWorld = rabbit.worldObj;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        Vec3 vec3;
        if (!LOTRMod.canGrief(this.theWorld)) {
            return false;
        }
        if (this.theRabbit.getRNG().nextInt(20) == 0 && (vec3 = this.findCropsLocation()) != null) {
            this.xPos = vec3.xCoord;
            this.yPos = vec3.yCoord;
            this.zPos = vec3.zCoord;
            return true;
        }
        return false;
    }

    public boolean continueExecuting() {
        if (!LOTRMod.canGrief(this.theWorld)) {
            return false;
        }
        if (this.pathingTick < 200 && this.eatingTick < 60) {
            int i = MathHelper.floor_double((double)this.xPos);
            int j = MathHelper.floor_double((double)this.yPos);
            int k = MathHelper.floor_double((double)this.zPos);
            return this.canEatBlock(i, j, k);
        }
        return false;
    }

    public void resetTask() {
        this.pathingTick = 0;
        this.eatingTick = 0;
        this.rePathDelay = 0;
        this.theRabbit.setRabbitEating(false);
    }

    public void updateTask() {
        if (this.theRabbit.getDistanceSq(this.xPos, this.yPos, this.zPos) > 1.0) {
            this.theRabbit.setRabbitEating(false);
            this.theRabbit.getLookHelper().setLookPosition(this.xPos, this.yPos - 0.5, this.zPos, 10.0f, (float)this.theRabbit.getVerticalFaceSpeed());
            --this.rePathDelay;
            if (this.rePathDelay <= 0) {
                this.rePathDelay = 10;
                this.theRabbit.getNavigator().tryMoveToXYZ(this.xPos, this.yPos, this.zPos, this.moveSpeed);
            }
            ++this.pathingTick;
        } else {
            this.theRabbit.setRabbitEating(true);
            ++this.eatingTick;
            if (this.eatingTick % 6 == 0) {
                this.theRabbit.playSound("random.eat", 1.0f, (this.theWorld.rand.nextFloat() - this.theWorld.rand.nextFloat()) * 0.2f + 1.0f);
            }
            if (this.eatingTick >= 60) {
                this.theWorld.setBlockToAir(MathHelper.floor_double((double)this.xPos), MathHelper.floor_double((double)this.yPos), MathHelper.floor_double((double)this.zPos));
            }
        }
    }

    private Vec3 findCropsLocation() {
        Random random = this.theRabbit.getRNG();
        for (int l = 0; l < 32; ++l) {
            int k;
            int j;
            int i = MathHelper.floor_double((double)this.theRabbit.posX) + MathHelper.getRandomIntegerInRange((Random)random, (int)-16, (int)16);
            if (!this.canEatBlock(i, j = MathHelper.floor_double((double)this.theRabbit.boundingBox.minY) + MathHelper.getRandomIntegerInRange((Random)random, (int)-8, (int)8), k = MathHelper.floor_double((double)this.theRabbit.posZ) + MathHelper.getRandomIntegerInRange((Random)random, (int)-16, (int)16))) continue;
            return Vec3.createVectorHelper((double)((double)i + 0.5), (double)j, (double)((double)k + 0.5));
        }
        return null;
    }

    private boolean canEatBlock(int i, int j, int k) {
        Block block = this.theWorld.getBlock(i, j, k);
        return block instanceof BlockCrops && !this.theRabbit.anyFarmhandsNearby(i, j, k);
    }
}

