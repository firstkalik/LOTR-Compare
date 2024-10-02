/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.model;

import java.util.List;
import lotr.common.entity.animal.LOTREntityPolarBear;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class LOTRModelPolarBear
extends ModelBase {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer body2_r1;
    private final ModelRenderer leftArm;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightArm;
    private final ModelRenderer rightLeg;

    public LOTRModelPolarBear() {
        this(0.0f);
    }

    public LOTRModelPolarBear(float f) {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this);
        this.head.setRotationPoint(0.0f, 10.5f, -14.0f);
        this.head.cubeList.add(new ModelBox(this.head, 0, 0, -3.5f, -3.5f, -7.0f, 7, 7, 7, 0.0f));
        this.head.cubeList.add(new ModelBox(this.head, 0, 44, -2.5f, 0.5f, -10.0f, 5, 3, 3, 0.0f));
        this.head.cubeList.add(new ModelBox(this.head, 26, 0, 2.5f, -4.5f, -5.0f, 2, 2, 1, 0.0f));
        this.head.cubeList.add(new ModelBox(this.head, 26, 0, -4.5f, -4.5f, -5.0f, 2, 2, 1, 0.0f));
        this.body = new ModelRenderer((ModelBase)this);
        this.body.setRotationPoint(0.0f, 14.0f, -6.0f);
        this.body2_r1 = new ModelRenderer((ModelBase)this);
        this.body2_r1.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.body.addChild(this.body2_r1);
        this.setRotationAngle(this.body2_r1, 1.5708f, 0.0f, 0.0f);
        this.body2_r1.cubeList.add(new ModelBox(this.body2_r1, 0, 19, -7.0f, 4.0f, 0.0f, 14, 14, 11, 0.0f));
        this.body2_r1.cubeList.add(new ModelBox(this.body2_r1, 39, 0, -6.0f, -8.0f, 0.0f, 12, 12, 10, 0.0f));
        this.leftArm = new ModelRenderer((ModelBase)this);
        this.leftArm.setRotationPoint(3.5f, 14.0f, -8.0f);
        this.leftArm.cubeList.add(new ModelBox(this.leftArm, 50, 40, -2.0f, 0.0f, -3.0f, 4, 10, 6, 0.0f));
        this.leftLeg = new ModelRenderer((ModelBase)this);
        this.leftLeg.setRotationPoint(4.25f, 14.0f, 7.0f);
        this.leftLeg.cubeList.add(new ModelBox(this.leftLeg, 50, 22, -1.75f, 0.0f, -4.0f, 4, 10, 8, 0.0f));
        this.rightArm = new ModelRenderer((ModelBase)this);
        this.rightArm.setRotationPoint(-3.75f, 14.0f, -8.0f);
        this.rightArm.cubeList.add(new ModelBox(this.rightArm, 50, 40, -1.75f, 0.0f, -3.0f, 4, 10, 6, 0.0f));
        this.rightLeg = new ModelRenderer((ModelBase)this);
        this.rightLeg.setRotationPoint(-4.5f, 14.0f, 7.0f);
        this.rightLeg.cubeList.add(new ModelBox(this.rightLeg, 50, 22, -2.0f, 0.0f, -4.0f, 4, 10, 8, 0.0f));
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        LOTREntityPolarBear LOTREntityPolarBear2 = (LOTREntityPolarBear)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (this.isChild) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
            this.head.render(f5);
            this.body.render(f5);
            this.leftArm.render(f5);
            this.leftLeg.render(f5);
            this.rightArm.render(f5);
            this.rightLeg.render(f5);
            GL11.glPopMatrix();
        } else {
            this.body.render(f5);
            this.leftLeg.render(f5);
            this.leftArm.render(f5);
            this.rightLeg.render(f5);
            this.rightArm.render(f5);
            this.head.render(f5);
        }
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.head.rotateAngleX = 0.17453292f;
        this.head.rotateAngleY = 0.0f;
        this.head.rotateAngleX += (float)Math.toRadians(f4);
        this.head.rotateAngleY += (float)Math.toRadians(f3);
        this.leftLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.0f * f1;
        this.leftArm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.0f * f1;
        this.rightLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.0f * f1;
        this.rightArm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.0f * f1;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

