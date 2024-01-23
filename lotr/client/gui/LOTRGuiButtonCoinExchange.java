/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.client.gui.LOTRGuiCoinExchange;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonCoinExchange
extends GuiButton {
    public LOTRGuiButtonCoinExchange(int i, int j, int k) {
        super(i, j, k, 32, 17, "");
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(LOTRGuiCoinExchange.guiTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            int u = 176 + this.id * this.width;
            int v = 0 + k * this.height;
            this.drawTexturedModalRect(this.xPosition, this.yPosition, u, v, this.width, this.height);
            this.mouseDragged(mc, i, j);
        }
    }
}

