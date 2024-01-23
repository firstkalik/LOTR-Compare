/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRenderNearHaradrim;
import lotr.common.entity.npc.LOTREntityNearHaradrimBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderHaradrimTrader
extends LOTRRenderNearHaradrim {
    private ResourceLocation traderOutfit;

    public LOTRRenderHaradrimTrader(String s) {
        this.traderOutfit = new ResourceLocation("lotr:mob/nearHarad/" + s + ".png");
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityNearHaradrimBase haradrim = (LOTREntityNearHaradrimBase)entity;
        if (pass == 1 && haradrim.getEquipmentInSlot(3) == null) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(this.traderOutfit);
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)haradrim, pass, f);
    }
}

