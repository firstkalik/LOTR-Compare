/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraft.item.crafting.ShapelessRecipes
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 *  org.apache.commons.lang3.tuple.Pair
 */
package lotr.common.tileentity;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockCraftingTable;
import lotr.common.inventory.LOTRContainerCraftingTable;
import lotr.common.item.LOTRItemCrossbow;
import lotr.common.item.LOTRItemMountArmor;
import lotr.common.item.LOTRItemThrowingAxe;
import lotr.common.item.LOTRMaterial;
import lotr.common.recipe.LOTRRecipePoisonWeapon;
import lotr.common.recipe.LOTRRecipes;
import lotr.common.tileentity.LOTRTileEntityForgeBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.tuple.Pair;

public class LOTRTileEntityUnsmeltery
extends LOTRTileEntityForgeBase {
    public static Random unsmeltingRand = new Random();
    public static Map<Pair<Item, Integer>, Integer> unsmeltableCraftingCounts = new HashMap<Pair<Item, Integer>, Integer>();
    public float prevRocking;
    public float rocking;
    public float prevRockingPhase;
    public float rockingPhase = unsmeltingRand.nextFloat() * 3.1415927f * 2.0f;
    public boolean prevServerActive;
    public boolean serverActive;
    public boolean clientActive;

    @Override
    public int getForgeInvSize() {
        return 3;
    }

    @Override
    public void setupForgeSlots() {
        this.inputSlots = new int[]{0};
        this.fuelSlot = 1;
        this.outputSlots = new int[]{2};
    }

    @Override
    protected boolean canMachineInsertInput(ItemStack itemstack) {
        return itemstack != null && this.getLargestUnsmeltingResult(itemstack) != null;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
            this.prevServerActive = this.serverActive;
            this.serverActive = this.isSmelting();
            if (this.serverActive != this.prevServerActive) {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }
        } else {
            this.prevRocking = this.rocking;
            this.prevRockingPhase = this.rockingPhase;
            this.rockingPhase += 0.1f;
            this.rocking = this.clientActive ? (this.rocking += 0.05f) : (this.rocking -= 0.01f);
            this.rocking = MathHelper.clamp_float((float)this.rocking, (float)0.0f, (float)1.0f);
        }
    }

    public float getRockingAmount(float tick) {
        float mag = this.prevRocking + (this.rocking - this.prevRocking) * tick;
        float phase = this.prevRockingPhase + (this.rockingPhase - this.prevRockingPhase) * tick;
        return mag * MathHelper.sin((float)phase);
    }

    @Override
    public int getSmeltingDuration() {
        return 400;
    }

    @Override
    protected boolean canDoSmelting() {
        ItemStack input = this.inventory[this.inputSlots[0]];
        if (input == null) {
            return false;
        }
        ItemStack result = this.getLargestUnsmeltingResult(input);
        if (result == null) {
            return false;
        }
        ItemStack output = this.inventory[this.outputSlots[0]];
        if (output == null) {
            return true;
        }
        if (!output.isItemEqual(result)) {
            return false;
        }
        int resultSize = output.stackSize + result.stackSize;
        return resultSize <= this.getInventoryStackLimit() && resultSize <= result.getMaxStackSize();
    }

    @Override
    protected void doSmelt() {
        if (this.canDoSmelting()) {
            ItemStack input = this.inventory[this.inputSlots[0]];
            ItemStack result = this.getRandomUnsmeltingResult(input);
            if (result != null) {
                if (this.inventory[this.outputSlots[0]] == null) {
                    this.inventory[this.outputSlots[0]] = result.copy();
                } else if (this.inventory[this.outputSlots[0]].isItemEqual(result)) {
                    this.inventory[this.outputSlots[0]].stackSize += result.stackSize;
                }
            }
            --this.inventory[this.inputSlots[0]].stackSize;
            if (this.inventory[this.inputSlots[0]].stackSize <= 0) {
                this.inventory[this.inputSlots[0]] = null;
            }
        }
    }

    @Override
    public String getForgeName() {
        return StatCollector.translateToLocal((String)"container.lotr.unsmeltery");
    }

    public boolean canBeUnsmelted(ItemStack itemstack) {
        if (itemstack == null) {
            return false;
        }
        ItemStack material = LOTRTileEntityUnsmeltery.getEquipmentMaterial(itemstack);
        if (material != null) {
            if (TileEntityFurnace.getItemBurnTime((ItemStack)material) != 0) {
                return false;
            }
            if (itemstack.getItem() instanceof ItemBlock && Block.getBlockFromItem((Item)itemstack.getItem()).getMaterial().getCanBurn()) {
                return false;
            }
            return this.determineResourcesUsed(itemstack, material) > 0;
        }
        return false;
    }

    public ItemStack getLargestUnsmeltingResult(ItemStack itemstack) {
        if (itemstack == null || !this.canBeUnsmelted(itemstack)) {
            return null;
        }
        ItemStack material = LOTRTileEntityUnsmeltery.getEquipmentMaterial(itemstack);
        int items = this.determineResourcesUsed(itemstack, material);
        int meta = material.getItemDamage();
        if (meta == 32767) {
            meta = 0;
        }
        return new ItemStack(material.getItem(), items, meta);
    }

    public ItemStack getRandomUnsmeltingResult(ItemStack itemstack) {
        int items_int;
        ItemStack result = this.getLargestUnsmeltingResult(itemstack);
        if (result == null) {
            return null;
        }
        float items = result.stackSize;
        items *= 0.8f;
        if (itemstack.isItemStackDamageable()) {
            items *= (float)(itemstack.getMaxDamage() - itemstack.getItemDamage()) / (float)itemstack.getMaxDamage();
        }
        if ((items_int = Math.round(items *= MathHelper.randomFloatClamp((Random)unsmeltingRand, (float)0.7f, (float)1.0f))) <= 0) {
            return null;
        }
        return new ItemStack(result.getItem(), items_int, result.getItemDamage());
    }

    public static ItemStack getEquipmentMaterial(ItemStack itemstack) {
        if (itemstack == null) {
            return null;
        }
        Item item = itemstack.getItem();
        ItemStack material = null;
        if (item instanceof ItemTool) {
            material = ((ItemTool)item).func_150913_i().getRepairItemStack();
        } else if (item instanceof ItemSword) {
            material = LOTRMaterial.getToolMaterialByName(((ItemSword)item).getToolMaterialName()).getRepairItemStack();
        } else if (item instanceof LOTRItemCrossbow) {
            material = ((LOTRItemCrossbow)item).getCrossbowMaterial().getRepairItemStack();
        } else if (item instanceof LOTRItemThrowingAxe) {
            material = ((LOTRItemThrowingAxe)item).getAxeMaterial().getRepairItemStack();
        } else if (item instanceof ItemArmor) {
            material = new ItemStack(((ItemArmor)item).getArmorMaterial().func_151685_b());
        } else if (item instanceof LOTRItemMountArmor) {
            material = new ItemStack(((LOTRItemMountArmor)item).getMountArmorMaterial().func_151685_b());
        }
        if (material != null) {
            if (item.getIsRepairable(itemstack, material)) {
                return material;
            }
        } else {
            if (item instanceof ItemHoe) {
                return LOTRMaterial.getToolMaterialByName(((ItemHoe)item).getToolMaterialName()).getRepairItemStack();
            }
            if (item == Items.bucket) {
                return new ItemStack(Items.iron_ingot);
            }
            if (item == LOTRMod.silverRing) {
                return new ItemStack(LOTRMod.silverNugget);
            }
            if (item == LOTRMod.goldRing) {
                return new ItemStack(Items.gold_nugget);
            }
            if (item == LOTRMod.mithrilRing) {
                return new ItemStack(LOTRMod.mithrilNugget);
            }
            if (item == LOTRMod.gobletGold) {
                return new ItemStack(Items.gold_ingot);
            }
            if (item == LOTRMod.gobletSilver) {
                return new ItemStack(LOTRMod.silver);
            }
            if (item == LOTRMod.gobletCopper) {
                return new ItemStack(LOTRMod.bronze);
            }
        }
        return null;
    }

    public int determineResourcesUsed(ItemStack itemstack, ItemStack material) {
        return this.determineResourcesUsed(itemstack, material, null);
    }

    public int determineResourcesUsed(ItemStack itemstack, ItemStack material, List<IRecipe> recursiveCheckedRecipes) {
        if (itemstack == null) {
            return 0;
        }
        Pair key = Pair.of((Object)itemstack.getItem(), (Object)itemstack.getItemDamage());
        if (unsmeltableCraftingCounts.containsKey((Object)key)) {
            return unsmeltableCraftingCounts.get((Object)key);
        }
        int count = 0;
        ArrayList<List<IRecipe>> allRecipeLists = new ArrayList<List<IRecipe>>();
        allRecipeLists.add(CraftingManager.getInstance().getRecipeList());
        EntityPlayer player = this.getProxyPlayer();
        for (LOTRBlockCraftingTable table : LOTRBlockCraftingTable.allCraftingTables) {
            Iterator container = LOTRMod.proxy.getServerGuiElement(table.tableGUIID, player, this.worldObj, 0, 0, 0);
            if (!(container instanceof LOTRContainerCraftingTable)) continue;
            LOTRContainerCraftingTable containerTable = (LOTRContainerCraftingTable)((Object)container);
            allRecipeLists.add(containerTable.recipeList);
        }
        allRecipeLists.add(LOTRRecipes.uncraftableUnsmeltingRecipes);
        if (recursiveCheckedRecipes == null) {
            recursiveCheckedRecipes = new ArrayList<IRecipe>();
        }
        block1: for (List recipes : allRecipeLists) {
            for (Object recipesObj : recipes) {
                int i;
                ShapedRecipes shaped;
                ShapelessRecipes shapeless;
                ItemStack result;
                Object ingredients;
                IRecipe irecipe = (IRecipe)recipesObj;
                if (recursiveCheckedRecipes.contains((Object)irecipe) || (result = irecipe.getRecipeOutput()) == null || result.getItem() != itemstack.getItem() || !itemstack.isItemStackDamageable() && result.getItemDamage() != itemstack.getItemDamage()) continue;
                recursiveCheckedRecipes.add(irecipe);
                if (irecipe instanceof ShapedRecipes) {
                    shaped = (ShapedRecipes)irecipe;
                    ingredients = shaped.recipeItems;
                    i = this.countMatchingIngredients(material, Arrays.asList(ingredients), recursiveCheckedRecipes);
                    if ((i /= result.stackSize) > 0) {
                        count = i;
                        break block1;
                    }
                }
                if (irecipe instanceof ShapelessRecipes) {
                    shapeless = (ShapelessRecipes)irecipe;
                    ingredients = shapeless.recipeItems;
                    i = this.countMatchingIngredients(material, (List)ingredients, recursiveCheckedRecipes);
                    if ((i /= result.stackSize) > 0) {
                        count = i;
                        break block1;
                    }
                }
                if (irecipe instanceof ShapedOreRecipe) {
                    shaped = (ShapedOreRecipe)irecipe;
                    ingredients = shaped.getInput();
                    i = this.countMatchingIngredients(material, Arrays.asList(ingredients), recursiveCheckedRecipes);
                    if ((i /= result.stackSize) > 0) {
                        count = i;
                        break block1;
                    }
                }
                if (irecipe instanceof ShapelessOreRecipe) {
                    shapeless = (ShapelessOreRecipe)irecipe;
                    ingredients = shapeless.getInput();
                    i = this.countMatchingIngredients(material, (List)ingredients, recursiveCheckedRecipes);
                    if ((i /= result.stackSize) > 0) {
                        count = i;
                        break block1;
                    }
                }
                if (!(irecipe instanceof LOTRRecipePoisonWeapon)) continue;
                LOTRRecipePoisonWeapon poison = (LOTRRecipePoisonWeapon)irecipe;
                ingredients = new Object[]{poison.getInputItem()};
                i = this.countMatchingIngredients(material, Arrays.asList(ingredients), recursiveCheckedRecipes);
                if ((i /= result.stackSize) <= 0) continue;
                count = i;
                break block1;
            }
        }
        unsmeltableCraftingCounts.put((Pair<Item, Integer>)key, count);
        return count;
    }

    public EntityPlayer getProxyPlayer() {
        if (!this.worldObj.isRemote) {
            return FakePlayerFactory.get((WorldServer)((WorldServer)this.worldObj), (GameProfile)new GameProfile(null, "LOTRUnsmeltery"));
        }
        return LOTRMod.proxy.getClientPlayer();
    }

    public int countMatchingIngredients(ItemStack material, List ingredientList, List<IRecipe> recursiveCheckedRecipes) {
        int i = 0;
        for (Object obj : ingredientList) {
            if (obj instanceof ItemStack) {
                ItemStack ingredient = (ItemStack)obj;
                if (OreDictionary.itemMatches((ItemStack)material, (ItemStack)ingredient, (boolean)false)) {
                    ++i;
                    continue;
                }
                int sub = this.determineResourcesUsed(ingredient, material, recursiveCheckedRecipes);
                if (sub <= 0) continue;
                i += sub;
                continue;
            }
            if (!(obj instanceof List)) continue;
            List oreIngredients = (List)obj;
            boolean matched = false;
            for (ItemStack ingredient : oreIngredients) {
                if (!OreDictionary.itemMatches((ItemStack)material, (ItemStack)ingredient, (boolean)false)) continue;
                matched = true;
                break;
            }
            if (matched) {
                ++i;
                continue;
            }
            for (ItemStack ingredient : oreIngredients) {
                int sub = this.determineResourcesUsed(ingredient, material, recursiveCheckedRecipes);
                if (sub <= 0) continue;
                i += sub;
            }
        }
        return i;
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        data.setBoolean("Active", this.serverActive);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, data);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound data = packet.func_148857_g();
        this.clientActive = data.getBoolean("Active");
    }
}

