package github.Louwind.Features.client.resource;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.generator.FeatureGenerator;
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

public class FeatureGeneratorManager extends JsonDataLoader implements SimpleResourceReloadListener<Map<Identifier, JsonElement>>  {

    private static final Gson GSON = FeatureGsons.getFeatureGsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    private final Map<Identifier, FeatureGenerator> features = Maps.newHashMap();

    public FeatureGeneratorManager() {
        super(GSON, "features");
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

    public FeatureGenerator get(Identifier id) {
        return this.features.get(id);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:feature_types");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            try {
                FeatureGenerator generator = GSON.fromJson(jsonElement, FeatureGenerator.class);

                this.features.put(id, generator);
            } catch (Exception exception) {
                LOGGER.error("Couldn't parse feature {}", id, exception);
            }

        });

    }

}
