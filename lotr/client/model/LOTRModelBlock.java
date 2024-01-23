/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package lotr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LOTRModelBlock
extends ModelBase {
    private ModelRenderer block = new ModelRenderer((ModelBase)this, 0, 0);

    public LOTRModelBlock(float f) {
        this.block.addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16, f);
        this.block.setRotationPoint(0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.block.render(f5);
    }
}

