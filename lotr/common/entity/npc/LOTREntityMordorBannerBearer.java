/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTRBannerBearer;
import lotr.common.entity.npc.LOTREntityMordorOrc;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTREntityMordorBannerBearer
extends LOTREntityMordorOrc
implements LOTRBannerBearer {
    public LOTREntityMordorBannerBearer(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.MORDOR;
    }
}

