/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.entity.item.LOTREntityRugBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public abstract class LOTRRenderRugBase
extends Render {
    private ModelBase rugModel;

    public LOTRRenderRugBase(ModelBase m) {
        this.rugModel = m;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityRugBase rug = (LOTREntityRugBase)entity;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        this.bindEntityTexture((Entity)rug);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glRotatef((float)(180.0f - rug.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        this.preRenderCallback();
        this.rugModel.render((Entity)rug, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }

    protected void preRenderCallback() {
    }
}

