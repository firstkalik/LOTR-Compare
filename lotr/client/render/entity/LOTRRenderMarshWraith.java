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

import lotr.client.model.LOTRModelMarshWraith;
import lotr.common.entity.npc.LOTREntityMarshWraith;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderMarshWraith
extends RenderLiving {
    private static ResourceLocation skin = new ResourceLocation("lotr:mob/wraith/marshWraith.png");

    public LOTRRenderMarshWraith() {
        super((ModelBase)new LOTRModelMarshWraith(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        super.preRenderCallback(entity, f);
        float f1 = 0.9375f;
        GL11.glScalef((float)f1, (float)f1, (float)f1);
        LOTREntityMarshWraith wraith = (LOTREntityMarshWraith)entity;
        if (wraith.getSpawnFadeTime() < 30) {
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)3008);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((float)wraith.getSpawnFadeTime() / 30.0f));
        } else if (wraith.getDeathFadeTime() > 0) {
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)3008);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((float)wraith.getDeathFadeTime() / 30.0f));
        }
    }

    protected float getDeathMaxRotation(EntityLivingBase entity) {
        return 0.0f;
    }
}

