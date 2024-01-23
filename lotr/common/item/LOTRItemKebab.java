/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.item.LOTRItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class LOTRItemKebab
extends LOTRItemFood {
    public LOTRItemKebab(int healAmount, float saturation, boolean canWolfEat) {
        super(healAmount, saturation, canWolfEat);
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isRemote && world.rand.nextInt(100) == 0) {
            entityplayer.addChatMessage((IChatComponent)new ChatComponentText("That was a good kebab. You feel a lot better."));
        }
        return super.onEaten(itemstack, world, entityplayer);
    }
}

