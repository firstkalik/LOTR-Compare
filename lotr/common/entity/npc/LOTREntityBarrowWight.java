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
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAIHiredRemainStill;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityBarrowWight
extends LOTREntityNPC {
    private static Potion[] attackEffects = new Potion[]{Potion.moveSlowdown, Potion.digSlowdown, Potion.wither};

    public LOTREntityBarrowWight(World world) {
        super(world);
        this.setSize(0.8f, 2.5f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIHiredRemainStill(this));
        this.tasks.addTask(2, this.getWightAttackAI());
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 12.0f, 0.02f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 8.0f, 0.02f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 12.0f, 0.02f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.addTargetTasks(true);
        this.spawnsInDarkness = true;
    }

    public EntityAIBase getWightAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.5, false);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)-1);
    }

    public int getTargetEntityID() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    public void setTargetEntityID(Entity entity) {
        this.dataWatcher.updateObject(16, (Object)(entity == null ? -1 : entity.getEntityId()));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(6.0);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.HOSTILE;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.isRemote) {
            for (int l = 0; l < 1; ++l) {
                double d = this.posX + (double)this.width * MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.5, (double)0.5);
                double d1 = this.posY + (double)this.height * MathHelper.getRandomDoubleInRange((Random)this.rand, (double)0.4, (double)0.8);
                double d2 = this.posZ + (double)this.width * MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.5, (double)0.5);
                double d3 = MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.1, (double)0.1);
                double d4 = MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.2, (double)-0.05);
                double d5 = MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.1, (double)0.1);
                if (this.rand.nextBoolean()) {
                    LOTRMod.proxy.spawnParticle("morgulPortal", d, d1, d2, d3, d4, d5);
                    continue;
                }
                this.worldObj.spawnParticle("smoke", d, d1, d2, d3, d4, d5);
            }
        }
    }

    @Override
    public void setAttackTarget(EntityLivingBase target, boolean speak) {
        super.setAttackTarget(target, speak);
        if (!this.worldObj.isRemote) {
            this.setTargetEntityID((Entity)target);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            int difficulty;
            int duration;
            if (entity instanceof EntityLivingBase && (duration = (difficulty = this.worldObj.difficultySetting.getDifficultyId()) * (difficulty + 5) / 2) > 0) {
                for (Potion effect : attackEffects) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(effect.id, duration * 20, 0));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killBarrowWight;
    }

    @Override
    public float getAlignmentBonus() {
        return 0.0f;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer entityplayer) {
        return 4 + this.rand.nextInt(5);
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int bones = 1 + this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(Items.bone, 1);
        }
        if (this.rand.nextBoolean()) {
            this.dropChestContents(LOTRChestContents.BARROW_DOWNS, 1, 2 + i + 1);
        }
    }

    @Override
    public boolean canDropRares() {
        return true;
    }

    protected String getHurtSound() {
        return "lotr:wight.hurt";
    }

    protected String getDeathSound() {
        return "lotr:wight.death";
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
    }

    @Override
    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            if (this.liftSpawnRestrictions) {
                return true;
            }
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)this.boundingBox.minY);
            int k = MathHelper.floor_double((double)this.posZ);
            return j > 62 && this.worldObj.getBlock(i, j - 1, k) == this.worldObj.getBiomeGenForCoords((int)i, (int)k).topBlock;
        }
        return false;
    }

    @Override
    public boolean canReEquipHired(int slot, ItemStack itemstack) {
        return false;
    }
}

