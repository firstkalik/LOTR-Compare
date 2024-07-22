/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.util.EnumChatFormatting
 */
package lotr.common.item;

import lotr.common.item.AnvilNameColorProvider;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;

public class LOTRItemWithAnvilNameColor
extends Item
implements AnvilNameColorProvider {
    private final EnumChatFormatting anvilNameColor;

    public LOTRItemWithAnvilNameColor(EnumChatFormatting color) {
        this.anvilNameColor = color;
    }

    @Override
    public EnumChatFormatting getAnvilNameColor() {
        return this.anvilNameColor;
    }
}

