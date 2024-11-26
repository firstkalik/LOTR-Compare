/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.animal;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.ai.LOTREntityAIAvoidWithChance;
import lotr.common.entity.ai.LOTREntityAIFlee;
import lotr.common.entity.ai.LOTREntityAIRabbitEatCrops;
import lotr.common.entity.animal.LOTRAmbientCreature;
import lotr.common.entity.animal.LOTRAmbientSpawnChecks;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFarmhand;
import lotr.common.world.biome.LOTRBiomeGenLastDesert;
import lotr.common.world.biome.LOTRBiomeGenNearHarad;
import lotr.common.world.biome.LOTRBiomeGenRedSemiDesert;
import lotr.common.world.biome.LOTRBiomeGenRhunSemiDesert;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityRabbit
extends EntityCreature
implements LOTRAmbientCreature,
LOTRRandomSkinEntity {
    private static final String fleeSound = "lotr:rabbit.flee";

    public LOTREntityRabbit(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIFlee(this, 2.0));
        this.tasks.addTask(2, (EntityAIBase)new LOTREntityAIAvoidWithChance(this, EntityPlayer.class, 4.0f, 1.3, 1.5, 0.05f, fleeSound));
        this.tasks.addTask(2, (EntityAIBase)new LOTREntityAIAvoidWithChance(this, LOTREntityNPC.class, 4.0f, 1.3, 1.5, 0.05f, fleeSound));
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIRabbitEatCrops(this, 1.2));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLivingBase.class, 8.0f, 0.05f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(17, (Object)0);
        this.dataWatcher.addObject(18, (Object)0);
    }

    public boolean isRabbitEating() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setRabbitEating(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)(flag ? (byte)1 : 0));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
    }

    @Override
    public void setUniqueID(UUID uuid) {
        this.entityUniqueID = uuid;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && !this.worldObj.isRemote && damagesource.getEntity() instanceof EntityPlayer && this.isRabbitEating()) {
            EntityPlayer entityplayer = (EntityPlayer)damagesource.getEntity();
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.attackRabbit);
        }
        return flag;
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        int k;
        data = super.onSpawnWithEgg(data);
        int i = MathHelper.floor_double((double)this.posX);
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k = MathHelper.floor_double((double)this.posZ));
        if (biome instanceof LOTRBiomeGenLastDesert || biome instanceof LOTRBiomeGenRhunSemiDesert || biome instanceof LOTRBiomeGenRedSemiDesert || biome instanceof LOTRBiomeGenNearHarad) {
            this.setRabbitType(RabbitType.DESERT);
        } else {
            this.setRabbitType(RabbitType.COMMON);
        }
        return data;
    }

    public void dropFewItems(boolean flag, int i) {
        int meat = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < meat; ++l) {
            if (this.isBurning()) {
                this.dropItem(LOTRMod.rabbitCooked, 1);
                continue;
            }
            this.dropItem(LOTRMod.rabbitRaw, 1);
        }
    }

    protected boolean canDespawn() {
        return true;
    }

    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            boolean flag = LOTRAmbientSpawnChecks.canSpawn((EntityLiving)this, 8, 4, 32, 4, Material.plants, Material.vine);
            if (flag) {
                int j;
                int k;
                int i = MathHelper.floor_double((double)this.posX);
                Block blockBelow = this.worldObj.getBlock(i, (j = MathHelper.floor_double((double)this.posY)) - 1, k = MathHelper.floor_double((double)this.posZ));
                return (blockBelow == Blocks.grass || blockBelow == Blocks.sand) && !this.anyFarmhandsNearby(i, j, k);
            }
        }
        return false;
    }

    public boolean anyFarmhandsNearby(int i, int j, int k) {
        int range = 16;
        List farmhands = this.worldObj.getEntitiesWithinAABB(LOTRFarmhand.class, AxisAlignedBB.getBoundingBox((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1)).expand((double)range, (double)range, (double)range));
        return !farmhands.isEmpty();
    }

    public float getBlockPathWeight(int i, int j, int k) {
        Block block = this.worldObj.getBlock(i, j - 1, k);
        if (block == Blocks.grass) {
            return 10.0f;
        }
        return this.worldObj.getLightBrightness(i, j, k) - 0.5f;
    }

    protected int getExperiencePoints(EntityPlayer entityplayer) {
        return 1 + this.rand.nextInt(2);
    }

    protected String getHurtSound() {
        return "lotr:rabbit.hurt";
    }

    protected String getDeathSound() {
        return "lotr:rabbit.death";
    }

    public int getTalkInterval() {
        return 200;
    }

    public RabbitType getRabbitType() {
        byte i = this.dataWatcher.getWatchableObjectByte(18);
        if (i < 0 || i >= RabbitType.values().length) {
            i = 0;
        }
        return RabbitType.values()[i];
    }

    public void setRabbitType(RabbitType type) {
        this.setRabbitType(type.ordinal());
    }

    public void setRabbitType(int i) {
        this.dataWatcher.updateObject(18, (Object)((byte)i));
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setRabbitType(nbt.getInteger("RabbitType"));
        this.setRabbitEating(nbt.getBoolean("RabbitEating"));
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("RabbitType", this.getRabbitType().ordinal());
        nbt.setBoolean("RabbitEating", this.isRabbitEating());
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }

    public static enum RabbitType {
        COMMON("common"),
        DESERT("desert");

        public String textureDir;

        private RabbitType(String s) {
            this.textureDir = s;
        }
    }

}

