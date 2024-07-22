/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRPacketWeaponFX
implements IMessage {
    private Type type;
    private int entityID;

    public LOTRPacketWeaponFX() {
    }

    public LOTRPacketWeaponFX(Type t, Entity entity) {
        this.type = t;
        this.entityID = entity.getEntityId();
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.type.ordinal());
        data.writeInt(this.entityID);
    }

    public void fromBytes(ByteBuf data) {
        byte typeID = data.readByte();
        this.type = Type.values()[typeID];
        this.entityID = data.readInt();
    }

    public static enum Type {
        MACE_SAURON,
        STAFF_GANDALF_WHITE,
        FIREBALL_GANDALF_WHITE,
        INFERNAL,
        CHILLING;

    }

    public static class Handler
    implements IMessageHandler<LOTRPacketWeaponFX, IMessage> {
        public IMessage onMessage(LOTRPacketWeaponFX packet, MessageContext context) {
            block4: {
                Entity entity;
                double x;
                Random rand;
                double z;
                double y;
                block8: {
                    World world;
                    block7: {
                        block6: {
                            block5: {
                                world = LOTRMod.proxy.getClientWorld();
                                entity = world.getEntityByID(packet.entityID);
                                if (entity == null) break block4;
                                x = entity.posX;
                                y = entity.boundingBox.minY;
                                z = entity.posZ;
                                rand = world.rand;
                                if (packet.type != Type.MACE_SAURON) break block5;
                                for (int i = 0; i < 360; i += 2) {
                                    float angle = (float)Math.toRadians(i);
                                    double dist = 1.5;
                                    double d = dist * (double)MathHelper.sin((float)angle);
                                    double d1 = dist * (double)MathHelper.cos((float)angle);
                                    world.spawnParticle("smoke", x + d, y + 0.1, z + d1, d * 0.2, 0.0, d1 * 0.2);
                                }
                                break block4;
                            }
                            if (packet.type != Type.STAFF_GANDALF_WHITE) break block6;
                            for (int i = 0; i < 360; i += 2) {
                                float angle = (float)Math.toRadians(i);
                                double dist = 1.5;
                                double d = dist * (double)MathHelper.sin((float)angle);
                                double d1 = dist * (double)MathHelper.cos((float)angle);
                                LOTRMod.proxy.spawnParticle("blueFlame", x + d, y + 0.1, z + d1, d * 0.2, 0.0, d1 * 0.2);
                            }
                            break block4;
                        }
                        if (packet.type != Type.FIREBALL_GANDALF_WHITE) break block7;
                        LOTRMod.proxy.spawnParticle("gandalfFireball", x, y, z, 0.0, 0.0, 0.0);
                        break block4;
                    }
                    if (packet.type != Type.INFERNAL) break block8;
                    for (int i = 0; i < 20; ++i) {
                        double d = x;
                        double d1 = y + (double)(entity.height * 0.7f);
                        double d2 = z;
                        float angleXZ = rand.nextFloat() * 3.1415927f * 2.0f;
                        float angleY = rand.nextFloat() * 3.1415927f * 2.0f;
                        float speed = MathHelper.randomFloatClamp((Random)rand, (float)0.1f, (float)0.15f);
                        double d3 = MathHelper.cos((float)angleXZ) * MathHelper.cos((float)angleY) * speed;
                        double d4 = MathHelper.sin((float)angleY) * speed;
                        double d5 = MathHelper.sin((float)angleXZ) * MathHelper.cos((float)angleY) * speed;
                        d4 += 0.15000000596046448;
                        world.spawnParticle("flame", d, d1, d2, d3 += entity.posX - entity.lastTickPosX, d4 += entity.posY - entity.lastTickPosY, d5 += entity.posZ - entity.lastTickPosZ);
                    }
                    break block4;
                }
                if (packet.type != Type.CHILLING) break block4;
                for (int i = 0; i < 40; ++i) {
                    double d = x;
                    double d1 = y + (double)(entity.height * 0.7f);
                    double d2 = z;
                    float angleXZ = rand.nextFloat() * 3.1415927f * 2.0f;
                    float angleY = rand.nextFloat() * 3.1415927f * 2.0f;
                    float speed = MathHelper.randomFloatClamp((Random)rand, (float)0.1f, (float)0.2f);
                    double d3 = MathHelper.cos((float)angleXZ) * MathHelper.cos((float)angleY) * speed;
                    double d4 = MathHelper.sin((float)angleY) * speed;
                    double d5 = MathHelper.sin((float)angleXZ) * MathHelper.cos((float)angleY) * speed;
                    LOTRMod.proxy.spawnParticle("chill", d, d1, d2, d3 += entity.posX - entity.lastTickPosX, d4 += entity.posY - entity.lastTickPosY, d5 += entity.posZ - entity.lastTickPosZ);
                }
            }
            return null;
        }
    }

}

