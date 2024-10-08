/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.lang.reflect.Constructor;
import java.util.Random;
import lotr.common.entity.npc.LOTREntityGondorBaker;
import lotr.common.entity.npc.LOTREntityGondorBlacksmith;
import lotr.common.entity.npc.LOTREntityGondorBrewer;
import lotr.common.entity.npc.LOTREntityGondorButcher;
import lotr.common.entity.npc.LOTREntityGondorFarmer;
import lotr.common.entity.npc.LOTREntityGondorFishmonger;
import lotr.common.entity.npc.LOTREntityGondorFlorist;
import lotr.common.entity.npc.LOTREntityGondorGreengrocer;
import lotr.common.entity.npc.LOTREntityGondorLumberman;
import lotr.common.entity.npc.LOTREntityGondorMan;
import lotr.common.entity.npc.LOTREntityGondorMason;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class LOTRWorldGenGondorMarketStall
extends LOTRWorldGenGondorStructure {
    private static Class[] allStallTypes = new Class[]{Greengrocer.class, Lumber.class, Mason.class, Brewer.class, Flowers.class, Butcher.class, Fish.class, Farmer.class, Blacksmith.class, Baker.class};

    public static LOTRWorldGenStructureBase2 getRandomStall(Random random, boolean flag) {
        try {
            Class cls = allStallTypes[random.nextInt(allStallTypes.length)];
            return (LOTRWorldGenStructureBase2)((Object)cls.getConstructor(Boolean.TYPE).newInstance(flag));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LOTRWorldGenGondorMarketStall(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int j1;
        this.setOriginAndRotation(world, i, j, k, rotation, 3);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i1 = -2; i1 <= 2; ++i1) {
                for (int k1 = -2; k1 <= 2; ++k1) {
                    j1 = this.getTopBlock(world, i1, k1) - 1;
                    if (!this.isSurface(world, i1, j1, k1)) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight <= 5) continue;
                    return false;
                }
            }
        }
        for (int i1 = -2; i1 <= 2; ++i1) {
            for (int k1 = -2; k1 <= 2; ++k1) {
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k1);
                for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i1, j1, k1) || this.getY(j1) < 0); --j1) {
                    this.setBlockAndMetadata(world, i1, j1, k1, this.brickBlock, this.brickMeta);
                    this.setGrassToDirt(world, i1, j1 - 1, k1);
                }
                for (j1 = 1; j1 <= 4; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
                if (i2 == 2 && k2 == 2) {
                    for (j1 = 1; j1 <= 3; ++j1) {
                        this.setBlockAndMetadata(world, i1, j1, k1, this.fenceBlock, this.fenceMeta);
                    }
                } else if (i2 == 2 || k2 == 2) {
                    this.setBlockAndMetadata(world, i1, 1, k1, this.plankBlock, this.plankMeta);
                    this.setBlockAndMetadata(world, i1, 3, k1, this.fenceBlock, this.fenceMeta);
                }
                this.generateRoof(world, random, i1, 4, k1);
            }
        }
        this.setBlockAndMetadata(world, -2, 1, 0, this.fenceGateBlock, 1);
        this.setBlockAndMetadata(world, -1, 1, -1, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, 1, 1, -1, (Block)Blocks.chest, 3);
        LOTREntityGondorMan trader = this.createTrader(world);
        this.spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
        return true;
    }

    protected abstract void generateRoof(World var1, Random var2, int var3, int var4, int var5);

    protected abstract LOTREntityGondorMan createTrader(World var1);

    public static class Greengrocer
    extends LOTRWorldGenGondorMarketStall {
        public Greengrocer(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int i2 = Math.abs(i1);
            if (IntMath.mod((int)(i2 + Math.abs(k1)), (int)2) == 0) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 14);
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 5);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorGreengrocer(world);
        }
    }

    public static class Lumber
    extends LOTRWorldGenGondorMarketStall {
        public Lumber(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int i2 = Math.abs(i1);
            int k2 = Math.abs(k1);
            if ((i2 == 2 || k2 == 2) && IntMath.mod((int)(i2 + k2), (int)2) == 0) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 13);
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 12);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorLumberman(world);
        }
    }

    public static class Mason
    extends LOTRWorldGenGondorMarketStall {
        public Mason(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int i2 = Math.abs(i1);
            int k2 = Math.abs(k1);
            if (i2 == 2 || k2 == 2 || i2 != 1 && k2 != 1) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 7);
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 8);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorMason(world);
        }
    }

    public static class Brewer
    extends LOTRWorldGenGondorMarketStall {
        public Brewer(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int i2 = Math.abs(i1);
            Math.abs(k1);
            if (i2 % 2 == 0) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 12);
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 4);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorBrewer(world);
        }
    }

    public static class Flowers
    extends LOTRWorldGenGondorMarketStall {
        public Flowers(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int i2 = Math.abs(i1);
            if (i2 == Math.abs(k1)) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 4);
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 13);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorFlorist(world);
        }
    }

    public static class Butcher
    extends LOTRWorldGenGondorMarketStall {
        public Butcher(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int i2 = Math.abs(i1);
            int k2 = Math.abs(k1);
            if (i2 == 2 || k2 == 2) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 6);
            } else if (i2 == 1 || k2 == 1) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 14);
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 0);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorButcher(world);
        }
    }

    public static class Fish
    extends LOTRWorldGenGondorMarketStall {
        public Fish(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int i2 = Math.abs(i1);
            int k2 = Math.abs(k1);
            if (i2 % 2 == 0) {
                if (k2 == 2) {
                    this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 0);
                } else {
                    this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 3);
                }
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 11);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorFishmonger(world);
        }
    }

    public static class Farmer
    extends LOTRWorldGenGondorMarketStall {
        public Farmer(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int k2;
            int i2 = Math.abs(i1);
            if (IntMath.mod((int)(i2 + (k2 = Math.abs(k1))), (int)2) == 0) {
                if (Integer.signum(i1) != -Integer.signum(k1) && i2 + k2 == 2) {
                    this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 4);
                } else {
                    this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 13);
                }
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 12);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorFarmer(world);
        }
    }

    public static class Blacksmith
    extends LOTRWorldGenGondorMarketStall {
        public Blacksmith(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            int i2 = Math.abs(i1);
            if (i2 == Math.abs(k1)) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 15);
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 7);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorBlacksmith(world);
        }
    }

    public static class Baker
    extends LOTRWorldGenGondorMarketStall {
        public Baker(boolean flag) {
            super(flag);
        }

        @Override
        protected void generateRoof(World world, Random random, int i1, int j1, int k1) {
            Math.abs(i1);
            int k2 = Math.abs(k1);
            if (k2 % 2 == 0) {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 1);
            } else {
                this.setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 12);
            }
        }

        @Override
        protected LOTREntityGondorMan createTrader(World world) {
            return new LOTREntityGondorBaker(world);
        }
    }

}

