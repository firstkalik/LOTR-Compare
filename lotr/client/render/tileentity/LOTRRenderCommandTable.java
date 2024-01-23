/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.client.LOTRTextures;
import lotr.common.tileentity.LOTRTileEntityCommandTable;
import lotr.common.world.genlayer.LOTRGenLayerWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderCommandTable
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntityCommandTable table = (LOTRTileEntityCommandTable)tileentity;
        this.renderTableAt(d, d1, d2, (double)tileentity.xCoord + 0.5, (double)tileentity.zCoord + 0.5, table.getZoomExp());
    }

    public void renderInvTable() {
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        EntityLivingBase viewer = Minecraft.getMinecraft().renderViewEntity;
        this.renderTableAt(0.0, 0.0, 0.0, viewer.posX, viewer.posZ, 0);
        this.bindTexture(TextureMap.locationBlocksTexture);
    }

    private void renderTableAt(double d, double d1, double d2, double viewerX, double viewerZ, int zoomExp) {
        GL11.glEnable((int)32826);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)2896);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.1f), (float)((float)d2 + 0.5f));
        float posX = Math.round(viewerX / (double)LOTRGenLayerWorld.scale) + 810L;
        float posY = Math.round(viewerZ / (double)LOTRGenLayerWorld.scale) + 730L;
        int viewportWidth = 400;
        viewportWidth = (int)Math.round((double)viewportWidth * Math.pow(2.0, zoomExp));
        double radius = 0.9;
        float minX = posX - (float)(viewportWidth / 2);
        float maxX = posX + (float)(viewportWidth / 2);
        if (minX < 0.0f) {
            posX = 0 + viewportWidth / 2;
        }
        if (maxX >= (float)LOTRGenLayerWorld.imageWidth) {
            posX = LOTRGenLayerWorld.imageWidth - viewportWidth / 2;
        }
        float minY = posY - (float)(viewportWidth / 2);
        float maxY = posY + (float)(viewportWidth / 2);
        if (minY < 0.0f) {
            posY = 0 + viewportWidth / 2;
        }
        if (maxY >= (float)LOTRGenLayerWorld.imageHeight) {
            posY = LOTRGenLayerWorld.imageHeight - viewportWidth / 2;
        }
        double minU = (double)(posX - (float)(viewportWidth / 2)) / (double)LOTRGenLayerWorld.imageWidth;
        double maxU = (double)(posX + (float)(viewportWidth / 2)) / (double)LOTRGenLayerWorld.imageWidth;
        double minV = (double)(posY - (float)(viewportWidth / 2)) / (double)LOTRGenLayerWorld.imageHeight;
        double maxV = (double)(posY + (float)(viewportWidth / 2)) / (double)LOTRGenLayerWorld.imageHeight;
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        LOTRTextures.drawMap((EntityPlayer)Minecraft.getMinecraft().thePlayer, true, -radius, radius, -radius, radius, 0.0, minU, maxU, minV, maxV, 1.0f);
        LOTRTextures.drawMapOverlay((EntityPlayer)Minecraft.getMinecraft().thePlayer, -radius, radius, -radius, radius, 0.0, minU, maxU, minV, maxV);
        double compassInset = radius * 0.05;
        LOTRTextures.drawMapCompassBottomLeft(-radius + compassInset, radius - compassInset, -0.01, 0.15 * radius * 0.0625);
        GL11.glDisable((int)3553);
        Tessellator tess = Tessellator.instance;
        double rRed = radius + 0.015;
        double rBlack = rRed + 0.015;
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.01f);
        tess.startDrawingQuads();
        tess.setColorOpaque_I(-6156032);
        tess.addVertex(-rRed, rRed, 0.0);
        tess.addVertex(rRed, rRed, 0.0);
        tess.addVertex(rRed, -rRed, 0.0);
        tess.addVertex(-rRed, -rRed, 0.0);
        tess.draw();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.01f);
        tess.startDrawingQuads();
        tess.setColorOpaque_I(-16777216);
        tess.addVertex(-rBlack, rBlack, 0.0);
        tess.addVertex(rBlack, rBlack, 0.0);
        tess.addVertex(rBlack, -rBlack, 0.0);
        tess.addVertex(-rBlack, -rBlack, 0.0);
        tess.draw();
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
        GL11.glEnable((int)2896);
    }
}

