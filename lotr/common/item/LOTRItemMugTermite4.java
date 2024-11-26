/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.item.LOTRItemMug;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemMugTermite4
extends LOTRItemMug {
    public LOTRItemMugTermite4(float f) {
        super(f);
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        ItemStack result = super.onEaten(itemstack, world, entityplayer);
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.drinkFire);
        if (!world.isRemote && world.rand.nextInt(5) == 0) {
            entityplayer.setFire(250);
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.INFERNAL, (Entity)entityplayer);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)entityplayer, 64.0));
            LOTRPacketWeaponFX packet1 = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.MACE_SAURON, (Entity)entityplayer);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet1, LOTRPacketHandler.nearEntity((Entity)entityplayer, 64.0));
        }
        return result;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        super.addInformation(itemstack, entityplayer, list, flag);
        list.add((Object)EnumChatFormatting.RED + StatCollector.translateToLocal((String)"item.lotr.drink.fire"));
    }
}

