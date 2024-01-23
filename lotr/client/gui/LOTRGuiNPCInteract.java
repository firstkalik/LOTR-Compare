/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.entity.Entity
 */
package lotr.client.gui;

import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;

public abstract class LOTRGuiNPCInteract
extends LOTRGuiScreenBase {
    protected LOTREntityNPC theEntity;

    public LOTRGuiNPCInteract(LOTREntityNPC entity) {
        this.theEntity = entity;
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        String s = this.theEntity.getCommandSenderName();
        this.fontRendererObj.drawString(s, (this.width - this.fontRendererObj.getStringWidth(s)) / 2, this.height / 5 * 3 - 20, 16777215);
        super.drawScreen(i, j, f);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (this.theEntity == null || !this.theEntity.isEntityAlive() || this.theEntity.getDistanceSqToEntity((Entity)this.mc.thePlayer) > 100.0) {
            this.mc.thePlayer.closeScreen();
        }
    }
}

