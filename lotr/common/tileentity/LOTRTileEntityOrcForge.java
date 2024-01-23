/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.tileentity;

import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityAlloyForgeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTRTileEntityOrcForge
extends LOTRTileEntityAlloyForgeBase {
    @Override
    public String getForgeName() {
        return StatCollector.translateToLocal((String)"container.lotr.orcForge");
    }

    @Override
    public ItemStack getSmeltingResult(ItemStack itemstack) {
        ItemFood food;
        if (this.isWood(itemstack)) {
            boolean isCharred;
            boolean bl = isCharred = itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.wood) && itemstack.getItemDamage() == 3;
            if (!isCharred) {
                return new ItemStack(LOTRMod.wood, 1, 3);
            }
        }
        if (itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreMorgulIron)) {
            return new ItemStack(LOTRMod.orcSteel);
        }
        if (itemstack.getItem() instanceof ItemFood && (food = (ItemFood)itemstack.getItem()).isWolfsFavoriteMeat()) {
            return new ItemStack(Items.rotten_flesh);
        }
        return super.getSmeltingResult(itemstack);
    }

    @Override
    protected ItemStack getAlloySmeltingResult(ItemStack itemstack, ItemStack alloyItem) {
        if (this.isIron(itemstack) && this.isCoal(alloyItem)) {
            return new ItemStack(LOTRMod.urukSteel);
        }
        if (this.isOrcSteel(itemstack) && alloyItem.getItem() == LOTRMod.guldurilCrystal) {
            return new ItemStack(LOTRMod.morgulSteel);
        }
        if (this.isOrcSteel(itemstack) && alloyItem.getItem() == LOTRMod.nauriteGem) {
            return new ItemStack(LOTRMod.blackUrukSteel);
        }
        return super.getAlloySmeltingResult(itemstack, alloyItem);
    }
}

