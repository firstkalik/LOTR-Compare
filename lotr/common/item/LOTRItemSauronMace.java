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
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.ArrayList;
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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
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
                for (Object entitie : entities) {
                    EntityLivingBase entity = (EntityLivingBase)entitie;
                    if (entity == user || entity instanceof EntityLiving && LOTRFaction.MORDOR.isGoodRelation(LOTRMod.getNPCFaction((Entity)((EntityLiving)entity))) || entity instanceof EntityPlayer && (user instanceof EntityPlayer ? !MinecraftServer.getServer().isPVPEnabled() || LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR) > 0.0f : user instanceof EntityLiving && ((EntityLiving)user).getAttackTarget() != entity && LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR) > 0.0f)) continue;
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
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase entity, EntityLivingBase attacker) {
        itemstack.damageItem(1, attacker);
        this.useTwoHandedSword(itemstack, entity.worldObj, entity, attacker);
        return true;
    }

    public void useTwoHandedSword(ItemStack itemstack, World world, EntityLivingBase target, EntityLivingBase user) {
        user.swingItem();
        if (!world.isRemote) {
            double radius = 5.5;
            Vec3 position = Vec3.createVectorHelper((double)user.posX, (double)user.posY, (double)user.posZ);
            Vec3 look = user.getLookVec();
            Vec3 sight = position.addVector(look.xCoord * radius, look.yCoord * radius, look.zCoord * radius);
            float sightWidth = 1.0f;
            List entities = world.getEntitiesWithinAABBExcludingEntity((Entity)user, user.boundingBox.addCoord(look.xCoord * radius, look.yCoord * radius, look.zCoord * radius).expand((double)sightWidth, (double)sightWidth, (double)sightWidth));
            ArrayList<EntityLivingBase> targets = new ArrayList<EntityLivingBase>();
            if (!entities.isEmpty()) {
                for (Object element : entities) {
                    EntityLivingBase entity;
                    Entity obj = (Entity)element;
                    if (!(obj instanceof EntityLivingBase) || (entity = (EntityLivingBase)obj) == user.ridingEntity && !entity.canRiderInteract() || !entity.canBeCollidedWith()) continue;
                    float width = 1.0f;
                    AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)width, (double)width, (double)width);
                    MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(position, sight);
                    if (axisalignedbb.isVecInside(position)) {
                        targets.add(entity);
                        continue;
                    }
                    if (movingobjectposition == null) continue;
                    targets.add(entity);
                }
            }
            if (!targets.isEmpty()) {
                for (EntityLivingBase entity : targets) {
                    if (entity == user || entity == target || entity instanceof EntityPlayer && (user instanceof EntityPlayer ? !MinecraftServer.getServer().isPVPEnabled() : user instanceof EntityLiving && ((EntityLiving)user).getAttackTarget() != entity)) continue;
                    float strength = this.lotrWeaponDamage - user.getDistanceToEntity((Entity)entity) * 0.75f;
                    if (strength <= 0.0f) {
                        strength = 0.01f;
                    }
                    if (user instanceof EntityPlayer) {
                        entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)((EntityPlayer)user)), strength / 3.5f);
                        continue;
                    }
                    entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)user), strength / 3.5f);
                }
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }
}

