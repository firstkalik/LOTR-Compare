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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityDunlendingWarrior;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.projectile.LOTREntityThrowingAxe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityDunlendingAxeThrower
extends LOTREntityDunlendingWarrior {
    public LOTREntityDunlendingAxeThrower(World world) {
        super(world);
    }

    @Override
    public EntityAIBase getDunlendingAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.4, 40, 60, 12.0f);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (this.rand.nextBoolean()) {
            this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.throwingAxeIron));
        } else {
            this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.throwingAxeBronze));
        }
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
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        ItemStack axeItem = this.npcItemsInv.getRangedWeapon();
        if (axeItem == null) {
            axeItem = new ItemStack(LOTRMod.throwingAxeIron);
        }
        LOTREntityThrowingAxe axe = new LOTREntityThrowingAxe(this.worldObj, (EntityLivingBase)this, target, axeItem, 1.0f, (float)this.getEntityAttribute(npcRangedAccuracy).getAttributeValue());
        this.playSound("random.bow", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)axe);
        this.swingItem();
    }
}
