/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityGundabadOrc;
import lotr.common.entity.npc.LOTREntityGundabadOrcArcher;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityGundabadWarg
extends LOTREntityWarg {
    public LOTREntityGundabadWarg(World world) {
        super(world);
    }

    @Override
    public EntityAIBase getWargAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.75, false);
    }

    @Override
    public LOTREntityNPC createWargRider() {
        return this.worldObj.rand.nextBoolean() ? new LOTREntityGundabadOrcArcher(this.worldObj) : new LOTREntityGundabadOrc(this.worldObj);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)20, (int)32));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.225);
        this.getEntityAttribute(npcAttackDamage).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)3, (int)5));
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }
}

