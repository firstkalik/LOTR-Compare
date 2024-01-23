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
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.StatCollector
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.gui.LOTRGuiHiredInteract;
import lotr.client.gui.LOTRGuiNPCInteract;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketHiredUnitDismiss;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class LOTRGuiHiredDismiss
extends LOTRGuiNPCInteract {
    public LOTRGuiHiredDismiss(LOTREntityNPC entity) {
        super(entity);
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 65, this.height / 5 * 3 + 40, 60, 20, StatCollector.translateToLocal((String)"lotr.gui.dismiss.dismiss")));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height / 5 * 3 + 40, 60, 20, StatCollector.translateToLocal((String)"lotr.gui.dismiss.cancel")));
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        boolean hasRider;
        super.drawScreen(i, j, f);
        String s = StatCollector.translateToLocal((String)"lotr.gui.dismiss.warning1");
        int y = this.height / 5 * 3;
        this.fontRendererObj.drawString(s, (this.width - this.fontRendererObj.getStringWidth(s)) / 2, y, 16777215);
        s = StatCollector.translateToLocal((String)"lotr.gui.dismiss.warning2");
        this.fontRendererObj.drawString(s, (this.width - this.fontRendererObj.getStringWidth(s)) / 2, y += this.fontRendererObj.FONT_HEIGHT, 16777215);
        y += this.fontRendererObj.FONT_HEIGHT;
        Entity mount = this.theEntity.ridingEntity;
        Entity rider = this.theEntity.riddenByEntity;
        boolean hasMount = mount instanceof LOTREntityNPC && ((LOTREntityNPC)mount).hiredNPCInfo.getHiringPlayer() == this.mc.thePlayer;
        boolean bl = hasRider = rider instanceof LOTREntityNPC && ((LOTREntityNPC)rider).hiredNPCInfo.getHiringPlayer() == this.mc.thePlayer;
        if (hasMount) {
            s = StatCollector.translateToLocal((String)"lotr.gui.dismiss.mount");
            this.fontRendererObj.drawString(s, (this.width - this.fontRendererObj.getStringWidth(s)) / 2, y, 11184810);
            y += this.fontRendererObj.FONT_HEIGHT;
        }
        if (hasRider) {
            s = StatCollector.translateToLocal((String)"lotr.gui.dismiss.rider");
            this.fontRendererObj.drawString(s, (this.width - this.fontRendererObj.getStringWidth(s)) / 2, y, 11184810);
            y += this.fontRendererObj.FONT_HEIGHT;
        }
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button.id == 1) {
                this.mc.displayGuiScreen((GuiScreen)new LOTRGuiHiredInteract(this.theEntity));
                return;
            }
            LOTRPacketHiredUnitDismiss packet = new LOTRPacketHiredUnitDismiss(this.theEntity.getEntityId(), button.id);
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        }
    }
}

