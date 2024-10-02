/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
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
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.entity.LOTRMountFunctions;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAIHiredRemainStill;
import lotr.common.entity.ai.LOTREntityAIUntamedPanic;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNPCRideable;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
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
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class LOTREntitySpiderBase
extends LOTREntityNPCRideable {
    public static int VENOM_NONE = 0;
    public static int VENOM_SLOWNESS = 1;
    public static int VENOM_POISON = 2;
    public static int VENOM_BLINDNESS = 3;
    public static int VENOM_NAUSEA = 4;
    public static int VENOM_WEAKNESS = 5;

    public LOTREntitySpiderBase(World world) {
        super(world);
        this.setSize(1.4f, 0.8f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIHiredRemainStill(this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.2, false));
        this.tasks.addTask(4, (EntityAIBase)new LOTREntityAIUntamedPanic(this, 1.2));
        this.tasks.addTask(5, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f, 0.02f));
        this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.addTargetTasks(true);
        this.spawnsInDarkness = true;
    }

    protected abstract int getRandomSpiderScale();

    protected abstract int getRandomSpiderType();

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, (Object)0);
        this.dataWatcher.addObject(21, (Object)0);
        this.dataWatcher.addObject(22, (Object)((byte)this.getRandomSpiderScale()));
        this.setSpiderType(this.getRandomSpiderType());
        this.dataWatcher.addObject(23, (Object)0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0 + (double)this.getSpiderScale() * 6.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35 - (double)this.getSpiderScale() * 0.03);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(2.0 + (double)this.getSpiderScale());
    }

    public boolean isSpiderClimbing() {
        return (this.dataWatcher.getWatchableObjectByte(20) & 1) != 0;
    }

    public void setSpiderClimbing(boolean flag) {
        byte b = this.dataWatcher.getWatchableObjectByte(20);
        b = flag ? (byte)(b | 1) : (byte)(b & 0xFFFFFFFE);
        this.dataWatcher.updateObject(20, (Object)b);
    }

    public int getSpiderType() {
        return this.dataWatcher.getWatchableObjectByte(21);
    }

    public void setSpiderType(int i) {
        this.dataWatcher.updateObject(21, (Object)((byte)i));
    }

    public int getSpiderScale() {
        return this.dataWatcher.getWatchableObjectByte(22);
    }

    public void setSpiderScale(int i) {
        this.dataWatcher.updateObject(22, (Object)((byte)i));
    }

    public float getSpiderScaleAmount() {
        return 0.5f + (float)this.getSpiderScale() / 2.0f;
    }

    public int getSpiderClimbTime() {
        return this.dataWatcher.getWatchableObjectShort(23);
    }

    public void setSpiderClimbTime(int i) {
        this.dataWatcher.updateObject(23, (Object)((short)i));
    }

    public boolean shouldRenderClimbingMeter() {
        return !this.onGround && this.getSpiderClimbTime() > 0;
    }

    public float getClimbFractionRemaining() {
        float f = (float)this.getSpiderClimbTime() / 100.0f;
        f = Math.min(f, 1.0f);
        f = 1.0f - f;
        return f;
    }

    @Override
    public boolean isMountSaddled() {
        return this.isNPCTamed() && this.riddenByEntity instanceof EntityPlayer;
    }

    @Override
    public boolean getBelongsToNPC() {
        return false;
    }

    @Override
    public void setBelongsToNPC(boolean flag) {
    }

    @Override
    public String getMountArmorTexture() {
        return null;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setByte("SpiderType", (byte)this.getSpiderType());
        nbt.setByte("SpiderScale", (byte)this.getSpiderScale());
        nbt.setShort("SpiderRideTime", (short)this.getSpiderClimbTime());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setSpiderType(nbt.getByte("SpiderType"));
        this.setSpiderScale(nbt.getByte("SpiderScale"));
        this.getEntityAttribute(npcAttackDamage).setBaseValue(2.0 + (double)this.getSpiderScale());
        this.setSpiderClimbTime(nbt.getShort("SpiderRideTime"));
    }

    @Override
    protected float getNPCScale() {
        return this.getSpiderScaleAmount();
    }

    public float getRenderSizeModifier() {
        return this.getSpiderScaleAmount();
    }

    protected boolean canRideSpider() {
        return this.getSpiderScale() > 0;
    }

    @Override
    protected double getBaseMountedYOffset() {
        return (double)this.height - 0.7;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            Entity rider = this.riddenByEntity;
            if (rider instanceof EntityPlayer && !this.onGround) {
                if (this.isCollidedHorizontally) {
                    this.setSpiderClimbTime(this.getSpiderClimbTime() + 1);
                }
            } else {
                this.setSpiderClimbTime(0);
            }
            if (this.getSpiderClimbTime() >= 100) {
                this.setSpiderClimbing(false);
                if (this.onGround) {
                    this.setSpiderClimbTime(0);
                }
            } else {
                this.setSpiderClimbing(this.isCollidedHorizontally);
            }
            if (rider instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)rider;
                if (LOTRBannerProtection.isProtected(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, LOTRBannerProtection.forPlayer(player, LOTRBannerProtection.Permission.FULL), true)) {
                    rider.mountEntity(null);
                    return;
                }
                if (LOTRLevelData.getData(player).getAlignment(this.getFaction()) < 50.0f) {
                    rider.mountEntity(null);
                }
            }
        }
    }

    @Override
    public boolean interact(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (this.getSpiderType() == VENOM_POISON && itemstack != null && itemstack.getItem() == Items.glass_bottle) {
            --itemstack.stackSize;
            if (itemstack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(LOTRMod.bottlePoison));
            } else if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(LOTRMod.bottlePoison)) && !entityplayer.capabilities.isCreativeMode) {
                entityplayer.dropPlayerItemWithRandomChoice(new ItemStack(LOTRMod.bottlePoison), false);
            }
            return true;
        }
        if (!this.worldObj.isRemote && LOTRBannerProtection.isProtected(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, LOTRBannerProtection.forPlayer(entityplayer, LOTRBannerProtection.Permission.FULL), true)) {
            return true;
        }
        if (this.worldObj.isRemote || this.hiredNPCInfo.isActive) {
            return false;
        }
        if (LOTRMountFunctions.interact(this, entityplayer)) {
            return true;
        }
        if (this.canRideSpider() && this.getAttackTarget() != entityplayer) {
            boolean hasRequiredAlignment = LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 50.0f;
            boolean notifyNotEnoughAlignment = false;
            if (!notifyNotEnoughAlignment && itemstack != null && LOTRMod.isOreNameEqual(itemstack, "bone") && this.isNPCTamed() && this.getHealth() < this.getMaxHealth()) {
                if (hasRequiredAlignment) {
                    if (!entityplayer.capabilities.isCreativeMode) {
                        --itemstack.stackSize;
                        if (itemstack.stackSize == 0) {
                            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                        }
                    }
                    this.heal(4.0f);
                    this.playSound(this.getLivingSound(), this.getSoundVolume(), this.getSoundPitch() * 1.5f);
                    return true;
                }
                notifyNotEnoughAlignment = true;
            }
            if (!notifyNotEnoughAlignment && this.riddenByEntity == null) {
                if (itemstack != null && itemstack.interactWithEntity(entityplayer, (EntityLivingBase)this)) {
                    return true;
                }
                if (hasRequiredAlignment) {
                    entityplayer.mountEntity((Entity)this);
                    this.setAttackTarget(null);
                    this.getNavigator().clearPathEntity();
                    return true;
                }
                notifyNotEnoughAlignment = true;
            }
            if (notifyNotEnoughAlignment) {
                LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, 50.0f, this.getFaction());
                return true;
            }
        }
        return super.interact(entityplayer);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            int difficulty;
            int duration;
            if (entity instanceof EntityLivingBase && (duration = (difficulty = this.worldObj.difficultySetting.getDifficultyId()) * (difficulty + 5) / 2) > 0) {
                if (this.getSpiderType() == VENOM_SLOWNESS && !((EntityLivingBase)entity).isPotionActive(LOTRPotions.frostResistance)) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration * 20, 0));
                } else if (this.getSpiderType() == VENOM_POISON) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, duration * 20, 0));
                } else if (this.getSpiderType() == VENOM_BLINDNESS) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.blindness.id, duration * 20, 0));
                } else if (this.getSpiderType() == VENOM_NAUSEA) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.confusion.id, duration * 20, 0));
                } else if (this.getSpiderType() == VENOM_WEAKNESS) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.weakness.id, duration * 20, 0));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        if (damagesource == DamageSource.fall) {
            return false;
        }
        return super.attackEntityFrom(damagesource, f);
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

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int string = this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int j = 0; j < string; ++j) {
            this.dropItem(Items.string, 1);
        }
        if (flag && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + i) > 0)) {
            this.dropItem(Items.spider_eye, 1);
        }
    }

    @Override
    public boolean canDropRares() {
        return false;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer entityplayer) {
        int i = this.getSpiderScale();
        return 2 + i + this.rand.nextInt(i + 2);
    }

    public boolean isOnLadder() {
        return this.isSpiderClimbing();
    }

    public void setInWeb() {
    }

    public void setInQuag() {
        super.setInWeb();
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean isPotionApplicable(PotionEffect effect) {
        if (this.getSpiderType() == VENOM_SLOWNESS && effect.getPotionID() == Potion.moveSlowdown.id) {
            return false;
        }
        if (this.getSpiderType() == VENOM_POISON && effect.getPotionID() == Potion.poison.id) {
            return false;
        }
        if (this.getSpiderType() == VENOM_BLINDNESS && effect.getPotionID() == Potion.blindness.id) {
            return false;
        }
        if (this.getSpiderType() == VENOM_NAUSEA && effect.getPotionID() == Potion.confusion.id) {
            return false;
        }
        if (this.getSpiderType() == VENOM_WEAKNESS && effect.getPotionID() == Potion.weakness.id) {
            return false;
        }
        return super.isPotionApplicable(effect);
    }

    @Override
    public boolean allowLeashing() {
        return this.isNPCTamed();
    }

    @Override
    public boolean canReEquipHired(int slot, ItemStack itemstack) {
        return false;
    }
}

