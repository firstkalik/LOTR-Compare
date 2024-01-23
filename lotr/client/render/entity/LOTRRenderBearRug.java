/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelBearRug;
import lotr.client.render.entity.LOTRRenderBear;
import lotr.client.render.entity.LOTRRenderRugBase;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.item.LOTREntityBearRug;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderBearRug
extends LOTRRenderRugBase {
    public LOTRRenderBearRug() {
        super(new LOTRModelBearRug());
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBearRug rug = (LOTREntityBearRug)entity;
        return LOTRRenderBear.getBearSkin(rug.getRugType());
    }

    @Override
    protected void preRenderCallback() {
        LOTRRenderBear.scaleBearModel();
    }
}

