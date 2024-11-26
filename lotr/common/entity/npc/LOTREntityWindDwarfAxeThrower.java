/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityWindDwarfWarrior;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.projectile.LOTREntityFirePotDwarven;
import lotr.common.entity.projectile.LOTREntityThrowingAxe2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityWindDwarfAxeThrower
extends LOTREntityWindDwarfWarrior {
    public EntityAIBase rangedAttackAI = this.createRangedAttackAI();
    public EntityAIBase meleeAttackAI;
    private boolean usedFirePot = false;

    public LOTREntityWindDwarfAxeThrower(World world) {
        super(world);
    }

    @Override
    protected EntityAIBase getDwarfAttackAI() {
        this.meleeAttackAI = super.getDwarfAttackAI();
        return this.meleeAttackAI;
    }

    protected EntityAIBase createRangedAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 30, 12.0f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.76);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeDwarven));
        this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.throwingAxeDwarven));
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.throwingAxeDwarven));
        return data;
    }

    @Override
    public void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            if (this.meleeAttackAI != null && this.tasks.taskEntries.contains((Object)this.meleeAttackAI)) {
                this.removeTask(this.meleeAttackAI);
            }
            if (this.rangedAttackAI != null && this.tasks.taskEntries.contains((Object)this.rangedAttackAI)) {
                this.removeTask(this.rangedAttackAI);
            }
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        }
        if (mode == LOTREntityNPC.AttackMode.MELEE) {
            if (this.rangedAttackAI != null && this.tasks.taskEntries.contains((Object)this.rangedAttackAI)) {
                this.removeTask(this.rangedAttackAI);
            }
            if (this.meleeAttackAI != null) {
                this.tasks.addTask(2, this.meleeAttackAI);
            }
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
        if (mode == LOTREntityNPC.AttackMode.RANGED) {
            if (this.meleeAttackAI != null && this.tasks.taskEntries.contains((Object)this.meleeAttackAI)) {
                this.removeTask(this.meleeAttackAI);
            }
            if (this.rangedAttackAI != null) {
                this.tasks.addTask(2, this.rangedAttackAI);
            }
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getRangedWeapon());
        }
    }

    private void removeTask(EntityAIBase task) {
        if (task != null) {
            Iterator iterator = this.tasks.taskEntries.iterator();
            while (iterator.hasNext()) {
                EntityAIBase entry = (EntityAIBase)iterator.next();
                if (entry != task) continue;
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        if (target == null) {
            return;
        }
        double distance = this.getDistanceToEntity((Entity)target);
        if (distance <= 4.0) {
            this.onAttackModeChange(LOTREntityNPC.AttackMode.MELEE, false);
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
            return;
        }
        if (!this.usedFirePot && !target.isBurning()) {
            this.setCurrentItemOrArmor(0, new ItemStack(LOTRMod.rhunFirePotDwarven));
            LOTREntityFirePotDwarven firePot = new LOTREntityFirePotDwarven(this.worldObj, (EntityLivingBase)this);
            firePot.setThrowableHeading(target.posX - this.posX, target.posY - this.posY, target.posZ - this.posZ, 1.0f, 0.5f);
            this.worldObj.spawnEntityInWorld((Entity)firePot);
            this.usedFirePot = true;
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getRangedWeapon());
            ItemStack axeItem = this.npcItemsInv.getRangedWeapon();
            if (axeItem == null) {
                axeItem = new ItemStack(LOTRMod.throwingAxeDwarven);
            }
            LOTREntityThrowingAxe2 axe = new LOTREntityThrowingAxe2(this.worldObj, (EntityLivingBase)this, target, axeItem, 1.0f, (float)this.getEntityAttribute(npcRangedAccuracy).getAttributeValue());
            this.worldObj.spawnEntityInWorld((Entity)axe);
        }
        this.playSound("random.bow", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
        this.swingItem();
    }

    @Override
    public double getMeleeRange() {
        EntityLivingBase target = this.getAttackTarget();
        if (target != null && target.isBurning()) {
            return 4.0;
        }
        return super.getMeleeRange();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.getAttackTarget() != null && !this.getAttackTarget().isBurning()) {
            this.usedFirePot = false;
        }
    }
}

