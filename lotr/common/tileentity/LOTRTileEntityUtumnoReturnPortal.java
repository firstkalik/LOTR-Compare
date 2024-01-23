/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S12PacketEntityVelocity
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRFaction;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketUtumnoReturn;
import lotr.common.world.LOTRTeleporterUtumno;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class LOTRTileEntityUtumnoReturnPortal
extends TileEntity {
    public static int PORTAL_TOP = 250;
    private int beamCheck = 0;
    public int ticksExisted;

    public void updateEntity() {
        ++this.ticksExisted;
        if (!this.worldObj.isRemote) {
            if (this.beamCheck % 20 == 0) {
                int i = this.xCoord;
                int k = this.zCoord;
                for (int j = this.yCoord + 1; j <= PORTAL_TOP; ++j) {
                    this.worldObj.setBlock(i, j, k, LOTRMod.utumnoReturnLight, 0, 3);
                }
            }
            ++this.beamCheck;
        }
        List nearbyEntities = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)PORTAL_TOP, (double)(this.zCoord + 1)));
        for (Object obj : nearbyEntities) {
            EntityPlayer entityplayer;
            Entity entity = (Entity)obj;
            if (LOTRMod.getNPCFaction(entity) == LOTRFaction.UTUMNO) continue;
            if (entity instanceof EntityPlayer) {
                entityplayer = (EntityPlayer)entity;
                if (entityplayer.capabilities.isFlying) continue;
            }
            if (!this.worldObj.isRemote) {
                float accel = 0.2f;
                entity.motionY += (double)accel;
                entity.motionY = Math.max(entity.motionY, 0.0);
                entity.setPosition((double)this.xCoord + 0.5, entity.boundingBox.minY, (double)this.zCoord + 0.5);
                entity.fallDistance = 0.0f;
            }
            if (entity instanceof EntityPlayer) {
                entityplayer = (EntityPlayer)entity;
                LOTRMod.proxy.setInUtumnoReturnPortal(entityplayer);
                if (entityplayer instanceof EntityPlayerMP) {
                    EntityPlayerMP entityplayermp = (EntityPlayerMP)entityplayer;
                    LOTRPacketUtumnoReturn packet = new LOTRPacketUtumnoReturn(entityplayer.posX, entityplayer.posZ);
                    LOTRPacketHandler.networkWrapper.sendTo((IMessage)packet, entityplayermp);
                    entityplayermp.playerNetServerHandler.sendPacket((Packet)new S12PacketEntityVelocity((Entity)entityplayer));
                }
            }
            if (this.worldObj.isRemote || !(entity.posY >= (double)PORTAL_TOP - 5.0)) continue;
            int dimension = LOTRDimension.MIDDLE_EARTH.dimensionID;
            LOTRTeleporterUtumno teleporter = LOTRTeleporterUtumno.newTeleporter(dimension);
            if (entity instanceof EntityPlayerMP) {
                EntityPlayerMP entityplayer2 = (EntityPlayerMP)entity;
                MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(entityplayer2, dimension, (Teleporter)teleporter);
                LOTRLevelData.getData((EntityPlayer)entityplayer2).addAchievement(LOTRAchievement.leaveUtumno);
                continue;
            }
            LOTRMod.transferEntityToDimension(entity, dimension, teleporter);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
}

