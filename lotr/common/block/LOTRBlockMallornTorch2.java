/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.block.LOTRBlockTorch;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRBlockMallornTorch2
extends LOTRBlockTorch {
    private int torchColor;

    public LOTRBlockMallornTorch2(int color) {
        this.setHardness(0.0f);
        this.setStepSound(Block.soundTypeWood);
        this.setLightLevel(0.875f);
        this.torchColor = color;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (world.getBlockMetadata(i, j, k) == 1) {
            double d = (double)i + 0.0;
            double d1 = (double)j + 0.0;
            double d2 = (double)k + 0.0;
            world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
            world.spawnParticle("flame", d, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public LOTRBlockTorch.TorchParticle createTorchParticle(Random var1) {
        return null;
    }
}

