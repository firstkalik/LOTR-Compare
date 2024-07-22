/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package lotr.common;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public final class PlayerProvider
implements IExtendedEntityProperties {
    private final EntityPlayer player;
    private int goldTotal;
    private int bankGold;
    private int glory;
    public int libraryInvestment;
    public int burningVillages;
    public int reficulHoles;
    public int bindLight;
    public boolean badKid;
    public List<ItemStack> stacks;

    public PlayerProvider(EntityPlayer player) {
        this.player = player;
    }

    public void init(Entity entity, World world) {
    }

    public int getGlory() {
        return this.glory;
    }

    public void setGlory(int glory) {
        this.glory = glory;
    }

    public int getGoldTotal() {
        return this.goldTotal;
    }

    public int getBankGold() {
        return this.bankGold;
    }

    public void setGoldTotal(int count) {
        this.goldTotal = count;
    }

    public void setBankGold(int count) {
        this.bankGold = count;
    }

    public static PlayerProvider get(EntityPlayer player) {
        return (PlayerProvider)player.getExtendedProperties("tokPlayer");
    }

    public void saveNBTData(NBTTagCompound compound) {
    }

    public void loadNBTData(NBTTagCompound compound) {
    }
}

