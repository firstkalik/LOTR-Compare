/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRShields;
import lotr.common.entity.npc.LOTRBannerBearer;
import lotr.common.entity.npc.LOTREntityDaleSoldier;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTREntityEsgarothBannerBearer
extends LOTREntityDaleSoldier
implements LOTRBannerBearer {
    public LOTREntityEsgarothBannerBearer(World world) {
        super(world);
        this.npcShield = LOTRShields.ALIGNMENT_ESGAROTH;
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.ESGAROTH;
    }
}

