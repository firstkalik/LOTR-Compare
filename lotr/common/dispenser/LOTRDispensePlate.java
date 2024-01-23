/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.dispenser.BehaviorProjectileDispense
 *  net.minecraft.dispenser.IPosition
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.world.World
 */
package lotr.common.dispenser;

import lotr.common.entity.projectile.LOTREntityPlate;
import net.minecraft.block.Block;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;

public class LOTRDispensePlate
extends BehaviorProjectileDispense {
    private Block plateBlock;

    public LOTRDispensePlate(Block block) {
        this.plateBlock = block;
    }

    protected IProjectile getProjectileEntity(World world, IPosition position) {
        return new LOTREntityPlate(world, this.plateBlock, position.getX(), position.getY(), position.getZ());
    }
}

