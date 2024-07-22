/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.BlockStem
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.IGrowable
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.entity.ai;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRMod;
import lotr.common.LOTRReflection;
import lotr.common.block.LOTRBlockCorn;
import lotr.common.block.LOTRBlockGrapevine;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFarmhand;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.inventory.LOTRInventoryNPC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTREntityAIFarm
extends EntityAIBase {
    public static final int DEPOSIT_THRESHOLD = 16;
    public static final int COLLECT_THRESHOLD = 16;
    public static final int MIN_CHEST_RANGE = 24;
    public LOTREntityNPC theEntity;
    public LOTRFarmhand theEntityFarmer;
    public World theWorld;
    public double moveSpeed;
    public float farmingEfficiency;
    public Action action = null;
    public ChunkCoordinates actionTarget;
    public ChunkCoordinates pathTarget;
    public int pathingTick;
    public int rePathDelay;
    public boolean harvestingSolidBlock;
    public FakePlayer fakePlayer;

    public LOTREntityAIFarm(LOTRFarmhand npc, double d, float f) {
        this.theEntity = (LOTREntityNPC)((Object)npc);
        this.theEntityFarmer = npc;
        this.theWorld = this.theEntity.worldObj;
        this.moveSpeed = d;
        this.setMutexBits(1);
        if (this.theWorld instanceof WorldServer) {
            this.fakePlayer = FakePlayerFactory.get((WorldServer)((WorldServer)this.theWorld), (GameProfile)new GameProfile(null, "LOTRFarming"));
        }
        this.farmingEfficiency = f;
    }

    public boolean shouldExecute() {
        boolean flag = this.shouldFarmhandExecute();
        return flag;
    }

    public boolean shouldFarmhandExecute() {
        if (this.theEntity.hiredNPCInfo.isActive && !this.theEntity.hiredNPCInfo.isGuardMode()) {
            return false;
        }
        this.setAppropriateHomeRange(null);
        if (this.theEntity.hasHome() && !this.theEntity.isWithinHomeDistanceCurrentPosition()) {
            return false;
        }
        if (this.theEntity.getRNG().nextFloat() < this.farmingEfficiency * 0.1f) {
            TargetPair hoeTarget;
            TargetPair bonemealTarget;
            TargetPair depositTarget;
            TargetPair harvestTarget;
            TargetPair plantTarget;
            TargetPair collectTarget;
            if (this.canDoDepositing() && (depositTarget = this.findTarget(Action.DEPOSITING)) != null) {
                this.actionTarget = depositTarget.actionTarget;
                this.pathTarget = depositTarget.pathTarget;
                this.action = Action.DEPOSITING;
                return true;
            }
            if (this.canDoHoeing() && (hoeTarget = this.findTarget(Action.HOEING)) != null) {
                this.actionTarget = hoeTarget.actionTarget;
                this.pathTarget = hoeTarget.pathTarget;
                this.action = Action.HOEING;
                return true;
            }
            if (this.canDoPlanting() && (plantTarget = this.findTarget(Action.PLANTING)) != null) {
                this.actionTarget = plantTarget.actionTarget;
                this.pathTarget = plantTarget.pathTarget;
                this.action = Action.PLANTING;
                return true;
            }
            if (this.canDoHarvesting() && (harvestTarget = this.findTarget(Action.HARVESTING)) != null) {
                this.actionTarget = harvestTarget.actionTarget;
                this.pathTarget = harvestTarget.pathTarget;
                this.action = Action.HARVESTING;
                return true;
            }
            if (this.canDoBonemealing() && (bonemealTarget = this.findTarget(Action.BONEMEALING)) != null) {
                this.actionTarget = bonemealTarget.actionTarget;
                this.pathTarget = bonemealTarget.pathTarget;
                this.action = Action.BONEMEALING;
                return true;
            }
            if (this.canDoCollecting() && (collectTarget = this.findTarget(Action.COLLECTING)) != null) {
                this.actionTarget = collectTarget.actionTarget;
                this.pathTarget = collectTarget.pathTarget;
                this.action = Action.COLLECTING;
                return true;
            }
        }
        return false;
    }

    public boolean isFarmingGrapes() {
        IPlantable seed = this.getSeedsToPlant();
        return seed.getPlant((IBlockAccess)this.theWorld, -1, -1, -1) instanceof LOTRBlockGrapevine;
    }

    public boolean canDoHoeing() {
        return true;
    }

    public boolean canDoPlanting() {
        if (this.theEntity.hiredNPCInfo.isActive) {
            ItemStack invSeeds = this.getInventorySeeds();
            return invSeeds != null && invSeeds.stackSize > 1;
        }
        return true;
    }

    public boolean canDoHarvesting() {
        if (this.theEntity.hiredNPCInfo.isActive) {
            return this.getInventorySeeds() != null && this.hasSpaceForCrops() && this.getCropForSeed(this.getSeedsToPlant()) != null;
        }
        return false;
    }

    public boolean canDoDepositing() {
        if (this.theEntity.hiredNPCInfo.isActive) {
            for (int l = 1; l <= 2; ++l) {
                ItemStack itemstack = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(l);
                if (itemstack == null || itemstack.stackSize < 16) continue;
                return true;
            }
        }
        return false;
    }

    public boolean canDoBonemealing() {
        if (this.theEntity.hiredNPCInfo.isActive) {
            ItemStack invBmeal = this.getInventoryBonemeal();
            return invBmeal != null;
        }
        return false;
    }

    public boolean canDoCollecting() {
        if (this.theEntity.hiredNPCInfo.isActive) {
            ItemStack seeds = this.getInventorySeeds();
            if (seeds != null && seeds.stackSize <= 16) {
                return true;
            }
            ItemStack bonemeal = this.getInventoryBonemeal();
            if (bonemeal == null || bonemeal != null && bonemeal.stackSize <= 16) {
                return true;
            }
        }
        return false;
    }

    public ItemStack getInventorySeeds() {
        Item item;
        IPlantable iplantable;
        if (this.theEntity.hiredNPCInfo.getHiredInventory() == null) {
            return null;
        }
        ItemStack itemstack = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(0);
        if (itemstack != null && (item = itemstack.getItem()) instanceof IPlantable && (iplantable = (IPlantable)item).getPlantType((IBlockAccess)this.theWorld, -1, -1, -1) == EnumPlantType.Crop) {
            return itemstack;
        }
        return null;
    }

    public IPlantable getSeedsToPlant() {
        ItemStack invSeeds;
        if (this.theEntity.hiredNPCInfo.isActive && (invSeeds = this.getInventorySeeds()) != null) {
            return (IPlantable)invSeeds.getItem();
        }
        return this.theEntityFarmer.getUnhiredSeeds();
    }

    public boolean hasSpaceForCrops() {
        if (this.theEntity.hiredNPCInfo.getHiredInventory() == null) {
            return false;
        }
        for (int l = 1; l <= 2; ++l) {
            ItemStack itemstack = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(l);
            if (itemstack != null && (itemstack.stackSize >= itemstack.getMaxStackSize() || !itemstack.isItemEqual(this.getCropForSeed(this.getSeedsToPlant())))) continue;
            return true;
        }
        return false;
    }

    public ItemStack getInventoryBonemeal() {
        if (this.theEntity.hiredNPCInfo.getHiredInventory() == null) {
            return null;
        }
        ItemStack itemstack = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(3);
        if (itemstack != null && itemstack.getItem() == Items.dye && itemstack.getItemDamage() == 15) {
            return itemstack;
        }
        return null;
    }

    public ItemStack getCropForSeed(IPlantable seed) {
        Block block = seed.getPlant((IBlockAccess)this.theWorld, -1, -1, -1);
        if (block instanceof BlockCrops) {
            return new ItemStack(LOTRReflection.getCropItem((BlockCrops)block));
        }
        if (block instanceof BlockStem) {
            return new ItemStack(LOTRReflection.getStemFruitBlock((BlockStem)block).getItemDropped(0, this.theWorld.rand, 0), 1, 0);
        }
        if (block instanceof LOTRBlockCorn) {
            return new ItemStack(LOTRMod.corn);
        }
        if (block instanceof LOTRBlockGrapevine) {
            return new ItemStack(((LOTRBlockGrapevine)block).getGrapeItem());
        }
        return null;
    }

    public void startExecuting() {
        super.startExecuting();
        this.setAppropriateHomeRange(this.action);
    }

    public void setAppropriateHomeRange(Action targetAction) {
        if (this.theEntity.hiredNPCInfo.isActive) {
            int hRange = this.theEntity.hiredNPCInfo.getGuardRange();
            ChunkCoordinates home = this.theEntity.getHomePosition();
            if (targetAction != null && (targetAction == Action.DEPOSITING || targetAction == Action.COLLECTING) && hRange < 24) {
                hRange = 24;
            }
            this.theEntity.setHomeArea(home.posX, home.posY, home.posZ, hRange);
        }
    }

    public boolean continueExecuting() {
        if (this.theEntity.hiredNPCInfo.isActive && !this.theEntity.hiredNPCInfo.isGuardMode()) {
            return false;
        }
        if (this.theEntity.getNavigator().noPath()) {
            return false;
        }
        if (this.pathingTick < 200) {
            if (this.action == Action.HOEING) {
                return this.canDoHoeing() && this.isSuitableForHoeing(this.actionTarget);
            }
            if (this.action == Action.PLANTING) {
                return this.canDoPlanting() && this.isSuitableForPlanting(this.actionTarget);
            }
            if (this.action == Action.HARVESTING) {
                return this.canDoHarvesting() && this.isSuitableForHarvesting(this.actionTarget);
            }
            if (this.action == Action.DEPOSITING) {
                return this.canDoDepositing() && this.isSuitableForDepositing(this.actionTarget);
            }
            if (this.action == Action.BONEMEALING) {
                return this.canDoBonemealing() && this.isSuitableForBonemealing(this.actionTarget);
            }
            if (this.action == Action.COLLECTING) {
                return this.canDoCollecting() && this.isSuitableForCollecting(this.actionTarget);
            }
        }
        return false;
    }

    public void resetTask() {
        this.action = null;
        this.setAppropriateHomeRange(this.action);
        this.actionTarget = null;
        this.pathTarget = null;
        this.pathingTick = 0;
        this.rePathDelay = 0;
        this.harvestingSolidBlock = false;
    }

    /*
     * WARNING - void declaration
     */
    public void updateTask() {
        boolean canCollect;
        boolean canDoAction = false;
        double distSq = this.theEntity.getDistanceSq((double)this.pathTarget.posX + 0.5, (double)this.pathTarget.posY, (double)this.pathTarget.posZ + 0.5);
        if (this.action == Action.HOEING || this.action == Action.PLANTING) {
            int i = MathHelper.floor_double((double)this.theEntity.posX);
            int j = MathHelper.floor_double((double)this.theEntity.boundingBox.minY);
            int k = MathHelper.floor_double((double)this.theEntity.posZ);
            canDoAction = i == this.pathTarget.posX && j == this.pathTarget.posY && k == this.pathTarget.posZ;
        } else {
            boolean bl = canDoAction = distSq < 9.0;
        }
        if (!canDoAction) {
            this.theEntity.getLookHelper().setLookPosition((double)this.actionTarget.posX + 0.5, (double)this.actionTarget.posY + 0.5, (double)this.actionTarget.posZ + 0.5, 10.0f, (float)this.theEntity.getVerticalFaceSpeed());
            --this.rePathDelay;
            if (this.rePathDelay <= 0) {
                this.rePathDelay = 10;
                this.theEntity.getNavigator().tryMoveToXYZ((double)this.pathTarget.posX + 0.5, (double)this.pathTarget.posY, (double)this.pathTarget.posZ + 0.5, this.moveSpeed);
            }
            ++this.pathingTick;
        } else if (this.action == Action.HOEING) {
            boolean canHoe = this.isSuitableForHoeing(this.actionTarget);
            if (canHoe) {
                this.theEntity.swingItem();
                ItemStack proxyHoe = new ItemStack(Items.iron_hoe);
                int hoeRange = 1;
                for (int i1 = -hoeRange; i1 <= hoeRange; ++i1) {
                    for (int k1 = -hoeRange; k1 <= hoeRange; ++k1) {
                        boolean alreadyChecked;
                        if (Math.abs(i1) + Math.abs(k1) > hoeRange) continue;
                        int x = this.actionTarget.posX + i1;
                        int z = this.actionTarget.posZ + k1;
                        int y = this.actionTarget.posY;
                        boolean bl = alreadyChecked = i1 == 0 && k1 == 0;
                        if (!alreadyChecked && !this.isSuitableForHoeing(x, y, z)) continue;
                        if (this.isReplaceable(x, y + 1, z)) {
                            this.theWorld.setBlockToAir(x, y + 1, z);
                        }
                        proxyHoe.tryPlaceItemIntoWorld((EntityPlayer)this.fakePlayer, this.theWorld, x, y, z, 1, 0.5f, 0.5f, 0.5f);
                    }
                }
            }
        } else if (this.action == Action.PLANTING) {
            boolean canPlant = this.isSuitableForPlanting(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
            if (canPlant) {
                this.theEntity.swingItem();
                IPlantable seed = this.getSeedsToPlant();
                Block plant = seed.getPlant((IBlockAccess)this.theWorld, this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
                int meta = seed.getPlantMetadata((IBlockAccess)this.theWorld, this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
                this.theWorld.setBlock(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ, plant, meta, 3);
                if (this.theEntity.hiredNPCInfo.isActive) {
                    this.theEntity.hiredNPCInfo.getHiredInventory().decrStackSize(0, 1);
                }
            }
        } else if (this.action == Action.HARVESTING) {
            boolean canHarvest = this.isSuitableForHarvesting(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
            if (canHarvest) {
                int meta;
                this.theEntity.swingItem();
                Block block = this.theWorld.getBlock(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
                ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
                if (block instanceof LOTRBlockCorn) {
                    int x = this.actionTarget.posX;
                    int z = this.actionTarget.posZ;
                    for (int j1 = 0; j1 <= LOTRBlockCorn.MAX_GROW_HEIGHT - 1; ++j1) {
                        int y = this.actionTarget.posY + j1;
                        if (this.theWorld.getBlock(x, y, z) != block || !LOTRBlockCorn.hasCorn(this.theWorld, x, y, z)) continue;
                        int meta2 = this.theWorld.getBlockMetadata(x, y, z);
                        drops.addAll(((LOTRBlockCorn)block).getCornDrops(this.theWorld, x, y, z, meta2));
                        LOTRBlockCorn.setHasCorn(this.theWorld, x, y, z, false);
                    }
                } else if (block instanceof LOTRBlockGrapevine) {
                    meta = this.theWorld.getBlockMetadata(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
                    drops.addAll(block.getDrops(this.theWorld, this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ, meta, 0));
                    block.removedByPlayer(this.theWorld, (EntityPlayer)this.fakePlayer, this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ, true);
                } else {
                    meta = this.theWorld.getBlockMetadata(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
                    drops.addAll(block.getDrops(this.theWorld, this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ, meta, 0));
                    this.theWorld.setBlockToAir(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
                }
                Block.SoundType cropSound = block.stepSound;
                this.theWorld.playSoundEffect((double)this.actionTarget.posX + 0.5, (double)this.actionTarget.posY + 0.5, (double)this.actionTarget.posZ + 0.5, cropSound.getBreakSound(), (cropSound.getVolume() + 1.0f) / 2.0f, cropSound.getPitch() * 0.8f);
                ItemStack seedItem = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(0);
                ItemStack cropItem = this.getCropForSeed(this.getSeedsToPlant());
                boolean addedOneCropSeed = false;
                block3: for (Object obj : drops) {
                    ItemStack itemstack;
                    ItemStack drop = (ItemStack)obj;
                    if (drop.isItemEqual(cropItem)) {
                        if (drop.isItemEqual(seedItem) && !addedOneCropSeed) {
                            addedOneCropSeed = true;
                            itemstack = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(0);
                            if (itemstack.stackSize + drop.stackSize <= itemstack.getMaxStackSize()) {
                                ++itemstack.stackSize;
                                this.theEntity.hiredNPCInfo.getHiredInventory().setInventorySlotContents(0, itemstack);
                                continue;
                            }
                        }
                        for (int l = 1; l <= 2; ++l) {
                            ItemStack itemstack2 = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(l);
                            if (itemstack2 == null) {
                                this.theEntity.hiredNPCInfo.getHiredInventory().setInventorySlotContents(l, drop);
                                continue block3;
                            }
                            if (itemstack2.stackSize + drop.stackSize > itemstack2.getMaxStackSize() || !itemstack2.isItemEqual(cropItem)) continue;
                            ++itemstack2.stackSize;
                            this.theEntity.hiredNPCInfo.getHiredInventory().setInventorySlotContents(l, itemstack2);
                            continue block3;
                        }
                        continue;
                    }
                    if (!drop.isItemEqual(seedItem) || itemstack.stackSize + drop.stackSize > (itemstack = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(0)).getMaxStackSize()) continue;
                    ++itemstack.stackSize;
                    this.theEntity.hiredNPCInfo.getHiredInventory().setInventorySlotContents(0, itemstack);
                }
            }
        } else if (this.action == Action.DEPOSITING) {
            boolean canDeposit = this.isSuitableForDepositing(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
            if (canDeposit) {
                this.theEntity.swingItem();
                TileEntity te = this.theWorld.getTileEntity(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
                if (te instanceof TileEntityChest) {
                    TileEntityChest chest = (TileEntityChest)te;
                    block5: for (int l = 1; l <= 2; ++l) {
                        ItemStack itemstack = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(l);
                        if (itemstack == null) continue;
                        for (int slot = 0; slot < chest.getSizeInventory(); ++slot) {
                            ItemStack chestItem = chest.getStackInSlot(slot);
                            if (chestItem != null && (!chestItem.isItemEqual(itemstack) || !ItemStack.areItemStackTagsEqual((ItemStack)chestItem, (ItemStack)itemstack) || chestItem.stackSize >= chestItem.getMaxStackSize())) continue;
                            if (chestItem == null) {
                                chestItem = itemstack.copy();
                                chestItem.stackSize = 0;
                            }
                            while (itemstack.stackSize > 0 && chestItem.stackSize < chestItem.getMaxStackSize()) {
                                ++chestItem.stackSize;
                                --itemstack.stackSize;
                            }
                            chest.setInventorySlotContents(slot, chestItem);
                            if (itemstack.stackSize > 0) continue;
                            this.theEntity.hiredNPCInfo.getHiredInventory().setInventorySlotContents(l, null);
                            continue block5;
                        }
                    }
                    this.theWorld.playSoundEffect((double)this.actionTarget.posX + 0.5, (double)this.actionTarget.posY + 0.5, (double)this.actionTarget.posZ + 0.5, "random.chestclosed", 0.5f, this.theWorld.rand.nextFloat() * 0.1f + 0.9f);
                }
            }
        } else if (this.action == Action.BONEMEALING) {
            boolean canBonemeal = this.isSuitableForBonemealing(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
            if (canBonemeal) {
                this.theEntity.swingItem();
                ItemStack bonemeal = this.getInventoryBonemeal();
                if (ItemDye.applyBonemeal((ItemStack)this.getInventoryBonemeal(), (World)this.theWorld, (int)this.actionTarget.posX, (int)this.actionTarget.posY, (int)this.actionTarget.posZ, (EntityPlayer)this.fakePlayer)) {
                    this.theWorld.playAuxSFX(2005, this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ, 0);
                }
                if (bonemeal.stackSize <= 0) {
                    bonemeal = null;
                }
                this.theEntity.hiredNPCInfo.getHiredInventory().setInventorySlotContents(3, bonemeal);
            }
        } else if (this.action == Action.COLLECTING && (canCollect = this.isSuitableForCollecting(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ))) {
            this.theEntity.swingItem();
            TileEntity te = this.theWorld.getTileEntity(this.actionTarget.posX, this.actionTarget.posY, this.actionTarget.posZ);
            if (te instanceof TileEntityChest) {
                int[] invSlots;
                TileEntityChest chest = (TileEntityChest)te;
                block8: for (int l : invSlots = new int[]{0, 3}) {
                    ItemStack itemstack22 = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(l);
                    if (itemstack22 == null && l == 3) {
                        itemstack22 = new ItemStack(Items.dye, 0, 15);
                    }
                    if (itemstack22 == null) continue;
                    for (int slot = 0; slot < chest.getSizeInventory(); ++slot) {
                        void itemstack22;
                        ItemStack chestItem = chest.getStackInSlot(slot);
                        if (chestItem == null || !chestItem.isItemEqual((ItemStack)itemstack22) || !ItemStack.areItemStackTagsEqual((ItemStack)chestItem, (ItemStack)itemstack22) || chestItem.stackSize <= 0) continue;
                        while (itemstack22.stackSize < itemstack22.getMaxStackSize() && chestItem.stackSize > 0) {
                            --chestItem.stackSize;
                            ++itemstack22.stackSize;
                        }
                        if (itemstack22.stackSize <= 0) {
                            Object itemstack22 = null;
                        }
                        if (chestItem.stackSize <= 0) {
                            chestItem = null;
                        }
                        this.theEntity.hiredNPCInfo.getHiredInventory().setInventorySlotContents(l, (ItemStack)itemstack22);
                        chest.setInventorySlotContents(slot, chestItem);
                        if (itemstack22.stackSize >= itemstack22.getMaxStackSize()) continue block8;
                    }
                }
                this.theWorld.playSoundEffect((double)this.actionTarget.posX + 0.5, (double)this.actionTarget.posY + 0.5, (double)this.actionTarget.posZ + 0.5, "random.chestopen", 0.5f, this.theWorld.rand.nextFloat() * 0.1f + 0.9f);
            }
        }
    }

    public TargetPair findTarget(Action targetAction) {
        this.setAppropriateHomeRange(targetAction);
        Random rand = this.theEntity.getRNG();
        boolean isChestAction = targetAction == Action.DEPOSITING || targetAction == Action.COLLECTING;
        List<Object> chests = new ArrayList();
        if (isChestAction) {
            chests = this.gatherNearbyChests();
        }
        for (int l = 0; l < 32; ++l) {
            int i = 0;
            int j = 0;
            int k = 0;
            boolean suitable = false;
            if (isChestAction) {
                if (!chests.isEmpty()) {
                    TileEntityChest chest = (TileEntityChest)chests.get(rand.nextInt(chests.size()));
                    i = chest.xCoord;
                    j = chest.yCoord;
                    k = chest.zCoord;
                    if (targetAction == Action.DEPOSITING) {
                        suitable = this.isSuitableForDepositing(i, j, k);
                    } else if (targetAction == Action.COLLECTING) {
                        suitable = this.isSuitableForCollecting(i, j, k);
                    }
                } else {
                    suitable = false;
                }
            } else {
                i = MathHelper.floor_double((double)this.theEntity.posX) + MathHelper.getRandomIntegerInRange((Random)rand, (int)-8, (int)8);
                j = MathHelper.floor_double((double)this.theEntity.boundingBox.minY) + MathHelper.getRandomIntegerInRange((Random)rand, (int)-4, (int)4);
                k = MathHelper.floor_double((double)this.theEntity.posZ) + MathHelper.getRandomIntegerInRange((Random)rand, (int)-8, (int)8);
                if (targetAction == Action.HOEING) {
                    suitable = this.isSuitableForHoeing(i, j, k);
                } else if (targetAction == Action.PLANTING) {
                    suitable = this.isSuitableForPlanting(i, j, k);
                } else if (targetAction == Action.HARVESTING) {
                    suitable = this.isSuitableForHarvesting(i, j, k);
                } else if (targetAction == Action.BONEMEALING) {
                    suitable = this.isSuitableForBonemealing(i, j, k);
                }
            }
            if (!suitable || !this.theEntity.isWithinHomeDistance(i, j, k)) continue;
            ChunkCoordinates target = new ChunkCoordinates(i, j, k);
            ChunkCoordinates path = this.getPathTarget(i, j, k, targetAction);
            PathEntity pathCheck = this.theEntity.getNavigator().getPathToXYZ((double)path.posX, (double)path.posY, (double)path.posZ);
            if (pathCheck == null) continue;
            return new TargetPair(target, path);
        }
        return null;
    }

    public List<TileEntityChest> gatherNearbyChests() {
        int x = MathHelper.floor_double((double)this.theEntity.posX);
        MathHelper.floor_double((double)this.theEntity.boundingBox.minY);
        int z = MathHelper.floor_double((double)this.theEntity.posZ);
        int searchRange = (int)this.theEntity.func_110174_bM();
        int chunkX = x >> 4;
        int chunkZ = z >> 4;
        int chunkRange = (searchRange >> 4) + 1;
        ArrayList<TileEntityChest> nearbyChests = new ArrayList<TileEntityChest>();
        for (int i = -chunkRange; i <= chunkRange; ++i) {
            for (int k = -chunkRange; k <= chunkRange; ++k) {
                int nearChunkX = chunkX + i;
                int nearChunkZ = chunkZ + k;
                if (!this.theWorld.getChunkProvider().chunkExists(nearChunkX, nearChunkZ)) continue;
                Chunk chunk = this.theWorld.getChunkFromChunkCoords(nearChunkX, nearChunkZ);
                for (Object obj : chunk.chunkTileEntityMap.values()) {
                    TileEntity te = (TileEntity)obj;
                    if (!(te instanceof TileEntityChest) || te.isInvalid()) continue;
                    TileEntityChest chest = (TileEntityChest)te;
                    if (!this.theEntity.isWithinHomeDistance(chest.xCoord, chest.yCoord, chest.zCoord)) continue;
                    nearbyChests.add(chest);
                }
            }
        }
        return nearbyChests;
    }

    public ChunkCoordinates getPathTarget(int i, int j, int k, Action targetAction) {
        if (targetAction == Action.HOEING) {
            if (this.isReplaceable(i, j + 1, k)) {
                return new ChunkCoordinates(i, j + 1, k);
            }
            return this.getAdjacentSolidOpenWalkTarget(i, j + 1, k);
        }
        if (targetAction == Action.PLANTING || targetAction == Action.HARVESTING || targetAction == Action.BONEMEALING) {
            if (this.harvestingSolidBlock) {
                return new ChunkCoordinates(i, j + 1, k);
            }
            if (this.isFarmingGrapes()) {
                int groundY = j;
                for (int j1 = 1; j1 <= 2; ++j1) {
                    if (this.theWorld.getBlock(i, j - j1 - 1, k) instanceof LOTRBlockGrapevine) continue;
                    groundY = j - j1 - 1;
                    break;
                }
                return this.getAdjacentSolidOpenWalkTarget(i, groundY + 1, k);
            }
            return new ChunkCoordinates(i, j, k);
        }
        if (targetAction == Action.DEPOSITING || targetAction == Action.COLLECTING) {
            return this.getAdjacentSolidOpenWalkTarget(i, j, k);
        }
        return new ChunkCoordinates(i, j, k);
    }

    public boolean isSolidOpenWalkTarget(int i, int j, int k) {
        Block below = this.theWorld.getBlock(i, j - 1, k);
        if (below.isOpaqueCube() || below.canSustainPlant((IBlockAccess)this.theWorld, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.wheat)) {
            ArrayList bounds = new ArrayList();
            AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 2), (double)(k + 1));
            for (int j1 = j; j1 <= j + 1; ++j1) {
                this.theWorld.getBlock(i, j1, k).addCollisionBoxesToList(this.theWorld, i, j1, k, aabb, bounds, (Entity)this.theEntity);
            }
            if (bounds.isEmpty()) {
                return this.theEntity.getNavigator().getPathToXYZ((double)i, (double)j, (double)k) != null;
            }
        }
        return false;
    }

    public ChunkCoordinates getAdjacentSolidOpenWalkTarget(int i, int j, int k) {
        ArrayList<ChunkCoordinates> possibleCoords = new ArrayList<ChunkCoordinates>();
        for (int i1 = -1; i1 <= 1; ++i1) {
            for (int k1 = -1; k1 <= 1; ++k1) {
                int i2 = i + i1;
                int k2 = k + k1;
                for (int j1 = 1; j1 >= -1; --j1) {
                    int j2 = j + j1;
                    if (!this.isSolidOpenWalkTarget(i2, j2, k2)) continue;
                    possibleCoords.add(new ChunkCoordinates(i2, j2, k2));
                }
            }
        }
        if (!possibleCoords.isEmpty()) {
            return (ChunkCoordinates)possibleCoords.get(0);
        }
        return new ChunkCoordinates(i, j, k);
    }

    public boolean isSuitableForHoeing(ChunkCoordinates pos) {
        return this.isSuitableForHoeing(pos.posX, pos.posY, pos.posZ);
    }

    public boolean isSuitableForHoeing(int i, int j, int k) {
        this.harvestingSolidBlock = false;
        Block block = this.theWorld.getBlock(i, j, k);
        boolean isGrassDirt = block.canSustainPlant((IBlockAccess)this.theWorld, i, j, k, ForgeDirection.UP, (IPlantable)Blocks.tallgrass);
        boolean isFarmland = block.canSustainPlant((IBlockAccess)this.theWorld, i, j, k, ForgeDirection.UP, (IPlantable)Blocks.wheat);
        if (isGrassDirt && !isFarmland && (this.isReplaceable(i, j + 1, k) || this.theWorld.getBlock(i, j + 1, k) == LOTRMod.grapevine)) {
            Block below = this.theWorld.getBlock(i, j - 1, k);
            if (below == Blocks.sand) {
                return false;
            }
            boolean waterNearby = false;
            int range = 4;
            block0: for (int i1 = i - range; i1 <= i + range; ++i1) {
                for (int k1 = k - range; k1 <= k + range; ++k1) {
                    if (this.theWorld.getBlock(i1, j, k1).getMaterial() != Material.water) continue;
                    waterNearby = true;
                    break block0;
                }
            }
            return waterNearby;
        }
        return false;
    }

    public boolean isSuitableForPlanting(ChunkCoordinates pos) {
        return this.isSuitableForPlanting(pos.posX, pos.posY, pos.posZ);
    }

    public boolean isSuitableForPlanting(int i, int j, int k) {
        this.harvestingSolidBlock = false;
        if (this.isFarmingGrapes()) {
            return this.theWorld.getBlock(i, j, k) == LOTRMod.grapevine && LOTRBlockGrapevine.canPlantGrapesAt(this.theWorld, i, j, k, this.getSeedsToPlant());
        }
        return this.theWorld.getBlock(i, j - 1, k).isFertile(this.theWorld, i, j - 1, k) && this.isReplaceable(i, j, k);
    }

    public boolean isSuitableForHarvesting(ChunkCoordinates pos) {
        return this.isSuitableForHarvesting(pos.posX, pos.posY, pos.posZ);
    }

    public boolean isSuitableForHarvesting(int i, int j, int k) {
        this.harvestingSolidBlock = false;
        IPlantable seed = this.getSeedsToPlant();
        Block plantBlock = seed.getPlant((IBlockAccess)this.theWorld, i, j, k);
        if (plantBlock instanceof BlockCrops) {
            this.harvestingSolidBlock = false;
            return this.theWorld.getBlock(i, j, k) == plantBlock && this.theWorld.getBlockMetadata(i, j, k) >= 7;
        }
        if (plantBlock instanceof BlockStem) {
            this.harvestingSolidBlock = true;
            return this.theWorld.getBlock(i, j, k) == LOTRReflection.getStemFruitBlock((BlockStem)plantBlock);
        }
        if (plantBlock instanceof LOTRBlockCorn) {
            this.harvestingSolidBlock = false;
            if (this.theWorld.getBlock(i, j, k) == plantBlock) {
                for (int j1 = 0; j1 <= LOTRBlockCorn.MAX_GROW_HEIGHT - 1; ++j1) {
                    int j2 = j + j1;
                    if (this.theWorld.getBlock(i, j2, k) != plantBlock || !LOTRBlockCorn.hasCorn(this.theWorld, i, j2, k)) continue;
                    return true;
                }
            }
        } else if (plantBlock instanceof LOTRBlockGrapevine) {
            this.harvestingSolidBlock = false;
            return this.theWorld.getBlock(i, j, k) == seed.getPlant((IBlockAccess)this.theWorld, i, j, k) && this.theWorld.getBlockMetadata(i, j, k) >= 7;
        }
        return false;
    }

    public boolean isSuitableForDepositing(ChunkCoordinates pos) {
        return this.isSuitableForDepositing(pos.posX, pos.posY, pos.posZ);
    }

    public boolean isSuitableForDepositing(int i, int j, int k) {
        this.harvestingSolidBlock = false;
        TileEntityChest chest = this.getSuitableChest(i, j, k);
        if (chest != null) {
            for (int l = 1; l <= 2; ++l) {
                ItemStack depositItem = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(l);
                if (depositItem == null) continue;
                for (int slot = 0; slot < chest.getSizeInventory(); ++slot) {
                    ItemStack chestItem = chest.getStackInSlot(slot);
                    if (chestItem != null && (!chestItem.isItemEqual(depositItem) || !ItemStack.areItemStackTagsEqual((ItemStack)chestItem, (ItemStack)depositItem) || chestItem.stackSize >= chestItem.getMaxStackSize())) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSuitableForBonemealing(ChunkCoordinates pos) {
        return this.isSuitableForBonemealing(pos.posX, pos.posY, pos.posZ);
    }

    public boolean isSuitableForBonemealing(int i, int j, int k) {
        IGrowable growableBlock;
        this.harvestingSolidBlock = false;
        IPlantable seed = this.getSeedsToPlant();
        Block plantBlock = seed.getPlant((IBlockAccess)this.theWorld, i, j, k);
        if (plantBlock instanceof IGrowable && this.theWorld.getBlock(i, j, k) == plantBlock && (growableBlock = (IGrowable)plantBlock).func_149851_a(this.theWorld, i, j, k, this.theWorld.isRemote)) {
            this.harvestingSolidBlock = plantBlock.isOpaqueCube();
            return true;
        }
        return false;
    }

    public boolean isSuitableForCollecting(ChunkCoordinates pos) {
        return this.isSuitableForCollecting(pos.posX, pos.posY, pos.posZ);
    }

    public boolean isSuitableForCollecting(int i, int j, int k) {
        this.harvestingSolidBlock = false;
        TileEntityChest chest = this.getSuitableChest(i, j, k);
        if (chest != null) {
            int[] invSlots;
            for (int l : invSlots = new int[]{0, 3}) {
                ItemStack collectMatch = this.theEntity.hiredNPCInfo.getHiredInventory().getStackInSlot(l);
                if (collectMatch == null && l == 3) {
                    collectMatch = new ItemStack(Items.dye, 0, 15);
                }
                if (collectMatch == null || collectMatch.stackSize > 16) continue;
                for (int slot = 0; slot < chest.getSizeInventory(); ++slot) {
                    ItemStack chestItem = chest.getStackInSlot(slot);
                    if (chestItem == null || !chestItem.isItemEqual(collectMatch) || !ItemStack.areItemStackTagsEqual((ItemStack)chestItem, (ItemStack)collectMatch) || chestItem.stackSize <= 0) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public TileEntityChest getSuitableChest(int i, int j, int k) {
        TileEntity te;
        Block block = this.theWorld.getBlock(i, j, k);
        int meta = this.theWorld.getBlockMetadata(i, j, k);
        TileEntityChest suitableChest = null;
        if (block.hasTileEntity(meta) && (te = this.theWorld.getTileEntity(i, j, k)) instanceof TileEntityChest) {
            TileEntityChest chest = (TileEntityChest)te;
            boolean flag = false;
            if (this.isFarmhandMarked(chest) || chest.adjacentChestXNeg != null && this.isFarmhandMarked(chest.adjacentChestXNeg)) {
                flag = true;
            } else if (chest.adjacentChestXPos != null && this.isFarmhandMarked(chest.adjacentChestXPos)) {
                flag = true;
            } else if (chest.adjacentChestZNeg != null && this.isFarmhandMarked(chest.adjacentChestZNeg)) {
                flag = true;
            } else if (chest.adjacentChestZPos != null && this.isFarmhandMarked(chest.adjacentChestZPos)) {
                flag = true;
            }
            if (flag) {
                suitableChest = chest;
            }
        }
        return suitableChest;
    }

    public boolean isFarmhandMarked(TileEntityChest chest) {
        int i = chest.xCoord;
        int j = chest.yCoord;
        int k = chest.zCoord;
        AxisAlignedBB chestBB = AxisAlignedBB.getBoundingBox((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
        List entities = this.theWorld.getEntitiesWithinAABB(EntityItemFrame.class, chestBB.expand(2.0, 2.0, 2.0));
        for (Object obj : entities) {
            ItemStack frameItem;
            EntityItemFrame frame = (EntityItemFrame)obj;
            if (frame.field_146063_b != i || frame.field_146064_c != j || frame.field_146062_d != k || (frameItem = frame.getDisplayedItem()) == null || !(frameItem.getItem() instanceof ItemHoe)) continue;
            return true;
        }
        return false;
    }

    public boolean isReplaceable(int i, int j, int k) {
        Block block = this.theWorld.getBlock(i, j, k);
        return !block.getMaterial().isLiquid() && block.isReplaceable((IBlockAccess)this.theWorld, i, j, k);
    }

    public static class TargetPair {
        public final ChunkCoordinates actionTarget;
        public final ChunkCoordinates pathTarget;

        public TargetPair(ChunkCoordinates action, ChunkCoordinates path) {
            this.actionTarget = action;
            this.pathTarget = path;
        }
    }

    public static enum Action {
        HOEING,
        PLANTING,
        HARVESTING,
        DEPOSITING,
        BONEMEALING,
        COLLECTING;

    }

}

