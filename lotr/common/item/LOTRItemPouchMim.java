/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryEnderChest
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemPouchMim
extends Item {
    private static int POUCH_COLOR = 10841676;
    @SideOnly(value=Side.CLIENT)
    private IIcon pouchIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon pouchIconOpen;
    @SideOnly(value=Side.CLIENT)
    private IIcon overlayIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon overlayIconOpen;

    public LOTRItemPouchMim() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.displayGUIChest((IInventory)player.getInventoryEnderChest());
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.pouchIcon = iconRegister.registerIcon(this.getIconString() + "_bundle");
        this.pouchIconOpen = iconRegister.registerIcon(this.getIconString() + "_bundle_open");
        this.overlayIcon = iconRegister.registerIcon(this.getIconString() + "_bundle_overlay");
        this.overlayIconOpen = iconRegister.registerIcon(this.getIconString() + "_bundle_open_overlay");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack itemstack, int pass) {
        IInventory inventory;
        EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
        boolean isOpen = false;
        if (entityplayer.openContainer instanceof ContainerChest && (inventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory()) instanceof InventoryEnderChest) {
            isOpen = true;
        }
        return isOpen ? (pass > 0 ? this.overlayIconOpen : this.pouchIconOpen) : (pass > 0 ? this.overlayIcon : this.pouchIcon);
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        return pass == 0 ? LOTRItemPouchMim.getPouchColor(itemstack) : 16777215;
    }

    public static int getPouchColor(ItemStack itemstack) {
        int dyeColor = LOTRItemPouchMim.getSavedDyeColor(itemstack);
        return dyeColor != -1 ? dyeColor : POUCH_COLOR;
    }

    private static int getSavedDyeColor(ItemStack itemstack) {
        NBTTagCompound tag = itemstack.getTagCompound();
        return tag != null && tag.hasKey("PouchColor") ? tag.getInteger("PouchColor") : -1;
    }

    public static boolean isPouchDyed(ItemStack itemstack) {
        return LOTRItemPouchMim.getSavedDyeColor(itemstack) != -1;
    }

    public static void setPouchColor(ItemStack itemstack, int color) {
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setInteger("PouchColor", color);
    }

    public static void removePouchDye(ItemStack itemstack) {
        if (itemstack.hasTagCompound()) {
            itemstack.getTagCompound().removeTag("PouchColor");
        }
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        return super.getUnlocalizedName();
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        if (LOTRItemPouchMim.isPouchDyed(itemstack)) {
            list.add(StatCollector.translateToLocal((String)"item.lotr.pouch.dyed"));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
    }
}

