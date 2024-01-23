/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.Vec3
 */
package lotr.common.entity.ai;

import java.util.Random;
import lotr.common.entity.npc.LOTREntityNPCRideable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

public class LOTREntityAIUntamedPanic
extends EntityAIBase {
    private LOTREntityNPCRideable theMount;
    private double speed;
    private double targetX;
    private double targetY;
    private double targetZ;

    public LOTREntityAIUntamedPanic(LOTREntityNPCRideable mount, double d) {
        this.theMount = mount;
        this.speed = d;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        if (!this.theMount.isNPCTamed() && this.theMount.riddenByEntity instanceof EntityPlayer) {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget((EntityCreature)this.theMount, (int)5, (int)4);
            if (vec3 == null) {
                return false;
            }
            this.targetX = vec3.xCoord;
            this.targetY = vec3.yCoord;
            this.targetZ = vec3.zCoord;
            return true;
        }
        return false;
    }

    public void startExecuting() {
        this.theMount.getNavigator().tryMoveToXYZ(this.targetX, this.targetY, this.targetZ, this.speed);
    }

    public boolean continueExecuting() {
        return !this.theMount.getNavigator().noPath() && this.theMount.riddenByEntity instanceof EntityPlayer && !this.theMount.isNPCTamed();
    }

    public void updateTask() {
        if (this.theMount.getRNG().nextInt(50) == 0) {
            if (this.theMount.riddenByEntity instanceof EntityPlayer) {
                int i = this.theMount.getNPCTemper();
                int j = this.theMount.getMaxNPCTemper();
                if (j > 0 && this.theMount.getRNG().nextInt(j) < i) {
                    this.theMount.tameNPC((EntityPlayer)this.theMount.riddenByEntity);
                    this.theMount.spawnHearts();
                    return;
                }
                this.theMount.increaseNPCTemper(5);
            }
            this.theMount.riddenByEntity.mountEntity((Entity)null);
            this.theMount.riddenByEntity = null;
            this.theMount.angerNPC();
            this.theMount.spawnSmokes();
        }
    }
}

