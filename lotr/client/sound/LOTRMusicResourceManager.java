/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.SimpleResource
 *  net.minecraft.client.resources.data.IMetadataSerializer
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.sound;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class LOTRMusicResourceManager
implements IResourceManager {
    private Map<ResourceLocation, IResource> resourceMap = new HashMap<ResourceLocation, IResource>();

    public Set getResourceDomains() {
        return this.resourceMap.entrySet();
    }

    public IResource getResource(ResourceLocation resource) throws IOException {
        return this.resourceMap.get((Object)resource);
    }

    public List getAllResources(ResourceLocation resource) throws IOException {
        ArrayList<IResource> list = new ArrayList<IResource>();
        list.add(this.getResource(resource));
        return list;
    }

    public void registerMusicResources(ResourceLocation resource, InputStream in) {
        SimpleResource ires = new SimpleResource(resource, in, null, null);
        this.resourceMap.put(resource, (IResource)ires);
    }
}

