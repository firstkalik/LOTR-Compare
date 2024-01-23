/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRBlockOreGem
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] oreIcons;
    private String[] oreNames = new String[]{"topaz", "amethyst", "sapphire", "ruby", "amber", "diamond", "opal", "emerald"};

    public LOTRBlockOreGem() {
        super(Material.rock);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(3.0f);
        this.setResistance(5.0f);
        this.setStepSound(Block.soundTypeStone);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.oreIcons = new IIcon[this.oreNames.length];
        for (int i = 0; i < this.oreNames.length; ++i) {
            this.oreIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.oreNames[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j >= this.oreNames.length) {
            j = 0;
        }
        return this.oreIcons[j];
    }

    public Item getItemDropped(int i, Random random, int j) {
        if (i == 0) {
            return LOTRMod.topaz;
        }
        if (i == 1) {
            return LOTRMod.amethyst;
        }
        if (i == 2) {
            return LOTRMod.sapphire;
        }
        if (i == 3) {
            return LOTRMod.ruby;
        }
        if (i == 4) {
            return LOTRMod.amber;
        }
        if (i == 5) {
            return LOTRMod.diamond;
        }
        if (i == 6) {
            return LOTRMod.opal;
        }
        if (i == 7) {
            return LOTRMod.emerald;
        }
        return Item.getItemFromBlock((Block)this);
    }

    public int quantityDropped(Random random) {
        return 1 + random.nextInt(2);
    }

    public int quantityDroppedWithBonus(int i, Random random) {
        if (i > 0 && Item.getItemFromBlock((Block)this) != this.getItemDropped(0, random, i)) {
            int drops = this.quantityDropped(random);
            return drops += random.nextInt(i + 1);
        }
        return this.quantityDropped(random);
    }

    public void dropBlockAsItemWithChance(World world, int i, int j, int k, int meta, float f, int fortune) {
        super.dropBlockAsItemWithChance(world, i, j, k, meta, f, fortune);
        if (this.getItemDropped(meta, world.rand, fortune) != Item.getItemFromBlock((Block)this)) {
            int amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            this.dropXpOnBlockBreak(world, i, j, k, amountXp);
        }
    }

    public int getDamageValue(World world, int i, int j, int k) {
        return world.getBlockMetadata(i, j, k);
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.oreNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

