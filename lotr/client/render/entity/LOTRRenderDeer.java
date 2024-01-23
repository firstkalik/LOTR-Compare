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

import lotr.client.model.LOTRModelDeer;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityDeer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDeer
extends RenderLiving {
    private static LOTRRandomSkins deerSkins;

    public LOTRRenderDeer() {
        super((ModelBase)new LOTRModelDeer(), 0.5f);
        deerSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/deer");
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityDeer deer = (LOTREntityDeer)entity;
        ResourceLocation deerSkin = deerSkins.getRandomSkin(deer);
        return deerSkin;
    }
}

