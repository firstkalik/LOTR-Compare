/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderFallingBlock
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.block.LOTRBlockRhunFireJar;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderFallingFireJar
extends RenderFallingBlock {
    private static RenderBlocks renderBlocks = new RenderBlocks();

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        EntityFallingBlock falling = (EntityFallingBlock)entity;
        World world = falling.func_145807_e();
        Block block = falling.func_145805_f();
        int i = MathHelper.floor_double((double)falling.posX);
        int j = MathHelper.floor_double((double)falling.posY);
        int k = MathHelper.floor_double((double)falling.posZ);
        if (block instanceof LOTRBlockRhunFireJar) {
            if (block != world.getBlock(i, j, k)) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
                this.bindEntityTexture(entity);
                GL11.glDisable((int)2896);
                LOTRRenderFallingFireJar.renderBlocks.blockAccess = world;
                Tessellator tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setTranslation((double)((float)(-i) - 0.5f), (double)((float)(-j) - 0.5f), (double)((float)(-k) - 0.5f));
                renderBlocks.renderBlockByRenderType(block, i, j, k);
                tessellator.setTranslation(0.0, 0.0, 0.0);
                tessellator.draw();
                GL11.glEnable((int)2896);
                GL11.glPopMatrix();
            }
        } else {
            super.doRender(entity, d, d1, d2, f, f1);
        }
    }
}

