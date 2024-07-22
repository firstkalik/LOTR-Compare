/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderDwarf;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityDwarf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderWickedDwarf
extends LOTRRenderDwarf {
    private static LOTRRandomSkins wickedSkinsMale;
    private static LOTRRandomSkins wickedBanditSkinsMale;
    private static final ResourceLocation apronTexture;

    public LOTRRenderWickedDwarf() {
        wickedSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/dwarf/wicked_male");
        wickedBanditSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/dwarf/wicked_male");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityDwarf dwarf = (LOTREntityDwarf)entity;
        return wickedSkinsMale.getRandomSkin(dwarf);
    }

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

    static {
        apronTexture = new ResourceLocation("lotr:mob/dwarf/wicked_apron.png");
    }
}

