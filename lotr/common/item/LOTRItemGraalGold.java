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

public class LOTRItemGraalGold
extends LOTRItemGraal {
    private IIcon fullIcon;
    private IIcon halfIcon;
    private IIcon emptyIcon;

    public LOTRItemGraalGold() {
        this.maxDrinks = 2;
    }

    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
        this.fullIcon = iconRegister.registerIcon("lotr:graal_gold_full");
        this.halfIcon = iconRegister.registerIcon("lotr:graal_gold_half");
        this.emptyIcon = iconRegister.registerIcon("lotr:graal_gold_empty");
    }

    public IIcon getIcon(ItemStack itemstack, int index) {
        int hasDrink;
        int n = hasDrink = itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("hasDrink") ? itemstack.getTagCompound().getInteger("hasDrink") : 0;
        if (hasDrink == 2) {
            return this.fullIcon;
        }
        if (hasDrink == 1) {
            return this.halfIcon;
        }
        return this.emptyIcon;
    }

    public IIcon getIconIndex(ItemStack itemstack) {
        int hasDrink;
        int n = hasDrink = itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("hasDrink") ? itemstack.getTagCompound().getInteger("hasDrink") : 0;
        if (hasDrink == 2) {
            return this.fullIcon;
        }
        if (hasDrink == 1) {
            return this.halfIcon;
        }
        return this.emptyIcon;
    }
}

