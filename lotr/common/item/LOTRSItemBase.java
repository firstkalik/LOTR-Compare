/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRConfig;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRSItemBase
extends Item {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] icons;
    private String[] itemNames = new String[]{"sword", "dagger", "helmet", "body", "legs", "boots"};

    public LOTRSItemBase() {
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isRemote) {
            ItemStack ancientItem = LOTRSItemBase.getRandomItem(itemstack);
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.craftAncientItem);
            world.playSoundAtEntity((Entity)entityplayer, "random.pop", 0.2f, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            return ancientItem;
        }
        return itemstack;
    }

    public static ItemStack getRandomItem(ItemStack itemstack) {
        ItemStack randomItem = null;
        InventoryBasic randomItemInv = new InventoryBasic("ancientItem", true, 1);
        LOTRChestContents itemPool = null;
        if (itemstack.getItemDamage() == 0) {
            itemPool = LOTRChestContents.ANCIENT_SWORD;
        } else if (itemstack.getItemDamage() == 1) {
            itemPool = LOTRChestContents.ANCIENT_DAGGER;
        } else if (itemstack.getItemDamage() == 2) {
            itemPool = LOTRChestContents.ANCIENT_HELMET;
        } else if (itemstack.getItemDamage() == 3) {
            itemPool = LOTRChestContents.ANCIENT_BODY;
        } else if (itemstack.getItemDamage() == 4) {
            itemPool = LOTRChestContents.ANCIENT_LEGS;
        } else if (itemstack.getItemDamage() == 5) {
            itemPool = LOTRChestContents.ANCIENT_BOOTS;
        }
        LOTRChestContents.fillInventory((IInventory)randomItemInv, itemRand, itemPool, 1);
        randomItem = randomItemInv.getStackInSlot(0);
        if (randomItem != null) {
            LOTREnchantment wraithbane;
            if (LOTRConfig.enchantingLOTR && (wraithbane = LOTREnchantment.baneWraith).canApply(randomItem, false) && itemRand.nextInt(4) == 0) {
                LOTREnchantmentHelper.setHasEnchant(randomItem, wraithbane);
            }
            return randomItem;
        }
        return itemstack;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int i) {
        if (i >= this.icons.length) {
            i = 0;
        }
        return this.icons[i];
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        return super.getUnlocalizedName() + "." + itemstack.getItemDamage();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        this.icons = new IIcon[this.itemNames.length];
        for (int i = 0; i < this.itemNames.length; ++i) {
            this.icons[i] = iconregister.registerIcon(this.getIconString() + "_" + this.itemNames[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= 5; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

