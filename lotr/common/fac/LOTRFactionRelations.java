/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.StatCollector
 */
package lotr.common.fac;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import lotr.common.network.LOTRPacketFactionRelations;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.StatCollector;

public class LOTRFactionRelations {
    private static Map<FactionPair, Relation> defaultMap = new HashMap<FactionPair, Relation>();
    private static Map<FactionPair, Relation> overrideMap = new HashMap<FactionPair, Relation>();
    public static boolean needsLoad = true;
    private static boolean needsSave = false;

    private static Relation getFromDefaultMap(FactionPair key) {
        if (defaultMap.containsKey(key)) {
            return defaultMap.get(key);
        }
        return Relation.NEUTRAL;
    }

    public static Relation getRelations(LOTRFaction f1, LOTRFaction f2) {
        if (f1 == LOTRFaction.UNALIGNED || f2 == LOTRFaction.UNALIGNED) {
            return Relation.NEUTRAL;
        }
        if (f1 == LOTRFaction.HOSTILE || f2 == LOTRFaction.HOSTILE) {
            return Relation.MORTAL_ENEMY;
        }
        if (f1 == f2) {
            return Relation.ALLY;
        }
        FactionPair key = new FactionPair(f1, f2);
        if (overrideMap.containsKey(key)) {
            return overrideMap.get(key);
        }
        return LOTRFactionRelations.getFromDefaultMap(key);
    }

    public static void setDefaultRelations(LOTRFaction f1, LOTRFaction f2, Relation relation) {
        LOTRFactionRelations.setRelations(f1, f2, relation, true);
    }

    public static void overrideRelations(LOTRFaction f1, LOTRFaction f2, Relation relation) {
        LOTRFactionRelations.setRelations(f1, f2, relation, false);
    }

    private static void setRelations(LOTRFaction f1, LOTRFaction f2, Relation relation, boolean isDefault) {
        if (f1 == LOTRFaction.UNALIGNED || f2 == LOTRFaction.UNALIGNED) {
            throw new IllegalArgumentException("Cannot alter UNALIGNED!");
        }
        if (f1 == LOTRFaction.HOSTILE || f2 == LOTRFaction.HOSTILE) {
            throw new IllegalArgumentException("Cannot alter HOSTILE!");
        }
        if (f1 == f2) {
            throw new IllegalArgumentException("Cannot alter a faction's relations with itself!");
        }
        FactionPair key = new FactionPair(f1, f2);
        if (isDefault) {
            if (relation == Relation.NEUTRAL) {
                defaultMap.remove(key);
            } else {
                defaultMap.put(key, relation);
            }
        } else {
            Relation defaultRelation = LOTRFactionRelations.getFromDefaultMap(key);
            if (relation == defaultRelation) {
                overrideMap.remove(key);
                LOTRFactionRelations.markDirty();
            } else {
                overrideMap.put(key, relation);
                LOTRFactionRelations.markDirty();
            }
            LOTRPacketFactionRelations pkt = LOTRPacketFactionRelations.oneEntry(key, relation);
            LOTRFactionRelations.sendPacketToAll(pkt);
        }
    }

    public static void resetAllRelations() {
        boolean wasEmpty = overrideMap.isEmpty();
        overrideMap.clear();
        if (!wasEmpty) {
            LOTRFactionRelations.markDirty();
            LOTRPacketFactionRelations pkt = LOTRPacketFactionRelations.reset();
            LOTRFactionRelations.sendPacketToAll(pkt);
        }
    }

    public static void markDirty() {
        needsSave = true;
    }

    public static boolean needsSave() {
        return needsSave;
    }

    private static File getRelationsFile() {
        return new File(LOTRLevelData.getOrCreateLOTRDir(), "faction_relations.dat");
    }

    public static void save() {
        try {
            File datFile = LOTRFactionRelations.getRelationsFile();
            if (!datFile.exists()) {
                LOTRLevelData.saveNBTToFile(datFile, new NBTTagCompound());
            }
            NBTTagCompound facData = new NBTTagCompound();
            NBTTagList relationTags = new NBTTagList();
            for (Map.Entry<FactionPair, Relation> e : overrideMap.entrySet()) {
                FactionPair pair = e.getKey();
                Relation rel = e.getValue();
                NBTTagCompound nbt = new NBTTagCompound();
                pair.writeToNBT(nbt);
                nbt.setString("Rel", rel.codeName());
                relationTags.appendTag((NBTBase)nbt);
            }
            facData.setTag("Overrides", (NBTBase)relationTags);
            LOTRLevelData.saveNBTToFile(datFile, facData);
            needsSave = false;
        }
        catch (Exception e) {
            FMLLog.severe((String)"Error saving LOTR faction relations", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            NBTTagCompound facData = LOTRLevelData.loadNBTFromFile(LOTRFactionRelations.getRelationsFile());
            overrideMap.clear();
            NBTTagList relationTags = facData.getTagList("Overrides", 10);
            for (int i = 0; i < relationTags.tagCount(); ++i) {
                NBTTagCompound nbt = relationTags.getCompoundTagAt(i);
                FactionPair pair = FactionPair.readFromNBT(nbt);
                Relation rel = Relation.forName(nbt.getString("Rel"));
                if (pair == null || rel == null) continue;
                overrideMap.put(pair, rel);
            }
            needsLoad = false;
            LOTRFactionRelations.save();
        }
        catch (Exception e) {
            FMLLog.severe((String)"Error loading LOTR faction relations", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

    public static void sendAllRelationsTo(EntityPlayerMP entityplayer) {
        LOTRPacketFactionRelations pkt = LOTRPacketFactionRelations.fullMap(overrideMap);
        LOTRPacketHandler.networkWrapper.sendTo((IMessage)pkt, entityplayer);
    }

    private static void sendPacketToAll(IMessage packet) {
        MinecraftServer srv = MinecraftServer.getServer();
        if (srv != null) {
            for (Object obj : srv.getConfigurationManager().playerEntityList) {
                EntityPlayerMP entityplayer = (EntityPlayerMP)obj;
                LOTRPacketHandler.networkWrapper.sendTo(packet, entityplayer);
            }
        }
    }

    public static enum Relation {
        ALLY,
        FRIEND,
        NEUTRAL,
        ENEMY,
        MORTAL_ENEMY;


        public String codeName() {
            return this.name();
        }

        public String getDisplayName() {
            return StatCollector.translateToLocal((String)("lotr.faction.rel." + this.codeName()));
        }

        public static List<String> listRelationNames() {
            ArrayList<String> names = new ArrayList<String>();
            for (Relation rel : Relation.values()) {
                names.add(rel.codeName());
            }
            return names;
        }

        public static Relation forID(int id) {
            for (Relation rel : Relation.values()) {
                if (rel.ordinal() != id) continue;
                return rel;
            }
            return null;
        }

        public static Relation forName(String name) {
            for (Relation rel : Relation.values()) {
                if (!rel.codeName().equals(name)) continue;
                return rel;
            }
            return null;
        }
    }

    public static class FactionPair {
        private final LOTRFaction fac1;
        private final LOTRFaction fac2;

        public FactionPair(LOTRFaction f1, LOTRFaction f2) {
            this.fac1 = f1;
            this.fac2 = f2;
        }

        public LOTRFaction getLeft() {
            return this.fac1;
        }

        public LOTRFaction getRight() {
            return this.fac2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof FactionPair) {
                FactionPair pair = (FactionPair)obj;
                if (this.fac1 == pair.fac1 && this.fac2 == pair.fac2) {
                    return true;
                }
                if (this.fac1 == pair.fac2 && this.fac2 == pair.fac1) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int f1 = this.fac1.ordinal();
            int f2 = this.fac2.ordinal();
            int lower = Math.min(f1, f2);
            int upper = Math.max(f1, f2);
            return upper << 16 | lower;
        }

        public void writeToNBT(NBTTagCompound nbt) {
            nbt.setString("FacPair1", this.fac1.codeName());
            nbt.setString("FacPair2", this.fac2.codeName());
        }

        public static FactionPair readFromNBT(NBTTagCompound nbt) {
            LOTRFaction f1 = LOTRFaction.forName(nbt.getString("FacPair1"));
            LOTRFaction f2 = LOTRFaction.forName(nbt.getString("FacPair2"));
            if (f1 != null && f2 != null) {
                return new FactionPair(f1, f2);
            }
            return null;
        }
    }

}

