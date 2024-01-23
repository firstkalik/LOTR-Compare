/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package lotr.common.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LOTREntityFallingFireJar
extends EntityFallingBlock
implements IEntityAdditionalSpawnData {
    public LOTREntityFallingFireJar(World world) {
        super(world);
    }

    public LOTREntityFallingFireJar(World world, double d, double d1, double d2, Block block) {
        super(world, d, d1, d2, block);
    }

    public LOTREntityFallingFireJar(World world, double d, double d1, double d2, Block block, int meta) {
        super(world, d, d1, d2, block, meta);
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeDouble(this.prevPosX);
        data.writeDouble(this.prevPosY);
        data.writeDouble(this.prevPosZ);
        data.writeInt(Block.getIdFromBlock((Block)this.func_145805_f()));
        data.writeByte(this.field_145814_a);
    }

    public void readSpawnData(ByteBuf data) {
        double x = data.readDouble();
        double y = data.readDouble();
        double z = data.readDouble();
        Block block = Block.getBlockById((int)data.readInt());
        byte meta = data.readByte();
        EntityFallingBlock proxy = new EntityFallingBlock(this.worldObj, x, y, z, block, (int)meta);
        NBTTagCompound nbt = new NBTTagCompound();
        proxy.writeToNBT(nbt);
        this.readFromNBT(nbt);
    }
}

