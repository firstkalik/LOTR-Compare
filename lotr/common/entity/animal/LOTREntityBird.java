/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.entity.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockBerryBush;
import lotr.common.block.LOTRBlockBerryBush2;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.LOTRScarecrows;
import lotr.common.entity.animal.LOTRAmbientCreature;
import lotr.common.entity.animal.LOTRAmbientSpawnChecks;
import lotr.common.inventory.LOTREntityInventory;
import lotr.common.item.D1;
import lotr.common.item.D2;
import lotr.common.item.D3;
import lotr.common.item.D4;
import lotr.common.item.D5;
import lotr.common.item.D6;
import lotr.common.item.D7;
import lotr.common.item.H1;
import lotr.common.item.LOTRValuableItems;
import lotr.common.item.Naria;
import lotr.common.item.Nenia;
import lotr.common.item.Thorin;
import lotr.common.item.Vilia;
import lotr.common.item.arven;
import lotr.common.item.aule;
import lotr.common.item.bright;
import lotr.common.item.elrond;
import lotr.common.item.elrondsilver;
import lotr.common.item.este;
import lotr.common.item.farin;
import lotr.common.item.haldir;
import lotr.common.item.irmo;
import lotr.common.item.khain;
import lotr.common.item.kibil;
import lotr.common.item.lesserfire;
import lotr.common.item.lesserivisible;
import lotr.common.item.lesserjump;
import lotr.common.item.lesserlight;
import lotr.common.item.lessermining;
import lotr.common.item.lessernightvision;
import lotr.common.item.lesserpower;
import lotr.common.item.lesserresistance;
import lotr.common.item.lessersaturation;
import lotr.common.item.lesserspeed;
import lotr.common.item.lesserstrenght;
import lotr.common.item.lessersuicide;
import lotr.common.item.lesserwatherbreathing;
import lotr.common.item.light;
import lotr.common.item.linhir;
import lotr.common.item.manve;
import lotr.common.item.melkor;
import lotr.common.item.melkor2;
import lotr.common.item.namo;
import lotr.common.item.narchuil;
import lotr.common.item.nessa;
import lotr.common.item.nienna;
import lotr.common.item.numenor;
import lotr.common.item.orome;
import lotr.common.item.ringBarachir;
import lotr.common.item.ringShaman;
import lotr.common.item.ringSmithing;
import lotr.common.item.sarumanring;
import lotr.common.item.theOneRing;
import lotr.common.item.thorinrune;
import lotr.common.item.thranduilmithril;
import lotr.common.item.thranduilsilver;
import lotr.common.item.thranduilsnake;
import lotr.common.item.tulkas;
import lotr.common.item.ulmo;
import lotr.common.item.vaire;
import lotr.common.item.vana;
import lotr.common.item.varda;
import lotr.common.item.yavanna;
import lotr.common.world.biome.LOTRBiomeGenFarHarad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTREntityBird
extends EntityLiving
implements LOTRAmbientCreature,
LOTRRandomSkinEntity {
    private ChunkCoordinates currentFlightTarget;
    private int flightTargetTime = 0;
    public int flapTime = 0;
    private LOTREntityInventory birdInv = new LOTREntityInventory("BirdItems", (EntityLivingBase)this, 9);
    private EntityItem stealTargetItem;
    private EntityPlayer stealTargetPlayer;
    private int stolenTime = 0;
    private boolean stealingCrops = false;

    public LOTREntityBird(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
        this.tasks.addTask(0, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 12.0f, 0.05f));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 12.0f, 0.1f));
        this.tasks.addTask(2, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
        this.dataWatcher.addObject(17, (Object)1);
    }

    public BirdType getBirdType() {
        byte i = this.dataWatcher.getWatchableObjectByte(16);
        if (i < 0 || i >= BirdType.values().length) {
            i = 0;
        }
        return BirdType.values()[i];
    }

    public void setBirdType(BirdType type) {
        this.setBirdType(type.ordinal());
    }

    public void setBirdType(int i) {
        this.dataWatcher.updateObject(16, (Object)((byte)i));
    }

    public boolean isBirdStill() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setBirdStill(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)(flag ? (byte)1 : 0));
    }

    public String getBirdTextureDir() {
        return this.getBirdType().textureDir;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(MathHelper.getRandomDoubleInRange((Random)this.rand, (double)0.08, (double)0.13));
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = MathHelper.floor_double((double)this.posX);
        MathHelper.floor_double((double)this.posY);
        int k = MathHelper.floor_double((double)this.posZ);
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
        if (biome instanceof LOTRBiomeGenFarHarad) {
            if (this.rand.nextInt(8) == 0) {
                this.setBirdType(BirdType.CROW);
            } else {
                this.setBirdType(BirdType.FAR_HARAD);
            }
        } else if (this.rand.nextInt(6) == 0) {
            this.setBirdType(BirdType.CROW);
        } else if (this.rand.nextInt(10) == 0) {
            this.setBirdType(BirdType.MAGPIE);
        } else {
            this.setBirdType(BirdType.COMMON);
        }
        return data;
    }

    @Override
    public void setUniqueID(UUID uuid) {
        this.entityUniqueID = uuid;
    }

    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity entity) {
    }

    protected void collideWithNearbyEntities() {
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected boolean canStealItems() {
        return this.getBirdType().canSteal;
    }

    protected boolean isStealable(ItemStack itemstack) {
        BirdType type = this.getBirdType();
        Item item = itemstack.getItem();
        if (type == BirdType.COMMON) {
            return item instanceof IPlantable && ((IPlantable)item).getPlantType((IBlockAccess)this.worldObj, -1, -1, -1) == EnumPlantType.Crop;
        }
        if (type == BirdType.CROW) {
            if (item instanceof ItemFood || LOTRMod.isOreNameEqual(itemstack, "bone")) {
                return true;
            }
            Class[] stealableItems = new Class[]{theOneRing.class, ringBarachir.class, ringShaman.class, ringSmithing.class, Vilia.class, Nenia.class, Naria.class, D1.class, D2.class, D3.class, D4.class, D5.class, D6.class, D7.class, H1.class, sarumanring.class, elrond.class, elrondsilver.class, narchuil.class, numenor.class, Thorin.class, thorinrune.class, arven.class, thranduilsilver.class, thranduilsnake.class, thranduilmithril.class, lesserfire.class, lesserivisible.class, lesserjump.class, lesserlight.class, lessernightvision.class, lesserpower.class, lessermining.class, lessersaturation.class, lesserresistance.class, lesserspeed.class, lesserstrenght.class, lesserwatherbreathing.class, lessersuicide.class, aule.class, este.class, irmo.class, manve.class, melkor.class, melkor2.class, namo.class, nessa.class, nienna.class, orome.class, tulkas.class, ulmo.class, vaire.class, vana.class, varda.class, yavanna.class, light.class, linhir.class, farin.class, haldir.class, khain.class, bright.class, kibil.class};
            if (this.getStolenItem() == null || this.getStolenItem().stackSize <= 0) {
                for (Class stealableItem : stealableItems) {
                    if (!item.getClass().equals((Object)stealableItem)) continue;
                    return true;
                }
            }
        }
        if (type == BirdType.MAGPIE) {
            return LOTRValuableItems.canMagpieSteal(itemstack);
        }
        return false;
    }

    public ItemStack getStolenItem() {
        return this.getEquipmentInSlot(4);
    }

    public void setStolenItem(ItemStack itemstack) {
        ItemStack currentStolenItem = this.getStolenItem();
        if (currentStolenItem == null || currentStolenItem.stackSize <= 0) {
            this.setCurrentItemOrArmor(4, itemstack);
        }
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.isBirdStill()) {
            this.motionZ = 0.0;
            this.motionY = 0.0;
            this.motionX = 0.0;
            this.posY = MathHelper.floor_double((double)this.posY);
            if (this.worldObj.isRemote) {
                if (this.rand.nextInt(200) == 0) {
                    this.flapTime = 40;
                }
                if (this.flapTime > 0) {
                    --this.flapTime;
                }
            }
        } else {
            this.motionY *= 0.6;
            if (this.worldObj.isRemote) {
                this.flapTime = 0;
            }
        }
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getStolenItem() != null) {
            ++this.stolenTime;
            if (this.stolenTime >= 200) {
                this.setStolenItem(null);
                this.stolenTime = 0;
            }
        }
        if (this.isBirdStill()) {
            if (!this.canBirdSit() || this.rand.nextInt(400) == 0 || this.worldObj.getClosestPlayerToEntity((Entity)this, 6.0) != null) {
                this.setBirdStill(false);
            }
        } else {
            if (this.canStealItems() && !this.stealingCrops && this.stealTargetItem == null && this.stealTargetPlayer == null && !this.birdInv.isFull() && this.rand.nextInt(100) == 0) {
                double range = 16.0;
                List players = this.worldObj.selectEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(range, range, range), new IEntitySelector(){

                    public boolean isEntityApplicable(Entity e) {
                        EntityPlayer entityplayer;
                        if (e instanceof EntityPlayer && LOTREntityBird.this.canStealPlayer(entityplayer = (EntityPlayer)e)) {
                            ChunkCoordinates coords = LOTREntityBird.this.getPlayerFlightTarget(entityplayer);
                            return LOTREntityBird.this.isValidFlightTarget(coords);
                        }
                        return false;
                    }
                });
                if (!players.isEmpty()) {
                    this.stealTargetPlayer = (EntityPlayer)players.get(this.rand.nextInt(players.size()));
                    this.currentFlightTarget = this.getPlayerFlightTarget(this.stealTargetPlayer);
                    this.newFlight();
                } else {
                    List entityItems = this.worldObj.selectEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(range, range, range), new IEntitySelector(){

                        public boolean isEntityApplicable(Entity e) {
                            EntityItem eItem;
                            if (e instanceof EntityItem && LOTREntityBird.this.canStealItem(eItem = (EntityItem)e)) {
                                ChunkCoordinates coords = LOTREntityBird.this.getItemFlightTarget(eItem);
                                return LOTREntityBird.this.isValidFlightTarget(coords);
                            }
                            return false;
                        }
                    });
                    if (!entityItems.isEmpty()) {
                        this.stealTargetItem = (EntityItem)entityItems.get(this.rand.nextInt(entityItems.size()));
                        this.currentFlightTarget = this.getItemFlightTarget(this.stealTargetItem);
                        this.newFlight();
                    }
                }
            }
            if (this.stealTargetItem != null || this.stealTargetPlayer != null) {
                if (this.birdInv.isFull() || this.currentFlightTarget == null || !this.isValidFlightTarget(this.currentFlightTarget)) {
                    this.cancelFlight();
                } else if (this.stealTargetItem != null && !this.canStealItem(this.stealTargetItem) || this.stealTargetPlayer != null && !this.canStealPlayer(this.stealTargetPlayer)) {
                    this.cancelFlight();
                } else {
                    if (this.stealTargetItem != null) {
                        this.currentFlightTarget = this.getItemFlightTarget(this.stealTargetItem);
                    } else if (this.stealTargetPlayer != null) {
                        this.currentFlightTarget = this.getPlayerFlightTarget(this.stealTargetPlayer);
                    }
                    if (this.getDistanceSqToFlightTarget() < 1.0) {
                        ItemStack stolenItem = null;
                        if (this.stealTargetItem != null) {
                            ItemStack itemstack = this.stealTargetItem.getEntityItem();
                            ItemStack stealCopy = itemstack.copy();
                            stealCopy.stackSize = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)1, (int)Math.min(stealCopy.stackSize, 4));
                            ItemStack safeCopy = stealCopy.copy();
                            if (this.birdInv.addItemToInventory(stealCopy)) {
                                itemstack.stackSize -= safeCopy.stackSize - stealCopy.stackSize;
                                if (itemstack.stackSize <= 0) {
                                    this.stealTargetItem.setDead();
                                }
                                stolenItem = safeCopy;
                            }
                        } else if (this.stealTargetPlayer != null) {
                            List<Integer> slots = this.getStealablePlayerSlots(this.stealTargetPlayer);
                            int randSlot = slots.get(this.rand.nextInt(slots.size()));
                            ItemStack itemstack = this.stealTargetPlayer.inventory.getStackInSlot(randSlot);
                            ItemStack stealCopy = itemstack.copy();
                            stealCopy.stackSize = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)1, (int)Math.min(stealCopy.stackSize, 4));
                            ItemStack safeCopy = stealCopy.copy();
                            if (this.birdInv.addItemToInventory(stealCopy)) {
                                itemstack.stackSize -= safeCopy.stackSize - stealCopy.stackSize;
                                if (itemstack.stackSize <= 0) {
                                    itemstack = null;
                                }
                                this.stealTargetPlayer.inventory.setInventorySlotContents(randSlot, itemstack);
                                stolenItem = safeCopy;
                            }
                        }
                        if (stolenItem != null) {
                            this.stolenTime = 0;
                            this.setStolenItem(stolenItem);
                            this.playSound("random.pop", 0.5f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                        }
                        this.cancelFlight();
                    }
                }
            } else if (this.stealingCrops) {
                if (!LOTRMod.canGrief(this.worldObj)) {
                    this.stealingCrops = false;
                } else if (this.currentFlightTarget == null || !this.isValidFlightTarget(this.currentFlightTarget)) {
                    this.cancelFlight();
                } else {
                    int i = this.currentFlightTarget.posX;
                    int j = this.currentFlightTarget.posY;
                    int k = this.currentFlightTarget.posZ;
                    if (this.getDistanceSqToFlightTarget() < 1.0) {
                        if (this.canStealCrops(i, j, k)) {
                            this.eatCropBlock(i, j, k);
                            this.playSound("random.eat", 1.0f, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2f + 1.0f);
                        }
                        this.cancelFlight();
                    } else if (!this.canStealCrops(i, j, k) || this.flightTargetTime % 100 == 0 && LOTRScarecrows.anyScarecrowsNearby(this.worldObj, i, j, k)) {
                        this.cancelFlight();
                    }
                }
            } else {
                int i;
                if (LOTRMod.canGrief(this.worldObj) && !this.stealingCrops && this.rand.nextInt(100) == 0) {
                    i = MathHelper.floor_double((double)this.posX);
                    int j = MathHelper.floor_double((double)this.posY);
                    int k = MathHelper.floor_double((double)this.posZ);
                    int range = 16;
                    int yRange = 8;
                    int attempts = 32;
                    for (int l = 0; l < attempts; ++l) {
                        int k1;
                        int j1;
                        int i1 = i + MathHelper.getRandomIntegerInRange((Random)this.rand, (int)(-range), (int)range);
                        if (!this.canStealCrops(i1, j1 = j + MathHelper.getRandomIntegerInRange((Random)this.rand, (int)(-yRange), (int)yRange), k1 = k + MathHelper.getRandomIntegerInRange((Random)this.rand, (int)(-range), (int)range)) || LOTRScarecrows.anyScarecrowsNearby(this.worldObj, i1, j1, k1)) continue;
                        this.stealingCrops = true;
                        this.currentFlightTarget = new ChunkCoordinates(i1, j1, k1);
                        this.newFlight();
                        break;
                    }
                }
                if (!this.stealingCrops) {
                    if (this.currentFlightTarget != null && !this.isValidFlightTarget(this.currentFlightTarget)) {
                        this.cancelFlight();
                    }
                    if (this.currentFlightTarget == null || this.rand.nextInt(50) == 0 || this.getDistanceSqToFlightTarget() < 4.0) {
                        i = MathHelper.floor_double((double)this.posX);
                        int j = MathHelper.floor_double((double)this.posY);
                        int k = MathHelper.floor_double((double)this.posZ);
                        this.currentFlightTarget = new ChunkCoordinates(i += this.rand.nextInt(16) - this.rand.nextInt(16), j += MathHelper.getRandomIntegerInRange((Random)this.rand, (int)-2, (int)3), k += this.rand.nextInt(16) - this.rand.nextInt(16));
                        this.newFlight();
                    }
                }
            }
            if (this.currentFlightTarget != null) {
                double speed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
                double d0 = (double)this.currentFlightTarget.posX + 0.5 - this.posX;
                double d1 = (double)this.currentFlightTarget.posY + 0.5 - this.posY;
                double d2 = (double)this.currentFlightTarget.posZ + 0.5 - this.posZ;
                this.motionX += (Math.signum(d0) * 0.5 - this.motionX) * speed;
                this.motionY += (Math.signum(d1) * 0.8 - this.motionY) * speed;
                this.motionZ += (Math.signum(d2) * 0.5 - this.motionZ) * speed;
                float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / 3.141592653589793) - 90.0f;
                float f1 = MathHelper.wrapAngleTo180_float((float)(f - this.rotationYaw));
                this.moveForward = 0.5f;
                this.rotationYaw += f1;
                ++this.flightTargetTime;
                if (this.flightTargetTime >= 400) {
                    this.cancelFlight();
                }
            }
            if (this.rand.nextInt(200) == 0 && this.canBirdSit()) {
                this.setBirdStill(true);
                this.cancelFlight();
            }
        }
    }

    private boolean canBirdSit() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.posY);
        int k = MathHelper.floor_double((double)this.posZ);
        Block block = this.worldObj.getBlock(i, j, k);
        Block below = this.worldObj.getBlock(i, j - 1, k);
        return block.getBlocksMovement((IBlockAccess)this.worldObj, i, j, k) && below.isSideSolid((IBlockAccess)this.worldObj, i, j - 1, k, ForgeDirection.UP);
    }

    private boolean isValidFlightTarget(ChunkCoordinates coords) {
        int i = coords.posX;
        int j = coords.posY;
        int k = coords.posZ;
        if (j >= 1) {
            Block block = this.worldObj.getBlock(i, j, k);
            return block.getBlocksMovement((IBlockAccess)this.worldObj, i, j, k);
        }
        return false;
    }

    private double getDistanceSqToFlightTarget() {
        double d = (double)this.currentFlightTarget.posX + 0.5;
        double d1 = (double)this.currentFlightTarget.posY + 0.5;
        double d2 = (double)this.currentFlightTarget.posZ + 0.5;
        return this.getDistanceSq(d, d1, d2);
    }

    private void cancelFlight() {
        this.currentFlightTarget = null;
        this.flightTargetTime = 0;
        this.stealTargetItem = null;
        this.stealTargetPlayer = null;
        this.stealingCrops = false;
    }

    private void newFlight() {
        this.flightTargetTime = 0;
    }

    private boolean canStealItem(EntityItem entity) {
        return entity.isEntityAlive() && this.isStealable(entity.getEntityItem());
    }

    private boolean canStealPlayer(EntityPlayer entityplayer) {
        if (entityplayer.capabilities.isCreativeMode || !entityplayer.isEntityAlive()) {
            return false;
        }
        List<Integer> slots = this.getStealablePlayerSlots(entityplayer);
        return !slots.isEmpty();
    }

    private List<Integer> getStealablePlayerSlots(EntityPlayer entityplayer) {
        ArrayList<Integer> slots = new ArrayList<Integer>();
        for (int i = 0; i <= 8; ++i) {
            ItemStack itemstack;
            if (i != entityplayer.inventory.currentItem || (itemstack = entityplayer.inventory.getStackInSlot(i)) == null || !this.isStealable(itemstack)) continue;
            slots.add(i);
        }
        return slots;
    }

    private ChunkCoordinates getItemFlightTarget(EntityItem entity) {
        int i = MathHelper.floor_double((double)entity.posX);
        int j = MathHelper.floor_double((double)entity.boundingBox.minY);
        int k = MathHelper.floor_double((double)entity.posZ);
        return new ChunkCoordinates(i, j, k);
    }

    private ChunkCoordinates getPlayerFlightTarget(EntityPlayer entityplayer) {
        int i = MathHelper.floor_double((double)entityplayer.posX);
        int j = MathHelper.floor_double((double)(entityplayer.boundingBox.minY + 1.0));
        int k = MathHelper.floor_double((double)entityplayer.posZ);
        return new ChunkCoordinates(i, j, k);
    }

    private boolean canStealCrops(int i, int j, int k) {
        Block block = this.worldObj.getBlock(i, j, k);
        if (block instanceof BlockCrops) {
            return true;
        }
        if (block instanceof LOTRBlockBerryBush || block instanceof LOTRBlockBerryBush2) {
            int meta = this.worldObj.getBlockMetadata(i, j, k);
            if (block instanceof LOTRBlockBerryBush) {
                return LOTRBlockBerryBush.hasBerries(meta);
            }
            return LOTRBlockBerryBush2.hasBerries(meta);
        }
        return false;
    }

    private void eatCropBlock(int i, int j, int k) {
        Block block = this.worldObj.getBlock(i, j, k);
        if (block instanceof LOTRBlockBerryBush) {
            int meta = this.worldObj.getBlockMetadata(i, j, k);
            meta = LOTRBlockBerryBush.setHasBerries(meta, false);
            this.worldObj.setBlockMetadataWithNotify(i, j, k, meta, 3);
        } else if (block instanceof LOTRBlockBerryBush2) {
            int meta = this.worldObj.getBlockMetadata(i, j, k);
            meta = LOTRBlockBerryBush2.setHasBerries(meta, false);
            this.worldObj.setBlockMetadataWithNotify(i, j, k, meta, 3);
        } else {
            this.worldObj.setBlockToAir(i, j, k);
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void fall(float f) {
    }

    protected void updateFallState(double d, boolean flag) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && !this.worldObj.isRemote && this.isBirdStill()) {
            this.setBirdStill(false);
        }
        return flag;
    }

    protected void dropFewItems(boolean flag, int i) {
        int feathers = this.rand.nextInt(3) + this.rand.nextInt(i + 1);
        for (int l = 0; l < feathers; ++l) {
            this.dropItem(Items.feather, 1);
        }
        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(Items.bone, 1);
        }
    }

    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote) {
            this.birdInv.dropAllItems();
        }
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setBirdType(nbt.getInteger("BirdType"));
        this.setBirdStill(nbt.getBoolean("BirdStill"));
        this.birdInv.writeToNBT(nbt);
        nbt.setShort("StealTime", (short)this.stolenTime);
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("BirdType", this.getBirdType().ordinal());
        nbt.setBoolean("BirdStill", this.isBirdStill());
        this.birdInv.readFromNBT(nbt);
        this.stolenTime = nbt.getShort("StealTime");
    }

    protected boolean canDespawn() {
        return super.canDespawn();
    }

    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            return this.canBirdSpawnHere();
        }
        return false;
    }

    protected boolean canBirdSpawnHere() {
        return LOTRAmbientSpawnChecks.canSpawn(this, 8, 12, 40, 4, Material.leaves);
    }

    public boolean allowLeashing() {
        return false;
    }

    protected boolean interact(EntityPlayer entityplayer) {
        return false;
    }

    public int getTalkInterval() {
        return 60;
    }

    public void playLivingSound() {
        boolean sound = true;
        if (!this.worldObj.isDaytime()) {
            boolean bl = sound = this.rand.nextInt(20) == 0;
        }
        if (sound) {
            super.playLivingSound();
        }
    }

    protected float getSoundVolume() {
        return 1.0f;
    }

    protected String getLivingSound() {
        BirdType type = this.getBirdType();
        if (type == BirdType.CROW) {
            return "lotr:bird.crow.say";
        }
        return "lotr:bird.say";
    }

    protected String getHurtSound() {
        BirdType type = this.getBirdType();
        if (type == BirdType.CROW) {
            return "lotr:bird.crow.hurt";
        }
        return "lotr:bird.hurt";
    }

    protected String getDeathSound() {
        BirdType type = this.getBirdType();
        if (type == BirdType.CROW) {
            return "lotr:bird.crow.hurt";
        }
        return "lotr:bird.hurt";
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }

    public static enum BirdType {
        COMMON("common", true),
        CROW("crow", true),
        MAGPIE("magpie", true),
        FAR_HARAD("farHarad", true);

        public final String textureDir;
        public final boolean canSteal;

        private BirdType(String s, boolean flag) {
            this.textureDir = s;
            this.canSteal = flag;
        }
    }

}

