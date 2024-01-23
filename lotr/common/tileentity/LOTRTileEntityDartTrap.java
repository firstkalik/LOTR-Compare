/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.block.BlockSourceImpl
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.dispenser.IBehaviorDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntityDispenser
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.IRegistry
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemDart;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.command.IEntitySelector;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IRegistry;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTRTileEntityDartTrap
extends TileEntityDispenser {
    private int fireCooldown;

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.field_146020_a : "container.lotr.dartTrap";
    }

    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
            if (this.fireCooldown > 0) {
                --this.fireCooldown;
            } else {
                AxisAlignedBB range;
                List entities;
                ItemStack itemstack;
                int slot = this.func_146017_i();
                if (slot >= 0 && (itemstack = this.getStackInSlot(slot)).getItem() instanceof LOTRItemDart && !(entities = this.worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, range = this.getTriggerRange(), LOTRMod.selectLivingExceptCreativePlayers())).isEmpty()) {
                    IBehaviorDispenseItem dispense = (IBehaviorDispenseItem)BlockDispenser.dispenseBehaviorRegistry.getObject((Object)itemstack.getItem());
                    ItemStack result = dispense.dispense((IBlockSource)new BlockSourceImpl(this.worldObj, this.xCoord, this.yCoord, this.zCoord), itemstack);
                    this.setInventorySlotContents(slot, result.stackSize == 0 ? null : result);
                    this.fireCooldown = 20;
                }
            }
        }
    }

    public AxisAlignedBB getTriggerRange() {
        Vec3 vecTarget;
        BlockSourceImpl blocksource = new BlockSourceImpl(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        EnumFacing facing = BlockDispenser.func_149937_b((int)this.getBlockMetadata());
        float front = 0.55f;
        float range = 16.0f;
        Vec3 vecPos = Vec3.createVectorHelper((double)((double)this.xCoord + 0.5), (double)((double)this.yCoord + 0.5), (double)((double)this.zCoord + 0.5));
        Vec3 vecFront = vecPos.addVector((double)((float)facing.getFrontOffsetX() * front), (double)((float)facing.getFrontOffsetY() * front), (double)((float)facing.getFrontOffsetZ() * front));
        MovingObjectPosition hitBlock = this.worldObj.func_147447_a(vecFront, vecTarget = vecPos.addVector((double)((float)facing.getFrontOffsetX() * range), (double)((float)facing.getFrontOffsetY() * range), (double)((float)facing.getFrontOffsetZ() * range)), true, true, false);
        if (hitBlock != null) {
            vecTarget = Vec3.createVectorHelper((double)((double)hitBlock.blockX + 0.5 - (double)facing.getFrontOffsetX()), (double)((double)hitBlock.blockY + 0.5 - (double)facing.getFrontOffsetY()), (double)((double)hitBlock.blockZ + 0.5 - (double)facing.getFrontOffsetZ()));
        }
        float f = 0.0f;
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox((double)((float)this.xCoord + f), (double)((float)this.yCoord + f), (double)((float)this.zCoord + f), (double)((float)(this.xCoord + 1) - f), (double)((float)(this.yCoord + 1) - f), (double)((float)(this.zCoord + 1) - f));
        bb = bb.addCoord(vecTarget.xCoord - vecPos.xCoord, vecTarget.yCoord - vecPos.yCoord, vecTarget.zCoord - vecPos.zCoord);
        return bb;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return this.getTriggerRange();
    }
}

