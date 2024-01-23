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
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderBlacksmith
extends LOTRRenderBiped {
    private LOTRRandomSkins skins;
    private ResourceLocation apron;
    private ModelBiped standardRenderPassModel = new LOTRModelHuman(0.5f, false);

    public LOTRRenderBlacksmith(String s, String s1) {
        super(new LOTRModelHuman(), 0.5f);
        this.skins = LOTRRandomSkins.loadSkinsList("lotr:mob/" + s);
        this.apron = new ResourceLocation("lotr:mob/" + s1 + ".png");
        this.setRenderPassModel((ModelBase)this.standardRenderPassModel);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return this.skins.getRandomSkin((LOTREntityNPC)entity);
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        if (pass == 1) {
            this.setRenderPassModel((ModelBase)this.standardRenderPassModel);
            this.bindTexture(this.apron);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }
}

