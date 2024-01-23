/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class LOTRRenderUtumnoPortal
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)false);
        int passes = 60;
        for (int i = 0; i < passes; ++i) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.0f + (float)i * 0.5f), (float)((float)d2 + 0.5f));
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F(0.0f, 0.0f, 0.0f, (float)(passes - i) / (float)passes);
            double width = 0.5;
            tessellator.addVertexWithUV(width, 0.0, width, 0.0, 0.0);
            tessellator.addVertexWithUV(width, 0.0, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, 0.0, -width, 0.0, 0.0);
            tessellator.addVertexWithUV(-width, 0.0, width, 0.0, 0.0);
            tessellator.draw();
            GL11.glPopMatrix();
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }
}

