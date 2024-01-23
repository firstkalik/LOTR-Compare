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
import lotr.client.render.entity.LOTRRenderGondorMan;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityGondorRenegade;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderGondorRenegade
extends LOTRRenderGondorMan {
    private static LOTRRandomSkins hoodSkins;

    public LOTRRenderGondorRenegade() {
        hoodSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/renegade_hood");
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityGondorRenegade renegade = (LOTREntityGondorRenegade)entity;
        if (pass == 0 && renegade.getEquipmentInSlot(4) == null) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(hoodSkins.getRandomSkin(renegade));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)renegade, pass, f);
    }
}

