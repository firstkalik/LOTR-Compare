/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.item;

import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityRugBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityGiraffeRug
extends LOTREntityRugBase {
    public LOTREntityGiraffeRug(World world) {
        super(world);
        this.setSize(2.0f, 0.3f);
    }

    @Override
    protected String getRugNoise() {
        return "";
    }

    @Override
    protected ItemStack getRugItem() {
        return new ItemStack(LOTRMod.giraffeRug);
    }
}

