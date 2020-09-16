package github.Louwind.Features;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

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

                StructurePools.register(structurePool);
            } catch (Exception exception) {
                LOGGER.error("Couldn't parse structure pool {}", id, exception);
            }

        });

    }

}
