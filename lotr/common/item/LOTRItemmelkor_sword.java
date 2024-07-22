/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.entity.projectile.LOTREntityFirePotMorgoth;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRStoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemmelkor_sword
extends LOTRItemSword
implements LOTRStoryItem {
    public LOTRItemmelkor_sword() {
        super(LOTRMaterial.UTUMNO);
        this.setMaxDamage(1500);
        this.lotrWeaponDamage = 9.5f;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if (p_77663_3_ instanceof EntityPlayer) {
            float high_elf = LOTRLevelData.getData((EntityPlayer)p_77663_3_).getAlignment(LOTRFaction.UTUMNO);
            if (p_77663_3_.ticksExisted % 180 != 0) {
                return;
            }
            if (high_elf <= 4000.0f) {
                p_77663_3_.setFire(5);
            }
        }
    }

    @Override
    public boolean hitEntity(ItemStack item, EntityLivingBase hitEntity, EntityLivingBase attackingEntity) {
        hitEntity.setFire(3);
        return true;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.swingItem();
        itemstack.damageItem(2, (EntityLivingBase)entityplayer);
        world.playSoundAtEntity((Entity)entityplayer, "mob.ghast.fireball", 2.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity)new LOTREntityFirePotMorgoth(world, (EntityLivingBase)entityplayer));
            world.playSoundAtEntity((Entity)entityplayer, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
            itemstack.damageItem(1, (EntityLivingBase)entityplayer);
        }
        return itemstack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 60;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }
}

