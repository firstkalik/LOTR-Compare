/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public abstract class Wand
extends Item {
    public static String noChargeAttackSound = "random.bow";
    private List<ItemStack> allowedItems = null;

    public Wand() {
        this.maxStackSize = 1;
        this.setMaxDamage(2000);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    public abstract int getUseCost();

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.block;
    }

    public boolean getIsRepairable(ItemStack srcItemStack, ItemStack rawMaterial) {
        if (this.allowedItems == null) {
            this.allowedItems = OreDictionary.getOres((String)"ingotGold");
        }
        for (int i = 0; i < this.allowedItems.size(); ++i) {
            if (!this.allowedItems.get(i).getUnlocalizedName().equals(rawMaterial.getUnlocalizedName())) continue;
            return true;
        }
        return false;
    }

    public boolean isOutOfCharge(ItemStack srcItemStack) {
        return srcItemStack.getItemDamage() >= srcItemStack.getMaxDamage() - this.getUseCost();
    }

    protected void playSound(String soundID, World world, EntityPlayer playerEntity) {
        if (!world.isRemote) {
            world.playSoundAtEntity((Entity)playerEntity, soundID, 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
        }
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    public abstract int getBaseRepairCost();
}

