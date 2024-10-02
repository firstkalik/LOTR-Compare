/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.village;

import lotr.common.world.map.LOTRWaypoint;

public class LocationInfo {
    public static LocationInfo RANDOM_GEN_HERE = new LocationInfo(0, 0, 0, "RANDOM_GEN");
    public static LocationInfo SPAWNED_BY_PLAYER = new LocationInfo(0, 0, 0, "PLAYER_SPAWNED");
    public static LocationInfo NONE_HERE = new LocationInfo(0, 0, 0, "NONE"){

        @Override
        public boolean isPresent() {
            return false;
        }
    };
    public int posX;
    public int posZ;
    public int rotation;
    public String name;
    public boolean isFixedLocation = false;
    public LOTRWaypoint associatedWaypoint;

    public LocationInfo(int x, int z, int r, String s) {
        this.posX = x;
        this.posZ = z;
        this.rotation = r;
        this.name = s;
    }

    public LOTRWaypoint getAssociatedWaypoint() {
        return this.associatedWaypoint;
    }

    public boolean isFixedLocation() {
        return this.isFixedLocation;
    }

    public boolean isPresent() {
        return true;
    }

    public LocationInfo setFixedLocation(LOTRWaypoint wp) {
        this.isFixedLocation = true;
        this.associatedWaypoint = wp;
        return this;
    }

}

