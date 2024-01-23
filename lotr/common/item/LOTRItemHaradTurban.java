/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.item.LOTRItemHaradRobes;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class LOTRItemHaradTurban
extends LOTRItemHaradRobes {
    @SideOnly(value=Side.CLIENT)
    private IIcon ornamentIcon;

    public LOTRItemHaradTurban() {
        super(0);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        super.registerIcons(iconregister);
        this.ornamentIcon = iconregister.registerIcon(this.getIconString() + "_ornament");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack itemstack, int pass) {
        if (pass == 1 && LOTRItemHaradTurban.hasOrnament(itemstack)) {
            return this.ornamentIcon;
        }
        return this.itemIcon;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        if (pass == 1 && LOTRItemHaradTurban.hasOrnament(itemstack)) {
            return 16777215;
        }
        return super.getColorFromItemStack(itemstack, pass);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        super.addInformation(itemstack, entityplayer, list, flag);
        if (LOTRItemHaradTurban.hasOrnament(itemstack)) {
            list.add(StatCollector.translateToLocal((String)"item.lotr.haradRobes.ornament"));
        }
    }

    public static boolean hasOrnament(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("TurbanOrnament")) {
            return itemstack.getTagCompound().getBoolean("TurbanOrnament");
        }
        return false;
    }

    public static void setHasOrnament(ItemStack itemstack, boolean flag) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setBoolean("TurbanOrnament", flag);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "lotr:armor/harad_turban.png";
    }
}

