/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import java.util.ArrayList;
import java.util.Collections;
import lotr.common.LOTRMod;
import lotr.common.inventory.LOTRSlotStackSize;
import lotr.common.tileentity.LOTRTileEntityForgeBase2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRTileEntityHobbitOven2
extends LOTRTileEntityForgeBase2
implements IInventory,
ISidedInventory {
    private ItemStack[] inventory = new ItemStack[19];
    public int ovenCookTime = 0;
    public int currentItemFuelValue = 0;
    public int currentCookTime = 0;
    private String specialOvenName;
    private int[] inputSlots = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private int[] outputSlots = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17};
    private int fuelSlot = 18;

    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (this.inventory[i] != null) {
            if (this.inventory[i].stackSize <= j) {
                ItemStack itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            ItemStack itemstack = this.inventory[i].splitStack(j);
            if (this.inventory[i].stackSize == 0) {
                this.inventory[i] = null;
            }
            return itemstack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.inventory[i] != null) {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.inventory[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.specialOvenName : StatCollector.translateToLocal((String)"container.lotr.hobbitOven");
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.specialOvenName != null && this.specialOvenName.length() > 0;
    }

    public void setOvenName(String s) {
        this.specialOvenName = s;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList items = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < items.tagCount(); ++i) {
            NBTTagCompound itemData = items.getCompoundTagAt(i);
            byte byte0 = itemData.getByte("Slot");
            if (byte0 < 0 || byte0 >= this.inventory.length) continue;
            this.inventory[byte0] = ItemStack.loadItemStackFromNBT((NBTTagCompound)itemData);
        }
        this.ovenCookTime = nbt.getShort("BurnTime");
        this.currentCookTime = nbt.getShort("CookTime");
        this.currentItemFuelValue = TileEntityFurnace.getItemBurnTime((ItemStack)this.inventory[18]);
        if (nbt.hasKey("CustomName")) {
            this.specialOvenName = nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NBTTagCompound itemData = new NBTTagCompound();
            itemData.setByte("Slot", (byte)i);
            this.inventory[i].writeToNBT(itemData);
            items.appendTag((NBTBase)itemData);
        }
        nbt.setTag("Items", (NBTBase)items);
        nbt.setShort("BurnTime", (short)this.ovenCookTime);
        nbt.setShort("CookTime", (short)this.currentCookTime);
        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.specialOvenName);
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    public static boolean isCookResultAcceptable(ItemStack result) {
        if (result == null) {
            return false;
        }
        Item item = result.getItem();
        return item instanceof ItemFood || item == LOTRMod.pipeweed || item == Item.getItemFromBlock((Block)LOTRMod.driedReeds);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        if (slot < 9) {
            return itemstack == null ? false : LOTRTileEntityHobbitOven2.isCookResultAcceptable(FurnaceRecipes.smelting().getSmeltingResult(itemstack));
        }
        if (slot < 18) {
            return false;
        }
        return TileEntityFurnace.isItemFuel((ItemStack)itemstack);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (side == 0) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i : this.outputSlots) {
                list.add(i);
            }
            list.add(this.fuelSlot);
            int[] temp = new int[list.size()];
            for (int i = 0; i < temp.length; ++i) {
                temp[i] = (Integer)list.get(i);
            }
            return temp;
        }
        if (side == 1) {
            ArrayList<LOTRSlotStackSize> list = new ArrayList<LOTRSlotStackSize>();
            for (int slot : this.inputSlots) {
                int size = this.getStackInSlot(slot) == null ? 0 : this.getStackInSlot((int)slot).stackSize;
                list.add(new LOTRSlotStackSize(slot, size));
            }
            Collections.sort(list);
            int[] temp = new int[this.inputSlots.length];
            for (int i = 0; i < temp.length; ++i) {
                LOTRSlotStackSize obj = (LOTRSlotStackSize)list.get(i);
                temp[i] = obj.slot;
            }
            return temp;
        }
        return new int[]{this.fuelSlot};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
        return this.isItemValidForSlot(slot, itemstack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
        if (side == 0) {
            if (slot == this.fuelSlot) {
                return itemstack.getItem() == Items.bucket;
            }
            return true;
        }
        return true;
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        if (packet.func_148857_g() != null && packet.func_148857_g().hasKey("CustomName")) {
            this.specialOvenName = packet.func_148857_g().getString("CustomName");
        }
    }

    @Override
    public int getForgeInvSize() {
        return 28;
    }

    @Override
    public void setupForgeSlots() {
        this.inputSlots = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12};
        this.outputSlots = new int[]{13, 14, 15, 16, 17, 18, 19, 20, 21};
        this.fuelSlot = 22;
    }

    @Override
    public int getSmeltingDuration() {
        return 200;
    }

    @Override
    public String getForgeName() {
        return StatCollector.translateToLocal((String)"container.lotr.dwarvenForge1");
    }

    private boolean canSmelt(int i) {
        ItemStack alloyResult;
        ItemStack result;
        int resultSize;
        if (this.inventory[i] == null) {
            return false;
        }
        if (this.inventory[i - 9] != null && (alloyResult = this.getAlloySmeltingResult(this.inventory[i], this.inventory[i - 4])) != null) {
            if (this.inventory[i + 9] == null) {
                return true;
            }
            resultSize = this.inventory[i + 9].stackSize + alloyResult.stackSize;
            if (this.inventory[i + 9].isItemEqual(alloyResult) && resultSize <= this.getInventoryStackLimit() && resultSize <= alloyResult.getMaxStackSize()) {
                return true;
            }
        }
        if ((result = this.getSmeltingResult(this.inventory[i])) == null) {
            return false;
        }
        if (this.inventory[i + 9] == null) {
            return true;
        }
        if (!this.inventory[i + 9].isItemEqual(result)) {
            return false;
        }
        resultSize = this.inventory[i + 9].stackSize + result.stackSize;
        return resultSize <= this.getInventoryStackLimit() && resultSize <= result.getMaxStackSize();
    }

    @Override
    protected boolean canDoSmelting() {
        for (int i = 9; i < 18; ++i) {
            if (!this.canSmelt(i)) continue;
            return true;
        }
        return false;
    }

    @Override
    protected void doSmelt() {
        for (int i = 9; i < 18; ++i) {
            this.smeltItemInSlot(i);
        }
    }

    private void smeltItemInSlot(int i) {
        if (this.canSmelt(i)) {
            ItemStack alloyResult;
            boolean smeltedAlloyItem = false;
            if (this.inventory[i - 9] != null && (alloyResult = this.getAlloySmeltingResult(this.inventory[i], this.inventory[i - 4])) != null && (this.inventory[i + 4] == null || this.inventory[i + 4].isItemEqual(alloyResult))) {
                if (this.inventory[i + 9] == null) {
                    this.inventory[i + 9] = alloyResult.copy();
                } else if (this.inventory[i + 9].isItemEqual(alloyResult)) {
                    this.inventory[i + 9].stackSize += alloyResult.stackSize;
                }
                --this.inventory[i].stackSize;
                if (this.inventory[i].stackSize <= 0) {
                    this.inventory[i] = null;
                }
                --this.inventory[i - 9].stackSize;
                if (this.inventory[i - 9].stackSize <= 0) {
                    this.inventory[i - 9] = null;
                }
                smeltedAlloyItem = true;
            }
            if (!smeltedAlloyItem) {
                ItemStack result = this.getSmeltingResult(this.inventory[i]);
                if (this.inventory[i + 9] == null) {
                    this.inventory[i + 9] = result.copy();
                } else if (this.inventory[i + 9].isItemEqual(result)) {
                    this.inventory[i + 9].stackSize += result.stackSize;
                }
                --this.inventory[i].stackSize;
                if (this.inventory[i].stackSize <= 0) {
                    this.inventory[i] = null;
                }
            }
        }
    }

    protected ItemStack getAlloySmeltingResult(ItemStack itemstack, ItemStack alloyItem) {
        if (this.isCopper(itemstack) && this.isTin(alloyItem) || this.isTin(itemstack) && this.isCopper(alloyItem)) {
            return new ItemStack(LOTRMod.bronze, 2);
        }
        return null;
    }

    protected boolean isCopper(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "oreCopper") || LOTRMod.isOreNameEqual(itemstack, "ingotCopper");
    }

    protected boolean isTin(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "oreTin") || LOTRMod.isOreNameEqual(itemstack, "ingotTin");
    }

    protected boolean isIron(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "oreIron") || LOTRMod.isOreNameEqual(itemstack, "ingotIron");
    }

    protected boolean isGold(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "oreGold") || LOTRMod.isOreNameEqual(itemstack, "ingotGold");
    }

    protected boolean isGold1(ItemStack itemstack) {
        return itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreGold) || itemstack.getItem() == LOTRMod.goldRaw;
    }

    protected boolean isIron1(ItemStack itemstack) {
        return itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreIron) || itemstack.getItem() == LOTRMod.ironRaw;
    }

    protected boolean isGoldNugget(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "nuggetGold");
    }

    protected boolean isIronNugget(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "nuggetIron");
    }

    protected boolean isSilver(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "oreSilver") || LOTRMod.isOreNameEqual(itemstack, "ingotSilver");
    }

    protected boolean isSilverNugget(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "nuggetSilver");
    }

    protected boolean isMithril(ItemStack itemstack) {
        return itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreMithril) || itemstack.getItem() == LOTRMod.mithril;
    }

    protected boolean isMithrilNugget(ItemStack itemstack) {
        return itemstack.getItem() == LOTRMod.mithrilNugget;
    }

    protected boolean isOrcSteel(ItemStack itemstack) {
        return itemstack.getItem() == Item.getItemFromBlock((Block)LOTRMod.oreMorgulIron) || itemstack.getItem() == LOTRMod.orcSteel;
    }

    protected boolean isCoal(ItemStack itemstack) {
        return itemstack.getItem() == Items.coal;
    }

    public ItemStack getSmeltingResult(ItemStack itemstack) {
        boolean isStoneMaterial = false;
        Item item = itemstack.getItem();
        Block block = Block.getBlockFromItem((Item)item);
        if (block != null && block != Blocks.air) {
            Material material = block.getMaterial();
            if (material == Material.rock || material == Material.sand || material == Material.clay) {
                isStoneMaterial = true;
            }
        } else if (item == Items.clay_ball || item == LOTRMod.clayMug || item == LOTRMod.goldRaw || item == LOTRMod.ironRaw || item == LOTRMod.clayPlate || item == LOTRMod.ceramicPlate) {
            isStoneMaterial = true;
        }
        if (isStoneMaterial || this.isWood(itemstack)) {
            return FurnaceRecipes.smelting().getSmeltingResult(itemstack);
        }
        return null;
    }

    protected boolean isWood(ItemStack itemstack) {
        return LOTRMod.isOreNameEqual(itemstack, "logWood");
    }
}

