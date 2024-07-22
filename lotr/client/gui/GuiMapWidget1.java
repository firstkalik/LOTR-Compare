/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package lotr.client.gui;

import lotr.client.gui.LOTRGuiMapWidget;
import net.minecraft.util.StatCollector;

public class GuiMapWidget1
extends LOTRGuiMapWidget {
    private final String name;

    public GuiMapWidget1(int x, int y, int w, String s, int u, int v) {
        super(x, y, w, s, u, v);
        this.name = s;
    }

    @Override
    public String getTranslatedName() {
        return StatCollector.translateToLocal((String)("lotr.gui.map.widget." + this.name));
    }
}

