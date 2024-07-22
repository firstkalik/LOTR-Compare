/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockDirectional
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockBed
extends BlockBed {
    public Item bedItem;
    private Block bedBottomBlock;
    private int bedBottomMetadata;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] bedIconsEnd;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] bedIconsSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] bedIconsTop;

    public LOTRBlockBed(Block block, int k) {
        this.bedBottomBlock = block;
        this.bedBottomMetadata = k;
        this.setHardness(0.2f);
        this.setStepSound(Block.soundTypeWood);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        int i1;
        if (i == 0) {
            return this.bedBottomBlock.getIcon(0, this.bedBottomMetadata);
        }
        int k = BlockDirectional.getDirection((int)j);
        int l = Direction.bedDirection[k][i];
        int n = i1 = BlockBed.isBlockHeadOfBed((int)j) ? 1 : 0;
        return !(i1 == 1 && l == 2 || i1 == 0 && l == 3) ? (l != 5 && l != 4 ? this.bedIconsTop[i1] : this.bedIconsSide[i1]) : this.bedIconsEnd[i1];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.bedIconsTop = new IIcon[]{iconregister.registerIcon(this.getTextureName() + "_feet_top"), iconregister.registerIcon(this.getTextureName() + "_head_top")};
        this.bedIconsEnd = new IIcon[]{iconregister.registerIcon(this.getTextureName() + "_feet_end"), iconregister.registerIcon(this.getTextureName() + "_head_end")};
        this.bedIconsSide = new IIcon[]{iconregister.registerIcon(this.getTextureName() + "_feet_side"), iconregister.registerIcon(this.getTextureName() + "_head_side")};
    }

    public Item getItemDropped(int i, Random random, int j) {
        return BlockBed.isBlockHeadOfBed((int)i) ? null : this.bedItem;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int i, int j, int k) {
        return this.bedItem;
    }

    public boolean isBed(IBlockAccess world, int i, int j, int k, EntityLivingBase entity) {
        return true;
    }
}

