/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class LOTRPotionRepair
extends Potion {
    public static int repairTime = 80;

    public LOTRPotionRepair() {
        super(32, false, 7829367);
        this.setPotionName("potion.lotr.repair");
        this.setIconIndex(0, 2);
    }

    public boolean isReady(int tick, int level) {
        int freq = 5 >> level;
        return freq > 0 && tick % freq == 0;
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (entity.ticksExisted % repairTime == 0) {
            for (int i = 0; i < 5; ++i) {
                ItemStack stack = entity.getEquipmentInSlot(i);
                if (stack == null || !stack.isItemStackDamageable() || stack.getItemDamage() <= 0) continue;
                int damage = Math.max(0, stack.getItemDamage() - (amplifier + 1));
                stack.setItemDamage(damage);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasStatusIcon() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        LOTRMod.proxy.renderCustomPotionEffect(x, y, effect, mc);
    }
}

