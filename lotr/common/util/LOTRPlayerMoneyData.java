/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraftforge.common.DimensionManager
 */
package lotr.common.util;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import lotr.common.util.LOTRPacketMoneyChange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraftforge.common.DimensionManager;

public class LOTRPlayerMoneyData {
    public static File ROOT;
    public static File ROOT_PD;
    public static final Map<String, LOTRPlayerMoneyData> server_cache;
    private static LOTRPlayerMoneyData client_cache;
    private final UUID uuid;
    private final String name;
    public boolean enabled = true;
    public int money = 0;

    public static void setRoots() {
        ROOT = LOTRPlayerMoneyData.folder(DimensionManager.getCurrentSaveRootDirectory(), "LOTR");
        ROOT_PD = LOTRPlayerMoneyData.folder(ROOT, "moneydata");
    }

    private static File folder(File parent, String name) {
        File ret = new File(parent, name);
        if (!ret.exists()) {
            ret.mkdirs();
        }
        return ret;
    }

    public void updateBalance(EntityPlayerMP player) {
        this.sendUpdatedBalance(player);
    }

    public void sendUpdatedBalance(EntityPlayerMP player) {
        LOTRPacketMoneyChange packet = new LOTRPacketMoneyChange(this.money);
        packet.sendTo(player);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static LOTRPlayerMoneyData of(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            String _name = player.getCommandSenderName();
            return server_cache.computeIfAbsent(_name, name -> {
                LOTRPlayerMoneyData data = new LOTRPlayerMoneyData(player.getGameProfile().getId(), player.getCommandSenderName());
                data.readFromFile();
                return data;
            });
        }
        Class<LOTRPlayerMoneyData> _name = LOTRPlayerMoneyData.class;
        synchronized (LOTRPlayerMoneyData.class) {
            // ** MonitorExit[_name] (shouldn't be in output)
            return client_cache != null ? client_cache : (client_cache = new LOTRPlayerMoneyData(player.getGameProfile().getId(), player.getGameProfile().getName()));
        }
    }

    public void setMoney(int money, EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            this.money = money;
            LOTRPacketMoneyChange packet = new LOTRPacketMoneyChange(this.money);
            packet.sendTo((EntityPlayerMP)player);
        }
    }

    public static void saveAll() {
        server_cache.values().forEach(LOTRPlayerMoneyData::writeToFile);
    }

    public static void quit() {
        LOTRPlayerMoneyData.saveAll();
        server_cache.clear();
    }

    public File file() {
        return new File(ROOT_PD, this.uuid.toString() + ".dat");
    }

    public void readFromFile() {
        File file = this.file();
        if (file.exists()) {
            try {
                NBTTagCompound nbt = CompressedStreamTools.readCompressed((InputStream)new FileInputStream(file));
                this.readFromNBT(nbt);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.disable();
            this.enable();
            this.writeToFile();
        }
    }

    public void writeToFile() {
        File file = this.file();
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        try {
            CompressedStreamTools.writeCompressed((NBTTagCompound)nbt, (OutputStream)new FileOutputStream(file));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected LOTRPlayerMoneyData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public EntityPlayerMP player() {
        return MinecraftServer.getServer().getConfigurationManager().func_152612_a(this.name);
    }

    public final void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("money", this.money);
    }

    public final void readFromNBT(NBTTagCompound nbt) {
        this.money = nbt.getInteger("money");
    }

    static {
        LOTRPlayerMoneyData.setRoots();
        server_cache = Maps.newHashMap();
        client_cache = null;
    }
}

