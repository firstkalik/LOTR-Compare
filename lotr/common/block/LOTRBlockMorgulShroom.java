/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.block.LOTRBlockMordorPlant;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class LOTRBlockMorgulShroom
extends LOTRBlockMordorPlant {
    public LOTRBlockMorgulShroom() {
        float f = 0.2f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
        this.setTickRandomly(true);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (random.nextInt(10) == 0) {
            int j1;
            int i1;
            int k1;
            boolean nearbyWater = false;
            for (i1 = i - 2; i1 <= i + 2 && !nearbyWater; ++i1) {
                for (j1 = j - 2; j1 <= j + 2 && !nearbyWater; ++j1) {
                    for (k1 = k - 2; k1 <= k + 2 && !nearbyWater; ++k1) {
                        if (world.getBlock(i1, j - 1, k1).getMaterial() != Material.water) continue;
                        nearbyWater = true;
                    }
                }
            }
            if (nearbyWater && world.isAirBlock(i1 = i - 1 + random.nextInt(3), j1 = j - 1 + random.nextInt(3), k1 = k - 1 + random.nextInt(3)) && this.canBlockStay(world, i1, j1, k1)) {
                world.setBlock(i1, j1, k1, (Block)this);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public String getItemIconName() {
        return this.getTextureName();
    }
}

