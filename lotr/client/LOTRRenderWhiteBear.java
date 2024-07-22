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
package lotr.client;

import lotr.client.model.LOTRModelBear;
import lotr.client.render.entity.LOTRRenderBear;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderWhiteBear
extends RenderLiving {
    public static ResourceLocation texture = new ResourceLocation("lotr:mob/bear/whiteBear.png");

    public LOTRRenderWhiteBear() {
        super((ModelBase)new LOTRModelBear(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    public void preRenderCallback(EntityLivingBase entity, float f) {
        LOTRRenderBear.scaleBearModel();
    }

    public static void scaleBearModel() {
        float scale = 1.2f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }
}

