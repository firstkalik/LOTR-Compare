/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.GuiNewChat
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.client.gui.inventory.GuiDispenser
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityMinecartContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.AnimalChest
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.ContainerDispenser
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityDispenser
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package lotr.common;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cubex2.mods.multipagechest.ContainerMultiPageChest;
import cubex2.mods.multipagechest.InventoryMPCPage;
import cubex2.mods.multipagechest.TileEntityMultiPageChest;
import cubex2.mods.multipagechest.client.GuiMultiPageChest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotr.client.gui.LOTRGuiAlloyForge;
import lotr.client.gui.LOTRGuiAnvil;
import lotr.client.gui.LOTRGuiArmorStand;
import lotr.client.gui.LOTRGuiBarrel;
import lotr.client.gui.LOTRGuiBeacon;
import lotr.client.gui.LOTRGuiBookshelf;
import lotr.client.gui.LOTRGuiBrandingIron;
import lotr.client.gui.LOTRGuiChestWithPouch;
import lotr.client.gui.LOTRGuiCoinExchange;
import lotr.client.gui.LOTRGuiCraftingTable;
import lotr.client.gui.LOTRGuiDaleCracker;
import lotr.client.gui.LOTRGuiEditSign;
import lotr.client.gui.LOTRGuiGollum;
import lotr.client.gui.LOTRGuiHiredFarmerInventory;
import lotr.client.gui.LOTRGuiHiredInteract;
import lotr.client.gui.LOTRGuiHiredWarriorInventory;
import lotr.client.gui.LOTRGuiHobbitOven;
import lotr.client.gui.LOTRGuiHornSelect;
import lotr.client.gui.LOTRGuiMap;
import lotr.client.gui.LOTRGuiMenu;
import lotr.client.gui.LOTRGuiMercenaryHire;
import lotr.client.gui.LOTRGuiMercenaryInteract;
import lotr.client.gui.LOTRGuiMillstone;
import lotr.client.gui.LOTRGuiMobSpawner;
import lotr.client.gui.LOTRGuiMountInventory;
import lotr.client.gui.LOTRGuiNPCMountInventory;
import lotr.client.gui.LOTRGuiNPCRespawner;
import lotr.client.gui.LOTRGuiPouch;
import lotr.client.gui.LOTRGuiRedBook;
import lotr.client.gui.LOTRGuiSquadronItem;
import lotr.client.gui.LOTRGuiTrade;
import lotr.client.gui.LOTRGuiTradeInteract;
import lotr.client.gui.LOTRGuiTradeUnitTradeInteract;
import lotr.client.gui.LOTRGuiUnitTrade;
import lotr.client.gui.LOTRGuiUnitTradeInteract;
import lotr.client.gui.LOTRGuiUnsmeltery;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRGuiMessageTypes;
import lotr.common.LOTRMod;
import lotr.common.LOTRReflection;
import lotr.common.LOTRTickHandlerServer;
import lotr.common.block.LOTRBlockFlowerPot;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.item.LOTREntityBanner;
import lotr.common.entity.npc.LOTREntityGollum;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNPCRideable;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.entity.npc.LOTRUnitTradeable;
import lotr.common.fac.LOTRAlignmentBonusMap;
import lotr.common.fac.LOTRFaction;
import lotr.common.inventory.LOTRContainerAlloyForge;
import lotr.common.inventory.LOTRContainerAnvil;
import lotr.common.inventory.LOTRContainerArmorStand;
import lotr.common.inventory.LOTRContainerBarrel;
import lotr.common.inventory.LOTRContainerBookshelf;
import lotr.common.inventory.LOTRContainerChestWithPouch;
import lotr.common.inventory.LOTRContainerCoinExchange;
import lotr.common.inventory.LOTRContainerCraftingTable;
import lotr.common.inventory.LOTRContainerDaleCracker;
import lotr.common.inventory.LOTRContainerGollum;
import lotr.common.inventory.LOTRContainerHiredFarmerInventory;
import lotr.common.inventory.LOTRContainerHiredWarriorInventory;
import lotr.common.inventory.LOTRContainerHobbitOven;
import lotr.common.inventory.LOTRContainerMillstone;
import lotr.common.inventory.LOTRContainerMountInventory;
import lotr.common.inventory.LOTRContainerNPCMountInventory;
import lotr.common.inventory.LOTRContainerPouch;
import lotr.common.inventory.LOTRContainerTrade;
import lotr.common.inventory.LOTRContainerUnitTrade;
import lotr.common.inventory.LOTRContainerUnsmeltery;
import lotr.common.item.LOTRItemDaleCracker;
import lotr.common.item.LOTRItemPouch;
import lotr.common.network.LOTRPacketClientsideGUI;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.tileentity.LOTRTileEntityAlloyForgeBase;
import lotr.common.tileentity.LOTRTileEntityArmorStand;
import lotr.common.tileentity.LOTRTileEntityBarrel;
import lotr.common.tileentity.LOTRTileEntityBeacon;
import lotr.common.tileentity.LOTRTileEntityBookshelf;
import lotr.common.tileentity.LOTRTileEntityChest;
import lotr.common.tileentity.LOTRTileEntityDartTrap;
import lotr.common.tileentity.LOTRTileEntityHobbitOven;
import lotr.common.tileentity.LOTRTileEntityMillstone;
import lotr.common.tileentity.LOTRTileEntitySign;
import lotr.common.tileentity.LOTRTileEntityUnsmeltery;
import lotr.common.util.LOTRGuiBank;
import lotr.common.world.map.LOTRAbstractWaypoint;
import lotr.common.world.map.LOTRConquestZone;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTRCommonProxy
implements IGuiHandler {
    public static void sendClientsideGUI(EntityPlayerMP entityplayer, int guiID, int x, int y, int z) {
        LOTRPacketClientsideGUI packet = new LOTRPacketClientsideGUI(guiID, x, y, z);
        LOTRPacketHandler.networkWrapper.sendTo((IMessage)packet, entityplayer);
    }

    public void registerRenderInformation() {
    }

    public void registerTileEntitySpecialRenderer() {
    }

    public void onPreload() {
    }

    public void onLoad() {
    }

    public void onPostload() {
    }

    public void testReflection(World world) {
        LOTRReflection.testAll(world);
    }

    public static int packGuiIDWithSlot(int guiID, int slotNo) {
        return guiID | slotNo << 16;
    }

    public static boolean testForSlotPackedGuiID(int fullID, int guiID) {
        return (fullID & 0xFFFF) == guiID;
    }

    public static int unpackSlot(int fullID) {
        return fullID >> 16;
    }

    public Object getServerGuiElement(int ID, EntityPlayer entityplayer, World world, int i, int j, int k) {
        TileEntity unsmeltery;
        Entity minecart;
        Entity entity;
        TileEntity millstone;
        TileEntity barrel;
        IInventory chest2;
        TileEntity forge;
        LOTREntityNPC npc;
        TileEntity stand;
        TileEntity bookshelf;
        int slot;
        TileEntity trap;
        TileEntity beacon;
        TileEntity chest;
        TileEntity oven;
        TileEntity te = world.getTileEntity(i, j, k);
        if (te instanceof TileEntityMultiPageChest) {
            return new ContainerMultiPageChest((IInventory)entityplayer.inventory, new InventoryMPCPage((TileEntityMultiPageChest)te, ID));
        }
        if (ID == 94) {
            return new LOTRGuiBank(entityplayer);
        }
        if (ID == 0 && (oven = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityHobbitOven) {
            return new LOTRContainerHobbitOven(entityplayer.inventory, (LOTRTileEntityHobbitOven)oven);
        }
        if (ID == 1) {
            return new LOTRContainerCraftingTable.Morgul(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 2) {
            return new LOTRContainerCraftingTable.Elven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 3 && (entity = world.getEntityByID(i)) instanceof LOTRTradeable) {
            return new LOTRContainerTrade(entityplayer.inventory, (LOTRTradeable)entity, world);
        }
        if (ID == 4) {
            return new LOTRContainerCraftingTable.Dwarven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 5 && (forge = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityAlloyForgeBase) {
            return new LOTRContainerAlloyForge(entityplayer.inventory, (LOTRTileEntityAlloyForgeBase)forge);
        }
        if (ID == 7 && (entity = world.getEntityByID(i)) instanceof LOTRUnitTradeable) {
            return new LOTRContainerUnitTrade(entityplayer, (LOTRUnitTradeable)entity, world);
        }
        if (ID == 8) {
            return new LOTRContainerCraftingTable.Uruk(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 10 && (entity = world.getEntityByID(i)) instanceof LOTREntityGollum) {
            return new LOTRContainerGollum(entityplayer.inventory, (LOTREntityGollum)entity);
        }
        if (ID == 12) {
            return new LOTRContainerCraftingTable.WoodElven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 13) {
            return new LOTRContainerCraftingTable.Gondorian(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 14) {
            return new LOTRContainerCraftingTable.Rohirric(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 15 && LOTRItemPouch.isHoldingPouch(entityplayer, i)) {
            return new LOTRContainerPouch(entityplayer, i);
        }
        if (ID == 16 && (barrel = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityBarrel) {
            return new LOTRContainerBarrel(entityplayer.inventory, (LOTRTileEntityBarrel)barrel);
        }
        if (ID == 17 && (stand = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityArmorStand) {
            return new LOTRContainerArmorStand(entityplayer.inventory, (LOTRTileEntityArmorStand)stand);
        }
        if (ID == 18) {
            return new LOTRContainerCraftingTable.Dunlending(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 22 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            npc = (LOTREntityNPC)entity;
            if (npc.hiredNPCInfo.isActive && npc.hiredNPCInfo.getHiringPlayer() == entityplayer && npc.hiredNPCInfo.getTask() == LOTRHiredNPCInfo.Task.FARMER) {
                return new LOTRContainerHiredFarmerInventory(entityplayer.inventory, npc);
            }
        }
        if (ID == 23) {
            return new LOTRContainerCraftingTable.Angmar(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 25) {
            return new LOTRContainerCraftingTable.NearHarad(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 26) {
            return new LOTRContainerCraftingTable.HighElven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 27) {
            return new LOTRContainerCraftingTable.BlueDwarven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 28) {
            return new LOTRContainerCraftingTable.Ranger(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 29) {
            entity = world.getEntityByID(i);
            if (entity instanceof LOTREntityHorse) {
                LOTREntityHorse horse = (LOTREntityHorse)entity;
                return new LOTRContainerMountInventory((IInventory)entityplayer.inventory, (IInventory)LOTRReflection.getHorseInv(horse), horse);
            }
            if (entity instanceof LOTREntityNPCRideable && ((LOTREntityNPCRideable)(npc = (LOTREntityNPCRideable)entity)).getMountInventory() != null) {
                return new LOTRContainerNPCMountInventory((IInventory)entityplayer.inventory, ((LOTREntityNPCRideable)npc).getMountInventory(), (LOTREntityNPCRideable)npc);
            }
        }
        if (ID == 30) {
            return new LOTRContainerCraftingTable.DolGuldur(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 31) {
            return new LOTRContainerCraftingTable.Gundabad(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 34) {
            return new LOTRContainerCraftingTable.HalfTroll(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 35 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            npc = (LOTREntityNPC)entity;
            return new LOTRContainerCoinExchange(entityplayer, npc);
        }
        if (ID == 36) {
            return new LOTRContainerCraftingTable.DolAmroth(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 37) {
            return new LOTRContainerCraftingTable.Moredain(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 38 && (unsmeltery = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityUnsmeltery) {
            return new LOTRContainerUnsmeltery(entityplayer.inventory, (LOTRTileEntityUnsmeltery)unsmeltery);
        }
        if (ID == 39) {
            return new LOTRContainerCraftingTable.Tauredain(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 40 && (trap = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityDartTrap) {
            return new ContainerDispenser((IInventory)entityplayer.inventory, (TileEntityDispenser)((LOTRTileEntityDartTrap)trap));
        }
        if (ID == 41 && (chest = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityChest) {
            return new ContainerChest((IInventory)entityplayer.inventory, (IInventory)((LOTRTileEntityChest)chest));
        }
        if (ID == 42) {
            return new LOTRContainerCraftingTable.Dale(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 43) {
            return new LOTRContainerCraftingTable.Dorwinion(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 44) {
            return new LOTRContainerCraftingTable.Hobbit(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 46 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            npc = (LOTREntityNPC)entity;
            if (npc.hiredNPCInfo.isActive && npc.hiredNPCInfo.getHiringPlayer() == entityplayer && npc.hiredNPCInfo.getTask() == LOTRHiredNPCInfo.Task.WARRIOR) {
                return new LOTRContainerHiredWarriorInventory(entityplayer.inventory, npc);
            }
        }
        if (ID == 48 && entityplayer.inventory.getCurrentItem() != null && entityplayer.inventory.getCurrentItem().getItem() instanceof LOTRItemDaleCracker) {
            return new LOTRContainerDaleCracker(entityplayer);
        }
        if (ID == 49) {
            return new LOTRContainerCraftingTable.Rhun(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 50 && (beacon = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityBeacon) {
            ((LOTRTileEntityBeacon)beacon).addEditingPlayer(entityplayer);
        }
        if (ID == 51) {
            return new LOTRContainerCraftingTable.Rivendell(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 52 && (millstone = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityMillstone) {
            return new LOTRContainerMillstone(entityplayer.inventory, (LOTRTileEntityMillstone)millstone);
        }
        if (ID == 53) {
            return new LOTRContainerAnvil(entityplayer, i, j, k);
        }
        if (ID == 54 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            return new LOTRContainerAnvil(entityplayer, (LOTREntityNPC)entity);
        }
        if (ID == 55 && (bookshelf = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityBookshelf) {
            return new LOTRContainerBookshelf((IInventory)entityplayer.inventory, (LOTRTileEntityBookshelf)bookshelf);
        }
        if (ID == 56) {
            return new LOTRContainerCraftingTable.Umbar(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 57) {
            return new LOTRContainerCraftingTable.Gulf(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 59 && (entity = world.getEntityByID(i)) instanceof LOTRMercenary) {
            return new LOTRContainerUnitTrade(entityplayer, (LOTRMercenary)entity, world);
        }
        if (ID == 62) {
            return new LOTRContainerCraftingTable.Bree(entityplayer.inventory, world, i, j, k);
        }
        if (LOTRCommonProxy.testForSlotPackedGuiID(ID, 63) && LOTRItemPouch.isHoldingPouch(entityplayer, slot = LOTRCommonProxy.unpackSlot(ID)) && (chest2 = LOTRItemPouch.getChestInvAt(entityplayer, world, i, j, k)) != null) {
            return new LOTRContainerChestWithPouch(entityplayer, slot, chest2);
        }
        if (LOTRCommonProxy.testForSlotPackedGuiID(ID, 64) && LOTRItemPouch.isHoldingPouch(entityplayer, slot = LOTRCommonProxy.unpackSlot(ID)) && (minecart = world.getEntityByID(i)) instanceof EntityMinecartContainer) {
            return new LOTRContainerChestWithPouch(entityplayer, slot, (IInventory)((EntityMinecartContainer)minecart));
        }
        if (ID == 65) {
            return new LOTRContainerCraftingTable.Red(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 66) {
            return new LOTRContainerCraftingTable.Angband(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 67) {
            return new LOTRContainerCraftingTable.Erebor(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 68) {
            return new LOTRContainerCraftingTable.Wind(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 69) {
            return new LOTRContainerCraftingTable.WickedDwarf(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 70) {
            return new LOTRContainerCraftingTable.Avari(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 71) {
            return new LOTRContainerCraftingTable.DarkElf(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 94) {
            return new LOTRGuiBank(entityplayer);
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer entityplayer, World world, int i, int j, int k) {
        LOTREntityNPC npc;
        TileEntity trap;
        TileEntity oven;
        Entity entity;
        TileEntity barrel;
        TileEntity forge;
        TileEntity stand;
        TileEntity beacon;
        TileEntity chest;
        TileEntity unsmeltery;
        TileEntity millstone;
        TileEntity te = world.getTileEntity(i, j, k);
        if (te instanceof TileEntityMultiPageChest) {
            return new GuiMultiPageChest(entityplayer.inventory, (TileEntityMultiPageChest)te, ID);
        }
        if (ID == 94) {
            return new LOTRGuiBank(entityplayer);
        }
        if (ID == 0 && (oven = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityHobbitOven) {
            return new LOTRGuiHobbitOven(entityplayer.inventory, (LOTRTileEntityHobbitOven)oven);
        }
        if (ID == 1) {
            return new LOTRGuiCraftingTable.Morgul(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 2) {
            return new LOTRGuiCraftingTable.Elven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 3 && (entity = world.getEntityByID(i)) instanceof LOTRTradeable) {
            return new LOTRGuiTrade(entityplayer.inventory, (LOTRTradeable)entity, world);
        }
        if (ID == 4) {
            return new LOTRGuiCraftingTable.Dwarven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 5 && (forge = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityAlloyForgeBase) {
            return new LOTRGuiAlloyForge(entityplayer.inventory, (LOTRTileEntityAlloyForgeBase)forge);
        }
        if (ID == 6) {
            return new LOTRGuiMobSpawner(world, i, j, k);
        }
        if (ID == 7 && (entity = world.getEntityByID(i)) instanceof LOTRUnitTradeable) {
            return new LOTRGuiUnitTrade(entityplayer, (LOTRUnitTradeable)entity, world);
        }
        if (ID == 8) {
            return new LOTRGuiCraftingTable.Uruk(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 9) {
            return new LOTRGuiHornSelect();
        }
        if (ID == 10 && (entity = world.getEntityByID(i)) instanceof LOTREntityGollum) {
            return new LOTRGuiGollum(entityplayer.inventory, (LOTREntityGollum)entity);
        }
        if (ID == 11) {
            return LOTRGuiMenu.openMenu(entityplayer);
        }
        if (ID == 12) {
            return new LOTRGuiCraftingTable.WoodElven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 13) {
            return new LOTRGuiCraftingTable.Gondorian(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 14) {
            return new LOTRGuiCraftingTable.Rohirric(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 15) {
            return new LOTRGuiPouch(entityplayer, i);
        }
        if (ID == 16 && (barrel = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityBarrel) {
            return new LOTRGuiBarrel(entityplayer.inventory, (LOTRTileEntityBarrel)barrel);
        }
        if (ID == 17 && (stand = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityArmorStand) {
            return new LOTRGuiArmorStand(entityplayer.inventory, (LOTRTileEntityArmorStand)stand);
        }
        if (ID == 18) {
            return new LOTRGuiCraftingTable.Dunlending(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 19 && (entity = world.getEntityByID(i)) instanceof LOTRTradeable) {
            return new LOTRGuiTradeInteract((LOTREntityNPC)entity);
        }
        if (ID == 20 && (entity = world.getEntityByID(i)) instanceof LOTRUnitTradeable) {
            return new LOTRGuiUnitTradeInteract((LOTREntityNPC)entity);
        }
        if (ID == 21 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            return new LOTRGuiHiredInteract((LOTREntityNPC)entity);
        }
        if (ID == 22 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            npc = (LOTREntityNPC)entity;
            if (npc.hiredNPCInfo.isActive && npc.hiredNPCInfo.getHiringPlayer() == entityplayer && npc.hiredNPCInfo.getTask() == LOTRHiredNPCInfo.Task.FARMER) {
                return new LOTRGuiHiredFarmerInventory(entityplayer.inventory, npc);
            }
        }
        if (ID == 23) {
            return new LOTRGuiCraftingTable.Angmar(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 24 && (entity = world.getEntityByID(i)) instanceof LOTRTradeable) {
            return new LOTRGuiTradeUnitTradeInteract((LOTREntityNPC)entity);
        }
        if (ID == 25) {
            return new LOTRGuiCraftingTable.NearHarad(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 26) {
            return new LOTRGuiCraftingTable.HighElven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 27) {
            return new LOTRGuiCraftingTable.BlueDwarven(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 28) {
            return new LOTRGuiCraftingTable.Ranger(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 29) {
            LOTREntityNPCRideable npc2;
            entity = world.getEntityByID(i);
            int invSize = j;
            if (entity instanceof LOTREntityHorse) {
                LOTREntityHorse horse = (LOTREntityHorse)entity;
                return new LOTRGuiMountInventory((IInventory)entityplayer.inventory, (IInventory)new AnimalChest(horse.getCommandSenderName(), invSize), horse);
            }
            if (entity instanceof LOTREntityNPCRideable && (npc2 = (LOTREntityNPCRideable)entity).getMountInventory() != null) {
                return new LOTRGuiNPCMountInventory((IInventory)entityplayer.inventory, (IInventory)new AnimalChest(npc2.getCommandSenderName(), invSize), npc2);
            }
        }
        if (ID == 30) {
            return new LOTRGuiCraftingTable.DolGuldur(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 31) {
            return new LOTRGuiCraftingTable.Gundabad(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 32) {
            return new LOTRGuiRedBook();
        }
        if (ID == 33) {
            return new LOTRGuiSquadronItem();
        }
        if (ID == 34) {
            return new LOTRGuiCraftingTable.HalfTroll(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 35 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            npc = (LOTREntityNPC)entity;
            return new LOTRGuiCoinExchange(entityplayer, npc);
        }
        if (ID == 36) {
            return new LOTRGuiCraftingTable.DolAmroth(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 37) {
            return new LOTRGuiCraftingTable.Moredain(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 38 && (unsmeltery = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityUnsmeltery) {
            return new LOTRGuiUnsmeltery(entityplayer.inventory, (LOTRTileEntityUnsmeltery)unsmeltery);
        }
        if (ID == 39) {
            return new LOTRGuiCraftingTable.Tauredain(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 40 && (trap = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityDartTrap) {
            return new GuiDispenser(entityplayer.inventory, (TileEntityDispenser)((LOTRTileEntityDartTrap)trap));
        }
        if (ID == 41 && (chest = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityChest) {
            return new GuiChest((IInventory)entityplayer.inventory, (IInventory)((LOTRTileEntityChest)chest));
        }
        if (ID == 42) {
            return new LOTRGuiCraftingTable.Dale(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 43) {
            return new LOTRGuiCraftingTable.Dorwinion(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 44) {
            return new LOTRGuiCraftingTable.Hobbit(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 45 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPCRespawner) {
            return new LOTRGuiNPCRespawner((LOTREntityNPCRespawner)entity);
        }
        if (ID == 46 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            npc = (LOTREntityNPC)entity;
            if (npc.hiredNPCInfo.isActive && npc.hiredNPCInfo.getHiringPlayer() == entityplayer && npc.hiredNPCInfo.getTask() == LOTRHiredNPCInfo.Task.WARRIOR) {
                return new LOTRGuiHiredWarriorInventory(entityplayer.inventory, npc);
            }
        }
        if (ID == 47) {
            Block block = world.getBlock(i, j, k);
            int meta = world.getBlockMetadata(i, j, k);
            LOTRTileEntitySign fake = (LOTRTileEntitySign)block.createTileEntity(world, meta);
            fake.setWorldObj(world);
            fake.xCoord = i;
            fake.yCoord = j;
            fake.zCoord = k;
            fake.isFakeGuiSign = true;
            return new LOTRGuiEditSign(fake);
        }
        if (ID == 48 && entityplayer.inventory.getCurrentItem() != null && entityplayer.inventory.getCurrentItem().getItem() instanceof LOTRItemDaleCracker) {
            return new LOTRGuiDaleCracker(entityplayer);
        }
        if (ID == 49) {
            return new LOTRGuiCraftingTable.Rhun(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 50 && (beacon = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityBeacon) {
            return new LOTRGuiBeacon((LOTRTileEntityBeacon)beacon);
        }
        if (ID == 51) {
            return new LOTRGuiCraftingTable.Rivendell(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 52 && (millstone = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityMillstone) {
            return new LOTRGuiMillstone(entityplayer.inventory, (LOTRTileEntityMillstone)millstone);
        }
        if (ID == 53) {
            return new LOTRGuiAnvil(entityplayer, i, j, k);
        }
        if (ID == 54 && (entity = world.getEntityByID(i)) instanceof LOTREntityNPC) {
            return new LOTRGuiAnvil(entityplayer, (LOTREntityNPC)entity);
        }
        if (ID == 55) {
            TileEntity bookshelf;
            if (world.getBlock(i, j, k) == Blocks.bookshelf) {
                world.setBlock(i, j, k, LOTRMod.bookshelfStorage, 0, 3);
            }
            if ((bookshelf = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityBookshelf) {
                return new LOTRGuiBookshelf((IInventory)entityplayer.inventory, (LOTRTileEntityBookshelf)bookshelf);
            }
        }
        if (ID == 56) {
            return new LOTRGuiCraftingTable.Umbar(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 57) {
            return new LOTRGuiCraftingTable.Gulf(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 58 && (entity = world.getEntityByID(i)) instanceof LOTRMercenary) {
            return new LOTRGuiMercenaryInteract((LOTREntityNPC)entity);
        }
        if (ID == 59 && (entity = world.getEntityByID(i)) instanceof LOTRMercenary) {
            return new LOTRGuiMercenaryHire(entityplayer, (LOTRMercenary)entity, world);
        }
        if (ID == 60) {
            return new LOTRGuiMap().setConquestGrid();
        }
        if (ID == 61) {
            return new LOTRGuiBrandingIron();
        }
        if (ID == 62) {
            return new LOTRGuiCraftingTable.Bree(entityplayer.inventory, world, i, j, k);
        }
        if (LOTRCommonProxy.testForSlotPackedGuiID(ID, 63)) {
            int slot = LOTRCommonProxy.unpackSlot(ID);
            IInventory chest2 = LOTRItemPouch.getChestInvAt(entityplayer, world, i, j, k);
            if (chest2 != null) {
                return new LOTRGuiChestWithPouch(entityplayer, slot, chest2);
            }
        }
        if (LOTRCommonProxy.testForSlotPackedGuiID(ID, 64)) {
            int slot = LOTRCommonProxy.unpackSlot(ID);
            Entity minecart = world.getEntityByID(i);
            if (minecart instanceof EntityMinecartContainer) {
                return new LOTRGuiChestWithPouch(entityplayer, slot, (IInventory)((EntityMinecartContainer)minecart));
            }
        }
        if (ID == 65) {
            return new LOTRGuiCraftingTable.Red(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 66) {
            return new LOTRGuiCraftingTable.Angband(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 67) {
            return new LOTRGuiCraftingTable.Erebor(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 68) {
            return new LOTRGuiCraftingTable.Wind(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 69) {
            return new LOTRGuiCraftingTable.WickedDwarfs(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 70) {
            return new LOTRGuiCraftingTable.Avari(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 71) {
            return new LOTRGuiCraftingTable.DarkElf(entityplayer.inventory, world, i, j, k);
        }
        if (ID == 94) {
            return new LOTRGuiBank(entityplayer);
        }
        return null;
    }

    public boolean isClient() {
        return false;
    }

    public boolean isSingleplayer() {
        return false;
    }

    public World getClientWorld() {
        return null;
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public boolean isPaused() {
        return false;
    }

    public void setClientDifficulty(EnumDifficulty difficulty) {
    }

    public void setWaypointModes(boolean showWP, boolean showCWP, boolean showHiddenSWP) {
    }

    public void spawnAlignmentBonus(LOTRFaction faction, float prevMainAlignment, LOTRAlignmentBonusMap factionBonusMap, String name, boolean isKill, boolean isHiredKill, float conquestBonus, double posX, double posY, double posZ) {
    }

    public void displayAlignDrain(int numFactions) {
    }

    public void queueAchievement(LOTRAchievement achievement) {
    }

    public void queueFellowshipNotification(IChatComponent message) {
    }

    public void queueConquestNotification(LOTRFaction fac, float conq, boolean isCleansing) {
    }

    public void displayMessage(LOTRGuiMessageTypes message) {
    }

    public void openHiredNPCGui(LOTREntityNPC npc) {
    }

    public void setMapIsOp(boolean isOp) {
    }

    public void displayFTScreen(LOTRAbstractWaypoint waypoint, int startX, int startZ) {
    }

    public void showFrostOverlay() {
    }

    public void showBurnOverlay() {
    }

    public void clearMapPlayerLocations() {
    }

    public void addMapPlayerLocation(GameProfile player, double posX, double posZ) {
    }

    public void setMapCWPProtectionMessage(IChatComponent message) {
    }

    public void displayBannerGui(LOTREntityBanner banner) {
    }

    public void validateBannerUsername(LOTREntityBanner banner, int slot, String prevText, boolean valid) {
    }

    public void clientReceiveSpeech(LOTREntityNPC npc, String speech) {
    }

    public void displayNewDate() {
    }

    public void displayMiniquestOffer(LOTRMiniQuest quest, LOTREntityNPC npc) {
    }

    public void setTrackedQuest(LOTRMiniQuest quest) {
    }

    public void displayAlignmentSee(String username, Map<LOTRFaction, Float> alignments) {
    }

    public void displayAlignmentChoice() {
    }

    public void cancelItemHighlight() {
    }

    public void displayAlignmentSee(String username, int currentHiredNPCs, int maxHiredNPCs, int getCustomMaxHiredNPCs) {
        String message = StatCollector.translateToLocalFormatted((String)"message.unitSee", (Object[])new Object[]{username, currentHiredNPCs, maxHiredNPCs, getCustomMaxHiredNPCs});
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentText(message));
    }

    public void receiveConquestGrid(LOTRFaction conqFac, List<LOTRConquestZone> allZones) {
    }

    public void handleInvasionWatch(int invasionEntityID, boolean overrideAlreadyWatched) {
    }

    public void setInPortal(EntityPlayer entityplayer) {
        if (!LOTRTickHandlerServer.playersInPortals.containsKey((Object)entityplayer)) {
            LOTRTickHandlerServer.playersInPortals.put(entityplayer, 0);
        }
    }

    public void setInElvenPortal(EntityPlayer entityplayer) {
        if (!LOTRTickHandlerServer.playersInElvenPortals.containsKey((Object)entityplayer)) {
            LOTRTickHandlerServer.playersInElvenPortals.put(entityplayer, 0);
        }
    }

    public void setInMorgulPortal(EntityPlayer entityplayer) {
        if (!LOTRTickHandlerServer.playersInMorgulPortals.containsKey((Object)entityplayer)) {
            LOTRTickHandlerServer.playersInMorgulPortals.put(entityplayer, 0);
        }
    }

    public void setInUtumnoReturnPortal(EntityPlayer entityplayer) {
    }

    public void setUtumnoReturnPortalCoords(EntityPlayer entityplayer, int x, int z) {
    }

    public void spawnParticle(String type, double d, double d1, double d2, double d3, double d4, double d5) {
    }

    public void placeFlowerInPot(World world, int i, int j, int k, int side, ItemStack itemstack) {
        world.setBlock(i, j, k, LOTRMod.flowerPot, 0, 3);
        LOTRBlockFlowerPot.setPlant(world, i, j, k, itemstack);
    }

    public void fillMugFromCauldron(World world, int i, int j, int k, int side, ItemStack itemstack) {
        world.setBlockMetadataWithNotify(i, j, k, world.getBlockMetadata(i, j, k) - 1, 3);
    }

    public void usePouchOnChest(EntityPlayer entityplayer, World world, int i, int j, int k, int side, ItemStack itemstack, int pouchSlot) {
        entityplayer.openGui((Object)LOTRMod.instance, LOTRCommonProxy.packGuiIDWithSlot(63, pouchSlot), world, i, j, k);
    }

    public void renderCustomPotionEffect(int x, int y, PotionEffect effect, Minecraft mc) {
    }

    public int getBeaconRenderID() {
        return 0;
    }

    public int getBarrelRenderID() {
        return 0;
    }

    public int getOrcBombRenderID() {
        return 0;
    }

    public int getDoubleTorchRenderID() {
        return 0;
    }

    public int getMobSpawnerRenderID() {
        return 0;
    }

    public int getPlateRenderID() {
        return 0;
    }

    public int getStalactiteRenderID() {
        return 0;
    }

    public int getFlowerPotRenderID() {
        return 0;
    }

    public int getCloverRenderID() {
        return 0;
    }

    public int getEntJarRenderID() {
        return 0;
    }

    public int getTrollTotemRenderID() {
        return 0;
    }

    public int getFenceRenderID() {
        return 0;
    }

    public int getGrassRenderID() {
        return 0;
    }

    public int getFallenLeavesRenderID() {
        return 0;
    }

    public int getCommandTableRenderID() {
        return 0;
    }

    public int getButterflyJarRenderID() {
        return 0;
    }

    public int getUnsmelteryRenderID() {
        return 0;
    }

    public int getChestRenderID() {
        return 0;
    }

    public int getReedsRenderID() {
        return 0;
    }

    public int getWasteRenderID() {
        return 0;
    }

    public int getBeamRenderID() {
        return 0;
    }

    public int getVCauldronRenderID() {
        return 0;
    }

    public int getGrapevineRenderID() {
        return 0;
    }

    public int getThatchFloorRenderID() {
        return 0;
    }

    public int getTreasureRenderID() {
        return 0;
    }

    public int getFlowerRenderID() {
        return 0;
    }

    public int getDoublePlantRenderID() {
        return 0;
    }

    public int getBirdCageRenderID() {
        return 0;
    }

    public int getRhunFireJarRenderID() {
        return 0;
    }

    public int getCoralRenderID() {
        return 0;
    }

    public int getDoorRenderID() {
        return 0;
    }

    public int getRopeRenderID() {
        return 0;
    }

    public int getOrcChainRenderID() {
        return 0;
    }

    public int getGuldurilRenderID() {
        return 0;
    }

    public int getOrcPlatingRenderID() {
        return 0;
    }

    public int getTrapdoorRenderID() {
        return 0;
    }

    public int getCampfireRenderID() {
        return 0;
    }
}

