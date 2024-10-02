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

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.entity.projectile.LOTREntityGandalfFireball;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRRingOne
extends LOTRItemBaseRing2 {
    public LOTRRingOne() {
        this.setMaxDamage(10000);
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        if (srcItemStack.getItem() instanceof LOTRRingOne) {
            boolean hasRing = false;
            for (int i = 0; i < playerEntity.inventory.getSizeInventory(); ++i) {
                ItemStack itemStack = playerEntity.inventory.getStackInSlot(i);
                if (itemStack == null || !(itemStack.getItem() instanceof LOTRRingOne)) continue;
                hasRing = true;
                break;
            }
            if (!hasRing) {
                return srcItemStack;
            }
        }
        return super.onItemRightClick(srcItemStack, world, playerEntity);
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        this.playSound("lotr:item.pooff", world, playerEntity);
        if (!world.isRemote) {
            playerEntity.attackEntityFrom(DamageSource.generic, 1.0f);
            playerEntity.addPotionEffect(new PotionEffect(5, 4800, 1));
            playerEntity.addPotionEffect(new PotionEffect(39, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(14, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(12, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(11, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(16, 2400, 0));
            playerEntity.attackEntityFrom(DamageSource.generic, 8.0f);
            playerEntity.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u0421\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d\u0430"));
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.INFERNAL, (Entity)playerEntity);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)playerEntity, 64.0));
            srcItemStack.damageItem(1, (EntityLivingBase)playerEntity);
            world.playSoundAtEntity((Entity)playerEntity, "lotr:item.pooff", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useOneRing);
        }
        return srcItemStack;
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer entity, World world, int i, int j, int k, int l, float a, float b, float c) {
        if (!world.isRemote) {
            entity.addPotionEffect(new PotionEffect(21, 96000, 2));
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.MACE_SAURON, (Entity)entity);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)entity, 64.0));
            entity.attackEntityFrom(DamageSource.generic, 2.0f);
            itemStack.damageItem(2, (EntityLivingBase)entity);
            LOTRLevelData.getData(entity).addAchievement(LOTRAchievement.useRing);
            entity.addChatMessage((IChatComponent)new ChatComponentText("\u00a7c\u0421\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d\u0430"));
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "lotr:item.pooff", 5.0f, 1.0f);
            return true;
        }
        return false;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player1;
            ItemStack equipped;
            EntityPlayer player = (EntityPlayer)entity;
            boolean hasRing = player.inventory.hasItemStack(itemstack);
            player.stepHeight = !hasRing ? (player.onGround && player.moveForward > 0.0f ? 0.5f : 0.5f) : (player.onGround && player.moveForward > 0.0f ? 1.5f : 0.5f);
            float high_elf8 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR);
            float high_elf9 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.UTUMNO);
            float high_elf10 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DOL_GULDUR);
            float high_elf11 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GUNDABAD);
            if (high_elf9 <= 1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 20, 0));
            }
            if (high_elf8 <= 1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(15, 10, 0));
            }
            if (high_elf8 <= -500.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 1));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 1));
            }
            if (high_elf8 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 2));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 2));
            }
            if (high_elf8 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 3));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 3));
            }
            if (high_elf8 <= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(18, 40, 0));
            }
            if (high_elf9 <= -500.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 1));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 1));
            }
            if (high_elf9 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 2));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 2));
            }
            if (high_elf9 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 3));
            }
            if (high_elf9 <= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(18, 40, 0));
            }
            if (high_elf10 <= -500.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 1));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 1));
            }
            if (high_elf10 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 2));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 2));
            }
            if (high_elf10 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 3));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 3));
            }
            if (high_elf10 <= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(18, 40, 0));
            }
            if (high_elf11 <= -500.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 1));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 1));
            }
            if (high_elf11 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 2));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 2));
            }
            if (high_elf11 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 40, 3));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 40, 3));
            }
            if (high_elf11 <= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(18, 40, 0));
            }
            super.onUpdate(itemstack, world, entity, par4, par5);
            if (entity instanceof EntityPlayer && (equipped = (player1 = (EntityPlayer)entity).getCurrentEquippedItem()) == itemstack) {
                player1.addPotionEffect(new PotionEffect(Potion.jump.id, 2, 1, true));
                player1.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2, 0, true));
                player1.addPotionEffect(new PotionEffect(new PotionEffect(31, 60, 0)));
                float high_elf2 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.HIGH_ELF);
                float high_elf3 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.LOTHLORIEN);
                float high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.WOOD_ELF);
                if (high_elf2 <= -1000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                }
                if (high_elf3 <= -1000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                }
                if (high_elf4 <= -1000.0f) {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(23, 20, 0));
                }
            }
            if (entity.ticksExisted % 1199 != 0) {
                return;
            }
            float high_elf2 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GONDOR);
            float high_elf3 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.ROHAN);
            float high_elf7 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DALE);
            float high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DURINS_FOLK);
            float high_elf5 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.BLUE_MOUNTAINS);
            float high_elf6 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.RED_MOUNTAINS);
            if (high_elf2 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf3 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf7 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf4 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(11, 1200, 0));
            }
            if (high_elf5 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(11, 1200, 0));
            }
            if (high_elf6 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(11, 1200, 0));
            }
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, World world, Entity entity) {
        world.spawnEntityInWorld((Entity)new LOTREntityGandalfFireball(world, (EntityLivingBase)player));
        return false;
    }

    public boolean hitEntity(ItemStack item, EntityLivingBase hitEntity, EntityLivingBase attackingEntity) {
        hitEntity.addPotionEffect(new PotionEffect(20, 120, 1));
        hitEntity.setFire(8);
        return true;
    }

    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase Entity2) {
        Entity2.addPotionEffect(new PotionEffect(10, 2400, 0));
        Entity2.addPotionEffect(new PotionEffect(11, 1200, 0));
        Entity2.addPotionEffect(new PotionEffect(5, 2400, 0));
        Entity2.addPotionEffect(new PotionEffect(14, 2400, 0));
        Entity2.worldObj.playSoundAtEntity((Entity)Entity2, "lotr:item.nazghul", 4.0f, 1.0f + itemRand.nextFloat() * 0.6f);
        player.attackEntityFrom(DamageSource.generic, 2.0f);
        return false;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.translateToLocal((String)"one.name"));
        list.add(StatCollector.translateToLocal((String)"one.name1"));
        list.add(StatCollector.translateToLocal((String)"one.name2"));
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
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

