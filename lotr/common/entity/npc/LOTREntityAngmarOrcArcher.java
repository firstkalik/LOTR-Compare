/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.item.LOTREntityArrowMorgul;
import lotr.common.entity.item.LOTREntityArrowWeak;
import lotr.common.entity.npc.LOTREntityAngmarOrc;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.item.LOTRItemBow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityAngmarOrcArcher
extends LOTREntityAngmarOrc {
    public LOTREntityAngmarOrcArcher(World world) {
        super(world);
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 30, 60, 16.0f);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.orcBow));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getRangedWeapon());
        return data;
    }

    @Override
    protected void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getRangedWeapon());
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        this.dropNPCArrows(i);
    }

    protected float getMorgulArrowChance() {
        return 0.06666667f;
    }

    protected float getWeakArrowChance() {
        return 0.06666667f;
    }

    @Override
    protected void npcArrowAttack(EntityLivingBase target, float f) {
        ItemStack heldItem = this.getHeldItem();
        float str = 1.3f + this.getDistanceToEntity((Entity)target) / 80.0f;
        float accuracy = (float)this.getEntityAttribute(npcRangedAccuracy).getAttributeValue();
        float poisonChance = this.getMorgulArrowChance();
        float weakArrowChance = this.getWeakArrowChance();
        EntityArrow arrow = this.rand.nextFloat() < poisonChance ? new LOTREntityArrowMorgul(this.worldObj, (EntityLivingBase)this, target, str, accuracy) : (this.rand.nextFloat() < weakArrowChance ? new LOTREntityArrowWeak(this.worldObj, (EntityLivingBase)this, target, str, accuracy) : new EntityArrow(this.worldObj, (EntityLivingBase)this, target, str *= LOTRItemBow.getLaunchSpeedFactor(heldItem), accuracy));
        if (heldItem != null) {
            LOTRItemBow.applyBowModifiers(arrow, heldItem);
        }
        this.playSound("random.bow", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)arrow);
    }
}

