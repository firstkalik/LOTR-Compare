/*
 * Decompiled with CFR 0.148.
 */
package lotr.client.sound;

import java.util.ArrayList;
import java.util.List;
import lotr.client.sound.LOTRMusicCategory;
import lotr.common.world.biome.LOTRMusicRegion;

public class LOTRTrackRegionInfo {
    private LOTRMusicRegion region;
    private List<String> subregions = new ArrayList<String>();
    private double weight;
    private List<LOTRMusicCategory> categories = new ArrayList<LOTRMusicCategory>();

    public LOTRTrackRegionInfo(LOTRMusicRegion r) {
        this.region = r;
        this.weight = 1.0;
    }

    public List<String> getSubregions() {
        return this.subregions;
    }

    public void addSubregion(String sub) {
        if (!this.subregions.contains(sub)) {
            this.subregions.add(sub);
        }
    }

    public void addAllSubregions() {
        List<String> allSubs = this.region.getAllSubregions();
        if (!allSubs.isEmpty()) {
            for (String sub : allSubs) {
                this.addSubregion(sub);
            }
        }
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public List<LOTRMusicCategory> getCategories() {
        return this.categories;
    }

    public void addCategory(LOTRMusicCategory cat) {
        if (!this.categories.contains((Object)cat)) {
            this.categories.add(cat);
        }
    }

    public void addAllCategories() {
        for (LOTRMusicCategory cat : LOTRMusicCategory.values()) {
            this.addCategory(cat);
        }
    }
}

