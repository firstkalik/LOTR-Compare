/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelHuman;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityDaleLevyman;
import lotr.common.entity.npc.LOTREntityDaleMan;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDaleMan
extends LOTRRenderBiped {
    private static LOTRRandomSkins skinsMale;
    private static LOTRRandomSkins skinsFemale;
    private static LOTRRandomSkins skinsSoldier;
    protected ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderDaleMan() {
        super(new LOTRModelHuman(), 0.5f);
        this.setRenderPassModel((ModelBase)this.outfitModel);
        skinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/dale/dale_male");
        skinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/dale/dale_female");
        skinsSoldier = LOTRRandomSkins.loadSkinsList("lotr:mob/dale/dale_soldier");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityDaleMan man = (LOTREntityDaleMan)entity;
        if (man.familyInfo.isMale()) {
            if (man instanceof LOTREntityDaleLevyman) {
                return skinsSoldier.getRandomSkin(man);
            }
            return skinsMale.getRandomSkin(man);
        }
        return skinsFemale.getRandomSkin(man);
    }
}

