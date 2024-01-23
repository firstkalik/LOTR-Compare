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

import lotr.common.entity.projectile.LOTREntityDart;
import lotr.common.item.LOTRItemDart;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRDispenseDart
extends BehaviorProjectileDispense {
    private LOTRItemDart theDartItem;

    public LOTRDispenseDart(LOTRItemDart item) {
        this.theDartItem = item;
    }

    protected IProjectile getProjectileEntity(World world, IPosition iposition) {
        ItemStack itemstack = new ItemStack((Item)this.theDartItem);
        LOTREntityDart dart = this.theDartItem.createDart(world, itemstack, iposition.getX(), iposition.getY(), iposition.getZ());
        dart.canBePickedUp = 1;
        return dart;
    }

    protected float func_82500_b() {
        return 1.5f;
    }

    protected void playDispenseSound(IBlockSource source) {
        source.getWorld().playSoundEffect((double)source.getXInt(), (double)source.getYInt(), (double)source.getZInt(), "lotr:item.dart", 1.0f, 1.2f);
    }
}

