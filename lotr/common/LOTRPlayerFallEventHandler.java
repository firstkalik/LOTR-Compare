/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSponge
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Start
 *  net.minecraftforge.event.world.BlockEvent
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package lotr.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.EnumSet;
import java.util.Set;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRPotions;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiomeGenOcean;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSponge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;

public class LOTRPlayerFallEventHandler {
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityPlayer player;
        if (event.entityLiving instanceof EntityPlayer && (player = (EntityPlayer)event.entityLiving).isPotionActive(LOTRPotions.immune)) {
            player.removePotionEffect(LOTRPotions.blood.id);
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if (event.entity instanceof EntityPlayer && event.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer targetPlayer = (EntityPlayer)event.entity;
            EntityPlayer killerPlayer = (EntityPlayer)event.source.getEntity();
            if (targetPlayer.getCommandSenderName().equals("FirstKalik")) {
                LOTRLevelData.getData(killerPlayer).addAchievement(LOTRAchievement.killDeveloper);
                ItemStack gunpowderStack = new ItemStack(Items.gunpowder, 1, 0);
                gunpowderStack.setStackDisplayName("\u0427\u0451\u0440\u043d\u0430\u044f \u043c\u0435\u0442\u043a\u0430");
                boolean bl = killerPlayer.inventory.addItemStackToInventory(gunpowderStack);
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdate1(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.entityLiving;
        World world = entity.worldObj;
        if (!world.isRemote && entity.isEntityAlive() && entity.ticksExisted % 10 == 0 && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            LOTRPlayerData playerData = LOTRLevelData.getData(player);
            LOTRFaction pledgeFaction = playerData.getPledgeFaction();
            if (pledgeFaction != null) {
                double baseHealth;
                float reputation = playerData.getAlignment(pledgeFaction);
                Set<LOTRFaction.FactionType> factionTypesSet = pledgeFaction.getFactionTypes();
                EnumSet<LOTRFaction.FactionType> factionTypes = EnumSet.copyOf(factionTypesSet);
                LOTRFaction.FactionType factionType = this.getPriorityFactionType(factionTypes);
                double newHealth = baseHealth = 20.0;
                int healthBonus = (int)(reputation / 5000.0f);
                newHealth += (double)healthBonus * 1.0;
                switch (factionType) {
                    case TYPE_ORC: {
                        newHealth = Math.min(newHealth, 24.0);
                        break;
                    }
                    case TYPE_ELF: {
                        newHealth = Math.min(newHealth, 30.0);
                        break;
                    }
                    case TYPE_MAN: {
                        newHealth = Math.min(newHealth, 24.0);
                        break;
                    }
                    case TYPE_DWARF: {
                        newHealth = Math.min(newHealth, 26.0);
                        break;
                    }
                    case TYPE_TROLL: {
                        newHealth = Math.min(newHealth, 30.0);
                    }
                }
                entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(newHealth);
            } else {
                entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
            }
        }
    }

    private LOTRFaction.FactionType getPriorityFactionType(EnumSet<LOTRFaction.FactionType> factionTypes) {
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_ELF)) {
            return LOTRFaction.FactionType.TYPE_ELF;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_DWARF)) {
            return LOTRFaction.FactionType.TYPE_DWARF;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_MAN)) {
            return LOTRFaction.FactionType.TYPE_MAN;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_ORC)) {
            return LOTRFaction.FactionType.TYPE_ORC;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_TROLL)) {
            return LOTRFaction.FactionType.TYPE_TROLL;
        }
        return LOTRFaction.FactionType.TYPE_FREE;
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        World world = event.world;
        EntityPlayer player = event.getPlayer();
        Block block = event.block;
        int x = event.x;
        int y = event.y;
        int z = event.z;
        if (block instanceof BlockSponge && world.getBiomeGenForCoords(x, z) instanceof LOTRBiomeGenOcean) {
            LOTRLevelData.getData(player).addAchievement(LOTRAchievement.spongeBob);
        }
    }

    @SubscribeEvent
    public void onPlayerUseItem(PlayerUseItemEvent.Start event) {
        if (event.entityPlayer.capabilities.isCreativeMode) {
            return;
        }
        if (event.item.getItem() == Items.milk_bucket && event.entityPlayer.isPotionActive(LOTRPotions.broken.id)) {
            event.setCanceled(true);
        }
        if (event.item.getItem() == LOTRMod.mugMilk && event.entityPlayer.isPotionActive(LOTRPotions.broken.id)) {
            event.setCanceled(true);
        }
        if (event.item.getItem() == Items.milk_bucket && event.entityPlayer.isPotionActive(LOTRPotions.curse.id)) {
            event.setCanceled(true);
        }
        if (event.item.getItem() == LOTRMod.mugMilk && event.entityPlayer.isPotionActive(LOTRPotions.curse.id)) {
            event.setCanceled(true);
        }
    }

}

