/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render;

import cpw.mods.fml.common.FMLLog;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import lotr.client.render.LOTRBufferedImageIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class LOTRDrinkIcons {
    private static int maskColor = 16711935;
    private static Map<String, BufferedImage> vesselIcons = new HashMap<String, BufferedImage>();
    private static Map<Item, BufferedImage> liquidIcons = new HashMap<Item, BufferedImage>();

    public static IIcon registerDrinkIcon(IIconRegister iconregister, Item item, String itemName, String vessel) {
        Minecraft mc = Minecraft.getMinecraft();
        IResourceManager resourceManager = mc.getResourceManager();
        TextureMap textureMap = (TextureMap)iconregister;
        String baseIconName = itemName.substring("lotr:".length());
        try {
            BufferedImage liquidIcon;
            BufferedImage vesselIcon = vesselIcons.get(vessel);
            if (vesselIcon == null) {
                ResourceLocation res = new ResourceLocation("lotr", "textures/items/drink_" + vessel + ".png");
                vesselIcon = ImageIO.read(resourceManager.getResource(res).getInputStream());
                vesselIcons.put(vessel, vesselIcon);
            }
            if ((liquidIcon = liquidIcons.get((Object)item)) == null) {
                ResourceLocation res = new ResourceLocation("lotr", "textures/items/" + baseIconName + "_liquid.png");
                liquidIcon = ImageIO.read(resourceManager.getResource(res).getInputStream());
                liquidIcons.put(item, liquidIcon);
            }
            String iconName = "lotr:textures/items/" + baseIconName + "_" + vessel;
            int iconWidth = vesselIcon.getWidth();
            int iconHeight = vesselIcon.getHeight();
            BufferedImage iconImage = new BufferedImage(iconWidth, iconHeight, 2);
            for (int i = 0; i < iconImage.getWidth(); ++i) {
                for (int j = 0; j < iconImage.getHeight(); ++j) {
                    int rgb = vesselIcon.getRGB(i, j);
                    if ((rgb & 0xFFFFFF) == maskColor) {
                        rgb = liquidIcon.getRGB(i, j);
                    }
                    iconImage.setRGB(i, j, rgb);
                }
            }
            LOTRBufferedImageIcon icon = new LOTRBufferedImageIcon(iconName, iconImage);
            icon.setIconWidth(iconImage.getWidth());
            icon.setIconHeight(iconImage.getHeight());
            textureMap.setTextureEntry(iconName, (TextureAtlasSprite)icon);
            return icon;
        }
        catch (IOException e) {
            FMLLog.severe((String)"Failed to load mug textures for %s", (Object[])new Object[]{item.getUnlocalizedName()});
            e.printStackTrace();
            return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("");
        }
    }

    public static IIcon registerLiquidIcon(IIconRegister iconregister, Item item, String itemName) {
        return iconregister.registerIcon(itemName + "_liquid");
    }
}

