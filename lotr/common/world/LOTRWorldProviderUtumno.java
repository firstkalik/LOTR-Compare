/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.chunk.IChunkProvider
 */
package lotr.common.world;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.Random;
import lotr.common.LOTRDimension;
import lotr.common.network.LOTRPacketBlockFX;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.world.LOTRChunkProviderUtumno;
import lotr.common.world.LOTRUtumnoLevel;
import lotr.common.world.LOTRWorldProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class LOTRWorldProviderUtumno
extends LOTRWorldProvider {
    public LOTRWorldProviderUtumno() {
        this.hasNoSky = true;
    }

    @Override
    public LOTRDimension getLOTRDimension() {
        return LOTRDimension.UTUMNO;
    }

    public IChunkProvider createChunkGenerator() {
        return new LOTRChunkProviderUtumno(this.worldObj, this.worldObj.getSeed());
    }

    protected void generateLightBrightnessTable() {
        for (int i = 0; i <= 15; ++i) {
            float f = (float)i / 15.0f;
            this.lightBrightnessTable[i] = (float)Math.pow(f, 4.0);
        }
    }

    @Override
    public float calculateCelestialAngle(long time, float f) {
        return 0.5f;
    }

    @SideOnly(value=Side.CLIENT)
    public double getVoidFogYFactor() {
        return 1.0;
    }

    @Override
    public float[] handleFinalFogColors(EntityLivingBase viewer, double tick, float[] rgb) {
        double y = viewer.prevPosY + (viewer.posY - viewer.prevPosY) * tick;
        LOTRUtumnoLevel level = LOTRUtumnoLevel.forY((int)y);
        if (level == LOTRUtumnoLevel.FIRE) {
            int min = level.getLowestCorridorFloor();
            int max = level.getHighestCorridorRoof();
            max = (int)((double)max - (double)(max - min) * 0.3);
            double yFactor = (y - (double)min) / (double)(max - min);
            yFactor = MathHelper.clamp_double((double)yFactor, (double)0.0, (double)1.0);
            yFactor = 1.0 - yFactor;
            Color clr = new Color(rgb[0], rgb[1], rgb[2]);
            float[] hsb = Color.RGBtoHSB(clr.getRed(), clr.getGreen(), clr.getBlue(), null);
            float br = hsb[2];
            if ((double)br > 0.0) {
                hsb[2] = br = (float)((double)br + (double)(1.0f - br) * yFactor);
            }
            rgb = new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2])).getRGBComponents(null);
        }
        return rgb;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    public int getRespawnDimension(EntityPlayerMP entityplayer) {
        return LOTRDimension.MIDDLE_EARTH.dimensionID;
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public int getActualHeight() {
        return this.getHeight();
    }

    public void setSpawnPoint(int i, int j, int k) {
    }

    public static void doEvaporateFX(World world, int i, int j, int k) {
        if (!world.isRemote) {
            world.playSoundEffect((double)((float)i + 0.5f), (double)((float)j + 0.5f), (double)((float)k + 0.5f), "random.fizz", 0.5f, 2.6f + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8f);
            LOTRPacketBlockFX pkt = new LOTRPacketBlockFX(LOTRPacketBlockFX.Type.UTUMNO_EVAPORATE, i, j, k);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)pkt, new NetworkRegistry.TargetPoint(world.provider.dimensionId, (double)i + 0.5, (double)j + 0.5, (double)k + 0.5, 32.0));
        }
    }

    public static interface UtumnoBlock {
    }

}

