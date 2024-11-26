/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemSpyGlass
extends Item {
    private static final float ZOOM_FOV = 25.0f;
    private static final float DEFAULT_FOV = 70.0f;

    public LOTRItemSpyGlass() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
        this.maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        boolean isZoomed;
        if (!world.isRemote) {
            return itemStack;
        }
        Minecraft mc = Minecraft.getMinecraft();
        boolean bl = isZoomed = mc.gameSettings.fovSetting == 25.0f;
        if (isZoomed) {
            mc.gameSettings.fovSetting = 70.0f;
            player.playSound("lotr:misc.stop", 1.0f, 1.0f);
        } else {
            mc.gameSettings.fovSetting = 25.0f;
            player.playSound("lotr:misc.use", 1.0f, 1.0f);
        }
        return itemStack;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (world.isRemote && entity instanceof EntityPlayer) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer player = (EntityPlayer)entity;
            boolean isZoomed = mc.gameSettings.fovSetting == 25.0f;
            ItemStack heldItem = player.getHeldItem();
            if (!isSelected && isZoomed && (heldItem == null || heldItem.getItem() != this)) {
                mc.gameSettings.fovSetting = 70.0f;
            }
        }
    }
}

