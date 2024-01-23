/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelGemsbok;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderGemsbok
extends RenderLiving {
    private static ResourceLocation texture = new ResourceLocation("lotr:mob/gemsbok.png");

    public LOTRRenderGemsbok() {
        super((ModelBase)new LOTRModelGemsbok(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}

