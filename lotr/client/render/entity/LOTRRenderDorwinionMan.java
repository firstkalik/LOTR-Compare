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

import lotr.client.model.LOTRModelHuman;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityDorwinionMan;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDorwinionMan
extends LOTRRenderBiped {
    private static LOTRRandomSkins skinsMale;
    private static LOTRRandomSkins skinsFemale;
    private static LOTRRandomSkins outfits;
    private ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderDorwinionMan() {
        super(new LOTRModelHuman(), 0.5f);
        skinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/dorwinion/dorwinion_male");
        skinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/dorwinion/dorwinion_female");
        outfits = LOTRRandomSkins.loadSkinsList("lotr:mob/dorwinion/outfit");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityDorwinionMan man = (LOTREntityDorwinionMan)entity;
        if (man.familyInfo.isMale()) {
            return skinsMale.getRandomSkin(man);
        }
        return skinsFemale.getRandomSkin(man);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityDorwinionMan man = (LOTREntityDorwinionMan)entity;
        if (pass == 1 && man.getEquipmentInSlot(3) == null && LOTRRandomSkins.nextInt(man, 2) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(outfits.getRandomSkin(man));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)man, pass, f);
    }
}

