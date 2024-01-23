/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.LOTRClientProxy;
import lotr.common.entity.projectile.LOTREntityGandalfFireball;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderGandalfFireball
extends Render {
    protected ResourceLocation getEntityTexture(Entity entity) {
        return LOTRClientProxy.particlesTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glEnable((int)32826);
        this.bindEntityTexture(entity);
        Tessellator tessellator = Tessellator.instance;
        this.drawSprite(tessellator, 24 + ((LOTREntityGandalfFireball)entity).animationTick);
        GL11.glDisable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    private void drawSprite(Tessellator tessellator, int index) {
        float f = (float)(index % 8 * 16 + 0) / 128.0f;
        float f1 = (float)(index % 8 * 16 + 16) / 128.0f;
        float f2 = (float)(index / 8 * 16 + 0) / 128.0f;
        float f3 = (float)(index / 8 * 16 + 16) / 128.0f;
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.renderManager.playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator.setBrightness(15728880);
        tessellator.addVertexWithUV((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        tessellator.addVertexWithUV((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        tessellator.addVertexWithUV((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        tessellator.addVertexWithUV((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        tessellator.draw();
    }
}

