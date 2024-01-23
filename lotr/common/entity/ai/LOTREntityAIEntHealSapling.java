/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityEnt;
import lotr.common.tileentity.LOTRTileEntityCorruptMallorn;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntityAIEntHealSapling
extends EntityAIBase {
    private LOTREntityEnt theEnt;
    private World theWorld;
    private double moveSpeed;
    private double xPos;
    private double yPos;
    private double zPos;
    private int healingTick;
    private static int HEAL_TIME = 160;
    private int pathingTick;
    private int rePathDelay;

    public LOTREntityAIEntHealSapling(LOTREntityEnt ent, double d) {
        this.theEnt = ent;
        this.moveSpeed = d;
        this.theWorld = ent.worldObj;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        Vec3 vec3;
        if (this.theEnt.canHealSapling && (vec3 = this.findSapling()) != null) {
            this.xPos = vec3.xCoord;
            this.yPos = vec3.yCoord;
            this.zPos = vec3.zCoord;
            return true;
        }
        return false;
    }

    public boolean continueExecuting() {
        if (!this.theEnt.canHealSapling) {
            return false;
        }
        if (this.pathingTick < 300 && this.healingTick < HEAL_TIME) {
            Block block = this.theWorld.getBlock(MathHelper.floor_double((double)this.xPos), MathHelper.floor_double((double)this.yPos), MathHelper.floor_double((double)this.zPos));
            return block == LOTRMod.corruptMallorn;
        }
        return false;
    }

    public void resetTask() {
        this.pathingTick = 0;
        this.healingTick = 0;
        this.rePathDelay = 0;
        this.theEnt.setHealingSapling(false);
    }

    public void updateTask() {
        if (this.theEnt.getDistanceSq(this.xPos, this.yPos, this.zPos) > 9.0) {
            this.theEnt.setHealingSapling(false);
            --this.rePathDelay;
            if (this.rePathDelay <= 0) {
                this.rePathDelay = 10;
                this.theEnt.getNavigator().tryMoveToXYZ(this.xPos, this.yPos, this.zPos, this.moveSpeed);
            }
            ++this.pathingTick;
        } else {
            this.theEnt.getNavigator().clearPathEntity();
            this.theEnt.getLookHelper().setLookPosition(this.xPos, this.yPos + 0.5, this.zPos, 10.0f, (float)this.theEnt.getVerticalFaceSpeed());
            this.theEnt.setHealingSapling(true);
            this.theEnt.saplingHealTarget = new ChunkCoordinates(MathHelper.floor_double((double)this.xPos), MathHelper.floor_double((double)this.yPos), MathHelper.floor_double((double)this.zPos));
            ++this.healingTick;
            if (this.healingTick >= HEAL_TIME) {
                this.theWorld.setBlock(MathHelper.floor_double((double)this.xPos), MathHelper.floor_double((double)this.yPos), MathHelper.floor_double((double)this.zPos), LOTRMod.sapling, 1, 3);
                this.theEnt.setHealingSapling(false);
            }
        }
    }

    private Vec3 findSapling() {
        double leastDistSq = 576.0;
        LOTRTileEntityCorruptMallorn mallorn = null;
        for (Object obj : this.theWorld.loadedTileEntityList) {
            if (!(obj instanceof LOTRTileEntityCorruptMallorn)) continue;
            LOTRTileEntityCorruptMallorn te = (LOTRTileEntityCorruptMallorn)((Object)obj);
            double distSq = this.theEnt.getDistanceSq((double)te.xCoord + 0.5, (double)te.yCoord, (double)te.zCoord + 0.5);
            if (!(distSq < leastDistSq)) continue;
            mallorn = te;
            leastDistSq = distSq;
        }
        if (mallorn != null) {
            return Vec3.createVectorHelper((double)((double)mallorn.xCoord + 0.5), (double)mallorn.yCoord, (double)((double)mallorn.zCoord + 0.5));
        }
        return null;
    }
}

