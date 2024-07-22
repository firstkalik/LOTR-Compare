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

public class LOTRModelCampfire
extends ModelBase {
    public ModelRenderer Log;
    public ModelRenderer Log_1;
    public ModelRenderer Log_2;
    public ModelRenderer Log_3;
    public ModelRenderer Stone1;
    public ModelRenderer Stone1_1;
    public ModelRenderer Stone1_2;
    public ModelRenderer Stone2;
    public ModelRenderer Stone2_1;
    public ModelRenderer Stone2_2;
    public ModelRenderer Stone2_3;
    public ModelRenderer Stone2_4;
    public ModelRenderer Stone2_5;

    public LOTRModelCampfire() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Log_3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Log_3.setRotationPoint(2.75f, 22.0f, -4.25f);
        this.Log_3.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10, 0.0f);
        this.setRotateAngle(this.Log_3, 0.0f, -0.7853982f, 0.0f);
        this.Stone1_1 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Stone1_1.setRotationPoint(0.0f, 23.0f, 2.0f);
        this.Stone1_1.addBox(0.0f, 0.0f, 3.9f, 2, 1, 2, 0.0f);
        this.setRotateAngle(this.Stone1_1, 0.0f, 0.22759093f, 0.0f);
        this.Stone2_4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Stone2_4.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Stone2_4.addBox(0.0f, 0.0f, 6.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.Stone2_4, 0.0f, 2.8684487f, 0.0f);
        this.Stone2_5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Stone2_5.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Stone2_5.addBox(0.0f, 0.0f, 6.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.Stone2_5, 0.0f, 1.5934856f, 0.0f);
        this.Stone1_2 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Stone1_2.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Stone1_2.addBox(0.0f, 0.0f, -7.7f, 2, 1, 2, 0.0f);
        this.setRotateAngle(this.Stone1_2, 0.0f, 1.3203416f, 0.0f);
        this.Log_1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Log_1.setRotationPoint(-5.0f, 22.0f, 1.0f);
        this.Log_1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10, 0.0f);
        this.setRotateAngle(this.Log_1, 0.0f, 1.5707964f, 0.0f);
        this.Stone2_3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Stone2_3.setRotationPoint(0.0f, 23.0f, 1.0f);
        this.Stone2_3.addBox(0.0f, 0.0f, 6.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.Stone2_3, 0.0f, 1.0016445f, 0.0f);
        this.Stone2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Stone2.setRotationPoint(0.0f, 23.0f, -3.0f);
        this.Stone2.addBox(0.0f, 0.0f, 4.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.Stone2, 0.0f, -2.4586453f, 0.0f);
        this.Stone2_1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Stone2_1.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Stone2_1.addBox(0.0f, 0.0f, 6.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.Stone2_1, 0.0f, -1.3203416f, 0.0f);
        this.Log = new ModelRenderer((ModelBase)this, 0, 0);
        this.Log.setRotationPoint(-1.0f, 22.0f, -5.0f);
        this.Log.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10, 0.0f);
        this.Stone2_2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Stone2_2.setRotationPoint(0.0f, 23.0f, 1.0f);
        this.Stone2_2.addBox(0.0f, 0.0f, 5.4f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.Stone2_2, 0.0f, -0.63739425f, 0.0f);
        this.Log_2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Log_2.setRotationPoint(-4.25f, 22.0f, -2.75f);
        this.Log_2.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10, 0.0f);
        this.setRotateAngle(this.Log_2, 0.0f, 0.7853982f, 0.0f);
        this.Stone1 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Stone1.setRotationPoint(3.7f, 23.0f, -6.0f);
        this.Stone1.addBox(0.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f);
        this.setRotateAngle(this.Stone1, 0.0f, 0.3642502f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Log_3.render(f5);
        this.Stone1_1.render(f5);
        this.Stone2_4.render(f5);
        this.Stone2_5.render(f5);
        this.Stone1_2.render(f5);
        this.Log_1.render(f5);
        this.Stone2_3.render(f5);
        this.Stone2.render(f5);
        this.Stone2_1.render(f5);
        this.Log.render(f5);
        this.Stone2_2.render(f5);
        this.Log_2.render(f5);
        this.Stone1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

