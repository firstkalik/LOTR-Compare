/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityTauredain;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.projectile.LOTREntityDart;
import lotr.common.item.LOTRItemBlowgun;
import lotr.common.item.LOTRItemDart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityTauredainBlowgunner
extends LOTREntityTauredain {
    public LOTREntityTauredainBlowgunner(World world) {
        super(world);
        this.addTargetTasks(true);
    }

    @Override
    public EntityAIBase createHaradrimAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.5, 10, 30, 16.0f);
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.tauredainBlowgun));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsTauredain));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsTauredain));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyTauredain));
        return data;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        ItemStack heldItem = this.getHeldItem();
        float str = 1.0f + this.getDistanceToEntity((Entity)target) / 16.0f * 0.015f;
        LOTREntityDart dart = ((LOTRItemDart)LOTRMod.tauredainDart).createDart(this.worldObj, (EntityLivingBase)this, target, new ItemStack(LOTRMod.tauredainDart), str *= LOTRItemBlowgun.getBlowgunLaunchSpeedFactor(heldItem), 1.0f);
        if (heldItem != null) {
            LOTRItemBlowgun.applyBlowgunModifiers(dart, heldItem);
        }
        this.playSound("lotr:item.dart", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 1.2f) + 0.5f);
        this.worldObj.spawnEntityInWorld((Entity)dart);
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        this.dropNPCAmmo(LOTRMod.tauredainDart, i);
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "tauredain/warrior/hired";
            }
            return "tauredain/warrior/friendly";
        }
        return "tauredain/warrior/hostile";
    }
}

