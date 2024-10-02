/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBreeBaker;
import lotr.common.entity.npc.LOTREntityBreeBlacksmith;
import lotr.common.entity.npc.LOTREntityBreeBrewer;
import lotr.common.entity.npc.LOTREntityBreeButcher;
import lotr.common.entity.npc.LOTREntityBreeFarmer;
import lotr.common.entity.npc.LOTREntityBreeFlorist;
import lotr.common.entity.npc.LOTREntityBreeGuard;
import lotr.common.entity.npc.LOTREntityBreeHobbitBaker;
import lotr.common.entity.npc.LOTREntityBreeHobbitBrewer;
import lotr.common.entity.npc.LOTREntityBreeHobbitButcher;
import lotr.common.entity.npc.LOTREntityBreeHobbitFlorist;
import lotr.common.entity.npc.LOTREntityBreeLumberman;
import lotr.common.entity.npc.LOTREntityBreeMason;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.world.structure2.LOTRWorldGenBreeStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class LOTRWorldGenBreeMarketStall
extends LOTRWorldGenBreeStructure {
    public static Class[] allStallTypes = new Class[]{Baker.class, Butcher.class, Brewer.class, Mason.class, Lumber.class, Smith.class, Florist.class, Farmer.class};
    public Block wool1Block;
    public int wool1Meta;
    public Block wool2Block;
    public int wool2Meta;

    public LOTRWorldGenBreeMarketStall(boolean flag) {
        super(flag);
    }

    public abstract LOTREntityNPC createTrader(World var1, Random var2);

    public abstract void decorateStall(World var1, Random var2);

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int i12;
        int k12;
        int j1;
        int step;
        int i2;
        int j2;
        int j12;
        int i1;
        int k2;
        this.setOriginAndRotation(world, i, j, k, rotation, 3);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i1 = -3; i1 <= 3; ++i1) {
                for (int k13 = -3; k13 <= 3; ++k13) {
                    j12 = this.getTopBlock(world, i1, k13) - 1;
                    if (!this.isSurface(world, i1, j12, k13)) {
                        return false;
                    }
                    if (j12 < minHeight) {
                        minHeight = j12;
                    }
                    if (j12 > maxHeight) {
                        maxHeight = j12;
                    }
                    if (maxHeight - minHeight <= 6) continue;
                    return false;
                }
            }
        }
        for (i12 = -2; i12 <= 2; ++i12) {
            for (k12 = -2; k12 <= 2; ++k12) {
                i2 = Math.abs(i12);
                k2 = Math.abs(k12);
                if (i2 == 2 && k2 == 2) {
                    for (j12 = 3; !(j12 < 0 && this.isOpaque(world, i12, j12, k12) || this.getY(j12) < 0); --j12) {
                        this.setBlockAndMetadata(world, i12, j12, k12, this.beamBlock, this.beamMeta);
                        this.setGrassToDirt(world, i12, j12 - 1, k12);
                    }
                    continue;
                }
                this.placeRandomFloor(world, random, i12, 0, k12);
                this.setGrassToDirt(world, i12, -1, k12);
                j12 = -1;
                while (!this.isOpaque(world, i12, j12, k12) && this.getY(j12) >= 0) {
                    this.setBlockAndMetadata(world, i12, j12, k12, Blocks.dirt, 0);
                    this.setGrassToDirt(world, i12, j12 - 1, k12);
                    --j12;
                }
                for (j12 = 1; j12 <= 4; ++j12) {
                    this.setAir(world, i12, j12, k12);
                }
                if (!(i2 == 2 && k2 <= 1 || k2 == 2 && i2 <= 1)) continue;
                this.setBlockAndMetadata(world, i12, 3, k12, this.fenceBlock, this.fenceMeta);
            }
        }
        for (i12 = -3; i12 <= 3; ++i12) {
            for (k12 = -3; k12 <= 3; ++k12) {
                i2 = Math.abs(i12);
                k2 = Math.abs(k12);
                if (i2 == 3 && k2 >= 1 && k2 <= 2 || k2 == 3 && i2 >= 1 && i2 <= 2) {
                    this.setBlockAndMetadata(world, i12, 3, k12, this.wool1Block, this.wool1Meta);
                }
                if (i2 + k2 == 3 || i2 + k2 == 4) {
                    if (i2 == 2 && k2 == 2) {
                        this.setBlockAndMetadata(world, i12, 4, k12, this.wool2Block, this.wool2Meta);
                    } else {
                        this.setBlockAndMetadata(world, i12, 4, k12, this.wool1Block, this.wool1Meta);
                    }
                }
                if (i2 + k2 > 2) continue;
                if (i2 == k2) {
                    this.setBlockAndMetadata(world, i12, 5, k12, this.wool2Block, this.wool2Meta);
                    continue;
                }
                this.setBlockAndMetadata(world, i12, 5, k12, this.wool1Block, this.wool1Meta);
            }
        }
        this.setBlockAndMetadata(world, -1, 1, -2, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 0, 1, -2, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, 1, 1, -2, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, 2, 1, -1, this.fenceGateBlock, 3);
        this.setBlockAndMetadata(world, 2, 1, 0, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, 2, 1, 1, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, 1, 1, 2, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, 0, 1, 2, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, -1, 1, 2, this.fenceGateBlock, 0);
        this.setBlockAndMetadata(world, -2, 1, 1, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, -2, 1, 0, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, -2, 1, -1, this.plankStairBlock, 7);
        for (i12 = -1; i12 <= 1; ++i12) {
            this.setBlockAndMetadata(world, i12, 1, -3, this.trapdoorBlock, 12);
        }
        for (int k14 = -1; k14 <= 1; ++k14) {
            this.setBlockAndMetadata(world, -3, 1, k14, this.trapdoorBlock, 15);
        }
        if (random.nextBoolean()) {
            this.setBlockAndMetadata(world, 1, 1, 1, (Block)Blocks.chest, 2);
        } else {
            this.setBlockAndMetadata(world, 1, 1, 1, LOTRMod.chestBasket, 2);
        }
        for (step = 0; step < 12; ++step) {
            i1 = 3 + step;
            j1 = 0 - step;
            k1 = -1;
            if (this.isOpaque(world, i1, j1, -1)) break;
            this.placeRandomFloor(world, random, i1, j1, k1);
            this.setGrassToDirt(world, i1, j1 - 1, k1);
            j2 = j1 - 1;
            while (!this.isOpaque(world, i1, j2, k1) && this.getY(j2) >= 0) {
                this.setBlockAndMetadata(world, i1, j2, k1, Blocks.dirt, 0);
                this.setGrassToDirt(world, i1, j2 - 1, k1);
                --j2;
            }
        }
        for (step = 0; step < 12; ++step) {
            i1 = -1;
            j1 = 0 - step;
            k1 = 3 + step;
            if (this.isOpaque(world, -1, j1, k1)) break;
            this.placeRandomFloor(world, random, i1, j1, k1);
            this.setGrassToDirt(world, i1, j1 - 1, k1);
            j2 = j1 - 1;
            while (!this.isOpaque(world, i1, j2, k1) && this.getY(j2) >= 0) {
                this.setBlockAndMetadata(world, i1, j2, k1, Blocks.dirt, 0);
                this.setGrassToDirt(world, i1, j2 - 1, k1);
                --j2;
            }
        }
        this.decorateStall(world, random);
        LOTREntityNPC trader = this.createTrader(world, random);
        this.spawnNPCAndSetHome(trader, world, 0, 1, 0, 1);
        return true;
    }

    @Override
    public void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.wool1Block = Blocks.wool;
        this.wool1Meta = 14;
        this.wool2Block = Blocks.wool;
        this.wool2Meta = 0;
    }

    public static LOTRWorldGenBreeMarketStall getRandomStall(Random random, boolean flag) {
        try {
            Class cls = allStallTypes[random.nextInt(allStallTypes.length)];
            return (LOTRWorldGenBreeMarketStall)((Object)cls.getConstructor(Boolean.TYPE).newInstance(flag));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LOTRWorldGenBreeMarketStall[] getRandomStalls(Random random, boolean flag, int num) {
        List<Class> types = Arrays.asList(Arrays.copyOf(allStallTypes, allStallTypes.length));
        Collections.shuffle(types, random);
        LOTRWorldGenBreeMarketStall[] ret = new LOTRWorldGenBreeMarketStall[num];
        for (int i = 0; i < ret.length; ++i) {
            int listIndex = i % types.size();
            Class cls = types.get(listIndex);
            try {
                ret[i] = (LOTRWorldGenBreeMarketStall)((Object)cls.getConstructor(Boolean.TYPE).newInstance(flag));
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static class Smith
    extends LOTRWorldGenBreeMarketStall {
        public Smith(boolean flag) {
            super(flag);
        }

        @Override
        public LOTREntityNPC createTrader(World world, Random random) {
            return new LOTREntityBreeBlacksmith(world);
        }

        @Override
        public void decorateStall(World world, Random random) {
            this.placeWeaponRack(world, 1, 2, -2, 7, new ItemStack(LOTRMod.ironCrossbow));
            this.placeWeaponRack(world, -2, 2, 1, 3, new ItemStack(LOTRMod.battleaxeIron));
            this.placeWeaponRack(world, 2, 2, 1, 1, new ItemStack(Items.iron_sword));
            LOTREntityBreeGuard armorGuard = new LOTREntityBreeGuard(world);
            armorGuard.onSpawnWithEgg(null);
            this.placeArmorStand(world, 0, 1, 1, 0, new ItemStack[]{armorGuard.getEquipmentInSlot(4), armorGuard.getEquipmentInSlot(3), null, null});
        }

        @Override
        public void setupRandomBlocks(Random random) {
            super.setupRandomBlocks(random);
            this.wool1Block = Blocks.wool;
            this.wool1Meta = 7;
        }
    }

    public static class Mason
    extends LOTRWorldGenBreeMarketStall {
        public Mason(boolean flag) {
            super(flag);
        }

        @Override
        public LOTREntityNPC createTrader(World world, Random random) {
            return new LOTREntityBreeMason(world);
        }

        @Override
        public void decorateStall(World world, Random random) {
            this.setBlockAndMetadata(world, 0, 1, 1, this.brickBlock, this.brickMeta);
            this.setBlockAndMetadata(world, 0, 2, 1, this.brickBlock, this.brickMeta);
            this.setGrassToDirt(world, 0, 0, 1);
            this.setBlockAndMetadata(world, -1, 1, -1, Blocks.cobblestone, 0);
            this.setGrassToDirt(world, -1, 0, -1);
            this.placeWeaponRack(world, 1, 2, -2, 7, new ItemStack(Items.iron_pickaxe));
            this.placeWeaponRack(world, -2, 2, 1, 6, new ItemStack(LOTRMod.pickaxeBronze));
        }

        @Override
        public void setupRandomBlocks(Random random) {
            super.setupRandomBlocks(random);
            this.wool1Block = Blocks.wool;
            this.wool1Meta = 8;
        }
    }

    public static class Lumber
    extends LOTRWorldGenBreeMarketStall {
        public Lumber(boolean flag) {
            super(flag);
        }

        @Override
        public LOTREntityNPC createTrader(World world, Random random) {
            return new LOTREntityBreeLumberman(world);
        }

        @Override
        public void decorateStall(World world, Random random) {
            this.setBlockAndMetadata(world, 0, 1, 1, Blocks.log, 0);
            this.setGrassToDirt(world, 0, 0, 1);
            this.setBlockAndMetadata(world, -1, 1, -1, LOTRMod.wood5, 4);
            this.setGrassToDirt(world, -1, 0, -1);
            this.placeWeaponRack(world, 1, 2, -2, 7, new ItemStack(Items.iron_axe));
        }

        @Override
        public void setupRandomBlocks(Random random) {
            super.setupRandomBlocks(random);
            this.wool1Block = Blocks.wool;
            this.wool1Meta = 12;
        }
    }

    public static class Florist
    extends LOTRWorldGenBreeMarketStall {
        public Florist(boolean flag) {
            super(flag);
        }

        @Override
        public LOTREntityNPC createTrader(World world, Random random) {
            return random.nextBoolean() ? new LOTREntityBreeHobbitFlorist(world) : new LOTREntityBreeFlorist(world);
        }

        @Override
        public void decorateStall(World world, Random random) {
            this.placeRandomFlowerPot(world, random, -1, 2, -2);
            this.placeRandomFlowerPot(world, random, 1, 2, -2);
            this.placeRandomFlowerPot(world, random, -2, 2, 0);
            this.placeRandomFlowerPot(world, random, 2, 2, 1);
            this.placeRandomFlowerPot(world, random, 0, 2, 2);
        }

        @Override
        public void setupRandomBlocks(Random random) {
            super.setupRandomBlocks(random);
            this.wool1Block = Blocks.wool;
            this.wool1Meta = 10;
        }
    }

    public static class Farmer
    extends LOTRWorldGenBreeMarketStall {
        public Farmer(boolean flag) {
            super(flag);
        }

        @Override
        public LOTREntityNPC createTrader(World world, Random random) {
            return new LOTREntityBreeFarmer(world);
        }

        @Override
        public void decorateStall(World world, Random random) {
            this.placePlate_item(world, random, -1, 2, -2, LOTRMod.plateBlock, this.getRandomFarmerItem(random), true);
            this.placePlate_item(world, random, 0, 2, -2, LOTRMod.woodPlateBlock, this.getRandomFarmerItem(random), true);
            this.placePlate_item(world, random, -2, 2, -1, LOTRMod.woodPlateBlock, this.getRandomFarmerItem(random), true);
            this.placePlate_item(world, random, -2, 2, 1, LOTRMod.ceramicPlateBlock, this.getRandomFarmerItem(random), true);
            this.placePlate_item(world, random, 2, 2, 0, LOTRMod.ceramicPlateBlock, this.getRandomFarmerItem(random), true);
            this.setBlockAndMetadata(world, -1, 1, -1, Blocks.pumpkin, 3);
            this.setGrassToDirt(world, -1, 0, -1);
        }

        public ItemStack getRandomFarmerItem(Random random) {
            ItemStack[] foods = new ItemStack[]{new ItemStack(Items.carrot), new ItemStack(Items.potato), new ItemStack(LOTRMod.lettuce), new ItemStack(LOTRMod.turnip), new ItemStack(LOTRMod.leek), new ItemStack(Items.apple), new ItemStack(LOTRMod.appleGreen), new ItemStack(LOTRMod.pear), new ItemStack(LOTRMod.plum)};
            ItemStack ret = foods[random.nextInt(foods.length)].copy();
            ret.stackSize = 1 + random.nextInt(3);
            return ret;
        }

        @Override
        public void setupRandomBlocks(Random random) {
            super.setupRandomBlocks(random);
            this.wool1Block = Blocks.wool;
            this.wool1Meta = 13;
        }
    }

    public static class Butcher
    extends LOTRWorldGenBreeMarketStall {
        public Butcher(boolean flag) {
            super(flag);
        }

        @Override
        public LOTREntityNPC createTrader(World world, Random random) {
            return random.nextBoolean() ? new LOTREntityBreeHobbitButcher(world) : new LOTREntityBreeButcher(world);
        }

        @Override
        public void decorateStall(World world, Random random) {
            this.placePlate_item(world, random, -1, 2, -2, LOTRMod.plateBlock, this.getRandomButcherItem(random), true);
            this.placePlate_item(world, random, 1, 2, -2, LOTRMod.ceramicPlateBlock, this.getRandomButcherItem(random), true);
            this.placePlate_item(world, random, -2, 2, 0, LOTRMod.woodPlateBlock, this.getRandomButcherItem(random), true);
            this.placePlate_item(world, random, 2, 2, 1, LOTRMod.plateBlock, this.getRandomButcherItem(random), true);
            this.placePlate_item(world, random, 0, 2, 2, LOTRMod.ceramicPlateBlock, this.getRandomButcherItem(random), true);
        }

        public ItemStack getRandomButcherItem(Random random) {
            ItemStack[] foods = new ItemStack[]{new ItemStack(Items.beef), new ItemStack(Items.porkchop), new ItemStack(LOTRMod.gammon), new ItemStack(Items.chicken), new ItemStack(LOTRMod.muttonRaw), new ItemStack(LOTRMod.rabbitRaw), new ItemStack(LOTRMod.deerRaw)};
            ItemStack ret = foods[random.nextInt(foods.length)].copy();
            ret.stackSize = 1 + random.nextInt(3);
            return ret;
        }

        @Override
        public void setupRandomBlocks(Random random) {
            super.setupRandomBlocks(random);
            this.wool1Block = Blocks.wool;
            this.wool1Meta = 14;
        }
    }

    public static class Brewer
    extends LOTRWorldGenBreeMarketStall {
        public Brewer(boolean flag) {
            super(flag);
        }

        @Override
        public LOTREntityNPC createTrader(World world, Random random) {
            return random.nextBoolean() ? new LOTREntityBreeHobbitBrewer(world) : new LOTREntityBreeBrewer(world);
        }

        @Override
        public void decorateStall(World world, Random random) {
            this.placeMug(world, random, -1, 2, -2, 0, LOTRFoods.BREE_DRINK);
            this.placeMug(world, random, 1, 2, -2, 0, LOTRFoods.BREE_DRINK);
            this.placeMug(world, random, 1, 2, 2, 2, LOTRFoods.BREE_DRINK);
            this.setBlockAndMetadata(world, -1, 1, -1, LOTRMod.barrel, 3);
            this.setBlockAndMetadata(world, -2, 2, 1, LOTRMod.barrel, 2);
            this.setBlockAndMetadata(world, 2, 2, 1, LOTRMod.barrel, 5);
        }

        @Override
        public void setupRandomBlocks(Random random) {
            super.setupRandomBlocks(random);
            this.wool1Block = Blocks.wool;
            this.wool1Meta = 1;
        }
    }

    public static class Baker
    extends LOTRWorldGenBreeMarketStall {
        public Baker(boolean flag) {
            super(flag);
        }

        @Override
        public LOTREntityNPC createTrader(World world, Random random) {
            return random.nextBoolean() ? new LOTREntityBreeHobbitBaker(world) : new LOTREntityBreeBaker(world);
        }

        @Override
        public void decorateStall(World world, Random random) {
            this.placePlate_item(world, random, -1, 2, -2, LOTRMod.woodPlateBlock, this.getRandomBakeryItem(random), true);
            this.placePlate_item(world, random, 1, 2, 2, LOTRMod.ceramicPlateBlock, this.getRandomBakeryItem(random), true);
            this.placePlate_item(world, random, -2, 2, 1, LOTRMod.plateBlock, this.getRandomBakeryItem(random), true);
            this.setBlockAndMetadata(world, 1, 2, -2, LOTRWorldGenBreeStructure.getRandomPieBlock(random), 0);
            this.setBlockAndMetadata(world, -2, 2, -1, LOTRWorldGenBreeStructure.getRandomPieBlock(random), 0);
            this.setBlockAndMetadata(world, 2, 2, 1, LOTRWorldGenBreeStructure.getRandomPieBlock(random), 0);
        }

        public ItemStack getRandomBakeryItem(Random random) {
            ItemStack[] foods = new ItemStack[]{new ItemStack(Items.bread), new ItemStack(LOTRMod.cornBread), new ItemStack(LOTRMod.hobbitPancake), new ItemStack(LOTRMod.hobbitPancakeMapleSyrup)};
            ItemStack ret = foods[random.nextInt(foods.length)].copy();
            ret.stackSize = 1 + random.nextInt(3);
            return ret;
        }

        @Override
        public void setupRandomBlocks(Random random) {
            super.setupRandomBlocks(random);
            this.wool1Block = Blocks.wool;
            this.wool1Meta = 4;
        }
    }

}

