/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRStoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemEru
extends Item
implements LOTRStoryItem {
    public LOTRItemEru() {
        this.maxStackSize = 1;
        this.setMaxDamage(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDevelopers);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entity) {
        float var4 = 1.0f;
        int i = (int)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)var4);
        int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62 - (double)entity.yOffset);
        int k = (int)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)var4);
        try {
            Desktop.getDesktop().browse(new URI("https://vk.com/lotrmc"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        itemstack.damageItem(10, (EntityLivingBase)entity);
        if (Math.random() * 10.0 <= 100.0) {
            entity.inventory.addItemStackToInventory(new ItemStack(LOTRMod.spell_heal, 1));
            entity.inventory.addItemStackToInventory(new ItemStack(LOTRMod.spell_healthboost, 1));
        }
        if (Math.random() * 100.0 <= 100.0) {
            entity.inventory.addItemStackToInventory(new ItemStack(LOTRMod.daggerMithril, 1));
            LOTRLevelData.getData(entity).addAchievement(LOTRAchievement.enterLotr);
        }
        world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "lotr:lion.hurt", 2.0f, 1.0f);
        return itemstack;
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add("\u00a7eCode: \u00a76Mevans (Owner) ");
        list.add("\u00a7lSupport: \u00a72Hummel009 \u00a72DwarfyAssassin\u00a76 FirstKalik");
        list.add("\u00a7lTextures: \u00a72ElderKing \u00a74Morzan \u00a73Skylfr \u00a77Gruk \u00a7bFerin_I\u00a76 FirstKalik");
        list.add("\u00a72Hummel009 ");
        list.add("\u00a7lTranslators: \u00a7eBek \u00a7bGalarcel \u00a7bAlqualinde \u00a72Hummel009 ");
        list.add("\u00a7lTesters: \u00a76Mr_Vexus \u00a72ElderKing \u00a7lFhrain \u00a7eThorin");
        list.add("\u00a78Right Click to Open");
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }
}

