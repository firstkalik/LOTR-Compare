/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.tileentity;

import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityAlloyForgeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTRTileEntityMoriaForgeMark2
extends LOTRTileEntityAlloyForgeBase {
    @Override
    public String getForgeName() {
        return StatCollector.translateToLocal((String)"container.lotr.moriaForgeMark2");
    }

    @Override
    public ItemStack getSmeltingResult(ItemStack itemstack) {
        if (itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreMithril)) {
            return new ItemStack(LOTRMod.mithril, 1);
        }
        if (itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreMithril2)) {
            return new ItemStack(LOTRMod.mithril, 1);
        }
        if (itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreSilver)) {
            return new ItemStack(LOTRMod.silver, 1);
        }
        if (itemstack.getItem() == Item.getItemFromBlock((Block)Blocks.gold_ore)) {
            return new ItemStack(Items.gold_ingot, 1);
        }
        if (itemstack.getItem() == Item.getItemFromBlock((Block)Blocks.iron_ore)) {
            return new ItemStack(Items.iron_ingot, 1);
        }
        if (itemstack.getItem() == LOTRMod.ironRaw) {
            return new ItemStack(Items.iron_ingot, 1);
        }
        if (itemstack.getItem() == LOTRMod.goldRaw) {
            return new ItemStack(Items.gold_ingot, 1);
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
        if (this.isIron1(itemstack) && this.isDwarvenNugget(alloyItem)) {
            return new ItemStack(LOTRMod.dwarfSteel, 2);
        }
        if (this.isSilverOre(itemstack) && this.isSilverNugget(alloyItem)) {
            return new ItemStack(LOTRMod.silver, 2);
        }
        if (this.isGoldOre(itemstack) && this.isGoldNugget(alloyItem) || this.isGoldNugget(itemstack) && this.isGoldOre(alloyItem)) {
            return new ItemStack(Items.gold_ingot, 2);
        }
        if (this.isIronOre(itemstack) && this.isIronNugget(alloyItem) || this.isIronNugget(itemstack) && this.isIronOre(alloyItem)) {
            return new ItemStack(Items.iron_ingot, 2);
        }
        if (this.isGold1(itemstack) && this.isGoldNugget(alloyItem) || this.isGoldNugget(itemstack) && this.isGold1(alloyItem)) {
            return new ItemStack(Items.gold_ingot, 2);
        }
        if (this.isIron1(itemstack) && this.isIronNugget(alloyItem) || this.isIronNugget(itemstack) && this.isIron1(alloyItem)) {
            return new ItemStack(Items.iron_ingot, 2);
        }
        if (this.isMithril(itemstack) && this.isMithrilNugget(alloyItem)) {
            return new ItemStack(LOTRMod.mithril, 2);
        }
        if (this.isMithrilNugget(itemstack) && this.isMithrilPlus(alloyItem) || this.isMithrilPlus(itemstack) && this.isMithrilNugget(alloyItem)) {
            return new ItemStack(LOTRMod.mithril, 2);
        }
        if (this.isCopper(itemstack) && this.isTin(itemstack) || this.isTin(itemstack) && this.isCopper(itemstack)) {
            return new ItemStack(LOTRMod.bronze, 2);
        }
        if (this.isIron(itemstack) && alloyItem.getItem() == LOTRMod.quenditeCrystal) {
            return new ItemStack(LOTRMod.galvorn, 2);
        }
        if (this.isIron(itemstack) && alloyItem.getItem() == Item.getItemFromBlock((Block)LOTRMod.rock) && alloyItem.getItemDamage() == 3) {
            return new ItemStack(LOTRMod.blueDwarfSteel, 1);
        }
        if (this.isIronRaw(itemstack) && alloyItem.getItem() == Item.getItemFromBlock((Block)LOTRMod.rock) && alloyItem.getItemDamage() == 3) {
            return new ItemStack(LOTRMod.blueDwarfSteel, 1);
        }
        return super.getAlloySmeltingResult(itemstack, alloyItem);
    }
}

