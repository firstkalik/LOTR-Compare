/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.common.tileentity.LOTRTileEntityUtumnoReturnPortal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderUtumnoReturnPortal
extends TileEntitySpecialRenderer {
    private static ResourceLocation lightCircle = new ResourceLocation("lotr:misc/utumnoPortal_lightCircle.png");

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntityUtumnoReturnPortal portal = (LOTRTileEntityUtumnoReturnPortal)tileentity;
        World world = portal.getWorldObj();
        world.theProfiler.startSection("utumnoReturnPortal");
        float renderTime = (float)portal.ticksExisted + f;
        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.01f);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1), (float)((float)d2 + 0.5f));
        float alpha = 0.2f + 0.15f * MathHelper.sin((float)(renderTime * 0.1f));
        int passes = 12;
        for (int i = 0; i < passes; ++i) {
            GL11.glPushMatrix();
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F(1.0f, 1.0f, 1.0f, alpha);
            double width = (float)(i + 1) / (float)passes * 0.99f;
            double bottom = 0.0;
            double top = bottom + (double)LOTRTileEntityUtumnoReturnPortal.PORTAL_TOP;
            tessellator.addVertexWithUV(width /= 2.0, bottom, width, 0.0, 0.0);
            tessellator.addVertexWithUV(width, top, width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, top, width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, bottom, width, 0.0, 0.0);
            tessellator.addVertexWithUV(width, bottom, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(width, top, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, top, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, bottom, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(width, bottom, width, 0.0, 0.0);
            tessellator.addVertexWithUV(width, top, width, 0.0, 0.0);
            tessellator.addVertexWithUV(width, top, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(width, bottom, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, bottom, width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, top, width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, top, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, bottom, -width, 0.0, 0.0);
            tessellator.draw();
            GL11.glPopMatrix();
        }
        GL11.glEnable((int)3553);
        this.bindTexture(lightCircle);
        int circles = 12;
        for (int i = 0; i < circles; ++i) {
            GL11.glPushMatrix();
            float rotation = renderTime * (float)i * 0.2f;
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            alpha = 0.15f + 0.1f * MathHelper.sin((float)(renderTime * 0.1f + (float)i));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            double width = 1.5f + 1.5f * MathHelper.sin((float)(renderTime * 0.05f + (float)i));
            double height = 0.01 + (double)i * 0.01;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(width, height, width, 1.0, 1.0);
            tessellator.addVertexWithUV(width, height, -width, 1.0, 0.0);
            tessellator.addVertexWithUV(-width, height, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, height, width, 0.0, 1.0);
            tessellator.draw();
            GL11.glPopMatrix();
        }
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        world.theProfiler.endSection();
    }
}

