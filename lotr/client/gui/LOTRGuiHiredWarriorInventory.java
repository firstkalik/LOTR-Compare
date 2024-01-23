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
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.inventory.LOTRContainerHiredWarriorInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiHiredWarriorInventory
extends GuiContainer {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/npc/hiredWarrior.png");
    private LOTREntityNPC theNPC;
    private LOTRContainerHiredWarriorInventory containerInv;

    public LOTRGuiHiredWarriorInventory(InventoryPlayer inv, LOTREntityNPC entity) {
        super((Container)new LOTRContainerHiredWarriorInventory(inv, entity));
        this.theNPC = entity;
        this.containerInv = (LOTRContainerHiredWarriorInventory)this.inventorySlots;
        this.ySize = 188;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String s = StatCollector.translateToLocal((String)"lotr.gui.warrior.openInv");
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, 95, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.theNPC instanceof LOTREntityOrc && ((LOTREntityOrc)this.theNPC).isOrcBombardier()) {
            Slot slotBomb = this.containerInv.getSlotFromInventory(this.containerInv.proxyInv, 5);
            Slot slotMelee = this.containerInv.getSlotFromInventory(this.containerInv.proxyInv, 4);
            this.drawTexturedModalRect(this.guiLeft + slotBomb.xDisplayPosition - 1, this.guiTop + slotBomb.yDisplayPosition - 1, slotMelee.xDisplayPosition - 1, slotMelee.yDisplayPosition - 1, 18, 18);
        }
    }
}

