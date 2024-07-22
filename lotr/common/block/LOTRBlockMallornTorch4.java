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

public class LOTRBlockMallornTorch4
extends LOTRBlockTorch {
    private int torchColor;

    public LOTRBlockMallornTorch4(int color) {
        this.setHardness(0.0f);
        this.setStepSound(Block.soundTypeWood);
        this.setLightLevel(0.875f);
        this.torchColor = color;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
        double d0 = (float)p_149734_2_ + 0.5f;
        double d1 = (float)p_149734_3_ + 0.7f;
        double d2 = (float)p_149734_4_ + 0.5f;
        double d3 = 0.2199999988079071;
        double d4 = 0.27000001072883606;
        if (l == 1) {
            p_149734_1_.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0, 0.0, 0.0);
            p_149734_1_.spawnParticle("flame", d0 - d4, d1 + d3, d2, 0.0, 0.0, 0.0);
        } else if (l == 2) {
            p_149734_1_.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0, 0.0, 0.0);
            p_149734_1_.spawnParticle("flame", d0 + d4, d1 + d3, d2, 0.0, 0.0, 0.0);
        } else if (l == 3) {
            p_149734_1_.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0, 0.0, 0.0);
            p_149734_1_.spawnParticle("flame", d0, d1 + d3, d2 - d4, 0.0, 0.0, 0.0);
        } else if (l == 4) {
            p_149734_1_.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0, 0.0, 0.0);
            p_149734_1_.spawnParticle("flame", d0, d1 + d3, d2 + d4, 0.0, 0.0, 0.0);
        } else {
            p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0, 0.0, 0.0);
            p_149734_1_.spawnParticle("flame", d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public LOTRBlockTorch.TorchParticle createTorchParticle(Random random) {
        return null;
    }
}

