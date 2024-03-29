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

import lotr.client.model.LOTRModelScorpion;
import lotr.common.entity.animal.LOTREntityDesertScorpion;
import lotr.common.entity.animal.LOTREntityScorpion;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderScorpion
extends RenderLiving {
    private static ResourceLocation jungleTexture = new ResourceLocation("lotr:mob/scorpion/jungle.png");
    private static ResourceLocation desertTexture = new ResourceLocation("lotr:mob/scorpion/desert.png");

    public LOTRRenderScorpion() {
        super((ModelBase)new LOTRModelScorpion(), 1.0f);
        this.setRenderPassModel((ModelBase)new LOTRModelScorpion());
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof LOTREntityDesertScorpion) {
            return desertTexture;
        }
        return jungleTexture;
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        float scale = ((LOTREntityScorpion)entity).getScorpionScaleAmount();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }

    protected float getDeathMaxRotation(EntityLivingBase entity) {
        return 180.0f;
    }

    public float handleRotationFloat(EntityLivingBase entity, float f) {
        float strikeTime = ((LOTREntityScorpion)entity).getStrikeTime();
        if (strikeTime > 0.0f) {
            strikeTime -= f;
        }
        return strikeTime / 20.0f;
    }
}

