/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 */
package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRDamage;
import lotr.common.block.LOTRBlockFlower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class LOTRBlockMiniCactus
extends LOTRBlockFlower {
    public LOTRBlockMiniCactus() {
        this(Material.plants);
    }

    public LOTRBlockMiniCactus(Material material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setHardness(0.0f);
        this.setStepSound(Block.soundTypeGrass);
        this.setFlowerBounds(0.3f, 0.0f, 0.3f, 0.7f, 0.4f, 0.7f);
    }

    @Override
    public Block setFlowerBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        long i1 = 113524218L;
        i1 = i1 * i1 * 42317861L + i1 * 11L;
        float d0 = (float)(((double)((float)(i1 >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5);
        float d2 = (float)(((double)((float)(i1 >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5);
        this.setBlockBounds(minX + d0, minY, minZ + d2, maxX + d0, maxY, maxZ + d2);
        return this;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Desert;
    }

    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        if (entity.isSprinting() || entity instanceof EntityPlayer) {
            EntityLivingBase living;
            boolean bootsLegs = false;
            if (entity instanceof EntityLivingBase && (living = (EntityLivingBase)entity).getEquipmentInSlot(1) != null && living.getEquipmentInSlot(2) != null) {
                bootsLegs = true;
            }
            if (!bootsLegs) {
                entity.attackEntityFrom(LOTRDamage.plantHurt, 0.5f);
            }
        }
    }
}

