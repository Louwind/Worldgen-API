package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.util.FeatureGsons;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static net.minecraft.util.registry.BuiltinRegistries.STRUCTURE_POOL;

public class StructurePoolManager extends JsonDataLoader implements SimpleResourceReloadListener<Map<Identifier, JsonElement>> {

    private static final Gson GSON = FeatureGsons.getProcessorGsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public StructurePoolManager() {
        super(GSON, "pools");
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

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:pools");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            try {
                StructurePool structurePool = GSON.fromJson(jsonElement, StructurePool.class);

                if(!STRUCTURE_POOL.containsId(id))
                    StructurePools.register(structurePool);
                else {
                    int rawId = STRUCTURE_POOL.getRawId(structurePool);

                    STRUCTURE_POOL.getKey(structurePool).ifPresent(key -> {
                        BuiltinRegistries.set(STRUCTURE_POOL, rawId, key, structurePool);
                    });
                }

            } catch (Exception exception) {
                LOGGER.error("Couldn't parse structure pool {}", id, exception);
            }

        });

    }

}
