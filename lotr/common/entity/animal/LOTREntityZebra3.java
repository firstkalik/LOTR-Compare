/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityList
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.animal.LOTREntityHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTREntityZebra3
extends LOTREntityHorse {
    public LOTREntityZebra3(World world) {
        super(world);
    }

    public int getHorseType() {
        return 0;
    }

    public boolean func_110259_cr() {
        return false;
    }

    @Override
    public String getCommandSenderName() {
        if (this.hasCustomNameTag()) {
            return this.getCustomNameTag();
        }
        String s = EntityList.getEntityString((Entity)this);
        return StatCollector.translateToLocal((String)("entity." + s + ".name"));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
        LOTREntityZebra3 zebra = (LOTREntityZebra3)super.createChild(entityageable);
        return zebra;
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }
}

