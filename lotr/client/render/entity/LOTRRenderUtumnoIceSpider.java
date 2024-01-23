/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRenderSpiderBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderUtumnoIceSpider
extends LOTRRenderSpiderBase {
    private static ResourceLocation spiderSkin = new ResourceLocation("lotr:mob/spider/spider_utumnoIce.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return spiderSkin;
    }
}

