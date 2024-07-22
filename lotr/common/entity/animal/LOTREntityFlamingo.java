/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIFollowParent
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMate
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityFlamingo
extends EntityAnimal {
    public boolean field_753_a = false;
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h = 5.0f;
    public static final int NECK_TIME = 20;
    public static final int FISHING_TIME = 160;
    public static final int FISHING_TIME_TOTAL = 200;

    public LOTREntityFlamingo(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setAvoidsWater(false);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.3));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIMate((EntityAnimal)this, 1.0));
        this.tasks.addTask(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2, Items.fish, false));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.2));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    protected void dropFewItems(boolean flag, int i) {
        int k;
        int feathers = this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int l = 0; l < feathers; ++l) {
            this.dropItem(Items.feather, 1);
        }
        int j3 = this.rand.nextInt(4) + this.rand.nextInt(1 + i);
        for (k = 0; k < j3; ++k) {
            this.dropItem(Items.bone, 1);
        }
        k = 1 + this.rand.nextInt(4) + this.rand.nextInt(i + 1);
        for (int j1 = 0; j1 < k; ++j1) {
            this.dropItem(Items.leather, 1);
        }
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    private int getFishingTick() {
        int i = this.dataWatcher.getWatchableObjectInt(16);
        return i;
    }

    public int getFishingTickPre() {
        return this.getFishingTick() >> 16;
    }

    public int getFishingTickCur() {
        return this.getFishingTick() & 0xFFFF;
    }

    public void setFishingTick(int pre, int cur) {
        int i = pre << 16 | cur & 0xFFFF;
        this.dataWatcher.updateObject(16, (Object)i);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.field_756_e = this.field_752_b;
        this.field_757_d = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround || this.inWater ? -1 : 4) * 0.3);
        if (this.destPos < 0.0f) {
            this.destPos = 0.0f;
        }
        if (this.destPos > 1.0f) {
            this.destPos = 1.0f;
        }
        if (!this.onGround && !this.inWater && this.field_755_h < 1.0f) {
            this.field_755_h = 1.0f;
        }
        this.field_755_h = (float)((double)this.field_755_h * 0.9);
        if (!this.onGround && !this.inWater && this.motionY < 0.0) {
            this.motionY *= 0.6;
        }
        this.field_752_b += this.field_755_h * 2.0f;
        if (!(this.worldObj.isRemote || this.isChild() || this.isInLove() || this.getFishingTickCur() != 0 || this.rand.nextInt(600) != 0 || this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.boundingBox.minY), MathHelper.floor_double((double)this.posZ)) != Blocks.water)) {
            this.setFishingTick(200, 200);
        }
        if (this.getFishingTickCur() > 0) {
            if (!this.worldObj.isRemote) {
                int cur = this.getFishingTickCur();
                this.setFishingTick(cur, cur - 1);
            } else {
                for (int i = 0; i < 3; ++i) {
                    double d = this.posX + MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.3, (double)0.3);
                    double d1 = this.boundingBox.minY + MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.3, (double)0.3);
                    double d2 = this.posZ + MathHelper.getRandomDoubleInRange((Random)this.rand, (double)-0.3, (double)0.3);
                    this.worldObj.spawnParticle("bubble", d, d1, d2, 0.0, 0.0, 0.0);
                }
            }
        }
        if (!this.worldObj.isRemote && this.isInLove() && this.getFishingTickCur() > 20) {
            this.setFishingTick(20, 20);
        }
    }

    public boolean attackEntityFrom(DamageSource source, float f) {
        boolean flag = super.attackEntityFrom(source, f);
        if (flag && !this.worldObj.isRemote && this.getFishingTickCur() > 20) {
            this.setFishingTick(20, 20);
        }
        return flag;
    }

    protected void fall(float f) {
    }

    protected String getLivingSound() {
        return "lotr:flamingo.say";
    }

    protected String getHurtSound() {
        return "lotr:flamingo.hurt";
    }

    protected String getDeathSound() {
        return "lotr:flamingo.death";
    }

    protected Item getDropItem() {
        return Items.feather;
    }

    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack.getItem() == Items.fish;
    }

    public EntityAgeable createChild(EntityAgeable entity) {
        return new LOTREntityFlamingo(this.worldObj);
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }
}

