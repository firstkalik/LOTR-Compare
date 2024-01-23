/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.ISound$AttenuationType
 *  net.minecraft.client.audio.ISoundEventAccessor
 *  net.minecraft.client.audio.PositionedSound
 *  net.minecraft.client.audio.SoundCategory
 *  net.minecraft.client.audio.SoundEventAccessorComposite
 *  net.minecraft.client.audio.SoundList
 *  net.minecraft.client.audio.SoundList$SoundEntry
 *  net.minecraft.client.audio.SoundList$SoundEntry$Type
 *  net.minecraft.client.audio.SoundPoolEntry
 *  net.minecraft.client.audio.SoundRegistry
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.sound;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotr.client.sound.LOTRMusic;
import lotr.client.sound.LOTRMusicCategory;
import lotr.client.sound.LOTRTrackRegionInfo;
import lotr.common.world.biome.LOTRMusicRegion;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.util.ResourceLocation;

public class LOTRMusicTrack
extends PositionedSound {
    private final String filename;
    private String title;
    private Map<LOTRMusicRegion, LOTRTrackRegionInfo> regions = new HashMap<LOTRMusicRegion, LOTRTrackRegionInfo>();
    private List<String> authors = new ArrayList<String>();

    public LOTRMusicTrack(String s) {
        super(LOTRMusicTrack.getMusicResource(s));
        this.volume = 1.0f;
        this.field_147663_c = 1.0f;
        this.xPosF = 0.0f;
        this.yPosF = 0.0f;
        this.zPosF = 0.0f;
        this.repeat = false;
        this.field_147665_h = 0;
        this.field_147666_i = ISound.AttenuationType.NONE;
        this.filename = s;
    }

    private static ResourceLocation getMusicResource(String s) {
        ResourceLocation res = new ResourceLocation("lotrmusic", s);
        return res;
    }

    public void loadTrack(InputStream in) throws IOException {
        this.loadSoundResource();
        LOTRMusic.addTrackToRegions(this);
    }

    private void loadSoundResource() {
        SoundEventAccessorComposite soundAccessorComp;
        ResourceLocation resource = this.getPositionedSoundLocation();
        SoundList soundList = new SoundList();
        soundList.setReplaceExisting(true);
        soundList.setSoundCategory(SoundCategory.MUSIC);
        SoundList.SoundEntry soundEntry = new SoundList.SoundEntry();
        soundEntry.setSoundEntryName(this.filename);
        soundEntry.setSoundEntryVolume(this.getVolume());
        soundEntry.setSoundEntryPitch(this.getPitch());
        soundEntry.setSoundEntryWeight(1);
        soundEntry.setSoundEntryType(SoundList.SoundEntry.Type.SOUND_EVENT);
        soundEntry.setStreaming(true);
        soundList.getSoundList().add(soundEntry);
        SoundRegistry sndRegistry = LOTRMusic.Reflect.getSoundRegistry();
        if (sndRegistry.containsKey((Object)resource) && !soundList.canReplaceExisting()) {
            soundAccessorComp = (SoundEventAccessorComposite)sndRegistry.getObject((Object)resource);
        } else {
            soundAccessorComp = new SoundEventAccessorComposite(resource, 1.0, 1.0, soundList.getSoundCategory());
            sndRegistry.registerSound(soundAccessorComp);
        }
        SoundPoolEntry soundPoolEntry = new SoundPoolEntry(resource, (double)soundEntry.getSoundEntryPitch(), (double)soundEntry.getSoundEntryVolume(), soundEntry.isStreaming());
        TrackSoundAccessor soundAccessor = new TrackSoundAccessor(soundPoolEntry, soundEntry.getSoundEntryWeight());
        soundAccessorComp.addSoundToEventPool((ISoundEventAccessor)soundAccessor);
    }

    public String getFilename() {
        return this.filename;
    }

    public String getTitle() {
        if (this.title != null) {
            return this.title;
        }
        return this.filename;
    }

    public void setTitle(String s) {
        this.title = s;
    }

    public Set<LOTRMusicRegion> getAllRegions() {
        return this.regions.keySet();
    }

    public LOTRTrackRegionInfo createRegionInfo(LOTRMusicRegion reg) {
        LOTRTrackRegionInfo info = this.regions.get((Object)reg);
        if (info == null) {
            info = new LOTRTrackRegionInfo(reg);
            this.regions.put(reg, info);
        }
        return info;
    }

    public LOTRTrackRegionInfo getRegionInfo(LOTRMusicRegion reg) {
        if (this.regions.containsKey((Object)reg)) {
            return this.regions.get((Object)reg);
        }
        return null;
    }

    public void addAuthor(String s) {
        this.authors.add(s);
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public String[] getTrackInfo() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Title: " + this.getTitle());
        list.add("Filename: " + this.getFilename());
        list.add("Regions:");
        for (LOTRMusicRegion reg : this.getAllRegions()) {
            List<LOTRMusicCategory> categories;
            LOTRTrackRegionInfo info = this.getRegionInfo(reg);
            list.add(">" + reg.regionName);
            list.add(">Weight: " + info.getWeight());
            List<String> subs = info.getSubregions();
            if (!subs.isEmpty()) {
                list.add(">Subregions:");
                for (String s2 : subs) {
                    list.add(">>" + s2);
                }
            }
            if ((categories = info.getCategories()).isEmpty()) continue;
            list.add(">Categories:");
            for (LOTRMusicCategory cat : categories) {
                list.add(">>" + cat.categoryName);
            }
        }
        list.add("Authors:");
        for (String auth : this.getAuthors()) {
            list.add(">" + auth);
        }
        return list.toArray(new String[0]);
    }

    private static class TrackSoundAccessor
    implements ISoundEventAccessor {
        private final SoundPoolEntry soundEntry;
        private final int weight;

        private TrackSoundAccessor(SoundPoolEntry e, int i) {
            this.soundEntry = e;
            this.weight = i;
        }

        public int func_148721_a() {
            return this.weight;
        }

        public SoundPoolEntry func_148720_g() {
            return new SoundPoolEntry(this.soundEntry);
        }
    }

}

