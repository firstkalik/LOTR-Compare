/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.entity.item.LOTREntityGiraffeRug;
import lotr.common.entity.item.LOTREntityRugBase;
import lotr.common.item.LOTRItemRugBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemGiraffeRug
extends LOTRItemRugBase {
    public LOTRItemGiraffeRug() {
        super("giraffe");
    }

    @Override
    protected LOTREntityRugBase createRug(World world, ItemStack itemstack) {
        LOTREntityGiraffeRug rug = new LOTREntityGiraffeRug(world);
        return rug;
    }
}

