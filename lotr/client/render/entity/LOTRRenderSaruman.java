/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.awt.Color;
import java.util.Random;
import lotr.client.model.LOTRModelHuman;
import lotr.client.render.entity.LOTRRenderBiped;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderSaruman
extends LOTRRenderBiped {
    private static ResourceLocation skin = new ResourceLocation("lotr:mob/char/saruman.png");
    private Random rand = new Random();
    private boolean twitch;

    public LOTRRenderSaruman() {
        super(new LOTRModelHuman(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        super.preRenderCallback(entity, f);
        if (entity.ticksExisted % 60 == 0) {
            boolean bl = this.twitch = !this.twitch;
        }
        if (this.twitch) {
            GL11.glRotatef((float)(this.rand.nextFloat() * 40.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(this.rand.nextFloat() * 40.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(this.rand.nextFloat() * 40.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)(this.rand.nextFloat() * 0.5f), (float)(this.rand.nextFloat() * 0.5f), (float)(this.rand.nextFloat() * 0.5f));
        }
        int i = entity.ticksExisted % 360;
        float hue = (float)i / 360.0f;
        Color color = Color.getHSBColor(hue, 1.0f, 1.0f);
        float r = (float)color.getRed() / 255.0f;
        float g = (float)color.getGreen() / 255.0f;
        float b = (float)color.getBlue() / 255.0f;
        GL11.glColor3f((float)r, (float)g, (float)b);
    }
}

