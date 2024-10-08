/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.storage.WorldInfo
 */
package lotr.common.world.map;

import com.google.common.math.IntMath;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRConfig;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionRank;
import lotr.common.network.LOTRPacketConquestGrid;
import lotr.common.network.LOTRPacketConquestNotification;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.genlayer.LOTRGenLayerWorld;
import lotr.common.world.map.LOTRConquestZone;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.WorldInfo;

public class LOTRConquestGrid {
    public static final float MIN_CONQUEST = 0.0f;
    public static final float MAX_CONQUEST_SET = 100000.0f;
    private static final int MAP_GRID_POW2 = 3;
    private static final int MAP_GRID_SCALE = IntMath.pow((int)2, (int)3);
    private static final LOTRConquestZone dummyZone = new LOTRConquestZone(-999, -999).setDummyZone();
    private static final Set<GridCoordPair> dirtyZones = new HashSet<GridCoordPair>();
    public static boolean needsLoad = true;
    private static Map<GridCoordPair, LOTRConquestZone> zoneMap = new HashMap<GridCoordPair, LOTRConquestZone>();
    private static Map<GridCoordPair, List<LOTRFaction>> cachedZoneFactions = new HashMap<GridCoordPair, List<LOTRFaction>>();

    private static int worldToGridX(int i) {
        int mapX = i >> 7;
        return (mapX += 810) >> 3;
    }

    private static int worldToGridZ(int k) {
        int mapZ = k >> 7;
        return (mapZ += 730) >> 3;
    }

    private static int gridToMapCoord(int i) {
        return i << 3;
    }

    public static LOTRConquestZone getZoneByWorldCoords(int i, int k) {
        int x = LOTRConquestGrid.worldToGridX(i);
        int z = LOTRConquestGrid.worldToGridZ(k);
        return LOTRConquestGrid.getZoneByGridCoords(x, z);
    }

    public static LOTRConquestZone getZoneByEntityCoords(Entity entity) {
        int i = MathHelper.floor_double((double)entity.posX);
        int k = MathHelper.floor_double((double)entity.posZ);
        return LOTRConquestGrid.getZoneByWorldCoords(i, k);
    }

    public static LOTRConquestZone getZoneByGridCoords(int i, int k) {
        if (i < 0 || i >= MathHelper.ceiling_float_int((float)((float)LOTRGenLayerWorld.imageWidth / (float)MAP_GRID_SCALE))) {
            return dummyZone;
        }
        if (k < 0 || k >= MathHelper.ceiling_float_int((float)((float)LOTRGenLayerWorld.imageHeight / (float)MAP_GRID_SCALE))) {
            return dummyZone;
        }
        GridCoordPair key = new GridCoordPair(i, k);
        LOTRConquestZone zone = zoneMap.get(key);
        if (zone == null) {
            File zoneDat = LOTRConquestGrid.getZoneDat(key);
            zone = LOTRConquestGrid.loadZoneFromFile(zoneDat);
            if (zone == null) {
                zone = new LOTRConquestZone(i, k);
            }
            zoneMap.put(key, zone);
        }
        return zone;
    }

    public static int[] getMinCoordsOnMap(LOTRConquestZone zone) {
        return new int[]{LOTRConquestGrid.gridToMapCoord(zone.gridX), LOTRConquestGrid.gridToMapCoord(zone.gridZ)};
    }

    public static int[] getMaxCoordsOnMap(LOTRConquestZone zone) {
        return new int[]{LOTRConquestGrid.gridToMapCoord(zone.gridX + 1), LOTRConquestGrid.gridToMapCoord(zone.gridZ + 1)};
    }

    public static boolean conquestEnabled(World world) {
        return LOTRConfig.enableConquest && world.getWorldInfo().getTerrainType() != LOTRMod.worldTypeMiddleEarthClassic;
    }

    public static float onConquestKill(EntityPlayer entityplayer, LOTRFaction pledgeFaction, LOTRFaction enemyFaction, float alignBonus) {
        World world = entityplayer.worldObj;
        if (!world.isRemote && LOTRConquestGrid.conquestEnabled(world) && LOTRLevelData.getData(entityplayer).getEnableConquestKills() && entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) {
            LOTRConquestZone centralZone = LOTRConquestGrid.getZoneByEntityCoords((Entity)entityplayer);
            float conqAmount = alignBonus * LOTRLevelData.getConquestRate();
            float conqGain = conqAmount * LOTRConquestGrid.getConquestGainRate(entityplayer);
            float conqCleanse = conqAmount * LOTRConquestGrid.getConquestGainRate(entityplayer);
            return LOTRConquestGrid.doRadialConquest(world, centralZone, entityplayer, pledgeFaction, enemyFaction, conqGain, conqCleanse);
        }
        return 0.0f;
    }

    private static float getConquestGainRate(EntityPlayer entityplayer) {
        int k;
        int i = MathHelper.floor_double((double)entityplayer.posX);
        BiomeGenBase bgb = entityplayer.worldObj.getBiomeGenForCoords(i, k = MathHelper.floor_double((double)entityplayer.posZ));
        if (bgb instanceof LOTRBiome) {
            LOTRBiome biome = (LOTRBiome)bgb;
            return biome.npcSpawnList.conquestGainRate;
        }
        return 1.0f;
    }

    public static float doRadialConquest(World world, LOTRConquestZone centralZone, EntityPlayer killingPlayer, LOTRFaction pledgeFaction, LOTRFaction enemyFaction, float conqGain, float conqCleanse) {
        if (!centralZone.isDummyZone) {
            int range = 3;
            float radius = 3.5f;
            float centralConqBonus = 0.0f;
            for (int i1 = -3; i1 <= 3; ++i1) {
                for (int k1 = -3; k1 <= 3; ++k1) {
                    int distSq = i1 * i1 + k1 * k1;
                    if (!((float)distSq <= 12.25f)) continue;
                    int zoneX = centralZone.gridX + i1;
                    int zoneZ = centralZone.gridZ + k1;
                    float dist = MathHelper.sqrt_float((float)distSq);
                    float frac = 1.0f - dist / 3.5f;
                    float conqGainHere = frac * conqGain;
                    float conqCleanseHere = frac * conqCleanse;
                    LOTRConquestZone zone = LOTRConquestGrid.getZoneByGridCoords(zoneX, zoneZ);
                    if (zone.isDummyZone) continue;
                    boolean doneEnemyCleansing = false;
                    if (enemyFaction != null) {
                        float f;
                        float enemyConq = zone.getConquestStrength(enemyFaction, world);
                        if (f > 0.0f) {
                            zone.addConquestStrength(enemyFaction, -conqCleanseHere, world);
                            float newEnemyConq = zone.getConquestStrength(enemyFaction, world);
                            if (zone == centralZone) {
                                centralConqBonus = newEnemyConq - enemyConq;
                            }
                            if (killingPlayer != null) {
                                LOTRConquestGrid.checkNotifyConquest(zone, killingPlayer, enemyFaction, newEnemyConq, enemyConq, true);
                            }
                            doneEnemyCleansing = true;
                        }
                    }
                    if (doneEnemyCleansing || pledgeFaction == null) continue;
                    float prevZoneConq = zone.getConquestStrength(pledgeFaction, world);
                    zone.addConquestStrength(pledgeFaction, conqGainHere, world);
                    float newZoneConq = zone.getConquestStrength(pledgeFaction, world);
                    if (zone == centralZone) {
                        centralConqBonus = newZoneConq - prevZoneConq;
                    }
                    if (killingPlayer == null) continue;
                    LOTRConquestGrid.checkNotifyConquest(zone, killingPlayer, pledgeFaction, newZoneConq, prevZoneConq, false);
                }
            }
            return centralConqBonus;
        }
        return 0.0f;
    }

    private static void checkNotifyConquest(LOTRConquestZone zone, EntityPlayer originPlayer, LOTRFaction faction, float newConq, float prevConq, boolean isCleansing) {
        float notifInterval = 50.0f;
        double notifRange = 200.0;
        if (MathHelper.floor_double((double)(newConq / 50.0f)) != MathHelper.floor_double((double)(prevConq / 50.0f)) || newConq == 0.0f && prevConq != newConq) {
            World world = originPlayer.worldObj;
            List playerEntities = world.playerEntities;
            for (Object obj : playerEntities) {
                LOTRFaction pledgeFac;
                boolean playerApplicable;
                EntityPlayerMP player = (EntityPlayerMP)obj;
                LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)player);
                if (!(player.getDistanceSqToEntity((Entity)originPlayer) <= 40000.0) || LOTRConquestGrid.getZoneByEntityCoords((Entity)player) != zone) continue;
                boolean bl = isCleansing ? (pledgeFac = pd.getPledgeFaction()) != null && pledgeFac.isBadRelation(faction) : (playerApplicable = pd.isPledgedTo(faction));
                if (!playerApplicable) continue;
                LOTRPacketConquestNotification pkt = new LOTRPacketConquestNotification(faction, newConq, isCleansing);
                LOTRPacketHandler.networkWrapper.sendTo((IMessage)pkt, player);
            }
        }
    }

    public static void updateZones(World world) {
        MinecraftServer srv;
        if (LOTRConquestGrid.conquestEnabled(world) && (srv = MinecraftServer.getServer()) != null) {
            int tick = srv.getTickCounter();
            int interval = 6000;
            for (LOTRConquestZone zone : zoneMap.values()) {
                if (zone.isEmpty() || IntMath.mod((int)tick, (int)interval) != IntMath.mod((int)zone.hashCode(), (int)interval)) continue;
                zone.checkForEmptiness(world);
            }
        }
    }

    public static ConquestViewableQuery canPlayerViewConquest(EntityPlayer entityplayer, LOTRFaction fac) {
        LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
        LOTRFaction pledged = pd.getPledgeFaction();
        if (pledged != null) {
            if (fac == pledged) {
                return ConquestViewableQuery.canView();
            }
            float align = pd.getAlignment(pledged);
            LOTRFactionRank pledgeRank = pledged.getPledgeRank();
            if (fac.isAlly(pledged) || fac.isBadRelation(pledged)) {
                return ConquestViewableQuery.canView();
            }
            LOTRFactionRank higherRank = pledged.getRankNAbove(pledgeRank, 2);
            if (align >= higherRank.alignment) {
                return ConquestViewableQuery.canView();
            }
            return new ConquestViewableQuery(ConquestViewable.NEED_RANK, higherRank);
        }
        return new ConquestViewableQuery(ConquestViewable.UNPLEDGED, null);
    }

    public static void sendConquestGridTo(EntityPlayerMP entityplayer, LOTRFaction fac) {
        LOTRPacketConquestGrid pkt = new LOTRPacketConquestGrid(fac, zoneMap.values(), entityplayer.worldObj);
        LOTRPacketHandler.networkWrapper.sendTo((IMessage)pkt, entityplayer);
        LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
        if (fac == pd.getPledgeFaction()) {
            pd.addAchievement(LOTRAchievement.factionConquest);
        }
    }

    public static ConquestEffective getConquestEffectIn(World world, LOTRConquestZone zone, LOTRFaction theFaction) {
        List<LOTRFaction> cachedFacs;
        GridCoordPair gridCoords;
        if (!LOTRGenLayerWorld.loadedBiomeImage()) {
            new LOTRGenLayerWorld();
        }
        if ((cachedFacs = cachedZoneFactions.get(gridCoords = GridCoordPair.forZone(zone))) == null) {
            Object biome;
            cachedFacs = new ArrayList<LOTRFaction>();
            ArrayList includedBiomes = new ArrayList();
            int[] mapMin = LOTRConquestGrid.getMinCoordsOnMap(zone);
            int[] mapMax = LOTRConquestGrid.getMaxCoordsOnMap(zone);
            int mapXMin = mapMin[0];
            int mapXMax = mapMax[0];
            int mapZMin = mapMin[1];
            int mapZMax = mapMax[1];
            for (int i = mapXMin; i < mapXMax; ++i) {
                for (int k = mapZMin; k < mapZMax; ++k) {
                    biome = LOTRGenLayerWorld.getBiomeOrOcean(i, k);
                    if (includedBiomes.contains(biome)) continue;
                    includedBiomes.add(biome);
                }
            }
            for (LOTRFaction fac : LOTRFaction.getPlayableAlignmentFactions()) {
                biome = includedBiomes.iterator();
                while (biome.hasNext()) {
                    LOTRBiome biome2 = (LOTRBiome)((Object)biome.next());
                    if (!biome2.npcSpawnList.isFactionPresent(world, fac)) continue;
                    cachedFacs.add(fac);
                }
            }
            cachedZoneFactions.put(gridCoords, cachedFacs);
        }
        if (cachedFacs.contains((Object)theFaction)) {
            return ConquestEffective.EFFECTIVE;
        }
        for (LOTRFaction allyFac : theFaction.getConquestBoostRelations()) {
            if (!cachedFacs.contains((Object)allyFac)) continue;
            return ConquestEffective.ALLY_BOOST;
        }
        return ConquestEffective.NO_EFFECT;
    }

    private static File getConquestDir() {
        File dir = new File(LOTRLevelData.getOrCreateLOTRDir(), "conquest_zones");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private static File getZoneDat(LOTRConquestZone zone) {
        GridCoordPair key = GridCoordPair.forZone(zone);
        return LOTRConquestGrid.getZoneDat(key);
    }

    private static File getZoneDat(GridCoordPair key) {
        return new File(LOTRConquestGrid.getConquestDir(), key.gridX + "." + key.gridZ + ".dat");
    }

    public static void markZoneDirty(LOTRConquestZone zone) {
        GridCoordPair key = GridCoordPair.forZone(zone);
        if (zoneMap.containsKey(key)) {
            dirtyZones.add(key);
        }
    }

    public static boolean anyChangedZones() {
        return !dirtyZones.isEmpty();
    }

    public static void saveChangedZones() {
        try {
            HashSet<GridCoordPair> removes = new HashSet<GridCoordPair>();
            for (GridCoordPair key : dirtyZones) {
                LOTRConquestZone zone = zoneMap.get(key);
                if (zone == null) continue;
                LOTRConquestGrid.saveZoneToFile(zone);
                if (!zone.isEmpty()) continue;
                removes.add(key);
            }
            dirtyZones.clear();
            for (GridCoordPair key : removes) {
                zoneMap.remove(key);
            }
        }
        catch (Exception e) {
            FMLLog.severe((String)"Error saving LOTR conquest zones", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public static void loadAllZones() {
        try {
            zoneMap.clear();
            dirtyZones.clear();
            File dir = LOTRConquestGrid.getConquestDir();
            if (dir.exists()) {
                File[] subfiles;
                for (File zoneDat : subfiles = dir.listFiles()) {
                    LOTRConquestZone zone;
                    if (zoneDat.isDirectory() || !zoneDat.getName().endsWith(".dat") || (zone = LOTRConquestGrid.loadZoneFromFile(zoneDat)) == null) continue;
                    GridCoordPair key = GridCoordPair.forZone(zone);
                    zoneMap.put(key, zone);
                }
            }
            needsLoad = false;
            FMLLog.info((String)"LOTR: Loaded %s conquest zones", (Object[])new Object[]{zoneMap.size()});
        }
        catch (Exception e) {
            FMLLog.severe((String)"Error loading LOTR conquest zones", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    private static LOTRConquestZone loadZoneFromFile(File zoneDat) {
        try {
            NBTTagCompound nbt = LOTRLevelData.loadNBTFromFile(zoneDat);
            if (nbt.hasNoTags()) {
                return null;
            }
            LOTRConquestZone zone = LOTRConquestZone.readFromNBT(nbt);
            if (zone.isEmpty()) {
                return null;
            }
            return zone;
        }
        catch (Exception e) {
            FMLLog.severe((String)"Error loading LOTR conquest zone from %s", (Object[])new Object[]{zoneDat.getName()});
            e.printStackTrace();
            return null;
        }
    }

    public static void saveZoneToFile(LOTRConquestZone zone) {
        File zoneDat = LOTRConquestGrid.getZoneDat(zone);
        try {
            if (zone.isEmpty()) {
                zoneDat.delete();
            } else {
                NBTTagCompound nbt = new NBTTagCompound();
                zone.writeToNBT(nbt);
                LOTRLevelData.saveNBTToFile(zoneDat, nbt);
            }
        }
        catch (Exception e) {
            FMLLog.severe((String)"Error saving LOTR conquest zone to %s", (Object[])new Object[]{zoneDat.getName()});
            e.printStackTrace();
        }
    }

    public static class ConquestViewableQuery {
        public final ConquestViewable result;
        public final LOTRFactionRank needRank;

        public ConquestViewableQuery(ConquestViewable res, LOTRFactionRank rank) {
            this.result = res;
            this.needRank = rank;
        }

        public static ConquestViewableQuery canView() {
            return new ConquestViewableQuery(ConquestViewable.CAN_VIEW, null);
        }
    }

    public static class GridCoordPair {
        public final int gridX;
        public final int gridZ;

        public GridCoordPair(int i, int k) {
            this.gridX = i;
            this.gridZ = k;
        }

        public static GridCoordPair forZone(LOTRConquestZone zone) {
            return new GridCoordPair(zone.gridX, zone.gridZ);
        }

        public int hashCode() {
            int i = 1664525 * this.gridX + 1013904223;
            int j = 1664525 * (this.gridZ ^ 0xDEADBEEF) + 1013904223;
            return i ^ j;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GridCoordPair)) {
                return false;
            }
            GridCoordPair otherPair = (GridCoordPair)other;
            return this.gridX == otherPair.gridX && this.gridZ == otherPair.gridZ;
        }
    }

    public static enum ConquestViewable {
        UNPLEDGED,
        CAN_VIEW,
        NEED_RANK;

    }

    public static enum ConquestEffective {
        EFFECTIVE,
        ALLY_BOOST,
        NO_EFFECT;

    }

}

