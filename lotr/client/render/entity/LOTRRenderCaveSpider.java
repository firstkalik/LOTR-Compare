/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRenderSpiderBase;
import lotr.common.entity.npc.LOTREntityCaveSpider;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderCaveSpider
extends LOTRRenderSpiderBase {
    private static ResourceLocation[] spiderSkins = new ResourceLocation[]{new ResourceLocation("lotr:mob/spider/spider_cave.png"), new ResourceLocation("lotr:mob/spider/spider_cavePoison.png")};

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityCaveSpider spider = (LOTREntityCaveSpider)entity;
        return spiderSkins[spider.getSpiderType()];
    }
}

