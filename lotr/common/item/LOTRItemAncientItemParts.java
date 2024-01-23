/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRItemAncientItemParts
extends Item {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] icons;
    private String[] partNames = new String[]{"swordTip", "swordBlade", "swordHilt", "armorPlate"};

    public LOTRItemAncientItemParts() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
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
        this.icons = new IIcon[this.partNames.length];
        for (int i = 0; i < this.partNames.length; ++i) {
            this.icons[i] = iconregister.registerIcon(this.getIconString() + "_" + this.partNames[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= 3; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

