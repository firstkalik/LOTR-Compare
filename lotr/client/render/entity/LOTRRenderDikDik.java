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

import lotr.client.model.LOTRModelDikDik;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityDikDik;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDikDik
extends RenderLiving {
    private static LOTRRandomSkins skins;

    public LOTRRenderDikDik() {
        super((ModelBase)new LOTRModelDikDik(), 0.8f);
        skins = LOTRRandomSkins.loadSkinsList("lotr:mob/dikdik");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityDikDik dikdik = (LOTREntityDikDik)entity;
        return skins.getRandomSkin(dikdik);
    }
}

