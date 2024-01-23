/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 */
package lotr.common.entity.ai;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.entity.ai.LOTREntityAIConsumeBase;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class LOTREntityAIEat
extends LOTREntityAIConsumeBase {
    public LOTREntityAIEat(LOTREntityNPC entity, LOTRFoods foods, int chance) {
        super(entity, foods, chance);
    }

    @Override
    protected ItemStack createConsumable() {
        return this.foodPool.getRandomFood(this.rand);
    }

    @Override
    protected void updateConsumeTick(int tick) {
        if (tick % 4 == 0) {
            this.theEntity.spawnFoodParticles();
            this.theEntity.playSound("random.eat", 0.5f + 0.5f * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
        }
    }

    @Override
    protected void consume() {
        ItemStack itemstack = this.theEntity.getHeldItem();
        Item item = itemstack.getItem();
        if (item instanceof ItemFood) {
            ItemFood food = (ItemFood)item;
            this.theEntity.heal((float)food.func_150905_g(itemstack));
        }
    }
}

