/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.entity.item.LOTREntityRugBase;
import lotr.common.entity.item.LOTREntityWargskinRug;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.item.LOTRItemRugBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemWargskinRug
extends LOTRItemRugBase {
    public LOTRItemWargskinRug() {
        super(LOTREntityWarg.WargType.wargTypeNames());
    }

    @Override
    protected LOTREntityRugBase createRug(World world, ItemStack itemstack) {
        LOTREntityWargskinRug rug = new LOTREntityWargskinRug(world);
        rug.setRugType(LOTREntityWarg.WargType.forID(itemstack.getItemDamage()));
        return rug;
    }
}

