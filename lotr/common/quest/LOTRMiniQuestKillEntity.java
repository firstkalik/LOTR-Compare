/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package lotr.common.quest;

import java.util.Random;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestKill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class LOTRMiniQuestKillEntity
extends LOTRMiniQuestKill {
    public Class entityType;

    public LOTRMiniQuestKillEntity(LOTRPlayerData pd) {
        super(pd);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setString("KillClass", LOTREntities.getStringFromClass(this.entityType));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.entityType = LOTREntities.getClassFromString(nbt.getString("KillClass"));
    }

    @Override
    public boolean isValidQuest() {
        return super.isValidQuest() && this.entityType != null && EntityLivingBase.class.isAssignableFrom(this.entityType);
    }

    @Override
    protected String getKillTargetName() {
        String entityName = LOTREntities.getStringFromClass(this.entityType);
        return StatCollector.translateToLocal((String)("entity." + entityName + ".name"));
    }

    @Override
    public void onKill(EntityPlayer entityplayer, EntityLivingBase entity) {
        if (this.killCount < this.killTarget && this.entityType.isAssignableFrom(entity.getClass())) {
            ++this.killCount;
            this.updateQuest();
        }
    }

    public static class QFKillEntity
    extends LOTRMiniQuestKill.QFKill<LOTRMiniQuestKillEntity> {
        private Class entityType;

        public QFKillEntity(String name) {
            super(name);
        }

        public QFKillEntity setKillEntity(Class entityClass, int min, int max) {
            this.entityType = entityClass;
            this.setKillTarget(min, max);
            return this;
        }

        @Override
        public Class getQuestClass() {
            return LOTRMiniQuestKillEntity.class;
        }

        @Override
        public LOTRMiniQuestKillEntity createQuest(LOTREntityNPC npc, Random rand) {
            LOTRMiniQuestKillEntity quest = (LOTRMiniQuestKillEntity)super.createQuest(npc, rand);
            quest.entityType = this.entityType;
            return quest;
        }
    }

}

