/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.LOTRSquadrons;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.item.LOTRItemSword;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTRItemCommandSword
extends LOTRItemSword
implements LOTRSquadrons.SquadronItem {
    public LOTRItemCommandSword() {
        super(Item.ToolMaterial.IRON);
        this.setMaxDamage(0);
        this.lotrWeaponDamage = 1.0f;
    }

    public boolean isDamageable() {
        return false;
    }

    public int getItemEnchantability() {
        return 0;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.swingItem();
        if (!world.isRemote) {
            Entity entity = this.getEntityTarget(entityplayer);
            if (entity != null) {
                MovingObjectPosition entityHit = new MovingObjectPosition(entity, Vec3.createVectorHelper((double)entity.posX, (double)(entity.boundingBox.minY + (double)(entity.height / 2.0f)), (double)entity.posZ));
                this.command(entityplayer, world, itemstack, entityHit);
            } else {
                double range = 64.0;
                Vec3 eyePos = Vec3.createVectorHelper((double)entityplayer.posX, (double)(entityplayer.posY + (double)entityplayer.getEyeHeight()), (double)entityplayer.posZ);
                Vec3 look = entityplayer.getLookVec();
                Vec3 sight = eyePos.addVector(look.xCoord * range, look.yCoord * range, look.zCoord * range);
                MovingObjectPosition rayTrace = world.func_147447_a(eyePos, sight, false, false, true);
                if (rayTrace != null && rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    this.command(entityplayer, world, itemstack, rayTrace);
                } else {
                    this.command(entityplayer, world, itemstack, null);
                }
            }
        }
        return itemstack;
    }

    private Entity getEntityTarget(EntityPlayer entityplayer) {
        double range = 64.0;
        Vec3 eyePos = Vec3.createVectorHelper((double)entityplayer.posX, (double)(entityplayer.posY + (double)entityplayer.getEyeHeight()), (double)entityplayer.posZ);
        Vec3 look = entityplayer.getLookVec();
        Vec3 sight = eyePos.addVector(look.xCoord * range, look.yCoord * range, look.zCoord * range);
        float sightWidth = 1.0f;
        List list = entityplayer.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)entityplayer, entityplayer.boundingBox.addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expand((double)sightWidth, (double)sightWidth, (double)sightWidth));
        Entity pointedEntity = null;
        double entityDist = range;
        for (Object element : list) {
            double d;
            Entity entity = (Entity)element;
            if (!(entity instanceof EntityLivingBase) || !entity.canBeCollidedWith()) continue;
            float width = 1.0f;
            AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)width, (double)width, (double)width);
            MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(eyePos, sight);
            if (axisalignedbb.isVecInside(eyePos)) {
                if (entityDist < 0.0) continue;
                pointedEntity = entity;
                entityDist = 0.0;
                continue;
            }
            if (movingobjectposition == null) continue;
            double d2 = eyePos.distanceTo(movingobjectposition.hitVec);
            if (d >= entityDist && entityDist != 0.0) continue;
            if (entity == entityplayer.ridingEntity && !entity.canRiderInteract()) {
                if (entityDist != 0.0) continue;
                pointedEntity = entity;
                continue;
            }
            pointedEntity = entity;
            entityDist = d2;
        }
        if (pointedEntity != null) {
            return pointedEntity;
        }
        return null;
    }

    private void command(final EntityPlayer entityplayer, World world, ItemStack itemstack, MovingObjectPosition hitTarget) {
        entityplayer.setRevengeTarget(null);
        List spreadTargets = new ArrayList();
        if (hitTarget != null) {
            Vec3 vec = hitTarget.hitVec;
            AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double)vec.xCoord, (double)vec.yCoord, (double)vec.zCoord, (double)vec.xCoord, (double)vec.yCoord, (double)vec.zCoord);
            aabb = aabb.expand(6.0, 6.0, 6.0);
            spreadTargets = world.selectEntitiesWithinAABB(EntityLivingBase.class, aabb, new IEntitySelector(){

                public boolean isEntityApplicable(Entity entity) {
                    return entity.isEntityAlive() && LOTRMod.canPlayerAttackEntity(entityplayer, (EntityLivingBase)entity, false);
                }
            });
        }
        List nearbyHiredUnits = world.getEntitiesWithinAABB(LOTREntityNPC.class, entityplayer.boundingBox.expand(12.0, 12.0, 12.0));
        for (Object nearbyHiredUnit : nearbyHiredUnits) {
            LOTREntityNPC npc = (LOTREntityNPC)nearbyHiredUnit;
            if (!npc.hiredNPCInfo.isActive || npc.hiredNPCInfo.getHiringPlayer() != entityplayer || !npc.hiredNPCInfo.getObeyCommandSword() || !LOTRSquadrons.areSquadronsCompatible(npc, itemstack)) continue;
            ArrayList<EntityLivingBase> validTargets = new ArrayList<EntityLivingBase>();
            if (!spreadTargets.isEmpty()) {
                for (Object obj : spreadTargets) {
                    EntityLivingBase entity = (EntityLivingBase)obj;
                    if (!LOTRMod.canNPCAttackEntity(npc, entity, true)) continue;
                    validTargets.add(entity);
                }
            }
            if (!validTargets.isEmpty()) {
                LOTREntityAINearestAttackableTargetBasic.TargetSorter sorter = new LOTREntityAINearestAttackableTargetBasic.TargetSorter((EntityLivingBase)npc);
                Collections.sort(validTargets, sorter);
                EntityLivingBase target = (EntityLivingBase)validTargets.get(0);
                npc.hiredNPCInfo.commandSwordAttack(target);
                npc.hiredNPCInfo.wasAttackCommanded = true;
                continue;
            }
            npc.hiredNPCInfo.commandSwordCancel();
        }
    }

}

