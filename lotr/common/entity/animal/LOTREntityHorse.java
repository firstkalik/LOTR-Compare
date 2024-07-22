/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.AnimalChest
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.animal;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRMod;
import lotr.common.LOTRReflection;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTREntityUtils;
import lotr.common.entity.ai.LOTREntityAIHiredHorseRemainStill;
import lotr.common.entity.ai.LOTREntityAIHorseFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAIHorseMoveToRiderTarget;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.item.LOTRItemMountArmor;
import lotr.common.world.biome.LOTRBiomeGenDorEnErnil;
import lotr.common.world.biome.LOTRBiomeGenRohan;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityHorse
extends EntityHorse
implements LOTRNPCMount {
    private boolean isMoving;
    private ItemStack prevMountArmor;
    private EntityAIBase attackAI;
    private EntityAIBase panicAI;
    private boolean prevIsChild = true;

    public LOTREntityHorse(World world) {
        super(world);
        this.tasks.addTask(0, (EntityAIBase)new LOTREntityAIHiredHorseRemainStill(this));
        this.tasks.addTask(0, (EntityAIBase)new LOTREntityAIHorseMoveToRiderTarget(this));
        this.tasks.addTask(0, (EntityAIBase)new LOTREntityAIHorseFollowHiringPlayer(this));
        EntityAITasks.EntityAITaskEntry panic = LOTREntityUtils.removeAITask((EntityCreature)this, EntityAIPanic.class);
        this.tasks.addTask(panic.priority, panic.action);
        this.panicAI = panic.action;
        this.attackAI = this.createMountAttackAI();
        if (this.isMountHostile()) {
            this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        }
    }

    protected EntityAIBase createMountAttackAI() {
        return null;
    }

    protected boolean isMountHostile() {
        return false;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(25, (Object)0);
        this.dataWatcher.addObject(26, (Object)1);
        this.dataWatcher.addObject(27, (Object)0);
        this.dataWatcher.addObject(28, (Object)0);
        this.dataWatcher.addObject(29, (Object)0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        if (this.isMountHostile()) {
            this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        }
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        if (!this.worldObj.isRemote) {
            data = super.onSpawnWithEgg(data);
            this.onLOTRHorseSpawn();
            this.setHealth(this.getMaxHealth());
            return data;
        }
        int j = this.rand.nextInt(7);
        int k = this.rand.nextInt(5);
        int i = j | k << 8;
        this.setHorseVariant(i);
        return data;
    }

    protected void onLOTRHorseSpawn() {
        int i = MathHelper.floor_double((double)this.posX);
        int k = MathHelper.floor_double((double)this.posZ);
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
        if (this.getClass() == LOTREntityHorse.class) {
            float healthBoost = 0.0f;
            float speedBoost = 0.0f;
            float jumpAdd = 0.0f;
            if (biome instanceof LOTRBiomeGenRohan) {
                healthBoost = 0.5f;
                speedBoost = 0.3f;
                jumpAdd = 0.2f;
            }
            if (biome instanceof LOTRBiomeGenDorEnErnil) {
                healthBoost = 0.3f;
                speedBoost = 0.2f;
                jumpAdd = 0.1f;
            }
            if (healthBoost > 0.0f) {
                double maxHealth = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
                this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth *= (double)(1.0f + this.rand.nextFloat() * healthBoost));
                this.setHealth(this.getMaxHealth());
            }
            if (speedBoost > 0.0f) {
                double movementSpeed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
                this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(movementSpeed *= (double)(1.0f + this.rand.nextFloat() * speedBoost));
            }
            double jumpStrength = this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).getAttributeValue();
            double jumpLimit = Math.max(jumpStrength, 1.0);
            if (jumpAdd > 0.0f) {
                jumpStrength += (double)jumpAdd;
            }
            jumpStrength = Math.min(jumpStrength, jumpLimit);
            this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).setBaseValue(jumpStrength);
        }
    }

    @Override
    public boolean getBelongsToNPC() {
        return this.dataWatcher.getWatchableObjectByte(25) == 1;
    }

    protected void dropFewItems(boolean flag, int i) {
        int meats = this.rand.nextInt(4) + this.rand.nextInt(1 + i);
        for (int l = 0; l < meats; ++l) {
            if (this.isBurning()) {
                this.dropItem(LOTRMod.horseCooked, 1);
                continue;
            }
            this.dropItem(LOTRMod.horseRaw, 1);
        }
    }

    @Override
    public void setBelongsToNPC(boolean flag) {
        this.dataWatcher.updateObject(25, (Object)(flag ? (byte)1 : 0));
        if (flag) {
            this.setHorseTamed(true);
            this.setHorseSaddled(true);
            if (this.getGrowingAge() < 0) {
                this.setGrowingAge(0);
            }
            if (this.getClass() == LOTREntityHorse.class) {
                this.setHorseType(0);
            }
        }
    }

    public boolean getMountable() {
        return this.dataWatcher.getWatchableObjectByte(26) == 1;
    }

    public void setMountable(boolean flag) {
        this.dataWatcher.updateObject(26, (Object)(flag ? (byte)1 : 0));
    }

    public ItemStack getMountArmor() {
        int ID = this.dataWatcher.getWatchableObjectInt(27);
        byte meta = this.dataWatcher.getWatchableObjectByte(28);
        return new ItemStack(Item.getItemById((int)ID), 1, (int)meta);
    }

    @Override
    public String getMountArmorTexture() {
        ItemStack armor = this.getMountArmor();
        if (armor != null && armor.getItem() instanceof LOTRItemMountArmor) {
            return ((LOTRItemMountArmor)armor.getItem()).getArmorTexture();
        }
        return null;
    }

    private void setMountArmorWatched(ItemStack itemstack) {
        if (itemstack == null) {
            this.dataWatcher.updateObject(27, (Object)0);
            this.dataWatcher.updateObject(28, (Object)0);
        } else {
            this.dataWatcher.updateObject(27, (Object)Item.getIdFromItem((Item)itemstack.getItem()));
            this.dataWatcher.updateObject(28, (Object)((byte)itemstack.getItemDamage()));
        }
    }

    public boolean isMountEnraged() {
        return this.dataWatcher.getWatchableObjectByte(29) == 1;
    }

    public void setMountEnraged(boolean flag) {
        this.dataWatcher.updateObject(29, (Object)(flag ? (byte)1 : 0));
    }

    @Override
    public boolean isMountSaddled() {
        return this.isHorseSaddled();
    }

    public boolean isHorseSaddled() {
        return (!this.isMoving || !this.getBelongsToNPC()) && super.isHorseSaddled();
    }

    public void saddleMountForWorldGen() {
        this.setGrowingAge(0);
        LOTRReflection.getHorseInv(this).setInventorySlotContents(0, new ItemStack(Items.saddle));
        LOTRReflection.setupHorseInv(this);
        this.setHorseTamed(true);
    }

    public void setChestedForWorldGen() {
        this.setChested(true);
        LOTRReflection.setupHorseInv(this);
    }

    public void setMountArmor(ItemStack itemstack) {
        LOTRReflection.getHorseInv(this).setInventorySlotContents(1, itemstack);
        LOTRReflection.setupHorseInv(this);
        this.setMountArmorWatched(itemstack);
    }

    @Override
    public boolean isMountArmorValid(ItemStack itemstack) {
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemMountArmor) {
            LOTRItemMountArmor armor = (LOTRItemMountArmor)itemstack.getItem();
            return armor.isValid(this);
        }
        return false;
    }

    public int getTotalArmorValue() {
        ItemStack itemstack = LOTRReflection.getHorseInv(this).getStackInSlot(1);
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemMountArmor) {
            LOTRItemMountArmor armor = (LOTRItemMountArmor)itemstack.getItem();
            return armor.getDamageReduceAmount();
        }
        return 0;
    }

    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            ItemStack armor = LOTRReflection.getHorseInv(this).getStackInSlot(1);
            if (this.ticksExisted > 20 && !ItemStack.areItemStacksEqual((ItemStack)this.prevMountArmor, (ItemStack)armor)) {
                this.playSound("mob.horse.armor", 0.5f, 1.0f);
            }
            this.prevMountArmor = armor;
            this.setMountArmorWatched(armor);
            if (this.riddenByEntity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)this.riddenByEntity;
                if (LOTRBannerProtection.isProtected(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, LOTRBannerProtection.forPlayer(player), true)) {
                    this.riddenByEntity.mountEntity(null);
                    return;
                }
                if (this.isInWater() && this.motionY < 0.0 && this.worldObj.func_147461_a(this.boundingBox.copy().addCoord(0.0, -1.0, 0.0)).isEmpty() && this.rand.nextFloat() < 0.55f) {
                    this.motionY += 0.05;
                    this.isAirBorne = true;
                }
            }
            if (this.isMountHostile()) {
                EntityLivingBase target;
                boolean isChild = this.isChild();
                if (isChild != this.prevIsChild) {
                    if (isChild) {
                        EntityAITasks.EntityAITaskEntry taskEntry = LOTREntityUtils.removeAITask((EntityCreature)this, this.attackAI.getClass());
                        this.tasks.addTask(taskEntry.priority, this.panicAI);
                    } else {
                        EntityAITasks.EntityAITaskEntry taskEntry = LOTREntityUtils.removeAITask((EntityCreature)this, this.panicAI.getClass());
                        this.tasks.addTask(taskEntry.priority, this.attackAI);
                    }
                }
                if (this.getAttackTarget() != null && (!(target = this.getAttackTarget()).isEntityAlive() || target instanceof EntityPlayer && ((EntityPlayer)target).capabilities.isCreativeMode)) {
                    this.setAttackTarget(null);
                }
                if (this.riddenByEntity instanceof EntityLiving) {
                    target = ((EntityLiving)this.riddenByEntity).getAttackTarget();
                    this.setAttackTarget(target);
                } else if (this.riddenByEntity instanceof EntityPlayer) {
                    this.setAttackTarget(null);
                }
                this.setMountEnraged(this.getAttackTarget() != null);
            }
            this.prevIsChild = this.isChild();
        }
        super.onLivingUpdate();
    }

    protected boolean isMovementBlocked() {
        this.isMoving = true;
        boolean flag = super.isMovementBlocked();
        this.isMoving = false;
        return flag;
    }

    public void moveEntityWithHeading(float f, float f1) {
        this.isMoving = true;
        super.moveEntityWithHeading(f, f1);
        this.isMoving = false;
    }

    @Override
    public void super_moveEntityWithHeading(float strafe, float forward) {
        super.moveEntityWithHeading(strafe, forward);
    }

    public float getBlockPathWeight(int i, int j, int k) {
        if (this.getBelongsToNPC() && this.riddenByEntity instanceof LOTREntityNPC) {
            return ((LOTREntityNPC)this.riddenByEntity).getBlockPathWeight(i, j, k);
        }
        return super.getBlockPathWeight(i, j, k);
    }

    public double getMountedYOffset() {
        double d = (double)this.height * 0.5;
        if (this.riddenByEntity != null) {
            d += (double)this.riddenByEntity.yOffset - this.riddenByEntity.getYOffset();
        }
        return d;
    }

    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack != null && LOTRMod.isOreNameEqual(itemstack, "apple");
    }

    public EntityAgeable createChild(EntityAgeable otherParent) {
        EntityHorse superChild = (EntityHorse)super.createChild(otherParent);
        LOTREntityHorse child = (LOTREntityHorse)EntityList.createEntityByName((String)LOTREntities.getStringFromClass(this.getClass()), (World)this.worldObj);
        child.setHorseType(superChild.getHorseType());
        child.setHorseVariant(superChild.getHorseVariant());
        double maxHealth = this.getChildAttribute((EntityAgeable)this, otherParent, SharedMonsterAttributes.maxHealth, 3.0);
        maxHealth = this.clampChildHealth(maxHealth);
        child.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        child.setHealth(child.getMaxHealth());
        double jumpStrength = this.getChildAttribute((EntityAgeable)this, otherParent, LOTRReflection.getHorseJumpStrength(), 0.1);
        jumpStrength = this.clampChildJump(jumpStrength);
        child.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).setBaseValue(jumpStrength);
        double moveSpeed = this.getChildAttribute((EntityAgeable)this, otherParent, SharedMonsterAttributes.movementSpeed, 0.03);
        moveSpeed = this.clampChildSpeed(moveSpeed);
        child.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(moveSpeed);
        if (this.isTame() && ((LOTREntityHorse)otherParent).isTame()) {
            child.setHorseTamed(true);
        }
        return child;
    }

    private double getChildAttribute(EntityAgeable parent, EntityAgeable otherParent, IAttribute stat, double variance) {
        double d;
        double val1 = parent.getEntityAttribute(stat).getBaseValue();
        double val2 = otherParent.getEntityAttribute(stat).getBaseValue();
        if (val1 <= d) {
            return MathHelper.getRandomDoubleInRange((Random)this.rand, (double)(val1 - variance), (double)(val2 + variance));
        }
        return MathHelper.getRandomDoubleInRange((Random)this.rand, (double)(val2 - variance), (double)(val1 + variance));
    }

    protected double clampChildHealth(double health) {
        return MathHelper.clamp_double((double)health, (double)12.0, (double)48.0);
    }

    protected double clampChildJump(double jump) {
        return MathHelper.clamp_double((double)jump, (double)0.3, (double)1.0);
    }

    protected double clampChildSpeed(double speed) {
        return MathHelper.clamp_double((double)speed, (double)0.08, (double)0.45);
    }

    public boolean interact(EntityPlayer entityplayer) {
        if (!this.worldObj.isRemote && LOTRBannerProtection.isProtected(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, LOTRBannerProtection.forPlayer(entityplayer), true)) {
            return true;
        }
        if (!this.getMountable()) {
            return false;
        }
        if (this.isMountEnraged()) {
            return false;
        }
        if (this.getBelongsToNPC()) {
            if (this.riddenByEntity == null) {
                if (!this.worldObj.isRemote) {
                    entityplayer.addChatMessage((IChatComponent)new ChatComponentTranslation("chat.lotr.mountOwnedByNPC", new Object[0]));
                }
                return true;
            }
            return false;
        }
        ItemStack itemstack = entityplayer.getHeldItem();
        if (itemstack != null && this.isBreedingItem(itemstack) && this.getGrowingAge() == 0 && !this.isInLove() && this.isTame()) {
            if (!entityplayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
                if (itemstack.stackSize <= 0) {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                }
            }
            this.func_146082_f(entityplayer);
            return true;
        }
        boolean prevInLove = this.isInLove();
        boolean flag = super.interact(entityplayer);
        if (this.isInLove() && !prevInLove) {
            this.resetInLove();
        }
        return flag;
    }

    public boolean attackEntityAsMob(Entity entity) {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), f);
        return flag;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        Entity attacker;
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && this.isChild() && this.isMountHostile() && (attacker = damagesource.getEntity()) instanceof EntityLivingBase) {
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(12.0, 12.0, 12.0));
            for (Object element : list) {
                LOTREntityHorse mount;
                Entity entity = (Entity)element;
                if (entity.getClass() != this.getClass() || (mount = (LOTREntityHorse)entity).isChild() || mount.isTame()) continue;
                mount.setAttackTarget((EntityLivingBase)attacker);
            }
        }
        return flag;
    }

    public void openGUI(EntityPlayer entityplayer) {
        if (!this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == entityplayer) && this.isTame()) {
            AnimalChest animalchest = LOTRReflection.getHorseInv(this);
            animalchest.func_110133_a(this.getCommandSenderName());
            entityplayer.openGui((Object)LOTRMod.instance, 29, this.worldObj, this.getEntityId(), animalchest.getSizeInventory(), 0);
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("BelongsNPC", this.getBelongsToNPC());
        nbt.setBoolean("Mountable", this.getMountable());
        AnimalChest inv = LOTRReflection.getHorseInv(this);
        if (inv.getStackInSlot(1) != null) {
            nbt.setTag("LOTRMountArmorItem", (NBTBase)inv.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
        }
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        ItemStack armor;
        super.readEntityFromNBT(nbt);
        boolean pre35 = false;
        if (nbt.hasKey("BelongsToNPC")) {
            pre35 = true;
            this.setBelongsToNPC(nbt.getBoolean("BelongsToNPC"));
        } else {
            this.setBelongsToNPC(nbt.getBoolean("BelongsNPC"));
        }
        if (nbt.hasKey("Mountable")) {
            this.setMountable(nbt.getBoolean("Mountable"));
        }
        AnimalChest inv = LOTRReflection.getHorseInv(this);
        if (nbt.hasKey("LOTRMountArmorItem") && (armor = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt.getCompoundTag("LOTRMountArmorItem"))) != null && this.isMountArmorValid(armor)) {
            inv.setInventorySlotContents(1, armor);
        }
        if (pre35) {
            double d;
            double jumpStrength = this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).getAttributeValue();
            if (d > 1.0) {
                System.out.println("Reducing horse jump strength from " + jumpStrength);
                jumpStrength = 1.0;
                this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).setBaseValue(jumpStrength);
                System.out.println("Jump strength now " + this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).getAttributeValue());
            }
        }
    }

    public boolean canDespawn() {
        return this.getBelongsToNPC() && this.riddenByEntity == null;
    }

    public void onDeath(DamageSource damagesource) {
        if (this.getBelongsToNPC()) {
            AnimalChest inv = LOTRReflection.getHorseInv(this);
            inv.setInventorySlotContents(0, null);
            inv.setInventorySlotContents(1, null);
        }
        super.onDeath(damagesource);
    }

    public String getCommandSenderName() {
        if (this.getClass() == LOTREntityHorse.class) {
            return super.getCommandSenderName();
        }
        if (this.hasCustomNameTag()) {
            return this.getCustomNameTag();
        }
        String s = EntityList.getEntityString((Entity)this);
        return StatCollector.translateToLocal((String)("entity." + s + ".name"));
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }

    public boolean allowLeashing() {
        if (this.getBelongsToNPC()) {
            return false;
        }
        return super.allowLeashing();
    }

    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public float getStepHeightWhileRiddenByPlayer() {
        return 1.0f;
    }
}

