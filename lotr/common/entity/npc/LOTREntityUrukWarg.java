/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    public LOTRFaction getFaction() {
        return LOTRFaction.ISENGARD;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }
}

