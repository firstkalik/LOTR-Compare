/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package lotr.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import lotr.client.LOTRClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class LanternMorgulModel
implements ISimpleBlockRenderingHandler {
    private final int FACE_NEG_X = 1;
    private final int FACE_POS_X = 2;
    private final int FACE_NEG_Y = 4;
    private final int FACE_POS_Y = 8;
    private final int FACE_NEG_Z = 16;
    private final int FACE_POS_Z = 32;

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        if (modelId == this.getRenderId()) {
            GL11.glTranslatef((float)-0.5f, (float)-1.5f, (float)-0.5f);
            GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
            IIcon icon = block.getBlockTextureFromSide(1);
            block.setBlockBounds(0.0f, 0.4375f, 0.0f, 0.375f, 0.875f, 0.375f);
            this.renderCuboidInvWithBlockBounds(renderer, block, 0.3125, 0.4375, 0.3125, icon, 51);
            block.setBlockBounds(0.0f, 0.0f, 0.5625f, 0.375f, 1.0f, 0.9375f);
            this.renderCuboidInvWithBlockBounds(renderer, block, 0.3125, 0.875, -0.25, icon, 4);
            this.renderCuboidInvWithBlockBounds(renderer, block, 0.3125, 0.3125, -0.25, icon, 8);
            block.setBlockBounds(0.0625f, 0.875f, 0.0625f, 0.3125f, 1.0f, 0.3125f);
            this.renderCuboidInvWithBlockBounds(renderer, block, 0.3125, 0.4375, 0.3125, icon, 51);
            block.setBlockBounds(0.0625f, 0.0f, 0.625f, 0.3125f, 1.0f, 0.875f);
            this.renderCuboidInvWithBlockBounds(renderer, block, 0.3125, 0.4375, -0.25, icon, 8);
            block.setBlockBoundsForItemRender();
            GL11.glScalef((float)0.6666667f, (float)0.6666667f, (float)0.6666667f);
            GL11.glTranslatef((float)0.5f, (float)1.5f, (float)0.5f);
        }
    }

    private void renderCuboidInvWithBlockBounds(RenderBlocks renderer, Block block, double x, double y, double z, IIcon icon, int faces) {
        Tessellator tessellator = Tessellator.instance;
        renderer.setRenderBoundsFromBlock(block);
        if ((faces & 1) == 1) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0f, 0.0f, 0.0f);
            renderer.renderFaceXNeg(block, x, y, z, icon);
            tessellator.draw();
        }
        if ((faces & 2) == 2) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0f, 0.0f, 0.0f);
            renderer.renderFaceXPos(block, x, y, z, icon);
            tessellator.draw();
        }
        if ((faces & 4) == 4) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, -1.0f, 0.0f);
            renderer.renderFaceYNeg(block, x, y, z, icon);
            tessellator.draw();
        }
        if ((faces & 8) == 8) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, 1.0f, 0.0f);
            renderer.renderFaceYPos(block, x, y, z, icon);
            tessellator.draw();
        }
        if ((faces & 0x10) == 16) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, 0.0f, -1.0f);
            renderer.renderFaceZNeg(block, x, y, z, icon);
            tessellator.draw();
        }
        if ((faces & 0x20) == 32) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, 0.0f, 1.0f);
            renderer.renderFaceZPos(block, x, y, z, icon);
            tessellator.draw();
        }
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (modelId == this.getRenderId()) {
            int meta = world.getBlockMetadata(x, y, z);
            float y_delta = 0.0f;
            boolean renderLongChain = false;
            if (meta == 1) {
                y_delta = 0.0625f;
                renderLongChain = true;
            }
            Tessellator tessellator = Tessellator.instance;
            tessellator.setColorOpaque_F(0.8f, 0.8f, 0.8f);
            tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
            block.setBlockBounds(0.0f, 0.4375f, 0.0f, 0.375f, 0.875f, 0.375f);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderFaceXNeg(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z + 0.3125f), block.getBlockTextureFromSide(4));
            renderer.renderFaceXPos(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z + 0.3125f), block.getBlockTextureFromSide(5));
            renderer.renderFaceZNeg(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z + 0.3125f), block.getBlockTextureFromSide(2));
            renderer.renderFaceZPos(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z + 0.3125f), block.getBlockTextureFromSide(1));
            block.setBlockBounds(0.0f, 0.0f, 0.5625f, 0.375f, 1.0f, 0.9375f);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderFaceYNeg(block, (double)((float)x + 0.3125f), (double)((float)y - 0.0f + y_delta), (double)((float)z - 0.25f), block.getBlockTextureFromSide(0));
            renderer.renderFaceYPos(block, (double)((float)x + 0.3125f), (double)((float)y - 0.5625f + y_delta), (double)((float)z - 0.25f), block.getBlockTextureFromSide(1));
            block.setBlockBounds(0.0625f, 0.875f, 0.0625f, 0.3125f, 1.0f, 0.3125f);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderFaceXNeg(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z + 0.3125f), block.getBlockTextureFromSide(4));
            renderer.renderFaceXPos(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z + 0.3125f), block.getBlockTextureFromSide(5));
            renderer.renderFaceZNeg(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z + 0.3125f), block.getBlockTextureFromSide(2));
            renderer.renderFaceZPos(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z + 0.3125f), block.getBlockTextureFromSide(1));
            block.setBlockBounds(0.0625f, 0.0f, 0.625f, 0.3125f, 1.0f, 0.875f);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderFaceYPos(block, (double)((float)x + 0.3125f), (double)((float)y - 0.4375f + y_delta), (double)((float)z - 0.25f), block.getBlockTextureFromSide(1));
            IIcon icon = block.getBlockTextureFromSide(1);
            if (renderLongChain) {
                double u_begin = icon.getInterpolatedU(11.0);
                double v_begin = icon.getInterpolatedV(10.0);
                double u_end = icon.getInterpolatedU(14.0);
                double v_end = icon.getInterpolatedV(12.0);
                double width = 0.1875;
                double height = 0.125;
                double x_base1 = 0.4375;
                double x_base2 = 0.375;
                double y_base = 0.625;
                double z_base1 = 0.5625;
                double z_base2 = 0.4375;
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base, (double)z + 0.4375, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base + height, (double)z + 0.4375, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base + height, (double)z + 0.5625, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base, (double)z + 0.5625, u_begin, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base + height, (double)z + 0.5625, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base + height, (double)z + 0.4375, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base, (double)z + 0.4375, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base, (double)z + 0.5625, u_begin, v_end);
                u_begin = icon.getInterpolatedU(11.0);
                v_begin = icon.getInterpolatedV(1.0);
                u_end = icon.getInterpolatedU(14.0);
                v_end = icon.getInterpolatedV(5.0);
                width = 0.1875;
                height = 0.25;
                y_base = 0.6875;
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base, (double)z + 0.5625, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base + height, (double)z + 0.5625, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base + height, (double)z + 0.4375, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base, (double)z + 0.4375, u_begin, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base + height, (double)z + 0.4375, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base + height, (double)z + 0.5625, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base, (double)z + 0.5625, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base, (double)z + 0.4375, u_begin, v_end);
                u_begin = icon.getInterpolatedU(11.0);
                v_begin = icon.getInterpolatedV(6.0);
                u_end = icon.getInterpolatedU(14.0);
                v_end = icon.getInterpolatedV(8.0);
                width = 0.1875;
                height = 0.125;
                y_base = 0.875;
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base, (double)z + 0.4375, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base + height, (double)z + 0.4375, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base + height, (double)z + 0.5625, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base, (double)z + 0.5625, u_begin, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base + height, (double)z + 0.5625, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base + height, (double)z + 0.4375, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + width, (double)y + y_base, (double)z + 0.4375, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + y_base, (double)z + 0.5625, u_begin, v_end);
            } else {
                double u_begin = icon.getInterpolatedU(11.0);
                double v_begin = icon.getInterpolatedV(10.0);
                double u_end = icon.getInterpolatedU(14.0);
                double v_end = icon.getInterpolatedV(12.0);
                double width = 0.1875;
                double height = 0.125;
                double x_base1 = 0.4375;
                double x_base2 = 0.375;
                double y_base = 0.5625;
                double z_base1 = 0.5625;
                double z_base2 = 0.4375;
                tessellator.addVertexWithUV((double)x + 0.375 + 0.1875, (double)y + 0.5625, (double)z + 0.4375, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.375 + 0.1875, (double)y + 0.5625 + 0.125, (double)z + 0.4375, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + 0.5625 + 0.125, (double)z + 0.5625, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + 0.5625, (double)z + 0.5625, u_begin, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + 0.5625 + 0.125, (double)z + 0.5625, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + 0.1875, (double)y + 0.5625 + 0.125, (double)z + 0.4375, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + 0.1875, (double)y + 0.5625, (double)z + 0.4375, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + 0.5625, (double)z + 0.5625, u_begin, v_end);
                tessellator.addVertexWithUV((double)x + 0.375 + 0.1875, (double)y + 0.5625, (double)z + 0.5625, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.375 + 0.1875, (double)y + 0.5625 + 0.125, (double)z + 0.5625, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + 0.5625 + 0.125, (double)z + 0.4375, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + 0.5625, (double)z + 0.4375, u_begin, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + 0.5625 + 0.125, (double)z + 0.4375, u_begin, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + 0.1875, (double)y + 0.5625 + 0.125, (double)z + 0.5625, u_end, v_begin);
                tessellator.addVertexWithUV((double)x + 0.375 + 0.1875, (double)y + 0.5625, (double)z + 0.5625, u_end, v_end);
                tessellator.addVertexWithUV((double)x + 0.4375, (double)y + 0.5625, (double)z + 0.4375, u_begin, v_end);
            }
            return true;
        }
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return LOTRClientProxy.LanternMorgulModel;
    }
}

