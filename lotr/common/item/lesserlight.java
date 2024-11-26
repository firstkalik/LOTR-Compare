/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemBaseRing2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class lesserlight
extends LOTRItemBaseRing2 {
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entity) {
        NBTTagCompound tagCompound = itemstack.getTagCompound();
        if (!world.isRemote && tagCompound != null && tagCompound.getBoolean("activated") && tagCompound.getInteger("cooldown") > 0) {
            entity.addChatComponentMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + StatCollector.translateToLocalFormatted((String)"ring.cooldown", (Object[])new Object[]{tagCompound.getInteger("cooldown") / 20})));
        }
        if (!(world.isRemote || itemstack.hasTagCompound() && itemstack.getTagCompound().getBoolean("activated"))) {
            entity.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
            float var4 = 1.0f;
            int i = (int)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)var4);
            int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62 - (double)entity.yOffset);
            int k = (int)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)var4);
            entity.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 60, 0));
            world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)(i + 1), (double)j, (double)(k - 1)));
            world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)(i - 3), (double)j, (double)(k - 1)));
            world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)(i - 1), (double)j, (double)(k + 1)));
            world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)(i - 1), (double)j, (double)(k - 3)));
            itemstack.damageItem(50, (EntityLivingBase)entity);
            entity.attackEntityFrom(DamageSource.generic, 2.0f);
            LOTRLevelData.getData(entity).addAchievement(LOTRAchievement.useRing);
            NBTTagCompound tagCompound1 = itemstack.getTagCompound();
            if (tagCompound1 == null) {
                tagCompound1 = new NBTTagCompound();
            }
            tagCompound1.setBoolean("activated", true);
            tagCompound1.setInteger("cooldown", 2400);
            itemstack.setTagCompound(tagCompound1);
        }
        return itemstack;
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
        NBTTagCompound tagCompound = itemstack.getTagCompound();
        if (!world.isRemote && tagCompound != null && tagCompound.getBoolean("activated") && tagCompound.getInteger("cooldown") > 0) {
            tagCompound.setInteger("cooldown", tagCompound.getInteger("cooldown") - 1);
            if (tagCompound.getInteger("cooldown") <= 0 && entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                String itemName = itemstack.getDisplayName();
                String message = String.format(StatCollector.translateToLocal((String)"ring.ready"), itemName);
                player.addChatComponentMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.GREEN + message));
                tagCompound.setBoolean("activated", false);
            }
        }
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean advanced) {
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
        NBTTagCompound tagCompound = par1ItemStack.getTagCompound();
        if (tagCompound != null && tagCompound.getInteger("cooldown") > 0) {
            list.add((Object)EnumChatFormatting.RED + StatCollector.translateToLocalFormatted((String)"ring.add.cooldown", (Object[])new Object[]{tagCompound.getInteger("cooldown") / 20}));
        }
        if (tagCompound == null || !tagCompound.getBoolean("activated")) {
            list.add((Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"lotr.ring.ready", (Object[])new Object[0]));
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.silver;
    }
}

