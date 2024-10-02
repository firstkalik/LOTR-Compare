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
import lotr.common.entity.animal.LOTREntityFrog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class LOTRModelFrog
extends ModelBase {
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer body;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftArm;
    private final ModelRenderer head;

    public LOTRModelFrog() {
        this(0.0f);
    }

    public LOTRModelFrog(float f) {
        this.textureWidth = 48;
        this.textureHeight = 48;
        this.leftLeg = new ModelRenderer((ModelBase)this);
        this.leftLeg.setRotationPoint(-2.5f, 21.0f, 3.75f);
        this.leftLeg.cubeList.add(new ModelBox(this.leftLeg, 0, 25, -3.0f, -0.1f, -2.0f, 3, 3, 4, 0.0f));
        this.leftLeg.cubeList.add(new ModelBox(this.leftLeg, 24, 35, -5.0f, 2.9f, -3.0f, 4, 0, 4, 0.0f));
        this.rightLeg = new ModelRenderer((ModelBase)this);
        this.rightLeg.setRotationPoint(3.5f, 21.0f, 3.75f);
        this.rightLeg.cubeList.add(new ModelBox(this.rightLeg, 14, 25, -1.0f, -0.1f, -2.0f, 3, 3, 4, 0.0f));
        this.rightLeg.cubeList.add(new ModelBox(this.rightLeg, 8, 35, 0.0f, 2.9f, -3.0f, 4, 0, 4, 0.0f));
        this.body = new ModelRenderer((ModelBase)this);
        this.body.setRotationPoint(0.0f, 20.0f, -0.25f);
        this.body.cubeList.add(new ModelBox(this.body, 3, 1, -3.5f, -0.1f, -4.0f, 7, 3, 9, 0.0f));
        this.body.cubeList.add(new ModelBox(this.body, 0, 13, -3.5f, -2.1f, -4.0f, 7, 3, 9, 0.0f));
        this.body.cubeList.add(new ModelBox(this.body, 0, 0, -3.5f, -4.1f, -3.0f, 3, 2, 3, 0.0f));
        this.body.cubeList.add(new ModelBox(this.body, 0, 5, 0.5f, -4.1f, -3.0f, 3, 2, 3, 0.0f));
        this.body.cubeList.add(new ModelBox(this.body, 0, 13, -3.5f, -4.6f, -3.5f, 3, 3, 0, 0.0f));
        this.body.cubeList.add(new ModelBox(this.body, 0, 16, 0.5f, -4.6f, -3.5f, 3, 3, 0, 0.0f));
        this.rightArm = new ModelRenderer((ModelBase)this);
        this.rightArm.setRotationPoint(-3.0f, 21.0f, -2.25f);
        this.rightArm.cubeList.add(new ModelBox(this.rightArm, 0, 38, -2.0f, -0.1f, -1.5f, 2, 3, 3, 0.0f));
        this.rightArm.cubeList.add(new ModelBox(this.rightArm, 9, 42, -2.0f, 2.9f, -3.5f, 4, 0, 4, 0.0f));
        this.leftArm = new ModelRenderer((ModelBase)this);
        this.leftArm.setRotationPoint(3.0f, 21.0f, -2.0f);
        this.leftArm.cubeList.add(new ModelBox(this.leftArm, 0, 32, 0.0f, -0.1f, -1.75f, 2, 3, 3, 0.0f));
        this.leftArm.cubeList.add(new ModelBox(this.leftArm, 23, 42, -2.0f, 2.9f, -3.75f, 4, 0, 4, 0.0f));
        this.head = new ModelRenderer((ModelBase)this);
        this.head.setRotationPoint(0.0f, 17.0f, -7.0f);
        this.head.cubeList.add(new ModelBox(this.head, 40, 0, -1.0f, -2.0f, -1.0f, 2, 2, 2, 0.0f));
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        LOTREntityFrog LOTREntityFrog2 = (LOTREntityFrog)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (this.isChild) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
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
        }
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.head.rotateAngleX = 0.17453292f;
        this.head.rotateAngleY = 0.0f;
        this.head.rotateAngleX += (float)Math.toRadians(f4);
        this.head.rotateAngleY += (float)Math.toRadians(f3);
        this.rightArm.rotateAngleX = 0.2617994f;
        this.leftArm.rotateAngleX = 0.2617994f;
        this.rightArm.rotateAngleX += MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftArm.rotateAngleX += MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.4f * f1;
        this.body.rotateAngleZ = MathHelper.cos((float)(f * 0.6662f)) * f1 * 0.3f;
        this.rightLeg.rotateAngleX = 0.2617994f;
        this.leftLeg.rotateAngleX = 0.2617994f;
        this.rightLeg.rotateAngleX += MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftLeg.rotateAngleX += MathHelper.cos((float)(f * 0.6662f + 3.1415927f)) * 1.4f * f1;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

