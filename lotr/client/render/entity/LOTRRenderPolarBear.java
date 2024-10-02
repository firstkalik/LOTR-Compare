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

import java.util.HashMap;
import java.util.Map;
import lotr.client.model.LOTRModelPolarBear;
import lotr.common.entity.animal.LOTREntityPolarBear;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderPolarBear
extends RenderLiving {
    public static Map polarBearSkins = new HashMap();

    public LOTRRenderPolarBear() {
        super((ModelBase)new LOTRModelPolarBear(), 0.5f);
    }

    public static ResourceLocation getPolarBearSkin(LOTREntityPolarBear.BearType type) {
        String s = type.textureName();
        ResourceLocation skin = (ResourceLocation)polarBearSkins.get(s);
        if (skin == null) {
            skin = new ResourceLocation("lotr:mob/polarBear/" + s + ".png");
            polarBearSkins.put(s, skin);
        }
        return skin;
    }

    public static void scaleBearModel() {
        float scale = 1.2f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityPolarBear polarBear = (LOTREntityPolarBear)entity;
        return LOTRRenderPolarBear.getPolarBearSkin(polarBear.getBearType());
    }

    public void preRenderCallback(EntityLivingBase entity, float f) {
        LOTRRenderPolarBear.scaleBearModel();
    }
}

