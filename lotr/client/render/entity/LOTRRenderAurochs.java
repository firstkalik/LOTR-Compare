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

import lotr.client.model.LOTRModelAurochs;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityAurochs;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderAurochs
extends RenderLiving {
    private static LOTRRandomSkins aurochsSkins;

    public LOTRRenderAurochs() {
        super((ModelBase)new LOTRModelAurochs(), 0.5f);
        aurochsSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/aurochs");
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityAurochs aurochs = (LOTREntityAurochs)entity;
        ResourceLocation skin = aurochsSkins.getRandomSkin(aurochs);
        return skin;
    }
}

