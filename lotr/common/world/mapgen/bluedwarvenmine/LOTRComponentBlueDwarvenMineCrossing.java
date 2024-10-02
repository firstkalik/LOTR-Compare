/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 */
package lotr.common.world.mapgen.bluedwarvenmine;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRStructureBlueDwarvenMinePieces;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class LOTRComponentBlueDwarvenMineCrossing
extends StructureComponent {
    private int corridorDirection;
    private boolean isMultipleFloors;

    public LOTRComponentBlueDwarvenMineCrossing() {
    }

    public LOTRComponentBlueDwarvenMineCrossing(int i, Random random, StructureBoundingBox structureBoundingBox, int j) {
        super(i);
        this.corridorDirection = j;
        this.boundingBox = structureBoundingBox;
        this.isMultipleFloors = this.boundingBox.getYSize() > 3;
    }

    protected void func_143012_a(NBTTagCompound nbt) {
        nbt.setInteger("Direction", this.corridorDirection);
        nbt.setBoolean("Multiple", this.isMultipleFloors);
    }

    protected void func_143011_b(NBTTagCompound nbt) {
        this.corridorDirection = nbt.getInteger("Direction");
        this.isMultipleFloors = nbt.getBoolean("Multiple");
    }

    public static StructureBoundingBox findValidPlacement(List list, Random random, int i, int j, int k, int l) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);
        if (random.nextInt(4) == 0) {
            structureboundingbox.maxY += 4;
        }
        switch (l) {
            case 0: {
                structureboundingbox.minX = i - 1;
                structureboundingbox.maxX = i + 3;
                structureboundingbox.maxZ = k + 4;
                break;
            }
            case 1: {
                structureboundingbox.minX = i - 4;
                structureboundingbox.minZ = k - 1;
                structureboundingbox.maxZ = k + 3;
                break;
            }
            case 2: {
                structureboundingbox.minX = i - 1;
                structureboundingbox.maxX = i + 3;
                structureboundingbox.minZ = k - 4;
                break;
            }
            case 3: {
                structureboundingbox.maxX = i + 4;
                structureboundingbox.minZ = k - 1;
                structureboundingbox.maxZ = k + 3;
            }
        }
        return StructureComponent.findIntersecting((List)list, (StructureBoundingBox)structureboundingbox) != null ? null : structureboundingbox;
    }

    public void buildComponent(StructureComponent component, List list, Random random) {
        int i = this.getComponentType();
        switch (this.corridorDirection) {
            case 0: {
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, i, false);
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 1, i, false);
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 3, i, false);
                break;
            }
            case 1: {
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, i, false);
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, i, false);
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 1, i, false);
                break;
            }
            case 2: {
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, i, false);
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 1, i, false);
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 3, i, false);
                break;
            }
            case 3: {
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, i, false);
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, i, false);
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, 3, i, false);
            }
        }
        if (this.isMultipleFloors) {
            if (random.nextBoolean()) {
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, 2, i, false);
            }
            if (random.nextBoolean()) {
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, 1, i, false);
            }
            if (random.nextBoolean()) {
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, 3, i, false);
            }
            if (random.nextBoolean()) {
                LOTRStructureBlueDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, 0, i, false);
            }
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
        }
        this.fillWithBlocks(world, structureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, structureBoundingBox, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, structureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.minX + 1, this.boundingBox.maxY, this.boundingBox.minZ + 1, LOTRMod.pillar, Blocks.air, false);
        this.fillWithBlocks(world, structureBoundingBox, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.minX + 1, this.boundingBox.maxY, this.boundingBox.maxZ - 1, LOTRMod.pillar, Blocks.air, false);
        this.fillWithBlocks(world, structureBoundingBox, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.minZ + 1, LOTRMod.pillar, Blocks.air, false);
        this.fillWithBlocks(world, structureBoundingBox, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ - 1, LOTRMod.pillar, Blocks.air, false);
        for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i) {
            for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j) {
                Block block = this.getBlockAtCurrentPosition(world, i, this.boundingBox.minY - 1, j, structureBoundingBox);
                if (block.getMaterial().isReplaceable() || block.getMaterial() == Material.sand) {
                    this.placeBlockAtCurrentPosition(world, Blocks.stone, 0, i, this.boundingBox.minY - 1, j, structureBoundingBox);
                }
                if (!(block = this.getBlockAtCurrentPosition(world, i, this.boundingBox.maxY + 1, j, structureBoundingBox)).getMaterial().isReplaceable() && block.getMaterial() != Material.sand) continue;
                this.placeBlockAtCurrentPosition(world, Blocks.stone, 0, i, this.boundingBox.maxY + 1, j, structureBoundingBox);
            }
        }
        this.fillWithBlocks(world, structureBoundingBox, this.boundingBox.minX + 2, this.boundingBox.minY - 1, this.boundingBox.minZ - 1, this.boundingBox.minX + 2, this.boundingBox.minY - 1, this.boundingBox.maxZ + 1, LOTRMod.pillar, Blocks.air, false);
        this.fillWithBlocks(world, structureBoundingBox, this.boundingBox.minX - 1, this.boundingBox.minY - 1, this.boundingBox.minZ + 2, this.boundingBox.maxX + 1, this.boundingBox.minY - 1, this.boundingBox.minZ + 2, LOTRMod.pillar, Blocks.air, false);
        return true;
    }
}

