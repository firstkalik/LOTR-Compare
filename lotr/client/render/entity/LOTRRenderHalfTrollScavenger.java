/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelHalfTroll;
import lotr.client.render.entity.LOTRRenderHalfTroll;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderHalfTrollScavenger
extends LOTRRenderHalfTroll {
    private static ResourceLocation outfitTexture = new ResourceLocation("lotr:mob/halfTroll/scavenger.png");
    private ModelBiped outfitModel = new LOTRModelHalfTroll(0.5f);

    public LOTRRenderHalfTrollScavenger() {
        this.setRenderPassModel((ModelBase)this.outfitModel);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        if (pass == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(outfitTexture);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }
}

