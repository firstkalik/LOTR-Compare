/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.block.LOTRBlockFlower;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRBlockPipeweedPlant
extends LOTRBlockFlower {
    public LOTRBlockPipeweedPlant() {
        this.setFlowerBounds(0.1f, 0.0f, 0.1f, 0.9f, 0.8f, 0.9f);
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (random.nextInt(4) == 0) {
            double d = (float)i + MathHelper.randomFloatClamp((Random)random, (float)0.1f, (float)0.9f);
            double d1 = (float)j + MathHelper.randomFloatClamp((Random)random, (float)0.5f, (float)0.75f);
            double d2 = (float)k + MathHelper.randomFloatClamp((Random)random, (float)0.1f, (float)0.9f);
            world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
        }
    }
}

