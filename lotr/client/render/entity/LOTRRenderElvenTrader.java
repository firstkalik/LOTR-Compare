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

import lotr.client.model.LOTRModelElf;
import lotr.client.render.entity.LOTRRenderElf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderElvenTrader
extends LOTRRenderElf {
    private ResourceLocation outfitTexture;
    private ModelBiped outfitModel = new LOTRModelElf(0.5f);

    public LOTRRenderElvenTrader(String s) {
        this.setRenderPassModel((ModelBase)this.outfitModel);
        this.outfitTexture = new ResourceLocation("lotr:mob/elf/" + s + ".png");
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        if (pass == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(this.outfitTexture);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }
}

