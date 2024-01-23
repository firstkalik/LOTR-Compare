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

import lotr.common.inventory.LOTRContainerArmorStand;
import lotr.common.tileentity.LOTRTileEntityArmorStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiArmorStand
extends GuiContainer {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/armor_stand.png");
    private LOTRTileEntityArmorStand theArmorStand;

    public LOTRGuiArmorStand(InventoryPlayer inv, LOTRTileEntityArmorStand armorStand) {
        super((Container)new LOTRContainerArmorStand(inv, armorStand));
        this.theArmorStand = armorStand;
        this.ySize = 189;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String s = this.theArmorStand.getInventoryName();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, 95, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}

