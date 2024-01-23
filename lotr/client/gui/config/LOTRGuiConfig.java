/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiConfig
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.config.Configuration
 */
package lotr.client.gui.config;

import cpw.mods.fml.client.config.GuiConfig;
import java.util.List;
import lotr.common.LOTRConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;

public class LOTRGuiConfig
extends GuiConfig {
    public LOTRGuiConfig(GuiScreen parent) {
        super(parent, LOTRConfig.getConfigElements(), "lotr", false, false, GuiConfig.getAbridgedConfigPath((String)LOTRConfig.config.toString()));
    }
}

