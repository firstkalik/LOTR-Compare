/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package lotr.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LOTRBlockCraftingTable
extends Block {
    public static List<LOTRBlockCraftingTable> allCraftingTables = new ArrayList<LOTRBlockCraftingTable>();
    public final LOTRFaction tableFaction;
    public final int tableGUIID;

    public LOTRBlockCraftingTable(Material material, LOTRFaction faction, int guiID) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
        this.setHardness(2.5f);
        this.tableFaction = faction;
        this.tableGUIID = guiID;
        allCraftingTables.add(this);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        boolean hasRequiredAlignment;
        boolean bl = hasRequiredAlignment = LOTRLevelData.getData(entityplayer).getAlignment(this.tableFaction) >= 1.0f;
        if (hasRequiredAlignment) {
            if (!world.isRemote) {
                entityplayer.openGui((Object)LOTRMod.instance, this.tableGUIID, world, i, j, k);
            }
        } else {
            for (int l = 0; l < 8; ++l) {
                double d = (float)i + world.rand.nextFloat();
                double d1 = (double)j + 1.0;
                double d2 = (float)k + world.rand.nextFloat();
                world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
            }
            if (!world.isRemote) {
                LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, 1.0f, this.tableFaction);
            }
        }
        return true;
    }
}

