/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.structure2.LOTRWorldGenSouthronStatue;

public class LOTRWorldGenUmbarStatue
extends LOTRWorldGenSouthronStatue {
    public LOTRWorldGenUmbarStatue(boolean flag) {
        super(flag);
    }

    @Override
    protected boolean isUmbar() {
        return true;
    }

    @Override
    protected String getRandomStatueStrscan(Random random) {
        String[] statues = new String[]{"pillar", "snake", "pharazon"};
        String str = "umbar_statue_" + statues[random.nextInt(statues.length)];
        return str;
    }
}

