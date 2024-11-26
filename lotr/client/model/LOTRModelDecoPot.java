/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package lotr.client.model;

import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LOTRModelDecoPot
extends ModelBase {
    private final ModelRenderer pot;

    public LOTRModelDecoPot() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.pot = new ModelRenderer((ModelBase)this);
        this.pot.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.pot.cubeList.add(new ModelBox(this.pot, 0, 11, -7.0f, -16.0f, -7.0f, 14, 16, 14, 0.0f));
        this.pot.cubeList.add(new ModelBox(this.pot, 0, 4, -3.0f, -17.0f, -3.0f, 6, 1, 6, 0.01f));
        this.pot.cubeList.add(new ModelBox(this.pot, 24, 0, -4.0f, -20.0f, -4.0f, 8, 3, 8, 0.01f));
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.pot.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

