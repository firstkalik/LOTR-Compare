/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.item.LOTREntityBearRug;
import lotr.common.entity.item.LOTREntityRugBase;
import lotr.common.item.LOTRItemRugBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemBearRug
extends LOTRItemRugBase {
    public LOTRItemBearRug() {
        super(LOTREntityBear.BearType.bearTypeNames());
    }

    @Override
    protected LOTREntityRugBase createRug(World world, ItemStack itemstack) {
        LOTREntityBearRug rug = new LOTREntityBearRug(world);
        rug.setRugType(LOTREntityBear.BearType.forID(itemstack.getItemDamage()));
        return rug;
    }
}

