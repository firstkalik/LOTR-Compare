/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.List;
import lotr.common.LOTRDimension;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemBaseRing2;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class farin
extends LOTRItemBaseRing2 {
    public static int cooldown = 10;
    public static int defaultCharges = 250;
    private ItemStack fauxPick;

    public farin() {
        this.setMaxDamage(defaultCharges + 1);
        this.fauxPick = new ItemStack(Items.diamond_pickaxe);
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        int i = (int)entity.posX;
        int j = (int)entity.posY;
        int k = (int)entity.posZ;
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 120, 1));
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

    public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ, int par7, float par8, float par9, float par10) {
        if (!playerEntity.capabilities.isCreativeMode && !playerEntity.capabilities.isCreativeMode && this.isOutOfCharge(srcItemStack)) {
            this.playSound(noChargeAttackSound, world, playerEntity);
            return true;
        }
        boolean success = this.mineBlock(playerEntity, world, targetX, targetY, targetZ);
        if (success && !playerEntity.capabilities.isCreativeMode) {
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        return success;
    }

    protected boolean mineBlock(EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ) {
        if (LOTRDimension.getCurrentDimension(world) == LOTRDimension.UTUMNO) {
            return false;
        }
        Block targetBlock = world.getBlock(targetX, targetY, targetZ);
        if (targetBlock == Blocks.bedrock || targetBlock == LOTRMod.oreMithril2) {
            return true;
        }
        int targetMeta = world.getBlockMetadata(targetX, targetY, targetZ);
        if (Items.diamond_pickaxe.canHarvestBlock(targetBlock, this.fauxPick) || targetBlock.getBlockHardness(world, targetX, targetY, targetZ) < 1.0f) {
            ArrayList drop = targetBlock.getDrops(world, targetX, targetY, targetZ, targetMeta, 0);
            if (!world.isRemote) {
                for (int i = 0; i < drop.size(); ++i) {
                    world.spawnEntityInWorld((Entity)new EntityItem(world, (double)targetX, (double)targetY, (double)targetZ, (ItemStack)drop.get(i)));
                }
            }
            world.setBlockToAir(targetX, targetY, targetZ);
            return true;
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean advanced) {
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
        list.add((Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"lotr.ring.ready", (Object[])new Object[0]));
    }
}

