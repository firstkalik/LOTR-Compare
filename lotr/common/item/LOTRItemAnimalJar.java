/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.block.LOTRBlockAnimalJar;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.animal.LOTREntityButterfly;
import lotr.common.item.LOTRItemBlockMetadata;
import lotr.common.tileentity.LOTRTileEntityAnimalJar;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LOTRItemAnimalJar
extends LOTRItemBlockMetadata {
    public LOTRItemAnimalJar(Block block) {
        super(block);
        this.setMaxStackSize(1);
    }

    public static NBTTagCompound getEntityData(ItemStack itemstack) {
        if (itemstack.hasTagCompound()) {
            NBTTagCompound nbt;
            if (itemstack.getTagCompound().hasKey("LOTRButterfly")) {
                nbt = itemstack.getTagCompound().getCompoundTag("LOTRButterfly");
                if (!nbt.hasNoTags()) {
                    nbt.setString("id", LOTREntities.getStringFromClass(LOTREntityButterfly.class));
                    LOTRItemAnimalJar.setEntityData(itemstack, (NBTTagCompound)nbt.copy());
                }
                itemstack.getTagCompound().removeTag("LOTRButterfly");
            }
            if (itemstack.getTagCompound().hasKey("JarEntity") && !(nbt = itemstack.getTagCompound().getCompoundTag("JarEntity")).hasNoTags()) {
                return nbt;
            }
        }
        return null;
    }

    public static void setEntityData(ItemStack itemstack, NBTTagCompound nbt) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        if (nbt == null) {
            itemstack.getTagCompound().removeTag("JarEntity");
        } else {
            itemstack.getTagCompound().setTag("JarEntity", (NBTBase)nbt);
        }
    }

    public static Entity getItemJarEntity(ItemStack itemstack, World world) {
        NBTTagCompound nbt = LOTRItemAnimalJar.getEntityData(itemstack);
        if (nbt != null) {
            Entity jarEntity = EntityList.createEntityFromNBT((NBTTagCompound)nbt, (World)world);
            return jarEntity;
        }
        return null;
    }

    public boolean placeBlockAt(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float f, float f1, float f2, int metadata) {
        if (super.placeBlockAt(itemstack, entityplayer, world, i, j, k, side, f, f1, f2, metadata)) {
            TileEntity tileentity = world.getTileEntity(i, j, k);
            if (tileentity instanceof LOTRTileEntityAnimalJar) {
                LOTRTileEntityAnimalJar jar = (LOTRTileEntityAnimalJar)tileentity;
                jar.setEntityData(LOTRItemAnimalJar.getEntityData(itemstack));
            }
            return true;
        }
        return false;
    }

    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer entityplayer, EntityLivingBase entity) {
        itemstack = entityplayer.getCurrentEquippedItem();
        World world = entityplayer.worldObj;
        LOTRBlockAnimalJar jarBlock = (LOTRBlockAnimalJar)this.field_150939_a;
        if (jarBlock.canCapture((Entity)entity) && LOTRItemAnimalJar.getEntityData(itemstack) == null) {
            NBTTagCompound nbt;
            if (!world.isRemote && entity.writeToNBTOptional(nbt = new NBTTagCompound())) {
                LOTRItemAnimalJar.setEntityData(itemstack, nbt);
                entity.playSound("random.pop", 0.5f, 0.5f + world.rand.nextFloat() * 0.5f);
                entity.setDead();
                if (entity instanceof LOTREntityButterfly) {
                    LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.catchButterfly);
                }
            }
            return true;
        }
        return false;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        Entity jarEntity;
        if (!world.isRemote && (jarEntity = LOTRItemAnimalJar.getItemJarEntity(itemstack, world)) != null) {
            double x = entityplayer.posX;
            double y = entityplayer.boundingBox.minY + (double)entityplayer.getEyeHeight();
            double z = entityplayer.posZ;
            Vec3 look = entityplayer.getLookVec();
            float length = 2.0f;
            jarEntity.setLocationAndAngles(x += look.xCoord * (double)length, y += look.yCoord * (double)length, z += look.zCoord * (double)length, world.rand.nextFloat(), 0.0f);
            world.spawnEntityInWorld(jarEntity);
            jarEntity.playSound("random.pop", 0.5f, 0.5f + world.rand.nextFloat() * 0.5f);
            LOTRItemAnimalJar.setEntityData(itemstack, null);
        }
        return itemstack;
    }
}

