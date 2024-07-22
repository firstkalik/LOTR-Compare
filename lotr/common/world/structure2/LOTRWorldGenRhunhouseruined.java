/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenMordorStructure;
import net.minecraft.world.World;

public class LOTRWorldGenRhunhouseruined
extends LOTRWorldGenMordorStructure {
    protected LOTRChestContents chestContents;

    public LOTRWorldGenRhunhouseruined(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        this.setOriginAndRotation(world, i, j, k, rotation, 8);
        this.setupRandomBlocks(random);
        this.loadStrScan("rhunhouseruined");
        this.generateStrScan(world, random, 0, 1, 0);
        return true;
    }
}

