/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.BlockPortal
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRDimension;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityUtumnoReturnPortal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class LOTRBlockUtumnoReturnPortal
extends BlockContainer {
    public LOTRBlockUtumnoReturnPortal() {
        super(Material.portal);
        this.setHardness(-1.0f);
        this.setResistance(Float.MAX_VALUE);
        this.setStepSound(Block.soundTypeStone);
        this.setLightLevel(1.0f);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityUtumnoReturnPortal();
    }

    public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB aabb, List list, Entity entity) {
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int quantityDropped(Random par1Random) {
        return 0;
    }

    public int getRenderType() {
        return -1;
    }

    public void onBlockAdded(World world, int i, int j, int k) {
        if (world.provider.dimensionId != LOTRDimension.UTUMNO.dimensionID) {
            world.setBlockToAir(i, j, k);
        }
    }

    public void breakBlock(World world, int i, int j, int k, Block block, int meta) {
        super.breakBlock(world, i, j, k, block, meta);
        if (!world.isRemote) {
            for (int j1 = j; j1 <= world.getHeight(); ++j1) {
                if (world.getBlock(i, j1, k) != LOTRMod.utumnoReturnLight) continue;
                world.setBlockToAir(i, j1, k);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int i, int j, int k) {
        return Item.getItemById((int)0);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return Blocks.portal.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }
}

