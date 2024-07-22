/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemBaseRing3;
import lotr.common.item.LOTRStoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTRItemAlatarStaff
extends LOTRItemBaseRing3
implements LOTRStoryItem {
    public LOTRItemAlatarStaff() {
        this.setMaxDamage(1500);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.lotrWeaponDamage = 7.0f;
    }

    public static ItemStack useStaff(ItemStack itemstack, World world, EntityLivingBase user) {
        user.swingItem();
        if (!world.isRemote) {
            List entities = world.getEntitiesWithinAABB(EntityLivingBase.class, user.boundingBox.expand(64.0, 64.0, 64.0));
            for (EntityLivingBase entity : entities) {
                if (!LOTRMod.canPlayerAttackEntity((EntityPlayer)user, entity, false)) continue;
                world.addWeatherEffect((Entity)new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ));
            }
        }
        return itemstack;
    }

    public static void wizardUseStaff(ItemStack itemstack, World world, EntityLivingBase user) {
        user.swingItem();
        if (!world.isRemote) {
            List entities = world.getEntitiesWithinAABB(EntityLivingBase.class, user.boundingBox.expand(64.0, 64.0, 64.0));
            for (EntityLivingBase entity : entities) {
                if (!LOTRMod.canNPCAttackEntity((EntityCreature)user, entity, false)) continue;
                world.addWeatherEffect((Entity)new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ));
            }
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        itemstack.damageItem(2, (EntityLivingBase)entityplayer);
        return LOTRItemAlatarStaff.useStaff(itemstack, world, (EntityLivingBase)entityplayer);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
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

