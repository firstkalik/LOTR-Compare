/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemSlab
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Facing
 *  net.minecraft.world.IBlockAccess
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
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class LOTRBlockSlabBase
extends BlockSlab {
    public Block singleSlab;
    public Block doubleSlab;
    private int subtypes;

    public LOTRBlockSlabBase(boolean flag, Material material, int n) {
        super(flag, material);
        this.subtypes = n;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.useNeighborBrightness = true;
        if (material == Material.wood) {
            this.setHardness(2.0f);
            this.setResistance(5.0f);
            this.setStepSound(Block.soundTypeWood);
        }
    }

    public static void registerSlabs(Block block, Block block1) {
        ((LOTRBlockSlabBase)block).singleSlab = block;
        ((LOTRBlockSlabBase)block).doubleSlab = block1;
        ((LOTRBlockSlabBase)block1).singleSlab = block;
        ((LOTRBlockSlabBase)block1).doubleSlab = block1;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)this.singleSlab);
    }

    protected ItemStack createStackedBlock(int i) {
        return new ItemStack(this.singleSlab, 2, i & 7);
    }

    public String func_150002_b(int i) {
        return super.getUnlocalizedName() + "." + i;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int i, int j, int k, int l) {
        boolean flag;
        if (this == this.doubleSlab) {
            return super.shouldSideBeRendered(world, i, j, k, l);
        }
        if (l != 1 && l != 0 && !super.shouldSideBeRendered(world, i, j, k, l)) {
            return false;
        }
        int i1 = i + Facing.offsetsXForSide[Facing.oppositeSide[l]];
        int j1 = j + Facing.offsetsYForSide[Facing.oppositeSide[l]];
        int k1 = k + Facing.offsetsZForSide[Facing.oppositeSide[l]];
        boolean bl = flag = (world.getBlockMetadata(i1, j1, k1) & 8) != 0;
        return flag ? (l == 0 ? true : (l == 1 && super.shouldSideBeRendered(world, i, j, k, l) ? true : world.getBlock(i, j, k) != this.singleSlab || (world.getBlockMetadata(i, j, k) & 8) == 0)) : (l == 1 ? true : (l == 0 && super.shouldSideBeRendered(world, i, j, k, l) ? true : world.getBlock(i, j, k) != this.singleSlab || (world.getBlockMetadata(i, j, k) & 8) != 0));
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        if (item != Item.getItemFromBlock((Block)this.doubleSlab)) {
            for (int j = 0; j < this.subtypes; ++j) {
                list.add(new ItemStack(item, 1, j));
            }
        }
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return (meta & 8) == 8 || this.isOpaqueCube();
    }

    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side) {
        return (world.getBlockMetadata(x, y, z) & 8) == 8 && side == 1 || this.isOpaqueCube();
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int i, int j, int k) {
        return Item.getItemFromBlock((Block)this.singleSlab);
    }

    public static class SlabItems {

        public static class WoodSlab1Single
        extends ItemSlab {
            public WoodSlab1Single(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle, (BlockSlab)LOTRMod.woodSlabDouble, false);
            }
        }

        public static class WoodSlab1Double
        extends ItemSlab {
            public WoodSlab1Double(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle, (BlockSlab)LOTRMod.woodSlabDouble, true);
            }
        }

        public static class WoodSlab2Single
        extends ItemSlab {
            public WoodSlab2Single(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle2, (BlockSlab)LOTRMod.woodSlabDouble2, false);
            }
        }

        public static class WoodSlab2Double
        extends ItemSlab {
            public WoodSlab2Double(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle2, (BlockSlab)LOTRMod.woodSlabDouble2, true);
            }
        }

        public static class WoodSlab3Single
        extends ItemSlab {
            public WoodSlab3Single(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle3, (BlockSlab)LOTRMod.woodSlabDouble3, false);
            }
        }

        public static class WoodSlab3Double
        extends ItemSlab {
            public WoodSlab3Double(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle3, (BlockSlab)LOTRMod.woodSlabDouble3, true);
            }
        }

        public static class WoodSlab4Single
        extends ItemSlab {
            public WoodSlab4Single(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle4, (BlockSlab)LOTRMod.woodSlabDouble4, false);
            }
        }

        public static class WoodSlab4Double
        extends ItemSlab {
            public WoodSlab4Double(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle4, (BlockSlab)LOTRMod.woodSlabDouble4, true);
            }
        }

        public static class WoodSlab5Single
        extends ItemSlab {
            public WoodSlab5Single(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle5, (BlockSlab)LOTRMod.woodSlabDouble5, false);
            }
        }

        public static class WoodSlab5Double
        extends ItemSlab {
            public WoodSlab5Double(Block block) {
                super(block, (BlockSlab)LOTRMod.woodSlabSingle5, (BlockSlab)LOTRMod.woodSlabDouble5, true);
            }
        }

        public static class RottenSlabSingle
        extends ItemSlab {
            public RottenSlabSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.rottenSlabSingle, (BlockSlab)LOTRMod.rottenSlabDouble, false);
            }
        }

        public static class RottenSlabDouble
        extends ItemSlab {
            public RottenSlabDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.rottenSlabSingle, (BlockSlab)LOTRMod.rottenSlabDouble, true);
            }
        }

        public static class Slab1Single
        extends ItemSlab {
            public Slab1Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle, (BlockSlab)LOTRMod.slabDouble, false);
            }
        }

        public static class SlabWickedDwarfPillarDouble
        extends ItemSlab {
            public SlabWickedDwarfPillarDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle120, (BlockSlab)LOTRMod.slabDouble120, true);
            }
        }

        public static class SlabWickedDwarfPillarSingle
        extends ItemSlab {
            public SlabWickedDwarfPillarSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle120, (BlockSlab)LOTRMod.slabDouble120, false);
            }
        }

        public static class SlabsarnkaranMossyDouble
        extends ItemSlab {
            public SlabsarnkaranMossyDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSinglesarnkaranMossy, (BlockSlab)LOTRMod.slabDoublesarnkaranMossy, true);
            }
        }

        public static class SlabsarnkaranMossySingle
        extends ItemSlab {
            public SlabsarnkaranMossySingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSinglesarnkaranMossy, (BlockSlab)LOTRMod.slabDoublesarnkaranMossy, false);
            }
        }

        public static class SlabsarnkaranSnowyDouble
        extends ItemSlab {
            public SlabsarnkaranSnowyDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSinglesarnkaranSnowy, (BlockSlab)LOTRMod.slabDoublesarnkaranSnowy, true);
            }
        }

        public static class SlabsarnkaranSnowySingle
        extends ItemSlab {
            public SlabsarnkaranSnowySingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSinglesarnkaranSnowy, (BlockSlab)LOTRMod.slabDoublesarnkaranSnowy, false);
            }
        }

        public static class SlabWickedDwarfDouble
        extends ItemSlab {
            public SlabWickedDwarfDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle119, (BlockSlab)LOTRMod.slabDouble119, true);
            }
        }

        public static class SlabWickedDwarfSingle
        extends ItemSlab {
            public SlabWickedDwarfSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle119, (BlockSlab)LOTRMod.slabDouble119, false);
            }
        }

        public static class Slab121Double
        extends ItemSlab {
            public Slab121Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle121, (BlockSlab)LOTRMod.slabDouble121, true);
            }
        }

        public static class Slab121Single
        extends ItemSlab {
            public Slab121Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle121, (BlockSlab)LOTRMod.slabDouble121, false);
            }
        }

        public static class Slab118Double
        extends ItemSlab {
            public Slab118Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle122, (BlockSlab)LOTRMod.slabDouble122, true);
            }
        }

        public static class Slab118Single
        extends ItemSlab {
            public Slab118Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle122, (BlockSlab)LOTRMod.slabDouble122, false);
            }
        }

        public static class Slab1Double
        extends ItemSlab {
            public Slab1Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle, (BlockSlab)LOTRMod.slabDouble, true);
            }
        }

        public static class Slab2Single
        extends ItemSlab {
            public Slab2Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle2, (BlockSlab)LOTRMod.slabDouble2, false);
            }
        }

        public static class Slab2Double
        extends ItemSlab {
            public Slab2Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle2, (BlockSlab)LOTRMod.slabDouble2, true);
            }
        }

        public static class Slab3Single
        extends ItemSlab {
            public Slab3Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle3, (BlockSlab)LOTRMod.slabDouble3, false);
            }
        }

        public static class Slab3Double
        extends ItemSlab {
            public Slab3Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle3, (BlockSlab)LOTRMod.slabDouble3, true);
            }
        }

        public static class Slab4Single
        extends ItemSlab {
            public Slab4Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle4, (BlockSlab)LOTRMod.slabDouble4, false);
            }
        }

        public static class Slab4Double
        extends ItemSlab {
            public Slab4Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle4, (BlockSlab)LOTRMod.slabDouble4, true);
            }
        }

        public static class Slab5Single
        extends ItemSlab {
            public Slab5Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle5, (BlockSlab)LOTRMod.slabDouble5, false);
            }
        }

        public static class Slab5Double
        extends ItemSlab {
            public Slab5Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle5, (BlockSlab)LOTRMod.slabDouble5, true);
            }
        }

        public static class Slab6Single
        extends ItemSlab {
            public Slab6Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle6, (BlockSlab)LOTRMod.slabDouble6, false);
            }
        }

        public static class Slab6Double
        extends ItemSlab {
            public Slab6Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle6, (BlockSlab)LOTRMod.slabDouble6, true);
            }
        }

        public static class Slab7Single
        extends ItemSlab {
            public Slab7Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle7, (BlockSlab)LOTRMod.slabDouble7, false);
            }
        }

        public static class Slab7Double
        extends ItemSlab {
            public Slab7Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle7, (BlockSlab)LOTRMod.slabDouble7, true);
            }
        }

        public static class Slab8Single
        extends ItemSlab {
            public Slab8Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle8, (BlockSlab)LOTRMod.slabDouble8, false);
            }
        }

        public static class Slab8Double
        extends ItemSlab {
            public Slab8Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle8, (BlockSlab)LOTRMod.slabDouble8, true);
            }
        }

        public static class Slab9Single
        extends ItemSlab {
            public Slab9Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle9, (BlockSlab)LOTRMod.slabDouble9, false);
            }
        }

        public static class Slab9Double
        extends ItemSlab {
            public Slab9Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle9, (BlockSlab)LOTRMod.slabDouble9, true);
            }
        }

        public static class Slab10Single
        extends ItemSlab {
            public Slab10Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle10, (BlockSlab)LOTRMod.slabDouble10, false);
            }
        }

        public static class Slab10Double
        extends ItemSlab {
            public Slab10Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle10, (BlockSlab)LOTRMod.slabDouble10, true);
            }
        }

        public static class Slab11Single
        extends ItemSlab {
            public Slab11Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle11, (BlockSlab)LOTRMod.slabDouble11, false);
            }
        }

        public static class Slab11Double
        extends ItemSlab {
            public Slab11Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle11, (BlockSlab)LOTRMod.slabDouble11, true);
            }
        }

        public static class Slab12Single
        extends ItemSlab {
            public Slab12Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle12, (BlockSlab)LOTRMod.slabDouble12, false);
            }
        }

        public static class Slab12Double
        extends ItemSlab {
            public Slab12Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle12, (BlockSlab)LOTRMod.slabDouble12, true);
            }
        }

        public static class Slab13Single
        extends ItemSlab {
            public Slab13Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle13, (BlockSlab)LOTRMod.slabDouble13, false);
            }
        }

        public static class Slab13Double
        extends ItemSlab {
            public Slab13Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle13, (BlockSlab)LOTRMod.slabDouble13, true);
            }
        }

        public static class Slab14Single
        extends ItemSlab {
            public Slab14Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle14, (BlockSlab)LOTRMod.slabDouble14, false);
            }
        }

        public static class Slab14Double
        extends ItemSlab {
            public Slab14Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingle14, (BlockSlab)LOTRMod.slabDouble14, true);
            }
        }

        public static class SlabVSingle
        extends ItemSlab {
            public SlabVSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleV, (BlockSlab)LOTRMod.slabDoubleV, false);
            }
        }

        public static class SlabVDouble
        extends ItemSlab {
            public SlabVDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleV, (BlockSlab)LOTRMod.slabDoubleV, true);
            }
        }

        public static class UtumnoSingle
        extends ItemSlab {
            public UtumnoSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabUtumnoSingle, (BlockSlab)LOTRMod.slabUtumnoDouble, false);
            }
        }

        public static class UtumnoDouble
        extends ItemSlab {
            public UtumnoDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabUtumnoSingle, (BlockSlab)LOTRMod.slabUtumnoDouble, true);
            }
        }

        public static class Utumno2Single
        extends ItemSlab {
            public Utumno2Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabUtumnoSingle2, (BlockSlab)LOTRMod.slabUtumnoDouble2, false);
            }
        }

        public static class Utumno2Double
        extends ItemSlab {
            public Utumno2Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabUtumnoSingle2, (BlockSlab)LOTRMod.slabUtumnoDouble2, true);
            }
        }

        public static class ScorchedSingle
        extends ItemSlab {
            public ScorchedSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.scorchedSlabSingle, (BlockSlab)LOTRMod.scorchedSlabDouble, false);
            }
        }

        public static class ScorchedDouble
        extends ItemSlab {
            public ScorchedDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.scorchedSlabSingle, (BlockSlab)LOTRMod.scorchedSlabDouble, true);
            }
        }

        public static class ClayTileSingle
        extends ItemSlab {
            public ClayTileSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabClayTileSingle, (BlockSlab)LOTRMod.slabClayTileDouble, false);
            }
        }

        public static class ClayTileDouble
        extends ItemSlab {
            public ClayTileDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabClayTileSingle, (BlockSlab)LOTRMod.slabClayTileDouble, true);
            }
        }

        public static class ClayTileDyedSingle
        extends ItemSlab {
            public ClayTileDyedSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabClayTileDyedSingle, (BlockSlab)LOTRMod.slabClayTileDyedDouble, false);
            }
        }

        public static class ClayTileDyedDouble
        extends ItemSlab {
            public ClayTileDyedDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabClayTileDyedSingle, (BlockSlab)LOTRMod.slabClayTileDyedDouble, true);
            }
        }

        public static class ClayTileDyed2Single
        extends ItemSlab {
            public ClayTileDyed2Single(Block block) {
                super(block, (BlockSlab)LOTRMod.slabClayTileDyedSingle2, (BlockSlab)LOTRMod.slabClayTileDyedDouble2, false);
            }
        }

        public static class ClayTileDyed2Double
        extends ItemSlab {
            public ClayTileDyed2Double(Block block) {
                super(block, (BlockSlab)LOTRMod.slabClayTileDyedSingle2, (BlockSlab)LOTRMod.slabClayTileDyedDouble2, true);
            }
        }

        public static class ThatchSingle
        extends ItemSlab {
            public ThatchSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleThatch, (BlockSlab)LOTRMod.slabDoubleThatch, false);
            }
        }

        public static class ThatchDouble
        extends ItemSlab {
            public ThatchDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleThatch, (BlockSlab)LOTRMod.slabDoubleThatch, true);
            }
        }

        public static class DirtSingle
        extends ItemSlab {
            public DirtSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleDirt, (BlockSlab)LOTRMod.slabDoubleDirt, false);
            }
        }

        public static class DirtDouble
        extends ItemSlab {
            public DirtDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleDirt, (BlockSlab)LOTRMod.slabDoubleDirt, true);
            }
        }

        public static class SandSingle
        extends ItemSlab {
            public SandSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleSand, (BlockSlab)LOTRMod.slabDoubleSand, false);
            }
        }

        public static class SandDouble
        extends ItemSlab {
            public SandDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleSand, (BlockSlab)LOTRMod.slabDoubleSand, true);
            }
        }

        public static class GravelSingle
        extends ItemSlab {
            public GravelSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleGravel, (BlockSlab)LOTRMod.slabDoubleGravel, false);
            }
        }

        public static class GravelDouble
        extends ItemSlab {
            public GravelDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabSingleGravel, (BlockSlab)LOTRMod.slabDoubleGravel, true);
            }
        }

        public static class BoneSingle
        extends ItemSlab {
            public BoneSingle(Block block) {
                super(block, (BlockSlab)LOTRMod.slabBoneSingle, (BlockSlab)LOTRMod.slabBoneDouble, false);
            }
        }

        public static class BoneDouble
        extends ItemSlab {
            public BoneDouble(Block block) {
                super(block, (BlockSlab)LOTRMod.slabBoneSingle, (BlockSlab)LOTRMod.slabBoneDouble, true);
            }
        }

    }

}

