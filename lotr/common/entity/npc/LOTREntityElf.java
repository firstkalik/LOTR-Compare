/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.LOTRPatron;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIDrink;
import lotr.common.entity.ai.LOTREntityAIEat;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAIHiredRemainStill;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNames;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class LOTREntityElf
extends LOTREntityNPC {
    protected EntityAIBase rangedAttackAI = this.createElfRangedAttackAI();
    protected EntityAIBase meleeAttackAI = this.createElfMeleeAttackAI();
    private int soloTick;
    private float soloSpinSpeed;
    private float soloSpin;
    private float prevSoloSpin;
    private static final int bowTickMax = 40;
    private float bowAmount;
    private float prevBowAmount;
    private static final float bowStep = 0.2f;

    public LOTREntityElf(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIHiredRemainStill(this));
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAIEat(this, LOTRFoods.ELF, 12000));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAIDrink(this, this.getElfDrinks(), 8000));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 8.0f, 0.02f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 5.0f, 0.02f));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0f, 0.02f));
        this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.addTargetTasks(true);
    }

    protected LOTRFoods getElfDrinks() {
        return LOTRFoods.ELF_DRINK;
    }

    protected EntityAIBase createElfRangedAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 40, 60, 16.0f);
    }

    protected EntityAIBase createElfMeleeAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.5, false);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(22, (Object)0);
        this.dataWatcher.addObject(23, (Object)0);
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(this.rand.nextBoolean());
    }

    @Override
    public void setupNPCName() {
        this.familyInfo.setName(LOTRNames.getSindarinOrQuenyaName(this.rand, this.familyInfo.isMale()));
    }

    @Override
    public String getNPCName() {
        return this.familyInfo.getName();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.5);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("BoopBoopBaDoop", this.isJazz());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setJazz(nbt.getBoolean("BoopBoopBaDoop"));
    }

    @Override
    public String getEntityClassName() {
        if (this.isJazz()) {
            return "Jazz-elf";
        }
        return super.getEntityClassName();
    }

    private boolean getJazzFlag(int i) {
        int pow2;
        byte b = this.dataWatcher.getWatchableObjectByte(22);
        return (b & (pow2 = 1 << i)) != 0;
    }

    private void setJazzFlag(int i, boolean flag) {
        byte b = this.dataWatcher.getWatchableObjectByte(22);
        int pow2 = 1 << i;
        b = flag ? (byte)(b | pow2) : (byte)(b & ~pow2);
        this.dataWatcher.updateObject(22, (Object)b);
    }

    public boolean isJazz() {
        return this.getJazzFlag(0);
    }

    public void setJazz(boolean flag) {
        this.setJazzFlag(0, flag);
    }

    public boolean isSolo() {
        return this.getJazzFlag(1);
    }

    public void setSolo(boolean flag) {
        this.setJazzFlag(1, flag);
    }

    private int getBowingTick() {
        return this.dataWatcher.getWatchableObjectShort(23);
    }

    private void setBowingTick(int i) {
        this.dataWatcher.updateObject(23, (Object)((short)i));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.isJazz()) {
            if (!this.worldObj.isRemote) {
                if (this.soloTick > 0) {
                    --this.soloTick;
                    this.rotationPitch = -10.0f + (MathHelper.sin((float)((float)this.soloTick * 0.3f)) + 1.0f) / 2.0f * -30.0f;
                } else if (this.rand.nextInt(200) == 0) {
                    this.soloTick = 60 + this.rand.nextInt(300);
                }
                this.setSolo(this.soloTick > 0);
            } else if (this.isSolo()) {
                if (this.rand.nextInt(3) == 0) {
                    double d = this.posX;
                    double d1 = this.boundingBox.minY + (double)this.getEyeHeight();
                    double d2 = this.posZ;
                    double d3 = MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.1, (double)0.1);
                    double d4 = MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.1, (double)0.1);
                    double d5 = MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.1, (double)0.1);
                    LOTRMod.proxy.spawnParticle("music", d, d1, d2, d3, d4, d5);
                }
                if (this.soloSpinSpeed == 0.0f || this.rand.nextInt(30) == 0) {
                    this.soloSpinSpeed = MathHelper.randomFloatClamp((Random)this.rand, (float)-25.0f, (float)25.0f);
                }
                this.prevSoloSpin = this.soloSpin;
                this.soloSpin += this.soloSpinSpeed;
            } else {
                this.soloSpin = 0.0f;
                this.prevSoloSpin = 0.0f;
                this.soloSpinSpeed = 0.0f;
            }
        }
        if (!this.worldObj.isRemote) {
            double range = 8.0;
            final double rangeSq = range * range;
            EntityPlayer bowingPlayer = null;
            List players = this.worldObj.selectEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(range, range, range), new IEntitySelector(){

                public boolean isEntityApplicable(Entity entity) {
                    EntityPlayer entityplayer = (EntityPlayer)entity;
                    if (entityplayer.isEntityAlive() && LOTREntityElf.this.isFriendly(entityplayer) && LOTREntityElf.this.getDistanceSqToEntity((Entity)entityplayer) <= rangeSq) {
                        return entityplayer.getUniqueID().equals(LOTRPatron.elfBowPlayer);
                    }
                    return false;
                }
            });
            if (players.isEmpty() || this.getAttackTarget() != null) {
                this.setBowingTick(0);
            } else {
                int tick = this.getBowingTick();
                if (tick >= 0) {
                    ++tick;
                }
                if (tick > 40) {
                    tick = -1;
                }
                this.setBowingTick(tick);
                if (tick >= 0) {
                    this.getNavigator().clearPathEntity();
                    bowingPlayer = (EntityPlayer)players.get(0);
                    float bowLook = (float)Math.toDegrees(Math.atan2(bowingPlayer.posZ - this.posZ, bowingPlayer.posX - this.posX));
                    this.rotationYaw = this.rotationYawHead = (bowLook -= 90.0f);
                }
            }
        } else {
            this.prevBowAmount = this.bowAmount;
            int tick = this.getBowingTick();
            if (tick <= 0 && this.bowAmount > 0.0f) {
                this.bowAmount -= 0.2f;
                this.bowAmount = Math.max(this.bowAmount, 0.0f);
            } else if (tick > 0 && this.bowAmount < 1.0f) {
                this.bowAmount += 0.2f;
                this.bowAmount = Math.min(this.bowAmount, 1.0f);
            }
        }
    }

    public float getBowingAmount(float f) {
        return this.prevBowAmount + (this.bowAmount - this.prevBowAmount) * f;
    }

    public float getSoloSpin(float f) {
        return this.prevSoloSpin + (this.soloSpin - this.prevSoloSpin) * f;
    }

    public ItemStack getHeldItem() {
        if (this.worldObj.isRemote && this.isJazz() && this.isSolo()) {
            return null;
        }
        return super.getHeldItem();
    }

    @Override
    protected void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.tasks.removeTask(this.meleeAttackAI);
            this.tasks.removeTask(this.rangedAttackAI);
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        }
        if (mode == LOTREntityNPC.AttackMode.MELEE) {
            this.tasks.removeTask(this.meleeAttackAI);
            this.tasks.removeTask(this.rangedAttackAI);
            this.tasks.addTask(2, this.meleeAttackAI);
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
        if (mode == LOTREntityNPC.AttackMode.RANGED) {
            this.tasks.removeTask(this.meleeAttackAI);
            this.tasks.removeTask(this.rangedAttackAI);
            this.tasks.addTask(2, this.rangedAttackAI);
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getRangedWeapon());
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(LOTRMod.elfBone, 1);
        }
        this.dropNPCArrows(i);
        this.dropElfItems(flag, i);
    }

    protected void dropElfItems(boolean flag, int i) {
        if (flag) {
            int dropChance = 40 - i * 8;
            if (this.rand.nextInt(dropChance = Math.max(dropChance, 1)) == 0) {
                this.dropItem(LOTRMod.lembas, 1);
            }
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            return this.liftSpawnRestrictions || this.canElfSpawnHere();
        }
        return false;
    }

    public abstract boolean canElfSpawnHere();

    public void addPotionEffect(PotionEffect effect) {
        if (effect.getPotionID() == Potion.poison.id) {
            return;
        }
        super.addPotionEffect(effect);
    }

    public String getLivingSound() {
        if (this.getAttackTarget() == null && this.rand.nextInt(10) == 0 && this.familyInfo.isMale()) {
            return "lotr:elf.male.say";
        }
        return super.getLivingSound();
    }

    @Override
    public String getAttackSound() {
        return this.familyInfo.isMale() ? "lotr:elf.male.attack" : super.getAttackSound();
    }

}

