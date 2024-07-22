/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.item.valaringbase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class vana
extends valaringbase {
    public static int cooldown = 10;
    public static int defaultCharges = 200;

    public vana() {
        this.setMaxDamage(defaultCharges + 1);
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    public ItemStack onEaten(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        if (!playerEntity.capabilities.isCreativeMode) {
            if (this.isOutOfCharge(srcItemStack)) {
                this.playSound(noChargeAttackSound, world, playerEntity);
                return srcItemStack;
            }
            srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
        }
        world.playSoundAtEntity((Entity)playerEntity, "lotr:ent.step", 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);
        if (!world.isRemote) {
            playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
            float closest = Float.MAX_VALUE;
            Object thisOne = null;
            for (int i = 0; i < world.loadedEntityList.size(); ++i) {
                if (!(((Entity)world.loadedEntityList.get(i)).getDistanceToEntity((Entity)playerEntity) < closest) || !(world.loadedEntityList.get(i) instanceof EntityLiving)) continue;
                closest = ((Entity)world.loadedEntityList.get(i)).getDistanceToEntity((Entity)playerEntity);
                thisOne = world.loadedEntityList.get(i);
            }
            float var4 = 1.0f;
            int a = (int)(playerEntity.prevPosX + (playerEntity.posX - playerEntity.prevPosX) * (double)var4);
            int b = (int)(playerEntity.prevPosY + (playerEntity.posY - playerEntity.prevPosY) * (double)var4 + 1.62 - (double)playerEntity.yOffset);
            int c = (int)(playerEntity.prevPosZ + (playerEntity.posZ - playerEntity.prevPosZ) * (double)var4);
            if (thisOne != null) {
                Vec3 look;
                EntityWolf fireball2;
                if (!world.isRemote) {
                    look = playerEntity.getLookVec();
                    fireball2 = new EntityWolf(world);
                    fireball2.setPosition((double)a, (double)b, (double)c);
                    fireball2.motionX = look.xCoord * 0.15;
                    fireball2.motionY = look.yCoord * 0.15;
                    fireball2.motionZ = look.zCoord * 0.15;
                    world.spawnEntityInWorld((Entity)fireball2);
                    fireball2.setTamed(true);
                    fireball2.setAttackTarget((EntityLivingBase)null);
                    fireball2.setHealth(40.0f);
                    fireball2.func_152115_b(playerEntity.getUniqueID().toString());
                    fireball2.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 2));
                    fireball2.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1200, 2));
                    fireball2.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1200, 2));
                }
                if (!world.isRemote) {
                    look = playerEntity.getLookVec();
                    fireball2 = new EntityWolf(world);
                    fireball2.setPosition((double)a, (double)b, (double)c);
                    fireball2.motionX = look.xCoord * 0.15;
                    fireball2.motionY = look.yCoord * 0.15;
                    fireball2.motionZ = look.zCoord * 0.15;
                    world.spawnEntityInWorld((Entity)fireball2);
                    fireball2.setTamed(true);
                    fireball2.setAttackTarget((EntityLivingBase)null);
                    fireball2.setHealth(40.0f);
                    fireball2.func_152115_b(playerEntity.getUniqueID().toString());
                    fireball2.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 2));
                    fireball2.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1200, 2));
                    fireball2.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1200, 2));
                }
                if (!world.isRemote) {
                    look = playerEntity.getLookVec();
                    fireball2 = new EntityWolf(world);
                    fireball2.setPosition((double)a, (double)b, (double)c);
                    fireball2.motionX = look.xCoord * 0.15;
                    fireball2.motionY = look.yCoord * 0.15;
                    fireball2.motionZ = look.zCoord * 0.15;
                    world.spawnEntityInWorld((Entity)fireball2);
                    fireball2.setTamed(true);
                    fireball2.setAttackTarget((EntityLivingBase)null);
                    fireball2.setHealth(40.0f);
                    fireball2.func_152115_b(playerEntity.getUniqueID().toString());
                    fireball2.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 2));
                    fireball2.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1200, 2));
                    fireball2.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1200, 2));
                }
                if (!world.isRemote) {
                    look = playerEntity.getLookVec();
                    fireball2 = new EntityWolf(world);
                    fireball2.setPosition((double)a, (double)b, (double)c);
                    fireball2.motionX = look.xCoord * 0.15;
                    fireball2.motionY = look.yCoord * 0.15;
                    fireball2.motionZ = look.zCoord * 0.15;
                    world.spawnEntityInWorld((Entity)fireball2);
                    fireball2.setTamed(true);
                    fireball2.setAttackTarget((EntityLivingBase)null);
                    fireball2.setHealth(40.0f);
                    fireball2.func_152115_b(playerEntity.getUniqueID().toString());
                    fireball2.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 2));
                    fireball2.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1200, 2));
                    fireball2.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1200, 2));
                }
                srcItemStack.damageItem(75, (EntityLivingBase)playerEntity);
            }
            LOTRLevelData.getData(playerEntity).addAchievement(LOTRAchievement.useRing);
            playerEntity.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u0421\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c \u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u043d\u0430"));
            srcItemStack.damageItem(1, (EntityLivingBase)playerEntity);
        }
        return srcItemStack;
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }

    public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ, int par7, float par8, float par9, float par10) {
        if (!playerEntity.capabilities.isCreativeMode && this.isOutOfCharge(srcItemStack)) {
            this.playSound(noChargeAttackSound, world, playerEntity);
            return true;
        }
        boolean success = this.growBlock(playerEntity, world, targetX, targetY, targetZ);
        if (success) {
            this.playSound("random.orb", world, playerEntity);
            if (!playerEntity.capabilities.isCreativeMode) {
                srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
            }
        }
        return success;
    }

    protected boolean growBlock(EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ) {
        Block targetBlock = world.getBlock(targetX, targetY, targetZ);
        ItemStack fauxItemStack = new ItemStack(Items.dye, 1, 15);
        if (targetBlock == Blocks.cactus) {
            int y = targetY + 1;
            while (world.getBlock(targetX, y, targetZ) == Blocks.cactus) {
                ++y;
            }
            if (world.isAirBlock(targetX, y, targetZ)) {
                world.setBlock(targetX, y, targetZ, Blocks.cactus);
            }
            return true;
        }
        if (targetBlock == Blocks.reeds) {
            int y = targetY + 1;
            while (world.getBlock(targetX, y, targetZ) == Blocks.reeds) {
                ++y;
            }
            if (world.isAirBlock(targetX, y, targetZ)) {
                world.setBlock(targetX, y, targetZ, Blocks.reeds);
            }
            return true;
        }
        if (targetBlock == Blocks.cobblestone) {
            world.setBlock(targetX, targetY, targetZ, Blocks.mossy_cobblestone);
            return true;
        }
        if (targetBlock == Blocks.stonebrick && world.getBlockMetadata(targetX, targetY, targetZ) == 0) {
            world.setBlockMetadataWithNotify(targetX, targetY, targetZ, 1, 3);
            return true;
        }
        return ItemDye.applyBonemeal((ItemStack)fauxItemStack, (World)world, (int)targetX, (int)targetY, (int)targetZ, (EntityPlayer)playerEntity);
    }
}

