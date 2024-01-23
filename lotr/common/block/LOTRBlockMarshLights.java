/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTRBlockMarshLights
extends Block {
    public LOTRBlockMarshLights() {
        super(Material.circuits);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }

    public boolean isCollidable() {
        return false;
    }

    public int getRenderType() {
        return -1;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    public int quantityDropped(Random random) {
        return 0;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return this.canBlockStay(world, i, j, k);
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return world.getBlock(i, j - 1, k).getMaterial() == Material.water;
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            world.setBlock(i, j, k, Blocks.air, 0, 2);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (random.nextInt(3) > 0) {
            if (random.nextInt(3) == 0) {
                LOTRMod.proxy.spawnParticle("marshFlame", (float)i + random.nextFloat(), (double)j - 0.5, (float)k + random.nextFloat(), 0.0, 0.05f + random.nextFloat() * 0.1f, 0.0);
            } else {
                LOTRMod.proxy.spawnParticle("marshLight", (float)i + random.nextFloat(), (double)j - 0.5, (float)k + random.nextFloat(), 0.0, 0.05f + random.nextFloat() * 0.1f, 0.0);
            }
        }
    }
}

