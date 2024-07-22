/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
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
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.entity.projectile.LOTREntityCrossbowBolt;
import lotr.common.item.LOTRItemBow;
import lotr.common.item.LOTRItemCrossbowBolt;
import lotr.common.item.LOTRMaterial;
import lotr.common.recipe.LOTRRecipes;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemCrossbow
extends ItemBow {
    public final double boltDamageFactor;
    private Item.ToolMaterial crossbowMaterial;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] crossbowPullIcons;

    public LOTRItemCrossbow(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemCrossbow(Item.ToolMaterial material) {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        this.crossbowMaterial = material;
        this.setMaxDamage((int)((float)this.crossbowMaterial.getMaxUses() * 1.25f));
        this.setMaxStackSize(1);
        this.boltDamageFactor = 1.0f + Math.max(0.0f, (this.crossbowMaterial.getDamageVsEntity() - 2.0f) * 0.1f);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (LOTRItemCrossbow.isLoaded(itemstack)) {
            ItemStack boltItem = LOTRItemCrossbow.getLoaded(itemstack);
            if (boltItem != null) {
                float charge = 1.0f;
                ItemStack shotBolt = boltItem.copy();
                shotBolt.stackSize = 1;
                LOTREntityCrossbowBolt bolt = new LOTREntityCrossbowBolt(world, (EntityLivingBase)entityplayer, shotBolt, charge * 2.0f * LOTRItemCrossbow.getCrossbowLaunchSpeedFactor(itemstack));
                if (bolt.boltDamageFactor < 1.0) {
                    bolt.boltDamageFactor = 1.0;
                }
                if (charge >= 1.0f) {
                    bolt.setIsCritical(true);
                }
                LOTRItemCrossbow.applyCrossbowModifiers(bolt, itemstack);
                if (!this.shouldConsumeBolt(itemstack, entityplayer)) {
                    bolt.canBePickedUp = 2;
                }
                if (!world.isRemote) {
                    world.spawnEntityInWorld((Entity)bolt);
                }
                world.playSoundAtEntity((Entity)entityplayer, "lotr:item.crossbow", 1.0f, 1.0f / (itemRand.nextFloat() * 0.4f + 1.2f) + charge * 0.5f);
                itemstack.damageItem(1, (EntityLivingBase)entityplayer);
                if (!world.isRemote) {
                    this.setLoaded(itemstack, null);
                }
            }
        } else if (!this.shouldConsumeBolt(itemstack, entityplayer) || this.getInvBoltSlot(entityplayer) >= 0) {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }

    public static float getCrossbowLaunchSpeedFactor(ItemStack itemstack) {
        float f = 1.0f;
        if (itemstack != null) {
            if (itemstack.getItem() instanceof LOTRItemCrossbow) {
                f = (float)((double)f * ((LOTRItemCrossbow)itemstack.getItem()).boltDamageFactor);
            }
            f *= LOTREnchantmentHelper.calcRangedDamageFactor(itemstack);
        }
        return f;
    }

    public static void applyCrossbowModifiers(LOTREntityCrossbowBolt bolt, ItemStack itemstack) {
        int power = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.power.effectId, (ItemStack)itemstack);
        if (power > 0) {
            bolt.boltDamageFactor += (double)power * 0.5 + 0.5;
        }
        int punch = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.punch.effectId, (ItemStack)itemstack);
        if ((punch += LOTREnchantmentHelper.calcRangedKnockback(itemstack)) > 0) {
            bolt.knockbackStrength = punch;
        }
        if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.flame.effectId, (ItemStack)itemstack) + LOTREnchantmentHelper.calcFireAspect(itemstack) > 0) {
            bolt.setFire(100);
        }
        for (LOTREnchantment ench : LOTREnchantment.allEnchantments) {
            if (!ench.applyToProjectile() || !LOTREnchantmentHelper.hasEnchant(itemstack, ench)) continue;
            LOTREnchantmentHelper.setProjectileEnchantment(bolt, ench);
        }
    }

    public void onUsingTick(ItemStack itemstack, EntityPlayer entityplayer, int count) {
        World world = entityplayer.worldObj;
        if (!world.isRemote && !LOTRItemCrossbow.isLoaded(itemstack) && this.getMaxItemUseDuration(itemstack) - count == this.getMaxDrawTime()) {
            world.playSoundAtEntity((Entity)entityplayer, "lotr:item.crossbowLoad", 1.0f, 1.5f + world.rand.nextFloat() * 0.2f);
        }
    }

    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int useTick) {
        int ticksInUse = this.getMaxItemUseDuration(itemstack) - useTick;
        if (ticksInUse >= this.getMaxDrawTime() && !LOTRItemCrossbow.isLoaded(itemstack)) {
            ItemStack boltItem = null;
            int boltSlot = this.getInvBoltSlot(entityplayer);
            if (boltSlot >= 0) {
                boltItem = entityplayer.inventory.mainInventory[boltSlot];
            }
            boolean shouldConsume = this.shouldConsumeBolt(itemstack, entityplayer);
            if (boltItem == null && !shouldConsume) {
                boltItem = new ItemStack(LOTRMod.crossbowBolt);
            }
            if (boltItem != null) {
                if (shouldConsume && boltSlot >= 0) {
                    --boltItem.stackSize;
                    if (boltItem.stackSize <= 0) {
                        entityplayer.inventory.mainInventory[boltSlot] = null;
                    }
                }
                if (!world.isRemote) {
                    this.setLoaded(itemstack, boltItem.copy());
                }
            }
            entityplayer.clearItemInUse();
        }
    }

    public int getMaxDrawTime() {
        return 50;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 72000;
    }

    public static boolean isLoaded(ItemStack itemstack) {
        return LOTRItemCrossbow.getLoaded(itemstack) != null;
    }

    public static ItemStack getLoaded(ItemStack itemstack) {
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemCrossbow) {
            NBTTagCompound nbt = itemstack.getTagCompound();
            if (nbt == null) {
                return null;
            }
            if (nbt.hasKey("LOTRCrossbowAmmo")) {
                NBTTagCompound ammoData = nbt.getCompoundTag("LOTRCrossbowAmmo");
                return ItemStack.loadItemStackFromNBT((NBTTagCompound)ammoData);
            }
            if (nbt.hasKey("LOTRCrossbowLoaded")) {
                return new ItemStack(LOTRMod.crossbowBolt);
            }
        }
        return null;
    }

    private void setLoaded(ItemStack itemstack, ItemStack ammo) {
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemCrossbow) {
            NBTTagCompound nbt = itemstack.getTagCompound();
            if (nbt == null) {
                nbt = new NBTTagCompound();
                itemstack.setTagCompound(nbt);
            }
            if (ammo != null) {
                NBTTagCompound ammoData = new NBTTagCompound();
                ammo.writeToNBT(ammoData);
                nbt.setTag("LOTRCrossbowAmmo", (NBTBase)ammoData);
            } else {
                nbt.removeTag("LOTRCrossbowAmmo");
            }
            if (nbt.hasKey("LOTRCrossbowLoaded")) {
                nbt.removeTag("LOTRCrossbowLoaded");
            }
        }
    }

    private boolean shouldConsumeBolt(ItemStack itemstack, EntityPlayer entityplayer) {
        return !entityplayer.capabilities.isCreativeMode && EnchantmentHelper.getEnchantmentLevel((int)Enchantment.infinity.effectId, (ItemStack)itemstack) == 0;
    }

    private int getInvBoltSlot(EntityPlayer entityplayer) {
        for (int slot = 0; slot < entityplayer.inventory.mainInventory.length; ++slot) {
            ItemStack invItem = entityplayer.inventory.mainInventory[slot];
            if (invItem == null || !(invItem.getItem() instanceof LOTRItemCrossbowBolt)) continue;
            return slot;
        }
        return -1;
    }

    public String getItemStackDisplayName(ItemStack itemstack) {
        String name = super.getItemStackDisplayName(itemstack);
        if (LOTRItemCrossbow.isLoaded(itemstack)) {
            name = StatCollector.translateToLocalFormatted((String)"item.lotr.crossbow.loaded", (Object[])new Object[]{name});
        }
        return name;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        ItemStack ammo = LOTRItemCrossbow.getLoaded(itemstack);
        if (ammo != null) {
            String ammoName = ammo.getDisplayName();
            list.add(StatCollector.translateToLocalFormatted((String)"item.lotr.crossbow.loadedItem", (Object[])new Object[]{ammoName}));
        }
    }

    public int getItemEnchantability() {
        return 1 + this.crossbowMaterial.getEnchantability() / 5;
    }

    public Item.ToolMaterial getCrossbowMaterial() {
        return this.crossbowMaterial;
    }

    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        if (LOTRRecipes.checkItemEquals(this.crossbowMaterial.getRepairItemStack(), repairItem)) {
            return true;
        }
        return super.getIsRepairable(itemstack, repairItem);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack itemstack, int renderPass, EntityPlayer entityplayer, ItemStack usingItem, int useRemaining) {
        if (LOTRItemCrossbow.isLoaded(itemstack)) {
            return this.crossbowPullIcons[2];
        }
        if (usingItem != null && usingItem.getItem() == this) {
            int ticksInUse = usingItem.getMaxItemUseDuration() - useRemaining;
            double useAmount = (double)ticksInUse / (double)this.getMaxDrawTime();
            if (useAmount >= 1.0) {
                return this.crossbowPullIcons[2];
            }
            if (useAmount > 0.5) {
                return this.crossbowPullIcons[1];
            }
            if (useAmount > 0.0) {
                return this.crossbowPullIcons[0];
            }
        }
        return this.itemIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconIndex(ItemStack itemstack) {
        if (LOTRItemCrossbow.isLoaded(itemstack)) {
            return this.crossbowPullIcons[2];
        }
        return this.itemIcon;
    }

    public IIcon getIcon(ItemStack itemstack, int pass) {
        return this.getIconIndex(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        this.itemIcon = iconregister.registerIcon(this.getIconString());
        this.crossbowPullIcons = new IIcon[3];
        this.crossbowPullIcons[0] = iconregister.registerIcon(this.getIconString() + "_" + LOTRItemBow.BowState.PULL_0.iconName);
        this.crossbowPullIcons[1] = iconregister.registerIcon(this.getIconString() + "_" + LOTRItemBow.BowState.PULL_1.iconName);
        this.crossbowPullIcons[2] = iconregister.registerIcon(this.getIconString() + "_" + LOTRItemBow.BowState.PULL_2.iconName);
    }
}

