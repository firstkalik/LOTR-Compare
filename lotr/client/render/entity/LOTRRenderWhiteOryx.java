/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderGemsbok;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityWhiteOryx;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderWhiteOryx
extends LOTRRenderGemsbok {
    private LOTRRandomSkins oryxSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/whiteOryx");

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return this.oryxSkins.getRandomSkin((LOTREntityWhiteOryx)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        float scale = 0.9f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }
}

