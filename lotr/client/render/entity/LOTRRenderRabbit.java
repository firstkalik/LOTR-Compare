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

import lotr.client.model.LOTRModelRabbit;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityRabbit;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderRabbit
extends RenderLiving {
    private static LOTRRandomSkins rabbitSkins;

    public LOTRRenderRabbit() {
        super((ModelBase)new LOTRModelRabbit(), 0.3f);
        rabbitSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/rabbit");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityRabbit rabbit = (LOTREntityRabbit)entity;
        return rabbitSkins.getRandomSkin(rabbit);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
    }
}

