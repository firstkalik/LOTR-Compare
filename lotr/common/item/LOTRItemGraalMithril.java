/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 */
package lotr.common.item;

import lotr.common.item.LOTRItemGraal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class LOTRItemGraalMithril
extends LOTRItemGraal {
    private IIcon fullIcon;
    private IIcon halfIcon;
    private IIcon partialIcon;
    private IIcon emptyIcon;

    public LOTRItemGraalMithril() {
        this.maxDrinks = 3;
    }

    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
        this.fullIcon = iconRegister.registerIcon("lotr:graal_mithril_full");
        this.halfIcon = iconRegister.registerIcon("lotr:graal_mithril_half");
        this.partialIcon = iconRegister.registerIcon("lotr:graal_mithril_partial");
        this.emptyIcon = iconRegister.registerIcon("lotr:graal_mithril_empty");
    }

    public IIcon getIcon(ItemStack itemstack, int index) {
        int hasDrink;
        int n = hasDrink = itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("hasDrink") ? itemstack.getTagCompound().getInteger("hasDrink") : 0;
        if (hasDrink == 3) {
            return this.fullIcon;
        }
        if (hasDrink == 2) {
            return this.halfIcon;
        }
        if (hasDrink == 1) {
            return this.partialIcon;
        }
        return this.emptyIcon;
    }

    public IIcon getIconIndex(ItemStack itemstack) {
        int hasDrink;
        int n = hasDrink = itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("hasDrink") ? itemstack.getTagCompound().getInteger("hasDrink") : 0;
        if (hasDrink == 3) {
            return this.fullIcon;
        }
        if (hasDrink == 2) {
            return this.halfIcon;
        }
        if (hasDrink == 1) {
            return this.partialIcon;
        }
        return this.emptyIcon;
    }
}

