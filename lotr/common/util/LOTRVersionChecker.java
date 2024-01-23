/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  org.apache.logging.log4j.Logger
 */
package lotr.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import lotr.common.util.LOTRLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.Logger;

public class LOTRVersionChecker {
    private static String versionURL = "https://dl.dropboxusercontent.com/s/sidxw1dicl2nsev/version.txt";
    private static boolean checkedUpdate = false;

    public static void checkForUpdates() {
        if (!checkedUpdate) {
            Thread checkThread = new Thread("LOTR Update Checker"){

                @Override
                public void run() {
                    try {
                        String line;
                        URL url = new URL(versionURL);
                        BufferedReader updateReader = new BufferedReader(new InputStreamReader(url.openStream()));
                        String updateVersion = "";
                        while ((line = updateReader.readLine()) != null) {
                            updateVersion = updateVersion.concat(line);
                        }
                        updateReader.close();
                        updateVersion = updateVersion.trim();
                        String currentVersion = "Update v36.0 for Minecraft 1.7.10";
                        if (!updateVersion.equals(currentVersion)) {
                            ChatComponentText component = new ChatComponentText("The Lord of the Rings Mod:");
                            component.getChatStyle().setColor(EnumChatFormatting.YELLOW);
                            EntityClientPlayerMP entityplayer = Minecraft.getMinecraft().thePlayer;
                            if (entityplayer != null) {
                                entityplayer.addChatMessage((IChatComponent)new ChatComponentTranslation("chat.lotr.update", new Object[]{component, updateVersion}));
                            }
                        }
                    }
                    catch (Exception e) {
                        LOTRLog.logger.warn("LOTR: Version check failed");
                        e.printStackTrace();
                    }
                }
            };
            checkedUpdate = true;
            checkThread.setDaemon(true);
            checkThread.start();
        }
    }

}

