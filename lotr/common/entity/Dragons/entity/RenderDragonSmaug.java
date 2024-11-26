/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelDragon
 *  net.minecraft.client.renderer.entity.RenderDragon
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.common.entity.Dragons.entity;

import lotr.common.entity.Dragons.entity.LOTREntityDragonSmaug;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDragonSmaug
extends RenderDragon {
    protected ResourceLocation texture;

    public RenderDragonSmaug(ModelDragon par1ModelBase, float parShadowSize) {
        this.setEntityTexture();
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        this.preRenderCallbackDragonAlpha((LOTREntityDragonSmaug)entity, f);
    }

    protected void preRenderCallbackDragonAlpha(LOTREntityDragonSmaug entity, float f) {
        GL11.glScalef((float)1.4f, (float)1.4f, (float)1.4f);
    }

    protected void setEntityTexture() {
        this.texture = new ResourceLocation("lotr:dragons/DragonSmaug.png");
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.texture;
    }
}

