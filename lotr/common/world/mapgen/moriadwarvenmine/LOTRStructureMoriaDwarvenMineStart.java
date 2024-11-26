/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureComponent
 *  net.minecraft.world.gen.structure.StructureStart
 */
package lotr.common.world.mapgen.moriadwarvenmine;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import lotr.common.world.mapgen.moriadwarvenmine.LOTRComponentMoriaDwarvenMineEntrance;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class LOTRStructureMoriaDwarvenMineStart
extends StructureStart {
    public LOTRStructureMoriaDwarvenMineStart() {
    }

    public LOTRStructureMoriaDwarvenMineStart(World world, Random random, int i, int j, boolean r) {
        LOTRComponentMoriaDwarvenMineEntrance startComponent = new LOTRComponentMoriaDwarvenMineEntrance(world, 0, random, (i << 4) + 8, (j << 4) + 8, r);
        this.components.add(startComponent);
        startComponent.buildComponent(startComponent, this.components, random);
        this.updateBoundingBox();
    }
}

