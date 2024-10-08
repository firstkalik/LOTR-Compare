/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.animal.LOTREntityHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTREntityZebra
extends LOTREntityHorse {
    public LOTREntityZebra(World world) {
        super(world);
    }

    public int getHorseType() {
        return 0;
    }

    public boolean func_110259_cr() {
        return false;
    }

    @Override
    public String getCommandSenderName() {
        if (this.hasCustomNameTag()) {
            return this.getCustomNameTag();
        }
        String s = EntityList.getEntityString((Entity)this);
        return StatCollector.translateToLocal((String)("entity." + s + ".name"));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
        LOTREntityZebra zebra = (LOTREntityZebra)super.createChild(entityageable);
        return zebra;
    }

    @Override
    public boolean interact(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() == Items.bucket && !entityplayer.capabilities.isCreativeMode) {
            --itemstack.stackSize;
            if (itemstack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(Items.milk_bucket));
            } else if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.milk_bucket))) {
                entityplayer.dropPlayerItemWithRandomChoice(new ItemStack(Items.milk_bucket, 1, 0), false);
            }
            return true;
        }
        return super.interact(entityplayer);
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        int j = this.rand.nextInt(2) + this.rand.nextInt(1 + i);
        for (int k = 0; k < j; ++k) {
            this.dropItem(Items.leather, 1);
        }
        int j3 = this.rand.nextInt(2) + this.rand.nextInt(1 + i);
        for (int k = 0; k < j3; ++k) {
            this.dropItem(Items.bone, 1);
        }
        j = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + i);
        for (int l = 0; l < j; ++l) {
            if (this.isBurning()) {
                this.dropItem(LOTRMod.zebraCooked, 1);
                continue;
            }
            this.dropItem(LOTRMod.zebraRaw, 1);
        }
    }

    protected String getLivingSound() {
        return "lotr:zebra.say";
    }

    protected String getHurtSound() {
        return "lotr:zebra.hurt";
    }

    protected String getDeathSound() {
        return "lotr:zebra.death";
    }

    protected String getAngrySoundName() {
        return "lotr:zebra.hurt";
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }
}

