/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import lotr.common.LOTRDamage;
import lotr.common.block.LOTRBlockGrass;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBlockTallGrass
extends LOTRBlockGrass {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] grassIcons;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] overlayIcons;
    public static String[] grassNames = new String[]{"short", "flower", "wheat", "thistle", "nettle", "fernsprout"};
    public static boolean[] grassOverlay = new boolean[]{false, true, true, true, false, false};

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        int meta = world.getBlockMetadata(i, j, k);
        if (meta == 3 && entity.isSprinting() || meta == 4 && entity instanceof EntityPlayer) {
            EntityLivingBase living;
            boolean bootsLegs = false;
            if (entity instanceof EntityLivingBase && (living = (EntityLivingBase)entity).getEquipmentInSlot(1) != null && living.getEquipmentInSlot(2) != null) {
                bootsLegs = true;
            }
            if (!bootsLegs) {
                entity.attackEntityFrom(LOTRDamage.plantHurt, 0.25f);
            }
        }
    }

    @Override
    public ArrayList getDrops(World world, int i, int j, int k, int meta, int fortune) {
        if (meta == 3) {
            ArrayList<ItemStack> thistles = new ArrayList<ItemStack>();
            thistles.add(new ItemStack((Block)this, 1, 3));
            return thistles;
        }
        return super.getDrops(world, i, j, k, meta, fortune);
    }

    @SideOnly(value=Side.CLIENT)
    public int getBlockColor() {
        return Blocks.tallgrass.getBlockColor();
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderColor(int meta) {
        return this.getBlockColor();
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int i, int j, int k) {
        return world.getBiomeGenForCoords(i, k).getBiomeGrassColor(i, j, k);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j >= grassNames.length) {
            j = 0;
        }
        if (i == -1) {
            return this.overlayIcons[j];
        }
        return this.grassIcons[j];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.grassIcons = new IIcon[grassNames.length];
        this.overlayIcons = new IIcon[grassNames.length];
        for (int i = 0; i < grassNames.length; ++i) {
            this.grassIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + grassNames[i]);
            if (!grassOverlay[i]) continue;
            this.overlayIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + grassNames[i] + "_overlay");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j < grassNames.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
}

