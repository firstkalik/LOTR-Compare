/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockColored
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.oredict.OreDictionary
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.BlockColored;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

public class LOTRItemDye
extends Item {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] dyeIcons;
    private String[] dyeNames = new String[]{"elanor", "niphredil", "bluebell", "green", "charcoal", "brown"};

    public LOTRItemDye() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
    }

    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer entityplayer, EntityLivingBase entityliving) {
        if (entityliving instanceof EntitySheep) {
            EntitySheep sheep = (EntitySheep)entityliving;
            int dye = LOTRItemDye.isItemDye(itemstack);
            if (dye == -1) {
                return false;
            }
            int blockDye = BlockColored.func_150031_c((int)dye);
            if (!sheep.getSheared() && sheep.getFleeceColor() != blockDye) {
                sheep.setFleeceColor(blockDye);
                --itemstack.stackSize;
            }
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int i) {
        if (i >= this.dyeIcons.length) {
            i = 0;
        }
        return this.dyeIcons[i];
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        return super.getUnlocalizedName() + "." + itemstack.getItemDamage();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        this.dyeIcons = new IIcon[this.dyeNames.length];
        for (int i = 0; i < this.dyeNames.length; ++i) {
            this.dyeIcons[i] = iconregister.registerIcon(this.getIconString() + "_" + this.dyeNames[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.dyeNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public static int isItemDye(ItemStack itemstack) {
        int[] oreIDs;
        if (itemstack.getItem() == Items.dye) {
            return itemstack.getItemDamage();
        }
        for (int id : oreIDs = OreDictionary.getOreIDs((ItemStack)itemstack)) {
            String oreName = OreDictionary.getOreName((int)id);
            for (int j = 0; j <= 15; ++j) {
                ItemStack dye = new ItemStack(Items.dye, 1, j);
                if (!LOTRMod.isOreNameEqual(dye, oreName)) continue;
                return j;
            }
        }
        return -1;
    }
}

