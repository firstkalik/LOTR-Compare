/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package lotr.common.entity.npc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import lotr.common.entity.npc.LOTRBannerBearer;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.entity.Entity;

public class DRHiredNPCWeight {
    private static final LinkedHashMap<Class<? extends LOTREntityNPC>, Integer> WEIGHT_MAP = new LinkedHashMap<Class<? extends LOTREntityNPC>, Integer>(){};

    public static int getHiredNPCWeight(Class<? extends LOTREntityNPC> entityClass, Class<? extends Entity> mountClass) {
        int mountWeight = 0;
        if (mountClass != null && !LOTREntityNPC.class.isAssignableFrom(mountClass)) {
            mountWeight = 2;
        }
        for (Map.Entry<Class<? extends LOTREntityNPC>, Integer> entry : WEIGHT_MAP.entrySet()) {
            if (!entry.getKey().isAssignableFrom(entityClass)) continue;
            return mountWeight + entry.getValue();
        }
        String entityClassName = entityClass.getTypeName();
        if (LOTRBannerBearer.class.isAssignableFrom(entityClass)) {
            return mountWeight + 3;
        }
        if (entityClassName.contains("AxeThrower") || entityClassName.contains("FireThrower") || entityClassName.contains("Berserker")) {
            return mountWeight + 2;
        }
        return mountWeight + 1;
    }

    public static int getHiredNPCWeight(LOTREntityNPC entity) {
        Entity mount = entity.ridingEntity;
        Class<?> mountClass = mount == null ? null : mount.getClass();
        return DRHiredNPCWeight.getHiredNPCWeight(entity.getClass(), mountClass);
    }

}

