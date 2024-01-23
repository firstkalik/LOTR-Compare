/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.client.render.LOTRRenderBlocks;
import lotr.client.render.tileentity.LOTRRenderDwarvenGlow;
import lotr.common.block.LOTRBlockGateDwarvenIthildin;
import lotr.common.tileentity.LOTRTileEntityDwarvenDoor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderDwarvenDoor
extends TileEntitySpecialRenderer {
    private RenderBlocks renderBlocks;

    public void func_147496_a(World world) {
        this.renderBlocks = new RenderBlocks((IBlockAccess)world);
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        if (this.renderBlocks == null) {
            this.renderBlocks = new RenderBlocks((IBlockAccess)tileentity.getWorldObj());
        }
        LOTRTileEntityDwarvenDoor door = (LOTRTileEntityDwarvenDoor)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        float alphaFunc = LOTRRenderDwarvenGlow.setupGlow(door.getGlowBrightness(f));
        this.bindTexture(TextureMap.locationBlocksTexture);
        Block block = door.getBlockType();
        if (block instanceof LOTRBlockGateDwarvenIthildin) {
            LOTRRenderBlocks.renderDwarvenDoorGlow((LOTRBlockGateDwarvenIthildin)block, this.renderBlocks, door.xCoord, door.yCoord, door.zCoord);
        }
        LOTRRenderDwarvenGlow.endGlow(alphaFunc);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

