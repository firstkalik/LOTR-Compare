/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRWeaponStats;
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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTRItemTwoHandedSword
extends LOTRItemSword {
    public LOTRItemTwoHandedSword(LOTRMaterial material) {
        super(material.toToolMaterial());
    }

    public LOTRItemTwoHandedSword(Item.ToolMaterial material) {
        super(material);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemTwoHandedSword.class, 0.65f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemTwoHandedSword.class, 1.25f);
        if (material == Item.ToolMaterial.IRON) {
            this.lotrWeaponDamage = 6.0f;
        }
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.block;
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase entity, EntityLivingBase attacker) {
        itemstack.damageItem(1, attacker);
        this.useTwoHandedSword(itemstack, entity.worldObj, entity, attacker);
        Random random = new Random();
        float pitch = 0.9f + random.nextFloat() * 0.2f;
        float volume = 1.5f;
        entity.worldObj.playSoundAtEntity((Entity)entity, "lotr:aurochs.swing", volume, pitch);
        return true;
    }

    public void useTwoHandedSword(ItemStack itemstack, World world, EntityLivingBase target, EntityLivingBase user) {
        user.swingItem();
        if (world.isRemote) {
            return;
        }
        double radius = 5.0;
        Vec3 position = Vec3.createVectorHelper((double)user.posX, (double)user.posY, (double)user.posZ);
        Vec3 look = user.getLookVec();
        Vec3 sight = position.addVector(look.xCoord * radius, look.yCoord * radius, look.zCoord * radius);
        float sightWidth = 2.0f;
        List entities = world.getEntitiesWithinAABBExcludingEntity((Entity)user, user.boundingBox.addCoord(look.xCoord * radius, look.yCoord * radius, look.zCoord * radius).expand((double)sightWidth, (double)sightWidth, (double)sightWidth));
        ArrayList<EntityLivingBase> targets = new ArrayList<EntityLivingBase>();
        for (Object element : entities) {
            EntityLivingBase entity;
            if (!(element instanceof EntityLivingBase) || (entity = (EntityLivingBase)element) == user || entity == target || entity instanceof EntityPlayer && (!(user instanceof EntityPlayer) ? user instanceof EntityLiving && ((EntityLiving)user).getAttackTarget() != entity : !MinecraftServer.getServer().isPVPEnabled())) continue;
            float width = 1.0f;
            AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)width, (double)width, (double)width);
            MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(position, sight);
            if (!axisalignedbb.isVecInside(position) && movingobjectposition == null) continue;
            targets.add(entity);
        }
        for (EntityLivingBase entity : targets) {
            if (entity == user || entity == target) continue;
            float strength = this.lotrWeaponDamage - user.getDistanceToEntity((Entity)entity) * 0.75f;
            strength = Math.max(strength, 0.01f);
            if (user instanceof EntityPlayer) {
                entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)((EntityPlayer)user)), strength / 2.5f);
                continue;
            }
            entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)user), strength / 2.5f);
        }
    }
}

