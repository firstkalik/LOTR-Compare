/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.item;

import lotr.client.LOTRClientProxy;
import lotr.client.render.item.LOTRRenderBow;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemCrossbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class LOTRRenderCrossbow
implements IItemRenderer {
    public boolean handleRenderType(ItemStack itemstack, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack itemstack, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemstack, Object ... data) {
        RotationMode rotationMode = null;
        EntityLivingBase holder = (EntityLivingBase)data[1];
        boolean loaded = LOTRItemCrossbow.isLoaded(itemstack);
        boolean using = false;
        if (holder instanceof EntityPlayer) {
            using = ((EntityPlayer)holder).getItemInUse() == itemstack;
        } else if (holder instanceof EntityLiving) {
            boolean bl = using = ((EntityLiving)holder).getHeldItem() == itemstack;
            if (using && holder instanceof LOTREntityNPC) {
                using = ((LOTREntityNPC)holder).clientCombatStance;
            }
        }
        if (LOTRRenderBow.renderingWeaponRack) {
            rotationMode = RotationMode.FIRST_PERSON_HOLDING;
        } else if (holder == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            rotationMode = using || loaded ? RotationMode.FIRST_PERSON_LOADED : RotationMode.FIRST_PERSON_HOLDING;
        } else {
            rotationMode = using || loaded ? RotationMode.ENTITY_LOADED : RotationMode.ENTITY_HOLDING;
            GL11.glTranslatef((float)0.9375f, (float)0.0625f, (float)0.0f);
            GL11.glRotatef((float)-335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)0.6666667f, (float)0.6666667f, (float)0.6666667f);
            GL11.glTranslatef((float)0.0f, (float)0.3f, (float)0.0f);
            GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)2.6666667f, (float)2.6666667f, (float)2.6666667f);
            GL11.glTranslatef((float)-0.25f, (float)-0.1875f, (float)0.1875f);
        }
        if (rotationMode == RotationMode.FIRST_PERSON_LOADED) {
            GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)-25.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.5f);
        } else if (rotationMode == RotationMode.ENTITY_HOLDING) {
            GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
            GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)0.625f, (float)-0.625f, (float)0.625f);
            GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)-0.3f, (float)0.0f);
            GL11.glScalef((float)1.625f, (float)1.625f, (float)1.625f);
            GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
        } else if (rotationMode == RotationMode.ENTITY_LOADED) {
            GL11.glRotatef((float)50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.15f);
            GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
        }
        IIcon icon = ((EntityLivingBase)data[1]).getItemIcon(itemstack, 0);
        if (icon == null) {
            GL11.glPopMatrix();
            return;
        }
        Minecraft.getMinecraft().getTextureManager();
        Tessellator tessellator = Tessellator.instance;
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.0625f);
        if (itemstack != null && itemstack.hasEffect(0)) {
            LOTRClientProxy.renderEnchantmentEffect();
        }
        GL11.glDisable((int)32826);
    }

    private static enum RotationMode {
        FIRST_PERSON_HOLDING,
        FIRST_PERSON_LOADED,
        ENTITY_HOLDING,
        ENTITY_LOADED;

    }

}

