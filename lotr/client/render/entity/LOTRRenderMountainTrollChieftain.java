/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelTroll;
import lotr.client.render.entity.LOTRRenderMountainTroll2;
import lotr.common.entity.npc.LOTREntityMountainTrollChieftain;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderMountainTrollChieftain
extends LOTRRenderMountainTroll2 {
    private static ResourceLocation armorTexture = new ResourceLocation("lotr:mob/troll/mountainTrollChieftain_armor.png");
    private LOTRModelTroll helmetModel = new LOTRModelTroll(1.5f, 2);
    private LOTRModelTroll chestplateModel = new LOTRModelTroll(1.5f, 3);

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        super.doRender(entity, d, d1, d2, f, f1);
        LOTREntityMountainTrollChieftain troll = (LOTREntityMountainTrollChieftain)entity;
        if (troll.addedToChunk) {
            BossStatus.setBossStatus((IBossDisplayData)troll, (boolean)false);
        }
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int pass, float f) {
        LOTREntityMountainTrollChieftain troll = (LOTREntityMountainTrollChieftain)entity;
        this.bindTexture(armorTexture);
        if (pass == 2 && troll.getTrollArmorLevel() >= 2) {
            this.helmetModel.onGround = this.mainModel.onGround;
            this.setRenderPassModel((ModelBase)this.helmetModel);
            return 1;
        }
        if (pass == 3 && troll.getTrollArmorLevel() >= 1) {
            this.chestplateModel.onGround = this.mainModel.onGround;
            this.setRenderPassModel((ModelBase)this.chestplateModel);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        super.preRenderCallback(entity, f);
        GL11.glTranslatef((float)0.0f, (float)(-((LOTREntityMountainTrollChieftain)entity).getSpawningOffset(f)), (float)0.0f);
    }
}

