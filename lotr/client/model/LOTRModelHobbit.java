/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.model;

import lotr.client.model.LOTRModelBiped;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class LOTRModelHobbit
extends LOTRModelBiped {
    public ModelRenderer bipedChest;

    public LOTRModelHobbit() {
        this(0.0f);
    }

    public LOTRModelHobbit(float f) {
        this(f, 64, f == 0.0f ? 64 : 32);
    }

    public LOTRModelHobbit(float f, int width, int height) {
        super(f, 0.0f, width, height);
        boolean isArmor = height == 32;
        this.bipedChest = new ModelRenderer((ModelBase)this, 24, 0);
        this.bipedChest.addBox(-3.0f, 2.0f, -4.0f, 6, 3, 2, f);
        this.bipedChest.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedBody.addChild(this.bipedChest);
        if (!isArmor) {
            this.bipedHeadwear = new ModelRenderer((ModelBase)this, 0, 32);
            this.bipedHeadwear.addBox(-4.0f, -8.0f, -4.0f, 8, 12, 8, 0.5f + f);
            this.bipedHeadwear.setRotationPoint(0.0f, 2.0f, 0.0f);
        }
        if (!isArmor) {
            ModelRenderer rightFoot = new ModelRenderer((ModelBase)this, 40, 32);
            rightFoot.addBox(-2.0f, 10.0f, -5.0f, 4, 2, 3, f);
            rightFoot.rotateAngleY = (float)Math.toRadians(10.0);
            this.bipedRightLeg.addChild(rightFoot);
            ModelRenderer leftFoot = new ModelRenderer((ModelBase)this, 40, 32);
            leftFoot.addBox(-2.0f, 10.0f, -5.0f, 4, 2, 3, f);
            leftFoot.rotateAngleY = (float)Math.toRadians(-10.0);
            this.bipedLeftLeg.addChild(leftFoot);
        }
        this.bipedHead.rotationPointY += 4.0f;
        this.bipedHeadwear.rotationPointY += 4.0f;
        this.bipedBody.rotationPointY += 4.8f;
        this.bipedRightArm.rotationPointY += 4.8f;
        this.bipedLeftArm.rotationPointY += 4.8f;
        this.bipedRightLeg.rotationPointY += 4.8f;
        this.bipedLeftLeg.rotationPointY += 4.8f;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.bipedChest.showModel = entity instanceof LOTREntityNPC && ((LOTREntityNPC)entity).shouldRenderNPCChest();
        float f6 = 2.0f;
        if (this.isChild) {
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * f5), (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)(-1.0f * f5), (float)0.0f);
        } else {
            GL11.glPushMatrix();
        }
        this.bipedHead.render(f5);
        this.bipedHeadwear.render(f5);
        if (this.isChild) {
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)1.0f, (float)0.8333333f, (float)1.0f);
        this.bipedBody.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef((float)1.0f, (float)0.8333333f, (float)1.0f);
        this.bipedRightArm.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef((float)1.0f, (float)0.8333333f, (float)1.0f);
        this.bipedLeftArm.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef((float)1.0f, (float)0.8333333f, (float)1.0f);
        this.bipedRightLeg.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef((float)1.0f, (float)0.8333333f, (float)1.0f);
        this.bipedLeftLeg.render(f5);
        GL11.glPopMatrix();
        if (this.isChild) {
            GL11.glPopMatrix();
        } else {
            GL11.glPopMatrix();
        }
    }
}

