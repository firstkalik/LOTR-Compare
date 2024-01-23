/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package lotr.common.block;

import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockGrapevine;
import net.minecraft.item.Item;

public class LOTRBlockGrapevineWhite
extends LOTRBlockGrapevine {
    public LOTRBlockGrapevineWhite() {
        super(true);
    }

    @Override
    public Item getGrapeItem() {
        return LOTRMod.grapeWhite;
    }

    @Override
    public Item getGrapeSeedsItem() {
        return LOTRMod.seedsGrapeWhite;
    }
}

