/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentThorns
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayer$EnumStatus
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingHealEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.PlayerDropsEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.event.entity.player.PlayerSleepInBedEvent
 *  net.minecraftforge.event.entity.player.PlayerWakeUpEvent
 *  net.minecraftforge.event.world.BlockEvent
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package lotr;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRConfig;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRPotions;
import lotr.common.LOTRTime;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.entity.ai.DontSuffocateAI;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTREntityEnt;
import lotr.common.entity.npc.LOTREntityMan;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;

public class LOTRPlayerDeathHandler {
    private Map<String, InventoryPlayer> invMap = new HashMap<String, InventoryPlayer>();

    @SubscribeEvent
    public void handlePlayerDeath(LivingDeathEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entity;
            String playerUUID = player.getUniqueID().toString();
            if (this.shouldSaveInventory(player)) {
                this.savePlayerInventory(player, playerUUID);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onLivingDeath(LivingDeathEvent event) {
        EntityPlayer player;
        if (event.source.getEntity() instanceof EntityPlayer && event.entity instanceof EntityLivingBase && (player = (EntityPlayer)event.source.getEntity()).getHeldItem() != null && LOTREnchantmentHelper.hasEnchant(player.getHeldItem(), LOTREnchantment.vampireStrike)) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 0));
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onPlayerSleep(PlayerSleepInBedEvent event) {
        final EntityPlayer player = event.entityPlayer;
        if (!player.worldObj.isRemote) {
            if (LOTRConfig.alwaysSetBedSpawn) {
                player.setSpawnChunk(new ChunkCoordinates(event.x, event.y, event.z), false);
                player.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.ITALIC + StatCollector.translateToLocal((String)"tile.bed.respawnSet")));
            }
            if (LOTRConfig.checkBedUnsafe && player.worldObj.provider.dimensionId == LOTRDimension.MIDDLE_EARTH.dimensionID) {
                int width = 8;
                int height = 5;
                final LOTRPlayerData data = LOTRLevelData.getData(player);
                List entities = player.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)player, AxisAlignedBB.getBoundingBox((double)(event.x - width), (double)(event.y - height), (double)(event.z - width), (double)(event.x + width), (double)(event.y + height), (double)(event.z + width)), new IEntitySelector(){

                    public boolean isEntityApplicable(Entity entity) {
                        if (entity instanceof LOTREntityNPC) {
                            LOTREntityNPC npc = (LOTREntityNPC)entity;
                            if (npc.getAttackTarget() == player) {
                                return true;
                            }
                            float alignment = data.getAlignment(LOTRMod.getNPCFaction((Entity)npc));
                            return alignment < 0.0f;
                        }
                        return false;
                    }
                });
                if (!entities.isEmpty()) {
                    event.result = EntityPlayer.EnumStatus.NOT_SAFE;
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityJoinedWorld(EntityJoinWorldEvent event) {
        if (LOTRConfig.ridersAvoidSuffocation) {
            DontSuffocateAI.applyDontSuffocate(event.entity);
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entity;
            if (!player.worldObj.isRemote && player.getHealth() - event.ammount <= 2.0f) {
                for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                    ItemStack itemStack = player.inventory.getStackInSlot(i);
                    if (itemStack == null || itemStack.getItem() != LOTRMod.totemOfUndying) continue;
                    player.worldObj.playSoundAtEntity((Entity)player, "lotr:misc.totem", 1.0f, 1.0f);
                    player.clearActivePotions();
                    player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + 8.0f));
                    LOTRLevelData.getData(player).addAchievement(LOTRAchievement.usetotemOfUndying);
                    player.inventory.decrStackSize(i, 1);
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onLivingHurt1(LivingHurtEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entity;
            int i = MathHelper.floor_double((double)player.posX);
            MathHelper.floor_double((double)player.boundingBox.minY);
            int k = MathHelper.floor_double((double)player.posZ);
            World world = player.worldObj;
            BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
            if (!player.worldObj.isRemote && player.getHealth() - event.ammount <= 2.0f && biome instanceof LOTRBiome && !player.capabilities.isCreativeMode && (double)player.fallDistance <= 35.0) {
                player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 2));
                player.addPotionEffect(new PotionEffect(LOTRPotions.vulnerability.id, 1200, 0));
                player.addPotionEffect(new PotionEffect(LOTRPotions.broken.id, 2400, 0));
                player.worldObj.playSoundAtEntity((Entity)player, "lotr:misc.bone", 0.8f, 0.9f);
                LOTRLevelData.getData(player).addAchievement(LOTRAchievement.highground);
            }
            if (player.getHealth() - event.ammount <= 2.0f && biome instanceof LOTRBiome && !player.capabilities.isCreativeMode && (double)player.fallDistance > 20.0) {
                player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600, 2));
                player.addPotionEffect(new PotionEffect(LOTRPotions.vulnerability.id, 1200, 0));
                player.addPotionEffect(new PotionEffect(LOTRPotions.broken.id, 2400, 0));
                player.worldObj.playSoundAtEntity((Entity)player, "lotr:misc.bone", 0.8f, 0.9f);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onLivingHurtEnch(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer && event.source.getSourceOfDamage() != null) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            int levell = this.getEnchantmentLevelByInstance(player, LOTREnchantment.thornArmor);
            if ((double)player.worldObj.rand.nextFloat() < (double)levell * 0.05) {
                float dmg = event.ammount;
                event.source.getSourceOfDamage().attackEntityFrom(DamageSource.causeThornsDamage((Entity)player), (float)EnchantmentThorns.func_92095_b((int)((int)dmg * 2), (Random)player.getRNG()));
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void register(BlockEvent.BreakEvent event) {
        if (event.block == null || event.world == null || event.getPlayer() == null || event.getPlayer().getHeldItem() == null) {
            return;
        }
        ItemStack item = event.getPlayer().getHeldItem();
        World world = event.world;
        EntityPlayer player = event.getPlayer();
        if (LOTREnchantmentHelper.hasEnchant(item, LOTREnchantment.lumberjack) && event.block.isWood((IBlockAccess)world, event.x, event.y, event.z) && player.isSneaking()) {
            this.breakTree(world, player, event.x, event.y, event.z);
        }
    }

    void breakTree(World world, EntityPlayer player, int x, int y, int z) {
        LinkedList<int[]> queue = new LinkedList<int[]>();
        HashSet<String> visited = new HashSet<String>();
        queue.add(new int[]{x, y, z});
        visited.add(x + "," + y + "," + z);
        int[][] directions = new int[][]{{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}, {1, 1, 0}, {-1, 1, 0}, {0, 1, 1}, {0, 1, -1}, {1, -1, 0}, {-1, -1, 0}, {0, -1, 1}, {0, -1, -1}, {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}, {1, 1, 1}, {-1, 1, 1}, {1, -1, 1}, {-1, -1, 1}, {1, 1, -1}, {-1, 1, -1}, {1, -1, -1}, {-1, -1, -1}, {0, 2, 0}, {0, -2, 0}, {2, 0, 0}, {-2, 0, 0}, {0, 0, 2}, {0, 0, -2}, {1, 2, 0}, {-1, 2, 0}, {0, 2, 1}, {0, 2, -1}, {1, -2, 0}, {-1, -2, 0}, {0, -2, 1}, {0, -2, -1}, {2, 1, 0}, {-2, 1, 0}, {2, -1, 0}, {-2, -1, 0}, {0, 1, 2}, {0, -1, 2}, {0, 1, -2}, {0, -1, -2}, {1, 0, 2}, {-1, 0, 2}, {1, 0, -2}, {-1, 0, -2}};
        int maxHeight = 50;
        int maxRadius = 2;
        while (!queue.isEmpty()) {
            int currZ;
            int currY;
            int[] pos = (int[])queue.poll();
            int currX = pos[0];
            Block block = world.getBlock(currX, currY = pos[1], currZ = pos[2]);
            if (!block.isWood((IBlockAccess)world, currX, currY, currZ) || LOTRBannerProtection.isProtected(world, currX, currY, currZ, LOTRBannerProtection.forPlayer(player), false) || Math.abs(currY - y) > maxHeight || Math.abs(currX - x) > maxRadius || Math.abs(currZ - z) > maxRadius) continue;
            if (player.getHeldItem().attemptDamageItem(1, world.rand)) {
                return;
            }
            this.breakBlock(world, player, currX, currY, currZ);
            for (int[] dir : directions) {
                int newX = currX + dir[0];
                int newY = currY + dir[1];
                int newZ = currZ + dir[2];
                String key = newX + "," + newY + "," + newZ;
                if (visited.contains(key) || !world.getBlock(newX, newY, newZ).isWood((IBlockAccess)world, newX, newY, newZ)) continue;
                queue.add(new int[]{newX, newY, newZ});
                visited.add(key);
            }
        }
    }

    void breakBlock(World world, EntityPlayer player, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        block.onBlockHarvested(world, x, y, z, meta, player);
        if (block.removedByPlayer(world, player, x, y, z, true)) {
            block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            block.harvestBlock(world, player, x, y, z, meta);
        }
        world.setBlockToAir(x, y, z);
        if (!world.isRemote) {
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock((Block)block) + (meta << 12));
        }
    }

    @SubscribeEvent
    public void onPlayerSleepInBed(PlayerSleepInBedEvent event) {
        EntityPlayer player = event.entityPlayer;
        World world = player.worldObj;
        int i = (int)player.posX;
        int j = (int)player.posY;
        int k = (int)player.posZ;
        long worldTime = LOTRTime.getWorldTime();
        if (world.provider.canRespawnHere() && worldTime % (long)LOTRTime.DAY_LENGTH >= 12541L && worldTime % (long)LOTRTime.DAY_LENGTH <= 48000L) {
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "lotr:misc.sleep1", 0.5f, 1.0f);
        }
    }

    @SubscribeEvent
    public void onPlayerWakeUp(PlayerWakeUpEvent event) {
        block4: {
            EntityPlayer player = event.entityPlayer;
            World world = player.worldObj;
            int i = (int)player.posX;
            int j = (int)player.posY;
            int k = (int)player.posZ;
            long worldTime = LOTRTime.getWorldTime();
            if (!world.provider.canRespawnHere()) break block4;
            if (worldTime % (long)LOTRTime.DAY_LENGTH >= 0L && worldTime % (long)LOTRTime.DAY_LENGTH <= 20000L) {
                world.playSound((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "lotr:misc.sleep", 0.5f, 1.0f, false);
                LOTRLevelData.getData(player).addAchievement(LOTRAchievement.sweetDreams);
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 240, 1));
                player.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 120, 0));
                player.setHealth(player.getMaxHealth());
                for (int l = 0; l < 10; ++l) {
                    double d0 = player.posX + (world.rand.nextDouble() - 0.5) * 2.0;
                    double d1 = player.posY + world.rand.nextDouble() * 2.0;
                    double d2 = player.posZ + (world.rand.nextDouble() - 0.5) * 2.0;
                    world.spawnParticle("heart", d0, d1, d2, 0.0, 0.0, 0.0);
                }
            } else {
                for (int l = 0; l < 10; ++l) {
                    double d0 = player.posX + (world.rand.nextDouble() - 0.5) * 2.0;
                    double d1 = player.posY + world.rand.nextDouble() * 2.0;
                    double d2 = player.posZ + (world.rand.nextDouble() - 0.5) * 2.0;
                    world.spawnParticle("crit", d0, d1, d2, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public int getEnchantmentLevelByInstance(EntityPlayer player, LOTREnchantment targetEnchantInstance) {
        int totalLevel = 0;
        for (int slot = 1; slot <= 4; ++slot) {
            if (player.getEquipmentInSlot(slot) == null) continue;
            List<LOTREnchantment> enchants = LOTREnchantmentHelper.getEnchantList(player.getEquipmentInSlot(slot));
            for (LOTREnchantment ench : enchants) {
                if (ench != targetEnchantInstance) continue;
                ++totalLevel;
            }
        }
        return totalLevel;
    }

    @SubscribeEvent
    public void handlePlayerDrops(PlayerDropsEvent event) {
        EntityPlayer player = event.entityPlayer;
        String playerUUID = player.getUniqueID().toString();
        if (this.invMap.containsKey(playerUUID)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void handlePlayerClone(PlayerEvent.Clone event) {
        EntityPlayer original = event.original;
        EntityPlayer player = event.entityPlayer;
        String playerUUID = original.getUniqueID().toString();
        if (this.invMap.containsKey(playerUUID)) {
            System.out.println("Restoring inventory for player: " + playerUUID);
            player.inventory.copyInventory(this.invMap.get(playerUUID));
            this.invMap.remove(playerUUID);
        }
    }

    private boolean shouldSaveInventory(EntityPlayer player) {
        if (LOTRConfig.enableSoulboundClover || player.inventory.hasItem(LOTRMod.magicCloverPlus)) {
            return true;
        }
        if (player.inventory.hasItem(LOTRMod.magicClover)) {
            LOTRLevelData.getData(player).addAchievement(LOTRAchievement.usemagicClover);
            player.inventory.consumeInventoryItem(LOTRMod.magicClover);
            return true;
        }
        int size = player.inventory.getSizeInventory();
        for (int i = 0; i < size; ++i) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack == null || !LOTRPlayerDeathHandler.hasEnchantment(stack, "soulbound")) continue;
            return true;
        }
        return false;
    }

    private void savePlayerInventory(EntityPlayer player, String playerUUID) {
        System.out.println("Saving inventory for player: " + playerUUID);
        InventoryPlayer inv = new InventoryPlayer(player);
        inv.copyInventory(player.inventory);
        this.invMap.put(playerUUID, inv);
        player.inventory.clearInventory(null, -1);
    }

    public static boolean hasEnchantment(ItemStack stack, String enchantmentName) {
        NBTTagList list;
        if (stack != null && stack.hasTagCompound() && (list = stack.getEnchantmentTagList()) != null) {
            for (int i = 0; i < list.tagCount(); ++i) {
                short enchantmentId;
                String enchantment;
                NBTTagCompound nbt = list.getCompoundTagAt(i);
                if (nbt == null || !nbt.hasKey("id") || (enchantment = Enchantment.enchantmentsList[enchantmentId = nbt.getShort("id")].getName()) == null || !enchantment.contains(enchantmentName)) continue;
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onLivingHurtBowDamageBoost(LivingHurtEvent event) {
        if (event.source.getSourceOfDamage() instanceof EntityArrow) {
            EntityPlayer player;
            EntityArrow arrow = (EntityArrow)event.source.getSourceOfDamage();
            if (arrow.shootingEntity != null && arrow.shootingEntity instanceof EntityPlayer && (player = (EntityPlayer)arrow.shootingEntity).isPotionActive(LOTRPotions.bowDamageBoost)) {
                int amplifier = player.getActivePotionEffect(LOTRPotions.bowDamageBoost).getAmplifier();
                event.ammount *= (float)(amplifier + 1) * 2.0f;
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onLivingHurtMeleeDamageBoost(LivingHurtEvent event) {
        if (event.source.getEntity() instanceof EntityPlayer) {
            Potion potion;
            EntityPlayer player = (EntityPlayer)event.source.getEntity();
            EntityLivingBase target = event.entityLiving;
            if (target.isPotionActive(LOTRPotions.vulnerability)) {
                event.ammount = (float)((double)event.ammount * (1.0 + (double)(target.getActivePotionEffect(LOTRPotions.vulnerability).getAmplifier() + 1) * 0.2));
            }
            if ((target instanceof LOTREntityElf || target instanceof LOTREntityOrc || target instanceof LOTREntityTroll || target instanceof LOTREntityWarg) && player.isPotionActive(potion = Potion.potionTypes[LOTRPotions.meleeDamageBoost.id])) {
                event.ammount += 2.0f;
            }
            if ((target instanceof LOTREntityElf || target instanceof LOTREntityMan || target instanceof LOTREntityDwarf || target instanceof LOTREntityEnt) && player.isPotionActive(potion = Potion.potionTypes[LOTRPotions.meleeDamageBoostSauron.id])) {
                event.ammount += 2.0f;
            }
        }
    }

    @SubscribeEvent
    public void onLivingHeal2(LivingHealEvent event) {
        World world = event.entityLiving.worldObj;
        EntityLivingBase entityLiving = event.entityLiving;
        if (entityLiving.isPotionActive(LOTRPotions.blood.id) && event.amount <= 1.0f) {
            event.amount *= 0.7f;
        }
        if (entityLiving.isPotionActive(LOTRPotions.infection.id) && event.amount <= 1.0f) {
            event.amount *= 0.0f;
        }
        if (entityLiving.isPotionActive(LOTRPotions.vulnerability.id) && event.amount <= 1.0f) {
            event.amount *= 0.0f;
        }
    }

    @SubscribeEvent
    public void handleEntityJoinWorld(EntityJoinWorldEvent event) {
        String playerUUID;
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer && event.entity.isEntityAlive() && this.invMap.containsKey(playerUUID = (player = (EntityPlayer)event.entity).getUniqueID().toString())) {
            System.out.println("Restoring inventory for player: " + playerUUID);
            player.inventory.copyInventory(this.invMap.get(playerUUID));
            this.invMap.remove(playerUUID);
        }
    }

}

