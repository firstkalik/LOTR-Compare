/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 */
package lotr.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class LOTRSlotSeeds
extends Slot {
    private World worldObj;

    public LOTRSlotSeeds(IInventory inv, int i, int j, int k, World world) {
        super(inv, i, j, k);
        this.worldObj = world;
    }

    public boolean isItemValid(ItemStack itemstack) {
        Item item = itemstack.getItem();
        return item instanceof IPlantable && ((IPlantable)item).getPlantType((IBlockAccess)this.worldObj, -1, -1, -1) == EnumPlantType.Crop;
    }
}

