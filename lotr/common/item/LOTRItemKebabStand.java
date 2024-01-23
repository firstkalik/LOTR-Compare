/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.tileentity.LOTRTileEntityKebabStand;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class LOTRItemKebabStand
extends ItemBlock {
    public LOTRItemKebabStand(Block block) {
        super(block);
    }

    public static void setKebabData(ItemStack itemstack, NBTTagCompound kebabData) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setTag("LOTRKebabData", (NBTBase)kebabData);
    }

    public static void setKebabData(ItemStack itemstack, LOTRTileEntityKebabStand kebabStand) {
        if (kebabStand.shouldSaveBlockData()) {
            NBTTagCompound kebabData = new NBTTagCompound();
            kebabStand.writeKebabStandToNBT(kebabData);
            LOTRItemKebabStand.setKebabData(itemstack, kebabData);
        }
    }

    public static NBTTagCompound getKebabData(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("LOTRKebabData")) {
            NBTTagCompound kebabData = itemstack.getTagCompound().getCompoundTag("LOTRKebabData");
            return kebabData;
        }
        return null;
    }

    public static void loadKebabData(ItemStack itemstack, LOTRTileEntityKebabStand kebabStand) {
        NBTTagCompound kebabData = LOTRItemKebabStand.getKebabData(itemstack);
        if (kebabData != null) {
            kebabStand.readKebabStandFromNBT(kebabData);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        NBTTagCompound kebabData = LOTRItemKebabStand.getKebabData(itemstack);
        if (kebabData != null) {
            LOTRTileEntityKebabStand kebabStand = new LOTRTileEntityKebabStand();
            kebabStand.readKebabStandFromNBT(kebabData);
            int meats = kebabStand.getMeatAmount();
            list.add(StatCollector.translateToLocalFormatted((String)"tile.lotr.kebabStand.meats", (Object[])new Object[]{meats}));
            if (kebabStand.isCooked()) {
                list.add(StatCollector.translateToLocal((String)"tile.lotr.kebabStand.cooked"));
            }
        }
    }
}

