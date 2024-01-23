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
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemHammer;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRStoryItem;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRItemSauronMace
extends LOTRItemHammer
implements LOTRStoryItem {
    public LOTRItemSauronMace() {
        super(LOTRMaterial.MORDOR);
        this.setMaxDamage(1500);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.lotrWeaponDamage = 8.0f;
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        itemstack.damageItem(2, (EntityLivingBase)entityplayer);
        return LOTRItemSauronMace.useSauronMace(itemstack, world, (EntityLivingBase)entityplayer);
    }

    public static ItemStack useSauronMace(ItemStack itemstack, World world, EntityLivingBase user) {
        user.swingItem();
        world.playSoundAtEntity((Entity)user, "lotr:item.maceSauron", 2.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        if (!world.isRemote) {
            List entities = world.getEntitiesWithinAABB(EntityLivingBase.class, user.boundingBox.expand(12.0, 8.0, 12.0));
            if (!entities.isEmpty()) {
                for (int i = 0; i < entities.size(); ++i) {
                    EntityLiving entityliving;
                    EntityLivingBase entity = (EntityLivingBase)entities.get(i);
                    if (entity == user || entity instanceof EntityLiving && LOTRFaction.MORDOR.isGoodRelation(LOTRMod.getNPCFaction((Entity)(entityliving = (EntityLiving)entity))) || entity instanceof EntityPlayer && (!(user instanceof EntityPlayer) ? user instanceof EntityLiving && ((EntityLiving)user).getAttackTarget() != entity && LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR) > 0.0f : !MinecraftServer.getServer().isPVPEnabled() || LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR) > 0.0f)) continue;
                    float strength = 6.0f - user.getDistanceToEntity((Entity)entity) * 0.75f;
                    if (strength < 1.0f) {
                        strength = 1.0f;
                    }
                    if (user instanceof EntityPlayer) {
                        entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)((EntityPlayer)user)), 6.0f * strength);
                    } else {
                        entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)user), 6.0f * strength);
                    }
                    float knockback = strength;
                    if (knockback > 4.0f) {
                        knockback = 4.0f;
                    }
                    entity.addVelocity((double)(-MathHelper.sin((float)(user.rotationYaw * 3.1415927f / 180.0f)) * 0.7f * knockback), 0.2 + 0.12 * (double)knockback, (double)(MathHelper.cos((float)(user.rotationYaw * 3.1415927f / 180.0f)) * 0.7f * knockback));
                }
            }
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.MACE_SAURON, (Entity)user);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)user, 64.0));
        }
        return itemstack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }
}

