/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 */
package lotr.common.world.mapgen.greydwarvenmine;

import java.util.List;
import java.util.Random;
import lotr.common.world.mapgen.greydwarvenmine.LOTRComponentGreyDwarvenMineCorridor;
import lotr.common.world.mapgen.greydwarvenmine.LOTRComponentGreyDwarvenMineCrossing;
import lotr.common.world.mapgen.greydwarvenmine.LOTRComponentGreyDwarvenMineStairs;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class LOTRStructureGreyDwarvenMinePieces {
    private static StructureComponent getRandomComponent(List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        int l = random.nextInt(100);
        if (l >= 80) {
            StructureBoundingBox structureboundingbox = LOTRComponentGreyDwarvenMineCrossing.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentGreyDwarvenMineCrossing(iteration, random, structureboundingbox, direction, ruined);
            }
        } else if (l >= 70) {
            StructureBoundingBox structureboundingbox = LOTRComponentGreyDwarvenMineStairs.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentGreyDwarvenMineStairs(iteration, random, structureboundingbox, direction, ruined);
            }
        } else {
            StructureBoundingBox structureboundingbox = LOTRComponentGreyDwarvenMineCorridor.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentGreyDwarvenMineCorridor(iteration, random, structureboundingbox, direction, ruined);
            }
        }
        return null;
    }

    private static StructureComponent getNextMineComponent(StructureComponent component, List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        if (iteration > 12) {
            return null;
        }
        if (Math.abs(i - component.getBoundingBox().minX) <= 80 && Math.abs(k - component.getBoundingBox().minZ) <= 80) {
            StructureComponent structurecomponent1 = LOTRStructureGreyDwarvenMinePieces.getRandomComponent(list, random, i, j, k, direction, iteration + 1, ruined);
            if (structurecomponent1 != null) {
                list.add(structurecomponent1);
                structurecomponent1.buildComponent(component, list, random);
            }
            return structurecomponent1;
        }
        return null;
    }

    public static StructureComponent getNextComponent(StructureComponent component, List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        return LOTRStructureGreyDwarvenMinePieces.getNextMineComponent(component, list, random, i, j, k, direction, iteration, ruined);
    }
}

