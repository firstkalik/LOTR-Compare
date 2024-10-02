/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import lotr.common.item.valaringbase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class vaire
extends valaringbase {
    public static int cooldown = 10;
    public static int defaultCharges = 200;

    public vaire() {
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

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        int i = (int)entity.posX;
        int j = (int)entity.posY;
        int k = (int)entity.posZ;
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            boolean hasRing = player.inventory.hasItemStack(itemstack);
            player.stepHeight = !hasRing ? (player.onGround && player.moveForward > 0.0f ? 0.5f : 0.5f) : (player.onGround && player.moveForward > 0.0f ? 1.0f : 0.5f);
        }
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(16, 640, 3));
        }
        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(11, 120, 0));
        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(5, 120, 0));
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean advanced) {
        list.add((Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"lotr.ring.ready", (Object[])new Object[0]));
    }
}

