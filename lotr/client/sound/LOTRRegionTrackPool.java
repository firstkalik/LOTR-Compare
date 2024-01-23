/*
 * Decompiled with CFR 0.148.
 */
package lotr.client.sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import lotr.client.sound.LOTRMusicTrack;
import lotr.client.sound.LOTRTrackRegionInfo;
import lotr.client.sound.LOTRTrackSorter;
import lotr.common.world.biome.LOTRMusicRegion;

public class LOTRRegionTrackPool {
    private final LOTRMusicRegion region;
    private final String subregion;
    private List<LOTRMusicTrack> trackList = new ArrayList<LOTRMusicTrack>();

    public LOTRRegionTrackPool(LOTRMusicRegion r, String s) {
        this.region = r;
        this.subregion = s;
    }

    public void addTrack(LOTRMusicTrack track) {
        this.trackList.add(track);
    }

    public boolean isEmpty() {
        return this.trackList.isEmpty();
    }

    public LOTRMusicTrack getRandomTrack(Random rand, LOTRTrackSorter.Filter filter) {
        double weight;
        List<LOTRMusicTrack> sortedTracks = LOTRTrackSorter.sortTracks(this.trackList, filter);
        double totalWeight = 0.0;
        for (LOTRMusicTrack track : sortedTracks) {
            double weight2 = track.getRegionInfo(this.region).getWeight();
            totalWeight += weight2;
        }
        double randWeight = rand.nextDouble();
        randWeight *= totalWeight;
        Iterator<LOTRMusicTrack> it = sortedTracks.iterator();
        LOTRMusicTrack track = null;
        do {
            if (it.hasNext()) continue;
            return track;
        } while ((randWeight -= (weight = (track = it.next()).getRegionInfo(this.region).getWeight())) >= 0.0);
        return track;
    }
}

