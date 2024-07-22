/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IReloadableResourceManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import cpw.mods.fml.common.FMLLog;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;
import lotr.client.LOTRTextures;
import lotr.common.entity.LOTRRandomSkinEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRandomSkins
implements IResourceManagerReloadListener {
    private static Random rand = new Random();
    private static Minecraft mc = Minecraft.getMinecraft();
    private static Map<String, LOTRRandomSkins> allRandomSkins = new HashMap<String, LOTRRandomSkins>();
    protected String skinPath;
    protected List<ResourceLocation> skins;

    public static LOTRRandomSkins loadSkinsList(String path) {
        LOTRRandomSkins skins = allRandomSkins.get(path);
        if (skins == null) {
            skins = new LOTRRandomSkins(path, true, new Object[0]);
            allRandomSkins.put(path, skins);
        }
        return skins;
    }

    private LOTRRandomSkins(String path, boolean register, Object ... args) {
        this.skinPath = path;
        this.handleExtraArgs(args);
        if (register) {
            ((IReloadableResourceManager)mc.getResourceManager()).registerReloadListener((IResourceManagerReloadListener)this);
        } else {
            this.loadAllRandomSkins();
        }
    }

    protected void handleExtraArgs(Object ... args) {
    }

    protected void loadAllRandomSkins() {
        this.skins = new ArrayList<ResourceLocation>();
        int skinCount = 0;
        int skips = 0;
        int maxSkips = 10;
        boolean foundAfterSkip = false;
        do {
            ResourceLocation skin = new ResourceLocation(this.skinPath + "/" + skinCount + ".png");
            boolean noFile = false;
            try {
                if (mc.getResourceManager().getResource(skin) == null) {
                    noFile = true;
                }
            }
            catch (Exception e) {
                noFile = true;
            }
            if (noFile) {
                if (++skips >= maxSkips) break;
                ++skinCount;
                continue;
            }
            this.skins.add(skin);
            ++skinCount;
            if (skips <= 0) continue;
            foundAfterSkip = true;
        } while (true);
        if (this.skins.isEmpty()) {
            FMLLog.warning((String)"LOTR: No random skins for %s", (Object[])new Object[]{this.skinPath});
        }
        if (foundAfterSkip) {
            FMLLog.warning((String)"LOTR: Random skins %s skipped a number. This is not good - please number your skins from 0 and upwards, with no gaps!", (Object[])new Object[]{this.skinPath});
        }
    }

    public ResourceLocation getRandomSkin(LOTRRandomSkinEntity rsEntity) {
        if (this.skins == null || this.skins.isEmpty()) {
            return LOTRTextures.missingTexture;
        }
        Entity entity = (Entity)rsEntity;
        long l = entity.getUniqueID().getLeastSignificantBits();
        long hash = this.skinPath.hashCode();
        l = l * 39603773L ^ l * 6583690632L ^ hash;
        l = l * hash * 2906920L + l * 65936063L;
        rand.setSeed(l);
        int i = rand.nextInt(this.skins.size());
        return this.skins.get(i);
    }

    public ResourceLocation getRandomSkin() {
        int i = rand.nextInt(this.skins.size());
        return this.skins.get(i);
    }

    public static int nextInt(LOTRRandomSkinEntity rsEntity, int n) {
        Entity entity = (Entity)rsEntity;
        long l = entity.getUniqueID().getLeastSignificantBits();
        l = l * 29506206L * (l ^ 0x6429C58L) + 25859L;
        l = l * l * 426430295004L + 25925025L * l;
        rand.setSeed(l);
        return rand.nextInt(n);
    }

    public List<ResourceLocation> getAllSkins() {
        return this.skins;
    }

    public void onResourceManagerReload(IResourceManager resourcemanager) {
        this.loadAllRandomSkins();
    }

    public static LOTRRandomSkins getCombinatorialSkins(String path, String ... layers) {
        String combinedPath = path;
        for (String s : layers) {
            combinedPath = combinedPath + "_" + s;
        }
        LOTRRandomSkins skins = allRandomSkins.get(combinedPath);
        if (skins == null) {
            skins = new LOTRRandomSkinsCombinatorial(path, layers);
            allRandomSkins.put(combinedPath, skins);
        }
        return skins;
    }

    private static class LOTRRandomSkinsCombinatorial
    extends LOTRRandomSkins {
        private String[] skinLayers;

        private LOTRRandomSkinsCombinatorial(String path, String ... layers) {
            super(path, true, (Object[])layers);
        }

        @Override
        protected void handleExtraArgs(Object ... args) {
            this.skinLayers = (String[])args;
        }

        @Override
        protected void loadAllRandomSkins() {
            this.skins = new ArrayList();
            ArrayList layeredImages = new ArrayList();
            ArrayList<BufferedImage> tempLayered = new ArrayList<BufferedImage>();
            block2: for (String layer : this.skinLayers) {
                String layerPath = this.skinPath + "/" + layer;
                LOTRRandomSkins layerSkins = new LOTRRandomSkins(layerPath, false, new Object[0]);
                tempLayered.clear();
                for (ResourceLocation overlay : layerSkins.getAllSkins()) {
                    try {
                        BufferedImage overlayImage = ImageIO.read(mc.getResourceManager().getResource(overlay).getInputStream());
                        if (layeredImages.isEmpty()) {
                            tempLayered.add(overlayImage);
                            continue;
                        }
                        for (BufferedImage baseImage : layeredImages) {
                            int skinWidth = baseImage.getWidth();
                            int skinHeight = baseImage.getHeight();
                            BufferedImage newImage = new BufferedImage(skinWidth, skinHeight, 2);
                            for (int i = 0; i < skinWidth; ++i) {
                                for (int j = 0; j < skinHeight; ++j) {
                                    int opaqueTest;
                                    int baseRGB = baseImage.getRGB(i, j);
                                    int overlayRGB = overlayImage.getRGB(i, j);
                                    if ((overlayRGB & 0xFF000000) == (opaqueTest = -16777216)) {
                                        newImage.setRGB(i, j, overlayRGB);
                                        continue;
                                    }
                                    newImage.setRGB(i, j, baseRGB);
                                }
                            }
                            tempLayered.add(newImage);
                        }
                    }
                    catch (IOException e) {
                        FMLLog.severe((String)("LOTR: Error combining skins " + this.skinPath + " on layer " + layer + "!"), (Object[])new Object[0]);
                        e.printStackTrace();
                        break block2;
                    }
                }
                layeredImages.clear();
                layeredImages.addAll(tempLayered);
            }
            int skinCount = 0;
            for (BufferedImage image : layeredImages) {
                ResourceLocation skin = LOTRRandomSkins.access$200().renderEngine.getDynamicTextureLocation(this.skinPath + "_layered/" + skinCount + ".png", new DynamicTexture(image));
                this.skins.add(skin);
                ++skinCount;
            }
            FMLLog.info((String)("LOTR: Assembled combinatorial skins for " + this.skinPath + ": " + this.skins.size() + " skins"), (Object[])new Object[0]);
        }
    }

}

