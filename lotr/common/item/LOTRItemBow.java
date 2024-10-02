/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ArrowLooseEvent
 *  net.minecraftforge.event.entity.player.ArrowNockEvent
 */
package lotr.common.item;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lotr.client.render.item.LOTRRenderBow;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.entity.item.LOTREntityArrowAvari;
import lotr.common.entity.item.LOTREntityArrowExplosion;
import lotr.common.entity.item.LOTREntityArrowHunger;
import lotr.common.entity.item.LOTREntityArrowMorgul;
import lotr.common.entity.item.LOTREntityArrowPoisoned;
import lotr.common.entity.item.LOTREntityArrowSlow;
import lotr.common.entity.item.LOTREntityArrowWeak;
import lotr.common.item.LOTREntityArrowFire;
import lotr.common.item.LOTRMaterial;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class LOTRItemBow
extends ItemBow {
    private Item.ToolMaterial bowMaterial;
    public final double arrowDamageFactor;
    private int bowPullTime;
    public static final float MIN_BOW_DRAW_AMOUNT = 0.65f;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] bowPullIcons;

    public LOTRItemBow(LOTRMaterial material) {
        this(material.toToolMaterial(), 1.0);
    }

    public LOTRItemBow(LOTRMaterial material, double d) {
        this(material.toToolMaterial(), d);
    }

    public LOTRItemBow(Item.ToolMaterial material) {
        this(material, 1.0);
    }

    public LOTRItemBow(Item.ToolMaterial material, double d) {
        this.bowMaterial = material;
        this.setMaxDamage((int)((float)material.getMaxUses() * 1.5f));
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        this.arrowDamageFactor = d;
        this.bowPullTime = 20;
    }

    public LOTRItemBow setDrawTime(int i) {
        this.bowPullTime = i;
        return this;
    }

    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i) {
        int useTick = this.getMaxItemUseDuration(itemstack) - i;
        ArrowLooseEvent event = new ArrowLooseEvent(entityplayer, itemstack, useTick);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return;
        }
        useTick = event.charge;
        ItemStack arrowItem = null;
        int arrowSlot = this.getInvArrowSlot(entityplayer);
        if (arrowSlot >= 0) {
            arrowItem = entityplayer.inventory.mainInventory[arrowSlot];
        }
        boolean shouldConsume = this.shouldConsumeArrow(itemstack, entityplayer);
        if (arrowItem == null && !shouldConsume) {
            arrowItem = new ItemStack(Items.arrow);
        }
        if (arrowItem != null) {
            float charge = (float)useTick / (float)this.getMaxDrawTime();
            if (charge < 0.65f) {
                return;
            }
            charge = (charge * charge + charge * 2.0f) / 3.0f;
            charge = Math.min(charge, 1.0f);
            EntityArrow arrow = arrowItem.getItem() == LOTRMod.arrowMorgul ? new LOTREntityArrowMorgul(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)) : (arrowItem.getItem() == LOTRMod.arrowPoisoned ? new LOTREntityArrowPoisoned(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)) : (arrowItem.getItem() == LOTRMod.arrowWeak ? new LOTREntityArrowWeak(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)) : (arrowItem.getItem() == LOTRMod.arrowSlow ? new LOTREntityArrowSlow(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)) : (arrowItem.getItem() == LOTRMod.arrowExplosion ? new LOTREntityArrowExplosion(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)) : (arrowItem.getItem() == LOTRMod.arrowAvari ? new LOTREntityArrowAvari(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)) : (arrowItem.getItem() == LOTRMod.arrowHunger ? new LOTREntityArrowHunger(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)) : (arrowItem.getItem() == LOTRMod.arrowFire ? new LOTREntityArrowFire(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)) : new EntityArrow(world, (EntityLivingBase)entityplayer, charge * 2.0f * LOTRItemBow.getLaunchSpeedFactor(itemstack)))))))));
            if (arrow.getDamage() < 1.0) {
                arrow.setDamage(1.0);
            }
            if (charge >= 1.0f) {
                arrow.setIsCritical(true);
            }
            LOTRItemBow.applyBowModifiers(arrow, itemstack);
            itemstack.damageItem(1, (EntityLivingBase)entityplayer);
            world.playSoundAtEntity((Entity)entityplayer, "random.bow", 1.0f, 1.0f / (itemRand.nextFloat() * 0.4f + 1.2f) + charge * 0.5f);
            if (!shouldConsume) {
                arrow.canBePickedUp = 2;
            } else if (arrowSlot >= 0) {
                if (!LOTREnchantmentHelper.hasEnchant(itemstack, LOTREnchantment.rangedInfinity)) {
                    --arrowItem.stackSize;
                    if (arrowItem.stackSize <= 0) {
                        entityplayer.inventory.mainInventory[arrowSlot] = null;
                    }
                } else {
                    arrow.canBePickedUp = 0;
                }
            }
            if (!world.isRemote) {
                world.spawnEntityInWorld((Entity)arrow);
            }
        }
    }

    public static float getLaunchSpeedFactor(ItemStack itemstack) {
        float f = 1.0f;
        if (itemstack != null) {
            if (itemstack.getItem() instanceof LOTRItemBow) {
                f = (float)((double)f * ((LOTRItemBow)itemstack.getItem()).arrowDamageFactor);
            }
            f *= LOTREnchantmentHelper.calcRangedDamageFactor(itemstack);
        }
        return f;
    }

    public static void applyBowModifiers(EntityArrow arrow, ItemStack itemstack) {
        int power = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.power.effectId, (ItemStack)itemstack);
        if (power > 0) {
            arrow.setDamage(arrow.getDamage() + (double)power * 0.5 + 0.5);
        }
        int punch = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.punch.effectId, (ItemStack)itemstack);
        if ((punch += LOTREnchantmentHelper.calcRangedKnockback(itemstack)) > 0) {
            arrow.setKnockbackStrength(punch);
        }
        if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.flame.effectId, (ItemStack)itemstack) + LOTREnchantmentHelper.calcFireAspect(itemstack) > 0) {
            arrow.setFire(100);
        }
        for (LOTREnchantment ench : LOTREnchantment.allEnchantments) {
            if (!ench.applyToProjectile() || !LOTREnchantmentHelper.hasEnchant(itemstack, ench)) continue;
            LOTREnchantmentHelper.setProjectileEnchantment((Entity)arrow, ench);
        }
    }

    public int getMaxDrawTime() {
        return this.bowPullTime;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        ArrowNockEvent event = new ArrowNockEvent(entityplayer, itemstack);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return event.result;
        }
        if (!this.shouldConsumeArrow(itemstack, entityplayer) || this.getInvArrowSlot(entityplayer) >= 0) {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }

    private boolean shouldConsumeArrow(ItemStack itemstack, EntityPlayer entityplayer) {
        return !entityplayer.capabilities.isCreativeMode && EnchantmentHelper.getEnchantmentLevel((int)Enchantment.infinity.effectId, (ItemStack)itemstack) == 0;
    }

    private int getInvArrowSlot(EntityPlayer entityplayer) {
        for (int slot = 0; slot < entityplayer.inventory.mainInventory.length; ++slot) {
            ItemStack invItem = entityplayer.inventory.mainInventory[slot];
            if (invItem == null || invItem.getItem() != Items.arrow && invItem.getItem() != LOTRMod.arrowPoisoned && invItem.getItem() != LOTRMod.arrowFire && invItem.getItem() != LOTRMod.arrowWeak && invItem.getItem() != LOTRMod.arrowSlow && invItem.getItem() != LOTRMod.arrowExplosion && invItem.getItem() != LOTRMod.arrowAvari && invItem.getItem() != LOTRMod.arrowHunger && invItem.getItem() != LOTRMod.arrowMorgul) continue;
            return slot;
        }
        return -1;
    }

    public int getItemEnchantability() {
        return 1 + this.bowMaterial.getEnchantability() / 5;
    }

    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == Items.string ? true : super.getIsRepairable(itemstack, repairItem);
    }

    public BowState getBowState(EntityLivingBase entity, ItemStack usingItem, int useRemaining) {
        if (entity instanceof EntityPlayer && usingItem != null && usingItem.getItem() == this) {
            int ticksInUse = usingItem.getMaxItemUseDuration() - useRemaining;
            double useAmount = (double)ticksInUse / (double)this.bowPullTime;
            if (useAmount >= 0.9) {
                return BowState.PULL_2;
            }
            if (useAmount > 0.65) {
                return BowState.PULL_1;
            }
            if (useAmount > 0.0) {
                return BowState.PULL_0;
            }
        }
        return BowState.HELD;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack itemstack, int renderPass, EntityPlayer entityplayer, ItemStack usingItem, int useRemaining) {
        BowState bowState = this.getBowState((EntityLivingBase)entityplayer, usingItem, useRemaining);
        if (bowState == BowState.PULL_0) {
            return this.bowPullIcons[0];
        }
        if (bowState == BowState.PULL_1) {
            return this.bowPullIcons[1];
        }
        if (bowState == BowState.PULL_2) {
            return this.bowPullIcons[2];
        }
        return this.itemIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        this.itemIcon = iconregister.registerIcon(this.getIconString());
        this.bowPullIcons = new IIcon[3];
        IItemRenderer bowRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)new ItemStack((Item)this), (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
        if (bowRenderer instanceof LOTRRenderBow && ((LOTRRenderBow)bowRenderer).isLargeBow()) {
            Arrays.fill((Object[])this.bowPullIcons, (Object)this.itemIcon);
        } else {
            this.bowPullIcons[0] = iconregister.registerIcon(this.getIconString() + "_" + BowState.PULL_0.iconName);
            this.bowPullIcons[1] = iconregister.registerIcon(this.getIconString() + "_" + BowState.PULL_1.iconName);
            this.bowPullIcons[2] = iconregister.registerIcon(this.getIconString() + "_" + BowState.PULL_2.iconName);
        }
    }

    public static enum BowState {
        HELD(""),
        PULL_0("pull_0"),
        PULL_1("pull_1"),
        PULL_2("pull_2");

        public final String iconName;

        private BowState(String s) {
            this.iconName = s;
        }
    }

}

