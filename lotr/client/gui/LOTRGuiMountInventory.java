/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiScreenHorseInventory
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 */
package lotr.client.gui;

import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.inventory.LOTRContainerMountInventory;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class LOTRGuiMountInventory
extends GuiScreenHorseInventory {
    public LOTRGuiMountInventory(IInventory playerInv, IInventory horseInv, LOTREntityHorse horse) {
        super(playerInv, horseInv, (EntityHorse)horse);
        this.inventorySlots = new LOTRContainerMountInventory(playerInv, horseInv, horse);
    }
}

