/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 */
package lotr.common.world.mapgen.winddwarvenmine;

import java.util.List;
import java.util.Random;
import lotr.common.world.mapgen.winddwarvenmine.LOTRComponentWindDwarvenMineCorridor;
import lotr.common.world.mapgen.winddwarvenmine.LOTRWorldGenWindMountainsMineEntrance;
import lotr.common.world.structure2.LOTRWorldGenDwarvenMineEntranceRuined3;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class LOTRComponenWindDwarvenMineEntrance
extends StructureComponent {
    private int posX;
    private int posY = -1;
    private int posZ;
    private static LOTRWorldGenWindMountainsMineEntrance entranceBlueGen = new LOTRWorldGenWindMountainsMineEntrance(false);
    private static LOTRWorldGenDwarvenMineEntranceRuined3 entranceRuinedGen = new LOTRWorldGenDwarvenMineEntranceRuined3(false);
    private int direction;

    public LOTRComponenWindDwarvenMineEntrance() {
    }

    public LOTRComponenWindDwarvenMineEntrance(World world, int l, Random random, int i, int k, boolean r) {
        super(l);
        this.boundingBox = new StructureBoundingBox(i - 4, 40, k - 4, i + 4, 256, k + 4);
        this.posX = i;
        this.posZ = k;
    }

    protected void func_143012_a(NBTTagCompound nbt) {
        nbt.setInteger("EntranceX", this.posX);
        nbt.setInteger("EntranceY", this.posY);
        nbt.setInteger("EntranceZ", this.posZ);
        nbt.setInteger("Direction", this.direction);
    }

    protected void func_143011_b(NBTTagCompound nbt) {
        this.posX = nbt.getInteger("EntranceX");
        this.posY = nbt.getInteger("EntranceY");
        this.posZ = nbt.getInteger("EntranceZ");
        this.direction = nbt.getInteger("Direction");
    }

    public void buildComponent(StructureComponent component, List list, Random random) {
        StructureBoundingBox structureBoundingBox = null;
        this.direction = random.nextInt(4);
        switch (this.direction) {
            case 0: {
                structureBoundingBox = new StructureBoundingBox(this.posX - 1, this.boundingBox.minY + 1, this.posZ + 4, this.posX + 1, this.boundingBox.minY + 4, this.posZ + 15);
                break;
            }
            case 1: {
                structureBoundingBox = new StructureBoundingBox(this.posX - 15, this.boundingBox.minY + 1, this.posZ - 1, this.posX - 4, this.boundingBox.minY + 4, this.posZ + 1);
                break;
            }
            case 2: {
                structureBoundingBox = new StructureBoundingBox(this.posX - 1, this.boundingBox.minY + 1, this.posZ - 15, this.posX + 1, this.boundingBox.minY + 4, this.posZ - 4);
                break;
            }
            case 3: {
                structureBoundingBox = new StructureBoundingBox(this.posX + 4, this.boundingBox.minY + 1, this.posZ - 1, this.posX + 15, this.boundingBox.minY + 4, this.posZ + 1);
            }
        }
        LOTRComponentWindDwarvenMineCorridor corridor = new LOTRComponentWindDwarvenMineCorridor(0, random, structureBoundingBox, this.direction, false);
        list.add(corridor);
        corridor.buildComponent(component, list, random);
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        if (this.posY == -1) {
            this.posY = world.getTopSolidOrLiquidBlock(this.posX, this.posZ);
        }
        if (world.getBlock(this.posX, this.posY - 1, this.posZ) != Blocks.grass) {
            return false;
        }
        boolean useRuinedGen = random.nextBoolean();
        if (useRuinedGen) {
            return entranceRuinedGen.generateWithSetRotation(world, random, this.posX, this.posY, this.posZ, this.direction);
        }
        LOTRComponenWindDwarvenMineEntrance.entranceBlueGen.isRuined = false;
        return entranceBlueGen.generateWithSetRotation(world, random, this.posX, this.posY, this.posZ, this.direction);
    }

    static {
        LOTRComponenWindDwarvenMineEntrance.entranceBlueGen.restrictions = false;
        LOTRComponenWindDwarvenMineEntrance.entranceRuinedGen.restrictions = false;
    }
}

