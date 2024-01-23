/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.StatCollector
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.gui.LOTRGuiNPCInteract;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketUnitTraderInteract;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;

public class LOTRGuiUnitTradeInteract
extends LOTRGuiNPCInteract {
    private GuiButton buttonTalk;
    private GuiButton buttonHire;

    public LOTRGuiUnitTradeInteract(LOTREntityNPC entity) {
        super(entity);
    }

    public void initGui() {
        this.buttonTalk = new GuiButton(0, this.width / 2 - 65, this.height / 5 * 3, 60, 20, StatCollector.translateToLocal((String)"lotr.gui.npc.talk"));
        this.buttonHire = new GuiButton(1, this.width / 2 + 5, this.height / 5 * 3, 60, 20, StatCollector.translateToLocal((String)"lotr.gui.npc.hire"));
        this.buttonList.add(this.buttonTalk);
        this.buttonList.add(this.buttonHire);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            LOTRPacketUnitTraderInteract packet = new LOTRPacketUnitTraderInteract(this.theEntity.getEntityId(), button.id);
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        }
    }
}

