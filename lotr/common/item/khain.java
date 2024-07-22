/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemBaseRing2;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class khain
extends LOTRItemBaseRing2 {
    public static int cooldown = 10;
    public static int defaultCharges = 250;

    public khain() {
        this.setMaxDamage(defaultCharges + 1);
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        int i = (int)entity.posX;
        int j = (int)entity.posY;
        int k = (int)entity.posZ;
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 120, 0));
        }
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }

    public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ, int blockFace, float par8, float par9, float par10) {
        if (!playerEntity.capabilities.isCreativeMode && this.isOutOfCharge(srcItemStack)) {
            this.playSound(noChargeAttackSound, world, playerEntity);
            return true;
        }
        boolean success = false;
        switch (blockFace) {
            case 0: {
                success = this.placeMageLight(world, targetX, targetY - 1, targetZ);
                break;
            }
            case 1: {
                success = this.placeMageLight(world, targetX, targetY + 1, targetZ);
                break;
            }
            case 2: {
                success = this.placeMageLight(world, targetX, targetY, targetZ - 1);
                break;
            }
            case 3: {
                success = this.placeMageLight(world, targetX, targetY, targetZ + 1);
                break;
            }
            case 4: {
                success = this.placeMageLight(world, targetX - 1, targetY, targetZ);
                break;
            }
            case 5: {
                success = this.placeMageLight(world, targetX + 1, targetY, targetZ);
            }
        }
        if (success && !playerEntity.capabilities.isCreativeMode) {
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        return success;
    }

    private boolean placeMageLight(World w, int x, int y, int z) {
        if (w.isAirBlock(x, y, z)) {
            w.setBlock(x, y, z, LOTRMod.dwarvenTorch);
            return true;
        }
        return false;
    }
}

