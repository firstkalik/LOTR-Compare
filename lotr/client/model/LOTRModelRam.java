/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package lotr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class LOTRModelRam
extends ModelBase {
    public ModelRenderer tail;
    public ModelRenderer neck;
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg1;
    public ModelRenderer leftHorn_4;
    public ModelRenderer leftHorn_3;
    public ModelRenderer leftHorn_2;
    public ModelRenderer leftHorn_1;
    public ModelRenderer leftHorn;
    public ModelRenderer rightHorn;
    public ModelRenderer earRight;
    public ModelRenderer earLeft;

    public LOTRModelRam() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.rightHorn = new ModelRenderer((ModelBase)this, 1, 1);
        this.rightHorn.setRotationPoint(1.0f, -9.0f, 1.2f);
        this.rightHorn.addBox(-1.5f, 0.0f, 0.0f, 3, 3, 9, 0.0f);
        this.setRotateAngle(this.rightHorn, 0.83775806f, 0.4886922f, 0.27925268f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 38);
        this.leg3.setRotationPoint(3.3f, 15.0f, -6.0f);
        this.leg3.addBox(-2.0f, -1.0f, -2.0f, 4, 10, 4, 0.0f);
        this.earRight = new ModelRenderer((ModelBase)this, 34, 19);
        this.earRight.setRotationPoint(-1.5f, -9.5f, 0.0f);
        this.earRight.addBox(-1.0f, -2.5f, -1.0f, 1, 3, 2, 0.0f);
        this.leftHorn_1 = new ModelRenderer((ModelBase)this, 3, 2);
        this.leftHorn_1.setRotationPoint(11.0f, -8.0f, 0.5f);
        this.leftHorn_1.addBox(-2.0f, -2.0f, 0.0f, 2, 2, 8, 0.0f);
        this.setRotateAngle(this.leftHorn_1, 0.13962634f, -3.0368729f, -0.9075712f);
        this.leftHorn = new ModelRenderer((ModelBase)this, 1, 1);
        this.leftHorn.setRotationPoint(-7.0f, -13.3f, 6.8f);
        this.leftHorn.addBox(-3.0f, -1.5f, 0.0f, 3, 3, 9, 0.0f);
        this.setRotateAngle(this.leftHorn, -0.7330383f, -2.7576203f, 0.55850536f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 38);
        this.leg2.setRotationPoint(-3.3f, 15.0f, 9.0f);
        this.leg2.addBox(-2.0f, -1.0f, -2.0f, 4, 10, 4, 0.0f);
        this.earLeft = new ModelRenderer((ModelBase)this, 28, 19);
        this.earLeft.setRotationPoint(1.5f, -9.5f, 0.0f);
        this.earLeft.addBox(0.0f, -2.5f, -1.0f, 1, 3, 2, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 38);
        this.leg4.setRotationPoint(3.3f, 15.0f, 9.0f);
        this.leg4.addBox(-2.0f, -1.0f, -2.0f, 4, 10, 4, 0.0f);
        this.leftHorn_2 = new ModelRenderer((ModelBase)this, 3, 2);
        this.leftHorn_2.setRotationPoint(-11.0f, -8.0f, 0.5f);
        this.leftHorn_2.addBox(0.0f, -2.0f, 0.0f, 2, 2, 8, 0.0f);
        this.setRotateAngle(this.leftHorn_2, 0.13962634f, 3.0368729f, 0.9075712f);
        this.neck = new ModelRenderer((ModelBase)this, 0, 14);
        this.neck.setRotationPoint(0.0f, 5.5f, -9.0f);
        this.neck.addBox(-2.5f, -4.3f, -3.2f, 5, 8, 9, 0.0f);
        this.setRotateAngle(this.neck, -1.0646509f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 28, 0);
        this.head.setRotationPoint(0.0f, 7.5f, -9.5f);
        this.head.addBox(-3.0f, -10.0f, -6.0f, 6, 7, 12, 0.0f);
        this.setRotateAngle(this.head, 0.40142572f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 38);
        this.leg1.setRotationPoint(-3.3f, 15.0f, -6.0f);
        this.leg1.addBox(-2.0f, -1.0f, -2.0f, 4, 10, 4, 0.0f);
        this.leftHorn_4 = new ModelRenderer((ModelBase)this, 1, 1);
        this.leftHorn_4.setRotationPoint(7.0f, -13.3f, 6.8f);
        this.leftHorn_4.addBox(-3.0f, -1.5f, 0.0f, 3, 3, 9, 0.0f);
        this.setRotateAngle(this.leftHorn_4, 0.7330383f, -2.7576203f, 2.5830872f);
        this.tail = new ModelRenderer((ModelBase)this, 50, 38);
        this.tail.setRotationPoint(0.0f, 7.0f, 12.5f);
        this.tail.addBox(-1.0f, 0.0f, -2.0f, 2, 6, 2, 0.0f);
        this.setRotateAngle(this.tail, 0.41887903f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 3, 32);
        this.body.setRotationPoint(0.0f, 14.0f, -2.5f);
        this.body.addBox(-5.5f, -8.0f, -7.0f, 11, 10, 22, 0.0f);
        this.setRotateAngle(this.body, 0.0f, 0.0f, 0.0017453292f);
        this.leftHorn_3 = new ModelRenderer((ModelBase)this, 1, 1);
        this.leftHorn_3.setRotationPoint(-1.0f, -9.0f, 1.2f);
        this.leftHorn_3.addBox(-1.5f, 0.0f, 0.0f, 3, 3, 9, 0.0f);
        this.setRotateAngle(this.leftHorn_3, 0.83775806f, -0.4886922f, -0.27925268f);
        this.head.addChild(this.rightHorn);
        this.head.addChild(this.earRight);
        this.head.addChild(this.leftHorn_1);
        this.head.addChild(this.leftHorn);
        this.head.addChild(this.earLeft);
        this.head.addChild(this.leftHorn_2);
        this.head.addChild(this.leftHorn_4);
        this.head.addChild(this.leftHorn_3);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.leg3.render(f5);
        this.leg2.render(f5);
        this.leg4.render(f5);
        this.neck.render(f5);
        this.head.render(f5);
        this.leg1.render(f5);
        this.tail.render(f5);
        this.body.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.head.rotateAngleX = (float)Math.toRadians(f4) + 0.4014257f;
        this.head.rotateAngleY = (float)Math.toRadians(f3);
        this.neck.rotateAngleX = (float)Math.toRadians(-61.0);
        this.neck.rotateAngleY = this.head.rotateAngleY * 0.7f;
        this.earLeft.rotateAngleX = this.head.rotateAngleX;
        this.earLeft.rotateAngleY = this.head.rotateAngleY;
        this.earRight.rotateAngleX = this.head.rotateAngleX;
        this.earRight.rotateAngleY = this.head.rotateAngleY;
        this.leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.4f * f1;
        this.leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.4f * f1;
        this.leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.tail.rotateAngleX = (float)Math.toRadians(17.0);
    }
}

