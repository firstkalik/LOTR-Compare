/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public final class UltimateHelper {
    public static final UltimateHelper INSTANCE = new UltimateHelper();
    public static final ResourceLocation BACKGROUND = new ResourceLocation("taleofkingdoms", "textures/gui/crafting.png");

    public Entity getEntity(String name, World world) {
        return EntityList.createEntityByName((String)("taleofkingdoms." + name), (World)world);
    }

    public void spawnEntity(World world, String name, double x, double y, double z, float yaw) {
        this.spawnEntity(world, name, x, y, z, yaw, 0.0f);
    }

    public void spawnEntity(World world, String name, double x, double y, double z, float yaw, float pitch) {
        if (!world.isRemote) {
            EntityLivingBase entity = (EntityLivingBase)this.getEntity("taleofkingdoms." + name, world);
            entity.setLocationAndAngles(x, y, z, yaw, pitch);
            world.spawnEntityInWorld((Entity)entity);
        }
    }

    public String gold(String text) {
        StringBuilder output = new StringBuilder();
        int bound = text.length();
        for (int i = 0; i < bound; ++i) {
            if (((long)i + Minecraft.getSystemTime() / 40L) % 88L == 0L) {
                output.append((Object)ChatFormatting.WHITE).append((Object)ChatFormatting.BOLD).append(text.substring(i, i + 1));
                continue;
            }
            if (((long)i + Minecraft.getSystemTime() / 40L) % 88L == 1L) {
                output.append((Object)ChatFormatting.YELLOW).append((Object)ChatFormatting.BOLD).append(text.substring(i, i + 1));
                continue;
            }
            if (((long)i + Minecraft.getSystemTime() / 40L) % 88L == 87L) {
                output.append((Object)ChatFormatting.YELLOW).append((Object)ChatFormatting.BOLD).append(text.substring(i, i + 1));
                continue;
            }
            output.append((Object)ChatFormatting.GOLD).append((Object)ChatFormatting.BOLD).append(text.substring(i, i + 1));
        }
        return output.toString();
    }

    public String createText(String text) {
        return this.createText(text, ChatFormatting.WHITE);
    }

    public String createText(String text, ChatFormatting formatting) {
        return (Object)formatting + I18n.format((String)text, (Object[])new Object[0]);
    }
}

