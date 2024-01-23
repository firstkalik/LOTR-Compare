/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.inventory.ContainerHorseInventory
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotr.common.entity.animal.LOTREntityHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class LOTRContainerMountInventory
extends ContainerHorseInventory {
    public LOTRContainerMountInventory(IInventory playerInv, IInventory horseInv, final LOTREntityHorse horse) {
        super(playerInv, horseInv, (EntityHorse)horse);
        ArrayList slots = new ArrayList(this.inventorySlots);
        this.inventorySlots.clear();
        this.inventoryItemStacks.clear();
        this.addSlotToContainer((Slot)slots.get(0));
        Slot armorSlot = (Slot)slots.get(1);
        this.addSlotToContainer(new Slot(armorSlot.inventory, armorSlot.slotNumber, armorSlot.xDisplayPosition, armorSlot.yDisplayPosition){

            public boolean isItemValid(ItemStack itemstack) {
                return super.isItemValid(itemstack) && horse.func_110259_cr() && horse.isMountArmorValid(itemstack);
            }

            @SideOnly(value=Side.CLIENT)
            public boolean func_111238_b() {
                return horse.func_110259_cr();
            }
        });
        for (int i = 2; i < slots.size(); ++i) {
            this.addSlotToContainer((Slot)slots.get(i));
        }
    }

}

