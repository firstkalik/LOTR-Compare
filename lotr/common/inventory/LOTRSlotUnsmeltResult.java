/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.inventory.LOTRSlotProtected;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRSlotUnsmeltResult
extends LOTRSlotProtected {
    public LOTRSlotUnsmeltResult(IInventory inv, int i, int j, int k) {
        super(inv, i, j, k);
    }

    public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack) {
        super.onPickupFromSlot(entityplayer, itemstack);
        if (!entityplayer.worldObj.isRemote) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.unsmelt);
        }
    }
}

