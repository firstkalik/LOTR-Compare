/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.entity.ai;

import java.util.List;
import lotr.common.entity.npc.LOTREntityGollum;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTREntityAIGollumFollowOwner
extends EntityAIBase {
    private LOTREntityGollum theGollum;
    private EntityPlayer theOwner;
    private double moveSpeed;
    private PathNavigate theGollumPathfinder;
    private int followTick;
    private float maxDist;
    private float minDist;
    private boolean avoidsWater;
    private World theWorld;

    public LOTREntityAIGollumFollowOwner(LOTREntityGollum entity, double d, float f, float f1) {
        this.theGollum = entity;
        this.moveSpeed = d;
        this.theGollumPathfinder = entity.getNavigator();
        this.minDist = f;
        this.maxDist = f1;
        this.theWorld = entity.worldObj;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        EntityPlayer entityplayer = this.theGollum.getGollumOwner();
        if (entityplayer == null) {
            return false;
        }
        if (this.theGollum.isGollumSitting()) {
            return false;
        }
        if (this.theGollum.getDistanceSqToEntity((Entity)entityplayer) < (double)(this.minDist * this.minDist)) {
            return false;
        }
        this.theOwner = entityplayer;
        return true;
    }

    public boolean continueExecuting() {
        return this.theGollum.getGollumOwner() != null && !this.theGollumPathfinder.noPath() && this.theGollum.getDistanceSqToEntity((Entity)this.theOwner) > (double)(this.maxDist * this.maxDist) && !this.theGollum.isGollumSitting();
    }

    public void startExecuting() {
        this.followTick = 0;
        this.avoidsWater = this.theGollum.getNavigator().getAvoidsWater();
        this.theGollum.getNavigator().setAvoidsWater(false);
    }

    public void resetTask() {
        this.theOwner = null;
        this.theGollumPathfinder.clearPathEntity();
        this.theGollum.getNavigator().setAvoidsWater(this.avoidsWater);
    }

    public void updateTask() {
        this.theGollum.getLookHelper().setLookPositionWithEntity((Entity)this.theOwner, 10.0f, (float)this.theGollum.getVerticalFaceSpeed());
        if (!this.theGollum.isGollumSitting() && --this.followTick <= 0) {
            this.followTick = 10;
            if (!this.theGollumPathfinder.tryMoveToEntityLiving((Entity)this.theOwner, this.moveSpeed) && this.theGollum.getDistanceSqToEntity((Entity)this.theOwner) >= 256.0) {
                int i = MathHelper.floor_double((double)this.theOwner.posX);
                int j = MathHelper.floor_double((double)this.theOwner.boundingBox.minY);
                int k = MathHelper.floor_double((double)this.theOwner.posZ);
                float f = this.theGollum.width / 2.0f;
                float f1 = this.theGollum.height;
                AxisAlignedBB theGollumBoundingBox = AxisAlignedBB.getBoundingBox((double)(this.theOwner.posX - (double)f), (double)(this.theOwner.posY - (double)this.theGollum.yOffset + (double)this.theGollum.ySize), (double)(this.theOwner.posZ - (double)f), (double)(this.theOwner.posX + (double)f), (double)(this.theOwner.posY - (double)this.theGollum.yOffset + (double)this.theGollum.ySize + (double)f1), (double)(this.theOwner.posZ + (double)f));
                if (this.theWorld.func_147461_a(theGollumBoundingBox).isEmpty() && this.theWorld.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)this.theWorld, i, j - 1, k, ForgeDirection.UP)) {
                    this.theGollum.setLocationAndAngles(this.theOwner.posX, this.theOwner.boundingBox.minY, this.theOwner.posZ, this.theGollum.rotationYaw, this.theGollum.rotationPitch);
                    this.theGollum.fallDistance = 0.0f;
                    this.theGollum.getNavigator().clearPathEntity();
                }
            }
        }
    }
}

