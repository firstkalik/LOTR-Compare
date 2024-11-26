/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityIsengardSnaga;
import lotr.common.entity.npc.LOTREntityIsengardSnagaArcher;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityUrukWarg
extends LOTREntityWarg {
    public LOTREntityUrukWarg(World world) {
        super(world);
    }

    @Override
    public LOTREntityNPC createWargRider() {
        if (this.rand.nextBoolean()) {
            this.setWargArmor(new ItemStack(LOTRMod.wargArmorUruk));
        }
        return this.worldObj.rand.nextBoolean() ? new LOTREntityIsengardSnagaArcher(this.worldObj) : new LOTREntityIsengardSnaga(this.worldObj);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)20, (int)32));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.215);
        this.getEntityAttribute(npcAttackDamage).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)5, (int)8));
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ISENGARD;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }
}

