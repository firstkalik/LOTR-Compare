/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderElvenPortal
extends TileEntitySpecialRenderer {
    private FloatBuffer floatBuffer = GLAllocation.createDirectFloatBuffer((int)16);
    private ResourceLocation portalTexture0 = new ResourceLocation("lotr:misc/elvenportal_0.png");
    private ResourceLocation portalTexture1 = new ResourceLocation("lotr:misc/elvenportal_1.png");

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        float f0 = (float)(tileentity.getWorldObj().getTotalWorldTime() % 16L) + f;
        float f1 = (float)TileEntityRendererDispatcher.staticPlayerX + f0 * 0.25f;
        float f2 = (float)TileEntityRendererDispatcher.staticPlayerY;
        float f3 = (float)TileEntityRendererDispatcher.staticPlayerZ + f0 * 0.25f;
        GL11.glDisable((int)2896);
        GL11.glColor3f((float)0.2f, (float)0.6f, (float)1.0f);
        Random random = new Random(31100L);
        float f4 = 0.75f;
        for (int i = 0; i < 16; ++i) {
            GL11.glPushMatrix();
            float f5 = 16 - i;
            float f6 = 0.0625f;
            float f7 = 1.0f / (f5 + 1.0f);
            if (i == 0) {
                this.bindTexture(this.portalTexture0);
                f7 = 0.1f;
                f5 = 65.0f;
                f6 = 0.125f;
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
            }
            if (i == 1) {
                this.bindTexture(this.portalTexture1);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)1, (int)1);
                f6 = 0.5f;
            }
            float f8 = (float)(-(d1 + (double)f4));
            float f9 = f8 + ActiveRenderInfo.objectY;
            float f10 = f8 + f5 + ActiveRenderInfo.objectY;
            float f11 = f9 / f10;
            GL11.glTranslatef((float)f1, (float)(f11 += (float)(d1 + (double)f4)), (float)f3);
            GL11.glTexGeni((int)8192, (int)9472, (int)9217);
            GL11.glTexGeni((int)8193, (int)9472, (int)9217);
            GL11.glTexGeni((int)8194, (int)9472, (int)9217);
            GL11.glTexGeni((int)8195, (int)9472, (int)9216);
            GL11.glTexGen((int)8192, (int)9473, (FloatBuffer)this.getFloatBuffer(1.0f, 0.0f, 0.0f, 0.0f));
            GL11.glTexGen((int)8193, (int)9473, (FloatBuffer)this.getFloatBuffer(0.0f, 0.0f, 1.0f, 0.0f));
            GL11.glTexGen((int)8194, (int)9473, (FloatBuffer)this.getFloatBuffer(0.0f, 0.0f, 0.0f, 1.0f));
            GL11.glTexGen((int)8195, (int)9474, (FloatBuffer)this.getFloatBuffer(0.0f, 1.0f, 0.0f, 0.0f));
            GL11.glEnable((int)3168);
            GL11.glEnable((int)3169);
            GL11.glEnable((int)3170);
            GL11.glEnable((int)3171);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5890);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glTranslatef((float)0.0f, (float)((float)(Minecraft.getSystemTime() % 700000L) / 700000.0f), (float)0.0f);
            GL11.glScalef((float)f6, (float)f6, (float)f6);
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
            GL11.glRotatef((float)((float)(i * i * 4321 + i * 9) * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
            GL11.glTranslatef((float)(-f1), (float)(-f3), (float)(-f2));
            f9 = f8 + ActiveRenderInfo.objectY;
            GL11.glTranslatef((float)(ActiveRenderInfo.objectX * f5 / f9), (float)(ActiveRenderInfo.objectZ * f5 / f9), (float)(-f2));
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            f11 = random.nextFloat() * 0.5f + 0.1f;
            float f12 = random.nextFloat() * 0.5f + 0.4f;
            float f13 = random.nextFloat() * 0.5f + 0.5f;
            if (i == 0) {
                f13 = 1.0f;
                f12 = 1.0f;
                f11 = 1.0f;
            }
            tessellator.setColorRGBA_F(f11 * f7, f12 * f7, f13 * f7, 1.0f);
            tessellator.addVertex(d, d1 + (double)f4, d2);
            tessellator.addVertex(d, d1 + (double)f4, d2 + 1.0);
            tessellator.addVertex(d + 1.0, d1 + (double)f4, d2 + 1.0);
            tessellator.addVertex(d + 1.0, d1 + (double)f4, d2);
            tessellator.draw();
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)3171);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2896);
    }

    private FloatBuffer getFloatBuffer(float f, float f1, float f2, float f3) {
        this.floatBuffer.clear();
        this.floatBuffer.put(f).put(f1).put(f2).put(f3);
        this.floatBuffer.flip();
        return this.floatBuffer;
    }
}

