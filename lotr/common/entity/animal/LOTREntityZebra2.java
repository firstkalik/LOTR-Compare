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
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.fac.LOTRFaction;
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

public class LOTREntityZebra2
extends LOTREntityHorse {
    public LOTREntityZebra2(World world) {
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
        LOTREntityZebra2 zebra = (LOTREntityZebra2)super.createChild(entityageable);
        return zebra;
    }

    @Override
    public boolean interact(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        float highElf2 = LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.UTUMNO);
        if (itemstack != null && itemstack.getItem() == Items.bucket && !entityplayer.capabilities.isCreativeMode && highElf2 >= 1000.0f) {
            --itemstack.stackSize;
            if (itemstack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(Items.lava_bucket));
            } else if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.lava_bucket))) {
                entityplayer.dropPlayerItemWithRandomChoice(new ItemStack(Items.lava_bucket, 1, 0), false);
            }
            this.setHealth(this.getHealth() - 4.0f);
            entityplayer.worldObj.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "random.fizz", 0.4f, 0.5f);
            return true;
        }
        return super.interact(entityplayer);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        String s = this.rand.nextInt(3) > 0 ? "flame" : "smoke";
        this.worldObj.spawnParticle(s, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }
}

