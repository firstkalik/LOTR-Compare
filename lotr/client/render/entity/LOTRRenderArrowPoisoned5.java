/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderArrow
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderArrowPoisoned5
extends RenderArrow {
    private static final ResourceLocation arrowPoisonTexture = new ResourceLocation("lotr:item/arrowDragon.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return arrowPoisonTexture;
    }

    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        super.doRender(entity, 0.0, 0.0, 0.0, entityYaw, partialTicks);
        GL11.glPopMatrix();
    }
}

