/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTREntityUtumnoWarg;
import lotr.common.entity.npc.LOTREntityWarg;
import net.minecraft.world.World;

public class LOTREntityUtumnoObsidianWarg
extends LOTREntityUtumnoWarg {
    public LOTREntityUtumnoObsidianWarg(World world) {
        super(world);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.setWargType(LOTREntityWarg.WargType.OBSIDIAN);
    }
}

