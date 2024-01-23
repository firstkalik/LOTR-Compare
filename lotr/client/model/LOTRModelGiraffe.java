/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class LOTRModelGiraffe
extends ModelBase {
    public ModelRenderer body = new ModelRenderer((ModelBase)this, 0, 0).setTextureSize(128, 64);
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;

    public LOTRModelGiraffe(float f) {
        this.body.addBox(-6.0f, -8.0f, -13.0f, 12, 16, 26, f);
        this.body.setRotationPoint(0.0f, -11.0f, 0.0f);
        this.neck = new ModelRenderer((ModelBase)this, 0, 44).setTextureSize(128, 64);
        this.neck.addBox(-4.5f, -13.0f, -4.5f, 9, 11, 9, f);
        this.neck.setTextureOffset(78, 0).addBox(-3.0f, -37.0f, -3.0f, 6, 40, 6, f);
        this.neck.setRotationPoint(0.0f, -14.0f, -7.0f);
        this.head = new ModelRenderer((ModelBase)this, 96, 48).setTextureSize(128, 64);
        this.head.addBox(-3.0f, -43.0f, -6.0f, 6, 6, 10, f);
        this.head.setTextureOffset(10, 0).addBox(-4.0f, -45.0f, 1.5f, 1, 3, 2, f);
        this.head.setTextureOffset(17, 0).addBox(3.0f, -45.0f, 1.5f, 1, 3, 2, f);
        this.head.setTextureOffset(0, 0).addBox(-2.5f, -47.0f, 0.0f, 1, 4, 1, f);
        this.head.setTextureOffset(5, 0).addBox(1.5f, -47.0f, 0.0f, 1, 4, 1, f);
        this.head.setTextureOffset(76, 56).addBox(-2.0f, -41.0f, -11.0f, 4, 3, 5, f);
        this.head.setRotationPoint(0.0f, -14.0f, -7.0f);
        this.tail = new ModelRenderer((ModelBase)this, 104, 0).setTextureSize(128, 64);
        this.tail.addBox(-0.5f, 0.0f, 0.0f, 1, 24, 1, f);
        this.tail.setRotationPoint(0.0f, -12.0f, 13.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 112, 0).setTextureSize(128, 64);
        this.leg1.addBox(-2.0f, 0.0f, -2.0f, 4, 27, 4, f);
        this.leg1.setRotationPoint(-3.9f, -3.0f, 8.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 112, 0).setTextureSize(128, 64);
        this.leg2.addBox(-2.0f, 0.0f, -2.0f, 4, 27, 4, f);
        this.leg2.setRotationPoint(3.9f, -3.0f, 8.0f);
        this.leg2.mirror = true;
        this.leg3 = new ModelRenderer((ModelBase)this, 112, 0).setTextureSize(128, 64);
        this.leg3.addBox(-2.0f, 0.0f, -2.0f, 4, 27, 4, f);
        this.leg3.setRotationPoint(-3.9f, -3.0f, -7.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 112, 0).setTextureSize(128, 64);
        this.leg4.addBox(-2.0f, 0.0f, -2.0f, 4, 27, 4, f);
        this.leg4.setRotationPoint(3.9f, -3.0f, -7.0f);
        this.leg4.mirror = true;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (entity.riddenByEntity != null && entity.riddenByEntity instanceof EntityPlayer) {
            this.setRiddenHeadNeckRotation(f, f1, f2, f3, f4, f5);
        } else {
            this.setDefaultHeadNeckRotation(f, f1, f2, f3, f4, f5);
        }
        if (this.isChild) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)(8.0f * f5), (float)(4.0f * f5));
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
            this.head.render(f5);
            this.body.render(f5);
            this.neck.render(f5);
            this.leg1.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.leg4.render(f5);
            this.tail.render(f5);
            GL11.glPopMatrix();
        } else {
            this.head.render(f5);
            this.body.render(f5);
            this.neck.render(f5);
            this.leg1.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.leg4.render(f5);
            this.tail.render(f5);
        }
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.leg1.rotateAngleX = 0.5f * MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leg2.rotateAngleX = 0.5f * MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.4f * f1;
        this.leg3.rotateAngleX = 0.5f * MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.4f * f1;
        this.leg4.rotateAngleX = 0.5f * MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.tail.rotateAngleZ = 0.2f * MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.4f * f1;
    }

    public void setRiddenHeadNeckRotation(float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.setRotationPoint(0.0f, 25.0f, -48.0f);
        this.neck.rotateAngleX = 1.5707964f;
        this.neck.rotateAngleY = 0.0f;
        this.head.rotateAngleX = 0.0f;
        this.head.rotateAngleY = 0.0f;
    }

    public void setDefaultHeadNeckRotation(float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.setRotationPoint(0.0f, -14.0f, -7.0f);
        this.neck.rotateAngleX = 0.17453294f + f4 / 57.29578f;
        this.head.rotateAngleX = 0.17453294f + f4 / 57.29578f;
        this.neck.rotateAngleY = f3 / 57.29578f;
        this.head.rotateAngleY = f3 / 57.29578f;
    }
}

