/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.client.model.LOTRModelUnsmeltery;
import lotr.common.block.LOTRBlockForgeBase;
import lotr.common.tileentity.LOTRTileEntityUnsmeltery;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderUnsmeltery
extends TileEntitySpecialRenderer {
    private ModelBase unsmelteryModel = new LOTRModelUnsmeltery();
    private ResourceLocation idleTexture = new ResourceLocation("lotr:item/unsmeltery/idle.png");
    private ResourceLocation activeTexture = new ResourceLocation("lotr:item/unsmeltery/active.png");

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        boolean useActiveTexture;
        LOTRTileEntityUnsmeltery unsmeltery = (LOTRTileEntityUnsmeltery)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        float rotation = 0.0f;
        float rocking = 0.0f;
        if (unsmeltery != null) {
            switch (unsmeltery.getBlockMetadata() & 7) {
                case 2: {
                    rotation = 180.0f;
                    break;
                }
                case 3: {
                    rotation = 0.0f;
                    break;
                }
                case 4: {
                    rotation = 90.0f;
                    break;
                }
                case 5: {
                    rotation = 270.0f;
                }
            }
            rocking = unsmeltery.getRockingAmount(f);
        }
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        boolean bl = useActiveTexture = unsmeltery != null && LOTRBlockForgeBase.isForgeActive((IBlockAccess)unsmeltery.getWorldObj(), unsmeltery.xCoord, unsmeltery.yCoord, unsmeltery.zCoord);
        if (useActiveTexture) {
            this.bindTexture(this.activeTexture);
        } else {
            this.bindTexture(this.idleTexture);
        }
        this.unsmelteryModel.render(null, rocking, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public void renderInvUnsmeltery() {
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        this.renderTileEntityAt(null, 0.0, 0.0, 0.0, 0.0f);
        GL11.glEnable((int)32826);
    }
}

