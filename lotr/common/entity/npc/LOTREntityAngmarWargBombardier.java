/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTREntityWargBombardier4;
import lotr.common.fac.LOTRFaction;
import net.minecraft.world.World;

public class LOTREntityAngmarWargBombardier
extends LOTREntityWargBombardier4 {
    public LOTREntityAngmarWargBombardier(World world) {
        super(world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }
}

