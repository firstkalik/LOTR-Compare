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

import lotr.client.model.LOTRModelElk;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderHorse;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityElk;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderElk
extends RenderLiving {
    private static LOTRRandomSkins elkSkins;
    private static ResourceLocation saddleTexture;

    public LOTRRenderElk() {
        super((ModelBase)new LOTRModelElk(), 0.5f);
        this.setRenderPassModel((ModelBase)new LOTRModelElk(0.5f));
        elkSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/elk/elk");
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityElk elk = (LOTREntityElk)entity;
        ResourceLocation elkSkin = elkSkins.getRandomSkin(elk);
        return LOTRRenderHorse.getLayeredMountTexture(elk, elkSkin);
    }

    protected int shouldRenderPass(EntityLivingBase entity, int pass, float f) {
        if (pass == 0 && ((LOTREntityElk)entity).isMountSaddled()) {
            this.bindTexture(saddleTexture);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }

    static {
        saddleTexture = new ResourceLocation("lotr:mob/elk/saddle.png");
    }
}

