/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
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
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIFlee;
import lotr.common.entity.animal.LOTRAmbientCreature;
import lotr.common.entity.animal.LOTRAmbientSpawnChecks;
import lotr.common.entity.animal.LOTREntityMidges;
import lotr.common.world.biome.LOTRBiomeGenFarHaradJungle;
import lotr.common.world.biome.LOTRBiomeGenFarHaradMangrove;
import lotr.common.world.biome.LOTRBiomeGenFarHaradSwamp;
import lotr.common.world.biome.LOTRBiomeGenForodwaith;
import lotr.common.world.biome.LOTRBiomeGenLastDesert;
import lotr.common.world.biome.LOTRBiomeGenNearHarad;
import lotr.common.world.biome.LOTRBiomeGenRedSemiDesert;
import lotr.common.world.biome.LOTRBiomeGenRhunSemiDesert;
import lotr.common.world.biome.LOTRBiomeGenTaiga;
import lotr.common.world.biome.LOTRBiomeGenTaiga2;
import lotr.common.world.biome.LOTRBiomeGenTaigaRhun;
import lotr.common.world.biome.LOTRBiomeGenTundra;
import lotr.common.world.biome.LOTRBiomeGenTundra2;
import lotr.common.world.biome.LOTRBiomeGenTundra3;
import lotr.common.world.biome.LOTRBiomeGenTundra4;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityFrog
extends EntityAnimal
implements LOTRAmbientCreature,
LOTRRandomSkinEntity,
LOTRBiomeGenNearHarad.ImmuneToFrost,
LOTRBiomeGenNearHarad.ImmuneToHeat {
    public IEntitySelector frogAttackRange = new IEntitySelector(){

        public boolean isEntityApplicable(Entity entity) {
            return entity instanceof EntityLivingBase && entity.isEntityAlive() && LOTREntityFrog.this.getDistanceSqToEntity(entity) < 16.0;
        }
    };
    public EntityAIBase attackAI = new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.4, true);
    public EntityAIBase panicAI = new EntityAIPanic((EntityCreature)this, 1.5);

    public LOTREntityFrog(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(0, this.panicAI);
        this.tasks.addTask(0, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 2.0));
        this.tasks.addTask(1, this.attackAI);
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIFlee((EntityCreature)this, 2.0));
        this.tasks.addTask(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.4, Items.slime_ball, false));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.2));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 15.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, LOTREntityMidges.class, 0, true, false, this.frogAttackRange));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
    }

    public boolean attackEntityAsMob(Entity entity) {
        if (!(entity instanceof LOTREntityMidges)) {
            return false;
        }
        float damage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        boolean success = entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), damage);
        if (success) {
            float knockback = 0.75f;
            entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback * 0.5f), 0.0, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback * 0.5f));
        }
        return success;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        boolean success = super.attackEntityFrom(damagesource, f);
        if (success) {
            Entity attacker;
            if (this.isChild() && (attacker = damagesource.getEntity()) instanceof EntityLivingBase) {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(12.0, 12.0, 12.0));
                for (Entity entity : list) {
                    LOTREntityFrog frog;
                    if (entity.getClass() != this.getClass() || (frog = (LOTREntityFrog)entity).isChild()) continue;
                    frog.setAttackTarget((EntityLivingBase)attacker);
                }
            }
            if (this.getFrogType() == FrogType.DESERT && damagesource.getEntity() instanceof EntityLivingBase) {
                ((EntityLivingBase)damagesource.getEntity()).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1));
            }
        }
        return success;
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        LOTREntityFrog mate = (LOTREntityFrog)entity;
        LOTREntityFrog child = new LOTREntityFrog(this.worldObj);
        if (this.rand.nextBoolean()) {
            child.setFrogType(this.getFrogType());
        } else {
            child.setFrogType(mate.getFrogType());
        }
        return child;
    }

    protected void dropFewItems(boolean flag, int i) {
        if (flag && this.rand.nextInt(3) == 0) {
            this.dropItem(Items.slime_ball, 1);
        }
    }

    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack.getItem() == Items.chicken;
    }

    public boolean canDespawn() {
        return true;
    }

    public float getBlockPathWeight(int i, int j, int k) {
        Block block = this.worldObj.getBlock(i, j - 1, k);
        if (block == Blocks.grass) {
            return 10.0f;
        }
        return this.worldObj.getLightBrightness(i, j, k) - 0.5f;
    }

    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            return LOTRAmbientSpawnChecks.canSpawn((EntityLiving)this, 8, 4, 32, 4, Material.plants, Material.vine);
        }
        return false;
    }

    protected String getDeathSound() {
        return "lotr:frog.death";
    }

    protected String getHurtSound() {
        return "lotr:frog.hurt";
    }

    protected String getLivingSound() {
        return "lotr:frog.say";
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
        this.playSound("lotr:frog.step", 0.75f, this.getSoundPitch());
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }

    public float getSoundPitch() {
        return super.getSoundPitch() * 1.3f;
    }

    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public void setUniqueID(UUID uuid) {
        this.entityUniqueID = uuid;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
        this.dataWatcher.addObject(17, (Object)1);
    }

    public String getFrogTextureDir() {
        return this.getFrogType().textureDir;
    }

    public FrogType getFrogType() {
        byte i = this.dataWatcher.getWatchableObjectByte(16);
        if (i < 0 || i >= FrogType.values().length) {
            i = 0;
        }
        return FrogType.values()[i];
    }

    public void setFrogType(FrogType type) {
        this.setFrogType(type.ordinal());
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        int k;
        data = super.onSpawnWithEgg(data);
        int i = MathHelper.floor_double((double)this.posX);
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k = MathHelper.floor_double((double)this.posZ));
        if (biome instanceof LOTRBiomeGenTundra || biome instanceof LOTRBiomeGenTaiga || biome instanceof LOTRBiomeGenTaiga2 || biome instanceof LOTRBiomeGenTaigaRhun || biome instanceof LOTRBiomeGenTundra2 || biome instanceof LOTRBiomeGenTundra3 || biome instanceof LOTRBiomeGenTundra4 || biome instanceof LOTRBiomeGenForodwaith) {
            this.setFrogType(FrogType.SNOW);
        } else if (biome instanceof LOTRBiomeGenLastDesert || biome instanceof LOTRBiomeGenRhunSemiDesert || biome instanceof LOTRBiomeGenRedSemiDesert || biome instanceof LOTRBiomeGenNearHarad || biome instanceof LOTRBiomeGenFarHaradJungle || biome instanceof LOTRBiomeGenFarHaradMangrove || biome instanceof LOTRBiomeGenFarHaradSwamp) {
            this.setFrogType(FrogType.DESERT);
        } else {
            this.setFrogType(FrogType.COMMON);
        }
        return data;
    }

    public void setFrogType(int i) {
        this.dataWatcher.updateObject(16, (Object)((byte)i));
    }

    public boolean isFrogStill() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setFrogStill(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)((byte)(flag ? 1 : 0)));
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setFrogType(nbt.getInteger("FrogType"));
        this.setFrogStill(nbt.getBoolean("FrogStill"));
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("FrogType", this.getFrogType().ordinal());
        nbt.setBoolean("FrogStill", this.isFrogStill());
    }

    public static enum FrogType {
        COMMON("common", true),
        DESERT("desert", true),
        SNOW("snow", true);

        public boolean canSteal;
        public String textureDir;

        private FrogType(String s, boolean flag) {
            this.textureDir = s;
            this.canSteal = flag;
        }
    }

}

