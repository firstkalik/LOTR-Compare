/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class LOTREntityAnimalMF
extends EntityAnimal {
    public LOTREntityAnimalMF(World world) {
        super(world);
    }

    public abstract Class getAnimalMFBaseClass();

    public abstract boolean isMale();

    public boolean canMateWith(EntityAnimal mate) {
        LOTREntityAnimalMF mfMate = (LOTREntityAnimalMF)mate;
        if (mate == this) {
            return false;
        }
        if (this.getAnimalMFBaseClass().equals(mfMate.getAnimalMFBaseClass()) && this.isInLove() && mate.isInLove()) {
            boolean otherMale;
            boolean thisMale = this.isMale();
            return thisMale != (otherMale = mfMate.isMale());
        }
        return false;
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }
}

