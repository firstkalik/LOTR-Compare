/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.util.IRegistry
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.dispenser.LOTRDispenseCrossbowBolt;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IRegistry;

public class LOTRItemCrossbowBolt
extends Item {
    public boolean isPoisoned = false;

    public LOTRItemCrossbowBolt() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        BlockDispenser.dispenseBehaviorRegistry.putObject((Object)this, (Object)new LOTRDispenseCrossbowBolt(this));
    }

    public LOTRItemCrossbowBolt setPoisoned() {
        this.isPoisoned = true;
        return this;
    }
}

