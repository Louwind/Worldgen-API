package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public abstract class JsonReloadListener<T> extends JsonDataLoader implements SimpleResourceReloadListener<Map<Identifier, JsonElement>> {

    protected static final Logger LOGGER = LogManager.getLogger();

    protected final Gson gson;
    protected final Class<T> clazz;
    protected final Registry<T> registry;

    public JsonReloadListener(Gson gson, Class<T> clazz, Registry<T> registry, String dataType) {
        super(gson, dataType);

        this.clazz = clazz;
        this.gson = gson;
        this.registry = registry;
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
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            try {
                T structureProcessorList = this.gson.fromJson(jsonElement, this.clazz);

                if(!this.registry.containsId(id))
                    BuiltinRegistries.add(this.registry, id, structureProcessorList);
                else {
                    int rawId = this.registry.getRawId(structureProcessorList);

                    this.registry.getKey(structureProcessorList).ifPresent(key -> {
                        BuiltinRegistries.set(this.registry, rawId, key, structureProcessorList);
                    });
                }

            } catch (Exception exception) {
                LOGGER.error("Couldn't parse json {}", id, exception);
            }

        });

    }

}
