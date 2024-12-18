/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 */
package lotr.common.world.mapgen.winddwarvenmine;

import java.util.List;
import java.util.Random;
import lotr.common.world.mapgen.winddwarvenmine.LOTRStructureWindDwarvenMinePieces;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class LOTRComponentWindDwarvenMineStairs
extends StructureComponent {
    public LOTRComponentWindDwarvenMineStairs() {
    }

    public LOTRComponentWindDwarvenMineStairs(int i, Random random, StructureBoundingBox structureBoundingBox, int j, boolean r) {
        super(i);
        this.coordBaseMode = j;
        this.boundingBox = structureBoundingBox;
    }

    protected void func_143012_a(NBTTagCompound nbt) {
    }

    protected void func_143011_b(NBTTagCompound nbt) {
    }

    public static StructureBoundingBox findValidPlacement(List list, Random random, int i, int j, int k, int l) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j - 5, k, i, j + 2, k);
        switch (l) {
            case 0: {
                structureboundingbox.maxX = i + 2;
                structureboundingbox.maxZ = k + 8;
                break;
            }
            case 1: {
                structureboundingbox.minX = i - 8;
                structureboundingbox.maxZ = k + 2;
                break;
            }
            case 2: {
                structureboundingbox.maxX = i + 2;
                structureboundingbox.minZ = k - 8;
                break;
            }
            case 3: {
                structureboundingbox.maxX = i + 8;
                structureboundingbox.maxZ = k + 2;
            }
        }
        return StructureComponent.findIntersecting((List)list, (StructureBoundingBox)structureboundingbox) != null ? null : structureboundingbox;
    }

    public void buildComponent(StructureComponent component, List list, Random random) {
        int i = this.getComponentType();
        switch (this.coordBaseMode) {
            case 0: {
                LOTRStructureWindDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, i, false);
                break;
            }
            case 1: {
                LOTRStructureWindDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, 1, i, false);
                break;
            }
            case 2: {
                LOTRStructureWindDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, i, false);
                break;
            }
            case 3: {
                LOTRStructureWindDwarvenMinePieces.getNextComponent(component, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, 3, i, false);
            }
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
        }
        this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 2, 7, 1, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 7, 2, 2, 8, Blocks.air, Blocks.air, false);
        for (int i = 0; i < 5; ++i) {
            this.fillWithBlocks(world, structureBoundingBox, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, Blocks.air, Blocks.air, false);
        }
        return true;
    }
}

