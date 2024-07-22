/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.dispenser;

import lotr.common.block.LOTRBlockRhunFireJar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRDispenseRhunFireJar
extends BehaviorDefaultDispenseItem {
    private final BehaviorDefaultDispenseItem dispenseDefault = new BehaviorDefaultDispenseItem();

    protected ItemStack dispenseStack(IBlockSource dispenser, ItemStack itemstack) {
        int k;
        int i;
        int j;
        EnumFacing enumfacing = BlockDispenser.func_149937_b((int)dispenser.getBlockMetadata());
        World world = dispenser.getWorld();
        if (world.getBlock(i = dispenser.getXInt() + enumfacing.getFrontOffsetX(), j = dispenser.getYInt() + enumfacing.getFrontOffsetY(), k = dispenser.getZInt() + enumfacing.getFrontOffsetZ()).isReplaceable((IBlockAccess)world, i, j, k)) {
            LOTRBlockRhunFireJar.explodeOnAdded = false;
            world.setBlock(i, j, k, Block.getBlockFromItem((Item)itemstack.getItem()), itemstack.getItemDamage(), 3);
            LOTRBlockRhunFireJar.explodeOnAdded = true;
            --itemstack.stackSize;
            return itemstack;
        }
        return this.dispenseDefault.dispense(dispenser, itemstack);
    }
}

