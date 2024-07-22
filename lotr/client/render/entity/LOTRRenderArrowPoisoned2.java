/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderArrow
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderArrowPoisoned2
extends RenderArrow {
    private static final ResourceLocation arrowPoisonTexture = new ResourceLocation("lotr:item/arrowMorgul.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return arrowPoisonTexture;
    }
}

