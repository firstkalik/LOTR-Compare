/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.gui.GuiButton
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import lotr.client.gui.LOTRGuiUnitTradeInteract;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketMercenaryInteract;
import net.minecraft.client.gui.GuiButton;

public class LOTRGuiMercenaryInteract
extends LOTRGuiUnitTradeInteract {
    public LOTRGuiMercenaryInteract(LOTREntityNPC entity) {
        super(entity);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            LOTRPacketMercenaryInteract packet = new LOTRPacketMercenaryInteract(this.theEntity.getEntityId(), button.id);
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        }
    }
}

