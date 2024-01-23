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

import lotr.common.inventory.LOTRContainerMillstone;
import lotr.common.tileentity.LOTRTileEntityMillstone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiMillstone
extends GuiContainer {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/millstone.png");
    private LOTRTileEntityMillstone theMillstone;

    public LOTRGuiMillstone(InventoryPlayer inv, LOTRTileEntityMillstone millstone) {
        super((Container)new LOTRContainerMillstone(inv, millstone));
        this.theMillstone = millstone;
        this.ySize = 182;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String s = this.theMillstone.getInventoryName();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, 88, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.theMillstone.isMilling()) {
            int k = this.theMillstone.getMillProgressScaled(14);
            this.drawTexturedModalRect(this.guiLeft + 85, this.guiTop + 47, 176, 0, 14, k);
        }
    }
}

