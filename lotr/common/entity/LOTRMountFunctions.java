/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.Random;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNPCRideable;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketMountControl;
import lotr.common.network.LOTRPacketMountControlServerEnforce;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRMountFunctions {
    public static void setNavigatorRangeFromNPC(LOTRNPCMount mount, LOTREntityNPC npc) {
        EntityLiving entity = (EntityLiving)mount;
        double d = npc.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
        entity.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(d);
    }

    public static void update(LOTRNPCMount mount) {
        EntityLiving entity = (EntityLiving)mount;
        World world = entity.worldObj;
        Random rand = entity.getRNG();
        if (!world.isRemote) {
            if (rand.nextInt(900) == 0 && entity.isEntityAlive()) {
                entity.heal(1.0f);
            }
            if (!(entity instanceof LOTREntityNPC)) {
                EntityLivingBase target;
                if (entity.getAttackTarget() != null && (!(target = entity.getAttackTarget()).isEntityAlive() || target instanceof EntityPlayer && ((EntityPlayer)target).capabilities.isCreativeMode)) {
                    entity.setAttackTarget(null);
                }
                if (entity.riddenByEntity instanceof EntityLiving) {
                    target = ((EntityLiving)entity.riddenByEntity).getAttackTarget();
                    entity.setAttackTarget(target);
                } else if (entity.riddenByEntity instanceof EntityPlayer) {
                    entity.setAttackTarget(null);
                }
            }
        }
    }

    public static boolean interact(LOTRNPCMount mount, EntityPlayer entityplayer) {
        EntityLiving entity = (EntityLiving)mount;
        if (mount.getBelongsToNPC() && entity.riddenByEntity == null) {
            if (!entity.worldObj.isRemote) {
                entityplayer.addChatMessage((IChatComponent)new ChatComponentTranslation("chat.lotr.mountOwnedByNPC", new Object[0]));
            }
            return true;
        }
        return false;
    }

    public static void move(LOTRNPCMount mount, float strafe, float forward) {
        EntityLiving entity = (EntityLiving)mount;
        Entity rider = entity.riddenByEntity;
        if (rider != null && rider instanceof EntityPlayer && mount.isMountSaddled()) {
            entity.prevRotationYaw = entity.rotationYaw = rider.rotationYaw;
            entity.rotationPitch = rider.rotationPitch * 0.5f;
            entity.rotationYaw %= 360.0f;
            entity.rotationPitch %= 360.0f;
            entity.rotationYawHead = entity.renderYawOffset = entity.rotationYaw;
            strafe = ((EntityLivingBase)rider).moveStrafing * 0.5f;
            forward = ((EntityLivingBase)rider).moveForward;
            if (forward <= 0.0f) {
                forward *= 0.25f;
            }
            entity.stepHeight = 1.0f;
            entity.jumpMovementFactor = entity.getAIMoveSpeed() * 0.1f;
            if (LOTRMountFunctions.canRiderControl_elseNoMotion(entity)) {
                entity.setAIMoveSpeed((float)entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                mount.super_moveEntityWithHeading(strafe, forward);
            }
            entity.prevLimbSwingAmount = entity.limbSwingAmount;
            double d0 = entity.posX - entity.prevPosX;
            double d1 = entity.posZ - entity.prevPosZ;
            float f4 = MathHelper.sqrt_double((double)(d0 * d0 + d1 * d1)) * 4.0f;
            if (f4 > 1.0f) {
                f4 = 1.0f;
            }
            entity.limbSwingAmount += (f4 - entity.limbSwingAmount) * 0.4f;
            entity.limbSwing += entity.limbSwingAmount;
        } else {
            entity.stepHeight = 0.5f;
            entity.jumpMovementFactor = 0.02f;
            mount.super_moveEntityWithHeading(strafe, forward);
        }
    }

    public static boolean sendControlToServer(EntityPlayer clientPlayer) {
        return LOTRMountFunctions.sendControlToServer(clientPlayer, null);
    }

    public static boolean isPlayerControlledMount(Entity mount) {
        if (mount != null && mount.riddenByEntity instanceof EntityPlayer && LOTRMountFunctions.isMountControllable(mount)) {
            return LOTRMountFunctions.canRiderControl(mount);
        }
        return false;
    }

    public static boolean isMountControllable(Entity mount) {
        if (mount instanceof EntityHorse && ((EntityHorse)mount).isTame()) {
            return true;
        }
        return mount instanceof LOTREntityNPCRideable && ((LOTREntityNPCRideable)mount).isNPCTamed();
    }

    public static boolean sendControlToServer(EntityPlayer clientPlayer, LOTRPacketMountControlServerEnforce pktSet) {
        Entity mount = clientPlayer.ridingEntity;
        if (LOTRMountFunctions.isPlayerControlledMount(mount)) {
            if (pktSet != null) {
                mount.setPositionAndRotation(pktSet.posX, pktSet.posY, pktSet.posZ, pktSet.rotationYaw, pktSet.rotationPitch);
                mount.updateRiderPosition();
            }
            LOTRPacketMountControl pkt = new LOTRPacketMountControl(mount);
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)pkt);
            return true;
        }
        return false;
    }

    public static boolean canRiderControl(Entity entity) {
        Entity rider = entity.riddenByEntity;
        if (rider instanceof EntityPlayer) {
            return ((EntityPlayer)rider).isClientWorld();
        }
        return !entity.worldObj.isRemote;
    }

    public static boolean canRiderControl_elseNoMotion(EntityLiving entity) {
        boolean flag = LOTRMountFunctions.canRiderControl((Entity)entity);
        if (!flag && entity.riddenByEntity instanceof EntityPlayer && LOTRMountFunctions.isMountControllable((Entity)entity)) {
            entity.motionX = 0.0;
            entity.motionY = 0.0;
            entity.motionZ = 0.0;
        }
        return flag;
    }
}

