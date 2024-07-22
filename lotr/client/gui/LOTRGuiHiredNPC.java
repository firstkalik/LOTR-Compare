/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRUnitTradeEntry;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketHiredUnitCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public abstract class LOTRGuiHiredNPC
extends LOTRGuiScreenBase {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/npc/hired.png");
    public int xSize = 200;
    public int ySize = 220;
    public int guiLeft;
    public int guiTop;
    public LOTREntityNPC theNPC;
    public int page;

    public LOTRGuiHiredNPC(LOTREntityNPC npc) {
        this.theNPC = npc;
    }

    public void initGui() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        String s = this.theNPC.getNPCName();
        this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 11, 3618615);
        s = this.theNPC.getEntityClassName();
        this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 26, 3618615);
        if (this.page == 0 && this.theNPC.hiredNPCInfo.hasHiringRequirements()) {
            int x = this.guiLeft + 6;
            int y = this.guiTop + 170;
            s = StatCollector.translateToLocal((String)"lotr.hiredNPC.commandReq");
            this.fontRendererObj.drawString(s, x, y, 3618615);
            y += this.fontRendererObj.FONT_HEIGHT;
            x += 4;
            ArrayList requirementLines = new ArrayList();
            int maxWidth = this.xSize - 12 - 4;
            LOTRFaction fac = this.theNPC.getHiringFaction();
            String alignS = LOTRAlignmentValues.formatAlignForDisplay(this.theNPC.hiredNPCInfo.alignmentRequiredToCommand);
            String alignReq = StatCollector.translateToLocalFormatted((String)"lotr.hiredNPC.commandReq.align", (Object[])new Object[]{alignS, fac.factionName()});
            requirementLines.addAll(this.fontRendererObj.listFormattedStringToWidth(alignReq, maxWidth));
            LOTRUnitTradeEntry.PledgeType pledge = this.theNPC.hiredNPCInfo.pledgeType;
            String pledgeReq = pledge.getCommandReqText(fac);
            if (pledgeReq != null) {
                requirementLines.addAll(this.fontRendererObj.listFormattedStringToWidth(pledgeReq, maxWidth));
            }
            for (Object obj : requirementLines) {
                String line = (String)obj;
                this.fontRendererObj.drawString(line, x, y, 3618615);
                y += this.fontRendererObj.FONT_HEIGHT;
            }
        }
        super.drawScreen(i, j, f);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (!this.theNPC.isEntityAlive() || this.theNPC.hiredNPCInfo.getHiringPlayer() != this.mc.thePlayer || this.theNPC.getDistanceSqToEntity((Entity)this.mc.thePlayer) > 64.0) {
            this.mc.thePlayer.closeScreen();
        }
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        this.sendActionPacket(-1);
    }

    public void sendActionPacket(int action) {
        this.sendActionPacket(action, 0);
    }

    public void sendActionPacket(int action, int value) {
        LOTRPacketHiredUnitCommand packet = new LOTRPacketHiredUnitCommand(this.theNPC.getEntityId(), this.page, action, value);
        LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
    }
}

