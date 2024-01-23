/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.registry.RegistryDelegate
 *  cpw.mods.fml.common.registry.RegistryDelegate$Delegate
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraft.item.crafting.ShapelessRecipes
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.stats.Achievement
 *  net.minecraft.stats.AchievementList
 *  net.minecraft.stats.StatBase
 *  net.minecraft.stats.StatCrafting
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ObjectIntIdentityMap
 *  net.minecraft.util.RegistryNamespaced
 *  net.minecraft.util.RegistrySimple
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package lotr.common.block;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.RegistryDelegate;
import io.gitlab.dwarfyassassin.lotrucp.core.hooks.GenericModHooks;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import lotr.common.LOTRReflection;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.RegistrySimple;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class LOTRBlockReplacement {
    private static boolean initForgeHooks = false;

    public static void replaceVanillaBlock(Block oldBlock, Block newBlock, Class<? extends ItemBlock> itemClass) {
        try {
            Item oldItem = Item.getItemFromBlock((Block)oldBlock);
            int id = Block.blockRegistry.getIDForObject((Object)oldBlock);
            String blockName = Reflect.getBlockName(oldBlock);
            String registryName = Block.blockRegistry.getNameForObject((Object)oldBlock);
            String itemblockName = blockName;
            if (oldItem != null) {
                itemblockName = Reflect.getItemName(oldItem);
            }
            GenericModHooks.removeBlockFromOreDictionary(oldBlock);
            newBlock.setBlockName(blockName);
            Reflect.overwriteBlockList(oldBlock, newBlock);
            Reflect.setDelegateName(newBlock.delegate, oldBlock.delegate.name());
            Reflect.getUnderlyingIntMap(Block.blockRegistry).func_148746_a((Object)newBlock, id);
            Reflect.getUnderlyingObjMap(Block.blockRegistry).put(registryName, newBlock);
            if (!initForgeHooks) {
                ForgeHooks.isToolEffective((ItemStack)new ItemStack(Items.iron_shovel), (Block)Blocks.dirt, (int)0);
                initForgeHooks = true;
            }
            for (int meta = 0; meta <= 15; ++meta) {
                newBlock.setHarvestLevel(oldBlock.getHarvestTool(meta), oldBlock.getHarvestLevel(meta), meta);
            }
            if (itemClass != null) {
                Constructor<?>[] itemCtors;
                Constructor<?> itemCtor = null;
                for (Constructor<?> ct : itemCtors = itemClass.getConstructors()) {
                    Class<?>[] params = ct.getParameterTypes();
                    if (params.length != 1 || !Block.class.isAssignableFrom(params[0])) continue;
                    itemCtor = ct;
                    break;
                }
                ItemBlock itemblock = ((ItemBlock)itemCtor.newInstance(new Object[]{newBlock})).setUnlocalizedName(itemblockName);
                Reflect.setDelegateName(itemblock.delegate, oldItem.delegate.name());
                Reflect.getUnderlyingIntMap(Item.itemRegistry).func_148746_a((Object)itemblock, id);
                Reflect.getUnderlyingObjMap(Item.itemRegistry).put(registryName, itemblock);
                LOTRBlockReplacement.replaceBlockStats(id, newBlock, itemblock);
                LOTRBlockReplacement.replaceRecipesEtc((Item)itemblock);
            }
        }
        catch (Exception e) {
            FMLLog.severe((String)"Failed to replace vanilla block %s", (Object[])new Object[]{oldBlock.getUnlocalizedName()});
            throw new RuntimeException(e);
        }
    }

    private static void replaceBlockStats(int id, Block newBlock, ItemBlock itemblock) {
        LOTRBlockReplacement.replaceStat(id, StatList.mineBlockStatArray, (StatBase)new StatCrafting("stat.mineBlock." + id, (IChatComponent)new ChatComponentTranslation("stat.mineBlock", new Object[]{new ItemStack(newBlock).func_151000_E()}), (Item)itemblock));
        LOTRBlockReplacement.replaceStat(id, StatList.objectUseStats, (StatBase)new StatCrafting("stat.useItem." + id, (IChatComponent)new ChatComponentTranslation("stat.useItem", new Object[]{new ItemStack((Item)itemblock).func_151000_E()}), (Item)itemblock));
        LOTRBlockReplacement.replaceStat(id, StatList.objectCraftStats, (StatBase)new StatCrafting("stat.craftItem." + id, (IChatComponent)new ChatComponentTranslation("stat.craftItem", new Object[]{new ItemStack((Item)itemblock).func_151000_E()}), (Item)itemblock));
    }

    public static void replaceVanillaItem(Item oldItem, Item newItem) {
        try {
            int id = Item.itemRegistry.getIDForObject((Object)oldItem);
            String itemName = Reflect.getItemName(oldItem);
            String registryName = Item.itemRegistry.getNameForObject((Object)oldItem);
            GenericModHooks.removeItemFromOreDictionary(oldItem);
            newItem.setUnlocalizedName(itemName);
            Reflect.overwriteItemList(oldItem, newItem);
            Reflect.setDelegateName(newItem.delegate, oldItem.delegate.name());
            Reflect.getUnderlyingIntMap(Item.itemRegistry).func_148746_a((Object)newItem, id);
            Reflect.getUnderlyingObjMap(Item.itemRegistry).put(registryName, newItem);
            LOTRBlockReplacement.replaceItemStats(id, newItem);
            LOTRBlockReplacement.replaceRecipesEtc(newItem);
        }
        catch (Exception e) {
            FMLLog.severe((String)"Failed to replace vanilla item %s", (Object[])new Object[]{oldItem.getUnlocalizedName()});
            throw new RuntimeException(e);
        }
    }

    private static void replaceItemStats(int id, Item newItem) {
        LOTRBlockReplacement.replaceStat(id, StatList.objectUseStats, (StatBase)new StatCrafting("stat.useItem." + id, (IChatComponent)new ChatComponentTranslation("stat.useItem", new Object[]{new ItemStack(newItem).func_151000_E()}), newItem));
        LOTRBlockReplacement.replaceStat(id, StatList.objectCraftStats, (StatBase)new StatCrafting("stat.craftItem." + id, (IChatComponent)new ChatComponentTranslation("stat.craftItem", new Object[]{new ItemStack(newItem).func_151000_E()}), newItem));
        if (newItem.isDamageable()) {
            LOTRBlockReplacement.replaceStat(id, StatList.objectBreakStats, (StatBase)new StatCrafting("stat.breakItem." + id, (IChatComponent)new ChatComponentTranslation("stat.breakItem", new Object[]{new ItemStack(newItem).func_151000_E()}), newItem));
        }
    }

    private static void replaceStat(int id, StatBase[] stats, StatBase newStat) {
        StatBase oldStat = stats[id];
        if (oldStat != null && oldStat.statId.equals(newStat.statId)) {
            for (int i = 0; i < stats.length; ++i) {
                StatBase otherOldStat = stats[i];
                if (otherOldStat == null || !otherOldStat.statId.equals(oldStat.statId)) continue;
                StatList.allStats.remove((Object)otherOldStat);
                StatList.objectMineStats.remove((Object)otherOldStat);
                StatList.itemStats.remove((Object)otherOldStat);
                StatList.generalStats.remove((Object)otherOldStat);
                Reflect.getOneShotStats().remove(otherOldStat.statId);
                stats[i] = newStat;
            }
            newStat.registerStat();
        }
    }

    private static void replaceRecipesEtc(Item newItem) {
        String newItemName = newItem.getUnlocalizedName();
        List craftingRecipes = CraftingManager.getInstance().getRecipeList();
        for (Object obj : craftingRecipes) {
            ShapedRecipes recipe;
            ItemStack output;
            if (obj instanceof ShapedRecipes && (output = (recipe = (ShapedRecipes)obj).getRecipeOutput()) != null && output.getItem() != null && output.getItem().getUnlocalizedName().equals(newItemName)) {
                LOTRBlockReplacement.injectReplacementItem(output, newItem);
            }
            if (obj instanceof ShapelessRecipes && (output = (recipe = (ShapelessRecipes)obj).getRecipeOutput()) != null && output.getItem() != null && output.getItem().getUnlocalizedName().equals(newItemName)) {
                LOTRBlockReplacement.injectReplacementItem(output, newItem);
            }
            if (obj instanceof ShapedOreRecipe && (output = (recipe = (ShapedOreRecipe)obj).getRecipeOutput()) != null && output.getItem() != null && output.getItem().getUnlocalizedName().equals(newItemName)) {
                LOTRBlockReplacement.injectReplacementItem(output, newItem);
            }
            if (!(obj instanceof ShapelessOreRecipe) || (output = (recipe = (ShapelessOreRecipe)obj).getRecipeOutput()) == null || output.getItem() == null || !output.getItem().getUnlocalizedName().equals(newItemName)) continue;
            LOTRBlockReplacement.injectReplacementItem(output, newItem);
        }
        for (Object obj : AchievementList.achievementList) {
            Achievement a = (Achievement)obj;
            ItemStack icon = a.theItemStack;
            if (!icon.getItem().getUnlocalizedName().equals(newItem.getUnlocalizedName())) continue;
            LOTRBlockReplacement.injectReplacementItem(icon, newItem);
        }
    }

    private static void injectReplacementItem(ItemStack itemstack, Item newItem) {
        NBTTagCompound nbt = new NBTTagCompound();
        itemstack.writeToNBT(nbt);
        itemstack.readFromNBT(nbt);
    }

    private static class Reflect {
        private Reflect() {
        }

        private static void overwriteBlockList(Block oldBlock, Block newBlock) {
            try {
                Field[] declaredFields;
                Field field = null;
                for (Field f : declaredFields = Blocks.class.getDeclaredFields()) {
                    LOTRReflection.unlockFinalField(f);
                    if (f.get(null) != oldBlock) continue;
                    field = f;
                    break;
                }
                LOTRReflection.setFinalField(Blocks.class, null, newBlock, field);
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
            }
        }

        private static void overwriteItemList(Item oldItem, Item newItem) {
            try {
                Field[] declaredFields;
                Field field = null;
                for (Field f : declaredFields = Items.class.getDeclaredFields()) {
                    LOTRReflection.unlockFinalField(f);
                    if (f.get(null) != oldItem) continue;
                    field = f;
                    break;
                }
                LOTRReflection.setFinalField(Items.class, null, newItem, field);
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
            }
        }

        private static String getBlockName(Block block) {
            try {
                return (String)ObfuscationReflectionHelper.getPrivateValue(Block.class, (Object)block, (String[])new String[]{"unlocalizedName", "field_149770_b"});
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
                return null;
            }
        }

        private static String getItemName(Item item) {
            try {
                return (String)ObfuscationReflectionHelper.getPrivateValue(Item.class, (Object)item, (String[])new String[]{"unlocalizedName", "field_77774_bZ"});
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
                return null;
            }
        }

        private static ObjectIntIdentityMap getUnderlyingIntMap(RegistryNamespaced registry) {
            try {
                return (ObjectIntIdentityMap)ObfuscationReflectionHelper.getPrivateValue(RegistryNamespaced.class, (Object)registry, (String[])new String[]{"underlyingIntegerMap", "field_148759_a"});
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
                return null;
            }
        }

        private static Map getUnderlyingObjMap(RegistryNamespaced registry) {
            try {
                return (Map)ObfuscationReflectionHelper.getPrivateValue(RegistrySimple.class, (Object)registry, (String[])new String[]{"registryObjects", "field_82596_a"});
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
                return null;
            }
        }

        private static void setDelegateName(RegistryDelegate rd, String name) {
            RegistryDelegate.Delegate delegate = (RegistryDelegate.Delegate)rd;
            try {
                ObfuscationReflectionHelper.setPrivateValue(RegistryDelegate.Delegate.class, (Object)delegate, (Object)name, (String[])new String[]{"name"});
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
            }
        }

        private static Map getOneShotStats() {
            try {
                return (Map)ObfuscationReflectionHelper.getPrivateValue(StatList.class, null, (String[])new String[]{"oneShotStats", "field_75942_a"});
            }
            catch (Exception e) {
                LOTRReflection.logFailure(e);
                return null;
            }
        }
    }

}

