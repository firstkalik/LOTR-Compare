/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.AnimalChest
 *  net.minecraft.inventory.IInvBasic
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAIHiredRemainStill;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.ai.LOTREntityAIUntamedPanic;
import lotr.common.entity.animal.LOTREntityDeer;
import lotr.common.entity.animal.LOTREntityRabbit;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNPCRideable;
import lotr.common.entity.npc.LOTREntityWargBombardier;
import lotr.common.entity.npc.LOTREntityWargBombardier2;
import lotr.common.entity.npc.LOTREntityWargBombardier4;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemMountArmor;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class LOTREntityWarg
extends LOTREntityNPCRideable
implements IInvBasic {
    private int eatingTick;
    private AnimalChest wargInventory;
    public boolean isStrongOrc = false;

    public LOTREntityWarg(World world) {
        super(world);
        this.setSize(1.5f, 1.7f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIHiredRemainStill(this));
        this.tasks.addTask(2, this.getWargAttackAI());
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIUntamedPanic(this, 1.2));
        this.tasks.addTask(4, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 12.0f, 0.02f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 8.0f, 0.02f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 12.0f, 0.02f));
        this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        int target = this.addTargetTasks(true);
        if (!(this instanceof LOTREntityWargBombardier)) {
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, LOTREntityRabbit.class, 500, false));
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, LOTREntityDeer.class, 1000, false));
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, EntitySheep.class, 1000, false));
        }
        if (!(this instanceof LOTREntityWargBombardier2)) {
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, LOTREntityRabbit.class, 500, false));
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, LOTREntityDeer.class, 1000, false));
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, EntitySheep.class, 1000, false));
        }
        if (!(this instanceof LOTREntityWargBombardier4)) {
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, LOTREntityRabbit.class, 500, false));
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, LOTREntityDeer.class, 1000, false));
            this.targetTasks.addTask(target + 1, (EntityAIBase)new LOTREntityAINearestAttackableTargetBasic(this, EntitySheep.class, 1000, false));
        }
        this.isImmuneToFrost = true;
        this.spawnsInDarkness = true;
        this.setupWargInventory();
    }

    public EntityAIBase getWargAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.6, false);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)0);
        this.dataWatcher.addObject(19, (Object)0);
        this.dataWatcher.addObject(20, (Object)0);
        if (this.rand.nextInt(500) == 0) {
            this.setWargType(WargType.WHITE);
        } else if (this.rand.nextInt(20) == 0) {
            this.setWargType(WargType.BLACK);
        } else if (this.rand.nextInt(15) == 0) {
            this.setWargType(WargType.BROWNDARK);
        } else if (this.rand.nextInt(3) == 0) {
            this.setWargType(WargType.GREYDARK);
        } else if (this.rand.nextInt(3) == 0) {
            this.setWargType(WargType.GREY);
        } else {
            this.setWargType(WargType.BROWN);
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)20, (int)32));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22);
        this.getEntityAttribute(npcAttackDamage).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)3, (int)5));
    }

    @Override
    public boolean isMountSaddled() {
        return this.dataWatcher.getWatchableObjectByte(18) == 1;
    }

    public void setWargSaddled(boolean flag) {
        this.dataWatcher.updateObject(18, (Object)(flag ? (byte)1 : 0));
    }

    public WargType getWargType() {
        byte i = this.dataWatcher.getWatchableObjectByte(19);
        return WargType.forID(i);
    }

    public void setWargType(WargType w) {
        this.dataWatcher.updateObject(19, (Object)((byte)w.wargID));
    }

    public ItemStack getWargArmorWatched() {
        int ID = this.dataWatcher.getWatchableObjectInt(20);
        return new ItemStack(Item.getItemById((int)ID));
    }

    @Override
    public String getMountArmorTexture() {
        ItemStack armor = this.getWargArmorWatched();
        if (armor != null && armor.getItem() instanceof LOTRItemMountArmor) {
            return ((LOTRItemMountArmor)armor.getItem()).getArmorTexture();
        }
        return null;
    }

    private void setWargArmorWatched(ItemStack itemstack) {
        if (itemstack == null) {
            this.dataWatcher.updateObject(20, (Object)0);
        } else {
            this.dataWatcher.updateObject(20, (Object)Item.getIdFromItem((Item)itemstack.getItem()));
        }
    }

    @Override
    public IInventory getMountInventory() {
        return this.wargInventory;
    }

    @Override
    public IEntityLivingData initCreatureForHire(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        return data;
    }

    public abstract LOTREntityNPC createWargRider();

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (!this.worldObj.isRemote && this.canWargBeRidden() && this.rand.nextInt(3) == 0) {
            LOTREntityNPC rider = this.createWargRider();
            rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0f);
            rider.onSpawnWithEgg(null);
            rider.isNPCPersistent = this.isNPCPersistent;
            this.worldObj.spawnEntityInWorld((Entity)rider);
            rider.mountEntity((Entity)this);
        }
        return data;
    }

    public boolean canWargBeRidden() {
        return true;
    }

    @Override
    public boolean getBelongsToNPC() {
        return false;
    }

    @Override
    public void setBelongsToNPC(boolean flag) {
    }

    private void setupWargInventory() {
        AnimalChest prevInv = this.wargInventory;
        this.wargInventory = new AnimalChest("WargInv", 2);
        this.wargInventory.func_110133_a(this.getCommandSenderName());
        if (prevInv != null) {
            prevInv.func_110132_b((IInvBasic)this);
            int invSize = Math.min(prevInv.getSizeInventory(), this.wargInventory.getSizeInventory());
            for (int slot = 0; slot < invSize; ++slot) {
                ItemStack itemstack = prevInv.getStackInSlot(slot);
                if (itemstack == null) continue;
                this.wargInventory.setInventorySlotContents(slot, itemstack.copy());
            }
            prevInv = null;
        }
        this.wargInventory.func_110134_a((IInvBasic)this);
        this.checkWargInventory();
    }

    private void checkWargInventory() {
        if (!this.worldObj.isRemote) {
            this.setWargSaddled(this.wargInventory.getStackInSlot(0) != null);
            this.setWargArmorWatched(this.getWargArmor());
        }
    }

    public void onInventoryChanged(InventoryBasic inv) {
        boolean prevSaddled = this.isMountSaddled();
        ItemStack prevArmor = this.getWargArmorWatched();
        this.checkWargInventory();
        ItemStack wargArmor = this.getWargArmorWatched();
        if (this.ticksExisted > 20) {
            if (!prevSaddled && this.isMountSaddled()) {
                this.playSound("mob.horse.leather", 0.5f, 1.0f);
            }
            if (!ItemStack.areItemStacksEqual((ItemStack)prevArmor, (ItemStack)wargArmor)) {
                this.playSound("mob.horse.armor", 0.5f, 1.0f);
            }
        }
    }

    public void setWargArmor(ItemStack itemstack) {
        this.wargInventory.setInventorySlotContents(1, itemstack);
        this.setupWargInventory();
        this.setWargArmorWatched(this.getWargArmor());
    }

    public ItemStack getWargArmor() {
        return this.wargInventory.getStackInSlot(1);
    }

    public int getTotalArmorValue() {
        ItemStack itemstack = this.getWargArmor();
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemMountArmor) {
            LOTRItemMountArmor armor = (LOTRItemMountArmor)itemstack.getItem();
            return armor.getDamageReduceAmount();
        }
        return 0;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setByte("WargType", (byte)this.getWargType().wargID);
        if (this.wargInventory.getStackInSlot(0) != null) {
            nbt.setTag("WargSaddleItem", (NBTBase)this.wargInventory.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
        }
        if (this.getWargArmor() != null) {
            nbt.setTag("WargArmorItem", (NBTBase)this.getWargArmor().writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        ItemStack wargArmor;
        super.readEntityFromNBT(nbt);
        this.setWargType(WargType.forID(nbt.getByte("WargType")));
        if (nbt.hasKey("WargSaddleItem")) {
            ItemStack saddle = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt.getCompoundTag("WargSaddleItem"));
            if (saddle != null && saddle.getItem() == Items.saddle) {
                this.wargInventory.setInventorySlotContents(0, saddle);
            }
        } else if (nbt.getBoolean("Saddled")) {
            this.wargInventory.setInventorySlotContents(0, new ItemStack(Items.saddle));
        }
        if (nbt.hasKey("WargArmorItem") && (wargArmor = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt.getCompoundTag("WargArmorItem"))) != null && this.isMountArmorValid(wargArmor)) {
            this.wargInventory.setInventorySlotContents(1, wargArmor);
        }
        this.checkWargInventory();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            EntityPlayer entityplayer;
            if (this.riddenByEntity instanceof EntityPlayer) {
                entityplayer = (EntityPlayer)this.riddenByEntity;
                if (LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) < 50.0f) {
                    entityplayer.mountEntity(null);
                } else if (this.isNPCTamed() && this.isMountSaddled()) {
                    LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.rideWarg);
                }
            }
            if (this.isNPCTamed() && this.isMountSaddled() && this.riddenByEntity instanceof EntityPlayer) {
                entityplayer = (EntityPlayer)this.riddenByEntity;
                if (this.fallDistance >= 20.0f) {
                    LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.flyingWarg);
                }
            }
            if (!(this.riddenByEntity instanceof LOTREntityNPC) && this.riddenByEntity != null && LOTRBannerProtection.isProtected(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, LOTRBannerProtection.forPlayer((EntityPlayer)this.riddenByEntity, LOTRBannerProtection.Permission.FOOD), true)) {
                this.riddenByEntity.mountEntity(null);
            }
            if (this.isStrongOrc) {
                boolean flag;
                int i = MathHelper.floor_double((double)this.posX);
                int j = MathHelper.floor_double((double)this.boundingBox.minY);
                int k = MathHelper.floor_double((double)this.posZ);
                BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
                boolean bl = flag = this.worldObj.isDaytime() && this.worldObj.canBlockSeeTheSky(i, j, k);
                if (biome instanceof LOTRBiome && ((LOTRBiome)biome).canSpawnHostilesInDay()) {
                    flag = true;
                }
                if (flag && this.ticksExisted % 20 == 0) {
                    this.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 0));
                    this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 0));
                    this.addPotionEffect(new PotionEffect(Potion.invisibility.id, 200, 0));
                    this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0));
                }
            }
            if (this.eatingTick > 0) {
                if (this.eatingTick % 4 == 0) {
                    this.worldObj.playSoundAtEntity((Entity)this, "random.eat", 0.5f + 0.5f * (float)this.rand.nextInt(2), 0.4f + this.rand.nextFloat() * 0.2f);
                }
                --this.eatingTick;
            }
        }
    }

    @Override
    public boolean interact(EntityPlayer entityplayer) {
        boolean hasRequiredAlignment;
        if (!this.worldObj.isRemote && LOTRBannerProtection.isProtected(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, LOTRBannerProtection.forPlayer(entityplayer, LOTRBannerProtection.Permission.FOOD), true)) {
            return true;
        }
        if (this.hiredNPCInfo.isActive) {
            return true;
        }
        boolean bl = hasRequiredAlignment = LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 50.0f;
        if (!this.isChild() && this.canWargBeRidden()) {
            if (this.riddenByEntity == null) {
                if (hasRequiredAlignment) {
                    ItemStack itemstack = entityplayer.inventory.getCurrentItem();
                    if (itemstack != null && itemstack.interactWithEntity(entityplayer, (EntityLivingBase)this)) {
                        return true;
                    }
                    if (!this.worldObj.isRemote) {
                        entityplayer.mountEntity((Entity)this);
                    }
                    this.setAttackTarget(null);
                    this.getNavigator().clearPathEntity();
                    return true;
                }
                LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, 50.0f, this.getFaction());
                return true;
            }
            if (this.riddenByEntity == entityplayer) {
                if (!this.worldObj.isRemote) {
                    entityplayer.mountEntity(null);
                }
                return true;
            }
        }
        return super.interact(entityplayer);
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int furs = 1 + this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int l = 0; l < furs; ++l) {
            this.dropItem(LOTRMod.fur, 1);
        }
        int bones = 2 + this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(LOTRMod.wargBone, 1);
        }
        if (flag) {
            int rugChance = 50 - i * 8;
            if (this.rand.nextInt(rugChance = Math.max(rugChance, 1)) == 0) {
                this.entityDropItem(new ItemStack(LOTRMod.wargskinRug, 1, this.getWargType().wargID), 0.0f);
            }
        }
    }

    @Override
    public boolean canDropRares() {
        return false;
    }

    public String getLivingSound() {
        return "lotr:warg.say";
    }

    public String getHurtSound() {
        return "lotr:warg.hurt";
    }

    public String getDeathSound() {
        return "lotr:warg.death";
    }

    @Override
    public String getAttackSound() {
        return "lotr:warg.attack";
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote) {
            if (this.getBelongsToNPC()) {
                this.wargInventory.setInventorySlotContents(0, null);
                this.wargInventory.setInventorySlotContents(1, null);
            }
            if (this.isNPCTamed()) {
                this.setWargSaddled(false);
                this.dropItem(Items.saddle, 1);
                ItemStack wargArmor = this.getWargArmor();
                if (wargArmor != null) {
                    this.entityDropItem(wargArmor, 0.0f);
                    this.setWargArmor(null);
                }
            }
        }
    }

    public float getTailRotation() {
        float f = (this.getMaxHealth() - this.getHealth()) / this.getMaxHealth();
        return f * -1.2f;
    }

    @Override
    public boolean allowLeashing() {
        return this.isNPCTamed();
    }

    @Override
    public boolean canReEquipHired(int slot, ItemStack itemstack) {
        return false;
    }

    public static enum WargType {
        BROWN(0),
        GREY(1),
        BLACK(2),
        WHITE(3),
        ICE(4),
        OBSIDIAN(5),
        FIRE(6),
        WHICE(7),
        BROWNDARK(8),
        GREYDARK(9);

        public final int wargID;

        private WargType(int i) {
            this.wargID = i;
        }

        public String textureName() {
            return this.name().toLowerCase();
        }

        public static WargType forID(int ID) {
            for (WargType w : WargType.values()) {
                if (w.wargID != ID) continue;
                return w;
            }
            return BROWN;
        }

        public static String[] wargTypeNames() {
            String[] names = new String[WargType.values().length];
            for (int i = 0; i < names.length; ++i) {
                names[i] = WargType.values()[i].textureName();
            }
            return names;
        }
    }

}

