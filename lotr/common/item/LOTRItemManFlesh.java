/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.FoodStats
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class LOTRItemManFlesh
extends ItemFood {
    public LOTRItemManFlesh(int i, float f, boolean flag) {
        super(i, f, flag);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
    }

    public static List<LOTRFaction> getManFleshFactions() {
        return LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC, LOTRFaction.FactionType.TYPE_TROLL);
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        --itemstack.stackSize;
        boolean orcAligned = false;
        for (LOTRFaction faction : LOTRItemManFlesh.getManFleshFactions()) {
            float alignment = LOTRLevelData.getData(entityplayer).getAlignment(faction);
            if (alignment <= 0.0f) continue;
            orcAligned = true;
            break;
        }
        if (orcAligned) {
            entityplayer.getFoodStats().func_151686_a((ItemFood)this, itemstack);
            if (!world.isRemote) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.eatManFlesh);
            }
        } else if (!world.isRemote) {
            int dur = 30;
            entityplayer.addPotionEffect(new PotionEffect(Potion.hunger.id, dur * 20));
        }
        world.playSoundAtEntity((Entity)entityplayer, "random.burp", 0.5f, world.rand.nextFloat() * 0.1f + 0.9f);
        this.onFoodEaten(itemstack, world, entityplayer);
        return itemstack;
    }
}

