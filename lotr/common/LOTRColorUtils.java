/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumChatFormatting
 */
package lotr.common;

import net.minecraft.util.EnumChatFormatting;

public class LOTRColorUtils {
    public static int getColorForFormatting(EnumChatFormatting formatting) {
        switch (formatting) {
            case BLACK: {
                return 0;
            }
            case DARK_BLUE: {
                return 170;
            }
            case DARK_GREEN: {
                return 43520;
            }
            case DARK_AQUA: {
                return 43690;
            }
            case DARK_RED: {
                return 11141120;
            }
            case DARK_PURPLE: {
                return 11141290;
            }
            case GOLD: {
                return 16755200;
            }
            case GRAY: {
                return 11184810;
            }
            case DARK_GRAY: {
                return 5592405;
            }
            case BLUE: {
                return 5592575;
            }
            case GREEN: {
                return 5635925;
            }
            case AQUA: {
                return 5636095;
            }
            case RED: {
                return 16733525;
            }
            case LIGHT_PURPLE: {
                return 16733695;
            }
            case YELLOW: {
                return 16777045;
            }
            case WHITE: {
                return 16777215;
            }
            case RESET: {
                return 16777215;
            }
        }
        return 16777215;
    }

}

