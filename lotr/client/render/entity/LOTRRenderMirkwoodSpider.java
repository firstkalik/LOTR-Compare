/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRenderSpiderBase;
import lotr.common.entity.npc.LOTREntityMirkwoodSpider;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderMirkwoodSpider
extends LOTRRenderSpiderBase {
    private static ResourceLocation[] spiderSkins = new ResourceLocation[]{new ResourceLocation("lotr:mob/spider/spider_mirkwood.png"), new ResourceLocation("lotr:mob/spider/spider_mirkwoodSlowness.png"), new ResourceLocation("lotr:mob/spider/spider_mirkwoodPoison.png")};

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityMirkwoodSpider spider = (LOTREntityMirkwoodSpider)entity;
        return spiderSkins[spider.getSpiderType()];
    }
}

