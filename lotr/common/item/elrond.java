/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.item.LOTRItemBaseRing2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class elrond
extends LOTRItemBaseRing2 {
    public static int cooldown = 10;
    public static int defaultCharges = 100;

    public elrond() {
        this.setMaxDamage(defaultCharges + 1);
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        world.playSoundAtEntity((Entity)playerEntity, "lotr:elf.male.attack", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        if (!world.isRemote) {
            playerEntity.addPotionEffect(new PotionEffect(37, 1200, 0));
            playerEntity.attackEntityFrom(DamageSource.generic, 4.0f);
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            playerEntity.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u0421\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d\u0430"));
            srcItemStack.damageItem(2, (EntityLivingBase)playerEntity);
        }
        return srcItemStack;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        int i = (int)entity.posX;
        int j = (int)entity.posY;
        int k = (int)entity.posZ;
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(1, 120, 0));
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
}

