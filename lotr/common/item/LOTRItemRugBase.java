/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.entity.item.LOTREntityRugBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class LOTRItemRugBase
extends Item {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] rugIcons;
    private String[] rugNames;

    public LOTRItemRugBase(String ... names) {
        this.rugNames = names;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int i) {
        if (i >= this.rugIcons.length) {
            i = 0;
        }
        return this.rugIcons[i];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        this.rugIcons = new IIcon[this.rugNames.length];
        for (int i = 0; i < this.rugIcons.length; ++i) {
            this.rugIcons[i] = iconregister.registerIcon(this.getIconString() + "_" + this.rugNames[i]);
        }
    }

    protected abstract LOTREntityRugBase createRug(World var1, ItemStack var2);

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        Block block = world.getBlock(i, j, k);
        if (block == Blocks.snow_layer) {
            l = 1;
        } else if (!block.isReplaceable((IBlockAccess)world, i, j, k)) {
            if (l == 0) {
                --j;
            }
            if (l == 1) {
                ++j;
            }
            if (l == 2) {
                --k;
            }
            if (l == 3) {
                ++k;
            }
            if (l == 4) {
                --i;
            }
            if (l == 5) {
                ++i;
            }
        }
        if (!entityplayer.canPlayerEdit(i, j, k, l, itemstack)) {
            return false;
        }
        if (world.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP) && !world.isRemote) {
            LOTREntityRugBase rug = this.createRug(world, itemstack);
            rug.setLocationAndAngles((double)((float)i + f), (double)j, (double)((float)k + f2), 180.0f - entityplayer.rotationYaw % 360.0f, 0.0f);
            if (world.checkNoEntityCollision(rug.boundingBox) && world.getCollidingBoundingBoxes((Entity)rug, rug.boundingBox).size() == 0 && !world.isAnyLiquid(rug.boundingBox)) {
                world.spawnEntityInWorld((Entity)rug);
                world.playSoundAtEntity((Entity)rug, Blocks.wool.stepSound.func_150496_b(), (Blocks.wool.stepSound.getVolume() + 1.0f) / 2.0f, Blocks.wool.stepSound.getPitch() * 0.8f);
                --itemstack.stackSize;
                return true;
            }
            rug.setDead();
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.rugNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

