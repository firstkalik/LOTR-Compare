/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelTroll;
import lotr.client.render.entity.LOTRRenderTroll;
import lotr.common.entity.item.LOTREntityStoneTroll;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderStoneTroll
extends Render {
    private static ResourceLocation texture = new ResourceLocation("lotr:mob/troll/stone.png");
    private static LOTRModelTroll model = new LOTRModelTroll();
    private static LOTRModelTroll shirtModel = new LOTRModelTroll(1.0f, 0);
    private static LOTRModelTroll trousersModel = new LOTRModelTroll(0.75f, 1);

    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)d), (float)((float)d1 + 1.5f), (float)((float)d2));
        this.bindEntityTexture(entity);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glRotatef((float)(180.0f - entity.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        model.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        this.bindTexture(LOTRRenderTroll.trollOutfits[((LOTREntityStoneTroll)entity).getTrollOutfit()]);
        shirtModel.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        trousersModel.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }
}

