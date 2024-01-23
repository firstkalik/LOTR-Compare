/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.World
 */
package lotr.common.dispenser;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemSpawnEgg;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class LOTRDispenseSpawnEgg
extends BehaviorDefaultDispenseItem {
    public ItemStack dispenseStack(IBlockSource dispenser, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.func_149937_b((int)dispenser.getBlockMetadata());
        double d = dispenser.getX() + (double)enumfacing.getFrontOffsetX();
        double d1 = (double)dispenser.getYInt() + 0.2;
        double d2 = dispenser.getZ() + (double)enumfacing.getFrontOffsetZ();
        Entity entity = LOTRItemSpawnEgg.spawnCreature(dispenser.getWorld(), itemstack.getItemDamage(), d, d1, d2);
        if (entity instanceof EntityLiving && itemstack.hasDisplayName()) {
            ((EntityLiving)entity).setCustomNameTag(itemstack.getDisplayName());
        }
        if (entity instanceof LOTREntityNPC) {
            ((LOTREntityNPC)entity).isNPCPersistent = true;
        }
        itemstack.splitStack(1);
        return itemstack;
    }
}

