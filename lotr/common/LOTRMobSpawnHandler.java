/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraftforge.event.entity.living.LivingSpawnEvent
 *  net.minecraftforge.event.entity.living.LivingSpawnEvent$CheckSpawn
 */
package lotr.common;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class LOTRMobSpawnHandler {
    @SubscribeEvent
    public void onCheckSpawn(LivingSpawnEvent.CheckSpawn event) {
        if (event.world.provider.dimensionId == 0 && (event.entityLiving.isCreatureType(EnumCreatureType.monster, false) || event.entityLiving.isCreatureType(EnumCreatureType.waterCreature, false) || event.entityLiving.isCreatureType(EnumCreatureType.ambient, false) || event.entityLiving.isCreatureType(EnumCreatureType.creature, false) || event.entityLiving.isCreatureType(EnumCreatureType.ambient, false))) {
            event.setResult(Event.Result.DENY);
        }
    }
}

