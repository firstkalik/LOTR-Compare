/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package lotr.common.entity.ai;

import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIHobbitSmoke;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemHobbitPipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTREntityAIGandalfSmoke
extends LOTREntityAIHobbitSmoke {
    public LOTREntityAIGandalfSmoke(LOTREntityNPC entity, int chance) {
        super(entity, chance);
    }

    @Override
    protected ItemStack createConsumable() {
        ItemStack pipe = new ItemStack(LOTRMod.hobbitPipe);
        LOTRItemHobbitPipe.setSmokeColor(pipe, 16);
        return pipe;
    }
}

