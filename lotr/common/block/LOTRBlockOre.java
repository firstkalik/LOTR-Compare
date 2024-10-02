/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRBlockOre
extends Block {
    public LOTRBlockOre() {
        super(Material.rock);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(3.0f);
        this.setResistance(5.0f);
        this.setStepSound(Block.soundTypeStone);
    }

    public Item getItemDropped(int i, Random random, int j) {
        if (this == LOTRMod.oreNaurite) {
            return LOTRMod.nauriteGem;
        }
        if (this == LOTRMod.oreLavaCoal) {
            return LOTRMod.lavaCoal;
        }
        if (this == LOTRMod.oreMordorLavaCoal) {
            return LOTRMod.lavaCoal;
        }
        if (this == LOTRMod.oreIron) {
            return LOTRMod.ironRaw;
        }
        if (this == LOTRMod.oreGold) {
            return LOTRMod.goldRaw;
        }
        if (this == LOTRMod.oreMithril2) {
            return LOTRMod.mithrilRaw;
        }
        if (this == LOTRMod.oreQuendite) {
            return LOTRMod.quenditeCrystal;
        }
        if (this == LOTRMod.oreGlowstone) {
            return Items.glowstone_dust;
        }
        if (this == LOTRMod.oreGulduril) {
            return LOTRMod.guldurilCrystal;
        }
        if (this == LOTRMod.oreSulfur) {
            return LOTRMod.sulfur;
        }
        if (this == LOTRMod.oreSaltpeter) {
            return LOTRMod.saltpeter;
        }
        return Item.getItemFromBlock((Block)this);
    }

    public int quantityDropped(Random random) {
        if (this == LOTRMod.oreNaurite) {
            return 1 + random.nextInt(2);
        }
        if (this == LOTRMod.oreLavaCoal) {
            return 1 + random.nextInt(1);
        }
        if (this == LOTRMod.oreIron) {
            return 1 + random.nextInt(1);
        }
        if (this == LOTRMod.oreMithril2) {
            return 1 + random.nextInt(1);
        }
        if (this == LOTRMod.oreGold) {
            return 1 + random.nextInt(1);
        }
        if (this == LOTRMod.oreGlowstone) {
            return 2 + random.nextInt(4);
        }
        if (this == LOTRMod.oreSulfur || this == LOTRMod.oreSaltpeter) {
            return 1 + random.nextInt(2);
        }
        return 1;
    }

    public int quantityDroppedWithBonus(int i, Random random) {
        if (i > 0 && Item.getItemFromBlock((Block)this) != this.getItemDropped(0, random, i)) {
            int factor = random.nextInt(i + 2) - 1;
            factor = Math.max(factor, 0);
            int drops = this.quantityDropped(random) * (factor + 1);
            if (this == LOTRMod.oreGlowstone) {
                drops = Math.min(drops, 8);
            }
            return drops;
        }
        return this.quantityDropped(random);
    }

    public void dropBlockAsItemWithChance(World world, int i, int j, int k, int meta, float f, int fortune) {
        super.dropBlockAsItemWithChance(world, i, j, k, meta, f, fortune);
        if (this.getItemDropped(meta, world.rand, fortune) != Item.getItemFromBlock((Block)this)) {
            int amountXp = 0;
            if (this == LOTRMod.oreNaurite) {
                amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            }
            if (this == LOTRMod.oreLavaCoal) {
                amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            }
            if (this == LOTRMod.oreIron) {
                amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            }
            if (this == LOTRMod.oreGold) {
                amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            }
            if (this == LOTRMod.oreQuendite) {
                amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            }
            if (this == LOTRMod.oreGlowstone) {
                amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            }
            if (this == LOTRMod.oreGulduril) {
                amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            }
            if (this == LOTRMod.oreSulfur || this == LOTRMod.oreSaltpeter) {
                amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
            }
            this.dropXpOnBlockBreak(world, i, j, k, amountXp);
        }
    }

    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.harvestBlock(world, entityplayer, i, j, k, l);
        if (!world.isRemote) {
            if (this == LOTRMod.oreMithril) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.mineMithril);
            }
            if (this == LOTRMod.oreMithril2) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.mineMithrilPlus);
            }
            if (this == LOTRMod.oreQuendite) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.mineQuendite);
            }
            if (this == LOTRMod.oreGlowstone) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.mineGlowstone);
            }
            if (this == LOTRMod.oreNaurite) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.mineNaurite);
            }
            if (this == LOTRMod.oreGulduril) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.mineGulduril);
            }
        }
    }
}

