/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public abstract class LOTRItemBaseRing2
extends Item {
    private List<ItemStack> allowedItems = null;
    public static String noChargeAttackSound = "random.bow";

    public LOTRItemBaseRing2() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.setMaxDamage(150);
        this.maxStackSize = 1;
    }

    public abstract int getUseCost();

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.block;
    }

    public boolean getIsRepairable(ItemStack srcItemStack, ItemStack rawMaterial) {
        if (this.allowedItems == null) {
            this.allowedItems = OreDictionary.getOres((String)"ingotGold");
        }
        for (int i = 0; i < this.allowedItems.size(); ++i) {
            if (!this.allowedItems.get(i).getUnlocalizedName().equals(rawMaterial.getUnlocalizedName())) continue;
            return true;
        }
        return false;
    }

    public boolean onDroppedByPlayer(ItemStack itemstack, EntityPlayer player) {
        boolean hasRing;
        if (itemstack.getItem() instanceof LOTRItemBaseRing2 && !(hasRing = player.inventory.hasItemStack(itemstack))) {
            player.stepHeight = 0.5f;
        }
        return this.bFull3D;
    }

    public boolean isOutOfCharge(ItemStack srcItemStack) {
        return srcItemStack.getItemDamage() >= srcItemStack.getMaxDamage() - this.getUseCost();
    }

    protected void playSound(String soundID, World world, EntityPlayer playerEntity) {
        if (!world.isRemote) {
            world.playSoundAtEntity((Entity)playerEntity, soundID, 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
        }
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        super.addInformation(itemstack, entityplayer, list, flag);
        list.add((Object)EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"right.name"));
    }

    public abstract int getBaseRepairCost();

    public boolean hitEntity(ItemStack item, EntityLivingBase hitEntity, Entity entity, EntityLivingBase attackingEntity) {
        item.damageItem(1, attackingEntity);
        return true;
    }
}

