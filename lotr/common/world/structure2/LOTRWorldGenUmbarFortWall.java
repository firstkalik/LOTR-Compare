/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.structure2;

import lotr.common.world.structure2.LOTRWorldGenSouthronFortWall;

public abstract class LOTRWorldGenUmbarFortWall
extends LOTRWorldGenSouthronFortWall {
    public LOTRWorldGenUmbarFortWall(boolean flag) {
        super(flag);
    }

    public static class Long
    extends LOTRWorldGenSouthronFortWall.Long {
        public Long(boolean flag) {
            super(flag);
        }

        @Override
        protected boolean isUmbar() {
            return true;
        }
    }

    public static class Short
    extends LOTRWorldGenSouthronFortWall.Short {
        public Short(boolean flag) {
            super(flag);
        }

        @Override
        protected boolean isUmbar() {
            return true;
        }
    }

}

