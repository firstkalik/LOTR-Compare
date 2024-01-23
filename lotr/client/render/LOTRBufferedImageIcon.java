/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.data.AnimationMetadataSection
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class LOTRBufferedImageIcon
extends TextureAtlasSprite {
    private final String iconName;
    private final BufferedImage imageRGB;
    private static Set<String> loadedResources = new HashSet<String>();

    public LOTRBufferedImageIcon(String s, BufferedImage rgb) {
        super(s);
        this.iconName = s;
        this.imageRGB = rgb;
        if (!loadedResources.contains(s)) {
            TextureManager texManager = Minecraft.getMinecraft().getTextureManager();
            ResourceLocation r = new ResourceLocation(this.iconName);
            ResourceLocation r1 = new ResourceLocation(r.getResourceDomain(), String.format("%s%s", r.getResourcePath(), ".png"));
            texManager.deleteTexture(r1);
            texManager.loadTexture(r1, (ITextureObject)new DynamicTexture(this.imageRGB));
            loadedResources.add(s);
        }
    }

    public boolean load(IResourceManager resourceManager, ResourceLocation resourceLocation) {
        BufferedImage[] imageArray = new BufferedImage[1 + Minecraft.getMinecraft().gameSettings.mipmapLevels];
        imageArray[0] = this.imageRGB;
        this.loadSprite(imageArray, null, (float)Minecraft.getMinecraft().gameSettings.anisotropicFiltering > 1.0f);
        return false;
    }

    public boolean hasCustomLoader(IResourceManager resourceManager, ResourceLocation resourceLocation) {
        return true;
    }
}

