/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.projectile.LOTREntityMysteryWeb;
import lotr.common.item.Wand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class melkor
extends Wand {
    public static int defaultCharges = 29;

    public melkor() {
        this.setMaxDamage(defaultCharges + 1);
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public int getBaseRepairCost() {
        return 20;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 10;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            boolean hasRing = player.inventory.hasItemStack(itemstack);
            player.stepHeight = !hasRing ? (player.onGround && player.moveForward > 0.0f ? 0.5f : 0.5f) : (player.onGround && player.moveForward > 0.0f ? 1.0f : 0.5f);
        }
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(31, 300, 0));
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.orcSteel;
    }

    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return super.onItemUse(srcItemStack, playerEntity, world, par4, par5, par6, par7, par8, par9, par10);
    }

    public void onPlayerStoppedUsing(ItemStack srcItemStack, World world, EntityPlayer playerEntity, int timeRemain) {
        super.onPlayerStoppedUsing(srcItemStack, world, playerEntity, timeRemain);
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        this.playSound("fireworks.launch", world, playerEntity);
        if (!world.isRemote) {
            Vec3 v3 = playerEntity.getLook(1.0f);
            world.spawnEntityInWorld((Entity)new LOTREntityMysteryWeb(world, (EntityLivingBase)playerEntity));
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            world.playSoundAtEntity((Entity)playerEntity, "fireworks.launch", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
        }
        return srcItemStack;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        super.addInformation(itemstack, entityplayer, list, flag);
        list.add(StatCollector.translateToLocal((String)"melkor.name"));
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
        list.add((Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"lotr.ring.ready", (Object[])new Object[0]));
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }
}

