/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTool
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lotr.common.enchant.LOTREnchantmentBane;
import lotr.common.enchant.LOTREnchantmentDamage;
import lotr.common.enchant.LOTREnchantmentDamage2;
import lotr.common.enchant.LOTREnchantmentDurability;
import lotr.common.enchant.LOTREnchantmentFishingSpeed;
import lotr.common.enchant.LOTREnchantmentKnockback;
import lotr.common.enchant.LOTREnchantmentLooting;
import lotr.common.enchant.LOTREnchantmentMeleeReach;
import lotr.common.enchant.LOTREnchantmentMeleeSpeed;
import lotr.common.enchant.LOTREnchantmentProtection;
import lotr.common.enchant.LOTREnchantmentProtectionBattleaxe;
import lotr.common.enchant.LOTREnchantmentProtectionFall;
import lotr.common.enchant.LOTREnchantmentProtectionFire;
import lotr.common.enchant.LOTREnchantmentProtectionHammer;
import lotr.common.enchant.LOTREnchantmentProtectionMithril;
import lotr.common.enchant.LOTREnchantmentProtectionRanged;
import lotr.common.enchant.LOTREnchantmentProtectionSword;
import lotr.common.enchant.LOTREnchantmentProtectionWither;
import lotr.common.enchant.LOTREnchantmentRangedDamage;
import lotr.common.enchant.LOTREnchantmentRangedKnockback;
import lotr.common.enchant.LOTREnchantmentSeaFortune;
import lotr.common.enchant.LOTREnchantmentSilkTouch;
import lotr.common.enchant.LOTREnchantmentSoulbound;
import lotr.common.enchant.LOTREnchantmentThornArmor;
import lotr.common.enchant.LOTREnchantmentToolSpeed;
import lotr.common.enchant.LOTREnchantmentType;
import lotr.common.enchant.LOTREnchantmentWeaponSpecial;
import lotr.common.enchant.LOTREnchantmentWeaponSpecial2;
import lotr.common.enchant.LOTREnchantmentWeaponSpecial3;
import lotr.common.enchant.LOTREnchantmentWeaponSpecial4;
import lotr.common.entity.npc.LOTREntityBalrog;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTREntityGandalf;
import lotr.common.entity.npc.LOTREntityHalfTroll;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTREntityMarshWraith;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTREntitySauron;
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.item.LOTRItemAxe;
import lotr.common.item.LOTRItemBattleaxe;
import lotr.common.item.LOTRItemMattock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public abstract class LOTREnchantment {
    public static List<LOTREnchantment> allEnchantments = new ArrayList<LOTREnchantment>();
    private static Map<String, LOTREnchantment> enchantsByName = new HashMap<String, LOTREnchantment>();
    private static LOTREnchantment randomEnchantment;
    public static final LOTREnchantment strong1;
    public static final LOTREnchantment strong2;
    public static final LOTREnchantment strong3;
    public static final LOTREnchantment strong4;
    public static final LOTREnchantment strong5;
    public static final LOTREnchantment weak1;
    public static final LOTREnchantment weak2;
    public static final LOTREnchantment weak3;
    public static final LOTREnchantment weak4;
    public static final LOTREnchantment baneElf;
    public static final LOTREnchantment baneGandalf;
    public static final LOTREnchantment baneHobbit;
    public static final LOTREnchantment baneSauron;
    public static final LOTREnchantment baneBalrog;
    public static final LOTREnchantment baneOrc;
    public static final LOTREnchantment baneDwarf;
    public static final LOTREnchantment baneWarg;
    public static final LOTREnchantment baneTroll;
    public static final LOTREnchantment baneSpider;
    public static final LOTREnchantment baneWight;
    public static final LOTREnchantment baneWraith;
    public static final LOTREnchantment durable1;
    public static final LOTREnchantment durable2;
    public static final LOTREnchantment durable3;
    public static final LOTREnchantment durable4;
    public static final LOTREnchantment durable5;
    public static final LOTREnchantment meleeSpeed1;
    public static final LOTREnchantment meleeSlow1;
    public static final LOTREnchantment meleeReach1;
    public static final LOTREnchantment meleeUnreach1;
    public static final LOTREnchantment knockback1;
    public static final LOTREnchantment knockback2;
    public static final LOTREnchantment toolSpeed1;
    public static final LOTREnchantment toolSpeed2;
    public static final LOTREnchantment toolSpeed3;
    public static final LOTREnchantment toolSpeed4;
    public static final LOTREnchantment toolSpeed5;
    public static final LOTREnchantment toolSlow1;
    public static final LOTREnchantment toolSilk;
    public static final LOTREnchantment looting1;
    public static final LOTREnchantment looting2;
    public static final LOTREnchantment looting3;
    public static final LOTREnchantment protect1;
    public static final LOTREnchantment protect2;
    public static final LOTREnchantment protectWeak1;
    public static final LOTREnchantment protectWeak2;
    public static final LOTREnchantment protectWeak3;
    public static final LOTREnchantment protectFire1;
    public static final LOTREnchantment protectFire2;
    public static final LOTREnchantment protectFire3;
    public static final LOTREnchantment protectWither;
    public static final LOTREnchantment protectHammer;
    public static final LOTREnchantment protectAxe;
    public static final LOTREnchantment protectFall1;
    public static final LOTREnchantment protectFall2;
    public static final LOTREnchantment protectFall3;
    public static final LOTREnchantment protectRanged1;
    public static final LOTREnchantment protectRanged2;
    public static final LOTREnchantment protectRanged3;
    public static final LOTREnchantment protectMithril;
    public static final LOTREnchantment protectionMithrilElven;
    public static final LOTREnchantment rangedStrong1;
    public static final LOTREnchantment rangedStrong2;
    public static final LOTREnchantment rangedStrong3;
    public static final LOTREnchantment rangedStrong4;
    public static final LOTREnchantment rangedWeak1;
    public static final LOTREnchantment rangedWeak2;
    public static final LOTREnchantment rangedWeak3;
    public static final LOTREnchantment rangedKnockback1;
    public static final LOTREnchantment rangedKnockback2;
    public static final LOTREnchantment fire;
    public static final LOTREnchantment wither;
    public static final LOTREnchantment chill;
    public static final LOTREnchantment headhunting;
    public static final LOTREnchantment rangedInfinity;
    public static final LOTREnchantment rangedProvident;
    public static final LOTREnchantment general;
    public static final LOTREnchantment ranged;
    public static final LOTREnchantment soulbound;
    public static final LOTREnchantment vampireStrike;
    public static final LOTREnchantment thornArmor;
    public static final LOTREnchantment lumberjack;
    public static final LOTREnchantment fishingSpeed1;
    public static final LOTREnchantment fishingSpeed2;
    public static final LOTREnchantment fishingSpeed3;
    public static final LOTREnchantment seaFortune1;
    public static final LOTREnchantment seaFortune2;
    public static final LOTREnchantment seaFortune3;
    public static final LOTREnchantment explodeArmor;
    public final String enchantName;
    public final List<LOTREnchantmentType> itemTypes;
    private int enchantWeight = 0;
    private float valueModifier = 1.0f;
    private boolean skilful = false;
    private boolean persistsReforge = false;
    private boolean bypassAnvilLimit = false;
    private boolean applyToProjectile = false;
    public boolean toolOnly = false;
    public boolean axeOnly = false;
    public boolean isSoulbound;

    public static LOTREnchantment getRandomEnchantment(Random rand) {
        return randomEnchantment;
    }

    public LOTREnchantment(String s, LOTREnchantmentType type) {
        this(s, new LOTREnchantmentType[]{type});
    }

    public LOTREnchantment setToolOnly() {
        this.toolOnly = true;
        return this;
    }

    public LOTREnchantment setAxeOnly() {
        this.axeOnly = true;
        return this;
    }

    public LOTREnchantment(String s, LOTREnchantmentType[] types) {
        this.enchantName = s;
        this.itemTypes = Arrays.asList(types);
        allEnchantments.add(this);
        enchantsByName.put(this.enchantName, this);
    }

    public int getEnchantWeight() {
        return this.enchantWeight;
    }

    public LOTREnchantment setEnchantWeight(int i) {
        this.enchantWeight = i;
        return this;
    }

    public float getValueModifier() {
        return this.valueModifier;
    }

    public LOTREnchantment setValueModifier(float f) {
        this.valueModifier = f;
        return this;
    }

    public boolean isSkilful() {
        return this.skilful;
    }

    public LOTREnchantment setSkilful() {
        this.skilful = true;
        return this;
    }

    public boolean persistsReforge() {
        return this.persistsReforge;
    }

    public LOTREnchantment setPersistsReforge() {
        this.persistsReforge = true;
        return this;
    }

    public boolean bypassAnvilLimit() {
        return this.bypassAnvilLimit;
    }

    public LOTREnchantment setBypassAnvilLimit() {
        this.bypassAnvilLimit = true;
        return this;
    }

    public boolean applyToProjectile() {
        return this.applyToProjectile;
    }

    public LOTREnchantment setApplyToProjectile() {
        this.applyToProjectile = true;
        return this;
    }

    public String getDisplayName() {
        return StatCollector.translateToLocal((String)("lotr.enchant." + this.enchantName));
    }

    public abstract String getDescription(ItemStack var1);

    public final String getNamedFormattedDescription(ItemStack itemstack) {
        String s = StatCollector.translateToLocalFormatted((String)"lotr.enchant.descFormat", (Object[])new Object[]{this.getDisplayName(), this.getDescription(itemstack)});
        s = this.isBeneficial() ? (Object)EnumChatFormatting.GRAY + s : (Object)EnumChatFormatting.DARK_GRAY + s;
        return s;
    }

    public abstract boolean isBeneficial();

    public IChatComponent getEarnMessage(ItemStack itemstack) {
        ChatComponentTranslation msg = new ChatComponentTranslation("lotr.enchant." + this.enchantName + ".earn", new Object[]{itemstack.getDisplayName()});
        msg.getChatStyle().setColor(EnumChatFormatting.YELLOW);
        return msg;
    }

    public IChatComponent getEarnMessageWithName(EntityPlayer entityplayer, ItemStack itemstack) {
        ChatComponentTranslation msg = new ChatComponentTranslation("lotr.enchant." + this.enchantName + ".earnName", new Object[]{entityplayer.getCommandSenderName(), itemstack.getDisplayName()});
        msg.getChatStyle().setColor(EnumChatFormatting.YELLOW);
        return msg;
    }

    public boolean canApply(ItemStack itemstack, boolean considering) {
        if (this.toolOnly) {
            return itemstack != null && itemstack.getItem() instanceof ItemTool;
        }
        for (LOTREnchantmentType type : this.itemTypes) {
            if (!type.canApply(itemstack, considering)) continue;
            return true;
        }
        return false;
    }

    public boolean canApply1(ItemStack itemstack, boolean considering) {
        if (this.axeOnly) {
            return itemstack != null && (itemstack.getItem() instanceof LOTRItemAxe || itemstack.getItem() instanceof LOTRItemBattleaxe || itemstack.getItem() instanceof LOTRItemMattock);
        }
        for (LOTREnchantmentType type : this.itemTypes) {
            if (!type.canApply(itemstack, considering)) continue;
            return true;
        }
        return false;
    }

    public void setSoulbound() {
        this.isSoulbound = true;
    }

    public boolean isCompatibleWith(LOTREnchantment other) {
        return this.getClass() != other.getClass();
    }

    public boolean hasTemplateItem() {
        return this.getEnchantWeight() > 0 && this.isBeneficial();
    }

    public static LOTREnchantment getEnchantmentByName(String s) {
        return enchantsByName.get(s);
    }

    protected final String formatAdditiveInt(int i) {
        String s = String.valueOf(i);
        if (i >= 0) {
            s = "+" + s;
        }
        return s;
    }

    protected final String formatAdditive(float f) {
        String s = this.formatDecimalNumber(f);
        if (f >= 0.0f) {
            s = "+" + s;
        }
        return s;
    }

    protected final String formatMultiplicative(float f) {
        String s = this.formatDecimalNumber(f);
        s = "x" + s;
        return s;
    }

    private final String formatDecimalNumber(float f) {
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(1);
        String s = df.format(f);
        return s;
    }

    static {
        strong1 = new LOTREnchantmentDamage("strong1", 0.5f).setEnchantWeight(10);
        strong2 = new LOTREnchantmentDamage("strong2", 1.0f).setEnchantWeight(5);
        strong3 = new LOTREnchantmentDamage("strong3", 2.0f).setEnchantWeight(2).setSkilful();
        strong4 = new LOTREnchantmentDamage("strong4", 3.0f).setEnchantWeight(1).setSkilful();
        strong5 = new LOTREnchantmentDamage("strong5", 3.5f).setEnchantWeight(1).setSkilful();
        weak1 = new LOTREnchantmentDamage("weak1", -0.5f).setEnchantWeight(6);
        weak2 = new LOTREnchantmentDamage("weak2", -1.0f).setEnchantWeight(4);
        weak3 = new LOTREnchantmentDamage("weak3", -2.0f).setEnchantWeight(2);
        weak4 = new LOTREnchantmentDamage("weak4", -3.0f).setEnchantWeight(1);
        baneElf = new LOTREnchantmentBane("baneElf", 4.0f, LOTREntityElf.class).setEnchantWeight(0);
        baneGandalf = new LOTREnchantmentBane("baneGandalf", 4.0f, LOTREntityGandalf.class).setEnchantWeight(0);
        baneHobbit = new LOTREnchantmentBane("baneHobbit", 2.5f, LOTREntityHobbit.class).setEnchantWeight(0);
        baneSauron = new LOTREnchantmentBane("baneSauron", 8.0f, LOTREntitySauron.class).setEnchantWeight(0);
        baneBalrog = new LOTREnchantmentBane("baneBalrog", 6.0f, LOTREntityBalrog.class).setEnchantWeight(0);
        baneOrc = new LOTREnchantmentBane("baneOrc", 4.0f, LOTREntityOrc.class).setEnchantWeight(0);
        baneDwarf = new LOTREnchantmentBane("baneDwarf", 4.0f, LOTREntityDwarf.class).setEnchantWeight(0);
        baneWarg = new LOTREnchantmentBane("baneWarg", 4.0f, LOTREntityWarg.class).setEnchantWeight(0);
        baneTroll = new LOTREnchantmentBane("baneTroll", 4.0f, LOTREntityTroll.class, LOTREntityHalfTroll.class).setEnchantWeight(0);
        baneSpider = new LOTREnchantmentBane("baneSpider", 4.0f, EnumCreatureAttribute.ARTHROPOD).setEnchantWeight(0);
        baneWight = new LOTREnchantmentBane("baneWight", 4.0f, EnumCreatureAttribute.UNDEAD).setEnchantWeight(0);
        baneWraith = new LOTREnchantmentBane("baneWraith", 0.0f, LOTREntityMarshWraith.class).setUnachievable().setEnchantWeight(0);
        durable1 = new LOTREnchantmentDurability("durable1", 1.25f).setEnchantWeight(15);
        durable2 = new LOTREnchantmentDurability("durable2", 1.5f).setEnchantWeight(8);
        durable3 = new LOTREnchantmentDurability("durable3", 2.0f).setEnchantWeight(4).setSkilful();
        durable4 = new LOTREnchantmentDurability("durable4", 2.5f).setEnchantWeight(2).setSkilful();
        durable5 = new LOTREnchantmentDurability("durable5", 3.0f).setEnchantWeight(1).setSkilful();
        meleeSpeed1 = new LOTREnchantmentMeleeSpeed("meleeSpeed1", 1.25f).setEnchantWeight(6);
        meleeSlow1 = new LOTREnchantmentMeleeSpeed("meleeSlow1", 0.75f).setEnchantWeight(4);
        meleeReach1 = new LOTREnchantmentMeleeReach("meleeReach1", 1.25f).setEnchantWeight(6);
        meleeUnreach1 = new LOTREnchantmentMeleeReach("meleeUnreach1", 0.75f).setEnchantWeight(4);
        knockback1 = new LOTREnchantmentKnockback("knockback1", 1).setEnchantWeight(6);
        knockback2 = new LOTREnchantmentKnockback("knockback2", 2).setEnchantWeight(2).setSkilful();
        toolSpeed1 = new LOTREnchantmentToolSpeed("toolSpeed1", 1.5f).setEnchantWeight(20);
        toolSpeed2 = new LOTREnchantmentToolSpeed("toolSpeed2", 2.0f).setEnchantWeight(10);
        toolSpeed3 = new LOTREnchantmentToolSpeed("toolSpeed3", 3.0f).setEnchantWeight(5).setSkilful();
        toolSpeed4 = new LOTREnchantmentToolSpeed("toolSpeed4", 4.0f).setEnchantWeight(2).setSkilful();
        toolSpeed5 = new LOTREnchantmentToolSpeed("toolSpeed5", 4.5f).setEnchantWeight(1).setSkilful();
        toolSlow1 = new LOTREnchantmentToolSpeed("toolSlow1", 0.75f).setEnchantWeight(10);
        toolSilk = new LOTREnchantmentSilkTouch("toolSilk").setEnchantWeight(10).setSkilful();
        looting1 = new LOTREnchantmentLooting("looting1", 1).setEnchantWeight(6);
        looting2 = new LOTREnchantmentLooting("looting2", 2).setEnchantWeight(2).setSkilful();
        looting3 = new LOTREnchantmentLooting("looting3", 3).setEnchantWeight(1).setSkilful();
        protect1 = new LOTREnchantmentProtection("protect1", 1).setEnchantWeight(10);
        protect2 = new LOTREnchantmentProtection("protect2", 2).setEnchantWeight(3).setSkilful();
        protectWeak1 = new LOTREnchantmentProtection("protectWeak1", -1).setEnchantWeight(5);
        protectWeak2 = new LOTREnchantmentProtection("protectWeak2", -2).setEnchantWeight(2);
        protectWeak3 = new LOTREnchantmentProtection("protectWeak3", -3).setEnchantWeight(1);
        protectFire1 = new LOTREnchantmentProtectionFire("protectFire1", 1).setEnchantWeight(5);
        protectFire2 = new LOTREnchantmentProtectionFire("protectFire2", 2).setEnchantWeight(2).setSkilful();
        protectFire3 = new LOTREnchantmentProtectionFire("protectFire3", 3).setEnchantWeight(1).setSkilful();
        protectWither = new LOTREnchantmentProtectionWither("protectWither", 1).setEnchantWeight(0).setSkilful();
        protectHammer = new LOTREnchantmentProtectionHammer("protectHammer").setEnchantWeight(0).setSkilful();
        protectAxe = new LOTREnchantmentProtectionBattleaxe("protectAxe").setEnchantWeight(0).setSkilful();
        protectFall1 = new LOTREnchantmentProtectionFall("protectFall1", 1).setEnchantWeight(5);
        protectFall2 = new LOTREnchantmentProtectionFall("protectFall2", 2).setEnchantWeight(2).setSkilful();
        protectFall3 = new LOTREnchantmentProtectionFall("protectFall3", 3).setEnchantWeight(1).setSkilful();
        protectRanged1 = new LOTREnchantmentProtectionRanged("protectRanged1", 1).setEnchantWeight(5);
        protectRanged2 = new LOTREnchantmentProtectionRanged("protectRanged2", 2).setEnchantWeight(2).setSkilful();
        protectRanged3 = new LOTREnchantmentProtectionRanged("protectRanged3", 3).setEnchantWeight(1).setSkilful();
        protectMithril = new LOTREnchantmentProtectionMithril("protectMithril").setEnchantWeight(0);
        protectionMithrilElven = new LOTREnchantmentProtectionSword("protectSword").setEnchantWeight(0);
        rangedStrong1 = new LOTREnchantmentRangedDamage("rangedStrong1", 1.1f).setEnchantWeight(10);
        rangedStrong2 = new LOTREnchantmentRangedDamage("rangedStrong2", 1.2f).setEnchantWeight(3);
        rangedStrong3 = new LOTREnchantmentRangedDamage("rangedStrong3", 1.3f).setEnchantWeight(1).setSkilful();
        rangedStrong4 = new LOTREnchantmentRangedDamage("rangedStrong4", 1.35f).setEnchantWeight(1).setSkilful();
        rangedWeak1 = new LOTREnchantmentRangedDamage("rangedWeak1", 0.75f).setEnchantWeight(8);
        rangedWeak2 = new LOTREnchantmentRangedDamage("rangedWeak2", 0.5f).setEnchantWeight(3);
        rangedWeak3 = new LOTREnchantmentRangedDamage("rangedWeak3", 0.25f).setEnchantWeight(1);
        rangedKnockback1 = new LOTREnchantmentRangedKnockback("rangedKnockback1", 1).setEnchantWeight(6);
        rangedKnockback2 = new LOTREnchantmentRangedKnockback("rangedKnockback2", 2).setEnchantWeight(2).setSkilful();
        fire = new LOTREnchantmentWeaponSpecial("fire").setEnchantWeight(0).setApplyToProjectile();
        wither = new LOTREnchantmentWeaponSpecial("wither").setEnchantWeight(0).setApplyToProjectile();
        chill = new LOTREnchantmentWeaponSpecial("chill").setEnchantWeight(0).setApplyToProjectile();
        headhunting = new LOTREnchantmentWeaponSpecial("headhunting").setCompatibleOtherSpecial().setIncompatibleBane().setEnchantWeight(0).setApplyToProjectile();
        rangedInfinity = new LOTREnchantmentWeaponSpecial2("rangedInfinity").setCompatibleOtherSpecial().setEnchantWeight(0).setApplyToProjectile().setSkilful();
        rangedProvident = new LOTREnchantmentWeaponSpecial3("rangedProvident").setCompatibleOtherSpecial().setEnchantWeight(0).setApplyToProjectile().setSkilful();
        general = new LOTREnchantmentWeaponSpecial("general").setCompatibleOtherSpecial().setToolOnly().setEnchantWeight(0).setApplyToProjectile().setSkilful();
        ranged = new LOTREnchantmentWeaponSpecial("ranged").setCompatibleOtherSpecial().setToolOnly().setEnchantWeight(0).setApplyToProjectile().setSkilful();
        soulbound = new LOTREnchantmentSoulbound("soulbound").setEnchantWeight(0);
        vampireStrike = new LOTREnchantmentDamage2("vampireStrike", StatCollector.translateToLocal((String)"lotr.enchant.vampireStrike.desc")).setEnchantWeight(0);
        thornArmor = new LOTREnchantmentThornArmor("thornArmor", StatCollector.translateToLocal((String)"lotr.enchant.thornArmor.desc")).setEnchantWeight(1);
        lumberjack = new LOTREnchantmentWeaponSpecial4("lumberjack").setEnchantWeight(0).setSkilful();
        fishingSpeed1 = new LOTREnchantmentFishingSpeed("fishingSpeed1", 1).setEnchantWeight(3);
        fishingSpeed2 = new LOTREnchantmentFishingSpeed("fishingSpeed2", 2).setEnchantWeight(2);
        fishingSpeed3 = new LOTREnchantmentFishingSpeed("fishingSpeed3", 3).setEnchantWeight(1).setSkilful();
        seaFortune1 = new LOTREnchantmentSeaFortune("seaFortune1", 1).setEnchantWeight(3);
        seaFortune2 = new LOTREnchantmentSeaFortune("seaFortune2", 2).setEnchantWeight(2);
        seaFortune3 = new LOTREnchantmentSeaFortune("seaFortune3", 3).setEnchantWeight(1).setSkilful();
        explodeArmor = new LOTREnchantmentThornArmor("explodeArmor", StatCollector.translateToLocal((String)"lotr.enchant.explodeArmor.desc")).setEnchantWeight(0);
    }
}

