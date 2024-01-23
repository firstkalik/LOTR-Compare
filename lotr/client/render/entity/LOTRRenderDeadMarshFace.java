/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.fx.LOTREntityDeadMarshFace;
import lotr.client.model.LOTRModelMarshWraith;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderDeadMarshFace
extends Render {
    private static ResourceLocation skin = new ResourceLocation("lotr:mob/wraith/marshWraith.png");
    private ModelBase model = new LOTRModelMarshWraith();

    protected ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityDeadMarshFace face = (LOTREntityDeadMarshFace)entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)face.faceAlpha);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)face.rotationYaw, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)face.rotationPitch, (float)0.0f, (float)1.0f, (float)0.0f);
        this.bindEntityTexture((Entity)face);
        this.model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

