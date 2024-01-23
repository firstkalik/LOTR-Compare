/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.WorldServer
 */
package lotr.common;

import lotr.common.LOTRPlayerData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;

public class LOTRPlayerQuestData {
    private LOTRPlayerData playerData;
    private boolean givenFirstPouches = false;

    public LOTRPlayerQuestData(LOTRPlayerData pd) {
        this.playerData = pd;
    }

    public void save(NBTTagCompound questData) {
        questData.setBoolean("Pouches", this.givenFirstPouches);
    }

    public void load(NBTTagCompound questData) {
        this.givenFirstPouches = questData.getBoolean("Pouches");
    }

    public void onUpdate(EntityPlayerMP entityplayer, WorldServer world) {
    }

    private void markDirty() {
        this.playerData.markDirty();
    }

    public boolean getGivenFirstPouches() {
        return this.givenFirstPouches;
    }

    public void setGivenFirstPouches(boolean flag) {
        this.givenFirstPouches = flag;
        this.markDirty();
    }
}

