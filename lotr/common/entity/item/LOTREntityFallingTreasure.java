/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockPistonMoving
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.item;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityFallingTreasure
extends Entity
implements IEntityAdditionalSpawnData {
    public Block theBlock;
    public int theBlockMeta;
    private int ticksFalling;

    public LOTREntityFallingTreasure(World world) {
        super(world);
    }

    public LOTREntityFallingTreasure(World world, double d, double d1, double d2, Block block) {
        this(world, d, d1, d2, block, 0);
    }

    public LOTREntityFallingTreasure(World world, double d, double d1, double d2, Block block, int meta) {
        super(world);
        this.blockMetaConstructor(d, d1, d2, block, meta);
    }

    private void blockMetaConstructor(double d, double d1, double d2, Block block, int meta) {
        this.theBlock = block;
        this.theBlockMeta = meta;
        this.preventEntitySpawning = true;
        this.setSize(0.98f, 0.98f);
        this.yOffset = this.height / 2.0f;
        this.setPosition(d, d1, d2);
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        this.prevPosX = d;
        this.prevPosY = d1;
        this.prevPosZ = d2;
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeDouble(this.prevPosX);
        data.writeDouble(this.prevPosY);
        data.writeDouble(this.prevPosZ);
        data.writeInt(Block.getIdFromBlock((Block)this.theBlock));
        data.writeByte(this.theBlockMeta);
    }

    public void readSpawnData(ByteBuf data) {
        double x = data.readDouble();
        double y = data.readDouble();
        double z = data.readDouble();
        Block block = Block.getBlockById((int)data.readInt());
        byte meta = data.readByte();
        this.blockMetaConstructor(x, y, z, block, meta);
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void entityInit() {
    }

    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    public void onUpdate() {
        if (this.theBlock.getMaterial() == Material.air) {
            this.setDead();
        } else {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            ++this.ticksFalling;
            this.motionY -= 0.04;
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.98;
            this.motionY *= 0.98;
            this.motionZ *= 0.98;
            if (!this.worldObj.isRemote) {
                int i = MathHelper.floor_double((double)this.posX);
                int j = MathHelper.floor_double((double)this.posY);
                int k = MathHelper.floor_double((double)this.posZ);
                Block block = this.worldObj.getBlock(i, j, k);
                int meta = this.worldObj.getBlockMetadata(i, j, k);
                if (this.ticksFalling == 1) {
                    if (block != this.theBlock) {
                        this.setDead();
                        return;
                    }
                    this.worldObj.setBlockToAir(i, j, k);
                }
                if (this.onGround) {
                    this.motionX *= 0.7;
                    this.motionZ *= 0.7;
                    this.motionY *= -0.5;
                    if (block != Blocks.piston_extension) {
                        this.setDead();
                        boolean placedTreasure = false;
                        if (block == this.theBlock && meta < 7) {
                            while (this.theBlockMeta >= 0 && meta < 7) {
                                --this.theBlockMeta;
                                ++meta;
                            }
                            this.worldObj.setBlockMetadataWithNotify(i, j, k, meta, 3);
                            placedTreasure = true;
                            ++j;
                        }
                        if (this.theBlockMeta >= 0) {
                            if (this.worldObj.canPlaceEntityOnSide(this.theBlock, i, j, k, true, 1, null, null) && this.worldObj.setBlock(i, j, k, this.theBlock, this.theBlockMeta, 3)) {
                                placedTreasure = true;
                            } else {
                                this.entityDropItem(new ItemStack(this.theBlock, this.theBlock.quantityDropped(this.theBlockMeta, 0, this.rand), this.theBlock.damageDropped(this.theBlockMeta)), 0.0f);
                            }
                        }
                        if (placedTreasure) {
                            Block.SoundType stepSound = this.theBlock.stepSound;
                            this.worldObj.playSoundEffect((double)((float)i + 0.5f), (double)((float)j + 0.5f), (double)((float)k + 0.5f), stepSound.func_150496_b(), (stepSound.getVolume() + 1.0f) / 2.0f, stepSound.getPitch() * 0.8f);
                        }
                    }
                } else if (!(this.ticksFalling <= 100 || this.worldObj.isRemote || j >= 1 && j <= 256 && this.ticksFalling <= 600)) {
                    this.entityDropItem(new ItemStack(this.theBlock, this.theBlock.quantityDropped(this.theBlockMeta, 0, this.rand), this.theBlock.damageDropped(this.theBlockMeta)), 0.0f);
                    this.setDead();
                }
            }
        }
    }

    protected void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setInteger("TileID", Block.getIdFromBlock((Block)this.theBlock));
        nbt.setByte("Data", (byte)this.theBlockMeta);
        nbt.setByte("Time", (byte)this.ticksFalling);
    }

    protected void readEntityFromNBT(NBTTagCompound nbt) {
        this.theBlock = Block.getBlockById((int)nbt.getInteger("TileID"));
        this.theBlockMeta = nbt.getByte("Data") & 0xFF;
        this.ticksFalling = nbt.getByte("Time") & 0xFF;
        if (this.theBlock.getMaterial() == Material.air) {
            this.theBlock = Blocks.sand;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getShadowSize() {
        return 0.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean canRenderOnFire() {
        return false;
    }
}

