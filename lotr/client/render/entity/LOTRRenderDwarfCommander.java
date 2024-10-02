/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelDwarf;
import lotr.client.render.entity.LOTRRenderDwarf;
import lotr.common.entity.npc.LOTREntityBlacklock;
import lotr.common.entity.npc.LOTREntityBlueDwarf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDwarfCommander
extends LOTRRenderDwarf {
    private static ResourceLocation cloak = new ResourceLocation("lotr:mob/dwarf/commander_cloak.png");
    private static ResourceLocation blueCloak = new ResourceLocation("lotr:mob/dwarf/blueMountains_commander_cloak.png");
    private static ResourceLocation redCloak = new ResourceLocation("lotr:mob/dwarf/commander_cloak.png");
    private LOTRModelDwarf cloakModel = new LOTRModelDwarf(1.5f);

    protected ResourceLocation getCloakTexture(EntityLivingBase entity) {
        return entity instanceof LOTREntityBlueDwarf ? blueCloak : cloak;
    }

    protected ResourceLocation getCloakTexture1(EntityLivingBase entity) {
        return entity instanceof LOTREntityBlacklock ? redCloak : cloak;
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        if (pass == 0) {
            this.bindTexture(this.getCloakTexture((EntityLivingBase)entity));
            this.cloakModel.bipedHead.showModel = false;
            this.cloakModel.bipedHeadwear.showModel = false;
            this.cloakModel.bipedBody.showModel = true;
            this.cloakModel.bipedRightArm.showModel = true;
            this.cloakModel.bipedLeftArm.showModel = true;
            this.cloakModel.bipedRightLeg.showModel = false;
            this.cloakModel.bipedLeftLeg.showModel = false;
            this.setRenderPassModel((ModelBase)this.cloakModel);
            this.cloakModel.onGround = this.mainModel.onGround;
            this.cloakModel.isRiding = this.mainModel.isRiding;
            this.cloakModel.isChild = this.mainModel.isChild;
            this.cloakModel.heldItemRight = this.modelBipedMain.heldItemRight;
        } else if (pass == 1) {
            this.bindTexture(this.getCloakTexture((EntityLivingBase)entity));
            this.modelBipedMain.bipedHead.showModel = true;
            this.modelBipedMain.bipedHeadwear.showModel = true;
            this.modelBipedMain.bipedBody.showModel = true;
            this.modelBipedMain.bipedRightArm.showModel = true;
            this.modelBipedMain.bipedLeftArm.showModel = true;
            this.modelBipedMain.bipedRightLeg.showModel = true;
            this.modelBipedMain.bipedLeftLeg.showModel = true;
            this.setRenderPassModel((ModelBase)this.modelBipedMain);
            this.modelBipedMain.onGround = this.mainModel.onGround;
            this.modelBipedMain.isRiding = this.mainModel.isRiding;
            this.modelBipedMain.isChild = this.mainModel.isChild;
            this.modelBipedMain.heldItemRight = this.mainModel.textureHeight;
        }
        return super.shouldRenderPass(entity, pass, f);
    }
}

