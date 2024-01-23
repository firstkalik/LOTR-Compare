/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 */
package lotr.common.inventory;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.inventory.LOTREntityInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class LOTRInventoryNPC
extends LOTREntityInventory {
    protected LOTREntityNPC theNPC;

    public LOTRInventoryNPC(String s, LOTREntityNPC npc, int i) {
        super(s, (EntityLivingBase)npc, i);
        this.theNPC = npc;
    }

    @Override
    protected void dropItem(ItemStack itemstack) {
        this.theNPC.npcDropItem(itemstack, 0.0f, false);
    }
}

