/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import java.util.List;
import lotr.common.inventory.LOTRContainerDaleCracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiDaleCracker
extends GuiContainer {
    private static ResourceLocation texture = new ResourceLocation("lotr:gui/daleCracker.png");
    private LOTRContainerDaleCracker theCracker;
    private GuiButton buttonSeal;

    public LOTRGuiDaleCracker(EntityPlayer entityplayer) {
        super((Container)new LOTRContainerDaleCracker(entityplayer));
        this.theCracker = (LOTRContainerDaleCracker)this.inventorySlots;
    }

    public void initGui() {
        super.initGui();
        this.buttonSeal = new GuiButton(0, this.guiLeft + this.xSize / 2 - 40, this.guiTop + 48, 80, 20, StatCollector.translateToLocal((String)"lotr.gui.daleCracker.seal"));
        this.buttonList.add(this.buttonSeal);
        this.buttonSeal.enabled = false;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String s = StatCollector.translateToLocal((String)"lotr.gui.daleCracker");
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(texture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    public void updateScreen() {
        super.updateScreen();
        this.buttonSeal.enabled = !this.theCracker.isCrackerInvEmpty();
    }

    protected boolean checkHotbarKeys(int i) {
        return false;
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled && button == this.buttonSeal && !this.theCracker.isCrackerInvEmpty()) {
            this.theCracker.sendSealingPacket((EntityPlayer)this.mc.thePlayer);
            this.mc.displayGuiScreen(null);
        }
    }
}

