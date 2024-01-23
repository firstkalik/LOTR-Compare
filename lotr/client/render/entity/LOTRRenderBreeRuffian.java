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

import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBreeMan;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityBreeRuffian;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderBreeRuffian
extends LOTRRenderBreeMan {
    private static LOTRRandomSkins skinsRuffian;
    private static LOTRRandomSkins hoodsRuffian;

    public LOTRRenderBreeRuffian() {
        skinsRuffian = LOTRRandomSkins.loadSkinsList("lotr:mob/bree/ruffian");
        hoodsRuffian = LOTRRandomSkins.loadSkinsList("lotr:mob/bree/ruffian_hood");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBreeRuffian ruffian = (LOTREntityBreeRuffian)entity;
        return skinsRuffian.getRandomSkin(ruffian);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityBreeRuffian ruffian = (LOTREntityBreeRuffian)entity;
        if (pass == 0 && ruffian.getEquipmentInSlot(4) == null && LOTRRandomSkins.nextInt(ruffian, 3) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(hoodsRuffian.getRandomSkin(ruffian));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)ruffian, pass, f);
    }
}

