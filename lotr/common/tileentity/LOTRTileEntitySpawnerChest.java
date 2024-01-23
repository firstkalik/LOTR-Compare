/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import lotr.common.entity.LOTREntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class LOTRTileEntitySpawnerChest
extends TileEntityChest {
    private String entityClassName = "";

    public void setMobID(Class entityClass) {
        this.entityClassName = LOTREntities.getStringFromClass(entityClass);
    }

    public Entity createMob() {
        return EntityList.createEntityByName((String)this.entityClassName, (World)this.worldObj);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.entityClassName = nbt.getString("MobID");
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setString("MobID", this.entityClassName);
    }
}

