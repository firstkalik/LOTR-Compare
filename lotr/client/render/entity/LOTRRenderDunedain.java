/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelHuman;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityDunedain;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDunedain
extends LOTRRenderBiped {
    private static LOTRRandomSkins skinsMale;
    private static LOTRRandomSkins skinsFemale;
    protected ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderDunedain() {
        super(new LOTRModelHuman(), 0.5f);
        this.setRenderPassModel((ModelBase)this.outfitModel);
        skinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/ranger/ranger_male");
        skinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/ranger/ranger_female");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityDunedain dunedain = (LOTREntityDunedain)entity;
        if (dunedain.familyInfo.isMale()) {
            return skinsMale.getRandomSkin(dunedain);
        }
        return skinsFemale.getRandomSkin(dunedain);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityDunedain dunedain = (LOTREntityDunedain)entity;
        return super.shouldRenderPass((EntityLiving)dunedain, pass, f);
    }
}

