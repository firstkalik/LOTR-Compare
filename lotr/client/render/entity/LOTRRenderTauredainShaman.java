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

import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderTauredain;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityTauredain;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderTauredainShaman
extends LOTRRenderTauredain {
    private static LOTRRandomSkins outfits;

    public LOTRRenderTauredainShaman() {
        outfits = LOTRRandomSkins.loadSkinsList("lotr:mob/tauredain/shaman_outfit");
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityTauredain tauredain = (LOTREntityTauredain)entity;
        if (pass == 1 && tauredain.getEquipmentInSlot(3) == null) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(outfits.getRandomSkin(tauredain));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)tauredain, pass, f);
    }
}

