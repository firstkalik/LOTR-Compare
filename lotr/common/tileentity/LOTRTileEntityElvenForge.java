/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.tileentity;

import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityAlloyForgeBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTRTileEntityElvenForge
extends LOTRTileEntityAlloyForgeBase {
    @Override
    public String getForgeName() {
        return StatCollector.translateToLocal((String)"container.lotr.elvenForge");
    }

    @Override
    protected ItemStack getAlloySmeltingResult(ItemStack itemstack, ItemStack alloyItem) {
        if (this.isIron(itemstack) && this.isCoal(alloyItem)) {
            return new ItemStack(LOTRMod.elfSteel);
        }
        if (this.isSilver(itemstack) && this.isMithrilNugget(alloyItem)) {
            return new ItemStack(LOTRMod.ithildin);
        }
        return super.getAlloySmeltingResult(itemstack, alloyItem);
    }
}

