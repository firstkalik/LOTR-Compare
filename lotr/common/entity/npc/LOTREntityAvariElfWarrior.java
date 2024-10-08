/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityAvariElf;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityAvariElfWarrior
extends LOTREntityAvariElf {
    public LOTREntityAvariElfWarrior(World world) {
        super(world);
        this.tasks.removeTask(this.rangedAttackAI);
        this.tasks.addTask(2, this.meleeAttackAI);
        this.spawnRidingHorse = this.rand.nextInt(4) == 0;
        this.npcShield = LOTRShields.ALIGNMENT_AVARI;
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityHorse elk = new LOTREntityHorse(this.worldObj);
        elk.setMountArmor(new ItemStack(LOTRMod.horseArmorAvari));
        return elk;
    }

    @Override
    protected EntityAIBase createElfRangedAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 25, 35, 24.0f);
    }

    @Override
    protected EntityAIBase createElfMeleeAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.4, false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(6);
        if (i == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pikeAvariElf));
        } else if (i == 1) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearAvariElf));
        } else {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordAvariElf));
        }
        this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.bowAvariElf));
        if (this.rand.nextInt(5) == 0) {
            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearAvariElf));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsAvariElf));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsAvariElf));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyAvariElf));
        if (this.rand.nextInt(10) != 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetAvariElf));
        } else {
            this.setCurrentItemOrArmor(4, null);
        }
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "avariElf/elf/hired";
            }
            if (LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= LOTREntityAvariElf.getWoodlandTrustLevel()) {
                return "avariElf/warrior/friendly";
            }
            return "avariElf/elf/neutral";
        }
        return "avariElf/warrior/hostile";
    }
}

