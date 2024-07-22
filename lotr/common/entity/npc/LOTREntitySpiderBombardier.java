/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.entity.npc.LOTREntitySpiderBase3;
import lotr.common.fac.LOTRFaction;
import net.minecraft.world.World;

public class LOTREntitySpiderBombardier
extends LOTREntitySpiderBase3 {
    public LOTREntitySpiderBombardier(World world) {
        super(world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.DOL_GULDUR;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    protected int getRandomSpiderScale() {
        return 1 + this.rand.nextInt(3);
    }

    @Override
    protected int getRandomSpiderType() {
        return VENOM_NONE;
    }
}

