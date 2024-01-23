/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.block.LOTRBlockTorch;

public class LOTRBlockElvenTorch
extends LOTRBlockTorch {
    @Override
    public LOTRBlockTorch.TorchParticle createTorchParticle(Random random) {
        if (random.nextInt(3) == 0) {
            return new LOTRBlockTorch.TorchParticle(this, "elvenGlow", 0.0, -0.1, 0.0, 0.0, 0.0, 0.0);
        }
        return null;
    }
}

