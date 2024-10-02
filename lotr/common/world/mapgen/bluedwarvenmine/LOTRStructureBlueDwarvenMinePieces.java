/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 */
package lotr.common.world.mapgen.bluedwarvenmine;

import java.util.List;
import java.util.Random;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRComponentBlueDwarvenMineCorridor;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRComponentBlueDwarvenMineCrossing;
import lotr.common.world.mapgen.bluedwarvenmine.LOTRComponentBlueDwarvenMineStairs;
import lotr.common.world.mapgen.dwarvenmine.LOTRComponentDwarvenMineCorridor;
import lotr.common.world.mapgen.dwarvenmine.LOTRComponentDwarvenMineCrossing;
import lotr.common.world.mapgen.dwarvenmine.LOTRComponentDwarvenMineStairs;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class LOTRStructureBlueDwarvenMinePieces {
    private static StructureComponent getRandomComponent(List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        int l = random.nextInt(100);
        if (l >= 80) {
            StructureBoundingBox structureboundingbox = LOTRComponentBlueDwarvenMineCrossing.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentDwarvenMineCrossing(iteration, random, structureboundingbox, direction, ruined);
            }
        } else if (l >= 70) {
            StructureBoundingBox structureboundingbox = LOTRComponentBlueDwarvenMineStairs.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentDwarvenMineStairs(iteration, random, structureboundingbox, direction, ruined);
            }
        } else {
            StructureBoundingBox structureboundingbox = LOTRComponentBlueDwarvenMineCorridor.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentDwarvenMineCorridor(iteration, random, structureboundingbox, direction, ruined);
            }
        }
        return null;
    }

    private static StructureComponent getNextMineComponent(StructureComponent component, List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        if (iteration > 12) {
            return null;
        }
        if (Math.abs(i - component.getBoundingBox().minX) <= 80 && Math.abs(k - component.getBoundingBox().minZ) <= 80) {
            StructureComponent structurecomponent1 = LOTRStructureBlueDwarvenMinePieces.getRandomComponent(list, random, i, j, k, direction, iteration + 1, ruined);
            if (structurecomponent1 != null) {
                list.add(structurecomponent1);
                structurecomponent1.buildComponent(component, list, random);
            }
            return structurecomponent1;
        }
        return null;
    }

    public static StructureComponent getNextComponent(StructureComponent component, List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        return LOTRStructureBlueDwarvenMinePieces.getNextMineComponent(component, list, random, i, j, k, direction, iteration, ruined);
    }
}

