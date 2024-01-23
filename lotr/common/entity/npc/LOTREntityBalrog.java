/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
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
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.ai.attributes.RangedAttribute
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIBalrogCharge;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.LOTRWorldProviderUtumno;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
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
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class LOTREntityBalrog
extends LOTREntityNPC {
    public static final IAttribute balrogChargeDamage = new RangedAttribute("lotr.balrogChargeDamage", 2.0, 0.0, Double.MAX_VALUE).setDescription("Balrog Charge Damage");
    private int chargeLean;
    private int prevChargeLean;
    private static final int chargeLeanTime = 10;
    public int chargeFrustration = 0;

    public LOTREntityBalrog(World world) {
        super(world);
        this.setSize(2.4f, 5.0f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIBalrogCharge(this, 3.0, 20.0f, 200));
        this.tasks.addTask(2, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.6, false));
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 24.0f, 0.02f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 16.0f, 0.02f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 12.0f, 0.02f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.addTargetTasks(true);
        this.spawnsInDarkness = true;
        this.isImmuneToFire = true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, (Object)0);
    }

    public boolean isBalrogCharging() {
        return this.dataWatcher.getWatchableObjectByte(20) == 1;
    }

    public void setBalrogCharging(boolean flag) {
        this.dataWatcher.updateObject(20, (Object)((byte)(flag ? 1 : 0)));
    }

    public float getInterpolatedChargeLean(float f) {
        return ((float)this.prevChargeLean + (float)(this.chargeLean - this.prevChargeLean) * f) / 10.0f;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(10.0);
        this.getAttributeMap().registerAttribute(balrogChargeDamage).setBaseValue(15.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (this.rand.nextBoolean()) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.balrogWhip));
        } else {
            int i = this.rand.nextInt(3);
            if (i == 0) {
                this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordUtumno));
            } else if (i == 1) {
                this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeUtumno));
            } else if (i == 2) {
                this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hammerUtumno));
            }
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        return data;
    }

    @Override
    protected void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    public void onLivingUpdate() {
        block12: {
            int l;
            super.onLivingUpdate();
            if (this.getHealth() < this.getMaxHealth() && this.ticksExisted % 10 == 0) {
                this.heal(1.0f);
            }
            if (!this.worldObj.isRemote) {
                this.chargeFrustration = this.getAttackTarget() == null ? 0 : (this.isBalrogCharging() ? 0 : ++this.chargeFrustration);
            }
            this.prevChargeLean = this.chargeLean;
            if (this.isBalrogCharging()) {
                if (this.chargeLean < 10) {
                    ++this.chargeLean;
                }
            } else if (this.chargeLean > 0) {
                --this.chargeLean;
            }
            if (!this.isWreathedInFlame()) break block12;
            if (!this.worldObj.isRemote && this.rand.nextInt(80) == 0) {
                boolean isUtumno = this.worldObj.provider instanceof LOTRWorldProviderUtumno;
                for (int l2 = 0; l2 < 24; ++l2) {
                    int i = MathHelper.floor_double((double)this.posX);
                    int j = MathHelper.floor_double((double)this.boundingBox.minY);
                    int k = MathHelper.floor_double((double)this.posZ);
                    Block block = this.worldObj.getBlock(i += MathHelper.getRandomIntegerInRange((Random)this.rand, (int)-8, (int)8), j += MathHelper.getRandomIntegerInRange((Random)this.rand, (int)-4, (int)8), k += MathHelper.getRandomIntegerInRange((Random)this.rand, (int)-8, (int)8));
                    float maxResistance = Blocks.obsidian.getExplosionResistance((Entity)this);
                    if (!block.isReplaceable((IBlockAccess)this.worldObj, i, j, k) && (!isUtumno || !(block.getExplosionResistance((Entity)this) <= maxResistance)) || !Blocks.fire.canPlaceBlockAt(this.worldObj, i, j, k)) continue;
                    this.worldObj.setBlock(i, j, k, (Block)Blocks.fire, 0, 3);
                }
            }
            if (this.isBalrogCharging()) {
                for (l = 0; l < 10; ++l) {
                    String s = this.rand.nextInt(3) == 0 ? "flame" : "largesmoke";
                    double d0 = this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width * 1.5;
                    double d1 = this.posY + (double)this.height * MathHelper.getRandomDoubleInRange((Random)this.rand, (double)0.5, (double)1.5);
                    double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width * 1.5;
                    double d3 = this.motionX * -2.0;
                    double d4 = this.motionY * -0.5;
                    double d5 = this.motionZ * -2.0;
                    this.worldObj.spawnParticle(s, d0, d1, d2, d3, d4, d5);
                }
            } else {
                for (l = 0; l < 3; ++l) {
                    String s = this.rand.nextInt(3) == 0 ? "flame" : "largesmoke";
                    double d0 = this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
                    double d1 = this.posY + 0.5 + this.rand.nextDouble() * (double)this.height * 1.5;
                    double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
                    this.worldObj.spawnParticle(s, d0, d1, d2, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public boolean isWreathedInFlame() {
        return this.isEntityAlive() && !this.isWet();
    }

    public void knockBack(Entity entity, float f, double d, double d1) {
        super.knockBack(entity, f, d, d1);
        this.motionX /= 4.0;
        this.motionY /= 4.0;
        this.motionZ /= 4.0;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            EntityLivingBase curTarget = this.getAttackTarget();
            if (curTarget != null && entity == curTarget) {
                this.chargeFrustration = 0;
            }
            if (this.getHeldItem() == null) {
                entity.setFire(5);
            }
            float attackDamage = (float)this.getEntityAttribute(LOTREntityNPC.npcAttackDamage).getAttributeValue();
            float knockbackModifier = 0.25f * attackDamage;
            entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockbackModifier * 0.5f), (double)knockbackModifier * 0.1, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockbackModifier * 0.5f));
            return true;
        }
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        if (damagesource == DamageSource.fall) {
            return false;
        }
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag) {
            EntityLivingBase curTarget = this.getAttackTarget();
            if (curTarget != null && damagesource.getEntity() == curTarget && damagesource.getSourceOfDamage() == curTarget) {
                this.chargeFrustration = 0;
            }
            return true;
        }
        return false;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killBalrog;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer entityplayer) {
        return 15 + this.rand.nextInt(10);
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int coals = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)4, (int)(16 + i * 8));
        for (int l = 0; l < coals; ++l) {
            this.dropItem(Items.coal, 1);
        }
        int fires = 1;
        if (i > 0) {
            float x = MathHelper.randomFloatClamp((Random)this.rand, (float)0.0f, (float)((float)i * 0.667f));
            while (x > 1.0f) {
                x -= 1.0f;
                ++fires;
            }
            if (this.rand.nextFloat() < x) {
                ++fires;
            }
        }
        for (int l = 0; l < fires; ++l) {
            this.dropItem(LOTRMod.balrogFire, 1);
        }
    }

    @Override
    public void dropNPCEquipment(boolean flag, int i) {
        ItemStack heldItem;
        if (flag && this.rand.nextInt(3) == 0 && (heldItem = this.getHeldItem()) != null) {
            this.entityDropItem(heldItem, 0.0f);
            this.setCurrentItemOrArmor(0, null);
        }
        super.dropNPCEquipment(flag, i);
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        if (!this.worldObj.isRemote) {
            int exRange = 8;
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)this.posY);
            int k = MathHelper.floor_double((double)this.posZ);
            for (int i1 = i - exRange; i1 <= i + exRange; ++i1) {
                for (int j1 = j - exRange; j1 <= j + exRange; ++j1) {
                    for (int k1 = k - exRange; k1 <= k + exRange; ++k1) {
                        Block block = this.worldObj.getBlock(i1, j1, k1);
                        if (block.getMaterial() != Material.fire) continue;
                        this.worldObj.setBlockToAir(i1, j1, k1);
                    }
                }
            }
        } else {
            for (int l = 0; l < 100; ++l) {
                double d = this.posX + (double)(this.width * MathHelper.randomFloatClamp((Random)this.rand, (float)-1.0f, (float)1.0f));
                double d1 = this.posY + (double)(this.height * MathHelper.randomFloatClamp((Random)this.rand, (float)0.0f, (float)1.0f));
                double d2 = this.posZ + (double)(this.width * MathHelper.randomFloatClamp((Random)this.rand, (float)-1.0f, (float)1.0f));
                double d3 = this.rand.nextGaussian() * 0.03;
                double d4 = this.rand.nextGaussian() * 0.03;
                double d5 = this.rand.nextGaussian() * 0.03;
                if (this.rand.nextInt(3) == 0) {
                    this.worldObj.spawnParticle("explode", d, d1, d2, d3, d4, d5);
                    continue;
                }
                this.worldObj.spawnParticle("flame", d, d1, d2, d3, d4, d5);
            }
        }
        super.onDeath(damagesource);
        this.playSound(this.getHurtSound(), this.getSoundVolume() * 2.0f, this.getSoundPitch() * 0.75f);
    }

    public String getLivingSound() {
        return "lotr:troll.say";
    }

    public String getHurtSound() {
        return "lotr:troll.say";
    }

    protected float getSoundVolume() {
        return 1.5f;
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
        this.playSound("lotr:troll.step", 0.75f, this.getSoundPitch());
    }
}

