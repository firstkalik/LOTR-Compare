/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.client.model.LOTRModelWeaponRack;
import lotr.client.render.item.LOTRRenderBow;
import lotr.common.tileentity.LOTRTileEntityWeaponRack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class LOTRRenderWeaponRack
extends TileEntitySpecialRenderer {
    private static ResourceLocation rackTexture = new ResourceLocation("lotr:item/weaponRack.png");
    private static LOTRModelWeaponRack rackModel = new LOTRModelWeaponRack();

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntityWeaponRack weaponRack = (LOTRTileEntityWeaponRack)tileentity;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
        int meta = weaponRack.getBlockMetadata();
        int dir = meta & 3;
        boolean wall = (meta & 4) != 0;
        switch (dir) {
            case 0: {
                GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 1: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 2: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        if (wall) {
            GL11.glTranslatef((float)0.0f, (float)0.375f, (float)-0.5f);
        }
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        float scale = 0.0625f;
        this.bindTexture(rackTexture);
        LOTRRenderWeaponRack.rackModel.onWall = wall;
        rackModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, scale);
        ItemStack weaponItem = weaponRack.getWeaponItem();
        if (weaponItem != null) {
            float weaponScale = 0.625f;
            GL11.glScalef((float)weaponScale, (float)weaponScale, (float)weaponScale);
            GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)0.52f, (float)0.0f);
            if (wall) {
                GL11.glTranslatef((float)0.0f, (float)1.1f, (float)0.51f);
            }
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.9375f, (float)0.0625f, (float)0.0f);
            GL11.glRotatef((float)-335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)0.6666667f, (float)0.6666667f, (float)0.6666667f);
            GL11.glTranslatef((float)0.0f, (float)0.3f, (float)0.0f);
            RenderManager renderManager = RenderManager.instance;
            int passes = 1;
            if (weaponItem.getItem().requiresMultipleRenderPasses()) {
                passes = weaponItem.getItem().getRenderPasses(weaponItem.getItemDamage());
            }
            LOTRRenderBow.renderingWeaponRack = true;
            for (int pass = 0; pass < passes; ++pass) {
                int color = weaponItem.getItem().getColorFromItemStack(weaponItem, pass);
                float r = (float)(color >> 16 & 0xFF) / 255.0f;
                float g = (float)(color >> 8 & 0xFF) / 255.0f;
                float b = (float)(color & 0xFF) / 255.0f;
                GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
                renderManager.itemRenderer.renderItem(weaponRack.getEntityForRender(), weaponItem, 0, IItemRenderer.ItemRenderType.EQUIPPED);
            }
            LOTRRenderBow.renderingWeaponRack = false;
        }
        GL11.glEnable((int)2884);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        this.renderWeaponName(weaponRack, d + 0.5, d1 + 0.75, d2 + 0.5);
    }

    private void renderWeaponName(LOTRTileEntityWeaponRack rack, double d, double d1, double d2) {
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && mop.blockX == rack.xCoord && mop.blockY == rack.yCoord && mop.blockZ == rack.zCoord) {
            ItemStack weaponItem = rack.getWeaponItem();
            if (Minecraft.isGuiEnabled() && weaponItem != null && weaponItem.hasDisplayName()) {
                float f2;
                RenderManager renderManager = RenderManager.instance;
                FontRenderer fontRenderer = this.func_147498_b();
                float f = 1.6f;
                float f1 = 0.016666668f * f;
                double dSq = renderManager.livingPlayer.getDistanceSq((double)rack.xCoord + 0.5, (double)rack.yCoord + 0.5, (double)rack.zCoord);
                if (dSq < (double)((f2 = 64.0f) * f2)) {
                    String name = weaponItem.getDisplayName();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)((float)d), (float)((float)d1 + 0.5f), (float)((float)d2));
                    GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)renderManager.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
                    GL11.glDisable((int)2896);
                    GL11.glDepthMask((boolean)false);
                    GL11.glDisable((int)2929);
                    GL11.glEnable((int)3042);
                    OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
                    Tessellator tessellator = Tessellator.instance;
                    int b0 = 0;
                    GL11.glDisable((int)3553);
                    tessellator.startDrawingQuads();
                    int j = fontRenderer.getStringWidth(name) / 2;
                    tessellator.setColorRGBA_F(0.0f, 0.0f, 0.0f, 0.25f);
                    tessellator.addVertex((double)(-j - 1), (double)(-1 + b0), 0.0);
                    tessellator.addVertex((double)(-j - 1), (double)(8 + b0), 0.0);
                    tessellator.addVertex((double)(j + 1), (double)(8 + b0), 0.0);
                    tessellator.addVertex((double)(j + 1), (double)(-1 + b0), 0.0);
                    tessellator.draw();
                    GL11.glEnable((int)3553);
                    fontRenderer.drawString(name, -fontRenderer.getStringWidth(name) / 2, b0, 553648127);
                    GL11.glEnable((int)2929);
                    GL11.glDepthMask((boolean)true);
                    fontRenderer.drawString(name, -fontRenderer.getStringWidth(name) / 2, b0, -1);
                    GL11.glEnable((int)2896);
                    GL11.glDisable((int)3042);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glPopMatrix();
                }
            }
        }
    }
}

