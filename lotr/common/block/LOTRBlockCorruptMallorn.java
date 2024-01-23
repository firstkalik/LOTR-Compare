/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockFlower;
import lotr.common.entity.npc.LOTREntityMallornEnt;
import lotr.common.tileentity.LOTRTileEntityCorruptMallorn;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRBlockCorruptMallorn
extends LOTRBlockFlower {
    public static int ENT_KILLS = 3;

    public LOTRBlockCorruptMallorn() {
        float f = 0.4f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setLightLevel(0.625f);
    }

    public boolean hasTileEntity(int meta) {
        return true;
    }

    public TileEntity createTileEntity(World world, int meta) {
        return new LOTRTileEntityCorruptMallorn();
    }

    public static void summonEntBoss(World world, int i, int j, int k) {
        world.setBlockToAir(i, j, k);
        LOTREntityMallornEnt ent = new LOTREntityMallornEnt(world);
        ent.setLocationAndAngles((double)i + 0.5, (double)j, (double)k + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
        ent.onSpawnWithEgg(null);
        world.spawnEntityInWorld((Entity)ent);
        ent.sendEntBossSpeech("summon");
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (!world.isRemote) {
            super.updateTick(world, i, j, k, random);
        }
    }

    public ArrayList getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(LOTRMod.sapling, 1, 1));
        return drops;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        for (int l = 0; l < 2; ++l) {
            double d = (double)i + 0.25 + (double)(random.nextFloat() * 0.5f);
            double d1 = (double)j + 0.5;
            double d2 = (double)k + 0.25 + (double)(random.nextFloat() * 0.5f);
            double d3 = -0.05 + (double)random.nextFloat() * 0.1;
            double d4 = 0.1 + (double)random.nextFloat() * 0.1;
            double d5 = -0.05 + (double)random.nextFloat() * 0.1;
            LOTRMod.proxy.spawnParticle("morgulPortal", d, d1, d2, d3, d4, d5);
        }
    }

    @Override
    public int getRenderType() {
        return 1;
    }
}

