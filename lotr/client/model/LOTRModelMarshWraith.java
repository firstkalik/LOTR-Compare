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

import lotr.common.entity.npc.LOTREntityMarshWraith;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class LOTRModelMarshWraith
extends ModelBase {
    private ModelRenderer head;
    private ModelRenderer headwear;
    private ModelRenderer body;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;
    private ModelRenderer cape;

    public LOTRModelMarshWraith() {
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.headwear = new ModelRenderer((ModelBase)this, 32, 0);
        this.headwear.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.5f);
        this.headwear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 24, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArm = new ModelRenderer((ModelBase)this, 46, 16);
        this.rightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.leftArm = new ModelRenderer((ModelBase)this, 46, 16);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.cape = new ModelRenderer((ModelBase)this, 24, 16);
        this.cape.addBox(-5.0f, 1.0f, 3.0f, 10, 16, 1);
        this.cape.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.cape.rotateAngleX = 0.1f;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.headwear.render(f5);
        this.body.render(f5);
        this.rightArm.render(f5);
        this.leftArm.render(f5);
        this.cape.render(f5);
        if (entity instanceof LOTREntityMarshWraith) {
            GL11.glDisable((int)3042);
            GL11.glDisable((int)32826);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.head.rotateAngleY = f3 / 57.295776f;
        this.head.rotateAngleX = f4 / 57.295776f;
        this.headwear.rotateAngleY = this.head.rotateAngleY;
        this.headwear.rotateAngleX = this.head.rotateAngleX;
    }
}

