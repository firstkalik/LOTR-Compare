/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelGiraffeRug;
import lotr.client.render.entity.LOTRRenderGiraffe;
import lotr.client.render.entity.LOTRRenderRugBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderGiraffeRug
extends LOTRRenderRugBase {
    public LOTRRenderGiraffeRug() {
        super(new LOTRModelGiraffeRug());
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return LOTRRenderGiraffe.texture;
    }
}

