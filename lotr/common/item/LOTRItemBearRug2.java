/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.entity.animal.LOTREntityBear2;
import lotr.common.entity.item.LOTREntityBearRug2;
import lotr.common.entity.item.LOTREntityRugBase;
import lotr.common.item.LOTRItemRugBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemBearRug2
extends LOTRItemRugBase {
    public LOTRItemBearRug2() {
        super(LOTREntityBear2.BearType.bearTypeNames());
    }

    @Override
    protected LOTREntityRugBase createRug(World world, ItemStack itemstack) {
        LOTREntityBearRug2 rug = new LOTREntityBearRug2(world);
        rug.setRugType(LOTREntityBear2.BearType.forID(itemstack.getItemDamage()));
        return rug;
    }
}

