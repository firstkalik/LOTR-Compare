/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Collection;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRPotions;
import lotr.common.item.LOTRItemGraalMithril;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemGraal
extends Item {
    protected int maxDrinks = 2;

    public LOTRItemGraal() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
        this.setMaxStackSize(1);
    }

    public static String strengthCalc(float input) {
        if (input == 0.25f) {
            return " (weak)";
        }
        if (input == 0.5f) {
            return " (light)";
        }
        if (input == 1.0f) {
            return " (moderate)";
        }
        if (input == 2.0f) {
            return " (strong)";
        }
        if (input == 3.0f) {
            return " (potent)";
        }
        return "";
    }

    private boolean canPlayerDrink(EntityPlayer entityplayer, ItemStack itemstack) {
        return itemstack.hasTagCompound() && itemstack.getTagCompound().getInteger("hasDrink") > 0;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.drink;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 32;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (this.canPlayerDrink(entityplayer, itemstack)) {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }

    public int getMaxDrinks() {
        return this.maxDrinks;
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isRemote && itemstack.hasTagCompound()) {
            NBTTagCompound tagCompound = itemstack.getTagCompound();
            int hasDrink = tagCompound.getInteger("hasDrink");
            if (hasDrink == 1) {
                if (!entityplayer.isSneaking()) {
                    float strength1 = tagCompound.getFloat("strength1");
                    this.applyPotionEffects(entityplayer, tagCompound.getString("potion1"), strength1);
                    tagCompound.setInteger("hasDrink", 0);
                }
            } else if (hasDrink > 1) {
                if (entityplayer.isSneaking()) {
                    for (int i = 1; i <= hasDrink; ++i) {
                        float strength = tagCompound.getFloat("strength" + i);
                        this.applyPotionEffects(entityplayer, tagCompound.getString("potion" + i), strength);
                    }
                    tagCompound.setInteger("hasDrink", 0);
                } else {
                    int currentDrink = hasDrink;
                    float strength = tagCompound.getFloat("strength" + currentDrink);
                    this.applyPotionEffects(entityplayer, tagCompound.getString("potion" + currentDrink), strength);
                    tagCompound.setInteger("hasDrink", currentDrink - 1);
                }
            }
            itemstack.setTagCompound(tagCompound);
        }
        return super.onEaten(itemstack, world, entityplayer);
    }

    public ItemStack addDrinkToGraal(ItemStack itemstack, String potionKey, float strength) {
        NBTTagCompound tagCompound;
        int hasDrink;
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        if ((hasDrink = (tagCompound = itemstack.getTagCompound()).getInteger("hasDrink")) < this.maxDrinks) {
            tagCompound.setInteger("hasDrink", ++hasDrink);
            tagCompound.setString("potion" + hasDrink, potionKey);
            tagCompound.setFloat("strength" + hasDrink, strength);
        }
        itemstack.setTagCompound(tagCompound);
        return itemstack;
    }

    public String getItemStackDisplayName(ItemStack itemstack) {
        String baseName = super.getItemStackDisplayName(itemstack);
        if (itemstack.hasTagCompound() && itemstack.getTagCompound().getInteger("hasDrink") > 0) {
            return baseName + " " + StatCollector.translateToLocal((String)"item.lotr.graal.filled");
        }
        return baseName;
    }

    private void applyPotionEffects(EntityPlayer entityplayer, String potionKey, float strength) {
        if (potionKey.contains("AthelasBrew")) {
            this.combinePotionEffect(entityplayer, Potion.regeneration, (int)(500.0f * strength), 0);
            this.combinePotionEffect(entityplayer, Potion.damageBoost, (int)(1600.0f * strength), 0);
        } else if (potionKey.contains("DwarvenTonic")) {
            this.combinePotionEffect(entityplayer, Potion.nightVision, (int)(4800.0f * strength), 0);
        } else if (potionKey.contains("RedDwarvenTonic")) {
            this.combinePotionEffect(entityplayer, Potion.digSpeed, (int)(1200.0f * strength), 0);
        } else if (potionKey.contains("BlueDwarvenTonic")) {
            this.combinePotionEffect(entityplayer, Potion.fireResistance, (int)(300.0f * strength), 0);
        } else if (potionKey.contains("UrukDraught")) {
            this.combinePotionEffect(entityplayer, Potion.damageBoost, (int)(600.0f * strength), 0);
            this.combinePotionEffect(entityplayer, Potion.moveSpeed, (int)(600.0f * strength), 0);
            this.combinePotionEffect(entityplayer, Potion.resistance, (int)(120.0f * strength), 0);
            entityplayer.attackEntityFrom(DamageSource.magic, 2.0f * strength);
        } else if (potionKey.contains("KhamBrew")) {
            this.combinePotionEffect(entityplayer, LOTRPotions.frostResistance, (int)(1200.0f * strength), 0);
        } else if (potionKey.contains("Miruvor")) {
            this.combinePotionEffect(entityplayer, Potion.moveSpeed, (int)(800.0f * strength), 0);
            this.combinePotionEffect(entityplayer, Potion.damageBoost, (int)(800.0f * strength), 0);
        } else if (potionKey.contains("MorgulDraught")) {
            this.combinePotionEffect(entityplayer, Potion.nightVision, (int)(6000.0f * strength), 0);
        } else if (potionKey.contains("OrcDraught")) {
            this.combinePotionEffect(entityplayer, Potion.moveSpeed, (int)(1200.0f * strength), 0);
            this.combinePotionEffect(entityplayer, Potion.damageBoost, (int)(1200.0f * strength), 0);
            entityplayer.attackEntityFrom(DamageSource.magic, 2.0f * strength);
        } else if (potionKey.contains("TauredainCocoa")) {
            this.combinePotionEffect(entityplayer, Potion.damageBoost, (int)(800.0f * strength), 0);
            this.combinePotionEffect(entityplayer, Potion.moveSpeed, (int)(800.0f * strength), 0);
        } else if (potionKey.contains("TorogDraught")) {
            this.combinePotionEffect(entityplayer, Potion.damageBoost, (int)(1800.0f * strength), 0);
        }
    }

    private void combinePotionEffect(EntityPlayer entityplayer, Potion potion, int duration, int amplifier) {
        Collection activeEffects = entityplayer.getActivePotionEffects();
        int totalDuration = duration;
        int maxAmplifier = amplifier;
        for (PotionEffect effect : activeEffects) {
            if (effect.getPotionID() != potion.id) continue;
            totalDuration += effect.getDuration();
            if (this instanceof LOTRItemGraalMithril) {
                maxAmplifier = Math.max(maxAmplifier, effect.getAmplifier() + 1);
                continue;
            }
            maxAmplifier = Math.max(maxAmplifier, effect.getAmplifier());
        }
        int finalAmplifier = Math.min(maxAmplifier, 1);
        entityplayer.removePotionEffect(potion.id);
        entityplayer.addPotionEffect(new PotionEffect(potion.id, totalDuration, finalAmplifier));
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        super.addInformation(stack, player, list, advanced);
        if (!stack.hasTagCompound() || stack.getTagCompound().getInteger("hasDrink") == 0) {
            list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"item.lotr.graal.empty"));
        } else {
            int hasDrink = stack.getTagCompound().getInteger("hasDrink");
            for (int i = 1; i <= hasDrink; ++i) {
                this.addLocalizedDrinkInfo(stack.getTagCompound().getString("potion" + i), stack.getTagCompound().getFloat("strength" + i), list);
            }
        }
    }

    private EnumChatFormatting getDrinkColor(String potionKey) {
        if (potionKey.contains("AthelasBrew")) {
            return EnumChatFormatting.GREEN;
        }
        if (potionKey.contains("DwarvenTonic")) {
            return EnumChatFormatting.BLUE;
        }
        if (potionKey.contains("RedDwarvenTonic")) {
            return EnumChatFormatting.DARK_PURPLE;
        }
        if (potionKey.contains("BlueDwarvenTonic")) {
            return EnumChatFormatting.LIGHT_PURPLE;
        }
        if (potionKey.contains("UrukDraught")) {
            return EnumChatFormatting.DARK_GRAY;
        }
        if (potionKey.contains("KhamBrew")) {
            return EnumChatFormatting.GOLD;
        }
        if (potionKey.contains("Miruvor")) {
            return EnumChatFormatting.YELLOW;
        }
        if (potionKey.contains("MorgulDraught")) {
            return EnumChatFormatting.DARK_GREEN;
        }
        if (potionKey.contains("OrcDraught")) {
            return EnumChatFormatting.RED;
        }
        if (potionKey.contains("TauredainCocoa")) {
            return EnumChatFormatting.DARK_RED;
        }
        if (potionKey.contains("TorogDraught")) {
            return EnumChatFormatting.GOLD;
        }
        return EnumChatFormatting.GRAY;
    }

    private EnumChatFormatting getStrengthColor(int index) {
        switch (index) {
            case 0: {
                return EnumChatFormatting.GREEN;
            }
            case 1: {
                return EnumChatFormatting.YELLOW;
            }
            case 2: {
                return EnumChatFormatting.GOLD;
            }
            case 3: {
                return EnumChatFormatting.RED;
            }
            case 4: {
                return EnumChatFormatting.DARK_RED;
            }
        }
        return EnumChatFormatting.WHITE;
    }

    private String getLocalizedStrength(float strength) {
        if (strength == 0.25f) {
            return StatCollector.translateToLocal((String)"item.lotr.drink.weak");
        }
        if (strength == 0.5f) {
            return StatCollector.translateToLocal((String)"item.lotr.drink.light");
        }
        if (strength == 1.0f) {
            return StatCollector.translateToLocal((String)"item.lotr.drink.moderate");
        }
        if (strength == 2.0f) {
            return StatCollector.translateToLocal((String)"item.lotr.drink.strong");
        }
        if (strength == 3.0f) {
            return StatCollector.translateToLocal((String)"item.lotr.drink.potent");
        }
        return "";
    }

    private void addLocalizedDrinkInfo(String potionKey, float strength, List list) {
        String localizedPotionName = StatCollector.translateToLocal((String)(potionKey + ".name"));
        int strengthIndex = this.getStrengthIndex(strength);
        EnumChatFormatting typeColor = this.getDrinkColor(potionKey);
        EnumChatFormatting strengthColor = this.getStrengthColor(strengthIndex);
        String strengthLocalized = this.getLocalizedStrength(strength);
        list.add((Object)typeColor + localizedPotionName + (Object)EnumChatFormatting.GRAY + " (" + (Object)strengthColor + strengthLocalized + (Object)EnumChatFormatting.GRAY + ")");
    }

    private int getStrengthIndex(float strength) {
        if (strength >= 3.0f) {
            return 4;
        }
        if (strength >= 2.0f) {
            return 3;
        }
        if (strength >= 1.0f) {
            return 2;
        }
        if (strength >= 0.5f) {
            return 1;
        }
        return 0;
    }

    public static boolean isItemFullDrink(ItemStack itemstack) {
        if (itemstack == null || !itemstack.hasTagCompound()) {
            return false;
        }
        int hasDrink = itemstack.getTagCompound().getInteger("hasDrink");
        return hasDrink > 0;
    }
}

