/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelTroll;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderTroll;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityMountainTroll2;
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.entity.projectile.LOTREntityThrownRock;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRRenderMountainTroll2
extends LOTRRenderTroll {
    private static LOTRRandomSkins mountainTrollSkins;
    private LOTREntityThrownRock heldRock;

    public LOTRRenderMountainTroll2() {
        mountainTrollSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/troll/mountainTroll");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return mountainTrollSkins.getRandomSkin((LOTREntityTroll)entity);
    }

    @Override
    protected void renderTrollWeapon(EntityLivingBase entity, float f) {
        LOTREntityMountainTroll2 troll = (LOTREntityMountainTroll2)entity;
        if (troll.isThrowingRocks()) {
            if (((LOTRModelTroll)this.mainModel).onGround <= 0.0f) {
                if (this.heldRock == null) {
                    this.heldRock = new LOTREntityThrownRock(troll.worldObj);
                }
                this.heldRock.setWorld(troll.worldObj);
                this.heldRock.setPosition(troll.posX, troll.posY, troll.posZ);
                ((LOTRModelTroll)this.mainModel).rightArm.postRender(0.0625f);
                GL11.glTranslatef((float)0.375f, (float)1.5f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                this.scaleTroll(troll, true);
                this.renderManager.renderEntityWithPosYaw((Entity)this.heldRock, 0.0, 0.0, 0.0, 0.0f, f);
            }
        } else {
            ((LOTRModelTroll)this.mainModel).renderWoodenClubWithSpikes(0.0625f);
        }
    }
}

