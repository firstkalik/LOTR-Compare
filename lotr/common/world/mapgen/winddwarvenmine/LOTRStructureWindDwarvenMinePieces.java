/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 */
package lotr.common.world.mapgen.winddwarvenmine;

import java.util.List;
import java.util.Random;
import lotr.common.world.mapgen.winddwarvenmine.LOTRComponentWindDwarvenMineCorridor;
import lotr.common.world.mapgen.winddwarvenmine.LOTRComponentWindDwarvenMineCrossing;
import lotr.common.world.mapgen.winddwarvenmine.LOTRComponentWindDwarvenMineStairs;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class LOTRStructureWindDwarvenMinePieces {
    private static StructureComponent getRandomComponent(List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        int l = random.nextInt(100);
        if (l >= 80) {
            StructureBoundingBox structureboundingbox = LOTRComponentWindDwarvenMineCrossing.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentWindDwarvenMineCrossing(iteration, random, structureboundingbox, direction, ruined);
            }
        } else if (l >= 70) {
            StructureBoundingBox structureboundingbox = LOTRComponentWindDwarvenMineStairs.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentWindDwarvenMineStairs(iteration, random, structureboundingbox, direction, ruined);
            }
        } else {
            StructureBoundingBox structureboundingbox = LOTRComponentWindDwarvenMineCorridor.findValidPlacement(list, random, i, j, k, direction);
            if (structureboundingbox != null) {
                return new LOTRComponentWindDwarvenMineCorridor(iteration, random, structureboundingbox, direction, ruined);
            }
        }
        return null;
    }

    private static StructureComponent getNextMineComponent(StructureComponent component, List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        if (iteration > 12) {
            return null;
        }
        if (Math.abs(i - component.getBoundingBox().minX) <= 80 && Math.abs(k - component.getBoundingBox().minZ) <= 80) {
            StructureComponent structurecomponent1 = LOTRStructureWindDwarvenMinePieces.getRandomComponent(list, random, i, j, k, direction, iteration + 1, ruined);
            if (structurecomponent1 != null) {
                list.add(structurecomponent1);
                structurecomponent1.buildComponent(component, list, random);
            }
            return structurecomponent1;
        }
        return null;
    }

    public static StructureComponent getNextComponent(StructureComponent component, List list, Random random, int i, int j, int k, int direction, int iteration, boolean ruined) {
        return LOTRStructureWindDwarvenMinePieces.getNextMineComponent(component, list, random, i, j, k, direction, iteration, ruined);
    }
}

