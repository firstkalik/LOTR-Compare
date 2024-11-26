/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.ColorizerGrass
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.block.LOTRBlockFlower;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiomeSunLands;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBlockClover
extends LOTRBlockFlower {
    @SideOnly(value=Side.CLIENT)
    public static IIcon stemIcon;
    @SideOnly(value=Side.CLIENT)
    public static IIcon petalIcon;

    public LOTRBlockClover() {
        this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.4f, 0.8f);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if (metadata == 1 && world.getBiomeGenForCoords(x, z) instanceof LOTRBiomeSunLands) {
            LOTRPlayerData playerData;
            EntityPlayer player = world.getClosestPlayer((double)x, (double)y, (double)z, 16.0);
            if (player != null && (playerData = LOTRLevelData.getData(player)) != null) {
                LOTRFaction pledgeFaction = playerData.getPledgeFaction();
                if (pledgeFaction != null) {
                    float reputation = playerData.getAlignment(pledgeFaction);
                    Set<LOTRFaction.FactionType> factionTypesSet = pledgeFaction.getFactionTypes();
                    EnumSet<LOTRFaction.FactionType> factionTypes = EnumSet.copyOf(factionTypesSet);
                    LOTRFaction.FactionType factionType = this.getPriorityFactionType(factionTypes);
                    if (reputation >= 10000.0f && factionType == LOTRFaction.FactionType.TYPE_FREE) {
                        if (player.isSneaking()) {
                            drops.add(new ItemStack(LOTRMod.magicCloverPlus));
                        } else {
                            drops.add(new ItemStack((Block)this, 1, metadata));
                        }
                    } else {
                        drops.add(new ItemStack((Block)this, 1, metadata));
                    }
                } else {
                    drops.add(new ItemStack((Block)this, 1, metadata));
                }
            }
        } else {
            drops.add(new ItemStack((Block)this, 1, metadata));
        }
        return drops;
    }

    private LOTRFaction.FactionType getPriorityFactionType(EnumSet<LOTRFaction.FactionType> factionTypes) {
        return LOTRFaction.FactionType.TYPE_FREE;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
        double posX = i;
        double posY = j;
        double posZ = k;
        long seed = (long)(i * 3129871) ^ (long)k * 116129781L ^ (long)j;
        seed = seed * seed * 42317861L + seed * 11L;
        return AxisAlignedBB.getBoundingBox((double)((posX += ((double)((float)(seed >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5) + this.minX), (double)(posY + this.minY), (double)((posZ += ((double)((float)(seed >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5) + this.minZ), (double)(posX + this.maxX), (double)(posY + this.maxY), (double)(posZ + this.maxZ));
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return petalIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        stemIcon = iconregister.registerIcon(this.getTextureName() + "_stem");
        petalIcon = iconregister.registerIcon(this.getTextureName() + "_petal");
    }

    public boolean isReplaceable(IBlockAccess world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        return meta != 1;
    }

    public int damageDropped(int i) {
        return i;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j <= 1; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }

    @Override
    public int getRenderType() {
        return LOTRMod.proxy.getCloverRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public int getBlockColor() {
        return ColorizerGrass.getGrassColor((double)1.0, (double)1.0);
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderColor(int i) {
        return this.getBlockColor();
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int i, int j, int k) {
        return world.getBiomeGenForCoords(i, k).getBiomeGrassColor(i, j, k);
    }
}

