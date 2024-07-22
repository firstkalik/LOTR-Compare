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

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class LOTRModelGandalfHat
extends ModelBase {
    public ModelRenderer Staff;
    public ModelRenderer Staff2;
    public ModelRenderer Staff22;
    public ModelRenderer Staff23;
    public ModelRenderer Staff24;
    public ModelRenderer Sphere;
    public ModelRenderer TopStaff;
    public ModelRenderer Topstaff2;
    public ModelRenderer Topstaff2_1;

    public LOTRModelGandalfHat() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Sphere = new ModelRenderer((ModelBase)this, 85, 0);
        this.Sphere.setRotationPoint(0.0f, -15.5f, 0.0f);
        this.Sphere.addBox(-1.0f, -1.0f, -1.0f, 2, 1, 2, 0.0f);
        this.Staff2 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Staff2.setRotationPoint(-1.2f, -17.0f, 1.2f);
        this.Staff2.addBox(-0.5f, -1.0f, -0.5f, 1, 4, 1, 0.0f);
        this.Topstaff2_1 = new ModelRenderer((ModelBase)this, 80, 25);
        this.Topstaff2_1.setRotationPoint(0.0f, 1.3f, 0.0f);
        this.Topstaff2_1.addBox(-0.5f, 0.0f, -1.5f, 1, 3, 3, 0.0f);
        this.Staff = new ModelRenderer((ModelBase)this, 70, 0);
        this.Staff.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Staff.addBox(-1.0f, -13.5f, -1.0f, 2, 30, 2, 0.0f);
        this.Staff23 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Staff23.setRotationPoint(1.2f, -17.0f, -1.2f);
        this.Staff23.addBox(-0.5f, -1.0f, -0.5f, 1, 4, 1, 0.0f);
        this.Staff24 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Staff24.setRotationPoint(-1.2f, -17.0f, -1.2f);
        this.Staff24.addBox(-0.5f, -1.0f, -0.5f, 1, 4, 1, 0.0f);
        this.Staff22 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Staff22.setRotationPoint(1.2f, -17.0f, 1.2f);
        this.Staff22.addBox(-0.5f, -1.0f, -0.5f, 1, 4, 1, 0.0f);
        this.TopStaff = new ModelRenderer((ModelBase)this, 80, 10);
        this.TopStaff.setRotationPoint(0.0f, -15.5f, 0.0f);
        this.TopStaff.addBox(-1.5f, 0.0f, -1.5f, 3, 2, 3, 0.0f);
        this.Topstaff2 = new ModelRenderer((ModelBase)this, 80, 20);
        this.Topstaff2.setRotationPoint(0.0f, 1.3f, 0.0f);
        this.Topstaff2.addBox(-1.5f, 0.0f, -0.5f, 3, 3, 1, 0.0f);
        this.Staff.addChild(this.Sphere);
        this.Staff.addChild(this.Staff2);
        this.TopStaff.addChild(this.Topstaff2_1);
        this.Staff.addChild(this.Staff23);
        this.Staff.addChild(this.Staff24);
        this.Staff.addChild(this.Staff22);
        this.Staff.addChild(this.TopStaff);
        this.TopStaff.addChild(this.Topstaff2);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.Staff.offsetX, (float)this.Staff.offsetY, (float)this.Staff.offsetZ);
        GL11.glTranslatef((float)(this.Staff.rotationPointX * f5), (float)(this.Staff.rotationPointY * f5), (float)(this.Staff.rotationPointZ * f5));
        GL11.glScaled((double)0.7, (double)1.3, (double)0.7);
        GL11.glTranslatef((float)(-this.Staff.offsetX), (float)(-this.Staff.offsetY), (float)(-this.Staff.offsetZ));
        GL11.glTranslatef((float)(-this.Staff.rotationPointX * f5), (float)(-this.Staff.rotationPointY * f5), (float)(-this.Staff.rotationPointZ * f5));
        this.Staff.render(f5);
        GL11.glPopMatrix();
    }
}

