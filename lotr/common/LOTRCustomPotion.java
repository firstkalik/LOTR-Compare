/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class LOTRCustomPotion
extends Potion {
    protected ResourceLocation texture;

    protected LOTRCustomPotion(int id, boolean isBad, int fluidColor, ResourceLocation tex, String namePot) {
        super(id, isBad, fluidColor);
        this.setPotionName(namePot);
        this.texture = tex;
    }

    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        return true;
    }

    public ResourceLocation getIcon() {
        return this.texture;
    }

    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.renderEngine.bindTexture(this.texture);
        LOTRCustomPotion.drawTexturedRect(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    public static void drawTexturedRect(int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight) {
        float f = 1.0f / (float)textureWidth;
        float f1 = 1.0f / (float)textureHeight;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)x, (double)(y + height), 0.0, (double)((float)u * f), (double)((float)(v + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), 0.0, (double)((float)(u + width) * f), (double)((float)(v + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)y, 0.0, (double)((float)(u + width) * f), (double)((float)v * f1));
        tessellator.addVertexWithUV((double)x, (double)y, 0.0, (double)((float)u * f), (double)((float)v * f1));
        tessellator.draw();
    }

    public Potion setIconIndex(int par1, int par2) {
        super.setIconIndex(par1, par2);
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        return super.getStatusIconIndex();
    }
}

