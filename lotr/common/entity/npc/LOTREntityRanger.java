/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRCapes;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityDunedain;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class LOTREntityRanger
extends LOTREntityDunedain {
    public EntityAIBase rangedAttackAI = this.createDunedainRangedAI();
    public EntityAIBase meleeAttackAI;
    private int sneakCooldown = 0;
    private EntityLivingBase prevRangerTarget;
    private EntityAIBase normalRangedAI;
    private EntityAIBase enhancedRangedAI;

    public LOTREntityRanger(World world) {
        super(world);
        this.addTargetTasks(true);
        this.npcCape = LOTRCapes.ALIGNMENT_RANGER.capeTexture;
        this.normalRangedAI = this.createDunedainRangedAI(1.25, 20, 40, 20.0f);
        this.enhancedRangedAI = this.createDunedainRangedAI(1.26, 10, 20, 30.0f);
        this.tasks.addTask(2, this.normalRangedAI);
    }

    protected EntityAIBase createDunedainRangedAI(double moveSpeed, int minAttackTime, int maxAttackTime, float maxRange) {
        return new LOTREntityAIRangedAttack(this, moveSpeed, minAttackTime, maxAttackTime, maxRange);
    }

    @Override
    protected EntityAIBase createDunedainAttackAI() {
        this.meleeAttackAI = new LOTREntityAIAttackOnCollide(this, 1.5, true);
        return this.meleeAttackAI;
    }

    protected EntityAIBase createDunedainRangedAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 20, 40, 20.0f);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(17, (Object)0);
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    public boolean isRangerSneaking() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setRangerSneaking(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)(flag ? (byte)1 : 0));
        if (flag) {
            this.sneakCooldown = 20;
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)25, (int)27));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.5);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerIron));
        this.npcItemsInv.setRangedWeapon(new ItemStack((Item)Items.bow));
        this.npcItemsInv.setIdleItem(null);
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsRanger));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsRanger));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyRanger));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetRanger));
        return data;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.getHealth() < this.getMaxHealth() / 2.0f) {
            this.updateRangedAI(this.enhancedRangedAI);
        } else {
            this.updateRangedAI(this.normalRangedAI);
        }
    }

    private void updateRangedAI(EntityAIBase newAI) {
        this.tasks.removeTask(this.normalRangedAI);
        this.tasks.removeTask(this.enhancedRangedAI);
        this.tasks.addTask(2, newAI);
    }

    @Override
    public void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
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
    public void setAttackTarget(EntityLivingBase target) {
        super.setAttackTarget(target);
        if (target != null && target != this.prevRangerTarget) {
            this.prevRangerTarget = target;
            if (!this.worldObj.isRemote && !this.isRangerSneaking() && this.ridingEntity == null) {
                this.setRangerSneaking(true);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && !this.worldObj.isRemote && this.isRangerSneaking()) {
            this.setRangerSneaking(false);
        }
        return flag;
    }

    public void swingItem() {
        super.swingItem();
        if (!this.worldObj.isRemote && this.isRangerSneaking()) {
            this.setRangerSneaking(false);
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        this.dropNPCArrows(i);
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
        if (!this.isRangerSneaking()) {
            super.func_145780_a(i, j, k, block);
        }
    }
}

