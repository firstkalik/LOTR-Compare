/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.server.MinecraftServer
 *  org.apache.logging.log4j.Logger
 */
package lotr.common.util;

import cpw.mods.fml.common.FMLLog;
import java.lang.reflect.Field;
import lotr.common.LOTRReflection;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;

public class LOTRLog {
    public static Logger logger;

    public static void findLogger() {
        try {
            Field[] fields;
            for (Field f : fields = MinecraftServer.class.getDeclaredFields()) {
                LOTRReflection.unlockFinalField(f);
                Object obj = f.get(null);
                if (!(obj instanceof Logger)) continue;
                logger = (Logger)obj;
                logger.info("LOTR: Found logger");
                break;
            }
        }
        catch (Exception e) {
            FMLLog.warning((String)"LOTR: Failed to find logger!", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }
}

