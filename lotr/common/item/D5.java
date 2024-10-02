/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
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
import lotr.common.item.LOTRRingOne;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class D5
extends LOTRItemBaseRing2 {
    private final ItemStack fauxPick = new ItemStack(Items.diamond_pickaxe);
    public static int cooldown = 10;
    public static int defaultCharges = 1000;

    public D5() {
        this.setMaxDamage(defaultCharges + 1);
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
        if (targetBlock == Blocks.stone_stairs) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.stairsDwarvenBrick);
            return true;
        }
        if (targetBlock == Blocks.cobblestone) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.brick, 6, 0);
            return true;
        }
        if (targetBlock == Blocks.stone) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.brick, 6, 0);
            return true;
        }
        if (targetBlock == Blocks.obsidian) {
            world.setBlock(targetX, targetY, targetZ, Blocks.lava);
            return true;
        }
        if (targetBlock == LOTRMod.oreIron) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.oreGold);
            return true;
        }
        if (targetBlock == LOTRMod.oreGold) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.oreIron);
            return true;
        }
        if (targetBlock == Blocks.iron_ore) {
            world.setBlock(targetX, targetY, targetZ, Blocks.gold_ore);
            return true;
        }
        if (targetBlock == Blocks.gravel) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.brick, 6, 0);
            return true;
        }
        if (targetBlock == Blocks.gold_ore) {
            world.setBlock(targetX, targetY, targetZ, Blocks.iron_ore);
            return true;
        }
        if (targetBlock == Blocks.dirt) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.brick, 6, 0);
            return true;
        }
        if (targetBlock == Blocks.iron_block) {
            world.setBlock(targetX, targetY, targetZ, Blocks.gold_block);
            return true;
        }
        if (targetBlock == Blocks.gold_block) {
            world.setBlock(targetX, targetY, targetZ, Blocks.iron_block);
            return true;
        }
        if (targetBlock == Blocks.torch) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.dwarvenTorch);
            return true;
        }
        if (targetBlock == Blocks.iron_bars) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.dwarfBars);
            return true;
        }
        if (targetBlock == LOTRMod.dwarfBars) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.blueDwarfBars);
            return true;
        }
        if (targetBlock == LOTRMod.blueDwarfBars) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.reddwarfBars);
            return true;
        }
        if (targetBlock == LOTRMod.rottenfleshBlock) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.kebabBlock);
            return true;
        }
        if (targetBlock == LOTRMod.dwarvenForge) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.moriaForge);
            return true;
        }
        if (targetBlock == LOTRMod.moriaForge) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.elvenForge);
            return true;
        }
        if (targetBlock == LOTRMod.elvenForge) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.orcForge);
            return true;
        }
        if (targetBlock == LOTRMod.orcForge) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.dwarvenForge);
            return true;
        }
        if (targetBlock == LOTRMod.erebortable) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.dwarvenTable);
            return true;
        }
        if (targetBlock == LOTRMod.dwarvenTable) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.erebortable);
            return true;
        }
        if (targetBlock == LOTRMod.reddwarvenTable) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.blueDwarvenTable);
            return true;
        }
        if (targetBlock == LOTRMod.blueDwarvenTable) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.reddwarvenTable);
            return true;
        }
        if (targetBlock == LOTRMod.gateGold) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.gateSilver);
            return true;
        }
        if (targetBlock == LOTRMod.gateSilver) {
            world.setBlock(targetX, targetY, targetZ, LOTRMod.gateGold);
            return true;
        }
        if (targetBlock == Blocks.stonebrick && world.getBlockMetadata(targetX, targetY, targetZ) == 0) {
            world.setBlockMetadataWithNotify(targetX, targetY, targetZ, 1, 3);
            return true;
        }
        return ItemDye.applyBonemeal((ItemStack)fauxItemStack, (World)world, (int)targetX, (int)targetY, (int)targetZ, (EntityPlayer)playerEntity);
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

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.dwarfSteel;
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        world.playSoundAtEntity((Entity)playerEntity, "lotr:dwarf.attack", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        if (!world.isRemote) {
            playerEntity.addPotionEffect(new PotionEffect(5, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(38, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(11, 2400, 0));
            playerEntity.addPotionEffect(new PotionEffect(1, 2400, 0));
            playerEntity.attackEntityFrom(DamageSource.generic, 5.0f);
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            playerEntity.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u0421\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d\u0430"));
            srcItemStack.damageItem(1, (EntityLivingBase)playerEntity);
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.INFERNAL, (Entity)playerEntity);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)playerEntity, 64.0));
        }
        return srcItemStack;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        if (entity instanceof EntityPlayer) {
            ItemStack equipped;
            EntityPlayer player1;
            EntityPlayer player = (EntityPlayer)entity;
            boolean hasRing = player.inventory.hasItemStack(itemstack);
            player.stepHeight = !hasRing ? (player.onGround && player.moveForward > 0.0f ? 0.5f : 0.5f) : (player.onGround && player.moveForward > 0.0f ? 1.0f : 0.5f);
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(11, 300, 0));
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
            }
            if (entity.ticksExisted % 1199 != 0) {
                return;
            }
            LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.INFERNAL, (Entity)player);
            itemstack.damageItem(1, (EntityLivingBase)player);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)player, 128.0));
            float high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GUNDABAD);
            float high_elf5 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.UTUMNO);
            float high_elf6 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR);
            float high_elf7 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DOL_GULDUR);
            if (high_elf4 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 1200, 3));
            }
            if (high_elf5 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 1200, 3));
            }
            if (high_elf6 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 1200, 3));
            }
            if (high_elf7 <= -1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 1200, 3));
            }
            if (high_elf4 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf5 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf6 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf7 <= -2000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 1200, 0));
            }
            if (high_elf4 >= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(32, 1200, 1));
            }
            if (high_elf5 >= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(32, 1200, 1));
            }
            if (high_elf6 >= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(32, 1200, 1));
            }
            if (high_elf7 >= -4000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(32, 1200, 1));
            }
            if (high_elf4 >= 500.0f) {
                entity.setFire(2);
            }
            if (high_elf5 >= 500.0f) {
                entity.setFire(2);
            }
            if (high_elf6 >= 500.0f) {
                entity.setFire(2);
            }
            if (high_elf7 >= 500.0f) {
                entity.setFire(2);
            }
            if (high_elf4 >= 1000.0f) {
                entity.setFire(5);
            }
            if (high_elf5 >= 1000.0f) {
                entity.setFire(5);
            }
            if (high_elf5 >= 1000.0f) {
                entity.setFire(5);
            }
            if (high_elf7 >= 1000.0f) {
                entity.setFire(5);
            }
            if (high_elf4 >= 2000.0f) {
                entity.setFire(10);
            }
            if (high_elf5 >= 2000.0f) {
                entity.setFire(10);
            }
            if (high_elf6 >= 2000.0f) {
                entity.setFire(10);
            }
            if (high_elf7 >= 2000.0f) {
                entity.setFire(10);
            }
            if (high_elf4 >= 4000.0f) {
                entity.setFire(20);
            }
            if (high_elf5 >= 4000.0f) {
                entity.setFire(18);
            }
            if (high_elf6 >= 4000.0f) {
                entity.setFire(18);
            }
            if (high_elf7 >= 4000.0f) {
                entity.setFire(18);
            }
        }
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.translateToLocal((String)"resistance.name"));
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
    }

    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase Entity2) {
        Entity2.addPotionEffect(new PotionEffect(10, 2400, 0));
        Entity2.addPotionEffect(new PotionEffect(11, 1200, 0));
        Entity2.addPotionEffect(new PotionEffect(5, 2400, 0));
        Entity2.worldObj.playSoundAtEntity((Entity)Entity2, "lotr:item.ring", 4.0f, 1.0f + itemRand.nextFloat() * 1.0f);
        player.attackEntityFrom(DamageSource.generic, 2.0f);
        return false;
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

