/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package lotr.common.item;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRPotions;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRStoryItem;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTRItemStaffGaurchot
extends LOTRItemSword
implements LOTRStoryItem {
    protected float lotrWeaponDamage;
    private boolean isHoldingSword = false;

    public LOTRItemStaffGaurchot(LOTRMaterial AULE) {
        super(LOTRMaterial.UTUMNO_LEGENDARY);
        this.setMaxDamage(4800);
        this.lotrWeaponDamage = 9.5f;
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemStaffGaurchot.class, 1.0f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemStaffGaurchot.class, 1.0f);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    @Override
    public LOTRItemStaffGaurchot addWeaponDamage(float f) {
        this.lotrWeaponDamage += f;
        return this;
    }

    @Override
    public float getLOTRWeaponDamage() {
        return this.lotrWeaponDamage;
    }

    @Override
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.removeAll((Object)SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
        multimap.put((Object)SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), (Object)new AttributeModifier(field_111210_e, "LOTR Weapon modifier", (double)this.lotrWeaponDamage, 0));
        return multimap;
    }

    public static UUID accessWeaponDamageModifier() {
        return field_111210_e;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        World world = attacker.worldObj;
        if (!world.isRemote && world.rand.nextFloat() <= 0.3f) {
            EnumDifficulty difficulty = world.difficultySetting;
            int baseDuration = 1 + difficulty.getDifficultyId() * 2;
            int duration = (baseDuration + world.rand.nextInt(baseDuration)) * 60;
            if (!target.isPotionActive(Potion.poison)) {
                target.addPotionEffect(new PotionEffect(Potion.poison.id, duration));
            }
            if (!target.isPotionActive(LOTRPotions.curse)) {
                target.addPotionEffect(new PotionEffect(LOTRPotions.curse.id, duration));
            }
        }
        return super.hitEntity(stack, target, attacker);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            this.isHoldingSword = isSelected;
            if (this.isHoldingSword && player.isPotionActive(Potion.poison)) {
                player.removePotionEffect(Potion.poison.id);
            }
        }
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }
}

