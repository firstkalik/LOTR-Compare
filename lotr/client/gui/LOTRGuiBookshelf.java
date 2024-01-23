/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.common.inventory.LOTRContainerBookshelf;
import lotr.common.tileentity.LOTRTileEntityBookshelf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiBookshelf
extends GuiContainer {
    private static final ResourceLocation chestTexture = new ResourceLocation("textures/gui/container/generic_54.png");
    private IInventory playerInv;
    private IInventory shelfInv;
    private int inventoryRows;

    public LOTRGuiBookshelf(IInventory player, LOTRTileEntityBookshelf shelf) {
        super((Container)new LOTRContainerBookshelf(player, shelf));
        this.playerInv = player;
        this.shelfInv = shelf;
        this.allowUserInput = false;
        int i = 222;
        int j = i - 108;
        this.inventoryRows = shelf.getSizeInventory() / 9;
        this.ySize = j + this.inventoryRows * 18;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        this.fontRendererObj.drawString(this.shelfInv.hasCustomInventoryName() ? this.shelfInv.getInventoryName() : StatCollector.translateToLocal((String)this.shelfInv.getInventoryName()), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : StatCollector.translateToLocal((String)this.playerInv.getInventoryName()), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(chestTexture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
}

