/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.apache.commons.lang3.StringUtils
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import java.util.UUID;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.LOTRLevelData;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.network.LOTRPacketBeaconEdit;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.tileentity.LOTRTileEntityBeacon;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

public class LOTRGuiBeacon
extends LOTRGuiScreenBase {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/beacon.png");
    private int xSize = 200;
    private int ySize = 160;
    private int guiLeft;
    private int guiTop;
    private int beaconX;
    private int beaconY;
    private int beaconZ;
    private UUID initFellowshipID;
    private LOTRFellowshipClient initFellowship;
    private String initBeaconName;
    private String currentBeaconName;
    private GuiButton buttonDone;
    private GuiTextField fellowshipNameField;
    private GuiTextField beaconNameField;

    public LOTRGuiBeacon(LOTRTileEntityBeacon beacon) {
        this.beaconX = beacon.xCoord;
        this.beaconY = beacon.yCoord;
        this.beaconZ = beacon.zCoord;
        this.initFellowshipID = beacon.getFellowshipID();
        this.initBeaconName = beacon.getBeaconName();
    }

    public void initGui() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.initFellowship = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getClientFellowshipByID(this.initFellowshipID);
        this.buttonDone = new GuiButton(0, this.guiLeft + this.xSize / 2 - 40, this.guiTop + 130, 80, 20, StatCollector.translateToLocal((String)"container.lotr.beacon.done"));
        this.buttonList.add(this.buttonDone);
        this.fellowshipNameField = new GuiTextField(this.fontRendererObj, this.guiLeft + this.xSize / 2 - 80, this.guiTop + 45, 160, 20);
        this.fellowshipNameField.setMaxStringLength(40);
        if (this.initFellowship != null) {
            this.fellowshipNameField.setText(this.initFellowship.getName());
        }
        this.beaconNameField = new GuiTextField(this.fontRendererObj, this.guiLeft + this.xSize / 2 - 80, this.guiTop + 100, 160, 20);
        this.beaconNameField.setMaxStringLength(40);
        if (!StringUtils.isBlank((CharSequence)this.initBeaconName)) {
            this.beaconNameField.setText(this.initBeaconName);
        }
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        TileEntity te = this.mc.theWorld.getTileEntity(this.beaconX, this.beaconY, this.beaconZ);
        String s = new ItemStack(te.getBlockType(), 1, te.getBlockMetadata()).getDisplayName();
        this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 11, 4210752);
        this.fellowshipNameField.drawTextBox();
        s = StatCollector.translateToLocal((String)"container.lotr.beacon.nameFellowship");
        this.fontRendererObj.drawString(s, this.fellowshipNameField.xPosition + 4, this.fellowshipNameField.yPosition - 4 - this.fontRendererObj.FONT_HEIGHT, 4210752);
        this.currentBeaconName = this.beaconNameField.getText();
        this.beaconNameField.setEnabled(true);
        if (this.beaconNameField.isFocused()) {
            this.beaconNameField.drawTextBox();
        } else {
            String beaconNameEff = this.currentBeaconName;
            if (StringUtils.isBlank((CharSequence)beaconNameEff)) {
                beaconNameEff = this.fellowshipNameField.getText();
                this.beaconNameField.setEnabled(false);
            }
            this.beaconNameField.setText(beaconNameEff);
            this.beaconNameField.drawTextBox();
            this.beaconNameField.setText(this.currentBeaconName);
        }
        s = StatCollector.translateToLocal((String)"container.lotr.beacon.nameBeacon");
        this.fontRendererObj.drawString(s, this.beaconNameField.xPosition + 4, this.beaconNameField.yPosition - 4 - this.fontRendererObj.FONT_HEIGHT * 2, 4210752);
        s = StatCollector.translateToLocal((String)"container.lotr.beacon.namePrefix");
        this.fontRendererObj.drawString(s, this.beaconNameField.xPosition + 4, this.beaconNameField.yPosition - 4 - this.fontRendererObj.FONT_HEIGHT, 4210752);
        super.drawScreen(i, j, f);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.fellowshipNameField.updateCursorCounter();
        this.beaconNameField.updateCursorCounter();
        double dSq = this.mc.thePlayer.getDistanceSq((double)this.beaconX + 0.5, (double)this.beaconY + 0.5, (double)this.beaconZ + 0.5);
        if (dSq > 64.0) {
            this.mc.thePlayer.closeScreen();
        } else {
            TileEntity tileentity = this.mc.theWorld.getTileEntity(this.beaconX, this.beaconY, this.beaconZ);
            if (!(tileentity instanceof LOTRTileEntityBeacon)) {
                this.mc.thePlayer.closeScreen();
            }
        }
    }

    @Override
    protected void keyTyped(char c, int i) {
        if (this.fellowshipNameField.getVisible() && this.fellowshipNameField.textboxKeyTyped(c, i)) {
            return;
        }
        if (this.beaconNameField.getVisible() && this.beaconNameField.textboxKeyTyped(c, i)) {
            return;
        }
        super.keyTyped(c, i);
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        this.fellowshipNameField.mouseClicked(i, j, k);
        this.beaconNameField.mouseClicked(i, j, k);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled && button == this.buttonDone) {
            this.mc.thePlayer.closeScreen();
        }
    }

    private void sendBeaconEditPacket(boolean closed) {
        LOTRFellowshipClient fs;
        UUID fsID = null;
        String fsName = this.fellowshipNameField.getText();
        if (!StringUtils.isBlank((CharSequence)fsName) && (fs = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getClientFellowshipByName(fsName)) != null) {
            fsID = fs.getFellowshipID();
        }
        String beaconName = this.currentBeaconName;
        LOTRPacketBeaconEdit packet = new LOTRPacketBeaconEdit(this.beaconX, this.beaconY, this.beaconZ, fsID, beaconName, true);
        LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
    }

    public void onGuiClosed() {
        this.sendBeaconEditPacket(true);
    }
}

