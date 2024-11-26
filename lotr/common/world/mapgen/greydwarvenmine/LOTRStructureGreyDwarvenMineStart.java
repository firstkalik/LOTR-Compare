/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureComponent
 *  net.minecraft.world.gen.structure.StructureStart
 */
package lotr.common.world.mapgen.greydwarvenmine;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import lotr.common.world.mapgen.greydwarvenmine.LOTRComponentGreyDwarvenMineEntrance;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class LOTRStructureGreyDwarvenMineStart
extends StructureStart {
    public LOTRStructureGreyDwarvenMineStart() {
    }

    public LOTRStructureGreyDwarvenMineStart(World world, Random random, int i, int j, boolean r) {
        LOTRComponentGreyDwarvenMineEntrance startComponent = new LOTRComponentGreyDwarvenMineEntrance(world, 0, random, (i << 4) + 8, (j << 4) + 8, r);
        this.components.add(startComponent);
        startComponent.buildComponent(startComponent, this.components, random);
        this.updateBoundingBox();
    }
}

