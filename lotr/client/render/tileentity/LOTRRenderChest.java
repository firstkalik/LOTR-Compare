/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.client.model.ModelChest
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import cpw.mods.fml.common.FMLLog;
import java.util.HashMap;
import java.util.Map;
import lotr.common.block.LOTRBlockChest;
import lotr.common.block.LOTRBlockSpawnerChest;
import lotr.common.tileentity.LOTRTileEntityChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderChest
extends TileEntitySpecialRenderer {
    private static Map<String, ResourceLocation> chestTextures = new HashMap<String, ResourceLocation>();
    private static ModelChest chestModel = new ModelChest();
    private LOTRTileEntityChest itemEntity = new LOTRTileEntityChest();

    public static ResourceLocation getChestTexture(String s) {
        ResourceLocation r = chestTextures.get(s);
        if (r == null) {
            r = new ResourceLocation("lotr:item/chest/" + s + ".png");
            chestTextures.put(s, r);
        }
        return r;
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        int meta;
        LOTRTileEntityChest chest = (LOTRTileEntityChest)tileentity;
        if (!chest.hasWorldObj()) {
            meta = 0;
        } else {
            Block block = tileentity.getBlockType();
            meta = tileentity.getBlockMetadata();
            if (block instanceof BlockChest && meta == 0) {
                try {
                    ((BlockChest)block).func_149954_e(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
                }
                catch (ClassCastException e) {
                    FMLLog.severe((String)"Attempted to render a chest at %d,  %d, %d that was not a chest", (Object[])new Object[]{tileentity.xCoord, tileentity.yCoord, tileentity.zCoord});
                }
                meta = tileentity.getBlockMetadata();
            }
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)d), (float)((float)d1 + 1.0f), (float)((float)d2 + 1.0f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        float rot = 0.0f;
        if (meta == 2) {
            rot = 180.0f;
        }
        if (meta == 3) {
            rot = 0.0f;
        }
        if (meta == 4) {
            rot = 90.0f;
        }
        if (meta == 5) {
            rot = -90.0f;
        }
        GL11.glRotatef((float)rot, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        float lid = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * f;
        lid = 1.0f - lid;
        lid = 1.0f - lid * lid * lid;
        LOTRRenderChest.chestModel.chestLid.rotateAngleX = -(lid * 3.1415927f / 2.0f);
        this.bindTexture(LOTRRenderChest.getChestTexture(chest.textureName));
        chestModel.renderAll();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void renderInvChest(Block block, int meta) {
        Block c;
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        this.itemEntity.textureName = "";
        if (block instanceof LOTRBlockChest) {
            this.itemEntity.textureName = ((LOTRBlockChest)block).getChestTextureName();
        } else if (block instanceof LOTRBlockSpawnerChest && (c = ((LOTRBlockSpawnerChest)block).chestModel) instanceof LOTRBlockChest) {
            this.itemEntity.textureName = ((LOTRBlockChest)c).getChestTextureName();
        }
        this.renderTileEntityAt(this.itemEntity, 0.0, 0.0, 0.0, 0.0f);
        GL11.glEnable((int)32826);
    }
}

