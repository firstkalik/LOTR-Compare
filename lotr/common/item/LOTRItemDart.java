/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IRegistry
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.dispenser.LOTRDispenseDart;
import lotr.common.entity.projectile.LOTREntityDart;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IRegistry;
import net.minecraft.world.World;

public class LOTRItemDart
extends Item {
    public boolean isPoisoned = false;
    public boolean isDrunk = false;

    public LOTRItemDart() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        BlockDispenser.dispenseBehaviorRegistry.putObject((Object)this, (Object)new LOTRDispenseDart(this));
    }

    public LOTRItemDart setPoisoned() {
        this.isPoisoned = true;
        return this;
    }

    public LOTRItemDart setDrunk() {
        this.isDrunk = true;
        return this;
    }

    public LOTREntityDart createDart(World world, ItemStack itemstack, double d, double d1, double d2) {
        LOTREntityDart dart = new LOTREntityDart(world, itemstack, d, d1, d2);
        return dart;
    }

    public LOTREntityDart createDart(World world, EntityLivingBase entity, ItemStack itemstack, float charge) {
        LOTREntityDart dart = new LOTREntityDart(world, entity, itemstack, charge);
        return dart;
    }

    public LOTREntityDart createDart(World world, EntityLivingBase entity, EntityLivingBase target, ItemStack itemstack, float charge, float inaccuracy) {
        LOTREntityDart dart = new LOTREntityDart(world, entity, target, itemstack, charge, inaccuracy);
        return dart;
    }
}

