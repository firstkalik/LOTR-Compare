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
import lotr.client.model.LOTRModelBear2;
import lotr.common.entity.animal.LOTREntityBear2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBear2
extends RenderLiving {
    private static Map bearSkins = new HashMap();

    public LOTRRenderBear2() {
        super((ModelBase)new LOTRModelBear2(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBear2 bear = (LOTREntityBear2)entity;
        return LOTRRenderBear2.getBearSkin(bear.getBearType());
    }

    public static ResourceLocation getBearSkin(LOTREntityBear2.BearType type) {
        String s = type.textureName();
        ResourceLocation skin = (ResourceLocation)bearSkins.get(s);
        if (skin == null) {
            skin = new ResourceLocation("lotr:mob/bear2/" + s + ".png");
            bearSkins.put(s, skin);
        }
        return skin;
    }

    public void preRenderCallback(EntityLivingBase entity, float f) {
        LOTRRenderBear2.scaleBearModel();
    }

    public static void scaleBearModel() {
        float scale = 1.2f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }
}

