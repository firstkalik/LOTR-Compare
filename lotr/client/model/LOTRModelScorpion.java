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

import java.util.List;
import lotr.client.model.LOTRModelSpider;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class LOTRModelScorpion
extends LOTRModelSpider {
    private ModelRenderer armRight;
    private ModelRenderer armLeft;
    private ModelRenderer tail;

    public LOTRModelScorpion() {
        this.abdomen.cubeList.clear();
        this.armRight = new ModelRenderer((ModelBase)this, 36, 16);
        this.armRight.addBox(-16.0f, -1.0f, 0.0f, 16, 2, 2);
        this.armRight.setRotationPoint(-3.0f, 18.5f, -4.0f);
        ModelRenderer clawRight = new ModelRenderer((ModelBase)this, 0, 12);
        clawRight.addBox(-13.0f, -2.0f, -16.0f, 4, 3, 5);
        clawRight.addBox(-13.0f, -1.0f, -20.0f, 1, 1, 4);
        clawRight.addBox(-10.0f, -1.0f, -20.0f, 1, 1, 4);
        clawRight.rotateAngleY = (float)Math.toRadians(50.0);
        this.armRight.addChild(clawRight);
        this.armLeft = new ModelRenderer((ModelBase)this, 36, 16);
        this.armLeft.mirror = true;
        this.armLeft.addBox(0.0f, -1.0f, 0.0f, 16, 2, 2);
        this.armLeft.setRotationPoint(3.0f, 18.5f, -4.0f);
        ModelRenderer clawLeft = new ModelRenderer((ModelBase)this, 0, 12);
        clawLeft.mirror = true;
        clawLeft.addBox(9.0f, -2.0f, -16.0f, 4, 3, 5);
        clawLeft.addBox(12.0f, -1.0f, -20.0f, 1, 1, 4);
        clawLeft.addBox(9.0f, -1.0f, -20.0f, 1, 1, 4);
        clawLeft.rotateAngleY = (float)Math.toRadians(-50.0);
        this.armLeft.addChild(clawLeft);
        this.tail = new ModelRenderer((ModelBase)this, 0, 12);
        this.tail.addBox(-2.5f, -3.0f, 0.0f, 5, 5, 11);
        this.tail.setRotationPoint(0.0f, 19.5f, 3.0f);
        ModelRenderer tail1 = new ModelRenderer((ModelBase)this, 0, 12);
        tail1.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 10);
        tail1.setRotationPoint(0.0f, -0.5f, 11.0f);
        tail1.rotateAngleX = (float)Math.toRadians(40.0);
        this.tail.addChild(tail1);
        ModelRenderer tail2 = new ModelRenderer((ModelBase)this, 0, 12);
        tail2.addBox(-1.5f, -2.0f, 0.0f, 3, 4, 10);
        tail2.setRotationPoint(0.0f, 0.0f, 11.0f);
        tail2.rotateAngleX = (float)Math.toRadians(40.0);
        tail1.addChild(tail2);
        ModelRenderer sting = new ModelRenderer((ModelBase)this, 0, 12);
        sting.addBox(-1.0f, -0.5f, 0.0f, 2, 3, 5);
        sting.addBox(-0.5f, 0.0f, 5.0f, 1, 1, 3);
        sting.setRotationPoint(0.0f, 0.0f, 9.0f);
        sting.rotateAngleX = (float)Math.toRadians(90.0);
        tail2.addChild(sting);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.armRight.render(f5);
        this.armLeft.render(f5);
        this.tail.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.armRight.rotateAngleY = (float)Math.toRadians(-50.0) + MathHelper.cos((float)(f * 0.4f)) * f1 * 0.4f;
        this.armRight.rotateAngleY += f2 * (float)Math.toRadians(-40.0);
        this.armLeft.rotateAngleY = -this.armRight.rotateAngleY;
        this.tail.rotateAngleX = (float)Math.toRadians(30.0) + MathHelper.cos((float)(f * 0.4f)) * f1 * 0.15f;
        this.tail.rotateAngleX += f2 * (float)Math.toRadians(90.0);
    }
}

