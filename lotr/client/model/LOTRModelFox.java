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
import lotr.client.render.entity.LOTRGlowingEyes;
import lotr.common.entity.animal.LOTREntityFox;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class LOTRModelFox
extends ModelBase
implements LOTRGlowingEyes.Model {
    private final ModelRenderer head;
    private final ModelRenderer tail;
    private final ModelRenderer tail_r1;
    private final ModelRenderer body;
    private final ModelRenderer body_r1;
    private final ModelRenderer rightArm;
    private final ModelRenderer rightLeg;
    private final ModelRenderer leftArm;
    private final ModelRenderer leftLeg;

    public LOTRModelFox() {
        this(0.0f);
    }

    public LOTRModelFox(float f) {
        this.textureWidth = 48;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this);
        this.head.setRotationPoint(0.0f, 18.0f, -5.0f);
        this.head.cubeList.add(new ModelBox(this.head, 1, 5, -4.0f, -3.0f, -6.0f, 8, 6, 6, 0.0f));
        this.head.cubeList.add(new ModelBox(this.head, 6, 18, -2.0f, 1.0f, -9.0f, 4, 2, 3, 0.0f));
        this.head.cubeList.add(new ModelBox(this.head, 8, 1, -4.0f, -5.0f, -5.0f, 2, 2, 1, 0.0f));
        this.head.cubeList.add(new ModelBox(this.head, 15, 1, 2.0f, -5.0f, -5.0f, 2, 2, 1, 0.0f));
        this.tail = new ModelRenderer((ModelBase)this);
        this.tail.setRotationPoint(0.0f, 17.5f, 6.0f);
        this.tail_r1 = new ModelRenderer((ModelBase)this);
        this.tail_r1.setRotationPoint(0.0f, 6.5f, -6.0f);
        this.tail.addChild(this.tail_r1);
        this.setRotationAngle(this.tail_r1, 1.5708f, 0.0f, 0.0f);
        this.tail_r1.cubeList.add(new ModelBox(this.tail_r1, 30, 0, -2.0f, 6.0f, 4.0f, 4, 9, 5, 0.0f));
        this.body = new ModelRenderer((ModelBase)this);
        this.body.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body_r1 = new ModelRenderer((ModelBase)this);
        this.body_r1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.addChild(this.body_r1);
        this.setRotationAngle(this.body_r1, 1.5708f, 0.0f, 0.0f);
        this.body_r1.cubeList.add(new ModelBox(this.body_r1, 24, 15, -3.0f, -5.0f, 4.0f, 6, 11, 6, 0.0f));
        this.rightArm = new ModelRenderer((ModelBase)this);
        this.rightArm.setRotationPoint(2.0f, 20.0f, -3.0f);
        this.rightArm.cubeList.add(new ModelBox(this.rightArm, 4, 24, -1.1f, -2.0f, -1.0f, 2, 6, 2, 0.0f));
        this.rightLeg = new ModelRenderer((ModelBase)this);
        this.rightLeg.setRotationPoint(2.0f, 20.0f, 4.0f);
        this.rightLeg.cubeList.add(new ModelBox(this.rightLeg, 4, 24, -1.1f, -2.0f, -1.0f, 2, 6, 2, 0.0f));
        this.leftArm = new ModelRenderer((ModelBase)this);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(-2.0f, 20.0f, -3.0f);
        this.leftArm.cubeList.add(new ModelBox(this.leftArm, 4, 24, -0.9f, -2.0f, -1.0f, 2, 6, 2, 0.0f));
        this.leftLeg = new ModelRenderer((ModelBase)this);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(-2.0f, 20.0f, 4.0f);
        this.leftLeg.cubeList.add(new ModelBox(this.leftLeg, 4, 24, -0.9f, -2.0f, -1.0f, 2, 6, 2, 0.0f));
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        LOTREntityFox LOTREntityFox2 = (LOTREntityFox)entity;
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
            this.tail.render(f5);
            GL11.glPopMatrix();
        } else {
            this.body.render(f5);
            this.leftLeg.render(f5);
            this.leftArm.render(f5);
            this.rightLeg.render(f5);
            this.rightArm.render(f5);
            this.head.render(f5);
            this.tail.render(f5);
        }
    }

    @Override
    public void renderGlowingEyes(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
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
        this.tail.rotateAngleX = -0.3f;
        this.tail.rotateAngleX += MathHelper.cos((float)(f * -0.3f)) * -0.2f * f1;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

