/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelWargskinRug;
import lotr.client.render.entity.LOTRRenderRugBase;
import lotr.client.render.entity.LOTRRenderWarg;
import lotr.common.entity.item.LOTREntityWargskinRug;
import lotr.common.entity.npc.LOTREntityWarg;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderWargskinRug
extends LOTRRenderRugBase {
    public LOTRRenderWargskinRug() {
        super(new LOTRModelWargskinRug());
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityWargskinRug rug = (LOTREntityWargskinRug)entity;
        return LOTRRenderWarg.getWargSkin(rug.getRugType());
    }
}

