/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package lotr.common.entity.item;

import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.item.LOTREntityRugBase;
import net.minecraft.entity.DataWatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LOTREntityBearRug
extends LOTREntityRugBase {
    public LOTREntityBearRug(World world) {
        super(world);
        this.setSize(1.8f, 0.3f);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)0);
    }

    public LOTREntityBear.BearType getRugType() {
        byte i = this.dataWatcher.getWatchableObjectByte(18);
        return LOTREntityBear.BearType.forID(i);
    }

    public void setRugType(LOTREntityBear.BearType t) {
        this.dataWatcher.updateObject(18, (Object)((byte)t.bearID));
    }

    @Override
    protected String getRugNoise() {
        return "lotr:bear.say";
    }

    @Override
    protected ItemStack getRugItem() {
        return new ItemStack(LOTRMod.bearRug, 1, this.getRugType().bearID);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setByte("RugType", (byte)this.getRugType().bearID);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setRugType(LOTREntityBear.BearType.forID(nbt.getByte("RugType")));
    }
}

