/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.World
 */
package lotr.common.dispenser;

import lotr.common.entity.item.LOTREntityOrcBomb;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class LOTRDispenseOrcBomb
extends BehaviorDefaultDispenseItem {
    protected ItemStack dispenseStack(IBlockSource dispenser, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.func_149937_b((int)dispenser.getBlockMetadata());
        World world = dispenser.getWorld();
        int i = dispenser.getXInt() + enumfacing.getFrontOffsetX();
        int j = dispenser.getYInt() + enumfacing.getFrontOffsetY();
        int k = dispenser.getZInt() + enumfacing.getFrontOffsetZ();
        LOTREntityOrcBomb bomb = new LOTREntityOrcBomb(world, (float)i + 0.5f, (float)j + 0.5f, (float)k + 0.5f, null);
        bomb.fuse += itemstack.getItemDamage() * 10;
        bomb.setBombStrengthLevel(itemstack.getItemDamage());
        bomb.droppedByPlayer = true;
        world.spawnEntityInWorld((Entity)bomb);
        --itemstack.stackSize;
        return itemstack;
    }
}

