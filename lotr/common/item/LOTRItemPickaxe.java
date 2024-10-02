/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRMaterial;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemPickaxe
extends ItemPickaxe {
    public LOTRItemPickaxe(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemPickaxe(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabTools);
    }

    public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ, int blockFace, float hitX, float hitY, float hitZ) {
        ItemStack torchStack = this.findTorchInInventory(playerEntity);
        if (torchStack == null) {
            return false;
        }
        boolean success = false;
        switch (blockFace) {
            case 0: {
                success = this.placeTorch(world, targetX, targetY - 1, targetZ, torchStack);
                break;
            }
            case 1: {
                success = this.placeTorch(world, targetX, targetY + 1, targetZ, torchStack);
                break;
            }
            case 2: {
                success = this.placeTorch(world, targetX, targetY, targetZ - 1, torchStack);
                break;
            }
            case 3: {
                success = this.placeTorch(world, targetX, targetY, targetZ + 1, torchStack);
                break;
            }
            case 4: {
                success = this.placeTorch(world, targetX - 1, targetY, targetZ, torchStack);
                break;
            }
            case 5: {
                success = this.placeTorch(world, targetX + 1, targetY, targetZ, torchStack);
            }
        }
        if (success && !playerEntity.capabilities.isCreativeMode) {
            --torchStack.stackSize;
            if (torchStack.stackSize <= 0) {
                playerEntity.inventory.consumeInventoryItem(torchStack.getItem());
            }
        }
        return success;
    }

    private ItemStack findTorchInInventory(EntityPlayer playerEntity) {
        Item[] torches = new Item[]{Item.getItemFromBlock((Block)Blocks.torch), Item.getItemFromBlock((Block)LOTRMod.orcTorch), Item.getItemFromBlock((Block)LOTRMod.dwarvenTorch), Item.getItemFromBlock((Block)LOTRMod.blubberTorch), Item.getItemFromBlock((Block)LOTRMod.dwarvenTorchGold), Item.getItemFromBlock((Block)LOTRMod.dwarvenTorchSilver), Item.getItemFromBlock((Block)LOTRMod.dwarvenTorchMithril), Item.getItemFromBlock((Block)LOTRMod.mallornTorchSilver), Item.getItemFromBlock((Block)LOTRMod.woodElvenTorch), Item.getItemFromBlock((Block)LOTRMod.highElvenTorch), Item.getItemFromBlock((Block)LOTRMod.morgulTorch), Item.getItemFromBlock((Block)LOTRMod.mallornTorchBlue), Item.getItemFromBlock((Block)LOTRMod.mallornTorchGold), Item.getItemFromBlock((Block)LOTRMod.mallornTorchGreen), Item.getItemFromBlock((Block)LOTRMod.avariTorch), Item.getItemFromBlock((Block)LOTRMod.reddwarvenTorchSilver)};
        for (int i = 0; i < playerEntity.inventory.getSizeInventory(); ++i) {
            ItemStack stack = playerEntity.inventory.getStackInSlot(i);
            if (stack == null) continue;
            for (Item torch : torches) {
                if (stack.getItem() != torch) continue;
                return stack;
            }
        }
        return null;
    }

    private boolean placeTorch(World world, int x, int y, int z, ItemStack torchStack) {
        if (world.isAirBlock(x, y, z)) {
            world.setBlock(x, y, z, Block.getBlockFromItem((Item)torchStack.getItem()));
            world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "dig.wood", 1.0f, 1.0f);
            return true;
        }
        return false;
    }
}

