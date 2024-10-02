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
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemBaseRing3;
import lotr.common.item.LOTRStoryItem;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTRItemGandalfStaffGrey
extends LOTRItemBaseRing3
implements LOTRStoryItem {
    private boolean isActive;

    public LOTRItemGandalfStaffGrey() {
        this.setMaxDamage(1500);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.lotrWeaponDamage = 7.0f;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 30;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        this.isActive = true;
        return itemstack;
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        itemstack.damageItem(2, (EntityLivingBase)entityplayer);
        if (!world.isRemote) {
            double explosionRadius = 12.0;
            AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox((double)(entityplayer.posX - explosionRadius), (double)(entityplayer.posY - explosionRadius), (double)(entityplayer.posZ - explosionRadius), (double)(entityplayer.posX + explosionRadius), (double)(entityplayer.posY + explosionRadius), (double)(entityplayer.posZ + explosionRadius));
            List entities = world.getEntitiesWithinAABBExcludingEntity((Entity)entityplayer, boundingBox);
            for (Entity entity : entities) {
                double distanceSq;
                if (entity instanceof EntityPlayer && entity == entityplayer || !((distanceSq = entityplayer.getDistanceSq(entity.posX, entity.posY, entity.posZ)) > 0.0)) continue;
                double distance = Math.sqrt(distanceSq);
                double force = 3.0 / distance;
                double x = entity.posX - entityplayer.posX;
                double y = entity.posY - entityplayer.posY;
                double z = entity.posZ - entityplayer.posZ;
                entity.motionX += (x *= force);
                entity.motionY += (y *= force);
                entity.motionZ += (z *= force);
            }
            world.playSoundAtEntity((Entity)entityplayer, "lotr:item.puff", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.FIREBALL_GANDALF_WHITE, (Entity)entityplayer);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)entityplayer, 256.0));
        }
        return LOTRItemGandalfStaffGrey.useStaff(itemstack, world, (EntityLivingBase)entityplayer);
    }

    public static ItemStack useStaff(ItemStack itemstack, World world, EntityLivingBase user) {
        user.swingItem();
        return itemstack;
    }

    public void onPlayerStoppedUsing(ItemStack srcItemStack, World world, EntityPlayer playerEntity, int timeRemain) {
        this.isActive = false;
        if (this.isOutOfCharge(srcItemStack)) {
            return;
        }
    }

    @Override
    public boolean isOutOfCharge(ItemStack srcItemStack) {
        return srcItemStack.getItemDamage() >= srcItemStack.getMaxDamage();
    }

    public boolean isActive() {
        return this.isActive;
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

