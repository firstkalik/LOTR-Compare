/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelMidge;
import lotr.common.entity.animal.LOTREntityMidges;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderMidges
extends RenderLiving {
    private static ResourceLocation midgeTexture = new ResourceLocation("lotr:mob/midge.png");
    private float renderTick;

    public LOTRRenderMidges() {
        super((ModelBase)new LOTRModelMidge(), 0.0f);
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.renderTick = f1;
        super.doRender(entity, d, d1, d2, f, f1);
    }

    protected void renderModel(EntityLivingBase entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bindEntityTexture((Entity)entity);
        this.mainModel.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity)entity);
        LOTREntityMidges midges = (LOTREntityMidges)entity;
        for (int l = 0; l < midges.midges.length; ++l) {
            LOTREntityMidges.Midge midge = midges.midges[l];
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(midge.midge_prevPosX + (midge.midge_posX - midge.midge_prevPosX) * this.renderTick), (float)(midge.midge_prevPosY + (midge.midge_posY - midge.midge_prevPosY) * this.renderTick), (float)(midge.midge_prevPosZ + (midge.midge_posZ - midge.midge_prevPosZ) * this.renderTick));
            GL11.glRotatef((float)midge.midge_rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)0.2f, (float)0.2f, (float)0.2f);
            this.mainModel.render((Entity)entity, f, f1, f2, f3, f4, f5);
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return midgeTexture;
    }
}

