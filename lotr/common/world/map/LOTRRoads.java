/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.util.StatCollector
 *  org.apache.commons.lang3.tuple.Pair
 */
package lotr.common.world.map;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotr.common.world.map.LOTRWaypoint;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.tuple.Pair;

public class LOTRRoads {
    public static List<LOTRRoads> allRoads = new ArrayList<LOTRRoads>();
    private static RoadPointDatabase roadPointDatabase = new RoadPointDatabase();
    public RoadPoint[] roadPoints;
    public List<RoadPoint> endpoints = new ArrayList<RoadPoint>();
    public final String roadName;

    private LOTRRoads(String name, RoadPoint ... ends) {
        this.roadName = name;
        for (RoadPoint e : ends) {
            this.endpoints.add(e);
        }
    }

    public String getDisplayName() {
        return StatCollector.translateToLocal((String)("lotr.road." + this.roadName));
    }

    private static void registerRoad(String name, Object ... waypoints) {
        ArrayList<RoadPoint> points = new ArrayList<RoadPoint>();
        for (Object obj : waypoints) {
            int[] coords;
            if (obj instanceof LOTRWaypoint) {
                LOTRWaypoint wp = (LOTRWaypoint)obj;
                points.add(new RoadPoint(wp.getXCoord(), wp.getZCoord(), true));
                continue;
            }
            if (obj instanceof int[]) {
                coords = (int[])obj;
                if (coords.length == 2) {
                    points.add(new RoadPoint(LOTRWaypoint.mapToWorldX(coords[0]), LOTRWaypoint.mapToWorldZ(coords[1]), false));
                    continue;
                }
                throw new IllegalArgumentException("Coords length must be 2!");
            }
            if (obj instanceof double[]) {
                coords = (double[])obj;
                if (coords.length == 2) {
                    points.add(new RoadPoint(LOTRWaypoint.mapToWorldX(coords[0]), LOTRWaypoint.mapToWorldZ(coords[1]), false));
                    continue;
                }
                throw new IllegalArgumentException("Coords length must be 2!");
            }
            throw new IllegalArgumentException("Wrong road parameter!");
        }
        RoadPoint[] array = points.toArray(new RoadPoint[0]);
        LOTRRoads[] roads = BezierCurves.getSplines(name, array);
        allRoads.addAll(Arrays.asList(roads));
    }

    public static void createRoads() {
        FMLLog.info((String)"LOTRRoads: Creating roads (reticulating splines)", (Object[])new Object[0]);
        long time = System.nanoTime();
        allRoads.clear();
        roadPointDatabase = new RoadPointDatabase();
        LOTRRoads.registerRoad("EredLuin", LOTRWaypoint.NOGROD, LOTRWaypoint.BELEGOST);
        LOTRRoads.registerRoad("NogrodForlond", LOTRWaypoint.NOGROD, LOTRWaypoint.FORLOND);
        LOTRRoads.registerRoad("NogrodMithlond", LOTRWaypoint.NOGROD, new int[]{654, 650}, LOTRWaypoint.MITHLOND_NORTH);
        LOTRRoads.registerRoad("Mithlond", LOTRWaypoint.HARLOND, new int[]{658, 755}, LOTRWaypoint.MITHLOND_SOUTH, new int[]{690, 711}, new int[]{681, 705}, LOTRWaypoint.MITHLOND_NORTH, new int[]{644, 733}, new int[]{603, 733}, new int[]{554, 715}, LOTRWaypoint.FORLOND);
        LOTRRoads.registerRoad("WestEast", LOTRWaypoint.MITHLOND_SOUTH, LOTRWaypoint.TOWER_HILLS, LOTRWaypoint.GREENHOLM, LOTRWaypoint.MICHEL_DELVING, LOTRWaypoint.WAYMEET, LOTRWaypoint.BYWATER, LOTRWaypoint.FROGMORTON, LOTRWaypoint.WHITFURROWS, LOTRWaypoint.BRANDYWINE_BRIDGE, new int[]{870, 718}, new int[]{902, 729}, LOTRWaypoint.BREE);
        LOTRRoads.registerRoad("WestEast", LOTRWaypoint.BREE, new double[]{LOTRWaypoint.BREE.getX() + 0.5, LOTRWaypoint.BREE.getY()});
        LOTRRoads.registerRoad("WestEast", new double[]{LOTRWaypoint.BREE.getX() + 2.0, LOTRWaypoint.BREE.getY() + 1.5}, new double[]{LOTRWaypoint.STADDLE.getX(), LOTRWaypoint.STADDLE.getY() + 5.0}, LOTRWaypoint.FORSAKEN_INN, new double[]{LOTRWaypoint.WEATHERTOP.getX(), LOTRWaypoint.WEATHERTOP.getY() + 2.0}, LOTRWaypoint.LAST_BRIDGE, new int[]{1132, 723}, new int[]{1178, 704}, LOTRWaypoint.HIGH_PASS, LOTRWaypoint.OLD_FORD, LOTRWaypoint.RIVER_GATE, LOTRWaypoint.DALE_CROSSROADS, LOTRWaypoint.REDWATER_FORD, new int[]{1785, 775}, LOTRWaypoint.RHUN_NORTH_FORD, LOTRWaypoint.RHUN_NORTHEAST, LOTRWaypoint.RHUN_ROAD_WAY, LOTRWaypoint.BARAZ_DUM);
        LOTRRoads.registerRoad("WestEast", new double[]{LOTRWaypoint.BREE.getX() - 0.375, LOTRWaypoint.BREE.getY() - 2.476}, new double[]{LOTRWaypoint.BREE.getX() + 2.0, LOTRWaypoint.BREE.getY() - 1.5});
        LOTRRoads.registerRoad("BywaterRoad", LOTRWaypoint.BYWATER, LOTRWaypoint.HOBBITON);
        LOTRRoads.registerRoad("Overhill", LOTRWaypoint.HOBBITON, LOTRWaypoint.OVERHILL);
        LOTRRoads.registerRoad("BucklandRoad", LOTRWaypoint.HAY_GATE, LOTRWaypoint.BUCKLEBURY, LOTRWaypoint.HAYSEND);
        LOTRRoads.registerRoad("Chetroad", new double[]{LOTRWaypoint.STADDLE.getX(), LOTRWaypoint.STADDLE.getY() + 5.0}, LOTRWaypoint.STADDLE, LOTRWaypoint.COMBE, LOTRWaypoint.ARCHET);
        LOTRRoads.registerRoad("Chetroad", LOTRWaypoint.STADDLE, new double[]{LOTRWaypoint.STADDLE.getX() - 0.5, LOTRWaypoint.STADDLE.getY()});
        LOTRRoads.registerRoad("Chetroad", LOTRWaypoint.COMBE, new double[]{LOTRWaypoint.COMBE.getX() + 0.5, LOTRWaypoint.COMBE.getY()});
        LOTRRoads.registerRoad("Chetroad", LOTRWaypoint.ARCHET, new double[]{LOTRWaypoint.ARCHET.getX(), LOTRWaypoint.ARCHET.getY() - 0.5});
        LOTRRoads.registerRoad("ElfPath", LOTRWaypoint.FOREST_GATE, LOTRWaypoint.ENCHANTED_RIVER, LOTRWaypoint.THRANDUIL_HALLS);
        LOTRRoads.registerRoad("EreborRoad", LOTRWaypoint.LONG_LAKE, LOTRWaypoint.DALE_CITY, LOTRWaypoint.EREBOR);
        LOTRRoads.registerRoad("DalePortRoad", LOTRWaypoint.DALE_CITY, LOTRWaypoint.DALE_CROSSROADS, LOTRWaypoint.DALE_PORT);
        LOTRRoads.registerRoad("DaleSouthRoad", LOTRWaypoint.EAST_RHOVANION_ROAD, LOTRWaypoint.OLD_RHOVANION, LOTRWaypoint.RUNNING_FORD, LOTRWaypoint.DALE_CROSSROADS, LOTRWaypoint.WEST_PEAK);
        LOTRRoads.registerRoad("IronHills", LOTRWaypoint.WEST_PEAK, new int[]{1652, 621}, LOTRWaypoint.EAST_PEAK);
        LOTRRoads.registerRoad("DorwinionSouthRoad", LOTRWaypoint.DALE_PORT, LOTRWaypoint.DORWINION_CROSSROADS, LOTRWaypoint.DORWINION_COURT, LOTRWaypoint.DORWINION_FORD);
        LOTRRoads.registerRoad("DorwinionEastRoad", LOTRWaypoint.OLD_RHOVANION, LOTRWaypoint.DORWINION_CROSSROADS, LOTRWaypoint.DORWINION_PORT);
        LOTRRoads.registerRoad("RhunRoad", LOTRWaypoint.DORWINION_FORD, LOTRWaypoint.BORDER_TOWN, LOTRWaypoint.RHUN_SEA_CITY, LOTRWaypoint.RHUN_CAPITAL, new int[]{1888, 958}, LOTRWaypoint.RHUN_NORTH_CITY, LOTRWaypoint.BAZYLAN, LOTRWaypoint.RHUN_NORTHEAST);
        LOTRRoads.registerRoad("RhunEastRoad", LOTRWaypoint.RHUN_NORTH_CITY, LOTRWaypoint.RHUN_EAST_TOWN, LOTRWaypoint.RHUN_EAST_CITY);
        LOTRRoads.registerRoad("Nobottom", LOTRWaypoint.TIGHFIELD, LOTRWaypoint.LITTLE_DELVING, LOTRWaypoint.NOBOTTLE, LOTRWaypoint.NEEDLEHOLE);
        LOTRRoads.registerRoad("Oatbarton", LOTRWaypoint.OATBARTON, LOTRWaypoint.FROGMORTON);
        LOTRRoads.registerRoad("Stock", LOTRWaypoint.TUCKBOROUGH, LOTRWaypoint.STOCK);
        LOTRRoads.registerRoad("Deephallow", LOTRWaypoint.SCARY, LOTRWaypoint.WHITFURROWS, LOTRWaypoint.STOCK, LOTRWaypoint.DEEPHALLOW);
        LOTRRoads.registerRoad("Willowbottom", LOTRWaypoint.WILLOWBOTTOM, LOTRWaypoint.DEEPHALLOW);
        LOTRRoads.registerRoad("ArnorRoad", LOTRWaypoint.ANNUMINAS, LOTRWaypoint.FORNOST);
        LOTRRoads.registerRoad("Greenway", LOTRWaypoint.FORNOST, LOTRWaypoint.BREE, LOTRWaypoint.GREENWAY_CROSSROADS);
        LOTRRoads.registerRoad("ElvenWay", LOTRWaypoint.WEST_GATE, new int[]{1133, 867}, new int[]{1124, 868}, LOTRWaypoint.OST_IN_EDHIL, new int[]{1073, 864}, LOTRWaypoint.OLD_ELF_WAY, new int[]{1002, 849}, new int[]{992, 860}, LOTRWaypoint.THARBAD, new int[]{959, 889}, new int[]{926, 913}, new int[]{902, 942}, LOTRWaypoint.LOND_DAER);
        LOTRRoads.registerRoad("BruinenPath", LOTRWaypoint.FORD_BRUINEN, LOTRWaypoint.RIVENDELL);
        LOTRRoads.registerRoad("NimrodelRoad", LOTRWaypoint.DIMRILL_DALE, LOTRWaypoint.NIMRODEL);
        LOTRRoads.registerRoad("AnduinRoad", LOTRWaypoint.MORANNON, new int[]{1428, 1066}, LOTRWaypoint.EAST_RHOVANION_ROAD, LOTRWaypoint.ANDUIN_CROSSROADS, new int[]{1325, 820}, new int[]{1318, 735}, LOTRWaypoint.FOREST_GATE);
        LOTRRoads.registerRoad("DolGuldurRoad", LOTRWaypoint.ANDUIN_CROSSROADS, LOTRWaypoint.DOL_GULDUR);
        LOTRRoads.registerRoad("Framsburg", LOTRWaypoint.FOREST_GATE, new int[]{1278, 605}, LOTRWaypoint.FRAMSBURG, new int[]{1260, 565}, LOTRWaypoint.DAINS_HALLS);
        LOTRRoads.registerRoad("NorthSouth", LOTRWaypoint.LITTLE_DELVING, LOTRWaypoint.WAYMEET, LOTRWaypoint.LONGBOTTOM, LOTRWaypoint.SARN_FORD, LOTRWaypoint.GREENWAY_CROSSROADS, LOTRWaypoint.THARBAD, LOTRWaypoint.ENEDWAITH_ROAD, LOTRWaypoint.FORDS_OF_ISEN, LOTRWaypoint.HELMS_CROSSROADS, LOTRWaypoint.GRIMSLADE, LOTRWaypoint.EDORAS, LOTRWaypoint.ALDBURG, LOTRWaypoint.MERING_STREAM, LOTRWaypoint.AMON_DIN);
        LOTRRoads.registerRoad("TirithRoad", LOTRWaypoint.AMON_DIN, LOTRWaypoint.MINAS_TIRITH);
        LOTRRoads.registerRoad("OsgiliathRoad", LOTRWaypoint.MINAS_TIRITH, LOTRWaypoint.OSGILIATH_WEST);
        LOTRRoads.registerRoad("OsgiliathCrossing", LOTRWaypoint.OSGILIATH_WEST, LOTRWaypoint.OSGILIATH_EAST);
        LOTRRoads.registerRoad("OsgiliathMorgulRoad", LOTRWaypoint.OSGILIATH_EAST, LOTRWaypoint.CROSSROADS_ITHILIEN, LOTRWaypoint.MINAS_MORGUL);
        LOTRRoads.registerRoad("GondorSouthRoad", LOTRWaypoint.MINAS_TIRITH, LOTRWaypoint.CROSSINGS_ERUI, new int[]{1408, 1291}, LOTRWaypoint.PELARGIR, LOTRWaypoint.LINHIR, new int[]{1266, 1301}, LOTRWaypoint.ETHRING, LOTRWaypoint.CALEMBEL, LOTRWaypoint.TARLANG, LOTRWaypoint.ERECH);
        LOTRRoads.registerRoad("IsengardRoad", LOTRWaypoint.FORDS_OF_ISEN, LOTRWaypoint.ISENGARD);
        LOTRRoads.registerRoad("HelmRoad", LOTRWaypoint.HELMS_CROSSROADS, LOTRWaypoint.HELMS_DEEP);
        LOTRRoads.registerRoad("WoldRoad", LOTRWaypoint.EDORAS, LOTRWaypoint.ENTWADE, new int[]{1260, 1060}, LOTRWaypoint.WOLD);
        LOTRRoads.registerRoad("DolAmroth", LOTRWaypoint.LOMOST, new int[]{1008, 1305}, LOTRWaypoint.ACROST, new int[]{1025, 1295}, new int[]{1050, 1290}, new int[]{1070, 1285}, LOTRWaypoint.SERELOND, new int[]{1090, 1290}, new int[]{1106, 1290}, LOTRWaypoint.TANTTAR, new int[]{1118, 1285}, LOTRWaypoint.RONDALPH, new int[]{1160, 1288}, new int[]{1180, 1286}, LOTRWaypoint.EDHELLOND, new int[]{1187, 1302}, new int[]{1187, 1316}, new int[]{1180, 1320}, new int[]{1175, 1330}, new int[]{1165, 1330}, LOTRWaypoint.DOL_AMROTH);
        LOTRRoads.registerRoad("Pelargir", LOTRWaypoint.PELARGIR, new int[]{1394, 1352});
        LOTRRoads.registerRoad("Poros", new int[]{1397, 1355}, LOTRWaypoint.CROSSINGS_OF_POROS);
        LOTRRoads.registerRoad("CairAndros", LOTRWaypoint.AMON_DIN, LOTRWaypoint.CAIR_ANDROS, LOTRWaypoint.NORTH_ITHILIEN);
        LOTRRoads.registerRoad("SauronRoad", LOTRWaypoint.MINAS_MORGUL, LOTRWaypoint.MOUNT_DOOM, LOTRWaypoint.BARAD_DUR, LOTRWaypoint.SEREGOST, new int[]{1742, 1209}, new int[]{1809, 1172}, LOTRWaypoint.EASTERN_GUARD, LOTRWaypoint.MORDOR_FORD, LOTRWaypoint.RHUN_SOUTH_PASS, new int[]{1875, 1003}, new int[]{1867, 996}, LOTRWaypoint.RHUN_CAPITAL);
        LOTRRoads.registerRoad("MorannonRoad", LOTRWaypoint.MORANNON, LOTRWaypoint.UDUN);
        LOTRRoads.registerRoad("MorannonRhunRoad", LOTRWaypoint.MORANNON, new int[]{1520, 1130}, new int[]{1658, 1140}, new int[]{1780, 1115}, LOTRWaypoint.MORDOR_FORD, LOTRWaypoint.RHUN_SOUTHEAST, LOTRWaypoint.KHAND_NORTH_ROAD, LOTRWaypoint.KHAND_FORD, LOTRWaypoint.HARNEN_BLACK_TOWN, LOTRWaypoint.CROSSINGS_OF_LITHNEN, LOTRWaypoint.HARNEN_ROAD_TOWN, LOTRWaypoint.HARNEN_RIVER_TOWN, LOTRWaypoint.HARNEN_SEA_TOWN, LOTRWaypoint.COAST_FORTRESS, LOTRWaypoint.GATE_FUINUR, LOTRWaypoint.UMBAR_CITY, LOTRWaypoint.GATE_HERUMOR);
        LOTRRoads.registerRoad("GorgorothRoad", LOTRWaypoint.UDUN, LOTRWaypoint.CARACH_ANGREN, LOTRWaypoint.BARAD_DUR, LOTRWaypoint.THAURBAND);
        LOTRRoads.registerRoad("HaradRoad", LOTRWaypoint.MORANNON, LOTRWaypoint.NORTH_ITHILIEN, LOTRWaypoint.CROSSROADS_ITHILIEN, LOTRWaypoint.CROSSINGS_OF_POROS, new int[]{1429, 1394}, new int[]{1408, 1432}, new int[]{1428, 1470}, new int[]{1435, 1526}, LOTRWaypoint.CROSSINGS_OF_HARAD, LOTRWaypoint.HARNEN_ROAD_TOWN, LOTRWaypoint.DESERT_TOWN);
        LOTRRoads.registerRoad("UmbarRoad", LOTRWaypoint.UMBAR_CITY, LOTRWaypoint.UMBAR_GATE, LOTRWaypoint.AIN_AL_HARAD, LOTRWaypoint.GARDENS_BERUTHIEL, LOTRWaypoint.FERTILE_VALLEY, LOTRWaypoint.SOUTH_DESERT_TOWN);
        LOTRRoads.registerRoad("GulfRoad", LOTRWaypoint.TOWN_BONES, new int[]{1794, 2110}, LOTRWaypoint.GULF_FORD, LOTRWaypoint.GULF_TRADE_TOWN, LOTRWaypoint.GULF_CITY, LOTRWaypoint.GULF_NORTH_TOWN, new int[]{1702, 1940}, LOTRWaypoint.GULF_OF_HARAD, new int[]{1775, 2002}, LOTRWaypoint.GULF_EAST_PORT);
        LOTRRoads.registerRoad("JungleNorthRoad", LOTRWaypoint.JUNGLE_CITY_TRADE, LOTRWaypoint.JUNGLE_CITY_OLD, LOTRWaypoint.JUNGLE_CITY_NORTH);
        LOTRRoads.registerRoad("JungleMangroveRoad", LOTRWaypoint.JUNGLE_CITY_NORTH, LOTRWaypoint.JUNGLE_CITY_EAST, LOTRWaypoint.HARADUIN_MOUTH);
        LOTRRoads.registerRoad("JungleDeepRoad", LOTRWaypoint.JUNGLE_CITY_NORTH, LOTRWaypoint.JUNGLE_CITY_CAPITAL, LOTRWaypoint.JUNGLE_CITY_CAVES, LOTRWaypoint.JUNGLE_CITY_DEEP);
        LOTRRoads.registerRoad("DwarvenRoad", LOTRWaypoint.RED_ROAD1, LOTRWaypoint.RED_ROAD1, new int[]{2652, 1361}, new int[]{2591, 1306});
        LOTRRoads.registerRoad("HumanRhunRoad", LOTRWaypoint.BALCARAS, new int[]{2046, 1009}, LOTRWaypoint.RHUN_EAST_CITY);
        LOTRRoads.registerRoad("OrcRhunRoad", LOTRWaypoint.RHUN_SOUTHEAST, new int[]{2068, 1124}, LOTRWaypoint.BALCARAS);
        LOTRRoads.registerRoad("JungleWestEastRoad", LOTRWaypoint.JUNGLE_CITY_OLD, LOTRWaypoint.JUNGLE_CITY_STONE, LOTRWaypoint.JUNGLE_CITY_CAPITAL, LOTRWaypoint.JUNGLE_LAKES, LOTRWaypoint.JUNGLE_CITY_WATCH);
        LOTRRoads.registerRoad("JungleLakeRoad", LOTRWaypoint.JUNGLE_LAKES, LOTRWaypoint.JUNGLE_CITY_EAST, LOTRWaypoint.HARADUIN_BRIDGE, LOTRWaypoint.OLD_JUNGLE_RUIN);
        LOTRRoads.registerRoad("DwarvenRoad", LOTRWaypoint.BALCARAS, new int[]{2340, 1070}, LOTRWaypoint.BA_BAD, LOTRWaypoint.NARGUN_KHAZAD);
        LOTRRoads.registerRoad("CarnDumRoad", LOTRWaypoint.CARN_DUM, LOTRWaypoint.ANGSUL, LOTRWaypoint.SHEDUN, LOTRWaypoint.ELDANAN, LOTRWaypoint.MORVA_TARTH, LOTRWaypoint.KAMET_BRIN, new int[]{1100, 680}, new int[]{1095, 706}, LOTRWaypoint.LAST_BRIDGE);
        LOTRRoads.registerRoad("GundabadRoad", LOTRWaypoint.SHEDUN, new int[]{1106, 573}, new int[]{1130, 589}, new int[]{1160, 587}, LOTRWaypoint.LITASH, LOTRWaypoint.MOUNT_GUNDABAD);
        LOTRRoads.registerRoad("NaragGund", LOTRWaypoint.BALCARAS, new int[]{2364, 1172}, LOTRWaypoint.RED_SOUTH);
        LOTRRoads.registerRoad("KheledDum", LOTRWaypoint.RED_NORTH, new int[]{2270, 711}, LOTRWaypoint.RHUN_ROAD_WAY, LOTRWaypoint.BALCARAS);
        LOTRRoads.registerRoad("EastWind", LOTRWaypoint.KHAND_NORTH_ROAD, new int[]{1970, 1334}, new int[]{2043, 1357}, new int[]{2118, 1410}, new int[]{2216, 1518}, new int[]{2318, 1532}, LOTRWaypoint.WIND_POINT);
        LOTRRoads.registerRoad("KheledDum1", LOTRWaypoint.RED_NORTH, new int[]{2351, 540}, new int[]{2351, 530}, LOTRWaypoint.NEST, new int[]{2306, 508}, new int[]{2290, 503}, new int[]{2278, 494}, new int[]{2267, 494}, LOTRWaypoint.SNOW_PICK2);
        LOTRRoads.registerRoad("NorthPath", LOTRWaypoint.FORNOST, new int[]{890, 573}, LOTRWaypoint.SOUTH_FOROCHEL);
        LOTRRoads.registerRoad("NorthPath", LOTRWaypoint.SOUTH_FOROCHEL, new int[]{937, 512}, LOTRWaypoint.CARN_DUM);
        LOTRRoads.registerRoad("RunLongPath", LOTRWaypoint.BALCARAS, new int[]{2244, 1145}, new int[]{2297, 1311}, new int[]{2356, 1406}, LOTRWaypoint.WIND_POINT);
        long newTime = System.nanoTime();
        int roads = allRoads.size();
        int points = 0;
        int dbEntries = 0;
        int dbPoints = 0;
        for (LOTRRoads road : allRoads) {
            points += road.roadPoints.length;
        }
        for (Map.Entry e : roadPointDatabase.pointMap.entrySet()) {
            ++dbEntries;
            dbPoints += ((List)e.getValue()).size();
        }
        FMLLog.info((String)("LOTRRoads: Created roads in " + (double)(newTime - time) / 1.0E9 + "s"), (Object[])new Object[0]);
        FMLLog.info((String)("LOTRRoads: roads=" + roads + ", points=" + points + ", dbEntries=" + dbEntries + ", dbPoints=" + dbPoints), (Object[])new Object[0]);
    }

    public static boolean isRoadAt(int x, int z) {
        return LOTRRoads.isRoadNear(x, z, 4) >= 0.0f;
    }

    public static float isRoadNear(int x, int z, int width) {
        double widthSq = width * width;
        float leastSqRatio = -1.0f;
        List<RoadPoint> points = roadPointDatabase.getPointsForCoords(x, z);
        for (RoadPoint point : points) {
            double dx = point.x - (double)x;
            double dz = point.z - (double)z;
            double distSq = dx * dx + dz * dz;
            if (distSq >= widthSq) continue;
            float f = (float)(distSq / widthSq);
            if (leastSqRatio == -1.0f) {
                leastSqRatio = f;
                continue;
            }
            if (f >= leastSqRatio) continue;
            leastSqRatio = f;
        }
        return leastSqRatio;
    }

    private static class RoadPointDatabase {
        private Map<Pair<Integer, Integer>, List<RoadPoint>> pointMap = new HashMap<Pair<Integer, Integer>, List<RoadPoint>>();

        private RoadPointDatabase() {
        }

        public List<RoadPoint> getPointsForCoords(int x, int z) {
            int x1 = x / 1000;
            int z1 = z / 1000;
            return this.getRoadList(x1, z1, false);
        }

        public void add(RoadPoint point) {
            int x = (int)Math.round(point.x / 1000.0);
            int z = (int)Math.round(point.z / 1000.0);
            int overlap = 1;
            for (int i = -overlap; i <= overlap; ++i) {
                for (int k = -overlap; k <= overlap; ++k) {
                    int xKey = x + i;
                    int zKey = z + k;
                    this.getRoadList(xKey, zKey, true).add(point);
                }
            }
        }

        private List<RoadPoint> getRoadList(int xKey, int zKey, boolean addToMap) {
            Pair key = Pair.of((Object)xKey, (Object)zKey);
            List<RoadPoint> list = this.pointMap.get((Object)key);
            if (list == null) {
                list = new ArrayList<RoadPoint>();
                if (addToMap) {
                    this.pointMap.put((Pair<Integer, Integer>)key, list);
                }
            }
            return list;
        }
    }

    public static class RoadPoint {
        public final double x;
        public final double z;
        public final boolean isWaypoint;

        public RoadPoint(double i, double j, boolean flag) {
            this.x = i;
            this.z = j;
            this.isWaypoint = flag;
        }
    }

    private static class BezierCurves {
        private static int roadLengthFactor = 1;

        private BezierCurves() {
        }

        private static LOTRRoads[] getSplines(String name, RoadPoint[] waypoints) {
            if (waypoints.length == 2) {
                RoadPoint p1 = waypoints[0];
                RoadPoint p2 = waypoints[1];
                LOTRRoads road = new LOTRRoads(name, new RoadPoint[]{p1, p2});
                double dx = p2.x - p1.x;
                double dz = p2.z - p1.z;
                int roadLength = (int)Math.round(Math.sqrt(dx * dx + dz * dz));
                int points = roadLength * roadLengthFactor;
                road.roadPoints = new RoadPoint[points];
                for (int l = 0; l < points; ++l) {
                    RoadPoint point;
                    double t = (double)l / (double)points;
                    road.roadPoints[l] = point = new RoadPoint(p1.x + dx * t, p1.z + dz * t, false);
                    roadPointDatabase.add(point);
                }
                return new LOTRRoads[]{road};
            }
            int length = waypoints.length;
            double[] x = new double[length];
            double[] z = new double[length];
            for (int i = 0; i < length; ++i) {
                x[i] = waypoints[i].x;
                z[i] = waypoints[i].z;
            }
            double[][] controlX = BezierCurves.getControlPoints(x);
            double[][] controlZ = BezierCurves.getControlPoints(z);
            int controlPoints = controlX[0].length;
            RoadPoint[] controlPoints1 = new RoadPoint[controlPoints];
            RoadPoint[] controlPoints2 = new RoadPoint[controlPoints];
            for (int i = 0; i < controlPoints; ++i) {
                RoadPoint p1 = new RoadPoint(controlX[0][i], controlZ[0][i], false);
                RoadPoint p2 = new RoadPoint(controlX[1][i], controlZ[1][i], false);
                controlPoints1[i] = p1;
                controlPoints2[i] = p2;
            }
            LOTRRoads[] roads = new LOTRRoads[length - 1];
            for (int i = 0; i < roads.length; ++i) {
                LOTRRoads road;
                RoadPoint p1 = waypoints[i];
                RoadPoint p2 = waypoints[i + 1];
                RoadPoint cp1 = controlPoints1[i];
                RoadPoint cp2 = controlPoints2[i];
                roads[i] = road = new LOTRRoads(name, new RoadPoint[]{p1, p2});
                double dx = p2.x - p1.x;
                double dz = p2.z - p1.z;
                int roadLength = (int)Math.round(Math.sqrt(dx * dx + dz * dz));
                int points = roadLength * roadLengthFactor;
                road.roadPoints = new RoadPoint[points];
                for (int l = 0; l < points; ++l) {
                    RoadPoint point;
                    double t = (double)l / (double)points;
                    road.roadPoints[l] = point = BezierCurves.bezier(p1, cp1, cp2, p2, t);
                    roadPointDatabase.add(point);
                }
            }
            return roads;
        }

        private static RoadPoint lerp(RoadPoint a, RoadPoint b, double t) {
            double x = a.x + (b.x - a.x) * t;
            double z = a.z + (b.z - a.z) * t;
            return new RoadPoint(x, z, false);
        }

        private static RoadPoint bezier(RoadPoint a, RoadPoint b, RoadPoint c, RoadPoint d, double t) {
            RoadPoint ab = BezierCurves.lerp(a, b, t);
            RoadPoint bc = BezierCurves.lerp(b, c, t);
            RoadPoint cd = BezierCurves.lerp(c, d, t);
            RoadPoint abbc = BezierCurves.lerp(ab, bc, t);
            RoadPoint bccd = BezierCurves.lerp(bc, cd, t);
            return BezierCurves.lerp(abbc, bccd, t);
        }

        private static double[][] getControlPoints(double[] src) {
            int i;
            int length = src.length - 1;
            double[] p1 = new double[length];
            double[] p2 = new double[length];
            double[] a = new double[length];
            double[] b = new double[length];
            double[] c = new double[length];
            double[] r = new double[length];
            a[0] = 0.0;
            b[0] = 2.0;
            c[0] = 1.0;
            r[0] = src[0] + 2.0 * src[1];
            for (i = 1; i < length - 1; ++i) {
                a[i] = 1.0;
                b[i] = 4.0;
                c[i] = 1.0;
                r[i] = 4.0 * src[i] + 2.0 * src[i + 1];
            }
            a[length - 1] = 2.0;
            b[length - 1] = 7.0;
            c[length - 1] = 0.0;
            r[length - 1] = 8.0 * src[length - 1] + src[length];
            for (i = 1; i < length; ++i) {
                double m = a[i] / b[i - 1];
                b[i] = b[i] - m * c[i - 1];
                r[i] = r[i] - m * r[i - 1];
            }
            p1[length - 1] = r[length - 1] / b[length - 1];
            for (i = length - 2; i >= 0; --i) {
                p1[i] = (r[i] - c[i] * p1[i + 1]) / b[i];
            }
            for (i = 0; i < length - 1; ++i) {
                p2[i] = 2.0 * src[i + 1] - p1[i + 1];
            }
            p2[length - 1] = 0.5 * (src[length] + p1[length - 1]);
            return new double[][]{p1, p2};
        }
    }

}

