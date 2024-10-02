/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.valaringbase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class yavanna
extends valaringbase {
    public static int cooldown = 10;
    public static int defaultCharges = 200;

    public yavanna() {
        this.setMaxDamage(defaultCharges + 1);
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }

    public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ, int par7, float par8, float par9, float par10) {
        if (!playerEntity.capabilities.isCreativeMode && this.isOutOfCharge(srcItemStack)) {
            this.playSound(noChargeAttackSound, world, playerEntity);
            return true;
        }
        boolean success = this.growBlock(playerEntity, world, targetX, targetY, targetZ);
        if (success) {
            this.playSound("random.orb", world, playerEntity);
            if (!playerEntity.capabilities.isCreativeMode) {
                srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
            }
        }
        return success;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean advanced) {
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
        list.add((Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"lotr.ring.ready", (Object[])new Object[0]));
    }

    protected boolean growBlock(EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ) {
        Block targetBlock = world.getBlock(targetX, targetY, targetZ);
        ItemStack fauxItemStack = new ItemStack(Items.dye, 1, 15);
        if (targetBlock == Blocks.cactus) {
            int y = targetY + 1;
            while (world.getBlock(targetX, y, targetZ) == Blocks.cactus) {
                ++y;
            }
            if (world.isAirBlock(targetX, y, targetZ)) {
                world.setBlock(targetX, y, targetZ, Blocks.cactus);
            }
            return true;
        }
        if (targetBlock == Blocks.reeds) {
            int y = targetY + 1;
            while (world.getBlock(targetX, y, targetZ) == Blocks.reeds) {
                ++y;
            }
            if (world.isAirBlock(targetX, y, targetZ)) {
                world.setBlock(targetX, y, targetZ, Blocks.reeds);
            }
            return true;
        }
        if (targetBlock == Blocks.cobblestone) {
            world.setBlock(targetX, targetY, targetZ, Blocks.mossy_cobblestone);
            return true;
        }
        if (targetBlock == Blocks.stonebrick && world.getBlockMetadata(targetX, targetY, targetZ) == 0) {
            world.setBlockMetadataWithNotify(targetX, targetY, targetZ, 1, 3);
            return true;
        }
        return ItemDye.applyBonemeal((ItemStack)fauxItemStack, (World)world, (int)targetX, (int)targetY, (int)targetZ, (EntityPlayer)playerEntity);
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        EntityPlayer player1;
        ItemStack equipped;
        int i = (int)entity.posX;
        int j = (int)entity.posY;
        int k = (int)entity.posZ;
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(42, 120, 0));
        }
        if (entity instanceof EntityPlayer && (equipped = (player1 = (EntityPlayer)entity).getCurrentEquippedItem()) == itemstack) {
            float high_elf2 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GUNDABAD);
            float high_elf3 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.UTUMNO);
            float high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR);
            float high_elf7 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DOL_GULDUR);
            if (high_elf2 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 600, 0));
            }
            if (high_elf3 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 600, 0));
            }
            if (high_elf4 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 600, 0));
            }
            if (high_elf7 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 600, 0));
            }
        }
        if (entity.ticksExisted % 1199 != 0) {
            return;
        }
    }
}

