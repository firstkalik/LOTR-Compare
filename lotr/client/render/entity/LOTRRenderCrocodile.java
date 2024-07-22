/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelCrocodile;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityCrocodile;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderCrocodile
extends RenderLiving {
    private static LOTRRandomSkins crocoSkins;

    public LOTRRenderCrocodile() {
        super((ModelBase)new LOTRModelCrocodile(), 0.75f);
        crocoSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/croco");
    }

    public float handleRotationFloat(EntityLivingBase entity, float f) {
        float snapTime = ((LOTREntityCrocodile)entity).getSnapTime();
        if (snapTime > 0.0f) {
            snapTime -= f;
        }
        return snapTime / 20.0f;
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityCrocodile croco = (LOTREntityCrocodile)entity;
        ResourceLocation crocoSkin = crocoSkins.getRandomSkin(croco);
        return crocoSkin;
    }
}

