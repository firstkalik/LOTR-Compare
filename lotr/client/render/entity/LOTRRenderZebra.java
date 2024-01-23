/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRenderHorse;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderZebra
extends LOTRRenderHorse {
    private static ResourceLocation zebraTexture = new ResourceLocation("lotr:mob/zebra.png");

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return zebraTexture;
    }
}

