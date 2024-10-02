/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIFollowParent
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMate
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
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
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
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIAvoidWithChance;
import lotr.common.entity.animal.LOTRAmbientCreature;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityPolarBear;
import lotr.common.entity.animal.LOTREntityRabbit;
import lotr.common.entity.npc.LOTREntityMan;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.world.biome.LOTRBiomeGenForodwaith;
import lotr.common.world.biome.LOTRBiomeGenGondor;
import lotr.common.world.biome.LOTRBiomeGenIthilien;
import lotr.common.world.biome.LOTRBiomeGenLastDesert;
import lotr.common.world.biome.LOTRBiomeGenNearHarad;
import lotr.common.world.biome.LOTRBiomeGenPukel;
import lotr.common.world.biome.LOTRBiomeGenRedSemiDesert;
import lotr.common.world.biome.LOTRBiomeGenRhunSemiDesert;
import lotr.common.world.biome.LOTRBiomeGenRohan;
import lotr.common.world.biome.LOTRBiomeGenTaiga;
import lotr.common.world.biome.LOTRBiomeGenTaiga2;
import lotr.common.world.biome.LOTRBiomeGenTaigaRhun;
import lotr.common.world.biome.LOTRBiomeGenTundra;
import lotr.common.world.biome.LOTRBiomeGenTundra2;
import lotr.common.world.biome.LOTRBiomeGenTundra3;
import lotr.common.world.biome.LOTRBiomeGenTundra4;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
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
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

public class LOTREntityFox
extends EntityAnimal
implements LOTRAmbientCreature,
LOTRRandomSkinEntity,
LOTRBiomeGenNearHarad.ImmuneToFrost {
    public IEntitySelector foxAttackRange = new IEntitySelector(){

        public boolean isEntityApplicable(Entity entity) {
            return entity instanceof EntityLivingBase && entity.isEntityAlive() && LOTREntityFox.this.getDistanceSqToEntity(entity) < 16.0;
        }
    };
    public EntityAIBase attackAI = new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.4, true);
    public EntityAIBase panicAI = new EntityAIPanic((EntityCreature)this, 1.5);
    public static boolean wreckAnimals;

    public LOTREntityFox(World world) {
        super(world);
        this.setSize(0.6f, 1.0f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(0, this.panicAI);
        this.tasks.addTask(0, (EntityAIBase)new LOTREntityAIAvoidWithChance((EntityCreature)this, EntityPlayer.class, 12.0f, 1.5, 2.0, 0.1f));
        this.tasks.addTask(0, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 2.0));
        this.tasks.addTask(1, this.attackAI);
        this.tasks.addTask(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityWolf.class, 12.0f, 1.5, 2.0));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, LOTREntityBear.class, 12.0f, 1.5, 2.0));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, LOTREntityWarg.class, 12.0f, 1.5, 2.0));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, LOTREntityPolarBear.class, 12.0f, 1.5, 2.0));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.4));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIMate((EntityAnimal)this, 1.0));
        this.tasks.addTask(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.4, Items.chicken, false));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.2));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 15.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(0, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, LOTREntityRabbit.class, 0, true, false, this.foxAttackRange));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityChicken.class, 0, true, false, this.foxAttackRange));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.5);
    }

    public boolean attackEntityAsMob(Entity entity) {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), f);
        if (flag) {
            float kb = 0.75f;
            entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * kb * 0.5f), 0.0, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * kb * 0.5f));
        }
        return flag;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        Entity attacker;
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && this.isChild() && (attacker = damagesource.getEntity()) instanceof EntityLivingBase) {
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(12.0, 12.0, 12.0));
            for (Entity entity : list) {
                LOTREntityFox fox;
                if (entity.getClass() != this.getClass() || (fox = (LOTREntityFox)entity).isChild()) continue;
                fox.setAttackTarget((EntityLivingBase)attacker);
            }
        }
        return flag;
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        LOTREntityFox mate = (LOTREntityFox)entity;
        LOTREntityFox child = new LOTREntityFox(this.worldObj);
        if (this.rand.nextBoolean()) {
            child.setFoxType(this.getFoxType());
        } else {
            child.setFoxType(mate.getFoxType());
        }
        return child;
    }

    protected void dropFewItems(boolean flag, int i) {
        int meat = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < meat; ++l) {
            this.dropItem(Items.leather, 1);
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

    protected String getDeathSound() {
        return "lotr:fox.death";
    }

    protected String getHurtSound() {
        return "lotr:fox.hurt";
    }

    protected String getLivingSound() {
        return "lotr:fox.say";
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

    public String getFoxTextureDir() {
        return this.getFoxType().textureDir;
    }

    public FoxType getFoxType() {
        byte i = this.dataWatcher.getWatchableObjectByte(16);
        if (i < 0 || i >= FoxType.values().length) {
            i = 0;
        }
        return FoxType.values()[i];
    }

    public void setFoxType(FoxType type) {
        this.setFoxType(type.ordinal());
    }

    public void setFoxType(int i) {
        this.dataWatcher.updateObject(16, (Object)((byte)i));
    }

    public boolean isFoxStill() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setFoxStill(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)((byte)(flag ? 1 : 0)));
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        int k;
        data = super.onSpawnWithEgg(data);
        int i = MathHelper.floor_double((double)this.posX);
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k = MathHelper.floor_double((double)this.posZ));
        if (biome instanceof LOTRBiomeGenTundra || biome instanceof LOTRBiomeGenTaiga || biome instanceof LOTRBiomeGenTaiga2 || biome instanceof LOTRBiomeGenTaigaRhun || biome instanceof LOTRBiomeGenTundra2 || biome instanceof LOTRBiomeGenTundra4 || biome instanceof LOTRBiomeGenTundra3 || biome instanceof LOTRBiomeGenForodwaith) {
            this.setFoxType(FoxType.SNOW);
        } else if (biome instanceof LOTRBiomeGenLastDesert || biome instanceof LOTRBiomeGenRhunSemiDesert || biome instanceof LOTRBiomeGenRedSemiDesert || biome instanceof LOTRBiomeGenNearHarad) {
            this.setFoxType(FoxType.DESERT);
        } else if (biome instanceof LOTRBiomeGenGondor || biome instanceof LOTRBiomeGenIthilien || biome instanceof LOTRBiomeGenPukel || biome instanceof LOTRBiomeGenRohan) {
            this.setFoxType(FoxType.MIDDLE);
        } else {
            this.setFoxType(FoxType.COMMON);
        }
        return data;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.getFoxType() == FoxType.SNOW && this.getAttackTarget() == null) {
            List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(16.0, 8.0, 16.0));
            for (Object player : players) {
                if (!(player instanceof EntityPlayer) || player.capabilities.isCreativeMode) continue;
                this.setAttackTarget((EntityLivingBase)player);
                return;
            }
            List entities = this.worldObj.getEntitiesWithinAABB(LOTREntityMan.class, this.boundingBox.expand(16.0, 8.0, 16.0));
            for (Entity entity : entities) {
                if (!(entity instanceof LOTREntityMan)) continue;
                this.setAttackTarget((EntityLivingBase)entity);
                return;
            }
        }
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setFoxType(nbt.getInteger("FoxType"));
        this.setFoxStill(nbt.getBoolean("FoxStill"));
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("FoxType", this.getFoxType().ordinal());
        nbt.setBoolean("FoxStill", this.isFoxStill());
    }

    public static enum FoxType {
        COMMON("common", true),
        DESERT("desert", true),
        MIDDLE("middle", true),
        SNOW("snow", true);

        public boolean canSteal;
        public String textureDir;

        private FoxType(String s, boolean flag) {
            this.textureDir = s;
            this.canSteal = flag;
        }
    }

}

