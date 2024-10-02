/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPCRideable;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRStoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.World;

public class LOTRItemPallandoStaff
extends LOTRItemSword
implements LOTRStoryItem {
    public LOTRItemPallandoStaff() {
        super(Item.ToolMaterial.IRON);
        this.setMaxDamage(1500);
        this.lotrWeaponDamage = 9.0f;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    public static void useStaff1(ItemStack itemstack, World world, EntityLivingBase user) {
        user.swingItem();
        if (!world.isRemote) {
            List entities = world.getEntitiesWithinAABB(EntityLivingBase.class, user.boundingBox.expand(64.0, 64.0, 64.0));
            for (EntityLivingBase entity : entities) {
                if (entity == user || entity instanceof EntityHorse && ((EntityHorse)entity).isTame() || entity instanceof EntityTameable && ((EntityTameable)entity).isTamed()) continue;
                entity.attackEntityFrom(DamageSource.magic, 5.0f);
                if (!LOTRMod.canNPCAttackEntity((EntityCreature)user, entity, false)) continue;
                EntityArrow arrow = new EntityArrow(world, user, 1.6f);
                arrow.setDamage(5.0);
                world.spawnEntityInWorld((Entity)arrow);
            }
        }
    }

    public static ItemStack useStaff(ItemStack itemstack, World world, EntityLivingBase user) {
        user.swingItem();
        if (!world.isRemote) {
            List entities = world.getEntitiesWithinAABB(EntityLivingBase.class, user.boundingBox.expand(32.0, 32.0, 32.0));
            for (EntityLivingBase entity : entities) {
                if (entity == user || entity instanceof EntityHorse && ((EntityHorse)entity).isTame() || entity instanceof EntityTameable && ((EntityTameable)entity).isTamed() || entity instanceof LOTREntityNPCRideable && ((LOTREntityNPCRideable)entity).isNPCTamed()) continue;
                entity.attackEntityFrom(new EntityDamageSourceIndirect("lotr:item.pooff", (Entity)entity, (Entity)user).setMagicDamage().setDamageBypassesArmor().setDamageAllowedInCreativeMode(), 5.0f);
                if (!LOTRMod.canPlayerAttackEntity((EntityPlayer)user, entity, false)) continue;
                EntityArrow arrow = new EntityArrow(world, user, entity, 1.6f, 0.0f);
                arrow.setDamage(3.0);
                arrow.canBePickedUp = 0;
                world.spawnEntityInWorld((Entity)arrow);
                itemstack.damageItem(1, user);
            }
        }
        return itemstack;
    }

    @Override
    public boolean hitEntity(ItemStack item, EntityLivingBase hitEntity, EntityLivingBase attackingEntity) {
        LOTRItemPallandoStaff.useStaff(item, hitEntity.worldObj, attackingEntity);
        return true;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        itemstack.damageItem(2, (EntityLivingBase)entityplayer);
        return LOTRItemPallandoStaff.useStaff(itemstack, world, (EntityLivingBase)entityplayer);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }
}

