/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.common.inventory.LOTRContainerHobbitOven;
import lotr.common.tileentity.LOTRTileEntityHobbitOven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiHobbitOven
extends GuiContainer {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/oven.png");
    private LOTRTileEntityHobbitOven theOven;

    public LOTRGuiHobbitOven(InventoryPlayer inv, LOTRTileEntityHobbitOven oven) {
        super((Container)new LOTRContainerHobbitOven(inv, oven));
        this.theOven = oven;
        this.ySize = 215;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String s = this.theOven.getInventoryName();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, 121, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.theOven.isCooking()) {
            int k = this.theOven.getCookTimeRemainingScaled(12);
            this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 94 + 12 - k, 176, 12 - k, 14, k + 2);
        }
        int l = this.theOven.getCookProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 40, 176, 14, 16, l + 1);
    }
}

