/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityDurmethOrc;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityDurmethOrcWarrior
extends LOTREntityDurmethOrc {
    private static ItemStack[] urukWeapons = new ItemStack[]{new ItemStack(LOTRMod.swordGundabadUruk), new ItemStack(LOTRMod.battleaxeGundabadUruk), new ItemStack(LOTRMod.hammerGundabadUruk), new ItemStack(LOTRMod.daggerGundabadUruk), new ItemStack(LOTRMod.daggerGundabadUrukPoisoned), new ItemStack(LOTRMod.pikeGundabadUruk)};

    public LOTREntityDurmethOrcWarrior(World world) {
        super(world);
        this.isWeakOrc = false;
        this.npcShield = LOTRShields.ALIGNMENT_GUNDABAD;
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.5, false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)25, (int)30));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.75);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(urukWeapons.length);
        this.npcItemsInv.setMeleeWeapon(urukWeapons[i].copy());
        if (this.rand.nextInt(6) == 0) {
            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearGundabadUruk));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsDurmethOrc));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsDurmethOrc));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyDurmethOrc));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetDurmethOrc));
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killDurmethOrcWarrior;
    }
}

