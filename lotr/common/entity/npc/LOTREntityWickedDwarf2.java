/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.entity.projectile.LOTREntityThrowingAxe;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityWickedDwarf2
extends LOTREntityDwarf
implements LOTRMercenary {
    public EntityAIBase rangedAttackAI = this.createRedRangedAI();
    public EntityAIBase meleeAttackAI;

    public LOTREntityWickedDwarf2(World world) {
        super(world);
    }

    @Override
    protected EntityAIBase getDwarfAttackAI() {
        this.meleeAttackAI = super.getDwarfAttackAI();
        return this.meleeAttackAI;
    }

    protected EntityAIBase createRedRangedAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 40, 12.0f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.75);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(7);
        if (i == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordWickedDwarf));
        } else if (i == 1 || i == 2) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeWickedDwarf));
        } else if (i == 3 || i == 4) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hammerWickedDwarf));
        } else if (i == 5) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeWickedDwarf));
        } else if (i == 6) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearWickedDwarf));
        }
        if (this.rand.nextInt(6) == 0) {
            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearWickedDwarf));
        }
        if (this.rand.nextInt(4) == 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.wdhelmet));
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.wdboots));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.wdlegs));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.wdbody));
        } else {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.wdhelmet));
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.wdboots));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.wdlegs));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.wdbody));
        }
        this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.throwingAxeWickedDwarf));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getRangedWeapon());
        return data;
    }

    @Override
    public double getMeleeRange() {
        EntityLivingBase target = this.getAttackTarget();
        if (target != null && target.isBurning()) {
            return Double.MAX_VALUE;
        }
        return super.getMeleeRange();
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
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        ItemStack axeItem = this.npcItemsInv.getRangedWeapon();
        if (axeItem == null) {
            axeItem = new ItemStack(LOTRMod.throwingAxeWickedDwarf);
        }
        LOTREntityThrowingAxe axe = new LOTREntityThrowingAxe(this.worldObj, (EntityLivingBase)this, target, axeItem, 1.0f, (float)this.getEntityAttribute(npcRangedAccuracy).getAttributeValue());
        this.playSound("random.bow", 1.0f, 1.0f / (this.rand.nextFloat() * 0.3f + 0.9f));
        this.worldObj.spawnEntityInWorld((Entity)axe);
        this.swingItem();
    }

    @Override
    public LOTRFaction getHiringFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    public int getMercBaseCost() {
        return 80;
    }

    @Override
    public float getMercAlignmentRequired() {
        return 250.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 0.0f && this.isFriendly(entityplayer);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.hireMoredainMercenary);
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.boundingBox.minY);
        int k = MathHelper.floor_double((double)this.posZ);
        return j > 30 && this.worldObj.getBlock(i, j - 1, k) == Blocks.snow;
    }

    @Override
    protected float getSoundPitch() {
        float f = super.getSoundPitch();
        if (!this.familyInfo.isMale()) {
            f *= 4.6f;
        }
        return f;
    }
}

