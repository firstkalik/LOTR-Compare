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

import lotr.client.render.entity.LOTRRenderDwarf;
import lotr.common.entity.npc.LOTREntityDwarf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDwarfSmith
extends LOTRRenderDwarf {
    private static ResourceLocation apronTexture = new ResourceLocation("lotr:mob/dwarf/blacksmith_apron.png");

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityDwarf dwarf = (LOTREntityDwarf)entity;
        if (pass == 1 && dwarf.getEquipmentInSlot(3) == null) {
            this.setRenderPassModel((ModelBase)this.standardRenderPassModel);
            this.bindTexture(apronTexture);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }
}

