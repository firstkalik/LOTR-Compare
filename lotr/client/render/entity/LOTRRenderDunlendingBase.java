/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelHuman;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.client.render.entity.LOTRRenderOrcBomb;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.item.LOTREntityOrcBomb;
import lotr.common.entity.npc.LOTREntityDunlending;
import lotr.common.entity.npc.LOTREntityDunlendingBerserker;
import lotr.common.entity.npc.LOTRFamilyInfo;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderDunlendingBase
extends LOTRRenderBiped {
    private static LOTRRandomSkins dunlendingSkinsMale;
    private static LOTRRandomSkins dunlendingSkinsFemale;
    private static LOTRRandomSkins dunlendingSkinsBerserker;

    public LOTRRenderDunlendingBase() {
        super(new LOTRModelHuman(), 0.5f);
        dunlendingSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/dunland/dunlending_male");
        dunlendingSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/dunland/dunlending_female");
        dunlendingSkinsBerserker = LOTRRandomSkins.loadSkinsList("lotr:mob/dunland/berserker");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityDunlending dunlending = (LOTREntityDunlending)entity;
        if (dunlending.familyInfo.isMale()) {
            if (dunlending instanceof LOTREntityDunlendingBerserker) {
                return dunlendingSkinsBerserker.getRandomSkin(dunlending);
            }
            return dunlendingSkinsMale.getRandomSkin(dunlending);
        }
        return dunlendingSkinsFemale.getRandomSkin(dunlending);
    }

    @Override
    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityDunlending dunlending = (LOTREntityDunlending)entity;
        ItemStack helmet = dunlending.getEquipmentInSlot(4);
        if (helmet != null && helmet.getItem() == Item.getItemFromBlock((Block)LOTRMod.orcBomb)) {
            GL11.glEnable((int)32826);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d), (float)((float)(d1 += 0.5) + 2.5f), (float)((float)d2));
            GL11.glRotatef((float)(-f), (float)0.0f, (float)1.0f, (float)0.0f);
            int i = entity.getBrightnessForRender(f1);
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            LOTRRenderOrcBomb bombRenderer = (LOTRRenderOrcBomb)RenderManager.instance.getEntityClassRenderObject(LOTREntityOrcBomb.class);
            bombRenderer.renderBomb((Entity)entity, 0.0, 0.0, 0.0, f1, 5, 0, 0.75f, 1.0f);
            GL11.glPopMatrix();
            GL11.glDisable((int)32826);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        super.doRender(entity, d, d1, d2, f, f1);
    }
}

