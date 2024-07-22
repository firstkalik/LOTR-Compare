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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemBaseRing2;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTRItemCommandHorn3
extends LOTRItemBaseRing2 {
    private final ItemStack fauxPick;
    public static int cooldown = 10;
    public static int defaultCharges = 1200;

    public LOTRItemCommandHorn3() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        this.fauxPick = new ItemStack(Items.diamond_pickaxe);
        this.setMaxDamage(defaultCharges + 1);
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 80;
    }

    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.dwarfSteel;
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        float var4 = 1.0f;
        int i = (int)(playerEntity.prevPosX + (playerEntity.posX - playerEntity.prevPosX) * (double)var4);
        int j = (int)(playerEntity.prevPosY + (playerEntity.posY - playerEntity.prevPosY) * (double)var4 + 1.62 - (double)playerEntity.yOffset);
        int k = (int)(playerEntity.prevPosZ + (playerEntity.posZ - playerEntity.prevPosZ) * (double)var4);
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        if (!world.isRemote) {
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "lotr:item.mordor", 100.0f, 1.0f);
            playerEntity.attackEntityFrom(DamageSource.generic, 2.0f);
            playerEntity.addPotionEffect(new PotionEffect(2, 60, 0));
            srcItemStack.damageItem(1, (EntityLivingBase)playerEntity);
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.INFERNAL, (Entity)playerEntity);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)playerEntity, 64.0));
        }
        return srcItemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
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

