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

public class LOTRItemBowPart
extends Item {
    @SideOnly(value=Side.CLIENT)
    private static IIcon[] keyIcons;
    private static final String[] keyParts;

    public LOTRItemBowPart() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if (damage >= keyIcons.length) {
            damage = 0;
        }
        return keyIcons[damage];
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        return super.getUnlocalizedName() + "." + itemstack.getItemDamage();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        keyIcons = new IIcon[keyParts.length];
        for (int i = 0; i < keyParts.length; ++i) {
            LOTRItemBowPart.keyIcons[i] = iconregister.registerIcon(this.getIconString() + "_" + keyParts[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < keyParts.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    static {
        keyParts = new String[]{"upper_limb", "handle", "lower_limb"};
    }
}

