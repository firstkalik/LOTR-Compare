/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelHuman;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderDunlendingBase;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityDunlending;
import lotr.common.entity.npc.LOTREntityDunlendingBartender;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDunlending
extends LOTRRenderDunlendingBase {
    private static LOTRRandomSkins dunlendingOutfits;
    private static ResourceLocation outfitApron;
    private ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderDunlending() {
        this.setRenderPassModel((ModelBase)this.outfitModel);
        dunlendingOutfits = LOTRRandomSkins.loadSkinsList("lotr:mob/dunland/outfit");
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityDunlending dunlending = (LOTREntityDunlending)entity;
        if (pass == 1 && dunlending.getEquipmentInSlot(3) == null) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            if (dunlending instanceof LOTREntityDunlendingBartender) {
                this.bindTexture(outfitApron);
            } else {
                this.bindTexture(dunlendingOutfits.getRandomSkin(dunlending));
            }
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)dunlending, pass, f);
    }

    static {
        outfitApron = new ResourceLocation("lotr:mob/dunland/bartender_apron.png");
    }
}

