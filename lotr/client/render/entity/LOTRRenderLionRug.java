/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelLionRug;
import lotr.client.render.entity.LOTRRenderLion;
import lotr.client.render.entity.LOTRRenderRugBase;
import lotr.common.entity.item.LOTREntityLionRug;
import lotr.common.item.LOTRItemLionRug;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderLionRug
extends LOTRRenderRugBase {
    public LOTRRenderLionRug() {
        super(new LOTRModelLionRug());
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityLionRug rug = (LOTREntityLionRug)entity;
        LOTRItemLionRug.LionRugType type = rug.getRugType();
        if (type == LOTRItemLionRug.LionRugType.LION) {
            return LOTRRenderLion.textureLion;
        }
        if (type == LOTRItemLionRug.LionRugType.LIONESS) {
            return LOTRRenderLion.textureLioness;
        }
        return new ResourceLocation("");
    }
}

