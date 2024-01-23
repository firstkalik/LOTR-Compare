/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.dispenser.BehaviorProjectileDispense
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.dispenser.IPosition
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.dispenser;

import lotr.common.entity.projectile.LOTREntityCrossbowBolt;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRDispenseCrossbowBolt
extends BehaviorProjectileDispense {
    private Item theBoltItem;

    public LOTRDispenseCrossbowBolt(Item item) {
        this.theBoltItem = item;
    }

    protected IProjectile getProjectileEntity(World world, IPosition iposition) {
        ItemStack itemstack = new ItemStack(this.theBoltItem);
        LOTREntityCrossbowBolt bolt = new LOTREntityCrossbowBolt(world, itemstack, iposition.getX(), iposition.getY(), iposition.getZ());
        bolt.canBePickedUp = 1;
        return bolt;
    }

    protected void playDispenseSound(IBlockSource source) {
        source.getWorld().playSoundEffect((double)source.getXInt(), (double)source.getYInt(), (double)source.getZInt(), "lotr:item.crossbow", 1.0f, 1.2f);
    }
}

