/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.block;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.animal.LOTREntityFrog;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTRBlockQuagmire
extends Block {
    public LOTRBlockQuagmire() {
        super(Material.ground);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        LOTRFaction pledgeFaction;
        LOTRPlayerData playerData;
        EntityPlayer player;
        if (entity instanceof EntityPlayer && (playerData = LOTRLevelData.getData(player = (EntityPlayer)entity)) != null && (pledgeFaction = playerData.getPledgeFaction()) != null) {
            Set<LOTRFaction.FactionType> factionTypesSet = pledgeFaction.getFactionTypes();
            EnumSet<LOTRFaction.FactionType> factionTypes = EnumSet.copyOf(factionTypesSet);
            LOTRFaction.FactionType factionType = this.getPriorityFactionType(factionTypes);
            float reputation = playerData.getAlignment(pledgeFaction);
            if (reputation >= 5000.0f && factionType == LOTRFaction.FactionType.TYPE_ELF) {
                return;
            }
        }
        entity.setInWeb();
    }

    private LOTRFaction.FactionType getPriorityFactionType(EnumSet<LOTRFaction.FactionType> factionTypes) {
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_ELF)) {
            return LOTRFaction.FactionType.TYPE_ELF;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_DWARF)) {
            return LOTRFaction.FactionType.TYPE_DWARF;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_MAN)) {
            return LOTRFaction.FactionType.TYPE_MAN;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_ORC)) {
            return LOTRFaction.FactionType.TYPE_ORC;
        }
        return LOTRFaction.FactionType.TYPE_FREE;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack currentItem = player.getCurrentEquippedItem();
        if (currentItem != null && currentItem.getItem() == Items.bucket && world.getBlock(x, y, z) == this) {
            if (!world.isRemote) {
                world.setBlockToAir(x, y, z);
                if (!player.capabilities.isCreativeMode) {
                    --currentItem.stackSize;
                    if (currentItem.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(LOTRMod.bucketQuagmire));
                    } else {
                        player.inventory.addItemStackToInventory(new ItemStack(LOTRMod.bucketQuagmire));
                    }
                    ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
                }
            }
            return true;
        }
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z, (double)((double)x + 1.0), (double)((double)y + 1.0), (double)((double)z + 1.0));
        double expand = 1.0;
        AxisAlignedBB expandedBox = boundingBox.expand(expand, expand, expand);
        for (Object obj : world.getEntitiesWithinAABB(Entity.class, expandedBox)) {
            Entity entity;
            if (!(obj instanceof Entity) || !((entity = (Entity)obj) instanceof EntityPlayer) && !(entity instanceof LOTREntityFrog)) continue;
            if (entity instanceof EntityPlayer) {
                LOTRFaction pledgeFaction;
                EntityPlayer player = (EntityPlayer)entity;
                LOTRPlayerData playerData = LOTRLevelData.getData(player);
                if (playerData == null || (pledgeFaction = playerData.getPledgeFaction()) == null) continue;
                float reputation = playerData.getAlignment(pledgeFaction);
                Set<LOTRFaction.FactionType> factionTypesSet = pledgeFaction.getFactionTypes();
                EnumSet<LOTRFaction.FactionType> factionTypes = EnumSet.copyOf(factionTypesSet);
                LOTRFaction.FactionType factionType = this.getPriorityFactionType(factionTypes);
                if (!(reputation >= 5000.0f) || factionType != LOTRFaction.FactionType.TYPE_ELF) continue;
                return super.getCollisionBoundingBoxFromPool(world, x, y, z);
            }
            if (!(entity instanceof LOTREntityFrog)) continue;
            return super.getCollisionBoundingBoxFromPool(world, x, y, z);
        }
        return null;
    }
}

