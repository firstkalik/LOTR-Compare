/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelBannerWall;
import lotr.client.render.entity.LOTRRenderBanner;
import lotr.common.entity.item.LOTREntityBannerWall;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBannerWall
extends Render {
    private static LOTRModelBannerWall model = new LOTRModelBannerWall();

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBannerWall banner = (LOTREntityBannerWall)entity;
        return LOTRRenderBanner.getStandTexture(banner.getBannerType());
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityBannerWall banner = (LOTREntityBannerWall)entity;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glRotatef((float)f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.bindTexture(LOTRRenderBanner.getStandTexture(banner.getBannerType()));
        model.renderPost(0.0625f);
        this.bindTexture(LOTRRenderBanner.getBannerTexture(banner.getBannerType()));
        model.renderBanner(0.0625f);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        if (RenderManager.debugBoundingBox) {
            GL11.glPushMatrix();
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3042);
            float width = entity.width / 2.0f;
            AxisAlignedBB aabb = banner.boundingBox.copy().offset(-RenderManager.renderPosX, -RenderManager.renderPosY, -RenderManager.renderPosZ);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderGlobal.drawOutlinedBoundingBox((AxisAlignedBB)aabb, (int)16777215);
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

