/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 */
package lotr.common.world.mapgen.moriadwarvenmine;

import cubex2.mods.multipagechest.BlockMultiPageChest;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.mapgen.moriadwarvenmine.LOTRStructureMoriaDwarvenMinePieces;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class LOTRComponentMoriaDwarvenMineCorridor
extends StructureComponent {
    private int sectionCount;
    private boolean ruined;

    public LOTRComponentMoriaDwarvenMineCorridor() {
    }

    public LOTRComponentMoriaDwarvenMineCorridor(int i, Random random, StructureBoundingBox structureBoundingBox, int j, boolean r) {
        super(i);
        this.coordBaseMode = j;
        this.boundingBox = structureBoundingBox;
        this.sectionCount = this.coordBaseMode != 2 && this.coordBaseMode != 0 ? this.boundingBox.getXSize() / 4 : this.boundingBox.getZSize() / 4;
        this.ruined = r;
    }

    protected void func_143012_a(NBTTagCompound nbt) {
        nbt.setInteger("Sections", this.sectionCount);
        nbt.setBoolean("Ruined", this.ruined);
    }

    protected void func_143011_b(NBTTagCompound nbt) {
        this.sectionCount = nbt.getInteger("Sections");
        this.ruined = nbt.getBoolean("Ruined");
    }

    public static StructureBoundingBox findValidPlacement(List list, Random random, int i, int j, int k, int l) {
        int i1;
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 3, k);
        for (i1 = random.nextInt(3) + 2; i1 > 0; --i1) {
            int j1 = i1 * 4;
            switch (l) {
                case 0: {
                    structureboundingbox.maxX = i + 2;
                    structureboundingbox.maxZ = k + (j1 - 1);
                    break;
                }
                case 1: {
                    structureboundingbox.minX = i - (j1 - 1);
                    structureboundingbox.maxZ = k + 2;
                    break;
                }
                case 2: {
                    structureboundingbox.maxX = i + 2;
                    structureboundingbox.minZ = k - (j1 - 1);
                    break;
                }
                case 3: {
                    structureboundingbox.maxX = i + (j1 - 1);
                    structureboundingbox.maxZ = k + 2;
                }
            }
            if (StructureComponent.findIntersecting((List)list, (StructureBoundingBox)structureboundingbox) == null) break;
        }
        return i1 > 0 ? structureboundingbox : null;
    }

    public void buildComponent(StructureComponent component, List list, Random random) {
        block24: {
            int i = this.getComponentType();
            int j = random.nextInt(4);
            switch (this.coordBaseMode) {
                case 0: {
                    if (j <= 1) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.maxZ + 1, this.coordBaseMode, i, this.ruined);
                        break;
                    }
                    if (j == 2) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.maxZ - 3, 1, i, this.ruined);
                        break;
                    }
                    LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.maxZ - 3, 3, i, this.ruined);
                    break;
                }
                case 1: {
                    if (j <= 1) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, i, this.ruined);
                        break;
                    }
                    if (j == 2) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.minZ - 1, 2, i, this.ruined);
                        break;
                    }
                    LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.maxZ + 1, 0, i, this.ruined);
                    break;
                }
                case 2: {
                    if (j <= 1) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.minZ - 1, this.coordBaseMode, i, this.ruined);
                        break;
                    }
                    if (j == 2) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.minZ, 1, i, this.ruined);
                        break;
                    }
                    LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.minZ, 3, i, this.ruined);
                    break;
                }
                case 3: {
                    if (j <= 1) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, i, this.ruined);
                        break;
                    }
                    if (j == 2) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.minZ - 1, 2, i, this.ruined);
                        break;
                    }
                    LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + random.nextInt(3), this.boundingBox.maxZ + 1, 0, i, this.ruined);
                }
            }
            if (i >= 12) break block24;
            if (this.coordBaseMode != 2 && this.coordBaseMode != 0) {
                int k = this.boundingBox.minX + 3;
                while (k + 3 <= this.boundingBox.maxX) {
                    int l = random.nextInt(5);
                    if (l == 0) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, k, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, i + 1, this.ruined);
                    } else if (l == 1) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, k, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, i + 1, this.ruined);
                    }
                    k += 4;
                }
            } else {
                int k = this.boundingBox.minZ + 3;
                while (k + 3 <= this.boundingBox.maxZ) {
                    int l = random.nextInt(5);
                    if (l == 0) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY, k, 1, i + 1, this.ruined);
                    } else if (l == 1) {
                        LOTRStructureMoriaDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY, k, 3, i + 1, this.ruined);
                    }
                    k += 4;
                }
            }
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
        }
        int length = this.sectionCount * 4 - 1;
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 2, 2, length, Blocks.air, Blocks.air, false);
        for (int l = 0; l < this.sectionCount; ++l) {
            int k = 2 + l * 4;
            for (int i : new int[]{0, 2}) {
                int wallHeight = this.ruined ? random.nextInt(3) : 2;
                for (int j = 0; j <= wallHeight; ++j) {
                    this.placeBlockAtCurrentPosition(world, LOTRMod.wall, 7, i, j, k, structureBoundingBox);
                }
            }
            this.fillWithBlocks(world, structureBoundingBox, -1, 0, k, -1, 2, k, LOTRMod.pillar, Blocks.air, false);
            this.fillWithBlocks(world, structureBoundingBox, 3, 0, k, 3, 2, k, LOTRMod.pillar, Blocks.air, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, -1, k - 2, 1, -1, k + 2, LOTRMod.pillar, Blocks.air, false);
            if (this.getBlockAtCurrentPosition(world, 1, -1, k - 3, structureBoundingBox) != Blocks.air) {
                this.placeBlockAtCurrentPosition(world, LOTRMod.pillar, 0, 1, -1, k - 3, structureBoundingBox);
            }
            if (this.getBlockAtCurrentPosition(world, 1, -1, k + 3, structureBoundingBox) != Blocks.air) {
                this.placeBlockAtCurrentPosition(world, LOTRMod.pillar, 0, 1, -1, k + 3, structureBoundingBox);
            }
            if (!this.ruined) {
                this.placeBlockAtCurrentPosition(world, LOTRMod.brick3, 12, 1, -1, k, structureBoundingBox);
                if (random.nextInt(80) == 0) {
                    this.placeBlockAtCurrentPosition(world, Blocks.crafting_table, 0, 2, 0, k - 1, structureBoundingBox);
                }
                if (random.nextInt(80) == 0) {
                    this.placeBlockAtCurrentPosition(world, Blocks.crafting_table, 0, 0, 0, k + 1, structureBoundingBox);
                }
            }
            if (random.nextInt(120) == 0) {
                this.generateStructureChestContentsWithStoneChest(world, structureBoundingBox, random, 2, 0, k - 1, LOTRChestContents.LOTRChestContents2.MORIA_DWARVEN_MINE_CORRIDOR.items, LOTRChestContents.getRandomItemAmount(LOTRChestContents.LOTRChestContents2.MORIA_DWARVEN_MINE_CORRIDOR, random));
            }
            if (random.nextInt(120) != 0) continue;
            this.generateStructureChestContentsWithStoneChest(world, structureBoundingBox, random, 0, 0, k + 1, LOTRChestContents.LOTRChestContents2.MORIA_DWARVEN_MINE_CORRIDOR.items, LOTRChestContents.getRandomItemAmount(LOTRChestContents.LOTRChestContents2.MORIA_DWARVEN_MINE_CORRIDOR, random));
        }
        for (int k = 0; k <= length; ++k) {
            for (int i = -1; i <= 3; ++i) {
                Block block = this.getBlockAtCurrentPosition(world, i, -1, k, structureBoundingBox);
                if (block.getMaterial().isReplaceable() || block.getMaterial() == Material.sand) {
                    this.placeBlockAtCurrentPosition(world, Blocks.stone, 0, i, -1, k, structureBoundingBox);
                }
                int j = 3;
                block = this.getBlockAtCurrentPosition(world, i, 3, k, structureBoundingBox);
                if (!block.getMaterial().isReplaceable() && block.getMaterial() != Material.sand) continue;
                this.placeBlockAtCurrentPosition(world, Blocks.stone, 0, i, j, k, structureBoundingBox);
            }
            for (int j = 0; j <= 2; ++j) {
                Block block = this.getBlockAtCurrentPosition(world, -1, j, k, structureBoundingBox);
                if (block.getMaterial().isReplaceable() || block.getMaterial() == Material.sand) {
                    this.placeBlockAtCurrentPosition(world, Blocks.stone, 0, -1, j, k, structureBoundingBox);
                }
                if (!(block = this.getBlockAtCurrentPosition(world, 3, j, k, structureBoundingBox)).getMaterial().isReplaceable() && block.getMaterial() != Material.sand) continue;
                this.placeBlockAtCurrentPosition(world, Blocks.stone, 0, 3, j, k, structureBoundingBox);
            }
        }
        return true;
    }

    protected boolean generateStructureChestContentsWithStoneChest(World world, StructureBoundingBox box, Random random, int x, int y, int z, WeightedRandomChestContent[] chestContents, int itemCount) {
        int j1;
        int k1;
        int i1 = this.getXWithOffset(x, z);
        if (box.isVecInside(i1, j1 = this.getYWithOffset(y), k1 = this.getZWithOffset(x, z)) && world.getBlock(i1, j1, k1) != LOTRMod.chestBlockMithril && world.getBlock(i1, j1, k1) != LOTRMod.chestStone) {
            if (random.nextBoolean()) {
                world.setBlock(i1, j1, k1, (Block)LOTRMod.chestBlockMithril, 0, 2);
            } else {
                world.setBlock(i1, j1, k1, LOTRMod.chestStone, 0, 2);
            }
            WeightedRandomChestContent.generateChestContents((Random)random, (WeightedRandomChestContent[])chestContents, (IInventory)((IInventory)world.getTileEntity(i1, j1, k1)), (int)itemCount);
            return true;
        }
        return false;
    }

    protected boolean generateStructureChestContentsWithStoneChest2(World world, StructureBoundingBox box, Random random, int x, int y, int z, WeightedRandomChestContent[] chestContents, int itemCount) {
        int j1;
        int k1;
        int i1 = this.getXWithOffset(x, z);
        if (box.isVecInside(i1, j1 = this.getYWithOffset(y), k1 = this.getZWithOffset(x, z)) && world.getBlock(i1, j1, k1) != LOTRMod.chestBlockMithril && world.getBlock(i1, j1, k1) != LOTRMod.chestStone) {
            if (random.nextBoolean()) {
                world.setBlock(i1, j1, k1, (Block)LOTRMod.chestBlockMithril, 0, 2);
            } else {
                world.setBlock(i1, j1, k1, LOTRMod.chestStone, 0, 2);
            }
            WeightedRandomChestContent.generateChestContents((Random)random, (WeightedRandomChestContent[])chestContents, (IInventory)((IInventory)world.getTileEntity(i1, j1, k1)), (int)itemCount);
            return true;
        }
        return false;
    }
}

