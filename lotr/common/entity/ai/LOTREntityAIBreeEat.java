/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package lotr.common.entity.ai;

import lotr.common.LOTRFoods;
import lotr.common.entity.ai.LOTREntityAIEat;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTREntityAIBreeEat
extends LOTREntityAIEat {
    public LOTREntityAIBreeEat(LOTREntityNPC entity, LOTRFoods foods, int chance) {
        super(entity, foods, chance);
    }

    @Override
    protected ItemStack createConsumable() {
        if (this.theEntity.getNPCName().equals("Peter Jackson")) {
            return new ItemStack(Items.carrot);
        }
        return super.createConsumable();
    }
}

