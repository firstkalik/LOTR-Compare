/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelSkeleton;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderSkeleton
extends RenderBiped {
    private static ResourceLocation skin = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public LOTRRenderSkeleton() {
        super((ModelBiped)new LOTRModelSkeleton(), 0.5f);
    }

    protected void func_82421_b() {
        this.field_82423_g = new LOTRModelSkeleton(1.0f);
        this.field_82425_h = new LOTRModelSkeleton(0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }
}

