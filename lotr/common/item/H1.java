/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
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
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemBaseRing2;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class H1
extends LOTRItemBaseRing2 {
    public H1() {
        this.setMaxDamage(600);
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.mithrilNugget;
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        world.playSoundAtEntity((Entity)playerEntity, "lotr:item.nazghul", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        if (!world.isRemote) {
            playerEntity.addPotionEffect(new PotionEffect(5, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(11, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(14, 2400, 0));
            playerEntity.attackEntityFrom(DamageSource.generic, 6.0f);
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            playerEntity.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u0421\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d\u0430"));
            srcItemStack.damageItem(1, (EntityLivingBase)playerEntity);
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.MACE_SAURON, (Entity)playerEntity);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)playerEntity, 64.0));
            world.playSoundAtEntity((Entity)playerEntity, "lotr:item.pooff", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
        }
        return srcItemStack;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        if (entity instanceof EntityPlayer) {
            float high_elf4;
            ItemStack equipped;
            EntityPlayer player1;
            EntityPlayer player = (EntityPlayer)entity;
            boolean hasRing = player.inventory.hasItemStack(itemstack);
            if (!hasRing) {
                player.stepHeight = player.onGround && player.moveForward > 0.0f ? 0.5f : 0.5f;
            } else {
                player.stepHeight = player.onGround && player.moveForward > 0.0f ? 1.0f : 0.5f;
                if (entity.ticksExisted % 1199 != 0) {
                    return;
                }
            }
            super.onUpdate(itemstack, world, entity, par4, par5);
            if (entity instanceof EntityPlayer && (equipped = (player1 = (EntityPlayer)entity).getCurrentEquippedItem()) == itemstack) {
                float high_elf2 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.HIGH_ELF);
                float high_elf3 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.LOTHLORIEN);
                high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.WOOD_ELF);
                if (high_elf2 <= -2000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                }
                if (high_elf3 <= -2000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                }
                if (high_elf4 <= -2000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                }
            }
            if (entity.ticksExisted % 1199 != 0) {
                return;
            }
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.MACE_SAURON, (Entity)player);
            itemstack.damageItem(1, (EntityLivingBase)player);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)player, 128.0));
            float high_elf2 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GONDOR);
            float high_elf3 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.ROHAN);
            float high_elf7 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DALE);
            high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DURINS_FOLK);
            float high_elf5 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.BLUE_MOUNTAINS);
            float high_elf6 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.RED_MOUNTAINS);
            float high_elf8 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR);
            float high_elf9 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.UTUMNO);
            float high_elf10 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DOL_GULDUR);
            float high_elf11 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GUNDABAD);
            if (high_elf2 <= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf3 <= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf4 <= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf8 <= -4000.0f) {
                player.attackEntityFrom(DamageSource.generic, 5.0f);
            }
            if (high_elf9 <= -2000.0f) {
                player.attackEntityFrom(DamageSource.generic, 5.0f);
            }
            if (high_elf10 <= -2000.0f) {
                player.attackEntityFrom(DamageSource.generic, 5.0f);
            }
            if (high_elf11 <= -2000.0f) {
                player.attackEntityFrom(DamageSource.generic, 5.0f);
            }
            if (high_elf8 <= -1000.0f) {
                player.attackEntityFrom(DamageSource.generic, 2.0f);
            }
            if (high_elf9 <= -1000.0f) {
                player.attackEntityFrom(DamageSource.generic, 2.0f);
            }
            if (high_elf10 <= -1000.0f) {
                player.attackEntityFrom(DamageSource.generic, 2.0f);
            }
            if (high_elf11 <= -1000.0f) {
                player.attackEntityFrom(DamageSource.generic, 2.0f);
            }
        }
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add((Object)EnumChatFormatting.WHITE + StatCollector.translateToLocal((String)"human.name"));
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
    }

    @Override
    public int getBaseRepairCost() {
        return 12;
    }
}

