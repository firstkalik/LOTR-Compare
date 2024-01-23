/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
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
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LOTRBlockDoor
extends BlockDoor {
    public LOTRBlockDoor() {
        this(Material.wood);
        this.setStepSound(Block.soundTypeWood);
        this.setHardness(3.0f);
    }

    public LOTRBlockDoor(Material material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return (i & 8) != 0 ? null : Item.getItemFromBlock((Block)this);
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int i, int j, int k) {
        return Item.getItemFromBlock((Block)this);
    }

    @SideOnly(value=Side.CLIENT)
    public String getItemIconName() {
        return this.getTextureName();
    }

    public int getRenderType() {
        return LOTRMod.proxy.getDoorRenderID();
    }
}

