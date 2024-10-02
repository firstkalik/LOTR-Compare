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
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityDolGuldurUruk;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityDolGuldurUrukBerserk
extends LOTREntityDolGuldurUruk {
    public static float BERSERKER_SCALE = 1.14f;

    public LOTREntityDolGuldurUrukBerserk(World world) {
        super(world);
        this.setSize(this.npcWidth * BERSERKER_SCALE, this.npcHeight * BERSERKER_SCALE);
        this.npcShield = LOTRShields.ALIGNMENT_DOL_GULDUR_URUK;
        this.isStrongOrc1 = true;
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.6, false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.21);
        this.getEntityAttribute(npcAttackDamageExtra).setBaseValue(1.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordDolGuldurUrukPoisoned));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsguldururukberserk));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsguldururukberserk));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyguldururukberserk));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetguldururukberserk));
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.76f;
    }
}

