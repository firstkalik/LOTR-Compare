/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.item.LOTRItemHaradRobes;
import lotr.common.item.LOTRMaterial;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRItemKaftan
extends LOTRItemHaradRobes {
    @SideOnly(value=Side.CLIENT)
    private IIcon overlayIcon;

    public LOTRItemKaftan(int slot) {
        super(LOTRMaterial.KAFTAN, slot);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        super.registerIcons(iconregister);
        this.overlayIcon = iconregister.registerIcon(this.getIconString() + "_overlay");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public IIcon getIcon(ItemStack itemstack, int pass) {
        if (pass >= 1) {
            return this.overlayIcon;
        }
        return super.getIcon(itemstack, pass);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        if (pass >= 1) {
            return 16777215;
        }
        return super.getColorFromItemStack(itemstack, pass);
    }

    public int getColor(ItemStack itemstack) {
        return LOTRItemKaftan.getRobesColor(itemstack);
    }
}

