/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.List;
import lotr.common.item.D1;
import lotr.common.item.D2;
import lotr.common.item.D3;
import lotr.common.item.D4;
import lotr.common.item.D5;
import lotr.common.item.D6;
import lotr.common.item.D7;
import lotr.common.item.H1;
import lotr.common.item.LOTRItemCoin;
import lotr.common.item.LOTRItemGem;
import lotr.common.item.LOTRItemRing;
import lotr.common.item.Naria;
import lotr.common.item.Nenia;
import lotr.common.item.Thorin;
import lotr.common.item.Vilia;
import lotr.common.item.arven;
import lotr.common.item.aule;
import lotr.common.item.elrond;
import lotr.common.item.elrondsilver;
import lotr.common.item.este;
import lotr.common.item.farin;
import lotr.common.item.haldir;
import lotr.common.item.irmo;
import lotr.common.item.khain;
import lotr.common.item.kibil;
import lotr.common.item.lesserfire;
import lotr.common.item.lesserivisible;
import lotr.common.item.lesserjump;
import lotr.common.item.lesserlight;
import lotr.common.item.lessermining;
import lotr.common.item.lessernightvision;
import lotr.common.item.lesserpower;
import lotr.common.item.lesserresistance;
import lotr.common.item.lessersaturation;
import lotr.common.item.lesserspeed;
import lotr.common.item.lesserstrenght;
import lotr.common.item.lessersuicide;
import lotr.common.item.lesserwatherbreathing;
import lotr.common.item.light;
import lotr.common.item.linhir;
import lotr.common.item.manve;
import lotr.common.item.melkor;
import lotr.common.item.melkor2;
import lotr.common.item.namo;
import lotr.common.item.narchuil;
import lotr.common.item.nessa;
import lotr.common.item.nienna;
import lotr.common.item.numenor;
import lotr.common.item.orome;
import lotr.common.item.ringBarachir;
import lotr.common.item.ringShaman;
import lotr.common.item.ringSmithing;
import lotr.common.item.sarumanring;
import lotr.common.item.theOneRing;
import lotr.common.item.thorinrune;
import lotr.common.item.thranduilmithril;
import lotr.common.item.thranduilsilver;
import lotr.common.item.thranduilsnake;
import lotr.common.item.tulkas;
import lotr.common.item.ulmo;
import lotr.common.item.vaire;
import lotr.common.item.vana;
import lotr.common.item.varda;
import lotr.common.item.yavanna;
import lotr.common.recipe.LOTRRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRValuableItems {
    private static List<ItemStack> toolMaterials = new ArrayList<ItemStack>();
    private static boolean initTools = false;

    private static void registerToolMaterials() {
        if (!initTools) {
            toolMaterials.clear();
            for (Item.ToolMaterial material : Item.ToolMaterial.values()) {
                ItemStack repair;
                if (material.getHarvestLevel() < 2 || (repair = material.getRepairItemStack()) == null) continue;
                toolMaterials.add(repair.copy());
            }
            initTools = true;
        }
    }

    public static List<ItemStack> getToolMaterials() {
        LOTRValuableItems.registerToolMaterials();
        return toolMaterials;
    }

    public static boolean canMagpieSteal(ItemStack itemstack) {
        LOTRValuableItems.registerToolMaterials();
        Item item = itemstack.getItem();
        if (item instanceof LOTRItemCoin || item instanceof LOTRItemGem) {
            return true;
        }
        if (item instanceof LOTRItemRing && (item instanceof theOneRing || item instanceof ringBarachir || item instanceof ringShaman || item instanceof ringSmithing || item instanceof Vilia || item instanceof Nenia || item instanceof Naria || item instanceof D1 || item instanceof D2 || item instanceof D3 || item instanceof D4 || item instanceof D5 || item instanceof D6 || item instanceof D7 || item instanceof H1 || item instanceof sarumanring || item instanceof elrond || item instanceof elrondsilver || item instanceof narchuil || item instanceof numenor || item instanceof Thorin || item instanceof thorinrune || item instanceof arven || item instanceof thranduilsilver || item instanceof thranduilsnake || item instanceof thranduilmithril || item instanceof lesserfire || item instanceof lesserivisible || item instanceof lesserjump || item instanceof lesserlight || item instanceof lessernightvision || item instanceof lesserpower || item instanceof lessermining || item instanceof lessersaturation || item instanceof lesserresistance || item instanceof lesserspeed || item instanceof lesserstrenght || item instanceof lesserwatherbreathing || item instanceof lessersuicide || item instanceof aule || item instanceof este || item instanceof irmo || item instanceof manve || item instanceof melkor || item instanceof melkor2 || item instanceof namo || item instanceof nessa || item instanceof nienna || item instanceof orome || item instanceof tulkas || item instanceof ulmo || item instanceof vaire || item instanceof vana || item instanceof varda || item instanceof yavanna || item instanceof light || item instanceof linhir || item instanceof farin || item instanceof haldir || item instanceof khain || item instanceof kibil)) {
            return true;
        }
        for (ItemStack listItem : toolMaterials) {
            if (!LOTRRecipes.checkItemEquals(listItem, itemstack)) continue;
            return true;
        }
        return false;
    }
}

