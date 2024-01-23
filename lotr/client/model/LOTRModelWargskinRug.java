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

import lotr.client.model.LOTRModelWarg;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class LOTRModelWargskinRug
extends ModelBase {
    private LOTRModelWarg wargModel = new LOTRModelWarg();

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles();
        GL11.glTranslatef((float)0.0f, (float)-0.3f, (float)0.0f);
        GL11.glPushMatrix();
        GL11.glScalef((float)1.5f, (float)0.4f, (float)1.0f);
        this.wargModel.body.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.0f);
        this.wargModel.tail.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.1f);
        this.wargModel.head.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.3f, (float)0.0f, (float)0.0f);
        this.wargModel.leg1.render(f5);
        this.wargModel.leg3.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.3f, (float)0.0f, (float)0.0f);
        this.wargModel.leg2.render(f5);
        this.wargModel.leg4.render(f5);
        GL11.glPopMatrix();
    }

    private void setRotationAngles() {
        this.wargModel.leg1.rotateAngleX = (float)Math.toRadians(30.0);
        this.wargModel.leg1.rotateAngleZ = (float)Math.toRadians(90.0);
        this.wargModel.leg2.rotateAngleX = (float)Math.toRadians(30.0);
        this.wargModel.leg2.rotateAngleZ = (float)Math.toRadians(-90.0);
        this.wargModel.leg3.rotateAngleX = (float)Math.toRadians(-20.0);
        this.wargModel.leg3.rotateAngleZ = (float)Math.toRadians(90.0);
        this.wargModel.leg4.rotateAngleX = (float)Math.toRadians(-20.0);
        this.wargModel.leg4.rotateAngleZ = (float)Math.toRadians(-90.0);
    }
}

