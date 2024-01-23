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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.item.Item
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
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
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRBlockCoralReef
extends Block {
    private IIcon[] plantIcons;
    private static final String[] plantNames = new String[]{"purple", "yellow", "blue", "red", "green"};
    private static final Random iconRand = new Random();

    public LOTRBlockCoralReef() {
        super(Material.rock);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(1.0f);
        this.setResistance(5.0f);
        this.setStepSound(Block.soundTypeStone);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        super.registerBlockIcons(iconregister);
        this.plantIcons = new IIcon[plantNames.length];
        for (int i = 0; i < plantNames.length; ++i) {
            this.plantIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + plantNames[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return super.getIcon(i, j);
    }

    public IIcon getRandomPlantIcon(int i, int j, int k) {
        int hash = i * 25799626 ^ k * 6879038 ^ j;
        iconRand.setSeed(hash);
        iconRand.setSeed(iconRand.nextLong());
        return this.plantIcons[iconRand.nextInt(this.plantIcons.length)];
    }

    public int getRenderType() {
        return LOTRMod.proxy.getCoralRenderID();
    }

    public Item getItemDropped(int i, Random random, int j) {
        return LOTRMod.coral;
    }

    public int quantityDropped(Random random) {
        return 1 + random.nextInt(2);
    }

    public int quantityDroppedWithBonus(int i, Random random) {
        int drops = this.quantityDropped(random);
        if (i > 0) {
            int factor = random.nextInt(i + 2) - 1;
            factor = Math.max(factor, 0);
            drops *= factor + 1;
        }
        return drops;
    }

    public void dropBlockAsItemWithChance(World world, int i, int j, int k, int meta, float f, int fortune) {
        super.dropBlockAsItemWithChance(world, i, j, k, meta, f, fortune);
        int amountXp = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)0, (int)2);
        this.dropXpOnBlockBreak(world, i, j, k, amountXp);
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity) {
        if (entity instanceof EntityLivingBase && !(entity instanceof EntityWaterMob)) {
            entity.attackEntityFrom(DamageSource.cactus, 0.5f);
        }
    }
}

