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

import lotr.client.model.LOTRModelSwan;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderSwan
extends RenderLiving {
    public static ResourceLocation swanSkin = new ResourceLocation("lotr:mob/swan.png");

    public LOTRRenderSwan() {
        super((ModelBase)new LOTRModelSwan(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return swanSkin;
    }
}

