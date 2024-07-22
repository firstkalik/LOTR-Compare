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
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package lotr.common.item;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.UUID;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemMattock2;
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
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LOTRItemHammerAule
extends LOTRItemMattock2
implements LOTRStoryItem {
    protected float lotrWeaponDamage;

    public LOTRItemHammerAule(LOTRMaterial AULE) {
        super(LOTRMaterial.AULE);
        this.setMaxDamage(4800);
        this.lotrWeaponDamage = 12.0f;
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemHammerAule.class, 1.1f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemHammerAule.class, 1.1f);
        LOTRWeaponStats.registerMeleeExtraKnockback(LOTRItemHammerAule.class, 1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    public LOTRItemHammerAule addWeaponDamage(float f) {
        this.lotrWeaponDamage += f;
        return this;
    }

    public float getLOTRWeaponDamage() {
        return this.lotrWeaponDamage;
    }

    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.removeAll((Object)SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
        multimap.put((Object)SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), (Object)new AttributeModifier(field_111210_e, "LOTR Weapon modifier", (double)this.lotrWeaponDamage, 0));
        return multimap;
    }

    public static UUID accessWeaponDamageModifier() {
        return field_111210_e;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        float high_elf4 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.GUNDABAD);
        float high_elf5 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.UTUMNO);
        float high_elf6 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.MORDOR);
        float high_elf7 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.DOL_GULDUR);
        if (high_elf4 <= -1000.0f) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(16, 1200, 0));
        }
        if (high_elf5 <= -1000.0f) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(16, 1200, 0));
        }
        if (high_elf6 <= -1000.0f) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(16, 1200, 0));
        }
        if (high_elf7 <= -1000.0f) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(16, 1200, 0));
        }
        if (high_elf4 <= -2000.0f) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 1200, 0));
        }
        if (high_elf5 <= -2000.0f) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 1200, 0));
        }
        if (high_elf6 <= -2000.0f) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 1200, 0));
        }
        if (high_elf7 <= -2000.0f) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(3, 1200, 0));
        }
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }
}

