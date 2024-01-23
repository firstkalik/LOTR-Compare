/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntityAIBurningPanic
extends EntityAIBase {
    private EntityCreature theEntity;
    private World theWorld;
    private double speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;
    private boolean avoidsWater;

    public LOTREntityAIBurningPanic(EntityCreature entity, double d) {
        this.theEntity = entity;
        this.theWorld = this.theEntity.worldObj;
        this.speed = d;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        if (this.theEntity.isBurning() && this.theEntity.getAttackTarget() == null) {
            Vec3 target = this.findWaterLocation();
            if (target == null) {
                target = RandomPositionGenerator.findRandomTarget((EntityCreature)this.theEntity, (int)5, (int)4);
            }
            if (target != null) {
                this.randPosX = target.xCoord;
                this.randPosY = target.yCoord;
                this.randPosZ = target.zCoord;
                return true;
            }
        }
        return false;
    }

    private Vec3 findWaterLocation() {
        Random random = this.theEntity.getRNG();
        for (int l = 0; l < 32; ++l) {
            int j;
            int k;
            int i = MathHelper.floor_double((double)this.theEntity.posX) + MathHelper.getRandomIntegerInRange((Random)random, (int)-8, (int)8);
            if (this.theWorld.getBlock(i, (j = MathHelper.floor_double((double)this.theEntity.boundingBox.minY) + MathHelper.getRandomIntegerInRange((Random)random, (int)-8, (int)8)) + 1, k = MathHelper.floor_double((double)this.theEntity.posZ) + MathHelper.getRandomIntegerInRange((Random)random, (int)-8, (int)8)).isNormalCube() || this.theWorld.getBlock(i, j, k).isNormalCube() || this.theWorld.getBlock(i, j - 1, k).getMaterial() != Material.water) continue;
            return Vec3.createVectorHelper((double)((double)i + 0.5), (double)((double)j + 0.5), (double)((double)k + 0.5));
        }
        return null;
    }

    public void startExecuting() {
        this.avoidsWater = this.theEntity.getNavigator().getAvoidsWater();
        this.theEntity.getNavigator().setAvoidsWater(false);
        this.theEntity.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
    }

    public boolean continueExecuting() {
        return this.theEntity.isBurning() && this.theEntity.getAttackTarget() == null && !this.theEntity.getNavigator().noPath();
    }

    public void resetTask() {
        this.theEntity.getNavigator().setAvoidsWater(this.avoidsWater);
    }
}

