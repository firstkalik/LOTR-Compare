/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelBoar;
import lotr.client.render.entity.LOTRRenderHorse;
import lotr.common.entity.animal.LOTREntityWildBoar;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderWildBoar
extends RenderLiving {
    public static ResourceLocation boarSkin = new ResourceLocation("lotr:mob/boar/boar.png");
    private static ResourceLocation saddleTexture = new ResourceLocation("lotr:mob/boar/saddle.png");

    public LOTRRenderWildBoar() {
        super((ModelBase)new LOTRModelBoar(), 0.7f);
        this.setRenderPassModel((ModelBase)new LOTRModelBoar(0.5f));
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityWildBoar boar = (LOTREntityWildBoar)entity;
        return LOTRRenderHorse.getLayeredMountTexture(boar, boarSkin);
    }

    protected int shouldRenderPass(EntityLivingBase entity, int pass, float f) {
        if (pass == 0 && ((LOTREntityWildBoar)entity).isMountSaddled()) {
            this.bindTexture(saddleTexture);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }
}

