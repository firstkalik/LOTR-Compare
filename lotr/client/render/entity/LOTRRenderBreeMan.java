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
import lotr.common.entity.npc.LOTREntityBreeMan;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderBreeMan
extends LOTRRenderBiped {
    private static LOTRRandomSkins skinsMale;
    private static LOTRRandomSkins skinsFemale;
    private static LOTRRandomSkins headwearFemale;
    protected ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderBreeMan() {
        super(new LOTRModelHuman(), 0.5f);
        skinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/bree/bree_male");
        skinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/bree/bree_female");
        headwearFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/bree/headwear_female");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBreeMan man = (LOTREntityBreeMan)entity;
        if (man.familyInfo.isMale()) {
            return skinsMale.getRandomSkin(man);
        }
        return skinsFemale.getRandomSkin(man);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityBreeMan man = (LOTREntityBreeMan)entity;
        if (pass == 0 && man.getEquipmentInSlot(4) == null && !man.familyInfo.isMale() && LOTRRandomSkins.nextInt(man, 4) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(headwearFemale.getRandomSkin(man));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)man, pass, f);
    }
}

