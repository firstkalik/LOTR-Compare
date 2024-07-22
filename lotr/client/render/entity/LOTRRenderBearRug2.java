/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelBearRug2;
import lotr.client.render.entity.LOTRRenderBear2;
import lotr.client.render.entity.LOTRRenderRugBase;
import lotr.common.entity.animal.LOTREntityBear2;
import lotr.common.entity.item.LOTREntityBearRug2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderBearRug2
extends LOTRRenderRugBase {
    public LOTRRenderBearRug2() {
        super(new LOTRModelBearRug2());
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBearRug2 rug = (LOTREntityBearRug2)entity;
        return LOTRRenderBear2.getBearSkin(rug.getRugType());
    }

    @Override
    protected void preRenderCallback() {
        LOTRRenderBear2.scaleBearModel();
    }
}

