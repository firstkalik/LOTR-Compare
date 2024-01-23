/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTRMobSpawnerCondition;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityHaradPyramidWraith;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class LOTREntityScorpion
extends EntityMob
implements LOTRMobSpawnerCondition {
    private float scorpionWidth = -1.0f;
    private float scorpionHeight;
    protected boolean spawningFromSpawner = false;
    private static IEntitySelector noWraiths = new IEntitySelector(){

        public boolean isEntityApplicable(Entity entity) {
            return !(entity instanceof LOTREntityHaradPyramidWraith);
        }
    };

    public LOTREntityScorpion(World world) {
        super(world);
        this.setSize(1.2f, 0.9f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.2, false));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f, 0.05f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, LOTREntityNPC.class, 0, true, false, noWraiths));
    }

    @Override
    public void setSpawningFromMobSpawner(boolean flag) {
        this.spawningFromSpawner = flag;
    }

    protected int getRandomScorpionScale() {
        return this.rand.nextInt(3);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)((byte)this.getRandomScorpionScale()));
        this.dataWatcher.addObject(19, (Object)0);
    }

    public int getScorpionScale() {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setScorpionScale(int i) {
        this.dataWatcher.updateObject(18, (Object)((byte)i));
    }

    public float getScorpionScaleAmount() {
        return 0.5f + (float)this.getScorpionScale() / 2.0f;
    }

    public int getStrikeTime() {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    public void setStrikeTime(int i) {
        this.dataWatcher.updateObject(19, (Object)i);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0 + (double)this.getScorpionScale() * 6.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35 - (double)this.getScorpionScale() * 0.05);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0 + (double)this.getScorpionScale());
    }

    public boolean isAIEnabled() {
        return true;
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setByte("ScorpionScale", (byte)this.getScorpionScale());
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setScorpionScale(nbt.getByte("ScorpionScale"));
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0 + (double)this.getScorpionScale());
    }

    public float getBlockPathWeight(int i, int j, int k) {
        if (this.spawningFromSpawner) {
            return 0.0f;
        }
        return super.getBlockPathWeight(i, j, k);
    }

    public void onLivingUpdate() {
        int i;
        super.onLivingUpdate();
        this.rescaleScorpion(this.getScorpionScaleAmount());
        if (!this.worldObj.isRemote && (i = this.getStrikeTime()) > 0) {
            this.setStrikeTime(i - 1);
        }
    }

    protected void setSize(float f, float f1) {
        boolean flag = this.scorpionWidth > 0.0f;
        this.scorpionWidth = f;
        this.scorpionHeight = f1;
        if (!flag) {
            this.rescaleScorpion(1.0f);
        }
    }

    private void rescaleScorpion(float f) {
        super.setSize(this.scorpionWidth * f, this.scorpionHeight * f);
    }

    public boolean interact(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() == Items.glass_bottle) {
            --itemstack.stackSize;
            if (itemstack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(LOTRMod.bottlePoison));
            } else if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(LOTRMod.bottlePoison)) && !entityplayer.capabilities.isCreativeMode) {
                entityplayer.dropPlayerItemWithRandomChoice(new ItemStack(LOTRMod.bottlePoison), false);
            }
            return true;
        }
        return super.interact(entityplayer);
    }

    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            int difficulty;
            int duration;
            if (!this.worldObj.isRemote) {
                this.setStrikeTime(20);
            }
            if (entity instanceof EntityLivingBase && (duration = (difficulty = this.worldObj.difficultySetting.getDifficultyId()) * (difficulty + 5) / 2) > 0) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, duration * 20, 0));
            }
            return true;
        }
        return false;
    }

    protected String getLivingSound() {
        return "mob.spider.say";
    }

    protected String getHurtSound() {
        return "mob.spider.say";
    }

    protected String getDeathSound() {
        return "mob.spider.death";
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
        this.playSound("mob.spider.step", 0.15f, 1.0f);
    }

    protected void dropFewItems(boolean flag, int i) {
        int k = 1 + this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int j = 0; j < k; ++j) {
            this.dropItem(Items.rotten_flesh, 1);
        }
    }

    protected int getExperiencePoints(EntityPlayer entityplayer) {
        int i = this.getScorpionScale();
        return 2 + i + this.rand.nextInt(i + 2);
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean isPotionApplicable(PotionEffect effect) {
        if (effect.getPotionID() == Potion.poison.id) {
            return false;
        }
        return super.isPotionApplicable(effect);
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }

}

