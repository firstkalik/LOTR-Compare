/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumChatFormatting
 */
package lotr.common.item;

import lotr.common.item.AnvilNameColorProvider;
import lotr.common.item.LOTRItemGem;
import net.minecraft.util.EnumChatFormatting;

public class LOTRItemGemWithAnvilNameColor
extends LOTRItemGem
implements AnvilNameColorProvider {
    private final EnumChatFormatting anvilNameColor;

    public LOTRItemGemWithAnvilNameColor(EnumChatFormatting color) {
        this.anvilNameColor = color;
    }

    @Override
    public EnumChatFormatting getAnvilNameColor() {
        return this.anvilNameColor;
    }
}

