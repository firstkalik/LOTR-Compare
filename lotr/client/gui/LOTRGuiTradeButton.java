/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.client.gui.LOTRGuiTrade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiTradeButton
extends GuiButton {
    public LOTRGuiTradeButton(int i, int j, int k) {
        super(i, j, k, 18, 18, "Trade");
    }

    public void drawButton(Minecraft mc, int i, int j) {
        GL11.glDisable((int)2896);
        mc.getTextureManager().bindTexture(LOTRGuiTrade.guiTexture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146123_n = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
        int hoverState = this.getHoverState(this.field_146123_n);
        Gui.func_146110_a((int)this.xPosition, (int)this.yPosition, (float)176.0f, (float)(hoverState * 18), (int)this.width, (int)this.height, (float)512.0f, (float)512.0f);
        this.mouseDragged(mc, i, j);
        GL11.glEnable((int)2896);
    }
}

