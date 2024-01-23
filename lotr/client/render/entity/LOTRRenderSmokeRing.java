/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.LOTRClientProxy;
import lotr.client.model.LOTRModelSmokeShip;
import lotr.common.entity.projectile.LOTREntitySmokeRing;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderSmokeRing
extends Render {
    private ModelBase magicShipModel = new LOTRModelSmokeShip();

    protected ResourceLocation getEntityTexture(Entity entity) {
        return LOTRClientProxy.particlesTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        LOTREntitySmokeRing smokeRing = (LOTREntitySmokeRing)entity;
        float age = smokeRing.getRenderSmokeAge(f1);
        float opacity = 1.0f - age;
        int colour = smokeRing.getSmokeColour();
        if (colour == 16) {
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2884);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(opacity * 0.75f));
            GL11.glScalef((float)0.3f, (float)-0.3f, (float)0.3f);
            GL11.glRotatef((float)(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f1 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f1), (float)0.0f, (float)0.0f, (float)-1.0f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.magicShipModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            GL11.glEnable((int)2884);
            GL11.glEnable((int)3553);
        } else {
            float[] colours = EntitySheep.fleeceColorTable[colour];
            GL11.glColor4f((float)colours[0], (float)colours[1], (float)colours[2], (float)opacity);
            this.bindEntityTexture(entity);
            float smokeSize = 0.1f + 0.9f * age;
            GL11.glScalef((float)smokeSize, (float)smokeSize, (float)smokeSize);
            this.drawSprite(tessellator, 0);
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    private void drawSprite(Tessellator tessellator, int index) {
        float var3 = (float)(index % 16 * 16 + 0) / 128.0f;
        float var4 = (float)(index % 16 * 16 + 16) / 128.0f;
        float var5 = (float)(index / 16 * 16 + 0) / 128.0f;
        float var6 = (float)(index / 16 * 16 + 16) / 128.0f;
        float var7 = 1.0f;
        float var8 = 0.5f;
        float var9 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.renderManager.playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator.addVertexWithUV((double)(0.0f - var8), (double)(0.0f - var9), 0.0, (double)var3, (double)var6);
        tessellator.addVertexWithUV((double)(var7 - var8), (double)(0.0f - var9), 0.0, (double)var4, (double)var6);
        tessellator.addVertexWithUV((double)(var7 - var8), (double)(var7 - var9), 0.0, (double)var4, (double)var5);
        tessellator.addVertexWithUV((double)(0.0f - var8), (double)(var7 - var9), 0.0, (double)var3, (double)var5);
        tessellator.draw();
    }
}

