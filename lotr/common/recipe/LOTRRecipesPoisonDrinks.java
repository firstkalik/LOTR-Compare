/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package lotr.common.recipe;

import cpw.mods.fml.common.FMLLog;
import java.lang.reflect.Field;
import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRPoisonedDrinks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class LOTRRecipesPoisonDrinks
implements IRecipe {
    public boolean matches(InventoryCrafting inv, World world) {
        ItemStack drink = null;
        ItemStack poison = null;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (LOTRPoisonedDrinks.canPoison(itemstack)) {
                if (drink != null) {
                    return false;
                }
                drink = itemstack;
                continue;
            }
            if (LOTRMod.isOreNameEqual(itemstack, "poison")) {
                if (poison != null) {
                    return false;
                }
                poison = itemstack;
                continue;
            }
            return false;
        }
        return drink != null && poison != null;
    }

    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack result;
        EntityPlayer craftingPlayer;
        block12: {
            ItemStack drink = null;
            ItemStack poison = null;
            for (int i = 0; i < inv.getSizeInventory(); ++i) {
                Field[] itemstack = inv.getStackInSlot(i);
                if (itemstack == null) continue;
                if (LOTRPoisonedDrinks.canPoison((ItemStack)itemstack)) {
                    if (drink != null) {
                        return null;
                    }
                    drink = itemstack.copy();
                    continue;
                }
                if (LOTRMod.isOreNameEqual((ItemStack)itemstack, "poison")) {
                    if (poison != null) {
                        return null;
                    }
                    poison = itemstack.copy();
                    continue;
                }
                return null;
            }
            if (drink == null || poison == null) {
                return null;
            }
            result = drink.copy();
            LOTRPoisonedDrinks.setDrinkPoisoned(result, true);
            craftingPlayer = null;
            try {
                Container cwb = null;
                for (Field f : inv.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    Object obj = f.get((Object)inv);
                    if (!(obj instanceof Container)) continue;
                    cwb = (Container)obj;
                    break;
                }
                if (cwb == null) break block12;
                for (Object obj : cwb.inventorySlots) {
                    Slot slot = (Slot)obj;
                    IInventory slotInv = slot.inventory;
                    if (!(slotInv instanceof InventoryPlayer)) continue;
                    InventoryPlayer playerInv = (InventoryPlayer)slotInv;
                    craftingPlayer = playerInv.player;
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (craftingPlayer != null) {
            LOTRPoisonedDrinks.setPoisonerPlayer(result, craftingPlayer);
        } else {
            FMLLog.bigWarning((String)"LOTR Warning! Poisoned drink was crafted, player could not be found!", (Object[])new Object[0]);
        }
        return result;
    }

    public int getRecipeSize() {
        return 2;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}

