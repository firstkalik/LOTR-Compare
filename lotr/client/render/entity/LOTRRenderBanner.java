/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.culling.Frustrum
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.util.HashMap;
import java.util.Map;
import lotr.client.model.LOTRModelBanner;
import lotr.common.LOTRConfig;
import lotr.common.entity.item.LOTREntityBanner;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBanner
extends Render {
    private static Map<LOTRItemBanner.BannerType, ResourceLocation> bannerTextures = new HashMap<LOTRItemBanner.BannerType, ResourceLocation>();
    private static ResourceLocation standTexture = new ResourceLocation("lotr:item/banner/stand.png");
    private static LOTRModelBanner model = new LOTRModelBanner();
    private static Frustrum bannerFrustum = new Frustrum();

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getStandTexture(entity);
    }

    public static ResourceLocation getStandTexture(LOTRItemBanner.BannerType type) {
        return standTexture;
    }

    private ResourceLocation getStandTexture(Entity entity) {
        LOTREntityBanner banner = (LOTREntityBanner)entity;
        return LOTRRenderBanner.getStandTexture(banner.getBannerType());
    }

    public static ResourceLocation getBannerTexture(LOTRItemBanner.BannerType type) {
        ResourceLocation r = bannerTextures.get((Object)type);
        if (r == null) {
            r = new ResourceLocation("lotr:item/banner/banner_" + type.bannerName + ".png");
            bannerTextures.put(type, r);
        }
        return r;
    }

    private ResourceLocation getBannerTexture(Entity entity) {
        LOTREntityBanner banner = (LOTREntityBanner)entity;
        return LOTRRenderBanner.getBannerTexture(banner.getBannerType());
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        int lx;
        int light2;
        int ly;
        LOTREntityBanner banner = (LOTREntityBanner)entity;
        Minecraft mc = Minecraft.getMinecraft();
        boolean debug = mc.gameSettings.showDebugInfo;
        boolean protecting = banner.isProtectingTerritory();
        boolean renderBox = debug && protecting;
        boolean seeThroughWalls = renderBox && LOTRConfig.showPermittedBannerSilhouettes && (mc.thePlayer.capabilities.isCreativeMode || banner.clientside_playerHasPermissionInSurvival());
        int protectColor = 65280;
        bannerFrustum.setPosition(d + RenderManager.renderPosX, d1 + RenderManager.renderPosY, d2 + RenderManager.renderPosZ);
        if (bannerFrustum.isBoundingBoxInFrustum(banner.boundingBox)) {
            GL11.glPushMatrix();
            GL11.glDisable((int)2884);
            GL11.glTranslatef((float)((float)d), (float)((float)d1 + 1.5f), (float)((float)d2));
            GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
            GL11.glRotatef((float)(180.0f - entity.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.01f, (float)0.0f);
            if (seeThroughWalls) {
                GL11.glDisable((int)2929);
                GL11.glDisable((int)3553);
                GL11.glDisable((int)2896);
                light2 = 15728880;
                lx = light2 % 65536;
                ly = light2 / 65536;
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)lx / 1.0f), (float)((float)ly / 1.0f));
                GL11.glColor4f((float)((float)(protectColor >> 16 & 0xFF) / 255.0f), (float)((float)(protectColor >> 8 & 0xFF) / 255.0f), (float)((float)(protectColor >> 0 & 0xFF) / 255.0f), (float)1.0f);
            }
            this.bindTexture(this.getStandTexture(entity));
            model.renderStand(0.0625f);
            model.renderPost(0.0625f);
            this.bindTexture(this.getBannerTexture(entity));
            model.renderBanner(0.0625f);
            if (seeThroughWalls) {
                GL11.glEnable((int)2929);
                GL11.glEnable((int)3553);
                GL11.glEnable((int)2896);
            }
            GL11.glEnable((int)2884);
            GL11.glPopMatrix();
        }
        if (renderBox) {
            GL11.glPushMatrix();
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3042);
            light2 = 15728880;
            lx = light2 % 65536;
            ly = light2 / 65536;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)lx / 1.0f), (float)((float)ly / 1.0f));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            float width = entity.width / 2.0f;
            AxisAlignedBB aabb = banner.createProtectionCube().offset(-RenderManager.renderPosX, -RenderManager.renderPosY, -RenderManager.renderPosZ);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderGlobal.drawOutlinedBoundingBox((AxisAlignedBB)aabb, (int)protectColor);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2884);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            GL11.glPopMatrix();
        }
    }
}

