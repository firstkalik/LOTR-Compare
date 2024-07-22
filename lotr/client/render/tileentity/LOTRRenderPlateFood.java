/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import java.util.Random;
import lotr.common.entity.LOTRPlateFallingInfo;
import lotr.common.tileentity.LOTRTileEntityPlate;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderPlateFood
extends TileEntitySpecialRenderer {
    private Random rand = new Random();

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntityPlate plate = (LOTRTileEntityPlate)tileentity;
        ItemStack plateItem = plate.getFoodItem();
        LOTRPlateFallingInfo fallInfo = plate.plateFallInfo;
        if (plateItem != null) {
            GL11.glPushMatrix();
            GL11.glDisable((int)2884);
            GL11.glEnable((int)32826);
            GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1), (float)((float)d2 + 0.5f));
            this.bindTexture(TextureMap.locationItemsTexture);
            IIcon icon = plateItem.getIconIndex();
            Tessellator tessellator = Tessellator.instance;
            float f4 = icon.getMinU();
            float f1 = icon.getMaxU();
            float f22 = icon.getMinV();
            float f3 = icon.getMaxV();
            int foods = plateItem.stackSize;
            float lowerOffset = 0.125f;
            for (int l = 0; l < foods; ++l) {
                GL11.glPushMatrix();
                float offset = 0.0f;
                if (fallInfo != null) {
                    offset = fallInfo.getFoodOffsetY(l, f);
                }
                offset = Math.max(offset, lowerOffset);
                GL11.glTranslatef((float)0.0f, (float)offset, (float)0.0f);
                lowerOffset = offset + 0.03125f;
                this.rand.setSeed((long)(plate.xCoord * 3129871) ^ (long)plate.zCoord * 116129781L ^ (long)plate.yCoord + (long)l * 5930563L);
                float rotation = this.rand.nextFloat() * 360.0f;
                GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.25f, (float)-0.25f, (float)0.0f);
                GL11.glScalef((float)0.5625f, (float)0.5625f, (float)0.5625f);
                ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)f1, (float)f22, (float)f4, (float)f3, (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.0625f);
                GL11.glPopMatrix();
            }
            GL11.glDisable((int)32826);
            GL11.glEnable((int)2884);
            GL11.glPopMatrix();
        }
    }
}

