package github.Louwind.Features.client.resource;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.metadata.StructureMetadata;
import github.Louwind.Features.util.FeatureGsons;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class StructureMetadataManager extends JsonDataLoader implements SimpleResourceReloadListener<Map<Identifier, JsonElement>> {

    private static final Gson GSON = FeatureGsons.getMetadataGsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    private final Map<Identifier, StructureMetadata> metadata = Maps.newHashMap();

    public StructureMetadataManager() {
        super(GSON, "structure_metadata");
    }

    @Override
    public CompletableFuture<Map<Identifier, JsonElement>> load(ResourceManager resourceManager, Profiler profiler, Executor executor) {
        Map<Identifier, JsonElement> map = this.prepare(resourceManager, profiler);

        return CompletableFuture.completedFuture(map);
    }

    @Override
    public CompletableFuture<Void> apply(Map<Identifier, JsonElement> loader, ResourceManager resourceManager, Profiler profiler, Executor executor) {
        return CompletableFuture.runAsync(() -> this.apply(loader, resourceManager, profiler));
    }

    public StructureMetadata get(Identifier id) {
        return this.metadata.get(id);
    }

    public boolean contains(Identifier id) {
        return this.metadata.containsKey(id);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:structure_metadata");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            try {
                StructureMetadata metadata = GSON.fromJson(jsonElement, StructureMetadata.class);

                this.metadata.put(id, metadata);

            } catch (Exception exception) {
                LOGGER.error("Couldn't parse structure metadata {}", id, exception);
            }

        });

    }

}
