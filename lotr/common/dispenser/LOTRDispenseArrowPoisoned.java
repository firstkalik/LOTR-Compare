/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.dispenser.BehaviorProjectileDispense
 *  net.minecraft.dispenser.IPosition
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.world.World
 */
package lotr.common.dispenser;

import lotr.common.entity.item.LOTREntityArrowPoisoned;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class LOTRDispenseArrowPoisoned
extends BehaviorProjectileDispense {
    protected IProjectile getProjectileEntity(World world, IPosition iposition) {
        LOTREntityArrowPoisoned arrow = new LOTREntityArrowPoisoned(world, iposition.getX(), iposition.getY(), iposition.getZ());
        arrow.canBePickedUp = 1;
        return arrow;
    }
}

