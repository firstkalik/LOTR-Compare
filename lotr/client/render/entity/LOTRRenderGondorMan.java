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
import lotr.common.entity.npc.LOTREntityGondorMan;
import lotr.common.entity.npc.LOTREntityGondorSoldier;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderGondorMan
extends LOTRRenderBiped {
    private static LOTRRandomSkins skinsMale;
    private static LOTRRandomSkins skinsFemale;
    private static LOTRRandomSkins skinsSoldier;
    private static LOTRRandomSkins outfits;
    private static LOTRRandomSkins headwearFemale;
    protected ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderGondorMan() {
        super(new LOTRModelHuman(), 0.5f);
        this.setRenderPassModel((ModelBase)this.outfitModel);
        skinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/gondor_male");
        skinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/gondor_female");
        skinsSoldier = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/gondorSoldier");
        outfits = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/outfit");
        headwearFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/headwear_female");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityGondorMan gondorian = (LOTREntityGondorMan)entity;
        if (gondorian.familyInfo.isMale()) {
            if (gondorian instanceof LOTREntityGondorSoldier) {
                return skinsSoldier.getRandomSkin(gondorian);
            }
            return skinsMale.getRandomSkin(gondorian);
        }
        return skinsFemale.getRandomSkin(gondorian);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityGondorMan gondorian = (LOTREntityGondorMan)entity;
        if (pass == 1 && gondorian.getEquipmentInSlot(3) == null && LOTRRandomSkins.nextInt(gondorian, 4) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(outfits.getRandomSkin(gondorian));
            return 1;
        }
        if (pass == 0 && gondorian.getEquipmentInSlot(4) == null && !gondorian.familyInfo.isMale() && LOTRRandomSkins.nextInt(gondorian, 4) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(headwearFemale.getRandomSkin(gondorian));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)gondorian, pass, f);
    }
}

