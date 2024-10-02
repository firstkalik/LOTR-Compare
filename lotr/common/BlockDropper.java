/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.BlockChance;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockDropper {
    private final List<BlockChance> blockChances = new ArrayList<BlockChance>();
    private final Random random = new Random();

    public BlockDropper() {
        this.blockChances.add(new BlockChance(Blocks.coal_ore, 0, 45.0f));
        this.blockChances.add(new BlockChance(Blocks.iron_ore, 0, 38.0f));
        this.blockChances.add(new BlockChance(Blocks.gold_ore, 0, 10.0f));
        this.blockChances.add(new BlockChance(LOTRMod.oreGold, 0, 5.0f));
        this.blockChances.add(new BlockChance(LOTRMod.oreIron, 0, 20.0f));
    }

    public void applyDrop(World world, int x, int y, int z) {
        float chance = this.random.nextFloat() * 100.0f;
        for (BlockChance blockChance : this.blockChances) {
            if (!(chance <= blockChance.getChance())) continue;
            world.setBlock(x, y, z, blockChance.getBlock(), blockChance.getMeta(), 2);
            return;
        }
    }
}

