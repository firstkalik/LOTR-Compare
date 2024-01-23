/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.dispenser.IPosition
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.World
 */
package lotr.common.dispenser;

import lotr.common.entity.projectile.LOTREntityThrowingAxe;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class LOTRDispenseThrowingAxe
extends BehaviorDefaultDispenseItem {
    public ItemStack dispenseStack(IBlockSource dispenser, ItemStack itemstack) {
        World world = dispenser.getWorld();
        IPosition iposition = BlockDispenser.func_149939_a((IBlockSource)dispenser);
        EnumFacing enumfacing = BlockDispenser.func_149937_b((int)dispenser.getBlockMetadata());
        LOTREntityThrowingAxe axe = new LOTREntityThrowingAxe(world, itemstack.copy(), iposition.getX(), iposition.getY(), iposition.getZ());
        axe.setThrowableHeading(enumfacing.getFrontOffsetX(), (float)enumfacing.getFrontOffsetY() + 0.1f, enumfacing.getFrontOffsetZ(), 1.1f, 6.0f);
        axe.canBePickedUp = 1;
        world.spawnEntityInWorld((Entity)axe);
        itemstack.splitStack(1);
        return itemstack;
    }

    protected void playDispenseSound(IBlockSource dispenser) {
        dispenser.getWorld().playAuxSFX(1002, dispenser.getXInt(), dispenser.getYInt(), dispenser.getZInt(), 0);
    }
}

