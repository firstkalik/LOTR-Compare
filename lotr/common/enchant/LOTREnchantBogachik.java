/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.event.entity.living.LivingEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.player.PlayerDropsEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lotr.common.enchant;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.enchant.LOTREnchantmentSoulbound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LOTREnchantBogachik {
    public static final LOTREnchantBogachik INSTANCE = new LOTREnchantBogachik();
    private static final Logger logger = LogManager.getLogger(LOTREnchantBogachik.class);
    private Map<String, List<ItemStack>> playerDroppedItems = new HashMap<String, List<ItemStack>>();

    @SubscribeEvent
    public void onLivingUpdateSoulbound(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            logger.debug("Player update: {}", new Object[]{player.getDisplayName()});
            if (player.ticksExisted % 60 == 0) {
                logger.debug("Checking inventory for soulbound items...");
                for (ItemStack it : player.inventory.mainInventory) {
                    NBTTagCompound tag;
                    if (it == null || it.getTagCompound() == null || !LOTREnchantmentHelper.hasEnchant(it, LOTREnchantment.soulbound) || LOTREnchantmentSoulbound.getCooldown(tag = it.getTagCompound()) != null && !LOTREnchantmentSoulbound.getCooldown(tag).isNegative() && !LOTREnchantmentSoulbound.getCooldown(tag).isZero()) continue;
                    tag.setInteger("Saves", 1);
                    logger.debug("Updated 'Saves' tag for item: {}", new Object[]{it});
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDrops(PlayerDropsEvent event) {
        EntityPlayer player = event.entityPlayer;
        String playerName = player.getDisplayName();
        ArrayList<ItemStack> droppedItems = new ArrayList<ItemStack>();
        Iterator iterator = event.drops.iterator();
        logger.debug("Player {} drops items", new Object[]{playerName});
        while (iterator.hasNext()) {
            NBTTagCompound tag;
            EntityItem drop = (EntityItem)iterator.next();
            ItemStack stack = drop.getEntityItem();
            int level = 1;
            if (!LOTREnchantmentHelper.hasEnchant(stack, LOTREnchantment.soulbound) || level <= 0) continue;
            NBTTagCompound nBTTagCompound = tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
            if (!tag.hasKey("Saves")) {
                tag.setInteger("Saves", level - 1);
                iterator.remove();
                droppedItems.add(stack);
                logger.debug("Item with 'Saves' tag initialized: {}", new Object[]{stack});
            } else {
                int Saves;
                long CDMillis = tag.getLong("CD");
                Instant CD = Instant.ofEpochMilli(CDMillis);
                if (CD.isBefore(Instant.now())) {
                    tag.setInteger("Saves", level);
                    tag.setLong("CD", 0L);
                    logger.debug("Item cooldown expired, resetting 'Saves': {}", new Object[]{stack});
                }
                if ((Saves = tag.getInteger("Saves")) > 0) {
                    tag.setInteger("Saves", Saves - 1);
                    iterator.remove();
                    droppedItems.add(stack);
                    logger.debug("Item with 'Saves' tag decremented: {}", new Object[]{stack});
                }
            }
            stack.setTagCompound(tag);
        }
        if (!droppedItems.isEmpty()) {
            this.playerDroppedItems.put(playerName, droppedItems);
            logger.debug("Player {} dropped items recorded: {}", new Object[]{playerName, droppedItems});
        }
    }

    private long getCooldownForLevel() {
        return 3600L;
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        EntityPlayer player = event.player;
        String playerName = player.getDisplayName();
        logger.debug("Player {} respawned", new Object[]{playerName});
        if (this.playerDroppedItems.containsKey(playerName)) {
            List<ItemStack> droppedItems = this.playerDroppedItems.get(playerName);
            Iterator<ItemStack> iterator = droppedItems.iterator();
            while (iterator.hasNext()) {
                ItemStack item = iterator.next();
                if (LOTREnchantmentHelper.hasEnchant(item, LOTREnchantment.soulbound)) {
                    NBTTagCompound tag;
                    Instant now = Instant.now();
                    NBTTagCompound nBTTagCompound = tag = item.hasTagCompound() ? item.getTagCompound() : new NBTTagCompound();
                    if (tag.hasKey("Saves") && (now.toEpochMilli() > tag.getLong("CD") || !tag.hasKey("CD") || tag.getLong("CD") == 0L)) {
                        Instant CD = now.plusSeconds(this.getCooldownForLevel());
                        tag.setLong("CD", CD.toEpochMilli());
                        logger.debug("Cooldown updated for item: {}", new Object[]{item});
                    }
                    item.setTagCompound(tag);
                    if (!player.inventory.addItemStackToInventory(item)) {
                        player.dropPlayerItemWithRandomChoice(item, false);
                        logger.debug("Item dropped on ground: {}", new Object[]{item});
                    }
                    iterator.remove();
                    continue;
                }
                logger.debug("Item {} does not have 'soulbound' enchantment, not respawning", new Object[]{item});
            }
            if (droppedItems.isEmpty()) {
                this.playerDroppedItems.remove(playerName);
                logger.debug("Player {}'s dropped items list cleared", new Object[]{playerName});
            }
        }
    }
}

