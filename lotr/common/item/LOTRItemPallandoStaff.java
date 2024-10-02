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
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.item.LOTRItemBaseRing3;
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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class LOTRItemPallandoStaff
extends LOTRItemBaseRing3
implements LOTRStoryItem {
    public static int defaultCharges = 1500;
    public static int cooldown = 40;

    public LOTRItemPallandoStaff() {
        this.setMaxDamage(defaultCharges + 1);
        this.setMaxDamage(1500);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.lotrWeaponDamage = 7.0f;
    }

    public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return super.onItemUse(srcItemStack, playerEntity, world, par4, par5, par6, par7, par8, par9, par10);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        world.playSoundAtEntity((Entity)playerEntity, "lotr:item.pooff", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity)new EntityEnderPearl(world, (EntityLivingBase)playerEntity));
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.FIREBALL_GANDALF_WHITE, (Entity)playerEntity);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)playerEntity, 256.0));
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            playerEntity.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u0421\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d\u0430"));
            srcItemStack.damageItem(1, (EntityLivingBase)playerEntity);
        }
        return srcItemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }
}

