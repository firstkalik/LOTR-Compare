/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelTermite;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderTermite
extends RenderLiving {
    private static ResourceLocation texture = new ResourceLocation("lotr:mob/termite.png");

    public LOTRRenderTermite() {
        super((ModelBase)new LOTRModelTermite(), 0.2f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        float scale = 0.25f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }
}

