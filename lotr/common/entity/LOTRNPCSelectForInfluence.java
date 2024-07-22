/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package lotr.common.entity;

import lotr.common.entity.LOTRNPCSelectByFaction;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;

public class LOTRNPCSelectForInfluence
extends LOTRNPCSelectByFaction {
    public LOTRNPCSelectForInfluence(LOTRFaction f) {
        super(f);
    }

    @Override
    public boolean isEntityApplicable(Entity entity) {
        boolean flag = super.isEntityApplicable(entity);
        if (flag && entity instanceof LOTREntityNPC && !((LOTREntityNPC)entity).generatesControlZone()) {
            return false;
        }
        return flag;
    }
}

