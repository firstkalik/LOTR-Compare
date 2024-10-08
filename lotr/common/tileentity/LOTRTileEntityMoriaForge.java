/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.tileentity;

import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityAlloyForgeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTRTileEntityMoriaForge
extends LOTRTileEntityAlloyForgeBase {
    @Override
    public String getForgeName() {
        return StatCollector.translateToLocal((String)"container.lotr.moriaForge");
    }

    @Override
    public ItemStack getSmeltingResult(ItemStack itemstack) {
        if (itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreMithril)) {
            return new ItemStack(LOTRMod.mithril, 1);
        }
        if (itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreMithril2)) {
            return new ItemStack(LOTRMod.mithril, 1);
        }
        if (itemstack.getItem() == LOTRMod.mithrilRaw) {
            return new ItemStack(LOTRMod.mithril, 1);
        }
        return super.getSmeltingResult(itemstack);
    }

    @Override
    protected ItemStack getAlloySmeltingResult(ItemStack itemstack, ItemStack alloyItem) {
        if (this.isIron(itemstack) && this.isCoal(alloyItem)) {
            return new ItemStack(LOTRMod.dwarfSteel, 1);
        }
        if (this.isSilverOre(itemstack) && this.isSilverNugget(alloyItem)) {
            return new ItemStack(LOTRMod.silver, 2);
        }
        if (this.isCopper(itemstack) && this.isTin(itemstack) || this.isTin(itemstack) && this.isCopper(itemstack)) {
            return new ItemStack(LOTRMod.bronze, 2);
        }
        if (this.isIron(itemstack) && alloyItem.getItem() == LOTRMod.quenditeCrystal) {
            return new ItemStack(LOTRMod.galvorn, 1);
        }
        if (this.isIron(itemstack) && alloyItem.getItem() == Item.getItemFromBlock((Block)LOTRMod.rock) && alloyItem.getItemDamage() == 3) {
            return new ItemStack(LOTRMod.blueDwarfSteel, 1);
        }
        if (this.isGold1(itemstack) && this.isGoldNugget(alloyItem) || this.isGoldNugget(itemstack) && this.isGold1(alloyItem)) {
            return new ItemStack(Items.gold_ingot, 2);
        }
        if (this.isIron1(itemstack) && this.isIronNugget(alloyItem) || this.isIronNugget(itemstack) && this.isIron1(alloyItem)) {
            return new ItemStack(Items.iron_ingot, 2);
        }
        return super.getAlloySmeltingResult(itemstack, alloyItem);
    }
}

