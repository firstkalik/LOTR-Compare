/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.awt.Color;
import lotr.client.LOTRTextures;
import lotr.client.model.LOTRModelElf;
import lotr.client.render.entity.LOTRGlowingEyes;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityAvariElf;
import lotr.common.entity.npc.LOTREntityDorwinionElf;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTREntityHighElf;
import lotr.common.entity.npc.LOTREntityRivendellElf;
import lotr.common.entity.npc.LOTREntityTormentedElf;
import lotr.common.entity.npc.LOTREntityWickedElf;
import lotr.common.entity.npc.LOTREntityWoodElf;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.item.LOTRItemRing;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderElf
extends LOTRRenderBiped {
    private static LOTRRandomSkins galadhrimSkinsMale;
    private static LOTRRandomSkins galadhrimSkinsFemale;
    private static LOTRRandomSkins woodElfSkinsMale;
    private static LOTRRandomSkins woodElfSkinsFemale;
    private static LOTRRandomSkins highElfSkinsMale;
    private static LOTRRandomSkins highElfSkinsFemale;
    private static LOTRRandomSkins dorwinionSkinsMale;
    private static LOTRRandomSkins dorwinionSkinsFemale;
    private static LOTRRandomSkins tormentedElfSkins;
    private static LOTRRandomSkins jazzSkinsMale;
    private static LOTRRandomSkins jazzSkinsFemale;
    private static LOTRRandomSkins jazzOutfits;
    private static LOTRRandomSkins avariElfSkinsMale;
    private static LOTRRandomSkins avariElfSkinsFemale;
    private static LOTRRandomSkins wickedElfSkins;
    private LOTRModelElf eyesModel = new LOTRModelElf(0.05f, 64, 64);
    private LOTRModelElf outfitModel = new LOTRModelElf(0.6f, 64, 64);

    public LOTRRenderElf() {
        super(new LOTRModelElf(), 0.5f);
        galadhrimSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/galadhrim_male");
        galadhrimSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/galadhrim_female");
        woodElfSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/woodElf_male");
        woodElfSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/woodElf_female");
        avariElfSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/avariElf_male");
        avariElfSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/avariElf_female");
        highElfSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/highElf_male");
        highElfSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/highElf_female");
        dorwinionSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/dorwinion_male");
        dorwinionSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/dorwinion_female");
        tormentedElfSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/tormented");
        jazzSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/jazz_male");
        jazzSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/jazz_female");
        jazzOutfits = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/jazz_outfit");
        wickedElfSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/elf/wicked_male");
    }

    @Override
    protected void func_82421_b() {
        this.field_82423_g = new LOTRModelElf(1.0f);
        this.field_82425_h = new LOTRModelElf(0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityElf elf = (LOTREntityElf)entity;
        boolean male = elf.familyInfo.isMale();
        if (elf.isJazz()) {
            if (male) {
                return jazzSkinsMale.getRandomSkin(elf);
            }
            return jazzSkinsFemale.getRandomSkin(elf);
        }
        if (elf instanceof LOTREntityTormentedElf) {
            return tormentedElfSkins.getRandomSkin(elf);
        }
        if (elf instanceof LOTREntityWickedElf) {
            return wickedElfSkins.getRandomSkin(elf);
        }
        if (elf instanceof LOTREntityDorwinionElf) {
            if (male) {
                return dorwinionSkinsMale.getRandomSkin(elf);
            }
            return dorwinionSkinsFemale.getRandomSkin(elf);
        }
        if (elf instanceof LOTREntityHighElf || elf instanceof LOTREntityRivendellElf) {
            if (male) {
                return highElfSkinsMale.getRandomSkin(elf);
            }
            return highElfSkinsFemale.getRandomSkin(elf);
        }
        if (elf instanceof LOTREntityWoodElf) {
            if (male) {
                return woodElfSkinsMale.getRandomSkin(elf);
            }
            return woodElfSkinsFemale.getRandomSkin(elf);
        }
        if (elf instanceof LOTREntityAvariElf) {
            if (male) {
                return avariElfSkinsMale.getRandomSkin(elf);
            }
            return avariElfSkinsFemale.getRandomSkin(elf);
        }
        if (male) {
            return galadhrimSkinsMale.getRandomSkin(elf);
        }
        return galadhrimSkinsFemale.getRandomSkin(elf);
    }

    protected void renderModel(EntityLivingBase entity, float f, float f1, float f2, float f3, float f4, float f5) {
        ResourceLocation eyes;
        super.renderModel(entity, f, f1, f2, f3, f4, f5);
        if (entity instanceof LOTREntityTormentedElf) {
            eyes = LOTRTextures.getEyesTexture(this.getEntityTexture((Entity)entity), new int[][]{{9, 12}, {13, 12}}, 2, 1);
            LOTRGlowingEyes.renderGlowingEyes((Entity)entity, eyes, this.eyesModel, f, f1, f2, f3, f4, f5);
        }
        if (entity instanceof LOTREntityWickedElf) {
            eyes = LOTRTextures.getEyesTexture(this.getEntityTexture((Entity)entity), new int[][]{{9, 12}, {13, 12}}, 2, 1);
            LOTRGlowingEyes.renderGlowingEyes((Entity)entity, eyes, this.eyesModel, f, f1, f2, f3, f4, f5);
        }
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityElf elf = (LOTREntityElf)entity;
        if (elf.isJazz() && pass == 0 && elf.getEquipmentInSlot(4) == null && LOTRRandomSkins.nextInt(elf, 2) == 0) {
            this.setRenderPassModel((ModelBase)this.outfitModel);
            this.bindTexture(jazzOutfits.getRandomSkin(elf));
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)elf, pass, f);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        super.preRenderCallback(entity, f);
        LOTREntityElf elf = (LOTREntityElf)entity;
        if (LOTRMod.isAprilFools()) {
            GL11.glScalef((float)0.25f, (float)0.25f, (float)0.25f);
        }
        if (elf.isJazz() && elf.isSolo()) {
            float hue = ((float)elf.ticksExisted + f) / 20.0f;
            float sat = 0.5f;
            Color color = Color.getHSBColor(hue %= 360.0f, sat, 1.0f);
            float r = (float)color.getRed() / 255.0f;
            float g = (float)color.getGreen() / 255.0f;
            float b = (float)color.getBlue() / 255.0f;
            GL11.glColor3f((float)r, (float)g, (float)b);
            float soloSpin = elf.getSoloSpin(f);
            GL11.glRotatef((float)soloSpin, (float)0.0f, (float)1.0f, (float)0.0f);
        }
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase entity, float f) {
        super.renderEquippedItems(entity, f);
        LOTREntityElf elf = (LOTREntityElf)entity;
        if (elf.isJazz() && elf.isSolo()) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.75f, (float)0.1f);
            GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            TextureManager texturemanager = this.renderManager.renderEngine;
            texturemanager.bindTexture(TextureMap.locationItemsTexture);
            TextureUtil.func_152777_a((boolean)false, (boolean)false, (float)1.0f);
            Tessellator tessellator = Tessellator.instance;
            IIcon icon = LOTRItemRing.saxIcon;
            float minU = icon.getMinU();
            float maxU = icon.getMaxU();
            float minV = icon.getMinV();
            float maxV = icon.getMaxV();
            GL11.glEnable((int)32826);
            ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)maxU, (float)minV, (float)minU, (float)maxV, (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.0625f);
            GL11.glDisable((int)32826);
            texturemanager.bindTexture(TextureMap.locationItemsTexture);
            TextureUtil.func_147945_b();
            GL11.glPopMatrix();
        }
    }
}

