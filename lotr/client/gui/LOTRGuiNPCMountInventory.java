/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.common.entity.npc.LOTREntityNPCRideable;
import lotr.common.inventory.LOTRContainerNPCMountInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiNPCMountInventory
extends GuiContainer {
    private static final ResourceLocation guiTexture = new ResourceLocation("textures/gui/container/horse.png");
    private IInventory thePlayerInv;
    private IInventory theMountInv;
    private LOTREntityNPCRideable theMount;
    private float mouseX;
    private float mouseY;

    public LOTRGuiNPCMountInventory(IInventory playerInv, IInventory mountInv, LOTREntityNPCRideable mount) {
        super((Container)new LOTRContainerNPCMountInventory(playerInv, mountInv, mount));
        this.thePlayerInv = playerInv;
        this.theMountInv = mountInv;
        this.theMount = mount;
        this.allowUserInput = false;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        this.fontRendererObj.drawString(this.theMountInv.hasCustomInventoryName() ? this.theMountInv.getInventoryName() : I18n.format((String)this.theMountInv.getInventoryName(), (Object[])new Object[0]), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.thePlayerInv.hasCustomInventoryName() ? this.thePlayerInv.getInventoryName() : I18n.format((String)this.thePlayerInv.getInventoryName(), (Object[])new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(k + 7, l + 35, 0, this.ySize + 54, 18, 18);
        GuiInventory.func_147046_a((int)(k + 51), (int)(l + 60), (int)17, (float)((float)(k + 51) - this.mouseX), (float)((float)(l + 75 - 50) - this.mouseY), (EntityLivingBase)this.theMount);
    }

    public void drawScreen(int i, int j, float f) {
        this.mouseX = i;
        this.mouseY = j;
        super.drawScreen(i, j, f);
    }
}

