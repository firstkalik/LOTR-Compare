/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.StringUtils
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.LOTRClientProxy;
import lotr.client.gui.LOTRGuiUnitTradeButton;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRSquadrons;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.entity.npc.LOTRUnitTradeEntries;
import lotr.common.entity.npc.LOTRUnitTradeEntry;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.inventory.LOTRContainerUnitTrade;
import lotr.common.inventory.LOTRSlotAlignmentReward;
import lotr.common.network.LOTRPacketBuyUnit;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public abstract class LOTRGuiHireBase
extends GuiContainer {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/npc/unit_trade.png");
    private LOTRHireableBase theUnitTrader;
    private LOTRFaction traderFaction;
    private LOTRUnitTradeEntries trades;
    private int currentTradeEntryIndex;
    private LOTREntityNPC currentDisplayedMob;
    private EntityLiving currentDisplayedMount;
    private float screenXSize;
    private float screenYSize;
    private LOTRGuiUnitTradeButton buttonHire;
    private LOTRGuiUnitTradeButton buttonLeftUnit;
    private LOTRGuiUnitTradeButton buttonRightUnit;
    private GuiTextField squadronNameField;
    private static final int extraInfoX = 49;
    private static final int extraInfoY = 106;
    private static final int extraInfoWidth = 9;
    private static final int extraInfoHeight = 7;

    public LOTRGuiHireBase(EntityPlayer entityplayer, LOTRHireableBase trader, World world) {
        super((Container)new LOTRContainerUnitTrade(entityplayer, trader, world));
        this.xSize = 220;
        this.ySize = 256;
        this.theUnitTrader = trader;
        this.traderFaction = trader.getFaction();
    }

    protected void setTrades(LOTRUnitTradeEntries t) {
        this.trades = t;
    }

    public void initGui() {
        super.initGui();
        this.buttonLeftUnit = new LOTRGuiUnitTradeButton(0, this.guiLeft + 90, this.guiTop + 144, 12, 19);
        this.buttonList.add(this.buttonLeftUnit);
        this.buttonLeftUnit.enabled = false;
        this.buttonHire = new LOTRGuiUnitTradeButton(1, this.guiLeft + 102, this.guiTop + 144, 16, 19);
        this.buttonList.add(this.buttonHire);
        this.buttonRightUnit = new LOTRGuiUnitTradeButton(2, this.guiLeft + 118, this.guiTop + 144, 12, 19);
        this.buttonList.add(this.buttonRightUnit);
        this.squadronNameField = new GuiTextField(this.fontRendererObj, this.guiLeft + this.xSize / 2 - 80, this.guiTop + 120, 160, 20);
        this.squadronNameField.setMaxStringLength(LOTRSquadrons.SQUADRON_LENGTH_MAX);
    }

    private LOTRUnitTradeEntry currentTrade() {
        return this.trades.tradeEntries[this.currentTradeEntryIndex];
    }

    public void drawScreen(int i, int j, float f) {
        this.buttonLeftUnit.enabled = this.currentTradeEntryIndex > 0;
        this.buttonHire.enabled = this.currentTrade().hasRequiredCostAndAlignment((EntityPlayer)this.mc.thePlayer, this.theUnitTrader);
        this.buttonRightUnit.enabled = this.currentTradeEntryIndex < this.trades.tradeEntries.length - 1;
        super.drawScreen(i, j, f);
        this.screenXSize = i;
        this.screenYSize = j;
    }

    public void updateScreen() {
        super.updateScreen();
        this.squadronNameField.updateCursorCounter();
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        LOTRUnitTradeEntry curTrade = this.currentTrade();
        this.drawCenteredString(this.theUnitTrader.getNPCName(), 110, 11, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 30, 162, 4210752);
        this.drawCenteredString(curTrade.getUnitTradeName(), 138, 50, 4210752);
        int reqX = 64;
        int reqXText = reqX + 19;
        int reqY = 65;
        int reqYTextBelow = 4;
        int reqGap = 18;
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2884);
        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), new ItemStack(LOTRMod.silverCoin), reqX, reqY);
        GL11.glDisable((int)2896);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int cost = curTrade.getCost((EntityPlayer)this.mc.thePlayer, this.theUnitTrader);
        this.fontRendererObj.drawString(String.valueOf(cost), reqXText, reqY + reqYTextBelow, 4210752);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(LOTRClientProxy.alignmentTexture);
        this.drawTexturedModalRect(reqX, reqY += reqGap, 0, 36, 16, 16);
        float alignment = curTrade.alignmentRequired;
        String alignS = LOTRAlignmentValues.formatAlignForDisplay(alignment);
        this.fontRendererObj.drawString(alignS, reqXText, reqY + reqYTextBelow, 4210752);
        if (curTrade.getPledgeType() != LOTRUnitTradeEntry.PledgeType.NONE) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.mc.getTextureManager().bindTexture(LOTRClientProxy.alignmentTexture);
            this.drawTexturedModalRect(reqX, reqY += reqGap, 0, 212, 16, 16);
            String pledge = StatCollector.translateToLocal((String)"container.lotr.unitTrade.pledge");
            this.fontRendererObj.drawString(pledge, reqXText, reqY + reqYTextBelow, 4210752);
            int i2 = i - this.guiLeft - reqX;
            int j2 = j - this.guiTop - reqY;
            if (i2 >= 0 && i2 < 16 && j2 >= 0 && j2 < 16) {
                String pledgeDesc = curTrade.getPledgeType().getCommandReqText(this.traderFaction);
                this.drawCreativeTabHoveringText(pledgeDesc, i - this.guiLeft, j - this.guiTop);
                GL11.glDisable((int)2896);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
        if (((LOTRContainerUnitTrade)this.inventorySlots).alignmentRewardSlots > 0) {
            Slot slot = this.inventorySlots.getSlot(0);
            boolean hasRewardCost = slot.getHasStack();
            if (hasRewardCost) {
                GL11.glEnable((int)2896);
                GL11.glEnable((int)2884);
                itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), new ItemStack(LOTRMod.silverCoin), 160, 100);
                GL11.glDisable((int)2896);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                cost = LOTRSlotAlignmentReward.REWARD_COST;
                this.fontRendererObj.drawString(String.valueOf(cost), 179, 104, 4210752);
            } else if (!slot.getHasStack() && LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getAlignment(this.traderFaction) < 1500.0f && this.func_146978_c(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, i, j)) {
                this.drawCreativeTabHoveringText(StatCollector.translateToLocalFormatted((String)"container.lotr.unitTrade.requiresAlignment", (Object[])new Object[]{Float.valueOf(1500.0f)}), i - this.guiLeft, j - this.guiTop);
                GL11.glDisable((int)2896);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
        if (curTrade.hasExtraInfo()) {
            String extraInfo = curTrade.getFormattedExtraInfo();
            boolean mouseover = i >= this.guiLeft + 49 && i < this.guiLeft + 49 + 9 && j >= this.guiTop + 106 && j < this.guiTop + 106 + 7;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.mc.getTextureManager().bindTexture(guiTexture);
            this.drawTexturedModalRect(49, 106, 220, 38 + (mouseover ? 1 : 0) * 7, 9, 7);
            if (mouseover) {
                float z = this.zLevel;
                int stringWidth = 200;
                List desc = this.fontRendererObj.listFormattedStringToWidth(extraInfo, stringWidth);
                this.func_146283_a(desc, i - this.guiLeft, j - this.guiTop);
                GL11.glDisable((int)2896);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.zLevel = z;
            }
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        boolean squadronPrompt;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (((LOTRContainerUnitTrade)this.inventorySlots).alignmentRewardSlots > 0) {
            Slot slot = this.inventorySlots.getSlot(0);
            this.drawTexturedModalRect(this.guiLeft + slot.xDisplayPosition - 3, this.guiTop + slot.yDisplayPosition - 3, this.xSize, 16, 22, 22);
            if (!slot.getHasStack() && LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getAlignment(this.traderFaction) < 1500.0f) {
                this.drawTexturedModalRect(this.guiLeft + slot.xDisplayPosition, this.guiTop + slot.yDisplayPosition, this.xSize, 0, 16, 16);
            }
        }
        this.drawMobOnGui(this.guiLeft + 32, this.guiTop + 109, (float)(this.guiLeft + 32) - this.screenXSize, (float)(this.guiTop + 109 - 50) - this.screenYSize);
        boolean bl = squadronPrompt = StringUtils.isNullOrEmpty((String)this.squadronNameField.getText()) && !this.squadronNameField.isFocused();
        if (squadronPrompt) {
            String squadronMessage = StatCollector.translateToLocal((String)"container.lotr.unitTrade.squadronBox");
            this.squadronNameField.setText((Object)EnumChatFormatting.DARK_GRAY + squadronMessage);
        }
        this.squadronNameField.drawTextBox();
        if (squadronPrompt) {
            this.squadronNameField.setText("");
        }
    }

    private void drawMobOnGui(int i, int j, float f, float f1) {
        Class entityClass = this.currentTrade().entityClass;
        Class mountClass = this.currentTrade().mountClass;
        if (this.currentDisplayedMob == null || this.currentDisplayedMob.getClass() != entityClass || mountClass == null && this.currentDisplayedMount != null || mountClass != null && (this.currentDisplayedMount == null || this.currentDisplayedMount.getClass() != mountClass)) {
            this.currentDisplayedMob = this.currentTrade().getOrCreateHiredNPC((World)this.mc.theWorld);
            if (mountClass != null) {
                EntityLiving mount;
                this.currentDisplayedMount = mount = this.currentTrade().createHiredMount((World)this.mc.theWorld);
                this.currentDisplayedMob.mountEntity((Entity)this.currentDisplayedMount);
            } else {
                this.currentDisplayedMount = null;
            }
        }
        float size = this.currentDisplayedMob.width * this.currentDisplayedMob.height * this.currentDisplayedMob.width;
        if (this.currentDisplayedMount != null) {
            size += this.currentDisplayedMount.width * this.currentDisplayedMount.height * this.currentDisplayedMount.width * 0.5f;
        }
        float scale = MathHelper.sqrt_float((float)MathHelper.sqrt_float((float)(1.0f / size))) * 30.0f;
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)i, (float)j, (float)50.0f);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f1 / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        this.currentDisplayedMob.renderYawOffset = (float)Math.atan(f / 40.0f) * 20.0f;
        this.currentDisplayedMob.rotationYaw = (float)Math.atan(f / 40.0f) * 40.0f;
        this.currentDisplayedMob.rotationPitch = -((float)Math.atan(f1 / 40.0f)) * 20.0f;
        this.currentDisplayedMob.rotationYawHead = this.currentDisplayedMob.rotationYaw;
        GL11.glTranslatef((float)0.0f, (float)this.currentDisplayedMob.yOffset, (float)0.0f);
        if (this.currentDisplayedMount != null) {
            GL11.glTranslatef((float)0.0f, (float)((float)this.currentDisplayedMount.getMountedYOffset()), (float)0.0f);
        }
        RenderManager.instance.playerViewY = 180.0f;
        RenderManager.instance.renderEntityWithPosYaw((Entity)this.currentDisplayedMob, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable((int)32826);
        OpenGlHelper.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GL11.glDisable((int)3553);
        OpenGlHelper.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        if (this.currentDisplayedMount != null) {
            GL11.glEnable((int)2903);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)i, (float)j, (float)50.0f);
            GL11.glScalef((float)(-scale), (float)scale, (float)scale);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            RenderHelper.enableStandardItemLighting();
            GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-((float)Math.atan(f1 / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            this.currentDisplayedMount.renderYawOffset = (float)Math.atan(f / 40.0f) * 20.0f;
            this.currentDisplayedMount.rotationYaw = (float)Math.atan(f / 40.0f) * 40.0f;
            this.currentDisplayedMount.rotationPitch = -((float)Math.atan(f1 / 40.0f)) * 20.0f;
            this.currentDisplayedMount.rotationYawHead = this.currentDisplayedMount.rotationYaw;
            GL11.glTranslatef((float)0.0f, (float)this.currentDisplayedMount.yOffset, (float)0.0f);
            RenderManager.instance.playerViewY = 180.0f;
            RenderManager.instance.renderEntityWithPosYaw((Entity)this.currentDisplayedMount, 0.0, 0.0, 0.0, 0.0f, 1.0f);
            GL11.glPopMatrix();
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable((int)32826);
            OpenGlHelper.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
            GL11.glDisable((int)3553);
            OpenGlHelper.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        }
    }

    protected void keyTyped(char c, int i) {
        if (this.squadronNameField.getVisible() && this.squadronNameField.textboxKeyTyped(c, i)) {
            return;
        }
        super.keyTyped(c, i);
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        this.squadronNameField.mouseClicked(i, j, k);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button == this.buttonLeftUnit) {
                if (this.currentTradeEntryIndex > 0) {
                    --this.currentTradeEntryIndex;
                }
            } else if (button == this.buttonHire) {
                String squadron = this.squadronNameField.getText();
                LOTRPacketBuyUnit packet = new LOTRPacketBuyUnit(this.currentTradeEntryIndex, squadron);
                LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            } else if (button == this.buttonRightUnit && this.currentTradeEntryIndex < this.trades.tradeEntries.length - 1) {
                ++this.currentTradeEntryIndex;
            }
        }
    }

    private void drawCenteredString(String s, int i, int j, int k) {
        this.fontRendererObj.drawString(s, i - this.fontRendererObj.getStringWidth(s) / 2, j, k);
    }
}

