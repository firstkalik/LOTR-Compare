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

import lotr.client.model.LOTRModelRam;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderHorse;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityRam;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderRam
extends RenderLiving {
    private static LOTRRandomSkins goatSkins;
    private static ResourceLocation saddleTexture;

    public LOTRRenderRam() {
        super((ModelBase)new LOTRModelRam(), 0.5f);
        this.setRenderPassModel((ModelBase)new LOTRModelRam());
        goatSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/ram/ram");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityRam goat = (LOTREntityRam)entity;
        ResourceLocation goatSkin = goatSkins.getRandomSkin(goat);
        return LOTRRenderHorse.getLayeredMountTexture(goat, goatSkin);
    }

    protected int shouldRenderPass(EntityLivingBase entity, int pass, float f) {
        if (pass == 0 && ((LOTREntityRam)entity).isMountSaddled()) {
            this.bindTexture(saddleTexture);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }

    static {
        saddleTexture = new ResourceLocation("lotr:mob/ram/saddle.png");
    }
}

