/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockTrapDoor
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.util.Random;
import lotr.client.render.tileentity.LOTRRenderBeacon;
import lotr.client.render.tileentity.LOTRRenderChest;
import lotr.client.render.tileentity.LOTRRenderCommandTable;
import lotr.client.render.tileentity.LOTRRenderGuldurilGlow;
import lotr.client.render.tileentity.LOTRRenderTrollTotem;
import lotr.client.render.tileentity.LOTRRenderUnsmeltery;
import lotr.client.render.tileentity.LOTRTileEntityMobSpawnerRenderer;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockBeacon;
import lotr.common.block.LOTRBlockBirdCage;
import lotr.common.block.LOTRBlockClover;
import lotr.common.block.LOTRBlockCoralReef;
import lotr.common.block.LOTRBlockFlower;
import lotr.common.block.LOTRBlockFlowerPot;
import lotr.common.block.LOTRBlockGateDwarvenIthildin;
import lotr.common.block.LOTRBlockGrass;
import lotr.common.block.LOTRBlockRhunFireJar;
import lotr.common.block.LOTRBlockTallGrass;
import lotr.common.block.LOTRBlockTreasurePile;
import lotr.common.tileentity.LOTRTileEntityBeacon;
import lotr.common.tileentity.LOTRTileEntityChest;
import lotr.common.tileentity.LOTRTileEntityCommandTable;
import lotr.common.tileentity.LOTRTileEntityGulduril;
import lotr.common.tileentity.LOTRTileEntityMobSpawner;
import lotr.common.tileentity.LOTRTileEntityTrollTotem;
import lotr.common.tileentity.LOTRTileEntityUnsmeltery;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBlocks
implements ISimpleBlockRenderingHandler {
    private static Random blockRand = new Random();
    private boolean renderInvIn3D;

    public LOTRRenderBlocks(boolean flag) {
        this.renderInvIn3D = flag;
    }

    public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int id, RenderBlocks renderblocks) {
        boolean fancyGraphics = Minecraft.getMinecraft().gameSettings.fancyGraphics;
        if (id == LOTRMod.proxy.getBeaconRenderID()) {
            this.renderBeacon(world, i, j, k, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getBarrelRenderID()) {
            this.renderBarrel(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getOrcBombRenderID()) {
            this.renderOrcBomb(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getDoubleTorchRenderID()) {
            this.renderDoubleTorch(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getMobSpawnerRenderID()) {
            return renderblocks.renderStandardBlock(block, i, j, k);
        }
        if (id == LOTRMod.proxy.getPlateRenderID()) {
            this.renderPlate(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getStalactiteRenderID()) {
            this.renderStalactite(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getFlowerPotRenderID()) {
            this.renderFlowerPot(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getCloverRenderID()) {
            LOTRRenderBlocks.renderClover(world, i, j, k, block, renderblocks, world.getBlockMetadata(i, j, k) == 1 ? 4 : 3, true);
            return true;
        }
        if (id == LOTRMod.proxy.getEntJarRenderID()) {
            this.renderEntJar(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getFenceRenderID()) {
            return renderblocks.renderBlockFence((BlockFence)block, i, j, k);
        }
        if (id == LOTRMod.proxy.getGrassRenderID()) {
            LOTRRenderBlocks.renderGrass(world, i, j, k, block, renderblocks, true);
            return true;
        }
        if (id == LOTRMod.proxy.getFallenLeavesRenderID()) {
            if (fancyGraphics) {
                this.renderFallenLeaves(world, i, j, k, block, renderblocks, new int[]{6, 10}, new int[]{2, 6}, new int[]{2, 6}, 0.7f);
                return true;
            }
            return renderblocks.renderStandardBlock(block, i, j, k);
        }
        if (id == LOTRMod.proxy.getCommandTableRenderID()) {
            this.renderCommandTable(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getButterflyJarRenderID()) {
            this.renderButterflyJar(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getUnsmelteryRenderID()) {
            return true;
        }
        if (id == LOTRMod.proxy.getChestRenderID()) {
            return true;
        }
        if (id == LOTRMod.proxy.getReedsRenderID()) {
            this.renderReeds(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getWasteRenderID()) {
            this.renderBlockRandomRotated(world, i, j, k, block, renderblocks, true);
            return true;
        }
        if (id == LOTRMod.proxy.getBeamRenderID()) {
            this.renderBeam(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getVCauldronRenderID()) {
            this.renderVanillaCauldron(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getGrapevineRenderID()) {
            this.renderGrapevine(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getThatchFloorRenderID()) {
            if (fancyGraphics) {
                this.renderFallenLeaves(world, i, j, k, block, renderblocks, new int[]{10, 16}, new int[]{6, 12}, new int[]{1, 1}, 1.0f);
                return true;
            }
            return renderblocks.renderStandardBlock(block, i, j, k);
        }
        if (id == LOTRMod.proxy.getTreasureRenderID()) {
            this.renderBlockRandomRotated(world, i, j, k, block, renderblocks, false);
            return true;
        }
        if (id == LOTRMod.proxy.getFlowerRenderID()) {
            this.renderFlowerBlock(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getDoublePlantRenderID()) {
            this.renderDoublePlant(world, i, j, k, (BlockDoublePlant)block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getBirdCageRenderID()) {
            this.renderBirdCage(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getRhunFireJarRenderID()) {
            this.renderRhunFireJar(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getCoralRenderID()) {
            this.renderCoral(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getDoorRenderID()) {
            return this.renderDoor(world, i, j, k, block, renderblocks);
        }
        if (id == LOTRMod.proxy.getRopeRenderID()) {
            this.renderRope(world, i, j, k, block, renderblocks);
            return true;
        }
        if (id == LOTRMod.proxy.getOrcChainRenderID()) {
            IIcon icon = renderblocks.getIconSafe(block.getIcon(world, i, j, k, 0));
            renderblocks.setOverrideBlockTexture(icon);
            boolean flag = renderblocks.renderCrossedSquares(block, i, j, k);
            renderblocks.clearOverrideBlockTexture();
            return flag;
        }
        if (id == LOTRMod.proxy.getGuldurilRenderID()) {
            return renderblocks.renderStandardBlock(block, i, j, k);
        }
        if (id == LOTRMod.proxy.getOrcPlatingRenderID()) {
            this.renderBlockRandomRotated(world, i, j, k, block, renderblocks, true);
            return true;
        }
        if (id == LOTRMod.proxy.getTrapdoorRenderID()) {
            this.renderTrapdoor(world, i, j, k, block, renderblocks);
            return true;
        }
        return false;
    }

    public void renderInventoryBlock(Block block, int meta, int id, RenderBlocks renderblocks) {
        if (id == LOTRMod.proxy.getBeaconRenderID()) {
            ((LOTRRenderBeacon)TileEntityRendererDispatcher.instance.getSpecialRendererByClass(LOTRTileEntityBeacon.class)).renderInvBeacon();
        }
        if (id == LOTRMod.proxy.getBarrelRenderID()) {
            this.renderInvBarrel(block, renderblocks);
        }
        if (id == LOTRMod.proxy.getOrcBombRenderID()) {
            this.renderInvOrcBomb(block, meta, renderblocks);
        }
        if (id == LOTRMod.proxy.getMobSpawnerRenderID()) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
            ((LOTRTileEntityMobSpawnerRenderer)TileEntityRendererDispatcher.instance.getSpecialRendererByClass(LOTRTileEntityMobSpawner.class)).renderInvMobSpawner(meta);
        }
        if (id == LOTRMod.proxy.getStalactiteRenderID()) {
            this.renderInvStalactite(block, meta, renderblocks);
        }
        if (id == LOTRMod.proxy.getCloverRenderID()) {
            LOTRRenderBlocks.renderInvClover(block, renderblocks, meta == 1 ? 4 : 3);
        }
        if (id == LOTRMod.proxy.getEntJarRenderID()) {
            this.renderInvEntJar(block, renderblocks);
        }
        if (id == LOTRMod.proxy.getTrollTotemRenderID()) {
            ((LOTRRenderTrollTotem)TileEntityRendererDispatcher.instance.getSpecialRendererByClass(LOTRTileEntityTrollTotem.class)).renderInvTrollTotem(meta);
        }
        if (id == LOTRMod.proxy.getFenceRenderID()) {
            this.renderInvFence(block, meta, renderblocks);
        }
        if (id == LOTRMod.proxy.getCommandTableRenderID()) {
            this.renderInvCommandTable(block, renderblocks);
            ((LOTRRenderCommandTable)TileEntityRendererDispatcher.instance.getSpecialRendererByClass(LOTRTileEntityCommandTable.class)).renderInvTable();
        }
        if (id == LOTRMod.proxy.getButterflyJarRenderID()) {
            this.renderInvButterflyJar(block, renderblocks);
        }
        if (id == LOTRMod.proxy.getUnsmelteryRenderID()) {
            ((LOTRRenderUnsmeltery)TileEntityRendererDispatcher.instance.getSpecialRendererByClass(LOTRTileEntityUnsmeltery.class)).renderInvUnsmeltery();
        }
        if (id == LOTRMod.proxy.getChestRenderID()) {
            ((LOTRRenderChest)TileEntityRendererDispatcher.instance.getSpecialRendererByClass(LOTRTileEntityChest.class)).renderInvChest(block, meta);
        }
        if (id == LOTRMod.proxy.getWasteRenderID()) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, meta);
        }
        if (id == LOTRMod.proxy.getBeamRenderID()) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, meta);
        }
        if (id == LOTRMod.proxy.getTreasureRenderID()) {
            LOTRBlockTreasurePile.setTreasureBlockBounds(block, meta);
            renderblocks.setRenderBoundsFromBlock(block);
            renderblocks.lockBlockBounds = true;
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, meta);
            renderblocks.unlockBlockBounds();
        }
        if (id == LOTRMod.proxy.getBirdCageRenderID()) {
            this.renderInvBirdCage(block, renderblocks, meta);
        }
        if (id == LOTRMod.proxy.getRhunFireJarRenderID()) {
            this.renderInvRhunFireJar(block, renderblocks, meta);
        }
        if (id == LOTRMod.proxy.getCoralRenderID()) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, meta);
        }
        if (id == LOTRMod.proxy.getGuldurilRenderID()) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, meta);
            ((LOTRRenderGuldurilGlow)TileEntityRendererDispatcher.instance.getSpecialRendererByClass(LOTRTileEntityGulduril.class)).renderInvGlow();
        }
        if (id == LOTRMod.proxy.getOrcPlatingRenderID()) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, meta);
        }
        if (id == LOTRMod.proxy.getTrapdoorRenderID()) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, meta);
        }
    }

    public boolean shouldRender3DInInventory(int modelID) {
        return this.renderInvIn3D;
    }

    public int getRenderId() {
        return 0;
    }

    private void renderBeacon(IBlockAccess world, int i, int j, int k, RenderBlocks renderblocks) {
        if (LOTRBlockBeacon.isFullyLit(world, i, j, k)) {
            renderblocks.renderBlockFire(Blocks.fire, i, j, k);
        }
    }

    private void renderKelp(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        block.setBlockBoundsBasedOnState(world, i, j, k);
        renderblocks.setRenderBoundsFromBlock(block);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        int c = block.colorMultiplier(world, i, j, k);
        float r = (float)(c >> 16 & 0xFF) / 255.0f;
        float g = (float)(c >> 8 & 0xFF) / 255.0f;
        float b = (float)(c & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float r1 = (r * 30.0f + g * 59.0f + b * 11.0f) / 100.0f;
            float g1 = (r * 30.0f + g * 70.0f) / 100.0f;
            float b1 = (r * 30.0f + b * 70.0f) / 100.0f;
            r = r1;
            g = g1;
            b = b1;
        }
        tessellator.setColorOpaque_F(r, g, b);
        IIcon iicon = renderblocks.getBlockIcon(block, world, i, j, k, 0);
        renderblocks.drawCrossedSquares(iicon, (double)i, (double)j, (double)k, 1.0f);
    }

    private void renderBarrel(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        renderblocks.setRenderBounds(0.15625, 0.0625, 0.15625, 0.84375, 0.75, 0.84375);
        renderblocks.renderStandardBlock(block, i, j, k);
        for (float f : new float[]{0.25f, 0.5f}) {
            renderblocks.setRenderBounds(0.125, (double)f, 0.125, 0.875, (double)(f + 0.0625f), 0.875);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        for (float f : new float[]{0.0f, 0.6875f}) {
            renderblocks.setRenderBounds(0.125, (double)f, 0.125, 0.25, (double)(f + 0.125f), 0.875);
            renderblocks.renderStandardBlock(block, i, j, k);
            renderblocks.setRenderBounds(0.75, (double)f, 0.125, 0.875, (double)(f + 0.125f), 0.875);
            renderblocks.renderStandardBlock(block, i, j, k);
            renderblocks.setRenderBounds(0.25, (double)f, 0.125, 0.75, (double)(f + 0.125f), 0.25);
            renderblocks.renderStandardBlock(block, i, j, k);
            renderblocks.setRenderBounds(0.25, (double)f, 0.75, 0.75, (double)(f + 0.125f), 0.875);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        renderblocks.setOverrideBlockTexture(block.getBlockTextureFromSide(-1));
        int meta = world.getBlockMetadata(i, j, k);
        if (meta == 2) {
            renderblocks.setRenderBounds(0.4375, 0.25, 0.0, 0.5625, 0.375, 0.125);
            renderblocks.renderStandardBlock(block, i, j, k);
            renderblocks.setRenderBounds(0.46875, 0.125, 0.0, 0.53125, 0.25, 0.0625);
            renderblocks.renderStandardBlock(block, i, j, k);
        } else if (meta == 3) {
            renderblocks.setRenderBounds(0.4375, 0.25, 0.875, 0.5625, 0.375, 1.0);
            renderblocks.renderStandardBlock(block, i, j, k);
            renderblocks.setRenderBounds(0.46875, 0.125, 0.9375, 0.53125, 0.25, 1.0);
            renderblocks.renderStandardBlock(block, i, j, k);
        } else if (meta == 4) {
            renderblocks.setRenderBounds(0.0, 0.25, 0.4375, 0.125, 0.375, 0.5625);
            renderblocks.renderStandardBlock(block, i, j, k);
            renderblocks.setRenderBounds(0.0, 0.125, 0.46875, 0.0625, 0.25, 0.53125);
            renderblocks.renderStandardBlock(block, i, j, k);
        } else if (meta == 5) {
            renderblocks.setRenderBounds(0.875, 0.25, 0.4375, 1.0, 0.375, 0.5625);
            renderblocks.renderStandardBlock(block, i, j, k);
            renderblocks.setRenderBounds(0.9375, 0.125, 0.46875, 1.0, 0.25, 0.53125);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    private void renderInvBarrel(Block block, RenderBlocks renderblocks) {
        renderblocks.renderAllFaces = true;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.15625, 0.0625, 0.15625, 0.84375, 0.75, 0.84375);
        for (float f : new float[]{0.25f, 0.5f}) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.125, f, 0.125, 0.875, f + 0.0625f, 0.875);
        }
        for (float f : new float[]{0.0f, 0.6875f}) {
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.125, f, 0.125, 0.25, f + 0.125f, 0.875);
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.75, f, 0.125, 0.875, f + 0.125f, 0.875);
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, f, 0.125, 0.75, f + 0.125f, 0.25);
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, f, 0.75, 0.75, f + 0.125f, 0.875);
        }
        renderblocks.setOverrideBlockTexture(block.getBlockTextureFromSide(-1));
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.875, 0.25, 0.4375, 1.0, 0.375, 0.5625);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.9375, 0.125, 0.46875, 1.0, 0.25, 0.53125);
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
    }

    private void renderOrcBomb(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        renderblocks.setRenderBounds(0.125, 0.1875, 0.125, 0.875, 0.9375, 0.875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.375, 0.9375, 0.375, 0.625, 1.0, 0.625);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.25, 0.9375, 0.375, 0.3125, 1.0, 0.4375);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.25, 0.9375, 0.5625, 0.3125, 1.0, 0.625);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.6875, 0.9375, 0.375, 0.75, 1.0, 0.4375);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.6875, 0.9375, 0.5625, 0.75, 1.0, 0.625);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.375, 0.9375, 0.25, 0.4375, 1.0, 0.3125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.5625, 0.9375, 0.25, 0.625, 1.0, 0.3125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.375, 0.9375, 0.6875, 0.4375, 1.0, 0.75);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.5625, 0.9375, 0.6875, 0.625, 1.0, 0.75);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.125, 0.0, 0.25, 0.1875, 0.1875, 0.75);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.8125, 0.0, 0.25, 0.875, 0.1875, 0.75);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.25, 0.0, 0.125, 0.75, 0.1875, 0.1875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.25, 0.0, 0.8125, 0.75, 0.1875, 0.875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setOverrideBlockTexture(block.getIcon(-1, world.getBlockMetadata(i, j, k)));
        renderblocks.setRenderBounds(0.0, 0.625, 0.3125, 0.0625, 0.6875, 0.6875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.0625, 0.625, 0.3125, 0.125, 0.6875, 0.375);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.0625, 0.625, 0.625, 0.125, 0.6875, 0.6875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.9375, 0.625, 0.3125, 1.0, 0.6875, 0.6875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.875, 0.625, 0.3125, 0.9375, 0.6875, 0.375);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.875, 0.625, 0.625, 0.9375, 0.6875, 0.6875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.3125, 0.625, 0.0, 0.6875, 0.6875, 0.0625);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.3125, 0.625, 0.0625, 0.375, 0.6875, 0.125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.625, 0.625, 0.0625, 0.6875, 0.6875, 0.125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.3125, 0.625, 0.9375, 0.6875, 0.6875, 1.0);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.3125, 0.625, 0.875, 0.375, 0.6875, 0.9375);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.625, 0.625, 0.875, 0.6875, 0.6875, 0.9375);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    private void renderInvOrcBomb(Block block, int meta, RenderBlocks renderblocks) {
        renderblocks.renderAllFaces = true;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.125, 0.1875, 0.125, 0.875, 0.9375, 0.875, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.375, 0.9375, 0.375, 0.625, 1.0, 0.625, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.9375, 0.375, 0.3125, 1.0, 0.4375, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.9375, 0.5625, 0.3125, 1.0, 0.625, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.6875, 0.9375, 0.375, 0.75, 1.0, 0.4375, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.6875, 0.9375, 0.5625, 0.75, 1.0, 0.625, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.375, 0.9375, 0.25, 0.4375, 1.0, 0.3125, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.5625, 0.9375, 0.25, 0.625, 1.0, 0.3125, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.375, 0.9375, 0.6875, 0.4375, 1.0, 0.75, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.5625, 0.9375, 0.6875, 0.625, 1.0, 0.75, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.125, 0.0, 0.25, 0.1875, 0.1875, 0.75, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.8125, 0.0, 0.25, 0.875, 0.1875, 0.75, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.0, 0.125, 0.75, 0.1875, 0.1875, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.0, 0.8125, 0.75, 0.1875, 0.875, meta);
        renderblocks.setOverrideBlockTexture(block.getIcon(-1, meta));
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.625, 0.3125, 0.0625, 0.6875, 0.6875, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0625, 0.625, 0.3125, 0.125, 0.6875, 0.375, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0625, 0.625, 0.625, 0.125, 0.6875, 0.6875, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.9375, 0.625, 0.3125, 1.0, 0.6875, 0.6875, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.875, 0.625, 0.3125, 0.9375, 0.6875, 0.375, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.875, 0.625, 0.625, 0.9375, 0.6875, 0.6875, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.3125, 0.625, 0.0, 0.6875, 0.6875, 0.0625, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.3125, 0.625, 0.0625, 0.375, 0.6875, 0.125, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.625, 0.625, 0.0625, 0.6875, 0.6875, 0.125, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.3125, 0.625, 0.9375, 0.6875, 0.6875, 1.0, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.3125, 0.625, 0.875, 0.375, 0.6875, 0.9375, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.625, 0.625, 0.875, 0.6875, 0.6875, 0.9375, meta);
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
    }

    private void renderDoubleTorch(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        block.setBlockBoundsBasedOnState(world, i, j, k);
        renderblocks.setRenderBoundsFromBlock(block);
        renderblocks.renderStandardBlock(block, i, j, k);
    }

    private void renderPlate(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        renderblocks.setRenderBounds(0.1875, 0.0, 0.1875, 0.8125, 0.0625, 0.8125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.125, 0.0625, 0.125, 0.875, 0.125, 0.875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    public static void renderEntityPlate(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        renderblocks.renderAllFaces = true;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.1875, 0.0, 0.1875, 0.8125, 0.0625, 0.8125);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.125, 0.0625, 0.125, 0.875, 0.125, 0.875);
        renderblocks.renderAllFaces = false;
    }

    private void renderStalactite(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        int metadata = world.getBlockMetadata(i, j, k);
        for (int l = 0; l < 16; ++l) {
            if (metadata == 0) {
                renderblocks.setRenderBounds((double)(0.5f - (float)l / 16.0f * 0.25f), (double)((float)l / 16.0f), (double)(0.5f - (float)l / 16.0f * 0.25f), (double)(0.5f + (float)l / 16.0f * 0.25f), (double)((float)(l + 1) / 16.0f), (double)(0.5f + (float)l / 16.0f * 0.25f));
                renderblocks.renderStandardBlock(block, i, j, k);
                continue;
            }
            if (metadata != 1) continue;
            renderblocks.setRenderBounds((double)(0.25f + (float)l / 16.0f * 0.25f), (double)((float)l / 16.0f), (double)(0.25f + (float)l / 16.0f * 0.25f), (double)(0.75f - (float)l / 16.0f * 0.25f), (double)((float)(l + 1) / 16.0f), (double)(0.75f - (float)l / 16.0f * 0.25f));
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    private void renderInvStalactite(Block block, int metadata, RenderBlocks renderblocks) {
        renderblocks.renderAllFaces = true;
        for (int l = 0; l < 16; ++l) {
            if (metadata == 0) {
                LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.5f - (float)l / 16.0f * 0.25f, (float)l / 16.0f, 0.5f - (float)l / 16.0f * 0.25f, 0.5f + (float)l / 16.0f * 0.25f, (float)(l + 1) / 16.0f, 0.5f + (float)l / 16.0f * 0.25f);
                continue;
            }
            if (metadata != 1) continue;
            LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25f + (float)l / 16.0f * 0.25f, (float)l / 16.0f, 0.25f + (float)l / 16.0f * 0.25f, 0.75f - (float)l / 16.0f * 0.25f, (float)(l + 1) / 16.0f, 0.75f - (float)l / 16.0f * 0.25f);
        }
        renderblocks.renderAllFaces = false;
    }

    private void renderFlowerPot(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        float f4;
        renderblocks.renderStandardBlock(block, i, j, k);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        float f = 1.0f;
        int l = block.colorMultiplier(world, i, j, k);
        IIcon icon = renderblocks.getBlockIconFromSide(block, 0);
        float f1 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(l & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            f4 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
            float f5 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
            float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        f4 = 0.1865f;
        renderblocks.renderFaceXPos(block, (double)((float)i - 0.5f + f4), (double)j, (double)k, icon);
        renderblocks.renderFaceXNeg(block, (double)((float)i + 0.5f - f4), (double)j, (double)k, icon);
        renderblocks.renderFaceZPos(block, (double)i, (double)j, (double)((float)k - 0.5f + f4), icon);
        renderblocks.renderFaceZNeg(block, (double)i, (double)j, (double)((float)k + 0.5f - f4), icon);
        renderblocks.renderFaceYPos(block, (double)i, (double)((float)j - 0.5f + f4 + 0.1875f), (double)k, renderblocks.getBlockIcon(Blocks.dirt));
        ItemStack plant = LOTRBlockFlowerPot.getPlant(world, i, j, k);
        if (plant != null) {
            int plantMeta;
            Block plantBlock = Block.getBlockFromItem((Item)plant.getItem());
            int plantColor = plantBlock.getRenderColor(plantMeta = plant.getItemDamage());
            if (plantColor != 16777215) {
                float plantR = (float)(plantColor >> 16 & 0xFF) / 255.0f;
                float plantG = (float)(plantColor >> 8 & 0xFF) / 255.0f;
                float plantB = (float)(plantColor & 0xFF) / 255.0f;
                tessellator.setColorOpaque_F(plantR, plantG, plantB);
            }
            tessellator.addTranslation(0.0f, 0.25f, 0.0f);
            if (plantBlock == LOTRMod.shireHeather) {
                renderblocks.drawCrossedSquares(plantBlock.getIcon(2, plantMeta), (double)i, (double)j, (double)k, 0.6f);
            } else if (plantBlock == LOTRMod.clover) {
                LOTRRenderBlocks.renderClover(world, i, j, k, plantBlock, renderblocks, plantMeta == 1 ? 4 : 3, false);
            } else if (plantBlock instanceof LOTRBlockGrass) {
                LOTRRenderBlocks.renderGrass(world, i, j, k, plantBlock, renderblocks, false);
            } else if (plantBlock instanceof LOTRBlockFlower) {
                renderblocks.drawCrossedSquares(plantBlock.getIcon(2, plantMeta), (double)i, (double)j, (double)k, 0.75f);
            } else if (plantBlock.getRenderType() == 1) {
                renderblocks.drawCrossedSquares(plantBlock.getIcon(2, plantMeta), (double)i, (double)j, (double)k, 0.75f);
            } else {
                renderblocks.renderBlockByRenderType(plantBlock, i, j, k);
            }
            tessellator.addTranslation(0.0f, -0.25f, 0.0f);
        }
    }

    private static void renderClover(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks, int petalCount, boolean randomTranslation) {
        double scale = 0.5;
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        int l = block.colorMultiplier(world, i, j, k);
        float f = 1.0f;
        float f1 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(l & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float f4 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
            float f5 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
            float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        renderblocks.setOverrideBlockTexture(LOTRBlockClover.stemIcon);
        double posX = i;
        double posY = j;
        double posZ = k;
        if (randomTranslation) {
            long seed = (long)(i * 3129871) ^ (long)k * 116129781L ^ (long)j;
            seed = seed * seed * 42317861L + seed * 11L;
            posX += ((double)((float)(seed >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5;
            posZ += ((double)((float)(seed >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5;
        }
        renderblocks.drawCrossedSquares(block.getIcon(2, 0), posX, posY, posZ, (float)scale);
        renderblocks.clearOverrideBlockTexture();
        for (int petal = 0; petal < petalCount; ++petal) {
            float rotation = (float)petal / (float)petalCount * 3.1415927f * 2.0f;
            IIcon icon = LOTRBlockClover.petalIcon;
            double d = icon.getMinU();
            double d1 = icon.getMinV();
            double d2 = icon.getMaxU();
            double d3 = icon.getMaxV();
            double d4 = posX + 0.5;
            double d5 = posY + 0.5 * scale + (double)petal * 0.0025;
            double d6 = posZ + 0.5;
            Vec3[] vecs = new Vec3[]{Vec3.createVectorHelper((double)(0.5 * scale), (double)0.0, (double)(-0.5 * scale)), Vec3.createVectorHelper((double)(-0.5 * scale), (double)0.0, (double)(-0.5 * scale)), Vec3.createVectorHelper((double)(-0.5 * scale), (double)0.0, (double)(0.5 * scale)), Vec3.createVectorHelper((double)(0.5 * scale), (double)0.0, (double)(0.5 * scale))};
            for (int l1 = 0; l1 < vecs.length; ++l1) {
                vecs[l1].rotateAroundY(rotation);
                vecs[l1].xCoord += d4;
                vecs[l1].yCoord += d5;
                vecs[l1].zCoord += d6;
            }
            tessellator.addVertexWithUV(vecs[0].xCoord, vecs[0].yCoord, vecs[0].zCoord, d, d1);
            tessellator.addVertexWithUV(vecs[1].xCoord, vecs[1].yCoord, vecs[1].zCoord, d, d3);
            tessellator.addVertexWithUV(vecs[2].xCoord, vecs[2].yCoord, vecs[2].zCoord, d2, d3);
            tessellator.addVertexWithUV(vecs[3].xCoord, vecs[3].yCoord, vecs[3].zCoord, d2, d1);
            tessellator.addVertexWithUV(vecs[3].xCoord, vecs[3].yCoord, vecs[3].zCoord, d2, d1);
            tessellator.addVertexWithUV(vecs[2].xCoord, vecs[2].yCoord, vecs[2].zCoord, d2, d3);
            tessellator.addVertexWithUV(vecs[1].xCoord, vecs[1].yCoord, vecs[1].zCoord, d, d3);
            tessellator.addVertexWithUV(vecs[0].xCoord, vecs[0].yCoord, vecs[0].zCoord, d, d1);
        }
    }

    private static void renderInvClover(Block block, RenderBlocks renderblocks, int petalCount) {
        GL11.glDisable((int)2896);
        double scale = 1.0;
        Tessellator tessellator = Tessellator.instance;
        int l = block.getRenderColor(0);
        float f = 1.0f;
        float f1 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(l & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float f4 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
            float f5 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
            float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        renderblocks.setOverrideBlockTexture(LOTRBlockClover.stemIcon);
        tessellator.startDrawingQuads();
        renderblocks.drawCrossedSquares(block.getIcon(2, 0), -scale * 0.5, -scale * 0.5, -scale * 0.5, (float)scale);
        tessellator.draw();
        renderblocks.clearOverrideBlockTexture();
        for (int petal = 0; petal < petalCount; ++petal) {
            float rotation = (float)petal / (float)petalCount * 3.1415927f * 2.0f;
            IIcon icon = LOTRBlockClover.petalIcon;
            double d = icon.getMinU();
            double d1 = icon.getMinV();
            double d2 = icon.getMaxU();
            double d3 = icon.getMaxV();
            double d4 = 0.0;
            double d5 = (double)petal * 0.0025;
            double d6 = 0.0;
            Vec3[] vecs = new Vec3[]{Vec3.createVectorHelper((double)(0.5 * scale), (double)0.0, (double)(-0.5 * scale)), Vec3.createVectorHelper((double)(-0.5 * scale), (double)0.0, (double)(-0.5 * scale)), Vec3.createVectorHelper((double)(-0.5 * scale), (double)0.0, (double)(0.5 * scale)), Vec3.createVectorHelper((double)(0.5 * scale), (double)0.0, (double)(0.5 * scale))};
            for (int l1 = 0; l1 < vecs.length; ++l1) {
                vecs[l1].rotateAroundY(rotation);
                vecs[l1].xCoord += d4;
                vecs[l1].yCoord += d5;
                vecs[l1].zCoord += d6;
            }
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(vecs[0].xCoord, vecs[0].yCoord, vecs[0].zCoord, d, d1);
            tessellator.addVertexWithUV(vecs[1].xCoord, vecs[1].yCoord, vecs[1].zCoord, d, d3);
            tessellator.addVertexWithUV(vecs[2].xCoord, vecs[2].yCoord, vecs[2].zCoord, d2, d3);
            tessellator.addVertexWithUV(vecs[3].xCoord, vecs[3].yCoord, vecs[3].zCoord, d2, d1);
            tessellator.addVertexWithUV(vecs[3].xCoord, vecs[3].yCoord, vecs[3].zCoord, d2, d1);
            tessellator.addVertexWithUV(vecs[2].xCoord, vecs[2].yCoord, vecs[2].zCoord, d2, d3);
            tessellator.addVertexWithUV(vecs[1].xCoord, vecs[1].yCoord, vecs[1].zCoord, d, d3);
            tessellator.addVertexWithUV(vecs[0].xCoord, vecs[0].yCoord, vecs[0].zCoord, d, d1);
            tessellator.draw();
        }
        GL11.glEnable((int)2896);
    }

    public static void renderDwarvenDoorGlow(LOTRBlockGateDwarvenIthildin block, RenderBlocks renderblocks, int i, int j, int k) {
        Tessellator tessellator = Tessellator.instance;
        block.setBlockBoundsBasedOnState(renderblocks.blockAccess, i, j, k);
        renderblocks.setRenderBoundsFromBlock((Block)block);
        double d = 0.01;
        IIcon icon = block.getGlowIcon(renderblocks.blockAccess, i, j, k, 0);
        if (icon != null) {
            tessellator.startDrawingQuads();
            renderblocks.renderFaceYNeg((Block)block, 0.0, -d, 0.0, icon);
            tessellator.draw();
            renderblocks.flipTexture = false;
        }
        if ((icon = block.getGlowIcon(renderblocks.blockAccess, i, j, k, 1)) != null) {
            tessellator.startDrawingQuads();
            renderblocks.renderFaceYPos((Block)block, 0.0, d, 0.0, icon);
            tessellator.draw();
            renderblocks.flipTexture = false;
        }
        if ((icon = block.getGlowIcon(renderblocks.blockAccess, i, j, k, 2)) != null) {
            tessellator.startDrawingQuads();
            renderblocks.renderFaceZNeg((Block)block, 0.0, 0.0, -d, icon);
            tessellator.draw();
            renderblocks.flipTexture = false;
        }
        if ((icon = block.getGlowIcon(renderblocks.blockAccess, i, j, k, 3)) != null) {
            tessellator.startDrawingQuads();
            renderblocks.renderFaceZPos((Block)block, 0.0, 0.0, d, icon);
            tessellator.draw();
            renderblocks.flipTexture = false;
        }
        if ((icon = block.getGlowIcon(renderblocks.blockAccess, i, j, k, 4)) != null) {
            tessellator.startDrawingQuads();
            renderblocks.renderFaceXNeg((Block)block, -d, 0.0, 0.0, icon);
            tessellator.draw();
            renderblocks.flipTexture = false;
        }
        if ((icon = block.getGlowIcon(renderblocks.blockAccess, i, j, k, 5)) != null) {
            tessellator.startDrawingQuads();
            renderblocks.renderFaceXPos((Block)block, d, 0.0, 0.0, icon);
            tessellator.draw();
            renderblocks.flipTexture = false;
        }
    }

    private void renderEntJar(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        renderblocks.setRenderBounds(0.25, 0.0, 0.25, 0.75, 0.0625, 0.75);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.25, 0.0625, 0.25, 0.75, 0.875, 0.3125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.25, 0.0625, 0.6875, 0.75, 0.875, 0.75);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.25, 0.0625, 0.3125, 0.31255000829696655, 0.875, 0.6875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.6875, 0.0625, 0.3125, 0.75, 0.875, 0.6875);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    private void renderInvEntJar(Block block, RenderBlocks renderblocks) {
        renderblocks.renderAllFaces = true;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.0, 0.25, 0.75, 0.0625, 0.75);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.0625, 0.25, 0.75, 0.875, 0.3125);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.0625, 0.6875, 0.75, 0.875, 0.75);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.0625, 0.3125, 0.31255000829696655, 0.875, 0.6875);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.6875, 0.0625, 0.3125, 0.75, 0.875, 0.6875);
        renderblocks.renderAllFaces = false;
    }

    private void renderInvFence(Block block, int meta, RenderBlocks renderblocks) {
        for (int k = 0; k < 4; ++k) {
            float f = 0.125f;
            float f1 = 0.0625f;
            if (k == 0) {
                renderblocks.setRenderBounds((double)(0.5f - f), 0.0, 0.0, (double)(0.5f + f), 1.0, (double)(f * 2.0f));
            }
            if (k == 1) {
                renderblocks.setRenderBounds((double)(0.5f - f), 0.0, (double)(1.0f - f * 2.0f), (double)(0.5f + f), 1.0, 1.0);
            }
            if (k == 2) {
                renderblocks.setRenderBounds((double)(0.5f - f1), (double)(1.0f - f1 * 3.0f), (double)(-f1 * 2.0f), (double)(0.5f + f1), (double)(1.0f - f1), (double)(1.0f + f1 * 2.0f));
            }
            if (k == 3) {
                renderblocks.setRenderBounds((double)(0.5f - f1), (double)(0.5f - f1 * 3.0f), (double)(-f1 * 2.0f), (double)(0.5f + f1), (double)(0.5f - f1), (double)(1.0f + f1 * 2.0f));
            }
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, -1.0f, 0.0f);
            renderblocks.renderFaceYNeg(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 0, meta));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, 1.0f, 0.0f);
            renderblocks.renderFaceYPos(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 1, meta));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, 0.0f, -1.0f);
            renderblocks.renderFaceZNeg(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 2, meta));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, 0.0f, 1.0f);
            renderblocks.renderFaceZPos(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 3, meta));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0f, 0.0f, 0.0f);
            renderblocks.renderFaceXNeg(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 4, meta));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0f, 0.0f, 0.0f);
            renderblocks.renderFaceXPos(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 5, meta));
            tessellator.draw();
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        }
        renderblocks.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
    }

    private static void renderGrass(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks, boolean randomTranslation) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        int meta = world.getBlockMetadata(i, j, k);
        int l = block.colorMultiplier(world, i, j, k);
        float f = 1.0f;
        float f1 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(l & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float f4 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
            float f5 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
            float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        double posX = i;
        double posY = j;
        double posZ = k;
        if (randomTranslation) {
            long seed = (long)(i * 3129871) ^ (long)k * 116129781L ^ (long)j;
            seed = seed * seed * 42317861L + seed * 11L;
            posX += ((double)((float)(seed >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5;
            posY += ((double)((float)(seed >> 20 & 0xFL) / 15.0f) - 1.0) * 0.2;
            posZ += ((double)((float)(seed >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5;
        }
        renderblocks.drawCrossedSquares(block.getIcon(2, meta), posX, posY, posZ, 1.0f);
        renderblocks.clearOverrideBlockTexture();
        if (block == LOTRMod.tallGrass && meta >= 0 && meta < LOTRBlockTallGrass.grassOverlay.length && LOTRBlockTallGrass.grassOverlay[meta]) {
            tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
            renderblocks.drawCrossedSquares(block.getIcon(-1, meta), posX, posY, posZ, 1.0f);
            renderblocks.clearOverrideBlockTexture();
        }
    }

    private void renderFallenLeaves(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks, int[] minMaxLeaves, int[] minMaxXSize, int[] minMaxZSize, float shade) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        int meta = world.getBlockMetadata(i, j, k);
        int color = block.colorMultiplier(world, i, j, k);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        r *= shade;
        g *= shade;
        b *= shade;
        if (EntityRenderer.anaglyphEnable) {
            r = (r * 30.0f + g * 59.0f + b * 11.0f) / 100.0f;
            g = (r * 30.0f + g * 70.0f) / 100.0f;
            b = (r * 30.0f + b * 70.0f) / 100.0f;
        }
        tessellator.setColorOpaque_F(r, g, b);
        long seed = (long)i * 237690503L ^ (long)k * 2689286L ^ (long)j;
        seed = seed * seed * 1732965593L + seed * 673L;
        blockRand.setSeed(seed);
        IIcon icon = block.getIcon(world, i, j, k, 0);
        int leaves = MathHelper.getRandomIntegerInRange((Random)blockRand, (int)minMaxLeaves[0], (int)minMaxLeaves[1]);
        for (int l = 0; l < leaves; ++l) {
            double posX = (float)i + blockRand.nextFloat();
            double posZ = (float)k + blockRand.nextFloat();
            double posY = (float)j + 0.01f + (float)l / (float)leaves * 0.1f;
            float rotation = blockRand.nextFloat() * 3.1415927f * 2.0f;
            int xSize = MathHelper.getRandomIntegerInRange((Random)blockRand, (int)minMaxXSize[0], (int)minMaxXSize[1]);
            int zSize = MathHelper.getRandomIntegerInRange((Random)blockRand, (int)minMaxZSize[0], (int)minMaxZSize[1]);
            double xSizeD = (double)xSize / 16.0;
            double zSizeD = (double)zSize / 16.0;
            int intMinU = MathHelper.getRandomIntegerInRange((Random)blockRand, (int)0, (int)(16 - xSize));
            int intMinV = MathHelper.getRandomIntegerInRange((Random)blockRand, (int)0, (int)(16 - zSize));
            double minU = icon.getInterpolatedU((double)intMinU);
            double minV = icon.getInterpolatedV((double)intMinV);
            double maxU = icon.getInterpolatedU((double)(intMinU + xSize));
            double maxV = icon.getInterpolatedV((double)(intMinV + zSize));
            double x2 = xSizeD / 2.0;
            double z2 = zSizeD / 2.0;
            Vec3[] vecs = new Vec3[]{Vec3.createVectorHelper((double)(-x2), (double)0.0, (double)(-z2)), Vec3.createVectorHelper((double)(-x2), (double)0.0, (double)z2), Vec3.createVectorHelper((double)x2, (double)0.0, (double)z2), Vec3.createVectorHelper((double)x2, (double)0.0, (double)(-z2))};
            for (int v = 0; v < vecs.length; ++v) {
                Vec3 vec = vecs[v];
                vec.rotateAroundY(rotation);
                vec.xCoord += posX;
                vec.yCoord += posY;
                vec.zCoord += posZ;
            }
            tessellator.addVertexWithUV(vecs[0].xCoord, vecs[0].yCoord, vecs[0].zCoord, minU, minV);
            tessellator.addVertexWithUV(vecs[1].xCoord, vecs[1].yCoord, vecs[1].zCoord, minU, maxV);
            tessellator.addVertexWithUV(vecs[2].xCoord, vecs[2].yCoord, vecs[2].zCoord, maxU, maxV);
            tessellator.addVertexWithUV(vecs[3].xCoord, vecs[3].yCoord, vecs[3].zCoord, maxU, minV);
        }
    }

    private void renderCommandTable(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        renderblocks.setRenderBoundsFromBlock(block);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setOverrideBlockTexture(Blocks.planks.getIcon(0, 0));
        renderblocks.setRenderBounds(-0.5, 1.0, -0.5, 0.5, 1.0625, 0.5);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(-0.5, 1.0, 0.5, 0.5, 1.0625, 1.5);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.5, 1.0, -0.5, 1.5, 1.0625, 0.5);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.5, 1.0, 0.5, 1.5, 1.0625, 1.5);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    private void renderInvCommandTable(Block block, RenderBlocks renderblocks) {
        renderblocks.renderAllFaces = true;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        renderblocks.setOverrideBlockTexture(Blocks.planks.getIcon(0, 0));
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, -0.5, 1.0, -0.5, 1.5, 1.0625, 1.5);
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
    }

    private void renderButterflyJar(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        renderblocks.setRenderBounds(0.1875, 0.0, 0.1875, 0.8125, 0.5625, 0.8125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.1875, 0.0, 0.1875, 0.8125, 0.0625, 0.8125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.25, 0.5625, 0.25, 0.75, 0.6875, 0.75);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setOverrideBlockTexture(block.getIcon(-1, 0));
        renderblocks.setRenderBounds(0.1875, 0.6875, 0.1875, 0.8125, 0.75, 0.8125);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    private void renderInvButterflyJar(Block block, RenderBlocks renderblocks) {
        renderblocks.renderAllFaces = true;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.1875, 0.0, 0.1875, 0.8125, 0.5625, 0.8125);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.1875, 0.0, 0.1875, 0.8125, 0.0625, 0.8125);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.5625, 0.25, 0.75, 0.6875, 0.75);
        renderblocks.setOverrideBlockTexture(block.getIcon(-1, 0));
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.1875, 0.6875, 0.1875, 0.8125, 0.75, 0.8125);
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
    }

    private void renderReeds(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        block.setBlockBoundsBasedOnState(world, i, j, k);
        renderblocks.setRenderBoundsFromBlock(block);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        int c = block.colorMultiplier(world, i, j, k);
        float r = (float)(c >> 16 & 0xFF) / 255.0f;
        float g = (float)(c >> 8 & 0xFF) / 255.0f;
        float b = (float)(c & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float r1 = (r * 30.0f + g * 59.0f + b * 11.0f) / 100.0f;
            float g1 = (r * 30.0f + g * 70.0f) / 100.0f;
            float b1 = (r * 30.0f + b * 70.0f) / 100.0f;
            r = r1;
            g = g1;
            b = b1;
        }
        tessellator.setColorOpaque_F(r, g, b);
        double d = i;
        double d1 = j;
        double d2 = k;
        if (world.getBlock(i, j - 1, k) == block) {
            IIcon iicon = renderblocks.getBlockIcon(block, world, i, j, k, 0);
            renderblocks.drawCrossedSquares(iicon, d, d1, d2, 1.0f);
        } else {
            IIcon iicon = renderblocks.getBlockIcon(block, world, i, j, k, 0);
            renderblocks.drawCrossedSquares(iicon, d, d1, d2, 1.0f);
            for (int j1 = j - 1; j1 > 0; --j1) {
                d1 -= 1.0;
                tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j1, k));
                boolean lower = world.getBlock(i, j1 - 1, k).isOpaqueCube();
                if (lower) {
                    iicon = renderblocks.getBlockIcon(block, world, i, j, k, -2);
                    renderblocks.drawCrossedSquares(iicon, d, d1, d2, 1.0f);
                    break;
                }
                iicon = renderblocks.getBlockIcon(block, world, i, j, k, -1);
                renderblocks.drawCrossedSquares(iicon, d, d1, d2, 1.0f);
            }
        }
    }

    private void renderBlockRandomRotated(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks, boolean rotateSides) {
        int[] sides = new int[6];
        for (int l = 0; l < sides.length; ++l) {
            int hash = i * 234890405 ^ k * 37383934 ^ j;
            blockRand.setSeed(hash += l * 285502);
            blockRand.setSeed(blockRand.nextLong());
            sides[l] = blockRand.nextInt(4);
        }
        if (rotateSides) {
            renderblocks.uvRotateEast = sides[0];
            renderblocks.uvRotateWest = sides[1];
            renderblocks.uvRotateSouth = sides[2];
            renderblocks.uvRotateNorth = sides[3];
        }
        renderblocks.uvRotateTop = sides[4];
        renderblocks.uvRotateBottom = sides[5];
        renderblocks.renderStandardBlock(block, i, j, k);
        if (rotateSides) {
            renderblocks.uvRotateEast = 0;
            renderblocks.uvRotateWest = 0;
            renderblocks.uvRotateSouth = 0;
            renderblocks.uvRotateNorth = 0;
        }
        renderblocks.uvRotateTop = 0;
        renderblocks.uvRotateBottom = 0;
    }

    private void renderBeam(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int meta = world.getBlockMetadata(i, j, k);
        int dir = meta & 0xC;
        if (dir == 0) {
            renderblocks.uvRotateEast = 3;
            renderblocks.uvRotateNorth = 3;
        } else if (dir == 4) {
            renderblocks.uvRotateEast = 1;
            renderblocks.uvRotateWest = 2;
            renderblocks.uvRotateTop = 2;
            renderblocks.uvRotateBottom = 1;
        } else if (dir == 8) {
            renderblocks.uvRotateSouth = 1;
            renderblocks.uvRotateNorth = 2;
        }
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.uvRotateSouth = 0;
        renderblocks.uvRotateEast = 0;
        renderblocks.uvRotateWest = 0;
        renderblocks.uvRotateNorth = 0;
        renderblocks.uvRotateTop = 0;
        renderblocks.uvRotateBottom = 0;
    }

    private void renderVanillaCauldron(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        renderblocks.renderStandardBlock(block, i, j, k);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        int color = block.colorMultiplier(world, i, j, k);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float r1 = (r * 30.0f + g * 59.0f + b * 11.0f) / 100.0f;
            float g1 = (r * 30.0f + g * 70.0f) / 100.0f;
            float b1 = (r * 30.0f + b * 70.0f) / 100.0f;
            r = r1;
            g = g1;
            b = b1;
        }
        tessellator.setColorOpaque_F(r, g, b);
        IIcon iconSide = block.getBlockTextureFromSide(2);
        float w = 0.125f;
        renderblocks.renderFaceXPos(block, (double)((float)i - 1.0f + w), (double)j, (double)k, iconSide);
        renderblocks.renderFaceXNeg(block, (double)((float)i + 1.0f - w), (double)j, (double)k, iconSide);
        renderblocks.renderFaceZPos(block, (double)i, (double)j, (double)((float)k - 1.0f + w), iconSide);
        renderblocks.renderFaceZNeg(block, (double)i, (double)j, (double)((float)k + 1.0f - w), iconSide);
        IIcon iconInner = BlockCauldron.getCauldronIcon((String)"inner");
        renderblocks.renderFaceYPos(block, (double)i, (double)((float)j - 1.0f + 0.25f), (double)k, iconInner);
        renderblocks.renderFaceYNeg(block, (double)i, (double)((float)j + 1.0f - 0.75f), (double)k, iconInner);
        int meta = world.getBlockMetadata(i, j, k);
        if (meta > 0) {
            color = Blocks.water.colorMultiplier(world, i, j, k);
            r = (float)(color >> 16 & 0xFF) / 255.0f;
            g = (float)(color >> 8 & 0xFF) / 255.0f;
            b = (float)(color & 0xFF) / 255.0f;
            tessellator.setColorOpaque_F(r, g, b);
            IIcon iconWater = BlockLiquid.getLiquidIcon((String)"water_still");
            renderblocks.renderFaceYPos(block, (double)i, (double)((float)j - 1.0f + BlockCauldron.getRenderLiquidLevel((int)meta)), (double)k, iconWater);
        }
    }

    private void renderGrapevine(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        block.setBlockBoundsForItemRender();
        renderblocks.setRenderBoundsFromBlock(block);
        renderblocks.renderStandardBlock(block, i, j, k);
        int meta = world.getBlockMetadata(i, j, k);
        renderblocks.setOverrideBlockTexture(block.getIcon(-1, meta));
        block.setBlockBoundsBasedOnState(world, i, j, k);
        renderblocks.setRenderBoundsFromBlock(block);
        double d = 0.001;
        renderblocks.renderMinY += d;
        renderblocks.renderMaxY -= d;
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.clearOverrideBlockTexture();
    }

    private void renderFlowerBlock(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        int color = block.colorMultiplier(world, i, j, k);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float f3 = (r * 30.0f + g * 59.0f + b * 11.0f) / 100.0f;
            float f4 = (r * 30.0f + g * 70.0f) / 100.0f;
            float f5 = (r * 30.0f + b * 70.0f) / 100.0f;
            r = f3;
            g = f4;
            b = f5;
        }
        tessellator.setColorOpaque_F(r, g, b);
        double d = i;
        double d1 = j;
        double d2 = k;
        long seed = (long)(i * 3129871) ^ (long)k * 116129781L ^ (long)j;
        seed = seed * seed * 42317861L + seed * 11L;
        IIcon iicon = renderblocks.getBlockIconFromSideAndMetadata(block, 0, world.getBlockMetadata(i, j, k));
        renderblocks.drawCrossedSquares(iicon, d += ((double)((float)(seed >> 16 & 0xFL) / 15.0f) - 0.5) * 0.3, d1, d2 += ((double)((float)(seed >> 24 & 0xFL) / 15.0f) - 0.5) * 0.3, 1.0f);
    }

    private void renderDoublePlant(IBlockAccess world, int i, int j, int k, BlockDoublePlant block, RenderBlocks renderblocks) {
        int plantType;
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j, k));
        int color = block.colorMultiplier(world, i, j, k);
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float f3 = (r * 30.0f + g * 59.0f + b * 11.0f) / 100.0f;
            float f4 = (r * 30.0f + g * 70.0f) / 100.0f;
            float f5 = (r * 30.0f + b * 70.0f) / 100.0f;
            r = f3;
            g = f4;
            b = f5;
        }
        tessellator.setColorOpaque_F(r, g, b);
        double d = i;
        double d1 = j;
        double d2 = k;
        long seed = (long)(i * 3129871) ^ (long)k * 116129781L;
        seed = seed * seed * 42317861L + seed * 11L;
        d += ((double)((float)(seed >> 16 & 0xFL) / 15.0f) - 0.5) * 0.3;
        d2 += ((double)((float)(seed >> 24 & 0xFL) / 15.0f) - 0.5) * 0.3;
        int meta = world.getBlockMetadata(i, j, k);
        boolean isTop = BlockDoublePlant.func_149887_c((int)meta);
        if (isTop) {
            if (world.getBlock(i, j - 1, k) != block) {
                return;
            }
            plantType = BlockDoublePlant.func_149890_d((int)world.getBlockMetadata(i, j - 1, k));
        } else {
            plantType = BlockDoublePlant.func_149890_d((int)meta);
        }
        IIcon icon = block.func_149888_a(isTop, plantType);
        renderblocks.drawCrossedSquares(icon, d, d1, d2, 1.0f);
    }

    private void renderBirdCage(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        int meta = world.getBlockMetadata(i, j, k);
        float d = 0.001f;
        if (!LOTRBlockBirdCage.isSameBirdCage(world, i, j, k, i - 1, j, k)) {
            renderblocks.setRenderBounds(0.0, 0.0625, 0.0, (double)(0.0f + d), 1.0, 1.0);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        if (!LOTRBlockBirdCage.isSameBirdCage(world, i, j, k, i + 1, j, k)) {
            renderblocks.setRenderBounds((double)(1.0f - d), 0.0625, 0.0, 1.0, 1.0, 1.0);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        if (!LOTRBlockBirdCage.isSameBirdCage(world, i, j, k, i, j, k - 1)) {
            renderblocks.setRenderBounds(0.0, 0.0625, 0.0, 1.0, 1.0, (double)(0.0f + d));
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        if (!LOTRBlockBirdCage.isSameBirdCage(world, i, j, k, i, j, k + 1)) {
            renderblocks.setRenderBounds(0.0, 0.0625, (double)(1.0f - d), 1.0, 1.0, 1.0);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        renderblocks.setRenderBounds(0.0, (double)(1.0f - d), 0.0, 1.0, 1.0, 1.0);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setOverrideBlockTexture(block.getIcon(-1, meta));
        renderblocks.setRenderBounds(0.0, 0.0, 0.0, 1.0, 0.0625, 1.0);
        renderblocks.renderStandardBlock(block, i, j, k);
        Block above = world.getBlock(i, j + 1, k);
        above.setBlockBoundsBasedOnState(world, i, j + 1, k);
        if (!above.getMaterial().isSolid() || above.getBlockBoundsMinY() > 0.0 || !above.getMaterial().isOpaque() && above.getRenderType() == 0) {
            renderblocks.setRenderBounds(0.375, 1.0, 0.375, 0.625, 1.0625, 0.625);
            renderblocks.renderStandardBlock(block, i, j, k);
            renderblocks.setRenderBounds(0.46875, 1.0625, 0.46875, 0.53125, 1.1875, 0.53125);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    private void renderInvBirdCage(Block block, RenderBlocks renderblocks, int meta) {
        renderblocks.renderAllFaces = true;
        float d = 0.001f;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.0625, 0.0, 0.0f + d, 1.0, 1.0, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 1.0f - d, 0.0625, 0.0, 1.0, 1.0, 1.0, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.0625, 0.0, 1.0, 1.0, 0.0f + d, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.0625, 1.0f - d, 1.0, 1.0, 1.0, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 1.0f - d, 0.0, 1.0, 1.0, 1.0, meta);
        renderblocks.setOverrideBlockTexture(block.getIcon(-1, meta));
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.0, 0.0, 1.0, 0.0625, 1.0, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.375, 1.0, 0.375, 0.625, 1.0625, 0.625, meta);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.46875, 1.0625, 0.46875, 0.53125, 1.1875, 0.53125, meta);
        renderblocks.clearOverrideBlockTexture();
        renderblocks.renderAllFaces = false;
    }

    private void renderRhunFireJar(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int ao = LOTRRenderBlocks.getAO();
        LOTRRenderBlocks.setAO(0);
        renderblocks.renderAllFaces = true;
        LOTRBlockRhunFireJar.renderingStage = 1;
        renderblocks.setRenderBounds(0.125, 0.0, 0.125, 0.875, 0.5, 0.875);
        renderblocks.renderStandardBlock(block, i, j, k);
        LOTRBlockRhunFireJar.renderingStage = 2;
        renderblocks.setRenderBounds(0.3125, 0.5, 0.3125, 0.6875, 0.6875, 0.6875);
        renderblocks.renderStandardBlock(block, i, j, k);
        LOTRBlockRhunFireJar.renderingStage = 3;
        renderblocks.setRenderBounds(0.25, 0.6875, 0.25, 0.75, 0.8125, 0.75);
        renderblocks.renderStandardBlock(block, i, j, k);
        LOTRBlockRhunFireJar.renderingStage = 4;
        renderblocks.setRenderBounds(0.3125, 0.8125, 0.3125, 0.6875, 0.875, 0.6875);
        renderblocks.renderStandardBlock(block, i, j, k);
        LOTRBlockRhunFireJar.renderingStage = 5;
        renderblocks.setRenderBounds(0.375, 0.875, 0.5, 0.625, 1.0, 0.5);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.5, 0.875, 0.375, 0.5, 1.0, 0.625);
        renderblocks.renderStandardBlock(block, i, j, k);
        LOTRBlockRhunFireJar.renderingStage = 6;
        renderblocks.setRenderBounds(0.0, 0.0, 0.5, 1.0, 1.0, 0.5);
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.setRenderBounds(0.5, 0.0, 0.0, 0.5, 1.0, 1.0);
        renderblocks.renderStandardBlock(block, i, j, k);
        LOTRBlockRhunFireJar.renderingStage = 0;
        renderblocks.renderAllFaces = false;
        LOTRRenderBlocks.setAO(ao);
    }

    private void renderInvRhunFireJar(Block block, RenderBlocks renderblocks, int meta) {
        renderblocks.renderAllFaces = true;
        LOTRBlockRhunFireJar.renderingStage = 1;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.125, 0.0, 0.125, 0.875, 0.5, 0.875);
        LOTRBlockRhunFireJar.renderingStage = 2;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.3125, 0.5, 0.3125, 0.6875, 0.6875, 0.6875);
        LOTRBlockRhunFireJar.renderingStage = 3;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.25, 0.6875, 0.25, 0.75, 0.8125, 0.75);
        LOTRBlockRhunFireJar.renderingStage = 4;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.3125, 0.8125, 0.3125, 0.6875, 0.875, 0.6875);
        LOTRBlockRhunFireJar.renderingStage = 5;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.375, 0.875, 0.5, 0.625, 1.0, 0.5);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.5, 0.875, 0.375, 0.5, 1.0, 0.625);
        LOTRBlockRhunFireJar.renderingStage = 6;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.0, 0.0, 0.5, 1.0, 1.0, 0.5);
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, 0.5, 0.0, 0.0, 0.5, 1.0, 1.0);
        LOTRBlockRhunFireJar.renderingStage = 0;
        renderblocks.renderAllFaces = false;
    }

    private void renderCoral(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        renderblocks.renderStandardBlock(block, i, j, k);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, i, j + 1, k));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        IIcon icon = ((LOTRBlockCoralReef)block).getRandomPlantIcon(i, j, k);
        renderblocks.drawCrossedSquares(icon, (double)i, (double)(j + 1), (double)k, 1.0f);
    }

    private boolean renderDoor(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        Tessellator tessellator = Tessellator.instance;
        int meta = world.getBlockMetadata(i, j, k);
        boolean topDoor = (meta & 8) != 0;
        boolean bl = topDoor;
        if (topDoor ? world.getBlock(i, j - 1, k) != block : world.getBlock(i, j + 1, k) != block) {
            return false;
        }
        boolean flag = false;
        float f = 0.5f;
        float f1 = 1.0f;
        float f2 = 0.8f;
        float f3 = 0.6f;
        int light2 = block.getMixedBrightnessForBlock(world, i, j, k);
        if (!topDoor || world.getBlock(i, j - 1, k) != block) {
            tessellator.setBrightness(renderblocks.renderMinY > 0.0 ? light2 : block.getMixedBrightnessForBlock(world, i, j - 1, k));
            tessellator.setColorOpaque_F(f, f, f);
            renderblocks.renderFaceYNeg(block, (double)i, (double)j, (double)k, renderblocks.getBlockIcon(block, world, i, j, k, 0));
            flag = true;
        }
        if (topDoor || world.getBlock(i, j + 1, k) != block) {
            tessellator.setBrightness(renderblocks.renderMaxY < 1.0 ? light2 : block.getMixedBrightnessForBlock(world, i, j + 1, k));
            tessellator.setColorOpaque_F(f1, f1, f1);
            renderblocks.renderFaceYPos(block, (double)i, (double)j, (double)k, renderblocks.getBlockIcon(block, world, i, j, k, 1));
            flag = true;
        }
        tessellator.setBrightness(renderblocks.renderMinZ > 0.0 ? light2 : block.getMixedBrightnessForBlock(world, i, j, k - 1));
        tessellator.setColorOpaque_F(f2, f2, f2);
        IIcon iicon = renderblocks.getBlockIcon(block, world, i, j, k, 2);
        renderblocks.renderFaceZNeg(block, (double)i, (double)j, (double)k, iicon);
        flag = true;
        renderblocks.flipTexture = false;
        tessellator.setBrightness(renderblocks.renderMaxZ < 1.0 ? light2 : block.getMixedBrightnessForBlock(world, i, j, k + 1));
        tessellator.setColorOpaque_F(f2, f2, f2);
        iicon = renderblocks.getBlockIcon(block, world, i, j, k, 3);
        renderblocks.renderFaceZPos(block, (double)i, (double)j, (double)k, iicon);
        flag = true;
        renderblocks.flipTexture = false;
        tessellator.setBrightness(renderblocks.renderMinX > 0.0 ? light2 : block.getMixedBrightnessForBlock(world, i - 1, j, k));
        tessellator.setColorOpaque_F(f3, f3, f3);
        iicon = renderblocks.getBlockIcon(block, world, i, j, k, 4);
        renderblocks.renderFaceXNeg(block, (double)i, (double)j, (double)k, iicon);
        flag = true;
        renderblocks.flipTexture = false;
        tessellator.setBrightness(renderblocks.renderMaxX < 1.0 ? light2 : block.getMixedBrightnessForBlock(world, i + 1, j, k));
        tessellator.setColorOpaque_F(f3, f3, f3);
        iicon = renderblocks.getBlockIcon(block, world, i, j, k, 5);
        renderblocks.renderFaceXPos(block, (double)i, (double)j, (double)k, iicon);
        flag = true;
        renderblocks.flipTexture = false;
        return flag;
    }

    private void renderRope(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        double ropeWidth = 0.125;
        double ropeMinX = 0.5 - ropeWidth / 2.0;
        double ropeMaxX = 1.0 - ropeMinX;
        double ropeOffset = 0.0625;
        int meta = world.getBlockMetadata(i, j, k);
        boolean top = world.getBlock(i, j + 1, k) != block || world.getBlockMetadata(i, j + 1, k) != meta;
        double knotHeight = 0.25;
        double knotBottom = 1.0 - knotHeight;
        double knotWidth = 0.25;
        double knotMinX = 0.5 - knotWidth / 2.0;
        double knotMaxX = 1.0 - knotMinX;
        double knotOffset = knotWidth;
        double ropeTop = top ? 1.0 - knotHeight : 1.0;
        double d = ropeTop;
        if (meta == 5) {
            renderblocks.setRenderBounds(0.0, 0.0, ropeMinX, ropeOffset, ropeTop, ropeMaxX);
            renderblocks.renderStandardBlock(block, i, j, k);
            if (top) {
                renderblocks.setRenderAllFaces(true);
                renderblocks.setRenderBounds(0.0, knotBottom, knotMinX, knotOffset, 1.0, knotMaxX);
                renderblocks.renderStandardBlock(block, i, j, k);
                renderblocks.setRenderAllFaces(false);
            }
        }
        if (meta == 4) {
            renderblocks.setRenderBounds(1.0 - ropeOffset, 0.0, ropeMinX, 1.0, ropeTop, ropeMaxX);
            renderblocks.renderStandardBlock(block, i, j, k);
            if (top) {
                renderblocks.setRenderAllFaces(true);
                renderblocks.setRenderBounds(1.0 - knotOffset, knotBottom, knotMinX, 1.0, 1.0, knotMaxX);
                renderblocks.renderStandardBlock(block, i, j, k);
                renderblocks.setRenderAllFaces(false);
            }
        }
        if (meta == 3) {
            renderblocks.setRenderBounds(ropeMinX, 0.0, 0.0, ropeMaxX, ropeTop, ropeOffset);
            renderblocks.renderStandardBlock(block, i, j, k);
            if (top) {
                renderblocks.setRenderAllFaces(true);
                renderblocks.setRenderBounds(knotMinX, knotBottom, 0.0, knotMaxX, 1.0, knotOffset);
                renderblocks.renderStandardBlock(block, i, j, k);
                renderblocks.setRenderAllFaces(false);
            }
        }
        if (meta == 2) {
            renderblocks.setRenderBounds(ropeMinX, 0.0, 1.0 - ropeOffset, ropeMaxX, ropeTop, 1.0);
            renderblocks.renderStandardBlock(block, i, j, k);
            if (top) {
                renderblocks.setRenderAllFaces(true);
                renderblocks.setRenderBounds(knotMinX, knotBottom, 1.0 - knotOffset, knotMaxX, 1.0, 1.0);
                renderblocks.renderStandardBlock(block, i, j, k);
                renderblocks.setRenderAllFaces(false);
            }
        }
    }

    private void renderTrapdoor(IBlockAccess world, int i, int j, int k, Block block, RenderBlocks renderblocks) {
        int meta = world.getBlockMetadata(i, j, k);
        if (!BlockTrapDoor.func_150118_d((int)meta)) {
            int dir = meta & 3;
            if (dir == 0) {
                renderblocks.uvRotateTop = 3;
                renderblocks.uvRotateBottom = 3;
            } else if (dir == 1) {
                renderblocks.uvRotateTop = 0;
                renderblocks.uvRotateBottom = 0;
            } else if (dir == 2) {
                renderblocks.uvRotateTop = 1;
                renderblocks.uvRotateBottom = 2;
            } else if (dir == 3) {
                renderblocks.uvRotateTop = 2;
                renderblocks.uvRotateBottom = 1;
            }
        }
        renderblocks.renderStandardBlock(block, i, j, k);
        renderblocks.uvRotateBottom = 0;
        renderblocks.uvRotateTop = 0;
    }

    private static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta) {
        block.setBlockBoundsForItemRender();
        renderblocks.setRenderBoundsFromBlock(block);
        double d = renderblocks.renderMinX;
        double d1 = renderblocks.renderMinY;
        double d2 = renderblocks.renderMinZ;
        double d3 = renderblocks.renderMaxX;
        double d4 = renderblocks.renderMaxY;
        double d5 = renderblocks.renderMaxZ;
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, d, d1, d2, d3, d4, d5, meta);
    }

    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, double d, double d1, double d2, double d3, double d4, double d5) {
        LOTRRenderBlocks.renderStandardInvBlock(renderblocks, block, d, d1, d2, d3, d4, d5, 0);
    }

    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, double d, double d1, double d2, double d3, double d4, double d5, int metadata) {
        Tessellator tessellator = Tessellator.instance;
        renderblocks.setRenderBounds(d, d1, d2, d3, d4, d5);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, -1.0f, 0.0f);
        renderblocks.renderFaceYNeg(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 0, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        renderblocks.renderFaceYPos(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 1, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, -1.0f);
        renderblocks.renderFaceZNeg(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 2, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, 1.0f);
        renderblocks.renderFaceZPos(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 3, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0f, 0.0f, 0.0f);
        renderblocks.renderFaceXNeg(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 4, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0f, 0.0f, 0.0f);
        renderblocks.renderFaceXPos(block, 0.0, 0.0, 0.0, renderblocks.getBlockIconFromSideAndMetadata(block, 5, metadata));
        tessellator.draw();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    private static int getAO() {
        return Minecraft.getMinecraft().gameSettings.ambientOcclusion;
    }

    private static void setAO(int i) {
        Minecraft.getMinecraft().gameSettings.ambientOcclusion = i;
    }
}

