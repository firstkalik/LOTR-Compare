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

public class LOTRGuiUnitTradeButton
extends GuiButton {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/npc/unit_trade_buttons.png");

    public LOTRGuiUnitTradeButton(int i, int j, int k, int width, int height) {
        super(i, j, k, width, height, "");
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(guiTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            boolean flag = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int k = this.id * 19;
            int l = 0;
            if (!this.enabled) {
                l += this.width * 2;
            } else if (flag) {
                l += this.width;
            }
            this.drawTexturedModalRect(this.xPosition, this.yPosition, l, k, this.width, this.height);
        }
    }
}

