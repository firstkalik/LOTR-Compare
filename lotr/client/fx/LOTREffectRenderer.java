/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.fx;

import java.util.ArrayList;
import java.util.List;
import lotr.client.LOTRClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTREffectRenderer {
    private Minecraft mc;
    private World worldObj;
    private List<EntityFX>[] particleLayers = new List[0];

    public LOTREffectRenderer(Minecraft minecraft) {
        this.mc = minecraft;
    }

    public void addEffect(EntityFX entityfx) {
        List<EntityFX> layerList;
        int layer = entityfx.getFXLayer();
        if (layer >= this.particleLayers.length) {
            List[] newLayers = new List[layer + 1];
            for (int l = 0; l < newLayers.length; ++l) {
                newLayers[l] = l < this.particleLayers.length ? this.particleLayers[l] : new ArrayList();
            }
            this.particleLayers = newLayers;
        }
        if ((layerList = this.particleLayers[layer]).size() >= 4000) {
            layerList.remove(0);
        }
        layerList.add(entityfx);
    }

    public void updateEffects() {
        for (List<EntityFX> layer : this.particleLayers) {
            for (int i = 0; i < layer.size(); ++i) {
                EntityFX particle = layer.get(i);
                if (particle != null) {
                    particle.onUpdate();
                }
                if (particle != null && !particle.isDead) continue;
                layer.remove(i--);
            }
        }
    }

    public void renderParticles(Entity entity, float f) {
        float f1 = ActiveRenderInfo.rotationX;
        float f2 = ActiveRenderInfo.rotationZ;
        float f3 = ActiveRenderInfo.rotationYZ;
        float f4 = ActiveRenderInfo.rotationXY;
        float f5 = ActiveRenderInfo.rotationXZ;
        EntityFX.interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
        EntityFX.interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
        EntityFX.interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
        for (int l = 0; l < this.particleLayers.length; ++l) {
            List<EntityFX> layer = this.particleLayers[l];
            if (layer.isEmpty()) continue;
            if (l == 0) {
                this.mc.getTextureManager().bindTexture(LOTRClientProxy.particlesTexture);
            } else if (l == 1) {
                this.mc.getTextureManager().bindTexture(LOTRClientProxy.particles2Texture);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDepthMask((boolean)false);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            for (EntityFX particle : layer) {
                if (particle == null) continue;
                tessellator.setBrightness(particle.getBrightnessForRender(f));
                particle.renderParticle(tessellator, f, f1, f5, f2, f3, f4);
            }
            tessellator.draw();
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            GL11.glAlphaFunc((int)516, (float)0.1f);
        }
    }

    public void clearEffectsAndSetWorld(World world) {
        this.worldObj = world;
        for (List<EntityFX> layer : this.particleLayers) {
            layer.clear();
        }
    }
}

