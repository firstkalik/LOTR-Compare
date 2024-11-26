/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import lotr.common.LOTRMod;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentSoulbound
extends LOTREnchantment {
    private final int level = 1;

    public LOTREnchantmentSoulbound(String s) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.MELEE, LOTREnchantmentType.ARMOR, LOTREnchantmentType.THROWING_AXE, LOTREnchantmentType.RANGED_LAUNCHER});
        this.setBypassAnvilLimit();
        this.setPersistsReforge();
        this.setSoulbound();
    }

    private int getSaves(NBTTagCompound t) {
        return t.getInteger("Saves");
    }

    private EnumChatFormatting getColorBySaves(int saves) {
        if (saves == 0) {
            return EnumChatFormatting.RED;
        }
        return EnumChatFormatting.GREEN;
    }

    public static Duration getCooldown(NBTTagCompound t) {
        Instant now = Instant.now();
        if (t.hasKey("CD")) {
            long cooldownEnd = t.getLong("CD");
            if (now.toEpochMilli() < cooldownEnd) {
                return Duration.between(now, Instant.ofEpochMilli(cooldownEnd));
            }
        }
        return null;
    }

    private static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60L;
        long seconds = duration.getSeconds() % 60L;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String getDescription(ItemStack stack) {
        NBTTagCompound tag;
        String baseDescription = StatCollector.translateToLocalFormatted((String)"lotr.enchant.soulbound.desc", (Object[])new Object[0]);
        NBTTagCompound nBTTagCompound = tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        if (tag.hasKey("Saves")) {
            int saves = this.getSaves(tag);
            Duration cooldown = LOTREnchantmentSoulbound.getCooldown(tag);
            if (cooldown != null && !cooldown.isZero() && !cooldown.isNegative()) {
                return (Object)this.getColorBySaves(saves) + "\u041d\u0435\u0430\u043a\u0442\u0438\u0432\u043d\u043e (\u041f\u0435\u0440\u0435\u0437\u0430\u0440\u044f\u0434\u043a\u0430: " + LOTREnchantmentSoulbound.formatDuration(cooldown) + ")" + (Object)EnumChatFormatting.RESET;
            }
            return (Object)EnumChatFormatting.GREEN + "\u0410\u043a\u0442\u0438\u0432\u043d\u043e (\u0417\u0430\u0440\u044f\u0436\u0435\u043d)" + (Object)EnumChatFormatting.RESET;
        }
        return (Object)EnumChatFormatting.GREEN + "\u0410\u043a\u0442\u0438\u0432\u043d\u043e (\u0417\u0430\u0440\u044f\u0436\u0435\u043d)" + (Object)EnumChatFormatting.RESET;
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        ArrayList<Item> validItems = new ArrayList<Item>();
        validItems.add(LOTRMod.sting);
        validItems.add(LOTRMod.ringil);
        validItems.add(LOTRMod.anduril);
        validItems.add(LOTRMod.gandalfStaffGrey);
        validItems.add(LOTRMod.gandalfStaffWhite);
        validItems.add(LOTRMod.gandalfhat);
        validItems.add(LOTRMod.gandalfbody);
        validItems.add(LOTRMod.gandalflegs);
        validItems.add(LOTRMod.gandalfboots);
        validItems.add(LOTRMod.radagasthat);
        validItems.add(LOTRMod.radagastbody);
        validItems.add(LOTRMod.radagastlegs);
        validItems.add(LOTRMod.radagastboots);
        validItems.add(LOTRMod.sarumanhat);
        validItems.add(LOTRMod.sarumanbody);
        validItems.add(LOTRMod.sarumanlegs);
        validItems.add(LOTRMod.sarumanboots);
        validItems.add(LOTRMod.alatarhat);
        validItems.add(LOTRMod.alatarbody);
        validItems.add(LOTRMod.alatarlegs);
        validItems.add(LOTRMod.alatarboots);
        validItems.add(LOTRMod.palandohat);
        validItems.add(LOTRMod.palandobody);
        validItems.add(LOTRMod.palandolegs);
        validItems.add(LOTRMod.palandoboots);
        validItems.add(LOTRMod.glamdring);
        validItems.add(LOTRMod.grond);
        validItems.add(LOTRMod.sauronMace);
        validItems.add(LOTRMod.melkor_sword);
        validItems.add(LOTRMod.battleaxe_melkor);
        validItems.add(LOTRMod.radaghaststaff);
        validItems.add(LOTRMod.sarumanstaff);
        validItems.add(LOTRMod.alatarstaff);
        validItems.add(LOTRMod.pallandostaff);
        validItems.add(LOTRMod.bodyBilbo);
        validItems.add(LOTRMod.d1);
        validItems.add(LOTRMod.d2);
        validItems.add(LOTRMod.d3);
        validItems.add(LOTRMod.d4);
        validItems.add(LOTRMod.d5);
        validItems.add(LOTRMod.d6);
        validItems.add(LOTRMod.d7);
        validItems.add(LOTRMod.naria);
        validItems.add(LOTRMod.nenia);
        validItems.add(LOTRMod.vilia);
        validItems.add(LOTRMod.arcenstone);
        validItems.add(LOTRMod.theOneRing);
        validItems.add(LOTRMod.h1);
        validItems.add(LOTRMod.bundle);
        return validItems.contains((Object)itemstack.getItem());
    }
}

