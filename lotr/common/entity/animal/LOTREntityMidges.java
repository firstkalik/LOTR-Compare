/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.animal;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.animal.LOTRAmbientCreature;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.world.biome.LOTRBiomeGenMidgewater;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityMidges
extends EntityLiving
implements LOTRAmbientCreature {
    private ChunkCoordinates currentFlightTarget;
    private EntityPlayer playerTarget;
    public Midge[] midges;

    public LOTREntityMidges(World world) {
        super(world);
        this.setSize(2.0f, 2.0f);
        this.renderDistanceWeight = 0.5;
        this.midges = new Midge[3 + this.rand.nextInt(6)];
        for (int l = 0; l < this.midges.length; ++l) {
            this.midges[l] = new Midge();
        }
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0);
    }

    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity entity) {
    }

    protected void collideWithNearbyEntities() {
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void onUpdate() {
        super.onUpdate();
        this.motionY *= 0.6;
        for (Midge midge : this.midges) {
            midge.update();
        }
        if (this.rand.nextInt(5) == 0) {
            this.playSound("lotr:midges.swarm", this.getSoundVolume(), this.getSoundPitch());
        }
        if (!this.worldObj.isRemote && this.isEntityAlive()) {
            int chance;
            boolean inMidgewater = this.worldObj.getBiomeGenForCoords(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.posZ)) instanceof LOTRBiomeGenMidgewater;
            int n = chance = inMidgewater ? 100 : 500;
            if (this.rand.nextInt(chance) == 0) {
                double range = inMidgewater ? 16.0 : 24.0;
                int threshold = inMidgewater ? 6 : 5;
                List list = this.worldObj.getEntitiesWithinAABB(LOTREntityMidges.class, this.boundingBox.expand(range, range, range));
                if (list.size() < threshold) {
                    LOTREntityMidges moreMidges = new LOTREntityMidges(this.worldObj);
                    moreMidges.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rand.nextFloat() * 360.0f, 0.0f);
                    moreMidges.onSpawnWithEgg(null);
                    this.worldObj.spawnEntityInWorld((Entity)moreMidges);
                }
            }
        }
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.currentFlightTarget != null && !this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ)) {
            this.currentFlightTarget = null;
        }
        if (this.playerTarget != null && (!this.playerTarget.isEntityAlive() || this.getDistanceSqToEntity((Entity)this.playerTarget) > 256.0)) {
            this.playerTarget = null;
        }
        if (this.playerTarget != null) {
            if (this.rand.nextInt(400) == 0) {
                this.playerTarget = null;
            } else {
                this.currentFlightTarget = new ChunkCoordinates((int)this.playerTarget.posX, (int)this.playerTarget.posY + 3, (int)this.playerTarget.posZ);
            }
        } else if (this.rand.nextInt(100) == 0) {
            EntityPlayer closestPlayer = this.worldObj.getClosestPlayerToEntity((Entity)this, 12.0);
            if (closestPlayer != null && this.rand.nextInt(7) == 0) {
                this.playerTarget = closestPlayer;
            } else {
                int height;
                int i = (int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7);
                int j = (int)this.posY + this.rand.nextInt(4) - this.rand.nextInt(3);
                int k = (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7);
                if (j < 1) {
                    j = 1;
                }
                if (j > (height = this.worldObj.getTopSolidOrLiquidBlock(i, k)) + 8) {
                    j = height + 8;
                }
                this.currentFlightTarget = new ChunkCoordinates(i, j, k);
            }
        }
        if (this.currentFlightTarget != null) {
            double dx = (double)this.currentFlightTarget.posX + 0.5 - this.posX;
            double dy = (double)this.currentFlightTarget.posY + 0.5 - this.posY;
            double dz = (double)this.currentFlightTarget.posZ + 0.5 - this.posZ;
            this.motionX += (Math.signum(dx) * 0.5 - this.motionX) * 0.1;
            this.motionY += (Math.signum(dy) * 0.7 - this.motionY) * 0.1;
            this.motionZ += (Math.signum(dz) * 0.5 - this.motionZ) * 0.1;
            this.moveForward = 0.2f;
        } else {
            this.motionZ = 0.0;
            this.motionY = 0.0;
            this.motionX = 0.0;
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void fall(float f) {
    }

    protected void updateFallState(double d, boolean flag) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    public void onDeath(DamageSource damagesource) {
        Entity attacker;
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote && damagesource instanceof EntityDamageSourceIndirect && (attacker = damagesource.getEntity()) instanceof LOTREntityNPC) {
            LOTREntityNPC npc = (LOTREntityNPC)attacker;
            if (npc.hiredNPCInfo.isActive && npc.hiredNPCInfo.getHiringPlayer() != null) {
                EntityPlayer entityplayer = npc.hiredNPCInfo.getHiringPlayer();
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.shootDownMidges);
            }
        }
    }

    protected boolean canDespawn() {
        return true;
    }

    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.posY);
        int k = MathHelper.floor_double((double)this.posZ);
        if (j < 62) {
            return false;
        }
        return this.worldObj.getBlock(i, j - 1, k) == this.worldObj.getBiomeGenForCoords((int)i, (int)k).topBlock && super.getCanSpawnHere();
    }

    public boolean allowLeashing() {
        return false;
    }

    protected boolean interact(EntityPlayer entityplayer) {
        return false;
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        int id = LOTREntities.getEntityID((Entity)this);
        if (id > 0 && LOTREntities.spawnEggs.containsKey(id)) {
            return new ItemStack(LOTRMod.spawnEgg, 1, id);
        }
        return null;
    }

    public class Midge {
        public float midge_posX;
        public float midge_posY;
        public float midge_posZ;
        public float midge_prevPosX;
        public float midge_prevPosY;
        public float midge_prevPosZ;
        private float midge_initialPosX;
        private float midge_initialPosY;
        private float midge_initialPosZ;
        public float midge_rotation;
        private int midgeTick;
        private int maxMidgeTick = 80;

        public Midge() {
            this.midge_initialPosX = this.midge_posX = -1.0f + LOTREntityMidges.this.rand.nextFloat() * 2.0f;
            this.midge_initialPosY = this.midge_posY = LOTREntityMidges.this.rand.nextFloat() * 2.0f;
            this.midge_initialPosZ = this.midge_posZ = -1.0f + LOTREntityMidges.this.rand.nextFloat() * 2.0f;
            this.midge_rotation = LOTREntityMidges.this.rand.nextFloat() * 360.0f;
            this.midgeTick = LOTREntityMidges.this.rand.nextInt(this.maxMidgeTick);
        }

        public void update() {
            this.midge_prevPosX = this.midge_posX;
            this.midge_prevPosY = this.midge_posY;
            this.midge_prevPosZ = this.midge_posZ;
            ++this.midgeTick;
            if (this.midgeTick > this.maxMidgeTick) {
                this.midgeTick = 0;
            }
            this.midge_posY = this.midge_initialPosY + 0.5f * MathHelper.sin((float)((float)this.midgeTick / 6.2831855f));
        }
    }

}

