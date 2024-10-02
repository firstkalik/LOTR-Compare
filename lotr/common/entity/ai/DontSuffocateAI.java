/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 */
package lotr.common.entity.ai;

import lotr.common.entity.ai.PositionGenFix;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class DontSuffocateAI
extends EntityAIBase {
    public EntityCreature mount;
    public int nextPath;
    public static boolean onAllCreatures = false;

    public DontSuffocateAI(EntityCreature creature) {
        this.mount = creature;
    }

    public static void applyDontSuffocate(Entity mount) {
        if (mount.riddenByEntity != null && !mount.worldObj.isRemote && mount instanceof EntityCreature && mount.riddenByEntity instanceof EntityCreature) {
            EntityCreature creature = (EntityCreature)mount;
            creature.tasks.addTask(3, (EntityAIBase)new DontSuffocateAI(creature));
        }
    }

    public boolean isSuffocating() {
        Entity rider = this.mount.riddenByEntity;
        double posX = rider.posX;
        double posZ = rider.posZ;
        int y = MathHelper.floor_double((double)((double)(rider.getEyeHeight() - 0.05f) + rider.posY));
        float width = rider.width * 0.8f;
        for (int side = 0; side < 4; ++side) {
            int z;
            int x = MathHelper.floor_double((double)(posX + (double)(((float)(side & 1) - 0.5f) * width)));
            if (!rider.worldObj.getChunkFromBlockCoords(x, z = MathHelper.floor_double((double)(posZ + (double)(((float)(side >> 1 & 1) - 0.5f) * width)))).getBlock(x & 0xF, y, z & 0xF).isNormalCube()) continue;
            return true;
        }
        return false;
    }

    public boolean unsafePos(double xCoord, double yCoord, double zCoord, Entity rider) {
        double distanceY = this.mount.posY - rider.posY + (double)rider.getEyeHeight() + 0.05;
        int x = MathHelper.floor_double((double)xCoord);
        int y = MathHelper.floor_double((double)(yCoord + distanceY));
        int z = MathHelper.floor_double((double)zCoord);
        return rider.worldObj.getBlock(x, y, z).isNormalCube();
    }

    public void updateTask() {
        int xzRange = 5;
        int yRange = 3;
        Vec3 dest = null;
        EntityCreature rider = (EntityCreature)this.mount.riddenByEntity;
        Vec3 avoid = Vec3.createVectorHelper((double)rider.posX, (double)rider.posY, (double)rider.posZ);
        double speed = 1.0 / this.mount.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 0.37;
        for (int trys = 0; trys < 16; ++trys) {
            if (trys == 1) {
                xzRange = 8;
                yRange = 4;
            } else if (trys == 4) {
                xzRange = 16;
                yRange = 7;
            }
            dest = PositionGenFix.findRandomTargetBlockAwayFrom(rider, xzRange, yRange, avoid);
            if (dest == null || this.unsafePos(dest.xCoord, dest.yCoord, dest.zCoord, (Entity)rider)) continue;
            this.mount.getNavigator().tryMoveToXYZ(dest.xCoord, dest.yCoord, dest.zCoord, speed);
            break;
        }
    }

    public boolean continueExecuting() {
        return false;
    }

    public boolean shouldExecute() {
        if (this.mount.riddenByEntity != null && this.nextPath < this.mount.ticksExisted && this.isSuffocating()) {
            this.nextPath = this.mount.ticksExisted + 80;
            return true;
        }
        return false;
    }
}

