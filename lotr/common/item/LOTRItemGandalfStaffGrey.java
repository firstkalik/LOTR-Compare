/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRStoryItem;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemGandalfStaffGrey
extends LOTRItemSword
implements LOTRStoryItem {
    public LOTRItemGandalfStaffGrey() {
        super(Item.ToolMaterial.WOOD);
        this.setMaxDamage(1500);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.lotrWeaponDamage = 7.0f;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 1200;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    public void onPlayerStoppedUsing(ItemStack srcItemStack, World world, EntityPlayer playerEntity, int timeRemain) {
        if (!playerEntity.capabilities.isCreativeMode) {
            srcItemStack.damageItem(10, (EntityLivingBase)playerEntity);
        }
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity)new EntityEnderPearl(world, (EntityLivingBase)playerEntity));
        }
        LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.FIREBALL_GANDALF_WHITE, (Entity)playerEntity);
        LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)playerEntity, 256.0));
    }
}

