/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemMap
extends Item {
    private static final int MAX_DISTANCE = 5000;
    private static final int LOOT_COUNT = 3;
    private IIcon inactiveIcon;
    private IIcon activeIcon;

    public LOTRItemMap() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
        this.setMaxStackSize(1);
    }

    public void registerIcons(IIconRegister iconRegister) {
        this.inactiveIcon = iconRegister.registerIcon("lotr:map_inactive");
        this.activeIcon = iconRegister.registerIcon("lotr:map_active");
        this.itemIcon = this.inactiveIcon;
    }

    public IIcon getIcon(ItemStack stack, int renderPass) {
        if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("isGenerated")) {
            return this.activeIcon;
        }
        return this.inactiveIcon;
    }

    public IIcon getIconIndex(ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("isGenerated")) {
            return this.activeIcon;
        }
        return this.inactiveIcon;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (!stack.hasTagCompound() || !stack.getTagCompound().getBoolean("isGenerated")) {
                this.createTreasureMap(stack, player);
            } else if (this.isPlayerAtTreasure(stack, player)) {
                this.discoverTreasure(stack, world, player);
            }
        }
        return stack;
    }

    private void createTreasureMap(ItemStack stack, EntityPlayer player) {
        NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        Random random = new Random();
        int targetX = (int)player.posX + random.nextInt(5000) - random.nextInt(5000);
        int targetZ = (int)player.posZ + random.nextInt(5000) - random.nextInt(5000);
        tag.setBoolean("isGenerated", true);
        tag.setInteger("targetX", targetX);
        tag.setInteger("targetZ", targetZ);
        stack.setTagCompound(tag);
        player.worldObj.playSoundAtEntity((Entity)player, "lotr:mapsound", 0.5f, 1.0f + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1f);
    }

    private boolean isPlayerAtTreasure(ItemStack stack, EntityPlayer player) {
        NBTTagCompound tag = stack.getTagCompound();
        int targetX = tag.getInteger("targetX");
        int targetZ = tag.getInteger("targetZ");
        return Math.floor(player.posX) == (double)targetX && Math.floor(player.posZ) == (double)targetZ;
    }

    private void discoverTreasure(ItemStack stack, World world, EntityPlayer player) {
        --stack.stackSize;
        List<ItemStack> loot = this.generateLoot(world, player);
        Collections.shuffle(loot);
        for (int i = 0; i < 3; ++i) {
            if (player.inventory.addItemStackToInventory(loot.get(i))) continue;
            player.dropPlayerItemWithRandomChoice(loot.get(i), false);
        }
        world.playSoundAtEntity((Entity)player, "lotr:event.trade", 0.5f, 1.0f + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1f);
    }

    private List<ItemStack> generateLoot(World world, EntityPlayer player) {
        Random rand = new Random();
        ArrayList<ItemStack> lootList = new ArrayList<ItemStack>();
        LOTRLevelData.getData(player).addAchievement(LOTRAchievement.treasure);
        lootList.add(new ItemStack(LOTRMod.silverCoin, rand.nextInt(20) + 1));
        lootList.add(new ItemStack(LOTRMod.ruby, rand.nextInt(1) + 1));
        lootList.add(new ItemStack(LOTRMod.emerald, rand.nextInt(1) + 1));
        lootList.add(new ItemStack(LOTRMod.opal, rand.nextInt(1) + 1));
        lootList.add(new ItemStack(LOTRMod.sapphire, rand.nextInt(1) + 1));
        lootList.add(new ItemStack(LOTRMod.diamond, rand.nextInt(1) + 1));
        lootList.add(new ItemStack(LOTRMod.bronzeNugget, rand.nextInt(1) + 1));
        lootList.add(new ItemStack(Items.iron_ingot, rand.nextInt(1) + 1));
        lootList.add(new ItemStack(Items.gold_ingot, rand.nextInt(1) + 1));
        lootList.add(new ItemStack(Items.gold_nugget, rand.nextInt(1) + 1));
        if (rand.nextDouble() < 0.3) {
            lootList.add(new ItemStack(LOTRMod.silverRing, rand.nextInt(1) + 1));
            lootList.add(new ItemStack(LOTRMod.goldRing, rand.nextInt(1) + 1));
            lootList.add(new ItemStack(LOTRMod.mithrilRing));
            lootList.add(new ItemStack(LOTRMod.mithrilNugget));
            lootList.add(new ItemStack(LOTRMod.silverCoin, rand.nextInt(20) + 1, 1));
            lootList.add(new ItemStack(LOTRMod.silver, rand.nextInt(1) + 1));
            if (rand.nextDouble() < 0.1) {
                lootList.add(new ItemStack(LOTRMod.totemOfUndying));
                lootList.add(new ItemStack(LOTRMod.treasureMap));
                lootList.add(new ItemStack(LOTRMod.silverCoin, rand.nextInt(10) + 1, 2));
                lootList.add(new ItemStack(LOTRMod.magicClover));
                lootList.add(new ItemStack(LOTRMod.mithril));
                world.playSoundAtEntity((Entity)player, "random.levelup", 0.5f, 1.0f);
            }
            if (rand.nextDouble() < 0.092) {
                lootList.add(new ItemStack(LOTRMod.treasureMap));
                lootList.add(new ItemStack(LOTRMod.ring_bright));
                lootList.add(new ItemStack(LOTRMod.totemOfUndying, rand.nextInt(1) + 1));
                lootList.add(new ItemStack(LOTRMod.totemOfUndyingPlus, rand.nextInt(1) + 1));
                lootList.add(new ItemStack(LOTRMod.magicClover, rand.nextInt(1) + 1));
                lootList.add(new ItemStack(LOTRMod.diggingBook2));
                lootList.add(new ItemStack(LOTRMod.mithrilRaw));
                lootList.add(new ItemStack(LOTRMod.graalGold));
                LOTRLevelData.getData(player).addAchievement(LOTRAchievement.treasureRare);
                world.playSoundAtEntity((Entity)player, "lotr:item.levelup", 0.5f, 1.0f);
            }
            if (rand.nextDouble() < 0.08) {
                lootList.add(new ItemStack(LOTRMod.treasureMap, rand.nextInt(1) + 1));
                lootList.add(new ItemStack(LOTRMod.ring_bright));
                lootList.add(new ItemStack(LOTRMod.silverCoin, rand.nextInt(10) + 1, 3));
                lootList.add(new ItemStack(LOTRMod.totemOfUndying, rand.nextInt(1) + 2));
                lootList.add(new ItemStack(LOTRMod.totemOfUndyingPlus, rand.nextInt(1) + 2));
                lootList.add(new ItemStack(LOTRMod.magicCloverPlus));
                lootList.add(new ItemStack(LOTRMod.graalMithril));
                lootList.add(new ItemStack(LOTRMod.diggingBook2, rand.nextInt(1) + 2));
                lootList.add(new ItemStack(LOTRMod.soulboundBook, rand.nextInt(1) + 2));
                LOTRLevelData.getData(player).addAchievement(LOTRAchievement.treasureUltraRare);
                world.playSoundAtEntity((Entity)player, "lotr:item.levelupskyrim", 0.5f, 1.0f);
            }
            if (rand.nextDouble() < 0.008) {
                ItemStack coinStack = new ItemStack(LOTRMod.silverCoin, 1, 6);
                if (player.inventory.addItemStackToInventory(coinStack)) {
                    LOTRLevelData.getData(player).addAchievement(LOTRAchievement.treasureMillionere);
                    world.playSoundAtEntity((Entity)player, "lotr:item.millionere", 0.5f, 1.0f);
                } else {
                    player.dropPlayerItemWithRandomChoice(coinStack, false);
                }
            }
        }
        return lootList;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        super.addInformation(stack, player, list, advanced);
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.getBoolean("isGenerated")) {
                int targetX = tag.getInteger("targetX");
                int targetZ = tag.getInteger("targetZ");
                list.add((Object)EnumChatFormatting.YELLOW + "X: " + targetX + " Z: " + targetZ);
                list.add((Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"lotr.map.activated", (Object[])new Object[0]));
            } else {
                list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
            }
        } else {
            list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
        }
    }
}

