/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.SoundCategory
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.client.sound;

import java.util.Random;
import lotr.client.LOTRClientProxy;
import lotr.client.sound.LOTRMusic;
import lotr.client.sound.LOTRMusicCategory;
import lotr.client.sound.LOTRMusicTrack;
import lotr.client.sound.LOTRRegionTrackPool;
import lotr.client.sound.LOTRTrackSorter;
import lotr.common.LOTRConfig;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRMusicRegion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRMusicTicker {
    public static LOTRMusicTrack currentTrack;
    private static boolean wasPlayingMenu;
    private static int timing;

    public static void update(Random rand) {
        Minecraft mc = Minecraft.getMinecraft();
        boolean noMusic = mc.gameSettings.getSoundLevel(SoundCategory.MUSIC) <= 0.0f;
        boolean menu = LOTRMusic.isMenuMusic();
        if (wasPlayingMenu != menu) {
            if (currentTrack != null) {
                mc.getSoundHandler().stopSound((ISound)currentTrack);
                currentTrack = null;
            }
            wasPlayingMenu = menu;
            timing = 100;
        }
        if (currentTrack != null) {
            if (noMusic) {
                mc.getSoundHandler().stopSound((ISound)currentTrack);
            }
            if (!mc.getSoundHandler().isSoundPlaying((ISound)currentTrack)) {
                currentTrack = null;
                LOTRMusicTicker.resetTiming(rand);
            }
        }
        if (!noMusic) {
            boolean update = false;
            if (menu) {
                update = true;
            } else {
                boolean bl = update = LOTRMusic.isLOTRDimension() && !Minecraft.getMinecraft().isGamePaused();
            }
            if (update && currentTrack == null && --timing <= 0) {
                currentTrack = LOTRMusicTicker.getNewTrack(mc, rand);
                if (currentTrack != null) {
                    wasPlayingMenu = menu;
                    mc.getSoundHandler().playSound((ISound)currentTrack);
                    timing = Integer.MAX_VALUE;
                } else {
                    timing = 400;
                }
            }
        }
    }

    private static void resetTiming(Random rand) {
        timing = LOTRMusic.isMenuMusic() ? MathHelper.getRandomIntegerInRange((Random)rand, (int)(LOTRConfig.musicIntervalMenuMin * 20), (int)(LOTRConfig.musicIntervalMenuMax * 20)) : MathHelper.getRandomIntegerInRange((Random)rand, (int)(LOTRConfig.musicIntervalMin * 20), (int)(LOTRConfig.musicIntervalMax * 20));
    }

    private static LOTRMusicTrack getNewTrack(Minecraft mc, Random rand) {
        LOTRMusicRegion.Sub regionSub = LOTRMusicTicker.getCurrentRegion(mc, rand);
        LOTRMusicCategory category = LOTRMusicTicker.getCurrentCategory(mc, rand);
        if (regionSub != null) {
            LOTRMusicRegion region = regionSub.region;
            String sub = regionSub.subregion;
            LOTRTrackSorter.Filter filter = category != null ? LOTRTrackSorter.forRegionAndCategory(region, category) : LOTRTrackSorter.forAny();
            LOTRRegionTrackPool trackPool = LOTRMusic.getTracksForRegion(region, sub);
            return trackPool.getRandomTrack(rand, filter);
        }
        return null;
    }

    private static LOTRMusicRegion.Sub getCurrentRegion(Minecraft mc, Random rand) {
        BiomeGenBase biome;
        int i;
        int k;
        WorldClient world = mc.theWorld;
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        if (LOTRMusic.isMenuMusic()) {
            return LOTRMusicRegion.MENU.getWithoutSub();
        }
        if (LOTRMusic.isLOTRDimension() && LOTRClientProxy.doesClientChunkExist((World)world, i = MathHelper.floor_double((double)entityplayer.posX), k = MathHelper.floor_double((double)entityplayer.posZ)) && (biome = world.getBiomeGenForCoords(i, k)) instanceof LOTRBiome) {
            LOTRBiome lotrbiome = (LOTRBiome)biome;
            LOTRMusicRegion.Sub region = lotrbiome.getBiomeMusic();
            return region;
        }
        return null;
    }

    private static LOTRMusicCategory getCurrentCategory(Minecraft mc, Random rand) {
        WorldClient world = mc.theWorld;
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        if (world != null && entityplayer != null) {
            int i = MathHelper.floor_double((double)entityplayer.posX);
            if (LOTRMusicCategory.isCave((World)world, i, MathHelper.floor_double((double)entityplayer.boundingBox.minY), MathHelper.floor_double((double)entityplayer.posZ))) {
                return LOTRMusicCategory.CAVE;
            }
            if (LOTRMusicCategory.isDay((World)world)) {
                return LOTRMusicCategory.DAY;
            }
            return LOTRMusicCategory.NIGHT;
        }
        return null;
    }

    static {
        wasPlayingMenu = true;
        timing = 100;
    }
}

