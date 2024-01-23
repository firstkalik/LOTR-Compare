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
import lotr.client.render.entity.LOTRRenderAurochs;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityKineAraw;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderKineAraw
extends LOTRRenderAurochs {
    private static LOTRRandomSkins kineSkins;

    public LOTRRenderKineAraw() {
        kineSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/kineAraw");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityKineAraw kine = (LOTREntityKineAraw)entity;
        ResourceLocation skin = kineSkins.getRandomSkin(kine);
        return skin;
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        float scale = LOTREntityKineAraw.KINE_SCALE;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }
}

