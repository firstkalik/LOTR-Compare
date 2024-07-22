/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.client.LOTRTickHandlerClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderMorgulPortal
extends TileEntitySpecialRenderer {
    private static ResourceLocation portalTexture = new ResourceLocation("lotr:misc/gulduril_glow.png");

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        float f1 = (float)LOTRTickHandlerClient.clientTick + f;
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        GL11.glPushMatrix();
        this.bindTexture(portalTexture);
        GL11.glMatrixMode((int)5890);
        GL11.glLoadIdentity();
        float f2 = f1 * 0.01f;
        GL11.glTranslatef((float)f2, (float)0.0f, (float)0.0f);
        GL11.glMatrixMode((int)5888);
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 0.5f + 0.25f * MathHelper.sin((float)(f1 / 40.0f))), (float)((float)d2 + 0.5f));
        float f4 = 0.9f;
        GL11.glColor4f((float)f4, (float)f4, (float)f4, (float)1.0f);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)768, (int)1);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.5, 0.0, 0.5, 0.0, 0.0);
        tessellator.addVertexWithUV(0.5, 0.0, -0.5, 0.0, 0.0);
        tessellator.addVertexWithUV(-0.5, 0.0, -0.5, 0.0, 0.0);
        tessellator.addVertexWithUV(-0.5, 0.0, 0.5, 0.0, 0.0);
        tessellator.draw();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glMatrixMode((int)5890);
        GL11.glLoadIdentity();
        GL11.glMatrixMode((int)5888);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }
}

