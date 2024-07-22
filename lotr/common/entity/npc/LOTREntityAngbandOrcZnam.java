/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTRBannerBearer;
import lotr.common.entity.npc.LOTREntityAngbandOrc;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTREntityAngbandOrcZnam
extends LOTREntityAngbandOrc
implements LOTRBannerBearer {
    public LOTREntityAngbandOrcZnam(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.DORNADAERACHAS;
    }
}

