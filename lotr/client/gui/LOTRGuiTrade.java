/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.gui.LOTRGuiTradeButton;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeEntry;
import lotr.common.entity.npc.LOTRTradeSellResult;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.inventory.LOTRContainerTrade;
import lotr.common.inventory.LOTRSlotTrade;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketSell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRGuiTrade
extends GuiContainer {
    public static final ResourceLocation guiTexture = new ResourceLocation("lotr:gui/npc/trade.png");
    private static int lockedTradeColor = -1610612736;
    public LOTREntityNPC theEntity;
    private LOTRContainerTrade containerTrade;
    private GuiButton buttonSell;
    private static int sellQueryX;
    private static int sellQueryY;
    private static int sellQueryWidth;

    public LOTRGuiTrade(InventoryPlayer inv, LOTRTradeable trader, World world) {
        super((Container)new LOTRContainerTrade(inv, trader, world));
        this.containerTrade = (LOTRContainerTrade)this.inventorySlots;
        this.theEntity = (LOTREntityNPC)((Object)trader);
        this.ySize = 270;
    }

    public void initGui() {
        super.initGui();
        this.buttonSell = new LOTRGuiTradeButton(0, this.guiLeft + 79, this.guiTop + 164);
        this.buttonSell.enabled = false;
        this.buttonList.add(this.buttonSell);
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        int l;
        this.drawCenteredString(this.theEntity.getNPCName(), 89, 11, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.lotr.trade.buy"), 8, 28, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.lotr.trade.sell"), 8, 79, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.lotr.trade.sellOffer"), 8, 129, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, 176, 4210752);
        for (l = 0; l < this.containerTrade.tradeInvBuy.getSizeInventory(); ++l) {
            LOTRSlotTrade slotBuy = (LOTRSlotTrade)this.containerTrade.getSlotFromInventory(this.containerTrade.tradeInvBuy, l);
            this.renderTradeSlot(slotBuy);
        }
        for (l = 0; l < this.containerTrade.tradeInvSell.getSizeInventory(); ++l) {
            LOTRSlotTrade slotSell = (LOTRSlotTrade)this.containerTrade.getSlotFromInventory(this.containerTrade.tradeInvSell, l);
            this.renderTradeSlot(slotSell);
        }
        int totalSellPrice = 0;
        for (int l2 = 0; l2 < this.containerTrade.tradeInvSellOffer.getSizeInventory(); ++l2) {
            LOTRTradeSellResult sellResult;
            Slot slotSell = this.containerTrade.getSlotFromInventory(this.containerTrade.tradeInvSellOffer, l2);
            ItemStack item = slotSell.getStack();
            if (item == null || (sellResult = LOTRTradeEntries.getItemSellResult(item, this.theEntity)) == null) continue;
            totalSellPrice += sellResult.totalSellValue;
        }
        if (totalSellPrice > 0) {
            this.fontRendererObj.drawString(StatCollector.translateToLocalFormatted((String)"container.lotr.trade.sellPrice", (Object[])new Object[]{totalSellPrice}), 100, 169, 4210752);
        }
        this.buttonSell.enabled = totalSellPrice > 0;
    }

    private void renderTradeSlot(LOTRSlotTrade slot) {
        LOTRTradeEntry trade = slot.getTrade();
        if (trade != null) {
            int lockedPixels = 0;
            boolean inFront = false;
            if (!trade.isAvailable()) {
                lockedPixels = 16;
                inFront = true;
            } else {
                lockedPixels = trade.getLockedProgressSlot();
                inFront = false;
            }
            if (lockedPixels > 0) {
                GL11.glPushMatrix();
                if (inFront) {
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)200.0f);
                }
                int x = slot.xDisplayPosition;
                int y = slot.yDisplayPosition;
                LOTRGuiTrade.drawRect((int)x, (int)y, (int)(x + lockedPixels), (int)(y + 16), (int)lockedTradeColor);
                GL11.glPopMatrix();
            }
            if (trade.isAvailable()) {
                int cost = slot.cost();
                if (cost > 0) {
                    this.renderCost(Integer.toString(cost), slot.xDisplayPosition + 8, slot.yDisplayPosition + 22);
                }
            } else {
                this.drawCenteredString(StatCollector.translateToLocal((String)"container.lotr.trade.locked"), slot.xDisplayPosition + 8, slot.yDisplayPosition + 22, 4210752);
            }
        }
    }

    private void renderCost(String s, int x, int y) {
        boolean halfSize;
        int l = this.fontRendererObj.getStringWidth(s);
        boolean bl = halfSize = l > 15;
        if (halfSize) {
            GL11.glPushMatrix();
            GL11.glScalef((float)0.5f, (float)0.5f, (float)1.0f);
            x *= 2;
            y *= 2;
            y += this.fontRendererObj.FONT_HEIGHT / 2;
        }
        this.drawCenteredString(s, x, y, 4210752);
        if (halfSize) {
            GL11.glPopMatrix();
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        LOTRGuiTrade.func_146110_a((int)this.guiLeft, (int)this.guiTop, (float)0.0f, (float)0.0f, (int)this.xSize, (int)this.ySize, (float)512.0f, (float)512.0f);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled && button == this.buttonSell) {
            LOTRPacketSell packet = new LOTRPacketSell();
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        }
    }

    private void drawCenteredString(String s, int i, int j, int k) {
        this.fontRendererObj.drawString(s, i - this.fontRendererObj.getStringWidth(s) / 2, j, k);
    }

    static {
        sellQueryWidth = 12;
    }
}

