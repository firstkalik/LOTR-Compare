/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRInventoryHiredReplacedItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRSlotHiredReplaceItem
extends Slot {
    private LOTREntityNPC theNPC;
    private LOTRInventoryHiredReplacedItems npcInv;
    private Slot parentSlot;

    public LOTRSlotHiredReplaceItem(Slot slot, LOTREntityNPC npc) {
        super(slot.inventory, slot.getSlotIndex(), slot.xDisplayPosition, slot.yDisplayPosition);
        int i;
        this.parentSlot = slot;
        this.theNPC = npc;
        this.npcInv = this.theNPC.hiredReplacedInv;
        if (!this.theNPC.worldObj.isRemote && this.npcInv.hasReplacedEquipment(i = this.getSlotIndex())) {
            this.inventory.setInventorySlotContents(i, this.npcInv.getEquippedReplacement(i));
        }
    }

    public boolean isItemValid(ItemStack itemstack) {
        return this.parentSlot.isItemValid(itemstack) && this.theNPC.canReEquipHired(this.getSlotIndex(), itemstack);
    }

    public int getSlotStackLimit() {
        return this.parentSlot.getSlotStackLimit();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getBackgroundIconIndex() {
        return this.parentSlot.getBackgroundIconIndex();
    }

    public void onSlotChanged() {
        super.onSlotChanged();
        if (!this.theNPC.worldObj.isRemote) {
            this.npcInv.onEquipmentChanged(this.getSlotIndex(), this.getStack());
        }
    }
}

