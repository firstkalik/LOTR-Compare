/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderDwarf;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityBanditDwarf;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderWickedDwarfBandit
extends LOTRRenderDwarf {
    private static LOTRRandomSkins wickedSkinsMale;
    private static LOTRRandomSkins wickedBanditSkinsMale;

    public LOTRRenderWickedDwarfBandit() {
        wickedSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/dwarf/wicked_male");
        wickedBanditSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/dwarf/wicked_male");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBanditDwarf dwarf = (LOTREntityBanditDwarf)entity;
        return wickedSkinsMale.getRandomSkin(dwarf);
    }
}

