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

import lotr.client.model.LOTRModelGiraffe;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class LOTRModelGiraffeRug
extends ModelBase {
    private LOTRModelGiraffe giraffeModel = new LOTRModelGiraffe(0.0f);

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.giraffeModel.setRiddenHeadNeckRotation(f, f1, f2, f3, f4, f5);
        this.setRotationAngles();
        GL11.glTranslatef((float)0.0f, (float)0.1f, (float)0.0f);
        GL11.glPushMatrix();
        GL11.glScalef((float)1.5f, (float)0.4f, (float)1.0f);
        this.giraffeModel.body.render(f5);
        this.giraffeModel.tail.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.6f, (float)-0.2f);
        this.giraffeModel.head.render(f5);
        this.giraffeModel.neck.render(f5);
        GL11.glPopMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.25f, (float)0.0f, (float)0.0f);
        this.giraffeModel.leg1.render(f5);
        this.giraffeModel.leg3.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.25f, (float)0.0f, (float)0.0f);
        this.giraffeModel.leg2.render(f5);
        this.giraffeModel.leg4.render(f5);
        GL11.glPopMatrix();
    }

    private void setRotationAngles() {
        this.giraffeModel.leg1.rotateAngleX = (float)Math.toRadians(30.0);
        this.giraffeModel.leg1.rotateAngleZ = (float)Math.toRadians(90.0);
        this.giraffeModel.leg2.rotateAngleX = (float)Math.toRadians(30.0);
        this.giraffeModel.leg2.rotateAngleZ = (float)Math.toRadians(-90.0);
        this.giraffeModel.leg3.rotateAngleX = (float)Math.toRadians(-20.0);
        this.giraffeModel.leg3.rotateAngleZ = (float)Math.toRadians(90.0);
        this.giraffeModel.leg4.rotateAngleX = (float)Math.toRadians(-20.0);
        this.giraffeModel.leg4.rotateAngleZ = (float)Math.toRadians(-90.0);
    }
}

