/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.resources.IReloadableResourceManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import lotr.common.entity.npc.LOTREntityHuornBase;
import lotr.common.entity.npc.LOTREntityTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.lwjgl.opengl.GL11;

public class LOTRHuornTextures
implements IResourceManagerReloadListener {
    public static LOTRHuornTextures INSTANCE = new LOTRHuornTextures();
    private RenderManager renderManager = RenderManager.instance;
    private HashMap woodTextures = new HashMap();
    private HashMap leafTexturesFast = new HashMap();
    private HashMap leafTexturesFancy = new HashMap();

    public void onResourceManagerReload(IResourceManager resourcemanager) {
        this.woodTextures.clear();
        this.leafTexturesFast.clear();
        this.leafTexturesFancy.clear();
    }

    public void bindWoodTexture(LOTREntityHuornBase entity) {
        int treeType = entity.getTreeType();
        Block block = LOTREntityTree.WOOD_BLOCKS[treeType];
        int meta = LOTREntityTree.WOOD_META[treeType];
        ResourceLocation texture = (ResourceLocation)this.woodTextures.get(treeType);
        if (texture == null) {
            texture = this.getDynamicHuornTexture(block, meta);
            this.woodTextures.put(treeType, texture);
        }
        this.renderManager.renderEngine.bindTexture(texture);
    }

    public void bindLeafTexture(LOTREntityHuornBase entity) {
        int treeType = entity.getTreeType();
        Block block = LOTREntityTree.LEAF_BLOCKS[treeType];
        int meta = LOTREntityTree.LEAF_META[treeType];
        ResourceLocation texture = (ResourceLocation)this.leafTexturesFast.get(treeType);
        if (Minecraft.isFancyGraphicsEnabled()) {
            texture = (ResourceLocation)this.leafTexturesFancy.get(treeType);
        }
        if (texture == null) {
            texture = this.getDynamicHuornTexture(block, meta);
            if (Minecraft.isFancyGraphicsEnabled()) {
                this.leafTexturesFancy.put(treeType, texture);
            } else {
                this.leafTexturesFast.put(treeType, texture);
            }
        }
        this.renderManager.renderEngine.bindTexture(texture);
        int color = block.getRenderColor(meta);
        if (block == Blocks.leaves && meta == 0) {
            int i = MathHelper.floor_double((double)entity.posX);
            int j = MathHelper.floor_double((double)entity.boundingBox.minY);
            int k = MathHelper.floor_double((double)entity.posZ);
            color = entity.worldObj.getBiomeGenForCoords(i, k).getBiomeFoliageColor(i, j, k);
        }
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
    }

    private ResourceLocation getDynamicHuornTexture(Block block, int meta) {
        try {
            boolean aF = Minecraft.getMinecraft().gameSettings.anisotropicFiltering > 1;
            Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture);
            BufferedImage[] icons = new BufferedImage[6];
            int width = block.getIcon(0, meta).getIconWidth();
            if (aF) {
                width -= 16;
            }
            for (int i = 0; i < 6; ++i) {
                IIcon icon = block.getIcon(i, meta);
                int iconWidth = icon.getIconWidth();
                int iconHeight = icon.getIconHeight();
                if (aF) {
                    iconWidth -= 16;
                    iconHeight -= 16;
                }
                if (iconWidth != width || iconHeight != width) {
                    throw new RuntimeException("Error registering Huorn textures: all icons for block " + block.getUnlocalizedName() + " must have the same texture dimensions");
                }
                ResourceLocation iconPath = new ResourceLocation(icon.getIconName());
                ResourceLocation r = new ResourceLocation(iconPath.getResourceDomain(), "textures/blocks/" + iconPath.getResourcePath() + ".png");
                BufferedImage iconImage = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(r).getInputStream());
                icons[i] = iconImage.getSubimage(0, 0, width, width);
            }
            BufferedImage image = new BufferedImage(width * 4, width * 2, 2);
            Graphics2D g2d = image.createGraphics();
            g2d.drawImage(icons[1], null, width, 0);
            g2d.drawImage(icons[0], null, width * 2, 0);
            g2d.drawImage(icons[4], null, 0, width);
            g2d.drawImage(icons[2], null, width, width);
            g2d.drawImage(icons[5], null, width * 2, width);
            g2d.drawImage(icons[3], null, width * 3, width);
            g2d.dispose();
            return this.renderManager.renderEngine.getDynamicTextureLocation("lotr:huorn", new DynamicTexture(image));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static {
        ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener((IResourceManagerReloadListener)INSTANCE);
    }
}

