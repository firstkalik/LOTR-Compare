/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityJumpHelper
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import java.util.List;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntityMallornLeafBomb
extends EntityThrowable {
    private UUID throwerUUID;
    private int leavesAge;
    private static int MAX_LEAVES_AGE = 200;
    public float leavesDamage;

    public LOTREntityMallornLeafBomb(World world) {
        super(world);
        this.setSize(2.0f, 2.0f);
        this.setPosition(this.posX, this.posY, this.posZ);
    }

    public LOTREntityMallornLeafBomb(World world, EntityLivingBase thrower) {
        super(world, thrower);
        this.setSize(2.0f, 2.0f);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.throwerUUID = thrower.getUniqueID();
    }

    public LOTREntityMallornLeafBomb(World world, EntityLivingBase thrower, EntityLivingBase target) {
        super(world, thrower);
        this.setSize(2.0f, 2.0f);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.throwerUUID = thrower.getUniqueID();
        this.posY = thrower.posY + (double)thrower.getEyeHeight() - 0.1;
        double dx = target.posX - thrower.posX;
        double dy = target.boundingBox.minY + (double)(target.height / 3.0f) - this.posY;
        double dz = target.posZ - thrower.posZ;
        double dxz = MathHelper.sqrt_double((double)(dx * dx + dz * dz));
        if (dxz >= 1.0E-7) {
            float f2 = (float)(Math.atan2(dz, dx) * 180.0 / 3.141592653589793) - 90.0f;
            float f3 = (float)(-(Math.atan2(dy, dxz) * 180.0 / 3.141592653589793));
            double d4 = dx / dxz;
            double d5 = dz / dxz;
            this.setLocationAndAngles(thrower.posX + d4, this.posY, thrower.posZ + d5, f2, f3);
            this.yOffset = 0.0f;
            this.setThrowableHeading(dx, dy, dz, this.func_70182_d(), 1.0f);
        }
    }

    public LOTREntityMallornLeafBomb(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
        this.setSize(2.0f, 2.0f);
        this.setPosition(this.posX, this.posY, this.posZ);
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("LeavesAge", this.leavesAge);
        nbt.setFloat("LeavesDamage", this.leavesDamage);
        if (this.throwerUUID != null) {
            nbt.setString("ThrowerUUID", this.throwerUUID.toString());
        }
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.leavesAge = nbt.getInteger("LeavesAge");
        this.leavesDamage = nbt.getFloat("LeavesDamage");
        if (nbt.hasKey("ThrowerUUID")) {
            this.throwerUUID = UUID.fromString(nbt.getString("ThrowerUUID"));
        }
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            ++this.leavesAge;
            if (this.leavesAge >= MAX_LEAVES_AGE) {
                this.explode(null);
            }
        } else {
            Vec3 axis = Vec3.createVectorHelper((double)(-this.motionX), (double)(-this.motionY), (double)(-this.motionZ));
            int leaves = 20;
            for (int l = 0; l < leaves; ++l) {
                float angle = (float)l / (float)leaves * 2.0f * 3.1415927f;
                Vec3 rotate = Vec3.createVectorHelper((double)1.0, (double)1.0, (double)1.0);
                rotate.rotateAroundX((float)Math.toRadians(40.0));
                rotate.rotateAroundY(angle);
                float dot = (float)rotate.dotProduct(axis);
                Vec3 parallel = Vec3.createVectorHelper((double)(axis.xCoord * (double)dot), (double)(axis.yCoord * (double)dot), (double)(axis.zCoord * (double)dot));
                Vec3 perp = parallel.subtract(rotate);
                Vec3 cross = rotate.crossProduct(axis);
                float sin = MathHelper.sin((float)(-angle));
                float cos = MathHelper.cos((float)(-angle));
                Vec3 crossSin = Vec3.createVectorHelper((double)(cross.xCoord * (double)sin), (double)(cross.yCoord * (double)sin), (double)(cross.zCoord * (double)sin));
                Vec3 perpCos = Vec3.createVectorHelper((double)(perp.xCoord * (double)cos), (double)(perp.yCoord * (double)cos), (double)(perp.zCoord * (double)cos));
                Vec3 result = parallel.addVector(crossSin.xCoord + perpCos.xCoord, crossSin.yCoord + perpCos.yCoord, crossSin.zCoord + perpCos.zCoord);
                double d = this.posX;
                double d1 = this.posY;
                double d2 = this.posZ;
                double d3 = result.xCoord / 10.0;
                double d4 = result.yCoord / 10.0;
                double d5 = result.zCoord / 10.0;
                LOTRMod.proxy.spawnParticle("leafGold_30", d, d1, d2, d3, d4, d5);
                LOTRMod.proxy.spawnParticle("mEntHeal_" + Block.getIdFromBlock((Block)LOTRMod.leaves) + "_" + 1, d, d1, d2, d3 * 0.5, d4 * 0.5, d5 * 0.5);
            }
        }
    }

    protected void onImpact(MovingObjectPosition m) {
        if (!this.worldObj.isRemote) {
            Entity entity;
            if (m.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                this.explode(null);
            } else if (m.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && this.isEntityVulnerable(entity = m.entityHit)) {
                this.explode(entity);
            }
        }
    }

    private void explode(Entity target) {
        if (!this.worldObj.isRemote) {
            double range = 2.0;
            List entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(range, range, range));
            if (!entities.isEmpty()) {
                for (int i = 0; i < entities.size(); ++i) {
                    float damage;
                    EntityLivingBase entity = (EntityLivingBase)entities.get(i);
                    if (!this.isEntityVulnerable((Entity)entity) || !((damage = this.leavesDamage / Math.max(1.0f, this.getDistanceToEntity((Entity)entity))) > 0.0f)) continue;
                    entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.getThrower()), damage);
                }
            }
            this.setDead();
        }
    }

    private boolean isEntityVulnerable(Entity target) {
        if (target == this.getThrower()) {
            return false;
        }
        if (target instanceof EntityLivingBase) {
            EntityLivingBase livingTarget = (EntityLivingBase)target;
            EntityLivingBase thrower = this.getThrower();
            if (thrower instanceof LOTREntityNPC) {
                ((LOTREntityNPC)thrower).getJumpHelper().doJump();
                return LOTRMod.canNPCAttackEntity((LOTREntityNPC)thrower, livingTarget, false);
            }
            return true;
        }
        return false;
    }

    public EntityLivingBase getThrower() {
        if (this.throwerUUID != null) {
            for (Object obj : this.worldObj.loadedEntityList) {
                Entity entity = (Entity)obj;
                if (!(entity instanceof EntityLivingBase) || !entity.getUniqueID().equals(this.throwerUUID)) continue;
                return (EntityLivingBase)entity;
            }
        }
        return null;
    }

    protected float func_70182_d() {
        return 1.0f;
    }

    protected float getGravityVelocity() {
        return 0.0f;
    }
}

