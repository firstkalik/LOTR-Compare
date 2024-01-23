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
import lotr.common.entity.npc.LOTREntityMoredain;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderMoredain
extends LOTRRenderBiped {
    private static LOTRRandomSkins skinsMale;
    private static LOTRRandomSkins skinsFemale;
    private static LOTRRandomSkins outfits;
    private ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderMoredain() {
        super(new LOTRModelHuman(), 0.5f);
        skinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/moredain/moredain_male");
        skinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/moredain/moredain_female");
        outfits = LOTRRandomSkins.loadSkinsList("lotr:mob/moredain/outfit");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityMoredain moredain = (LOTREntityMoredain)entity;
        if (moredain.familyInfo.isMale()) {
            return skinsMale.getRandomSkin(moredain);
        }
        return skinsFemale.getRandomSkin(moredain);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityMoredain moredain = (LOTREntityMoredain)entity;
        if (pass == 1 && moredain.getEquipmentInSlot(3) == null && LOTRRandomSkins.nextInt(moredain, 3) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(outfits.getRandomSkin(moredain));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)moredain, pass, f);
    }
}

