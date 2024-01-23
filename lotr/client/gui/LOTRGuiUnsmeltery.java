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

import lotr.common.inventory.LOTRContainerUnsmeltery;
import lotr.common.tileentity.LOTRTileEntityUnsmeltery;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiUnsmeltery
extends GuiContainer {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/unsmelter.png");
    private LOTRTileEntityUnsmeltery theUnsmeltery;

    public LOTRGuiUnsmeltery(InventoryPlayer inv, LOTRTileEntityUnsmeltery unsmeltery) {
        super((Container)new LOTRContainerUnsmeltery(inv, unsmeltery));
        this.theUnsmeltery = unsmeltery;
        this.ySize = 176;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String s = this.theUnsmeltery.getInventoryName();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, 72, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.theUnsmeltery.isSmelting()) {
            int k = this.theUnsmeltery.getSmeltTimeRemainingScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }
        int l = this.theUnsmeltery.getSmeltProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 34, 176, 14, l + 1, 16);
    }
}

