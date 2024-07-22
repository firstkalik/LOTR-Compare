/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class LOTRModelRacoon
extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer tail;
    public ModelRenderer nose;
    public ModelRenderer ear1;
    public ModelRenderer ear2;

    public LOTRModelRacoon() {
        this(0.0f);
    }

    public LOTRModelRacoon(float f) {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 36, 0);
        this.head.setRotationPoint(-1.0f, 16.0f, -6.0f);
        this.head.addBox(-3.5f, -3.0f, -5.0f, 7, 6, 5, 0.0f);
        this.ear1 = new ModelRenderer((ModelBase)this, 15, 0);
        this.ear1.setRotationPoint(-2.5f, -3.0f, -3.0f);
        this.ear1.addBox(-1.0f, -2.0f, -0.5f, 2, 2, 1, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg3.setRotationPoint(-3.0f, 20.0f, 4.0f);
        this.leg3.addBox(-1.0f, 0.0f, -1.0f, 2, 4, 2, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg4.setRotationPoint(-3.0f, 20.0f, -4.0f);
        this.leg4.addBox(-1.0f, 0.0f, -1.0f, 2, 4, 2, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg2.setRotationPoint(1.0f, 20.0f, 4.0f);
        this.leg2.addBox(-1.0f, 0.0f, -1.0f, 2, 4, 2, 0.0f);
        this.tail = new ModelRenderer((ModelBase)this, 0, 18);
        this.tail.setRotationPoint(-1.0f, 17.0f, 5.5f);
        this.tail.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 8, 0.0f);
        this.ear2 = new ModelRenderer((ModelBase)this, 15, 0);
        this.ear2.setRotationPoint(2.5f, -3.0f, -3.5f);
        this.ear2.addBox(-1.0f, -2.0f, -0.5f, 2, 2, 1, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 28, 14);
        this.body.setRotationPoint(-1.0f, 17.0f, 0.0f);
        this.body.addBox(-3.0f, -3.0f, -6.0f, 6, 6, 12, 0.0f);
        this.nose = new ModelRenderer((ModelBase)this, 22, 18);
        this.nose.setRotationPoint(0.0f, 2.0f, -5.0f);
        this.nose.addBox(-2.0f, -1.0f, -2.0f, 4, 2, 2, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg1.setRotationPoint(1.0f, 20.0f, -4.0f);
        this.leg1.addBox(-1.0f, 0.0f, -1.0f, 2, 4, 2, 0.0f);
        this.head.addChild(this.ear1);
        this.head.addChild(this.ear2);
        this.head.addChild(this.nose);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (this.isChild) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
            this.head.render(f5);
            this.body.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.tail.render(f5);
            this.leg1.render(f5);
            this.leg4.render(f5);
            GL11.glPopMatrix();
        } else {
            this.body.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.tail.render(f5);
            this.leg1.render(f5);
            this.leg4.render(f5);
            this.head.render(f5);
        }
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.head.rotateAngleX = (float)Math.toRadians(5.0);
        this.head.rotateAngleY = 0.0f;
        this.head.rotateAngleX += (float)Math.toRadians(f4);
        this.head.rotateAngleY += (float)Math.toRadians(f3);
        this.leg1.rotateAngleX = MathHelper.cos((float)(f * 0.8f)) * f1 * 1.4f;
        this.leg2.rotateAngleX = MathHelper.cos((float)(f * 0.8f + 3.1415927f)) * f1 * 1.4f;
        this.leg4.rotateAngleX = MathHelper.cos((float)(f * 0.8f + 3.1415927f)) * f1 * 1.4f;
        this.leg3.rotateAngleX = MathHelper.cos((float)(f * 0.8f)) * f1 * 1.4f;
        this.tail.rotateAngleX = (float)Math.toRadians(-10.0);
    }
}

