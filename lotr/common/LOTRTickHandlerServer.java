/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$WorldTickEvent
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFireworkRocket
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemEditableBook
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemWritableBook
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.storage.WorldInfo
 *  net.minecraftforge.common.DimensionManager
 */
package lotr.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRDate;
import lotr.common.LOTRDimension;
import lotr.common.LOTRGreyWandererTracker;
import lotr.common.LOTRInterModComms;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRNetHandlerPlayServer;
import lotr.common.LOTRReflection;
import lotr.common.LOTRTime;
import lotr.common.block.LOTRBlockPortal;
import lotr.common.entity.item.LOTREntityPortal;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionBounties;
import lotr.common.fac.LOTRFactionRelations;
import lotr.common.fellowship.LOTRFellowshipData;
import lotr.common.item.LOTRItemStructureSpawner;
import lotr.common.world.LOTRTeleporter;
import lotr.common.world.LOTRUtumnoLevel;
import lotr.common.world.LOTRWorldInfo;
import lotr.common.world.LOTRWorldProvider;
import lotr.common.world.LOTRWorldProviderUtumno;
import lotr.common.world.biome.LOTRBiomeGenGorgoroth;
import lotr.common.world.biome.variant.LOTRBiomeVariantStorage;
import lotr.common.world.map.LOTRConquestGrid;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRSpawnerNPCs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;

public class LOTRTickHandlerServer {
    public static HashMap playersInPortals = new HashMap();
    public static HashMap playersInElvenPortals = new HashMap();
    public static HashMap playersInMorgulPortals = new HashMap();
    private int fireworkDisplay;

    public LOTRTickHandlerServer() {
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if (world.isRemote) {
            return;
        }
        if (event.phase == TickEvent.Phase.START && world == DimensionManager.getWorld((int)0)) {
            World overworld = world;
            if (LOTRLevelData.needsLoad) {
                LOTRLevelData.load();
            }
            if (LOTRTime.needsLoad) {
                LOTRTime.load();
            }
            if (LOTRFellowshipData.needsLoad) {
                LOTRFellowshipData.loadAll();
            }
            if (LOTRFactionBounties.needsLoad) {
                LOTRFactionBounties.loadAll();
            }
            if (LOTRFactionRelations.needsLoad) {
                LOTRFactionRelations.load();
            }
            if (LOTRConquestGrid.needsLoad) {
                LOTRConquestGrid.loadAllZones();
            }
            for (WorldServer dimWorld : MinecraftServer.getServer().worldServers) {
                WorldInfo prevWorldInfo;
                if (!(dimWorld.provider instanceof LOTRWorldProvider) || (prevWorldInfo = dimWorld.getWorldInfo()).getClass() == LOTRWorldInfo.class) continue;
                LOTRWorldInfo newWorldInfo = new LOTRWorldInfo(overworld.getWorldInfo());
                newWorldInfo.setWorldName(prevWorldInfo.getWorldName());
                LOTRReflection.setWorldInfo((World)dimWorld, (WorldInfo)newWorldInfo);
                FMLLog.info((String)"LOTR: Successfully replaced world info in %s", (Object[])new Object[]{LOTRDimension.getCurrentDimensionWithFallback((World)dimWorld).dimensionName});
            }
            LOTRBannerProtection.updateWarningCooldowns();
            LOTRInterModComms.update();
        }
        if (event.phase == TickEvent.Phase.END) {
            if (world == DimensionManager.getWorld((int)0)) {
                if (LOTRLevelData.anyDataNeedsSave()) {
                    LOTRLevelData.save();
                }
                if (LOTRFellowshipData.anyDataNeedsSave()) {
                    LOTRFellowshipData.saveAll();
                }
                LOTRFactionBounties.updateAll();
                if (LOTRFactionBounties.anyDataNeedsSave()) {
                    LOTRFactionBounties.saveAll();
                }
                if (LOTRFactionRelations.needsSave()) {
                    LOTRFactionRelations.save();
                }
                if (LOTRConquestGrid.anyChangedZones()) {
                    LOTRConquestGrid.saveChangedZones();
                }
                if (world.getTotalWorldTime() % 600L == 0L) {
                    LOTRLevelData.save();
                    LOTRLevelData.saveAndClearUnusedPlayerData();
                    LOTRFellowshipData.saveAll();
                    LOTRFellowshipData.saveAndClearUnusedFellowships();
                    LOTRFactionBounties.saveAll();
                    LOTRFactionRelations.save();
                }
                if (LOTRItemStructureSpawner.lastStructureSpawnTick > 0) {
                    --LOTRItemStructureSpawner.lastStructureSpawnTick;
                }
                LOTRTime.update();
                LOTRGreyWandererTracker.updateCooldowns();
            }
            if (world == DimensionManager.getWorld((int)LOTRDimension.MIDDLE_EARTH.dimensionID)) {
                LOTRDate.update(world);
                if (LOTRMod.canSpawnMobs(world)) {
                    LOTRSpawnerNPCs.performSpawning(world);
                    LOTREventSpawner.performSpawning(world);
                }
                LOTRConquestGrid.updateZones(world);
                if (!world.playerEntities.isEmpty()) {
                    if (LOTRMod.isNewYearsDay()) {
                        if (this.fireworkDisplay == 0 && world.rand.nextInt(4000) == 0) {
                            this.fireworkDisplay = 100 + world.rand.nextInt(300);
                        }
                        if (this.fireworkDisplay > 0) {
                            --this.fireworkDisplay;
                            if (world.rand.nextInt(50) == 0) {
                                int launches = 1 + world.rand.nextInt(7 + world.playerEntities.size() / 2);
                                int range = 64;
                                for (int l = 0; l < launches; ++l) {
                                    int k;
                                    EntityPlayer entityplayer = (EntityPlayer)world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
                                    int i = MathHelper.floor_double((double)entityplayer.posX) + MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(-range), (int)range);
                                    if (!world.getBlock(i, world.getHeightValue(i, k = MathHelper.floor_double((double)entityplayer.posZ) + MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(-range), (int)range)) - 1, k).isNormalCube()) continue;
                                    int fireworks = 1 + world.rand.nextInt(4);
                                    for (int l1 = 0; l1 < fireworks; ++l1) {
                                        int k1;
                                        int j1;
                                        int groupRange = 8;
                                        int i1 = i - world.rand.nextInt(groupRange) + world.rand.nextInt(groupRange);
                                        if (!world.getBlock(i1, (j1 = world.getHeightValue(i1, k1 = k - world.rand.nextInt(groupRange) + world.rand.nextInt(groupRange))) - 1, k1).isNormalCube()) continue;
                                        ItemStack itemstack = new ItemStack(Items.fireworks);
                                        NBTTagCompound itemData = new NBTTagCompound();
                                        NBTTagCompound fireworkData = new NBTTagCompound();
                                        NBTTagList explosionsList = new NBTTagList();
                                        int explosions = 1 + world.rand.nextInt(3);
                                        for (int l2 = 0; l2 < explosions; ++l2) {
                                            NBTTagCompound explosionData = new NBTTagCompound();
                                            if (world.rand.nextBoolean()) {
                                                explosionData.setBoolean("Flicker", true);
                                            }
                                            if (world.rand.nextBoolean()) {
                                                explosionData.setBoolean("Trail", true);
                                            }
                                            int[] colors = new int[1 + world.rand.nextInt(3)];
                                            for (int l3 = 0; l3 < colors.length; ++l3) {
                                                colors[l3] = ItemDye.field_150922_c[world.rand.nextInt(ItemDye.field_150922_c.length)];
                                            }
                                            explosionData.setIntArray("Colors", colors);
                                            int effectType = world.rand.nextInt(5);
                                            if (effectType == 3) {
                                                effectType = 0;
                                            }
                                            explosionData.setByte("Type", (byte)effectType);
                                            explosionsList.appendTag((NBTBase)explosionData);
                                        }
                                        fireworkData.setTag("Explosions", (NBTBase)explosionsList);
                                        int flight = 1 + world.rand.nextInt(3);
                                        fireworkData.setByte("Flight", (byte)flight);
                                        itemData.setTag("Fireworks", (NBTBase)fireworkData);
                                        itemstack.setTagCompound(itemData);
                                        EntityFireworkRocket firework = new EntityFireworkRocket(world, (double)i1 + 0.5, (double)j1 + 0.5, (double)k1 + 0.5, itemstack);
                                        world.spawnEntityInWorld((Entity)firework);
                                    }
                                }
                            }
                        }
                    }
                    if (world.getTotalWorldTime() % 20L == 0L) {
                        for (Object element : world.playerEntities) {
                            EntityPlayer entityplayer = (EntityPlayer)element;
                            LOTRLevelData.sendPlayerLocationsToPlayer(entityplayer, world);
                        }
                    }
                }
            }
            if (world == DimensionManager.getWorld((int)LOTRDimension.UTUMNO.dimensionID) && !world.playerEntities.isEmpty() && LOTRMod.canSpawnMobs(world)) {
                LOTRSpawnerNPCs.performSpawning(world);
            }
            if (world.provider instanceof LOTRWorldProvider && world.getTotalWorldTime() % 100L == 0L) {
                LOTRBiomeVariantStorage.performCleanup((WorldServer)world);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.worldObj;
        if (world == null || world.isRemote) {
            return;
        }
        if (player != null && player instanceof EntityPlayerMP) {
            EntityPlayerMP entityplayer = (EntityPlayerMP)player;
            if (event.phase == TickEvent.Phase.START && entityplayer.playerNetServerHandler != null && !(entityplayer.playerNetServerHandler instanceof LOTRNetHandlerPlayServer)) {
                entityplayer.playerNetServerHandler = new LOTRNetHandlerPlayServer(MinecraftServer.getServer(), entityplayer.playerNetServerHandler.netManager, entityplayer);
            }
            if (event.phase == TickEvent.Phase.END) {
                EntityItem item;
                ItemStack heldItem;
                List items;
                LOTRLevelData.getData((EntityPlayer)entityplayer).onUpdate(entityplayer, (WorldServer)world);
                NetHandlerPlayServer netHandler = entityplayer.playerNetServerHandler;
                if (netHandler instanceof LOTRNetHandlerPlayServer) {
                    ((LOTRNetHandlerPlayServer)netHandler).update();
                }
                if ((heldItem = entityplayer.getHeldItem()) != null && (heldItem.getItem() instanceof ItemWritableBook || heldItem.getItem() instanceof ItemEditableBook)) {
                    entityplayer.func_143004_u();
                }
                if (entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) {
                    items = world.getEntitiesWithinAABB(EntityItem.class, entityplayer.boundingBox.expand(16.0, 16.0, 16.0));
                    for (Object obj : items) {
                        EntityItem item1 = (EntityItem)obj;
                        if (item1.getEntityItem() == null || item1.getEntityItem().getItem() != LOTRMod.theOneRing || !item1.isBurning()) continue;
                        int fireX = MathHelper.floor_double((double)item1.posX);
                        int fireY = MathHelper.floor_double((double)item1.posY);
                        int fireZ = MathHelper.floor_double((double)item1.posZ);
                        for (int x = -1; x <= 1; ++x) {
                            for (int y = -1; y <= 1; ++y) {
                                for (int z = -1; z <= 1; ++z) {
                                    Block block = world.getBlock(fireX + x, fireY + y, fireZ + z);
                                    if (block == Blocks.fire) {
                                        world.setBlockToAir(fireX + x, fireY + y, fireZ + z);
                                        continue;
                                    }
                                    if (block != Blocks.lava) continue;
                                    world.setBlock(fireX + x, fireY + y, fireZ + z, Blocks.obsidian);
                                }
                            }
                        }
                        BiomeGenBase biome = world.getBiomeGenForCoords(MathHelper.floor_double((double)entityplayer.posX), MathHelper.floor_double((double)entityplayer.posZ));
                        boolean ringRemoved = false;
                        if (biome instanceof LOTRBiomeGenGorgoroth) {
                            for (int i = 0; i < entityplayer.inventory.getSizeInventory(); ++i) {
                                ItemStack itemStack = entityplayer.inventory.getStackInSlot(i);
                                if (itemStack == null || itemStack.getItem() != LOTRMod.theOneRing) continue;
                                entityplayer.inventory.setInventorySlotContents(i, null);
                                ringRemoved = true;
                                break;
                            }
                        }
                        if (!ringRemoved && biome instanceof LOTRBiomeGenGorgoroth) continue;
                        item1.setDead();
                        if (ringRemoved) {
                            ItemStack ringStack = new ItemStack(LOTRMod.goldRing);
                            world.spawnEntityInWorld((Entity)new EntityItem(world, item1.posX, item1.posY, item1.posZ, ringStack));
                            LOTRLevelData.getData((EntityPlayer)entityplayer).addAchievement(LOTRAchievement.destroyRing);
                            continue;
                        }
                        LOTRLevelData.getData((EntityPlayer)entityplayer).addAchievement(LOTRAchievement.burnRing);
                        ItemStack ringStack = item1.getEntityItem();
                        world.spawnEntityInWorld((Entity)new EntityItem(world, item1.posX, item1.posY, item1.posZ, ringStack));
                    }
                }
                if (entityplayer.dimension == 0 && LOTRLevelData.madePortal == 0) {
                    items = world.getEntitiesWithinAABB(EntityItem.class, entityplayer.boundingBox.expand(16.0, 16.0, 16.0));
                    for (Object obj : items) {
                        item = (EntityItem)obj;
                        if (LOTRLevelData.madePortal != 0 || item.getEntityItem() == null || item.getEntityItem().getItem() != LOTRMod.goldRing || !item.isBurning()) continue;
                        LOTRLevelData.setMadePortal(1);
                        LOTRLevelData.markOverworldPortalLocation(MathHelper.floor_double((double)item.posX), MathHelper.floor_double((double)item.posY), MathHelper.floor_double((double)item.posZ));
                        item.setDead();
                        world.createExplosion((Entity)entityplayer, item.posX, item.posY + 3.0, item.posZ, 3.0f, true);
                        LOTREntityPortal portal = new LOTREntityPortal(world);
                        portal.setLocationAndAngles(item.posX, item.posY + 3.0, item.posZ, 0.0f, 0.0f);
                        world.spawnEntityInWorld((Entity)portal);
                    }
                }
                if (entityplayer.dimension == 0 || entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) {
                    items = world.getEntitiesWithinAABB(EntityItem.class, entityplayer.boundingBox.expand(16.0, 16.0, 16.0));
                    for (Object obj : items) {
                        int i1;
                        boolean foundPortalLocation;
                        int[] portalLocation;
                        int k1;
                        item = (EntityItem)obj;
                        if (item.getEntityItem() == null) continue;
                        int i = MathHelper.floor_double((double)item.posX);
                        int j = MathHelper.floor_double((double)item.posY);
                        int k = MathHelper.floor_double((double)item.posZ);
                        ItemStack itemstack = item.getEntityItem();
                        if ((LOTRLevelData.getData((EntityPlayer)entityplayer).getAlignment(LOTRFaction.LOTHLORIEN) >= 1.0f || LOTRLevelData.getData((EntityPlayer)entityplayer).getAlignment(LOTRFaction.HIGH_ELF) >= 1.0f) && (itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.elanor) || itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.niphredil))) {
                            foundPortalLocation = false;
                            portalLocation = new int[3];
                            for (i1 = i - 2; !foundPortalLocation && i1 <= i + 2; ++i1) {
                                for (k1 = k - 2; !foundPortalLocation && k1 <= k + 2; ++k1) {
                                    if (!((LOTRBlockPortal)LOTRMod.elvenPortal).isValidPortalLocation(world, i1, j, k1, false)) continue;
                                    foundPortalLocation = true;
                                    portalLocation = new int[]{i1, j, k1};
                                }
                            }
                            if (foundPortalLocation) {
                                item.setDead();
                                for (i1 = -1; i1 <= 1; ++i1) {
                                    for (k1 = -1; k1 <= 1; ++k1) {
                                        world.setBlock(portalLocation[0] + i1, portalLocation[1], portalLocation[2] + k1, LOTRMod.elvenPortal, 0, 2);
                                    }
                                }
                            }
                        }
                        if (LOTRLevelData.getData((EntityPlayer)entityplayer).getAlignment(LOTRFaction.MORDOR) < 1.0f && LOTRLevelData.getData((EntityPlayer)entityplayer).getAlignment(LOTRFaction.GUNDABAD) < 1.0f && LOTRLevelData.getData((EntityPlayer)entityplayer).getAlignment(LOTRFaction.DOL_GULDUR) < 1.0f || !LOTRMod.isOreNameEqual(itemstack, "bone")) continue;
                        foundPortalLocation = false;
                        portalLocation = new int[3];
                        for (i1 = i - 2; !foundPortalLocation && i1 <= i + 2; ++i1) {
                            for (k1 = k - 2; !foundPortalLocation && k1 <= k + 2; ++k1) {
                                if (!((LOTRBlockPortal)LOTRMod.morgulPortal).isValidPortalLocation(world, i1, j, k1, false)) continue;
                                foundPortalLocation = true;
                                portalLocation = new int[]{i1, j, k1};
                            }
                        }
                        if (!foundPortalLocation) continue;
                        item.setDead();
                        for (i1 = -1; i1 <= 1; ++i1) {
                            for (k1 = -1; k1 <= 1; ++k1) {
                                world.setBlock(portalLocation[0] + i1, portalLocation[1], portalLocation[2] + k1, LOTRMod.morgulPortal, 0, 2);
                            }
                        }
                    }
                }
                if ((entityplayer.dimension == 0 || entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) && playersInPortals.containsKey((Object)entityplayer)) {
                    int i;
                    List portals = world.getEntitiesWithinAABB(LOTREntityPortal.class, entityplayer.boundingBox.expand(8.0, 8.0, 8.0));
                    boolean inPortal = false;
                    for (i = 0; i < portals.size(); ++i) {
                        LOTREntityPortal portal = (LOTREntityPortal)((Object)portals.get(i));
                        if (!portal.boundingBox.intersectsWith(entityplayer.boundingBox)) continue;
                        inPortal = true;
                        break;
                    }
                    if (inPortal) {
                        i = (Integer)playersInPortals.get((Object)entityplayer);
                        playersInPortals.put(entityplayer, ++i);
                        if (i >= 100) {
                            int dimension = 0;
                            if (entityplayer.dimension == 0) {
                                dimension = LOTRDimension.MIDDLE_EARTH.dimensionID;
                            } else if (entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) {
                                dimension = 0;
                            }
                            if (world instanceof WorldServer) {
                                MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(entityplayer, dimension, (Teleporter)new LOTRTeleporter(DimensionManager.getWorld((int)dimension), true));
                            }
                            playersInPortals.remove((Object)entityplayer);
                        }
                    } else {
                        playersInPortals.remove((Object)entityplayer);
                    }
                }
                this.updatePlayerInPortal(entityplayer, playersInElvenPortals, (LOTRBlockPortal)LOTRMod.elvenPortal);
                this.updatePlayerInPortal(entityplayer, playersInMorgulPortals, (LOTRBlockPortal)LOTRMod.morgulPortal);
                if (entityplayer.dimension == LOTRDimension.UTUMNO.dimensionID) {
                    int i = MathHelper.floor_double((double)entityplayer.posX);
                    int j = MathHelper.floor_double((double)entityplayer.boundingBox.minY);
                    int k = MathHelper.floor_double((double)entityplayer.posZ);
                    int range = 32;
                    for (int l = 0; l < 60; ++l) {
                        int i1 = i + world.rand.nextInt(range) - world.rand.nextInt(range);
                        int j1 = j + world.rand.nextInt(range) - world.rand.nextInt(range);
                        int k1 = k + world.rand.nextInt(range) - world.rand.nextInt(range);
                        if (LOTRUtumnoLevel.forY(j1) == LOTRUtumnoLevel.ICE) {
                            Block block = world.getBlock(i1, j1, k1);
                            int meta = world.getBlockMetadata(i1, j1, k1);
                            if (block.getMaterial() == Material.water && meta == 0) {
                                world.setBlock(i1, j1, k1, Blocks.ice, 0, 3);
                            }
                        }
                        if (LOTRUtumnoLevel.forY(j1) != LOTRUtumnoLevel.FIRE) continue;
                        Block block = world.getBlock(i1, j1, k1);
                        int meta = world.getBlockMetadata(i1, j1, k1);
                        if (block.getMaterial() != Material.water || meta != 0) continue;
                        world.setBlock(i1, j1, k1, Blocks.air, 0, 3);
                        LOTRWorldProviderUtumno.doEvaporateFX(world, i1, j1, k1);
                    }
                }
            }
        }
    }

    private void updatePlayerInPortal(EntityPlayerMP entityplayer, HashMap players, LOTRBlockPortal portalBlock) {
        if ((entityplayer.dimension == 0 || entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) && players.containsKey((Object)entityplayer)) {
            boolean inPortal;
            boolean bl = inPortal = entityplayer.worldObj.getBlock(MathHelper.floor_double((double)entityplayer.posX), MathHelper.floor_double((double)entityplayer.boundingBox.minY), MathHelper.floor_double((double)entityplayer.posZ)) == portalBlock;
            if (inPortal) {
                int i = (Integer)players.get((Object)entityplayer);
                players.put(entityplayer, ++i);
                if (i >= entityplayer.getMaxInPortalTime()) {
                    int dimension = 0;
                    if (entityplayer.dimension == 0) {
                        dimension = LOTRDimension.MIDDLE_EARTH.dimensionID;
                    } else if (entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) {
                        dimension = 0;
                    }
                    WorldServer newWorld = MinecraftServer.getServer().worldServerForDimension(dimension);
                    MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(entityplayer, dimension, portalBlock.getPortalTeleporter(newWorld));
                    players.remove((Object)entityplayer);
                }
            } else {
                players.remove((Object)entityplayer);
            }
        }
    }
}

