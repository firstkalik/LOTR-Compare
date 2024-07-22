/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.world.WorldEvent
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import org.lwjgl.opengl.GL11;

public class ButtonRender
implements IResourceManagerReloadListener {
    public static final ResourceLocation ICONS = new ResourceLocation("lotr:map/icons.png");
    private int bindedShaderWidth = 0;
    private int bindedShaderHeight = 0;

    public ButtonRender() {
        FMLCommonHandler.instance().bus().register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onLoad(WorldEvent.Load event) {
        if (event.world.isRemote) {
            this.onResourceManagerReload(Minecraft.getMinecraft().getResourceManager());
        }
    }

    private void checkBindedShaderBufferSize() {
        if (OpenGlHelper.shadersSupported) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.displayWidth != this.bindedShaderWidth || mc.displayHeight != this.bindedShaderHeight) {
                this.onResourceManagerReload(Minecraft.getMinecraft().getResourceManager());
            }
        }
    }

    private void renderOverlay(float[] rgb, float alpha, Minecraft mc, ResourceLocation texture) {
        ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        if (rgb != null) {
            GL11.glColor4f((float)rgb[0], (float)rgb[1], (float)rgb[2], (float)alpha);
        } else {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        }
        GL11.glDisable((int)3008);
        if (texture != null) {
            mc.getTextureManager().bindTexture(texture);
        } else {
            GL11.glDisable((int)3553);
        }
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0, (double)height, -90.0, 0.0, 1.0);
        tessellator.addVertexWithUV((double)width, (double)height, -90.0, 1.0, 1.0);
        tessellator.addVertexWithUV((double)width, 0.0, -90.0, 1.0, 0.0);
        tessellator.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
        tessellator.draw();
        if (texture == null) {
            GL11.glEnable((int)3553);
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void onResourceManagerReload(IResourceManager p_110549_1_) {
    }
}

