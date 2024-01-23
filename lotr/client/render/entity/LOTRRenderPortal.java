/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelPortal;
import lotr.common.entity.item.LOTREntityPortal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderPortal
extends Render {
    public static ResourceLocation ringTexture = new ResourceLocation("lotr:misc/portal.png");
    public static ResourceLocation writingTexture = new ResourceLocation("lotr:misc/portal_writing.png");
    public static ModelBase ringModel = new LOTRModelPortal(false);
    public static ModelBase writingModelOuter = new LOTRModelPortal(true);
    public static ModelBase writingModelInner = new LOTRModelPortal(true);

    protected ResourceLocation getEntityTexture(Entity entity) {
        return ringTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityPortal portal = (LOTREntityPortal)entity;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)d), (float)((float)d1 + 0.75f), (float)((float)d2));
        GL11.glNormal3f((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glEnable((int)32826);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
        float portalScale = portal.getScale();
        if (portalScale < (float)LOTREntityPortal.MAX_SCALE) {
            portalScale += f1;
            GL11.glScalef((float)(portalScale /= (float)LOTREntityPortal.MAX_SCALE), (float)portalScale, (float)portalScale);
        }
        GL11.glRotatef((float)portal.getPortalRotation(f1), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        this.bindTexture(this.getEntityTexture(portal));
        float scale = 0.0625f;
        ringModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, scale);
        GL11.glDisable((int)2896);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Tessellator.instance.setBrightness(15728880);
        this.bindTexture(writingTexture);
        writingModelOuter.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, scale * 1.05f);
        writingModelInner.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, scale * 0.85f);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)32826);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }
}

