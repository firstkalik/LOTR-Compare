/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.item;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.UUID;
import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRMaterial;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRItemSword
extends ItemSword {
    @SideOnly(value=Side.CLIENT)
    public IIcon glowingIcon;
    private boolean isElvenBlade = false;
    protected float lotrWeaponDamage;

    public LOTRItemSword(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemSword(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        this.lotrWeaponDamage = material.getDamageVsEntity() + 4.0f;
    }

    public LOTRItemSword addWeaponDamage(float f) {
        this.lotrWeaponDamage += f;
        return this;
    }

    public float getLOTRWeaponDamage() {
        return this.lotrWeaponDamage;
    }

    public LOTRItemSword setIsElvenBlade() {
        this.isElvenBlade = true;
        return this;
    }

    public boolean isElvenBlade() {
        return this.isElvenBlade;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        super.registerIcons(iconregister);
        if (this.isElvenBlade) {
            this.glowingIcon = iconregister.registerIcon(this.getIconString() + "_glowing");
        }
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (this.getItemUseAction(itemstack) == EnumAction.none) {
            return itemstack;
        }
        return super.onItemRightClick(itemstack, world, entityplayer);
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
}

