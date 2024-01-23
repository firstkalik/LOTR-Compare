/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.block.LOTRBlockTreasurePile;
import lotr.common.entity.item.LOTREntityFallingTreasure;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderFallingCoinPile
extends Render {
    private static final RenderBlocks blockRenderer = new RenderBlocks();

    public LOTRRenderFallingCoinPile() {
        this.shadowSize = 0.5f;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityFallingTreasure fallingCoin = (LOTREntityFallingTreasure)entity;
        World world = fallingCoin.worldObj;
        Block block = fallingCoin.theBlock;
        int meta = fallingCoin.theBlockMeta;
        int i = MathHelper.floor_double((double)fallingCoin.posX);
        int j = MathHelper.floor_double((double)fallingCoin.posY);
        int k = MathHelper.floor_double((double)fallingCoin.posZ);
        if (block != null && block != world.getBlock(i, j, k)) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
            this.bindEntityTexture((Entity)fallingCoin);
            GL11.glDisable((int)2896);
            LOTRBlockTreasurePile.setTreasureBlockBounds(block, meta);
            blockRenderer.setRenderBoundsFromBlock(block);
            blockRenderer.renderBlockSandFalling(block, world, i, j, k, meta);
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationBlocksTexture;
    }
}

