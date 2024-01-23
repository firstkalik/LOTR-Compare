/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.block.LOTRBlockTorch;

public class LOTRBlockMorgulTorch
extends LOTRBlockTorch {
    @Override
    public LOTRBlockTorch.TorchParticle createTorchParticle(Random random) {
        double d3 = -0.05 + (double)random.nextFloat() * 0.1;
        double d4 = 0.1 + (double)random.nextFloat() * 0.1;
        double d5 = -0.05 + (double)random.nextFloat() * 0.1;
        return new LOTRBlockTorch.TorchParticle(this, "morgulPortal", 0.0, 0.0, 0.0, d3, d4, d5);
    }
}

