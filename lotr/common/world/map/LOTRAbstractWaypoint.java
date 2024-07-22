/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package lotr.common.world.map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface LOTRAbstractWaypoint {
    public String getCodeName();

    public String getDisplayName();

    public int getID();

    public WaypointLockState getLockState(EntityPlayer var1);

    public String getLoreText(EntityPlayer var1);

    public double getX();

    public int getXCoord();

    public double getY();

    public int getYCoord(World var1, int var2, int var3);

    public int getYCoordSaved();

    public int getZCoord();

    public boolean hasPlayerUnlocked(EntityPlayer var1);

    public boolean isHidden();

    public static enum WaypointLockState {
        STANDARD_LOCKED(0, 200),
        STANDARD_UNLOCKED(4, 200),
        STANDARD_UNLOCKED_CONQUEST(8, 200),
        CUSTOM_LOCKED(0, 204),
        CUSTOM_UNLOCKED(4, 204),
        SHARED_CUSTOM_LOCKED(0, 208),
        SHARED_CUSTOM_UNLOCKED(4, 208);

        public int iconU;
        public int iconV;

        private WaypointLockState(int u, int v) {
            this.iconU = u;
            this.iconV = v;
        }
    }

}

