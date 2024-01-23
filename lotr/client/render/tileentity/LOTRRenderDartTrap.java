/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.common.tileentity.LOTRTileEntityDartTrap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class LOTRRenderDartTrap
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer.capabilities.isCreativeMode && mc.gameSettings.showDebugInfo && Minecraft.isGuiEnabled()) {
            GL11.glPushMatrix();
            GL11.glTranslated((double)(-TileEntityRendererDispatcher.staticPlayerX), (double)(-TileEntityRendererDispatcher.staticPlayerY), (double)(-TileEntityRendererDispatcher.staticPlayerZ));
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3042);
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            AxisAlignedBB range = ((LOTRTileEntityDartTrap)tileentity).getTriggerRange();
            RenderGlobal.drawOutlinedBoundingBox((AxisAlignedBB)range, (int)16777215);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2884);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            GL11.glPopMatrix();
        }
    }
}

