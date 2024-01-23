/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.util.HashMap;
import java.util.Map;
import lotr.client.model.LOTRModelButterfly;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityButterfly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderButterfly
extends RenderLiving {
    private static Map<LOTREntityButterfly.ButterflyType, LOTRRandomSkins> textures = new HashMap<LOTREntityButterfly.ButterflyType, LOTRRandomSkins>();

    public LOTRRenderButterfly() {
        super((ModelBase)new LOTRModelButterfly(), 0.2f);
        for (LOTREntityButterfly.ButterflyType t : LOTREntityButterfly.ButterflyType.values()) {
            textures.put(t, LOTRRandomSkins.loadSkinsList("lotr:mob/butterfly/" + t.textureDir));
        }
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityButterfly butterfly = (LOTREntityButterfly)entity;
        if (butterfly.getButterflyType() == LOTREntityButterfly.ButterflyType.LORIEN) {
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
        }
        super.doRender(entity, d, d1, d2, f, f1);
        GL11.glEnable((int)2896);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityButterfly butterfly = (LOTREntityButterfly)entity;
        LOTRRandomSkins skins = textures.get((Object)butterfly.getButterflyType());
        return skins.getRandomSkin(butterfly);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        GL11.glScalef((float)0.3f, (float)0.3f, (float)0.3f);
    }

    protected float handleRotationFloat(EntityLivingBase entity, float f) {
        LOTREntityButterfly butterfly = (LOTREntityButterfly)entity;
        if (butterfly.isButterflyStill() && butterfly.flapTime > 0) {
            return (float)butterfly.flapTime - f;
        }
        return super.handleRotationFloat(entity, f);
    }
}

