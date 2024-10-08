/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package lotr.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotr.common.LOTRAchievement;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.LOTRWorldProvider;
import lotr.common.world.LOTRWorldProviderMiddleEarth;
import lotr.common.world.LOTRWorldProviderUtumno;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public enum LOTRDimension {
    MIDDLE_EARTH("MiddleEarth", 100, LOTRWorldProviderMiddleEarth.class, true, 100, EnumSet.of(DimensionRegion.WEST, DimensionRegion.EAST, DimensionRegion.SOUTH, DimensionRegion.NORTH)),
    UTUMNO("Utumno", 101, LOTRWorldProviderUtumno.class, false, 500, EnumSet.of(DimensionRegion.REG_UTUMNO));

    public String dimensionName;
    private int defaultID;
    public int dimensionID;
    private Class providerClass;
    private boolean loadSpawn;
    public LOTRBiome[] biomeList = new LOTRBiome[256];
    public Map<Integer, Integer> colorsToBiomeIDs = new HashMap<Integer, Integer>();
    public List<LOTRBiome> majorBiomes = new ArrayList<LOTRBiome>();
    public List<LOTRAchievement.Category> achievementCategories = new ArrayList<LOTRAchievement.Category>();
    public List<LOTRAchievement> allAchievements = new ArrayList<LOTRAchievement>();
    public List<LOTRFaction> factionList = new ArrayList<LOTRFaction>();
    public List<DimensionRegion> dimensionRegions = new ArrayList<DimensionRegion>();
    public int spawnCap;

    private LOTRDimension(String s, int i, Class c, boolean flag, int spawns, EnumSet<DimensionRegion> regions) {
        this.dimensionName = s;
        this.defaultID = i;
        this.providerClass = c;
        this.loadSpawn = flag;
        this.spawnCap = spawns;
        this.dimensionRegions.addAll(regions);
        for (DimensionRegion r : this.dimensionRegions) {
            r.setDimension(this);
        }
    }

    public String getUntranslatedDimensionName() {
        return "lotr.dimension." + this.dimensionName;
    }

    public String getDimensionName() {
        return StatCollector.translateToLocal((String)this.getUntranslatedDimensionName());
    }

    public static void configureDimensions(Configuration config, String category) {
        for (LOTRDimension dim : LOTRDimension.values()) {
            dim.dimensionID = config.get(category, "Dimension ID: " + dim.dimensionName, dim.defaultID).getInt();
        }
    }

    public static void registerDimensions() {
        for (LOTRDimension dim : LOTRDimension.values()) {
            DimensionManager.registerProviderType((int)dim.dimensionID, (Class)dim.providerClass, (boolean)dim.loadSpawn);
            DimensionManager.registerDimension((int)dim.dimensionID, (int)dim.dimensionID);
        }
    }

    public static LOTRDimension getCurrentDimension(World world) {
        WorldProvider provider;
        if (world != null && (provider = world.provider) instanceof LOTRWorldProvider) {
            return ((LOTRWorldProvider)provider).getLOTRDimension();
        }
        return null;
    }

    public static LOTRDimension getCurrentDimensionWithFallback(World world) {
        LOTRDimension dim = LOTRDimension.getCurrentDimension(world);
        if (dim == null) {
            return MIDDLE_EARTH;
        }
        return dim;
    }

    public static LOTRDimension forName(String s) {
        for (LOTRDimension dim : LOTRDimension.values()) {
            if (!dim.dimensionName.equals(s)) continue;
            return dim;
        }
        return null;
    }

    public static enum DimensionRegion {
        WEST("west"),
        EAST("east"),
        SOUTH("south"),
        NORTH("north"),
        REG_UTUMNO("utumno");

        private String regionName;
        private LOTRDimension dimension;
        public List<LOTRFaction> factionList = new ArrayList<LOTRFaction>();

        private DimensionRegion(String s) {
            this.regionName = s;
        }

        public void setDimension(LOTRDimension dim) {
            this.dimension = dim;
        }

        public LOTRDimension getDimension() {
            return this.dimension;
        }

        public String codeName() {
            return this.regionName;
        }

        public String getRegionName() {
            return StatCollector.translateToLocal((String)("lotr.dimension." + this.dimension.dimensionName + "." + this.codeName()));
        }

        public static DimensionRegion forName(String regionName) {
            for (DimensionRegion r : DimensionRegion.values()) {
                if (!r.codeName().equals(regionName)) continue;
                return r;
            }
            return null;
        }

        public static DimensionRegion forID(int ID) {
            for (DimensionRegion r : DimensionRegion.values()) {
                if (r.ordinal() != ID) continue;
                return r;
            }
            return null;
        }
    }

}

