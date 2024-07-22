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

import lotr.client.model.LOTRModelRacoon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderRaccoon
extends RenderLiving {
    private static ResourceLocation raccoonSkin;

    public LOTRRenderRaccoon() {
        super((ModelBase)new LOTRModelRacoon(), 0.3f);
        raccoonSkin = new ResourceLocation("lotr:mob/raccoon/raccoon.png");
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        return raccoonSkin;
    }
}

