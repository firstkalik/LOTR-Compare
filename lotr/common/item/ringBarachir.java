/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
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
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemBaseRing2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ringBarachir
extends LOTRItemBaseRing2 {
    public static int cooldown = 10;
    public static int defaultCharges = 250;

    public ringBarachir() {
        this.setMaxDamage(defaultCharges + 1);
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        NBTTagCompound tagCompound = srcItemStack.getTagCompound();
        if (!world.isRemote && tagCompound != null && tagCompound.getBoolean("activated") && tagCompound.getInteger("cooldown") > 0) {
            playerEntity.addChatComponentMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + StatCollector.translateToLocalFormatted((String)"ring.cooldown", (Object[])new Object[]{tagCompound.getInteger("cooldown") / 20})));
        }
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
        if (!(world.isRemote || srcItemStack.hasTagCompound() && srcItemStack.getTagCompound().getBoolean("activated"))) {
            playerEntity.addPotionEffect(new PotionEffect(1, 1200, 0));
            playerEntity.addPotionEffect(new PotionEffect(11, 600, 0));
            playerEntity.attackEntityFrom(DamageSource.generic, 3.0f);
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            playerEntity.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u0421\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d\u0430"));
            srcItemStack.damageItem(1, (EntityLivingBase)playerEntity);
            NBTTagCompound tagCompound = srcItemStack.getTagCompound();
            if (tagCompound == null) {
                tagCompound = new NBTTagCompound();
            }
            tagCompound.setBoolean("activated", true);
            tagCompound.setInteger("cooldown", 1200);
            srcItemStack.setTagCompound(tagCompound);
            world.playSoundAtEntity((Entity)playerEntity, "lotr:item.maceSauron", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        }
        return srcItemStack;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        NBTTagCompound tagCompound = itemstack.getTagCompound();
        if (!world.isRemote && tagCompound != null && tagCompound.getBoolean("activated") && tagCompound.getInteger("cooldown") > 0) {
            tagCompound.setInteger("cooldown", tagCompound.getInteger("cooldown") - 1);
            if (tagCompound.getInteger("cooldown") <= 0 && entity instanceof EntityPlayer) {
                EntityPlayer player1 = (EntityPlayer)entity;
                String itemName = itemstack.getDisplayName();
                String message = String.format(StatCollector.translateToLocal((String)"ring.ready"), itemName);
                player1.addChatComponentMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.GREEN + message));
                tagCompound.setBoolean("activated", false);
            }
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player1;
            ItemStack equipped;
            EntityPlayer player = (EntityPlayer)entity;
            boolean hasRing = player.inventory.hasItemStack(itemstack);
            player.stepHeight = !hasRing ? (player.onGround && player.moveForward > 0.0f ? 0.5f : 0.5f) : (player.onGround && player.moveForward > 0.0f ? 1.0f : 0.5f);
            if (entity instanceof EntityLivingBase) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(5, 240, 0));
            }
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(42, 240, 0));
            if (entity instanceof EntityPlayer && (equipped = (player1 = (EntityPlayer)entity).getCurrentEquippedItem()) == itemstack) {
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
                if (high_elf2 <= -2000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 20, 0));
                }
                if (high_elf3 <= -2000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 600, 0));
                }
                if (high_elf4 <= -2000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 600, 0));
                }
                if (high_elf7 <= -2000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 600, 0));
                }
            }
            if (entity.ticksExisted % 1199 != 0) {
                return;
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
    public int getUseCost() {
        return 1;
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }
}

