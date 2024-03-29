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
import lotr.common.entity.npc.LOTREntityCorsair;
import lotr.common.entity.npc.LOTREntityGulfHaradWarrior;
import lotr.common.entity.npc.LOTREntityGulfHaradrim;
import lotr.common.entity.npc.LOTREntityHarnedhrim;
import lotr.common.entity.npc.LOTREntityHarnedorWarrior;
import lotr.common.entity.npc.LOTREntityNearHaradrimBase;
import lotr.common.entity.npc.LOTREntityNearHaradrimWarrior;
import lotr.common.entity.npc.LOTREntityNomad;
import lotr.common.entity.npc.LOTREntityUmbarWarrior;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderNearHaradrim
extends LOTRRenderBiped {
    private static LOTRRandomSkins haradrimSkinsMale;
    private static LOTRRandomSkins haradrimSkinsFemale;
    private static LOTRRandomSkins warriorSkins;
    private static LOTRRandomSkins harnedorSkinsMale;
    private static LOTRRandomSkins harnedorSkinsFemale;
    private static LOTRRandomSkins harnedorWarriorSkins;
    private static LOTRRandomSkins harnedorOutfits;
    private static LOTRRandomSkins nomadSkinsMale;
    private static LOTRRandomSkins nomadSkinsFemale;
    private static LOTRRandomSkins nomadHats;
    protected ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderNearHaradrim() {
        super(new LOTRModelHuman(), 0.5f);
        this.setRenderPassModel((ModelBase)this.outfitModel);
        haradrimSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/haradrim_male");
        haradrimSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/haradrim_female");
        warriorSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/warrior");
        harnedorSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/harnedor_male");
        harnedorSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/harnedor_female");
        harnedorWarriorSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/harnedorWarrior");
        harnedorOutfits = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/harnedor_outfit");
        nomadSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/nomad_male");
        nomadSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/nomad_female");
        nomadHats = LOTRRandomSkins.loadSkinsList("lotr:mob/nearHarad/nomad_hat");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityNearHaradrimBase haradrim = (LOTREntityNearHaradrimBase)entity;
        if (haradrim instanceof LOTREntityHarnedhrim || haradrim instanceof LOTREntityGulfHaradrim || haradrim instanceof LOTREntityCorsair) {
            if (haradrim instanceof LOTREntityHarnedorWarrior || haradrim instanceof LOTREntityGulfHaradWarrior) {
                return harnedorWarriorSkins.getRandomSkin(haradrim);
            }
            if (haradrim.familyInfo.isMale()) {
                return harnedorSkinsMale.getRandomSkin(haradrim);
            }
            return harnedorSkinsFemale.getRandomSkin(haradrim);
        }
        if (haradrim instanceof LOTREntityNomad) {
            if (haradrim.familyInfo.isMale()) {
                return nomadSkinsMale.getRandomSkin(haradrim);
            }
            return nomadSkinsFemale.getRandomSkin(haradrim);
        }
        if (haradrim instanceof LOTREntityNearHaradrimWarrior || haradrim instanceof LOTREntityUmbarWarrior) {
            return warriorSkins.getRandomSkin(haradrim);
        }
        if (haradrim.familyInfo.isMale()) {
            return haradrimSkinsMale.getRandomSkin(haradrim);
        }
        return haradrimSkinsFemale.getRandomSkin(haradrim);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityNearHaradrimBase haradrim = (LOTREntityNearHaradrimBase)entity;
        if ((haradrim instanceof LOTREntityHarnedhrim || haradrim instanceof LOTREntityGulfHaradrim) && pass == 1 && haradrim.getEquipmentInSlot(3) == null && LOTRRandomSkins.nextInt(haradrim, 2) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(harnedorOutfits.getRandomSkin(haradrim));
            return 1;
        }
        if (haradrim instanceof LOTREntityNomad && pass == 0 && haradrim.getEquipmentInSlot(4) == null && LOTRRandomSkins.nextInt(haradrim, 2) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(nomadHats.getRandomSkin(haradrim));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)haradrim, pass, f);
    }
}

