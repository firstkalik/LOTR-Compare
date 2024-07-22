/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRItembattleaxe_melkor
extends LOTRItemSword {
    public LOTRItembattleaxe_melkor() {
        super(LOTRMaterial.UTUMNO);
        this.lotrWeaponDamage = 11.5f;
        this.setMaxDamage(1500);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if (p_77663_3_ instanceof EntityPlayer) {
            float high_elf = LOTRLevelData.getData((EntityPlayer)p_77663_3_).getAlignment(LOTRFaction.UTUMNO);
            if (p_77663_3_.ticksExisted % 180 != 0) {
                return;
            }
            if (high_elf <= 3000.0f) {
                p_77663_3_.setFire(5);
            }
        }
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitEntity, EntityLivingBase user) {
        if (super.hitEntity(itemstack, hitEntity, user)) {
            this.checkIncompatibleModifiers(itemstack);
            if (!user.worldObj.isRemote && hitEntity.hurtTime == hitEntity.maxHurtTime) {
                this.launchWhip(user, hitEntity);
            }
            return true;
        }
        return false;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 20;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        this.checkIncompatibleModifiers(itemstack);
        entityplayer.swingItem();
        if (!world.isRemote) {
            this.launchWhip((EntityLivingBase)entityplayer, null);
        }
        itemstack.damageItem(1, (EntityLivingBase)entityplayer);
        return itemstack;
    }

    private void launchWhip(EntityLivingBase user, EntityLivingBase hitEntity) {
        user.worldObj.playSoundAtEntity((Entity)user, "lotr:item.balrogWhip", 2.0f, 0.7f + itemRand.nextFloat() * 0.6f);
        double range = 16.0;
        Vec3 position = Vec3.createVectorHelper((double)user.posX, (double)user.posY, (double)user.posZ);
        Vec3 look = user.getLookVec();
        Vec3 sight = position.addVector(look.xCoord * range, look.yCoord * range, look.zCoord * range);
        float sightWidth = 1.0f;
        List list = user.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)user, user.boundingBox.addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expand((double)sightWidth, (double)sightWidth, (double)sightWidth));
        ArrayList<EntityLivingBase> whipTargets = new ArrayList<EntityLivingBase>();
        for (Object element : list) {
            EntityLivingBase entity;
            Entity obj = (Entity)element;
            if (!(obj instanceof EntityLivingBase) || (entity = (EntityLivingBase)obj) == user.ridingEntity && !entity.canRiderInteract() || !entity.canBeCollidedWith()) continue;
            float width = 1.0f;
            AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)width, (double)width, (double)width);
            MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(position, sight);
            if (axisalignedbb.isVecInside(position)) {
                whipTargets.add(entity);
                continue;
            }
            if (movingobjectposition == null) continue;
            whipTargets.add(entity);
        }
        for (EntityLivingBase entity : whipTargets) {
            if (entity != hitEntity && !entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)user), 1.0f)) continue;
            entity.setFire(5);
        }
        Vec3 eyeHeight = position.addVector(0.0, (double)user.getEyeHeight(), 0.0);
        block2: for (int l = 4; l < (int)range; ++l) {
            double d = (double)l / range;
            double dx = sight.xCoord - eyeHeight.xCoord;
            double dy = sight.yCoord - eyeHeight.yCoord;
            double dz = sight.zCoord - eyeHeight.zCoord;
            double x = eyeHeight.xCoord + dx * d;
            double y = eyeHeight.yCoord + dy * d;
            double z = eyeHeight.zCoord + dz * d;
            int i = MathHelper.floor_double((double)x);
            int j = MathHelper.floor_double((double)y);
            int k = MathHelper.floor_double((double)z);
            for (int j1 = j - 3; j1 <= j + 3; ++j1) {
                if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)user.worldObj, (int)i, (int)(j1 - 1), (int)k) || !user.worldObj.getBlock(i, j1, k).isReplaceable((IBlockAccess)user.worldObj, i, j1, k)) continue;
                boolean protection = false;
                if (user instanceof EntityPlayer) {
                    protection = LOTRBannerProtection.isProtected(user.worldObj, i, j1, k, LOTRBannerProtection.forPlayer((EntityPlayer)user), false);
                } else if (user instanceof EntityLiving) {
                    protection = LOTRBannerProtection.isProtected(user.worldObj, i, j1, k, LOTRBannerProtection.forNPC((EntityLiving)user), false);
                }
                if (protection) continue;
                user.worldObj.setBlock(i, j1, k, (Block)Blocks.fire, 0, 3);
                continue block2;
            }
        }
    }

    public int getItemEnchantability() {
        return 0;
    }

    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.balrogFire;
    }

    private void checkIncompatibleModifiers(ItemStack itemstack) {
        for (LOTREnchantment ench : new LOTREnchantment[]{LOTREnchantment.fire, LOTREnchantment.chill}) {
            if (!LOTREnchantmentHelper.hasEnchant(itemstack, ench)) continue;
            LOTREnchantmentHelper.removeEnchant(itemstack, ench);
        }
    }
}

