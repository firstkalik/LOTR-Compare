/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.StatCollector
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.gui.LOTRGuiHiredDismiss;
import lotr.client.gui.LOTRGuiNPCInteract;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketHiredUnitInteract;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class LOTRGuiHiredInteract
extends LOTRGuiNPCInteract {
    public LOTRGuiHiredInteract(LOTREntityNPC entity) {
        super(entity);
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 65, this.height / 5 * 3, 60, 20, StatCollector.translateToLocal((String)"lotr.gui.npc.talk")));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height / 5 * 3, 60, 20, StatCollector.translateToLocal((String)"lotr.gui.npc.command")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 65, this.height / 5 * 3 + 25, 130, 20, StatCollector.translateToLocal((String)"lotr.gui.npc.dismiss")));
        ((GuiButton)this.buttonList.get((int)0)).enabled = this.theEntity.getSpeechBank((EntityPlayer)this.mc.thePlayer) != null;
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button.id == 2) {
                this.mc.displayGuiScreen((GuiScreen)new LOTRGuiHiredDismiss(this.theEntity));
                return;
            }
            LOTRPacketHiredUnitInteract packet = new LOTRPacketHiredUnitInteract(this.theEntity.getEntityId(), button.id);
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        }
    }
}

