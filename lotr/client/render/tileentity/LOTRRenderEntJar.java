/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import java.awt.Color;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityEntJar;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderEntJar
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntityEntJar jar = (LOTRTileEntityEntJar)tileentity;
        if (jar.drinkAmount <= 0) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1), (float)((float)d2 + 0.5f));
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        float transparency = 0.5f;
        Tessellator tessellator = Tessellator.instance;
        IIcon icon = null;
        float minU = 0.0f;
        float maxU = 0.0f;
        float minV = 0.0f;
        float maxV = 0.0f;
        int color = 16777215;
        if (jar.drinkMeta >= 0) {
            this.bindTexture(TextureMap.locationItemsTexture);
            ItemStack drink = new ItemStack(LOTRMod.entDraught, 1, jar.drinkMeta);
            icon = drink.getIconIndex();
            minU = icon.getInterpolatedU(7.0);
            maxU = icon.getInterpolatedU(8.0);
            minV = icon.getInterpolatedV(7.0);
            maxV = icon.getInterpolatedV(8.0);
        } else {
            this.bindTexture(TextureMap.locationBlocksTexture);
            icon = Blocks.water.getBlockTextureFromSide(1);
            minU = icon.getInterpolatedU(0.0);
            maxU = icon.getInterpolatedU(6.0);
            minV = icon.getInterpolatedV(0.0);
            maxV = icon.getInterpolatedV(6.0);
            color = Blocks.water.colorMultiplier((IBlockAccess)jar.getWorldObj(), jar.xCoord, jar.yCoord, jar.zCoord);
        }
        double d3 = 0.1875;
        double d4 = -0.0625 - 0.75 * (double)jar.drinkAmount / (double)LOTRTileEntityEntJar.MAX_CAPACITY;
        tessellator.startDrawingQuads();
        float[] colors = new Color(color).getColorComponents(null);
        tessellator.setColorRGBA_F(colors[0], colors[1], colors[2], transparency);
        tessellator.addVertexWithUV(-d3, d4, d3, (double)minU, (double)maxV);
        tessellator.addVertexWithUV(d3, d4, d3, (double)maxU, (double)maxV);
        tessellator.addVertexWithUV(d3, d4, -d3, (double)maxU, (double)minV);
        tessellator.addVertexWithUV(-d3, d4, -d3, (double)minU, (double)minV);
        tessellator.draw();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)32826);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }
}

