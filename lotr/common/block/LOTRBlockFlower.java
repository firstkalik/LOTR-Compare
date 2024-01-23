/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 */
package lotr.common.block;

import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class LOTRBlockFlower
extends BlockBush {
    public LOTRBlockFlower() {
        this(Material.plants);
    }

    public LOTRBlockFlower(Material material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setHardness(0.0f);
        this.setStepSound(Block.soundTypeGrass);
    }

    public Block setFlowerBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        return this;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getFlowerRenderID();
    }
}

