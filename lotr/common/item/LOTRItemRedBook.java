/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestEvent;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemRedBook
extends Item {
    @SideOnly(value=Side.CLIENT)
    public static IIcon questOfferIcon;

    public LOTRItemRedBook() {
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.openGui((Object)LOTRMod.instance, 32, world, 0, 0, 0);
        if (!world.isRemote) {
            LOTRLevelData.getData(entityplayer).distributeMQEvent(new LOTRMiniQuestEvent.OpenRedBook());
        }
        return itemstack;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        LOTRPlayerData playerData = LOTRLevelData.getData(entityplayer);
        int activeQuests = playerData.getActiveMiniQuests().size();
        list.add(StatCollector.translateToLocalFormatted((String)"item.lotr.redBook.activeQuests", (Object[])new Object[]{activeQuests}));
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        super.registerIcons(iconregister);
        questOfferIcon = iconregister.registerIcon("lotr:questOffer");
    }
}

