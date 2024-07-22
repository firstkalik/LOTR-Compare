/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.stream.JsonReader
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.MusicTicker
 *  net.minecraft.client.audio.MusicTicker$MusicType
 *  net.minecraft.client.audio.SoundCategory
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.audio.SoundRegistry
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.resources.FileResourcePack
 *  net.minecraft.client.resources.IReloadableResourceManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.WorldProvider
 *  net.minecraftforge.client.event.sound.PlaySoundEvent17
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.commons.io.input.BOMInputStream
 *  org.apache.logging.log4j.Logger
 */
package lotr.client.sound;

import com.google.common.base.Charsets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import lotr.client.sound.LOTRMusicCategory;
import lotr.client.sound.LOTRMusicResourceManager;
import lotr.client.sound.LOTRMusicTicker;
import lotr.client.sound.LOTRMusicTrack;
import lotr.client.sound.LOTRRegionTrackPool;
import lotr.client.sound.LOTRTrackRegionInfo;
import lotr.common.LOTRDimension;
import lotr.common.LOTRReflection;
import lotr.common.util.LOTRLog;
import lotr.common.world.LOTRWorldProvider;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRMusicRegion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.logging.log4j.Logger;

public class LOTRMusic
implements IResourceManagerReloadListener {
    private static File musicDir;
    private static final String jsonFilename = "music.json";
    public static final String musicResourcePath = "lotrmusic";
    public static final LOTRMusicResourceManager trackResourceManager;
    private static List<LOTRMusicTrack> allTracks;
    private static Map<LOTRMusicRegion.Sub, LOTRRegionTrackPool> regionTracks;
    private static boolean initSubregions;
    private static Random musicRand;

    public LOTRMusic() {
        ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener((IResourceManagerReloadListener)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public void onResourceManagerReload(IResourceManager resourcemanager) {
        LOTRMusic.loadMusicPacks(Minecraft.getMinecraft().mcDataDir, (SimpleReloadableResourceManager)resourcemanager);
    }

    public void update() {
        LOTRMusicTicker.update(musicRand);
    }

    @SubscribeEvent
    public void onPlaySound(PlaySoundEvent17 event) {
        Minecraft.getMinecraft();
        if (!allTracks.isEmpty() && event.category == SoundCategory.MUSIC && !(event.sound instanceof LOTRMusicTrack)) {
            if (LOTRMusic.isLOTRDimension()) {
                event.result = null;
                return;
            }
            if (LOTRMusic.isMenuMusic() && !LOTRMusic.getTracksForRegion(LOTRMusicRegion.MENU, null).isEmpty()) {
                event.result = null;
                return;
            }
        }
    }

    public static boolean isLOTRDimension() {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient world = mc.theWorld;
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        return entityplayer != null && world != null && world.provider instanceof LOTRWorldProvider;
    }

    public static boolean isMenuMusic() {
        Minecraft mc = Minecraft.getMinecraft();
        return mc.func_147109_W() == MusicTicker.MusicType.MENU;
    }

    public static LOTRRegionTrackPool getTracksForRegion(LOTRMusicRegion region, String sub) {
        if (region.hasSubregion(sub) || region.hasNoSubregions() && sub == null) {
            LOTRMusicRegion.Sub key = region.getSubregion(sub);
            LOTRRegionTrackPool regionPool = regionTracks.get((Object)key);
            if (regionPool == null) {
                regionPool = new LOTRRegionTrackPool(region, sub);
                regionTracks.put(key, regionPool);
            }
            return regionPool;
        }
        LOTRLog.logger.warn("LOTRMusic: No subregion " + sub + " for region " + region.regionName + "!");
        return null;
    }

    public static void addTrackToRegions(LOTRMusicTrack track) {
        allTracks.add(track);
        for (LOTRMusicRegion region : track.getAllRegions()) {
            if (region.hasNoSubregions()) {
                LOTRMusic.getTracksForRegion(region, null).addTrack(track);
                continue;
            }
            for (String sub : track.getRegionInfo(region).getSubregions()) {
                LOTRMusic.getTracksForRegion(region, sub).addTrack(track);
            }
        }
    }

    private static void loadMusicPacks(File mcDir, SimpleReloadableResourceManager resourceMgr) {
        musicDir = new File(mcDir, musicResourcePath);
        if (!musicDir.exists()) {
            musicDir.mkdirs();
        }
        allTracks.clear();
        regionTracks.clear();
        if (!initSubregions) {
            for (LOTRDimension dim : LOTRDimension.values()) {
                for (LOTRBiome biome : dim.biomeList) {
                    if (biome == null) continue;
                    biome.getBiomeMusic();
                }
            }
            initSubregions = true;
        }
        for (File file : musicDir.listFiles()) {
            if (!file.isFile() || !file.getName().endsWith(".zip")) continue;
            try {
                FileResourcePack resourcePack = new FileResourcePack(file);
                resourceMgr.reloadResourcePack((IResourcePack)resourcePack);
                ZipFile zipFile = new ZipFile(file);
                LOTRMusic.loadMusicPack(zipFile, resourceMgr);
            }
            catch (Exception e) {
                LOTRLog.logger.warn("LOTRMusic: Failed to load music pack " + file.getName() + "!");
                e.printStackTrace();
            }
        }
        try {
            LOTRMusic.generateReadme();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadMusicPack(ZipFile zip, SimpleReloadableResourceManager resourceMgr) throws IOException {
        ZipEntry entry = zip.getEntry(jsonFilename);
        if (entry != null) {
            InputStream stream = zip.getInputStream(entry);
            JsonReader reader = new JsonReader((Reader)new InputStreamReader((InputStream)new BOMInputStream(stream), Charsets.UTF_8.name()));
            JsonParser parser = new JsonParser();
            ArrayList<LOTRMusicTrack> packTracks = new ArrayList<LOTRMusicTrack>();
            JsonObject root = parser.parse(reader).getAsJsonObject();
            JsonArray rootArray = root.get("tracks").getAsJsonArray();
            for (JsonElement e : rootArray) {
                JsonObject trackData = e.getAsJsonObject();
                String filename = trackData.get("file").getAsString();
                ZipEntry trackEntry = zip.getEntry("assets/lotrmusic/" + filename);
                if (trackEntry == null) {
                    LOTRLog.logger.warn("LOTRMusic: Track " + filename + " in pack " + zip.getName() + " does not exist!");
                    continue;
                }
                InputStream trackStream = zip.getInputStream(trackEntry);
                LOTRMusicTrack track = new LOTRMusicTrack(filename);
                if (trackData.has("title")) {
                    String title = trackData.get("title").getAsString();
                    track.setTitle(title);
                }
                JsonArray regions = trackData.get("regions").getAsJsonArray();
                for (Object r : regions) {
                    LOTRMusicRegion region;
                    JsonObject regionData = ((JsonElement)r).getAsJsonObject();
                    String regionName = regionData.get("name").getAsString();
                    boolean allRegions = false;
                    if (regionName.equalsIgnoreCase("all")) {
                        region = null;
                        allRegions = true;
                    } else {
                        region = LOTRMusicRegion.forName(regionName);
                        if (region == null) {
                            LOTRLog.logger.warn("LOTRMusic: No region named " + regionName + "!");
                            continue;
                        }
                    }
                    ArrayList<String> subregionNames = new ArrayList<String>();
                    if (region != null && regionData.has("sub")) {
                        JsonArray subList = regionData.get("sub").getAsJsonArray();
                        for (Object s : subList) {
                            String sub = ((JsonElement)s).getAsString();
                            if (region.hasSubregion(sub)) {
                                subregionNames.add(sub);
                                continue;
                            }
                            LOTRLog.logger.warn("LOTRMusic: No subregion " + sub + " for region " + region.regionName + "!");
                        }
                    }
                    ArrayList<LOTRMusicCategory> regionCategories = new ArrayList<LOTRMusicCategory>();
                    if (region != null && regionData.has("categories")) {
                        Object s;
                        JsonArray catList = regionData.get("categories").getAsJsonArray();
                        s = catList.iterator();
                        while (s.hasNext()) {
                            JsonElement cat = (JsonElement)s.next();
                            String categoryName = cat.getAsString();
                            LOTRMusicCategory category = LOTRMusicCategory.forName(categoryName);
                            if (category != null) {
                                regionCategories.add(category);
                                continue;
                            }
                            LOTRLog.logger.warn("LOTRMusic: No category named " + (String)categoryName + "!");
                        }
                    }
                    double weight = -1.0;
                    if (regionData.has("weight")) {
                        weight = regionData.get("weight").getAsDouble();
                    }
                    ArrayList<LOTRMusicRegion> regionsAdd = new ArrayList<LOTRMusicRegion>();
                    if (allRegions) {
                        regionsAdd.addAll(Arrays.asList(LOTRMusicRegion.values()));
                    } else {
                        regionsAdd.add(region);
                    }
                    for (LOTRMusicRegion reg : regionsAdd) {
                        LOTRTrackRegionInfo regInfo = track.createRegionInfo(reg);
                        if (weight >= 0.0) {
                            regInfo.setWeight(weight);
                        }
                        if (subregionNames.isEmpty()) {
                            regInfo.addAllSubregions();
                        } else {
                            for (String sub : subregionNames) {
                                regInfo.addSubregion(sub);
                            }
                        }
                        if (regionCategories.isEmpty()) {
                            regInfo.addAllCategories();
                            continue;
                        }
                        for (LOTRMusicCategory cat : regionCategories) {
                            regInfo.addCategory(cat);
                        }
                    }
                }
                if (trackData.has("authors")) {
                    Object r;
                    JsonArray authorList = trackData.get("authors").getAsJsonArray();
                    r = authorList.iterator();
                    while (r.hasNext()) {
                        JsonElement a = (JsonElement)r.next();
                        String author = a.getAsString();
                        track.addAuthor(author);
                    }
                }
                track.loadTrack(trackStream);
                packTracks.add(track);
            }
            reader.close();
            LOTRLog.logger.info("LOTRMusic: Successfully loaded music pack " + zip.getName() + " with " + packTracks.size() + " tracks");
        }
    }

    private static void generateReadme() throws IOException {
        File readme = new File(musicDir, "readme.txt");
        readme.createNewFile();
        PrintStream writer = new PrintStream(new FileOutputStream(readme));
        ResourceLocation template = new ResourceLocation("lotr:music/readme.txt");
        InputStream templateIn = Minecraft.getMinecraft().getResourceManager().getResource(template).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream)new BOMInputStream(templateIn), Charsets.UTF_8.name()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            if (line.equals("#REGIONS#")) {
                writer.println("all");
                for (Enum region : LOTRMusicRegion.values()) {
                    String regionString = "";
                    regionString = regionString + ((LOTRMusicRegion)region).regionName;
                    List<String> subregions = ((LOTRMusicRegion)region).getAllSubregions();
                    if (!subregions.isEmpty()) {
                        String subs = "";
                        for (String s : subregions) {
                            if (subs.length() > 0) {
                                subs = subs + ", ";
                            }
                            subs = subs + s;
                        }
                        regionString = regionString + ": {" + subs + "}";
                    }
                    writer.println(regionString);
                }
                continue;
            }
            if (line.equals("#CATEGORIES#")) {
                for (Enum category : LOTRMusicCategory.values()) {
                    String catString = ((LOTRMusicCategory)category).categoryName;
                    writer.println(catString);
                }
                continue;
            }
            writer.println(line);
        }
        writer.close();
        reader.close();
    }

    static {
        trackResourceManager = new LOTRMusicResourceManager();
        allTracks = new ArrayList<LOTRMusicTrack>();
        regionTracks = new HashMap<LOTRMusicRegion.Sub, LOTRRegionTrackPool>();
        initSubregions = false;
        musicRand = new Random();
    }

    public static class Reflect {
        public static void putDomainResourceManager(String domain, IResourceManager manager) {
            SimpleReloadableResourceManager masterManager = (SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager();
            try {
                Map map = (Map)ObfuscationReflectionHelper.getPrivateValue(SimpleReloadableResourceManager.class, (Object)masterManager, (String[])new String[]{"domainResourceManagers", "field_110548_a"});
                map.put(domain, manager);
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
            }
        }

        public static SoundRegistry getSoundRegistry() {
            SoundHandler handler = Minecraft.getMinecraft().getSoundHandler();
            try {
                return (SoundRegistry)ObfuscationReflectionHelper.getPrivateValue(SoundHandler.class, (Object)handler, (String[])new String[]{"sndRegistry", "field_147697_e"});
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
                return null;
            }
        }
    }

}

