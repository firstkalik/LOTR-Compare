/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockTorch
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public abstract class LOTRBlockTorch
extends BlockTorch {
    public LOTRBlockTorch() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        int meta = world.getBlockMetadata(i, j, k);
        double d = (double)i + 0.5;
        double d1 = (double)j + 0.7;
        double d2 = (double)k + 0.5;
        double particleY = 0.2;
        double particleX = 0.27;
        TorchParticle particle = this.createTorchParticle(random);
        if (particle != null) {
            if (meta == 1) {
                particle.spawn(d - particleX, d1 + particleY, d2);
            } else if (meta == 2) {
                particle.spawn(d + particleX, d1 + particleY, d2);
            } else if (meta == 3) {
                particle.spawn(d, d1 + particleY, d2 - particleX);
            } else if (meta == 4) {
                particle.spawn(d, d1 + particleY, d2 + particleX);
            } else {
                particle.spawn(d, d1, d2);
            }
        }
    }

    public abstract TorchParticle createTorchParticle(Random var1);

    public class TorchParticle {
        public String name;
        public double posX;
        public double posY;
        public double posZ;
        public double motionX;
        public double motionY;
        public double motionZ;

        public TorchParticle(String s, double x, double y, double z, double mx, double my, double mz) {
            this.name = s;
            this.posX = x;
            this.posY = y;
            this.posZ = z;
            this.motionX = mx;
            this.motionY = my;
            this.motionZ = mz;
        }

        public void spawn(double x, double y, double z) {
            LOTRMod.proxy.spawnParticle(this.name, x + this.posX, y + this.posY, z + this.posZ, this.motionX, this.motionY, this.motionZ);
        }
    }

}

