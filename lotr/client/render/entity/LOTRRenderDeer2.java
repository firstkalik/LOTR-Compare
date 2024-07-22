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

import lotr.client.model.LOTRModelDeer2;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityDeer2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDeer2
extends RenderLiving {
    private static LOTRRandomSkins deerSkins;

    public LOTRRenderDeer2() {
        super((ModelBase)new LOTRModelDeer2(), 0.5f);
        deerSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/deer2");
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityDeer2 deer = (LOTREntityDeer2)entity;
        ResourceLocation deerSkin = deerSkins.getRandomSkin(deer);
        return deerSkin;
    }
}

