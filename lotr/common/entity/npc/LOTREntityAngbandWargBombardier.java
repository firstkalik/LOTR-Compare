/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.entity.npc.LOTREntityWargBombardier2;
import lotr.common.fac.LOTRFaction;
import net.minecraft.world.World;

public class LOTREntityAngbandWargBombardier
extends LOTREntityWargBombardier2 {
    public LOTREntityAngbandWargBombardier(World world) {
        super(world);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.setWargType(LOTREntityWarg.WargType.FIRE);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }
}

