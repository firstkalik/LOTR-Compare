/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIConsumeBase;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.projectile.LOTREntitySmokeRing;
import lotr.common.item.LOTRItemHobbitPipe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityAIHobbitSmoke
extends LOTREntityAIConsumeBase {
    public LOTREntityAIHobbitSmoke(LOTREntityNPC entity, int chance) {
        super(entity, null, chance);
    }

    @Override
    protected ItemStack createConsumable() {
        return new ItemStack(LOTRMod.hobbitPipe);
    }

    @Override
    protected void updateConsumeTick(int tick) {
    }

    @Override
    protected void consume() {
        LOTREntitySmokeRing smoke = new LOTREntitySmokeRing(this.theEntity.worldObj, (EntityLivingBase)this.theEntity);
        int color = 0;
        ItemStack itemstack = this.theEntity.getHeldItem();
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemHobbitPipe) {
            color = LOTRItemHobbitPipe.getSmokeColor(itemstack);
        }
        smoke.setSmokeColour(color);
        this.theEntity.worldObj.spawnEntityInWorld((Entity)smoke);
        this.theEntity.playSound("lotr:item.puff", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
        this.theEntity.heal(2.0f);
    }
}

