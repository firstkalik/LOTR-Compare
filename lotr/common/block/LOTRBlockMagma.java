/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTRBlockMagma
extends LOTRModBlock {
    private static Random rand = new Random();

    public LOTRBlockMagma(int lightLevel) {
        super(LOTRModBlock.materialMagma);
        this.setLightLevel(0.5f);
        this.setHardness(3.0f);
        this.setResistance(5.0f);
    }

    public LOTRBlockMagma() {
        this(3);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.125f;
        return AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z, (double)(x + 1), (double)((float)(y + 1) - f), (double)(z + 1));
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        if (!(entity.isImmuneToFire() || !(entity instanceof EntityLivingBase) || entity.isSneaking() || entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode || !entity.attackEntityFrom(DamageSource.onFire, 1.0f))) {
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "random.fizz", 0.5f, 2.6f + (rand.nextFloat() - rand.nextFloat()) * 0.8f);
        }
        super.onEntityCollidedWithBlock(world, i, j, k, entity);
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        int meta = world.getBlockMetadata(i, j, k);
        int l = 0;
        while ((double)l < 0.5 * (double)meta * (double)meta + 0.5) {
            double d = (double)i + 0.25 + (double)(random.nextFloat() * 0.5f);
            double d1 = (double)j + 0.5;
            double d2 = (double)k + 0.25 + (double)(random.nextFloat() * 0.5f);
            double d3 = -0.05 + (double)random.nextFloat() * 0.1;
            double d4 = 0.1 + (double)random.nextFloat() * 0.1;
            double d5 = -0.05 + (double)random.nextFloat() * 0.1;
            world.spawnParticle("smoke", d, d1, d2, d3, d4, d5);
            ++l;
        }
    }

    public int getRenderType() {
        return 0;
    }
}

