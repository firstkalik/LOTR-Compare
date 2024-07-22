/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class LOTRRenderDwarvenGlow2 {
    public static float setupGlow(float brightness) {
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        float alphaFunc = GL11.glGetFloat((int)3010);
        GL11.glAlphaFunc((int)516, (float)0.02f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)brightness);
        return alphaFunc;
    }

    public static void endGlow(float alphaFunc) {
        GL11.glAlphaFunc((int)516, (float)alphaFunc);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2896);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

