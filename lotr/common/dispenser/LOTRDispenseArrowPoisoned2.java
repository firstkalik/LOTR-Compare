/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.dispenser.BehaviorProjectileDispense
 *  net.minecraft.dispenser.IPosition
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.world.World
 */
package lotr.common.dispenser;

import lotr.common.entity.item.LOTREntityArrowMorgul;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;

public class LOTRDispenseArrowPoisoned2
extends BehaviorProjectileDispense {
    protected IProjectile getProjectileEntity(World world, IPosition iposition) {
        LOTREntityArrowMorgul arrow = new LOTREntityArrowMorgul(world, iposition.getX(), iposition.getY(), iposition.getZ());
        arrow.canBePickedUp = 1;
        return arrow;
    }
}
