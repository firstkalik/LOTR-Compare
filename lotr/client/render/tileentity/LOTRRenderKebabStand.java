/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import java.util.HashMap;
import java.util.Map;
import lotr.client.model.LOTRModelKebabStand;
import lotr.common.tileentity.LOTRTileEntityKebabStand;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;

public class LOTRRenderKebabStand
extends TileEntitySpecialRenderer {
    private static LOTRModelKebabStand standModel = new LOTRModelKebabStand();
    private static Map<String, ResourceLocation> standTextures = new HashMap<String, ResourceLocation>();
    private static ResourceLocation rawTexture = new ResourceLocation("lotr:item/kebab/raw.png");
    private static ResourceLocation cookedTexture = new ResourceLocation("lotr:item/kebab/cooked.png");

    private static ResourceLocation getStandTexture(LOTRTileEntityKebabStand kebabStand) {
        ResourceLocation r;
        String s = kebabStand.getStandTextureName();
        if (!StringUtils.isNullOrEmpty((String)s)) {
            s = "_" + s;
        }
        if ((r = standTextures.get(s = "stand" + s)) == null) {
            r = new ResourceLocation("lotr:item/kebab/" + s + ".png");
            standTextures.put(s, r);
        }
        return r;
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntityKebabStand kebabStand = (LOTRTileEntityKebabStand)tileentity;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
        int meta = kebabStand.getBlockMetadata();
        switch (meta) {
            case 2: {
                GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 5: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        float scale = 0.0625f;
        this.bindTexture(LOTRRenderKebabStand.getStandTexture(kebabStand));
        standModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, scale);
        int meatAmount = kebabStand.getMeatAmount();
        if (meatAmount > 0) {
            boolean cooked = kebabStand.isCooked();
            if (cooked) {
                this.bindTexture(cookedTexture);
            } else {
                this.bindTexture(rawTexture);
            }
            float spin = kebabStand.getKebabSpin(f);
            standModel.renderKebab(scale, meatAmount, spin);
        }
        GL11.glEnable((int)2884);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

