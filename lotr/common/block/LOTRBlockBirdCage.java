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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockAnimalJar;
import lotr.common.entity.animal.LOTREntityBird;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockBirdCage
extends LOTRBlockAnimalJar {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] sideIcons;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] topIcons;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] baseIcons;
    private String[] cageTypes;

    public LOTRBlockBirdCage() {
        super(Material.glass);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.setHardness(0.5f);
        this.setStepSound(Block.soundTypeMetal);
        this.setCageTypes("bronze", "iron", "silver", "gold");
    }

    protected void setCageTypes(String ... s) {
        this.cageTypes = s;
    }

    @Override
    public boolean canCapture(Entity entity) {
        return entity instanceof LOTREntityBird;
    }

    @Override
    public float getJarEntityHeight() {
        return 0.5f;
    }

    @Override
    public boolean canBlockStay(World world, int i, int j, int k) {
        return true;
    }

    public static boolean isSameBirdCage(IBlockAccess world, int i, int j, int k, int i1, int j1, int k1) {
        Block block = world.getBlock(i, j, k);
        int meta = world.getBlockMetadata(i, j, k);
        Block block1 = world.getBlock(i1, j1, k1);
        int meta1 = world.getBlockMetadata(i1, j1, k1);
        return block instanceof LOTRBlockBirdCage && block == block1 && meta == meta1;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j >= this.cageTypes.length) {
            j = 0;
        }
        if (i == 0 || i == 1) {
            return this.topIcons[j];
        }
        if (i == -1) {
            return this.baseIcons[j];
        }
        return this.sideIcons[j];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.sideIcons = new IIcon[this.cageTypes.length];
        this.topIcons = new IIcon[this.cageTypes.length];
        this.baseIcons = new IIcon[this.cageTypes.length];
        for (int i = 0; i < this.cageTypes.length; ++i) {
            this.sideIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.cageTypes[i] + "_side");
            this.topIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.cageTypes[i] + "_top");
            this.baseIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.cageTypes[i] + "_base");
        }
    }

    public int getRenderType() {
        return LOTRMod.proxy.getBirdCageRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.cageTypes.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

