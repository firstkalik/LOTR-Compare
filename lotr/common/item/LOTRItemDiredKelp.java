/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.item.LOTRItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class LOTRItemDiredKelp
extends LOTRItemFood {
    private static List<Item> allBerries = new ArrayList<Item>();
    private boolean isPoisonous = false;

    public LOTRItemDiredKelp() {
        super(2, 0.2f, false);
        allBerries.add((Item)this);
    }

    public LOTRItemDiredKelp setPoisonous() {
        this.isPoisonous = true;
        return this;
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        ItemStack ret = super.onEaten(itemstack, world, entityplayer);
        if (this.isPoisonous && !world.isRemote) {
            int duration = 3 + world.rand.nextInt(4);
            PotionEffect poison = new PotionEffect(Potion.poison.id, duration * 20);
            entityplayer.addPotionEffect(poison);
        }
        return ret;
    }

    public static void registerAllBerries(String name) {
        for (Item berry : allBerries) {
            OreDictionary.registerOre((String)name, (Item)berry);
        }
    }
}

