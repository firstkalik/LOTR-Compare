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
import lotr.client.gui.LOTRGuiTradeInteract;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketUnitTraderInteract;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;

public class LOTRGuiTradeUnitTradeInteract
extends LOTRGuiTradeInteract {
    private GuiButton buttonHire;

    public LOTRGuiTradeUnitTradeInteract(LOTREntityNPC entity) {
        super(entity);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonHire = new GuiButton(-1, this.width / 2 - 65, this.height / 5 * 3 + 50, 130, 20, StatCollector.translateToLocal((String)"lotr.gui.npc.hire"));
        this.buttonList.add(this.buttonHire);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button == this.buttonHire) {
                LOTRPacketUnitTraderInteract packet = new LOTRPacketUnitTraderInteract(this.theEntity.getEntityId(), 1);
                LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            } else {
                super.actionPerformed(button);
            }
        }
    }
}

