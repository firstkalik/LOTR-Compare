/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import lotr.common.entity.LOTREntities;
import lotr.common.tileentity.LOTRTileEntityMobSpawner;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRItemMobSpawner
extends ItemBlock {
    public LOTRItemMobSpawner(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int i) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (LOTREntities.SpawnEggInfo info : LOTREntities.spawnEggs.values()) {
            list.add(new ItemStack(item, 1, info.spawnedID));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        String entityName = LOTREntities.getStringFromID(itemstack.getItemDamage());
        list.add(entityName);
    }

    public boolean placeBlockAt(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float f, float f1, float f2, int metadata) {
        if (super.placeBlockAt(itemstack, entityplayer, world, i, j, k, side, f, f1, f2, metadata)) {
            TileEntity tileentity = world.getTileEntity(i, j, k);
            if (tileentity != null && tileentity instanceof LOTRTileEntityMobSpawner) {
                ((LOTRTileEntityMobSpawner)tileentity).setEntityClassID(itemstack.getItemDamage());
                ((LOTRTileEntityMobSpawner)tileentity).spawnsPersistentNPCs = true;
            }
            return true;
        }
        return false;
    }
}

