/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRenderNearHaradrim;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderNearHaradrimWarlord
extends LOTRRenderNearHaradrim {
    private static ResourceLocation skin = new ResourceLocation("lotr:mob/nearHarad/warlord.png");

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }
}

