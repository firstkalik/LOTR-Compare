/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.ContainerWorkbench
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package lotr.common.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockCraftingTable;
import lotr.common.fac.LOTRFaction;
import lotr.common.recipe.LOTRRecipes;
import lotr.common.recipe.LOTRRecipesPouch;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public abstract class LOTRContainerCraftingTable
extends ContainerWorkbench {
    private World theWorld;
    private int tablePosX;
    private int tablePosY;
    private int tablePosZ;
    public final List<IRecipe> recipeList;
    public final LOTRBlockCraftingTable tableBlock;

    public LOTRContainerCraftingTable(InventoryPlayer inv, World world, int i, int j, int k, List<IRecipe> list, Block block) {
        super(inv, world, i, j, k);
        this.theWorld = world;
        this.tablePosX = i;
        this.tablePosY = j;
        this.tablePosZ = k;
        this.tableBlock = (LOTRBlockCraftingTable)block;
        this.recipeList = new ArrayList<IRecipe>(list);
        this.recipeList.add(new LOTRRecipesPouch(this.tableBlock.tableFaction));
    }

    public void onCraftMatrixChanged(IInventory inv) {
        if (this.recipeList == null) {
            return;
        }
        this.craftResult.setInventorySlotContents(0, LOTRRecipes.findMatchingRecipe(this.recipeList, this.craftMatrix, this.theWorld));
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.theWorld.getBlock(this.tablePosX, this.tablePosY, this.tablePosZ) == this.tableBlock && entityplayer.getDistanceSq((double)this.tablePosX + 0.5, (double)this.tablePosY + 0.5, (double)this.tablePosZ + 0.5) <= 64.0;
    }

    public static class Wind
    extends LOTRContainerCraftingTable {
        public Wind(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.windRecipes, LOTRMod.windtable);
        }
    }

    public static class Erebor
    extends LOTRContainerCraftingTable {
        public Erebor(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.ereborRecipes, LOTRMod.erebortable);
        }
    }

    public static class Angband
    extends LOTRContainerCraftingTable {
        public Angband(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.angbandRecipes, LOTRMod.angbandtable);
        }
    }

    public static class Red
    extends LOTRContainerCraftingTable {
        public Red(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.redDwarfRecipes, LOTRMod.reddwarvenTable);
        }
    }

    public static class Morgul
    extends LOTRContainerCraftingTable {
        public Morgul(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.morgulRecipes, LOTRMod.morgulTable);
        }
    }

    public static class Elven
    extends LOTRContainerCraftingTable {
        public Elven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.elvenRecipes, LOTRMod.elvenTable);
        }
    }

    public static class Dwarven
    extends LOTRContainerCraftingTable {
        public Dwarven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.dwarvenRecipes, LOTRMod.dwarvenTable);
        }
    }

    public static class Uruk
    extends LOTRContainerCraftingTable {
        public Uruk(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.urukRecipes, LOTRMod.urukTable);
        }
    }

    public static class Gondorian
    extends LOTRContainerCraftingTable {
        public Gondorian(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.gondorianRecipes, LOTRMod.gondorianTable);
        }
    }

    public static class Rohirric
    extends LOTRContainerCraftingTable {
        public Rohirric(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.rohirricRecipes, LOTRMod.rohirricTable);
        }
    }

    public static class WoodElven
    extends LOTRContainerCraftingTable {
        public WoodElven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.woodElvenRecipes, LOTRMod.woodElvenTable);
        }
    }

    public static class Dunlending
    extends LOTRContainerCraftingTable {
        public Dunlending(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.dunlendingRecipes, LOTRMod.dunlendingTable);
        }
    }

    public static class Angmar
    extends LOTRContainerCraftingTable {
        public Angmar(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.angmarRecipes, LOTRMod.angmarTable);
        }
    }

    public static class NearHarad
    extends LOTRContainerCraftingTable {
        public NearHarad(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.nearHaradRecipes, LOTRMod.nearHaradTable);
        }
    }

    public static class HighElven
    extends LOTRContainerCraftingTable {
        public HighElven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.highElvenRecipes, LOTRMod.highElvenTable);
        }
    }

    public static class DarkElf
    extends LOTRContainerCraftingTable {
        public DarkElf(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.darkElfRecipes, LOTRMod.darkElfTable);
        }
    }

    public static class Avari
    extends LOTRContainerCraftingTable {
        public Avari(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.avariRecipes, LOTRMod.avariTable);
        }
    }

    public static class WickedDwarf
    extends LOTRContainerCraftingTable {
        public WickedDwarf(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.WickedDwarfsRecipes, LOTRMod.wickedDwarvenTable);
        }
    }

    public static class BlueDwarven
    extends LOTRContainerCraftingTable {
        public BlueDwarven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.blueMountainsRecipes, LOTRMod.blueDwarvenTable);
        }
    }

    public static class Ranger
    extends LOTRContainerCraftingTable {
        public Ranger(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.rangerRecipes, LOTRMod.rangerTable);
        }
    }

    public static class DolGuldur
    extends LOTRContainerCraftingTable {
        public DolGuldur(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.dolGuldurRecipes, LOTRMod.dolGuldurTable);
        }
    }

    public static class Gundabad
    extends LOTRContainerCraftingTable {
        public Gundabad(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.gundabadRecipes, LOTRMod.gundabadTable);
        }
    }

    public static class HalfTroll
    extends LOTRContainerCraftingTable {
        public HalfTroll(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.halfTrollRecipes, LOTRMod.halfTrollTable);
        }
    }

    public static class DolAmroth
    extends LOTRContainerCraftingTable {
        public DolAmroth(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.dolAmrothRecipes, LOTRMod.dolAmrothTable);
        }
    }

    public static class Moredain
    extends LOTRContainerCraftingTable {
        public Moredain(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.moredainRecipes, LOTRMod.moredainTable);
        }
    }

    public static class Tauredain
    extends LOTRContainerCraftingTable {
        public Tauredain(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.tauredainRecipes, LOTRMod.tauredainTable);
        }
    }

    public static class Dale
    extends LOTRContainerCraftingTable {
        public Dale(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.daleRecipes, LOTRMod.daleTable);
        }
    }

    public static class Dorwinion
    extends LOTRContainerCraftingTable {
        public Dorwinion(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.dorwinionRecipes, LOTRMod.dorwinionTable);
        }
    }

    public static class Hobbit
    extends LOTRContainerCraftingTable {
        public Hobbit(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.hobbitRecipes, LOTRMod.hobbitTable);
        }
    }

    public static class Rhun
    extends LOTRContainerCraftingTable {
        public Rhun(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.rhunRecipes, LOTRMod.rhunTable);
        }
    }

    public static class Rivendell
    extends LOTRContainerCraftingTable {
        public Rivendell(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.rivendellRecipes, LOTRMod.rivendellTable);
        }
    }

    public static class Umbar
    extends LOTRContainerCraftingTable {
        public Umbar(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.umbarRecipes, LOTRMod.umbarTable);
        }
    }

    public static class Gulf
    extends LOTRContainerCraftingTable {
        public Gulf(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.gulfRecipes, LOTRMod.gulfTable);
        }
    }

    public static class Bree
    extends LOTRContainerCraftingTable {
        public Bree(InventoryPlayer inv, World world, int i, int j, int k) {
            super(inv, world, i, j, k, LOTRRecipes.breeRecipes, LOTRMod.breeTable);
        }
    }

}

