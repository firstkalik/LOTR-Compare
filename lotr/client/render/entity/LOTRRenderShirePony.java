/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRenderHorse;
import lotr.common.entity.animal.LOTREntityShirePony;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class LOTRRenderShirePony
extends LOTRRenderHorse {
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        float scale = LOTREntityShirePony.PONY_SCALE;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }
}

