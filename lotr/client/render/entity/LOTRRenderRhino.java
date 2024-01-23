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

import lotr.client.model.LOTRModelRhino;
import lotr.client.render.entity.LOTRRenderHorse;
import lotr.common.entity.animal.LOTREntityRhino;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderRhino
extends RenderLiving {
    private static ResourceLocation rhinoTexture = new ResourceLocation("lotr:mob/rhino/rhino.png");
    private static ResourceLocation saddleTexture = new ResourceLocation("lotr:mob/rhino/saddle.png");

    public LOTRRenderRhino() {
        super((ModelBase)new LOTRModelRhino(), 0.5f);
        this.setRenderPassModel((ModelBase)new LOTRModelRhino(0.5f));
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityRhino rhino = (LOTREntityRhino)entity;
        return LOTRRenderHorse.getLayeredMountTexture(rhino, rhinoTexture);
    }

    protected int shouldRenderPass(EntityLivingBase entity, int pass, float f) {
        if (pass == 0 && ((LOTREntityRhino)entity).isMountSaddled()) {
            this.bindTexture(saddleTexture);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }
}

