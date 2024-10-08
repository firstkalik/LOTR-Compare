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
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.animal.LOTREntityWildBoar;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityStonefootWarrior;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.entity.projectile.LOTREntityFirePotDwarven;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityStonefootFlameThrower
extends LOTREntityStonefootWarrior {
    public EntityAIBase rangedAttackAI = this.createRedRangedAI();
    public EntityAIBase meleeAttackAI;

    public LOTREntityStonefootFlameThrower(World world) {
        super(world);
        this.spawnRidingHorse = this.rand.nextInt(6) == 0;
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityWildBoar boar = new LOTREntityWildBoar(this.worldObj);
        boar.setMountArmor(new ItemStack(LOTRMod.boarArmorRedGilded));
        return boar;
    }

    @Override
    protected EntityAIBase getDwarfAttackAI() {
        this.meleeAttackAI = super.getDwarfAttackAI();
        return this.meleeAttackAI;
    }

    protected EntityAIBase createRedRangedAI() {
        return new LOTREntityAIRangedAttack(this, 1.3, 20, 30, 16.0f);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordRed));
        this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.rhunFirePotDwarven));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getRangedWeapon());
        return data;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        EntityArrow template = new EntityArrow(this.worldObj, (EntityLivingBase)this, target, 1.0f, 0.5f);
        LOTREntityFirePotDwarven pot = new LOTREntityFirePotDwarven(this.worldObj, (EntityLivingBase)this);
        pot.setLocationAndAngles(template.posX, template.posY, template.posZ, template.rotationYaw, template.rotationPitch);
        pot.motionX = template.motionX;
        pot.motionY = template.motionY;
        pot.motionZ = template.motionZ;
        this.playSound("random.bow", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)pot);
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
}

