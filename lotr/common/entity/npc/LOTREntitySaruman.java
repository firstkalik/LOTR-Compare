/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S27PacketExplosion
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIEat;
import lotr.common.entity.animal.LOTREntityRabbit;
import lotr.common.entity.npc.LOTREntityGandalf;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTREntitySaruman
extends LOTREntityNPC {
    private LOTREntityRabbit targetingRabbit;
    private int ticksChasingRabbit;
    private String randomNameTag;

    public LOTREntitySaruman(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIEat(this, new LOTRFoods(new ItemStack[]{new ItemStack(Blocks.log)}), 200));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLivingBase.class, 20.0f, 0.05f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.setCurrentItemOrArmor(0, new ItemStack(LOTRMod.gandalfStaffWhite));
        return data;
    }

    public String getCustomNameTag() {
        if (this.randomNameTag == null) {
            StringBuilder tmp = new StringBuilder();
            for (int l = 0; l < 100; ++l) {
                tmp.append((char)this.rand.nextInt(1000));
            }
            this.randomNameTag = tmp.toString();
        }
        return this.randomNameTag;
    }

    public boolean hasCustomNameTag() {
        return true;
    }

    public boolean getAlwaysRenderNameTag() {
        return true;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ISENGARD;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            List rabbits;
            if (this.rand.nextInt(10) == 0) {
                this.playSound(this.getLivingSound(), this.getSoundVolume(), this.getSoundPitch());
            }
            List allMobsExcludingRabbits = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(24.0, 24.0, 24.0));
            for (int i = 0; i < allMobsExcludingRabbits.size(); ++i) {
                Entity entity = (Entity)allMobsExcludingRabbits.get(i);
                if (entity instanceof LOTREntityRabbit || entity instanceof LOTREntityGandalf) continue;
                double dSq = this.getDistanceSqToEntity(entity);
                if (dSq <= 0.0) {
                    dSq = 1.0E-5;
                }
                float strength = 1.0f;
                if (entity instanceof EntityPlayer) {
                    strength /= 3.0f;
                }
                double force = (double)strength / dSq;
                double x = entity.posX - this.posX;
                double y = entity.posY - this.posY;
                double z = entity.posZ - this.posZ;
                x *= force;
                y *= force;
                z *= force;
                if (entity instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)entity).playerNetServerHandler.sendPacket((Packet)new S27PacketExplosion(this.posX, this.posY, this.posZ, 0.0f, new ArrayList(), Vec3.createVectorHelper((double)x, (double)y, (double)z)));
                    continue;
                }
                entity.motionX += x;
                entity.motionY += y;
                entity.motionZ += z;
            }
            if (this.rand.nextInt(40) == 0) {
                LOTREntityRabbit rabbit = new LOTREntityRabbit(this.worldObj);
                int i = MathHelper.floor_double((double)this.posX) - this.rand.nextInt(16) + this.rand.nextInt(16);
                int j = MathHelper.floor_double((double)this.boundingBox.minY) - this.rand.nextInt(8) + this.rand.nextInt(8);
                int k = MathHelper.floor_double((double)this.posZ) - this.rand.nextInt(16) + this.rand.nextInt(16);
                rabbit.setLocationAndAngles((double)i, (double)j, (double)k, 0.0f, 0.0f);
                AxisAlignedBB aabb = rabbit.boundingBox;
                if (this.worldObj.checkNoEntityCollision(aabb) && this.worldObj.getCollidingBoundingBoxes((Entity)rabbit, aabb).isEmpty() && !this.worldObj.isAnyLiquid(aabb)) {
                    this.worldObj.spawnEntityInWorld((Entity)rabbit);
                }
            }
            if (this.targetingRabbit == null && this.rand.nextInt(20) == 0 && !(rabbits = this.worldObj.getEntitiesWithinAABB(LOTREntityRabbit.class, this.boundingBox.expand(24.0, 24.0, 24.0))).isEmpty()) {
                LOTREntityRabbit rabbit = (LOTREntityRabbit)rabbits.get(this.rand.nextInt(rabbits.size()));
                if (rabbit.ridingEntity == null) {
                    this.targetingRabbit = rabbit;
                }
            }
            if (this.targetingRabbit != null) {
                if (!this.targetingRabbit.isEntityAlive()) {
                    this.targetingRabbit = null;
                } else {
                    this.getNavigator().tryMoveToEntityLiving((Entity)this.targetingRabbit, 1.0);
                    if ((double)this.getDistanceToEntity((Entity)this.targetingRabbit) < 1.0) {
                        LOTREntitySaruman entityToMount = this;
                        while (((Entity)entityToMount).riddenByEntity != null) {
                            entityToMount = ((Entity)entityToMount).riddenByEntity;
                        }
                        this.targetingRabbit.mountEntity((Entity)entityToMount);
                        this.targetingRabbit = null;
                    }
                }
            }
        }
    }

    protected String getLivingSound() {
        return "lotr:orc.say";
    }

    @Override
    public int getTalkInterval() {
        return 10;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int j = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int k = 0; k < j; ++k) {
            this.dropItem(Items.bone, 1);
        }
    }
}

