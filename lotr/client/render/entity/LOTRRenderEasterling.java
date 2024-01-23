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
import lotr.common.entity.npc.LOTREntityEasterling;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderEasterling
extends LOTRRenderBiped {
    private static LOTRRandomSkins easterlingSkinsMale;
    private static LOTRRandomSkins easterlingSkinsFemale;
    protected ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderEasterling() {
        super(new LOTRModelHuman(), 0.5f);
        this.setRenderPassModel((ModelBase)this.outfitModel);
        easterlingSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/rhun/easterling_male");
        easterlingSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/rhun/easterling_female");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityEasterling easterling = (LOTREntityEasterling)entity;
        if (easterling.familyInfo.isMale()) {
            return easterlingSkinsMale.getRandomSkin(easterling);
        }
        return easterlingSkinsFemale.getRandomSkin(easterling);
    }
}

