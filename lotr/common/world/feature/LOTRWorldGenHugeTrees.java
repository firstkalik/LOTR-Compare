/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenHugeTrees
extends WorldGenAbstractTree {
    static final byte[] otherCoordPairs = new byte[]{2, 0, 0, 1, 2, 1};
    Random rand = new Random();
    World worldObj;
    int[] basePos = new int[]{0, 0, 0};
    int heightLimit;
    int height;
    double heightAttenuation = 0.618;
    double branchDensity = 1.0;
    double branchSlope = 0.381;
    double scaleWidth = 1.0;
    double leafDensity = 1.0;
    int trunkSize = 1;
    int heightLimitLimit = 10;
    int leafDistanceLimit = 3;
    int[][] leafNodes;
    private final Block wood;
    private final Block leaves;
    private final int metaWood;
    private final int metaLeaves;

    public LOTRWorldGenHugeTrees(Block wood, int metaWood, Block leaves, int metaLeaves) {
        super(false);
        this.wood = wood;
        this.leaves = leaves;
        this.metaWood = metaWood;
        this.metaLeaves = metaLeaves;
    }

    void generateLeafNodeList() {
        int i;
        this.height = (int)((double)this.heightLimit * this.heightAttenuation);
        if (this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }
        if ((i = (int)(1.382 + Math.pow(this.leafDensity * (double)this.heightLimit / 16.0, 2.0))) < 1) {
            i = 1;
        }
        int[][] aint = new int[i * this.heightLimit][4];
        int j = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
        int k = 1;
        int l = this.basePos[1] + this.height;
        int i1 = j - this.basePos[1];
        aint[0][0] = this.basePos[0];
        aint[0][1] = j--;
        aint[0][2] = this.basePos[2];
        aint[0][3] = l;
        while (i1 >= 0) {
            float f = this.layerSize(i1);
            if (f < 0.0f) {
                --j;
                --i1;
                continue;
            }
            double d0 = 0.5;
            for (int j1 = 0; j1 < i; ++j1) {
                double d1 = this.scaleWidth * (double)f * ((double)this.rand.nextFloat() + 0.328);
                double d2 = (double)this.rand.nextFloat() * 2.0 * 3.141592653589793;
                int k1 = MathHelper.floor_double((double)(d1 * Math.sin(d2) + (double)this.basePos[0] + d0));
                int l1 = MathHelper.floor_double((double)(d1 * Math.cos(d2) + (double)this.basePos[2] + d0));
                int[] aint1 = new int[]{k1, j, l1};
                int[] aint2 = new int[]{k1, j + this.leafDistanceLimit, l1};
                if (this.checkBlockLine(aint1, aint2) != -1) continue;
                int[] aint3 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
                double d3 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - aint1[0]), 2.0) + Math.pow(Math.abs(this.basePos[2] - aint1[2]), 2.0));
                double d4 = d3 * this.branchSlope;
                int n = aint3[1] = (double)aint1[1] - d4 > (double)l ? l : (int)((double)aint1[1] - d4);
                if (this.checkBlockLine(aint3, aint1) != -1) continue;
                aint[k][0] = k1;
                aint[k][1] = j;
                aint[k][2] = l1;
                aint[k][3] = aint3[1];
                ++k;
            }
            --j;
            --i1;
        }
        this.leafNodes = new int[k][4];
        System.arraycopy(aint, 0, this.leafNodes, 0, k);
    }

    void func_150529_a(int p_150529_1_, int p_150529_2_, int p_150529_3_, float p_150529_4_, byte p_150529_5_, Block p_150529_6_) {
        int l = (int)((double)p_150529_4_ + 0.618);
        byte b1 = otherCoordPairs[p_150529_5_];
        byte b2 = otherCoordPairs[p_150529_5_ + 3];
        int[] aint = new int[]{p_150529_1_, p_150529_2_, p_150529_3_};
        int[] aint1 = new int[]{0, 0, 0};
        int j1 = -l;
        aint1[p_150529_5_] = aint[p_150529_5_];
        for (int i1 = -l; i1 <= l; ++i1) {
            aint1[b1] = aint[b1] + i1;
            j1 = -l;
            while (j1 <= l) {
                double d0 = Math.pow((double)Math.abs(i1) + 0.5, 2.0) + Math.pow((double)Math.abs(j1) + 0.5, 2.0);
                if (d0 > (double)(p_150529_4_ * p_150529_4_)) {
                    ++j1;
                    continue;
                }
                aint1[b2] = aint[b2] + j1;
                Block block1 = this.worldObj.getBlock(aint1[0], aint1[1], aint1[2]);
                if (!block1.isAir((IBlockAccess)this.worldObj, aint1[0], aint1[1], aint1[2]) && !block1.isLeaves((IBlockAccess)this.worldObj, aint1[0], aint1[1], aint1[2])) {
                    ++j1;
                    continue;
                }
                this.setBlockAndNotifyAdequately(this.worldObj, aint1[0], aint1[1], aint1[2], p_150529_6_, this.metaLeaves);
                ++j1;
            }
        }
    }

    float layerSize(int par1) {
        if ((double)par1 < (double)this.heightLimit * 0.3) {
            return -1.618f;
        }
        float f = (float)this.heightLimit / 2.0f;
        float f1 = (float)this.heightLimit / 2.0f - (float)par1;
        float f2 = f1 == 0.0f ? f : (Math.abs(f1) >= f ? 0.0f : (float)Math.sqrt(Math.pow(Math.abs(f), 2.0) - Math.pow(Math.abs(f1), 2.0)));
        return f2 *= 0.5f;
    }

    float leafSize(int par1) {
        return par1 >= 0 && par1 < this.leafDistanceLimit ? (par1 != 0 && par1 != this.leafDistanceLimit - 1 ? 3.0f : 2.0f) : -1.0f;
    }

    void generateLeafNode(int par1, int par2, int par3) {
        int i1 = par2 + this.leafDistanceLimit;
        for (int l = par2; l < i1; ++l) {
            float f = this.leafSize(l - par2);
            this.func_150529_a(par1, l, par3, f, (byte)1, this.leaves);
        }
    }

    void func_150530_a(int[] p_150530_1_, int[] p_150530_2_, Block p_150530_3_) {
        int[] aint2 = new int[]{0, 0, 0};
        int b1 = 0;
        for (int b0 = 0; b0 < 3; b0 = (int)((byte)(b0 + 1))) {
            aint2[b0] = p_150530_2_[b0] - p_150530_1_[b0];
            if (Math.abs(aint2[b0]) <= Math.abs(aint2[b1])) continue;
            b1 = b0;
        }
        if (aint2[b1] != 0) {
            byte b2 = otherCoordPairs[b1];
            byte b3 = otherCoordPairs[b1 + 3];
            int b4 = aint2[b1] > 0 ? 1 : -1;
            double d0 = (double)aint2[b2] / (double)aint2[b1];
            double d1 = (double)aint2[b3] / (double)aint2[b1];
            int[] aint3 = new int[]{0, 0, 0};
            int j = aint2[b1] + b4;
            for (int i = 0; i != j; i += b4) {
                int l;
                aint3[b1] = MathHelper.floor_double((double)((double)(p_150530_1_[b1] + i) + 0.5));
                aint3[b2] = MathHelper.floor_double((double)((double)p_150530_1_[b2] + (double)i * d0 + 0.5));
                aint3[b3] = MathHelper.floor_double((double)((double)p_150530_1_[b3] + (double)i * d1 + 0.5));
                byte b5 = (byte)this.metaWood;
                int k = Math.abs(aint3[0] - p_150530_1_[0]);
                int i1 = Math.max(k, l = Math.abs(aint3[2] - p_150530_1_[2]));
                if (i1 > 0) {
                    if (k == i1) {
                        b5 = (byte)(b5 + 4);
                    } else if (l == i1) {
                        b5 = (byte)(b5 + 8);
                    }
                }
                this.setBlockAndNotifyAdequately(this.worldObj, aint3[0], aint3[1], aint3[2], p_150530_3_, (int)b5);
            }
        }
    }

    void generateLeaves() {
        int j = this.leafNodes.length;
        for (int i = 0; i < j; ++i) {
            int k = this.leafNodes[i][0];
            int l = this.leafNodes[i][1];
            int i1 = this.leafNodes[i][2];
            this.generateLeafNode(k, l, i1);
        }
    }

    boolean leafNodeNeedsBase(int par1) {
        return (double)par1 >= (double)this.heightLimit * 0.2;
    }

    void generateTrunk() {
        int i = this.basePos[0];
        int j = this.basePos[1];
        int k = this.basePos[1] + this.height;
        int l = this.basePos[2];
        int[] aint = new int[]{i, j, l};
        int[] aint1 = new int[]{i, k, l};
        this.func_150530_a(aint, aint1, this.wood);
        if (this.trunkSize == 2) {
            aint[0] = aint[0] + 1;
            aint1[0] = aint1[0] + 1;
            this.func_150530_a(aint, aint1, this.wood);
            aint[2] = aint[2] + 1;
            aint1[2] = aint1[2] + 1;
            this.func_150530_a(aint, aint1, this.wood);
            aint[0] = aint[0] + -1;
            aint1[0] = aint1[0] + -1;
            this.func_150530_a(aint, aint1, this.wood);
        }
    }

    void generateLeafNodeBases() {
        int j = this.leafNodes.length;
        int[] aint = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
        for (int i = 0; i < j; ++i) {
            int[] aint1 = this.leafNodes[i];
            int[] aint2 = new int[]{aint1[0], aint1[1], aint1[2]};
            aint[1] = aint1[3];
            int k = aint[1] - this.basePos[1];
            if (!this.leafNodeNeedsBase(k)) continue;
            this.func_150530_a(aint, aint2, this.wood);
        }
    }

    int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
        int i;
        int[] aint2 = new int[]{0, 0, 0};
        int b1 = 0;
        for (int b0 = 0; b0 < 3; b0 = (int)((byte)(b0 + 1))) {
            aint2[b0] = par2ArrayOfInteger[b0] - par1ArrayOfInteger[b0];
            if (Math.abs(aint2[b0]) <= Math.abs(aint2[b1])) continue;
            b1 = b0;
        }
        if (aint2[b1] == 0) {
            return -1;
        }
        byte b2 = otherCoordPairs[b1];
        byte b3 = otherCoordPairs[b1 + 3];
        int b4 = aint2[b1] > 0 ? 1 : -1;
        double d0 = (double)aint2[b2] / (double)aint2[b1];
        double d1 = (double)aint2[b3] / (double)aint2[b1];
        int[] aint3 = new int[]{0, 0, 0};
        int j = aint2[b1] + b4;
        for (i = 0; i != j; i += b4) {
            aint3[b1] = par1ArrayOfInteger[b1] + i;
            aint3[b2] = MathHelper.floor_double((double)((double)par1ArrayOfInteger[b2] + (double)i * d0));
            aint3[b3] = MathHelper.floor_double((double)((double)par1ArrayOfInteger[b3] + (double)i * d1));
            Block block = this.worldObj.getBlock(aint3[0], aint3[1], aint3[2]);
            if (!this.isReplaceable(this.worldObj, aint3[0], aint3[1], aint3[2])) break;
        }
        return i == j ? -1 : Math.abs(i);
    }

    boolean validTreeLocation() {
        int[] aint = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] aint1 = new int[]{this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
        Block block = this.worldObj.getBlock(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
        boolean isSoil = block.canSustainPlant((IBlockAccess)this.worldObj, this.basePos[0], this.basePos[1] - 1, this.basePos[2], ForgeDirection.UP, (IPlantable)((BlockSapling)Blocks.sapling));
        if (!isSoil) {
            return false;
        }
        int i = this.checkBlockLine(aint, aint1);
        if (i == -1) {
            return true;
        }
        if (i < 6) {
            return false;
        }
        this.heightLimit = i;
        return true;
    }

    public void setScale(double par1, double par3, double par5) {
        this.heightLimitLimit = (int)(par1 * 12.0);
        if (par1 > 0.5) {
            this.leafDistanceLimit = 3;
        }
        this.scaleWidth = par3;
        this.leafDensity = par5;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
        this.worldObj = par1World;
        long l = par2Random.nextLong();
        this.rand.setSeed(l);
        this.basePos[0] = par3;
        this.basePos[1] = par4;
        this.basePos[2] = par5;
        if (this.heightLimit == 0) {
            this.heightLimit = 15 + this.rand.nextInt(this.heightLimitLimit);
        }
        if (!this.validTreeLocation()) {
            return false;
        }
        this.generateLeafNodeList();
        this.generateLeaves();
        this.generateTrunk();
        this.generateLeafNodeBases();
        return true;
    }
}

