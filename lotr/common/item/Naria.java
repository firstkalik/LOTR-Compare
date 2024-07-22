/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.entity.projectile.EntitySmallFireball
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemBaseRing2;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Naria
extends LOTRItemBaseRing2 {
    private boolean isEquipped = false;

    public Naria() {
        this.setMaxDamage(1200);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side, float par8, float par9, float par10) {
        if (side == 0) {
            --y;
        }
        if (side == 1) {
            ++y;
        }
        if (side == 2) {
            --z;
        }
        if (side == 3) {
            ++z;
        }
        if (side == 4) {
            --x;
        }
        if (side == 5) {
            ++x;
        }
        if (!par2EntityPlayer.canPlayerEdit(x, y, z, side, par1ItemStack)) {
            return false;
        }
        if (par3World.isAirBlock(x, y, z)) {
            par3World.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "fire.ignite", 1.0f, itemRand.nextFloat() * 0.4f + 0.8f);
            par3World.setBlock(x, y, z, (Block)Blocks.fire);
        }
        par1ItemStack.damageItem(1, (EntityLivingBase)par2EntityPlayer);
        return true;
    }

    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 15;
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        world.playSoundAtEntity((Entity)playerEntity, "mob.ghast.fireball", 2.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        if (!world.isRemote) {
            playerEntity.attackEntityFrom(DamageSource.generic, 0.5f);
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            Vec3 v3 = playerEntity.getLook(1.0f);
            EntitySmallFireball smallfireball = new EntitySmallFireball(world, playerEntity.posX, playerEntity.posY + (double)playerEntity.eyeHeight, playerEntity.posZ, v3.xCoord, v3.yCoord, v3.zCoord);
            smallfireball.shootingEntity = playerEntity;
            world.playSoundAtEntity((Entity)playerEntity, "mob.ghast.fireball", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
            world.spawnEntityInWorld((Entity)smallfireball);
        }
        return srcItemStack;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        int i = (int)entity.posX;
        int j = (int)entity.posY;
        int k = (int)entity.posZ;
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(1, 120, 0));
            ItemStack heldItem = player.getHeldItem();
            if (heldItem != null && heldItem.getItem() == this) {
                if (!this.isEquipped) {
                    if (player.onGround) {
                        player.stepHeight = 1.0f;
                    }
                    this.isEquipped = true;
                }
            } else if (this.isEquipped) {
                player.stepHeight = 0.5f;
                this.isEquipped = false;
            }
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(5, 120, 0));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(12, 120, 0));
            if (entity instanceof EntityPlayer) {
                EntityPlayer player1 = (EntityPlayer)entity;
                ItemStack equipped = player1.getCurrentEquippedItem();
                if (equipped == itemstack) {
                    float high_elf2 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GUNDABAD);
                    float high_elf3 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.UTUMNO);
                    float high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR);
                    float high_elf7 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DOL_GULDUR);
                    if (high_elf2 <= -1000.0f) {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                    }
                    if (high_elf3 <= -1000.0f) {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                    }
                    if (high_elf4 <= -1000.0f) {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                    }
                    if (high_elf7 <= -1000.0f) {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                    }
                }
                if (entity.ticksExisted % 1199 != 0) {
                    return;
                }
            }
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.INFERNAL, (Entity)player);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)player, 128.0));
            float high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GUNDABAD);
            float high_elf5 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.UTUMNO);
            float high_elf6 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR);
            float high_elf7 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DOL_GULDUR);
            if (high_elf4 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf5 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf6 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf7 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
        }
    }

    public boolean hitEntity(ItemStack item, EntityLivingBase hitEntity, EntityLivingBase attackingEntity) {
        hitEntity.setFire(8);
        return true;
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.translateToLocal((String)"narya.name"));
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }
}

