/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.client.gui.LOTRGuiRedBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonRedBook
extends GuiButton {
    public LOTRGuiButtonRedBook(int i, int x, int y, int w, int h, String s) {
        super(i, x, y, w, h, s);
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(LOTRGuiRedBook.guiTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            Gui.func_146110_a((int)this.xPosition, (int)this.yPosition, (float)170.0f, (float)(256 + k * 20), (int)this.width, (int)this.height, (float)512.0f, (float)512.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            Gui.func_146110_a((int)this.xPosition, (int)this.yPosition, (float)170.0f, (float)316.0f, (int)(this.width / 2), (int)this.height, (float)512.0f, (float)512.0f);
            Gui.func_146110_a((int)(this.xPosition + this.width / 2), (int)this.yPosition, (float)(370 - this.width / 2), (float)316.0f, (int)(this.width / 2), (int)this.height, (float)512.0f, (float)512.0f);
            this.mouseDragged(mc, i, j);
            int color = 8019267;
            if (!this.enabled) {
                color = 5521198;
            } else if (this.field_146123_n) {
                color = 8019267;
            }
            fontrenderer.drawString(this.displayString, this.xPosition + this.width / 2 - fontrenderer.getStringWidth(this.displayString) / 2, this.yPosition + (this.height - 8) / 2, color);
        }
    }
}

