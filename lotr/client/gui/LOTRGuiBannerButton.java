/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiBannerButton
extends GuiButton {
    public static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/banner_edit.png");

    public LOTRGuiBannerButton(int i, int j, int k) {
        super(i, j, k, 7, 7, "");
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(guiTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            boolean over = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int k = 226 + this.id * this.width;
            int l = this.getHoverState(over) * this.width;
            this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, this.width, this.height);
        }
    }
}

