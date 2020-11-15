package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.JsonReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static net.minecraft.util.registry.BuiltinRegistries.STRUCTURE_POOL;

public class StructurePoolReloadListener extends JsonReloadListener {

    private static final Gson GSON = FeatureGsons.getStructurePoolGsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public StructurePoolReloadListener() {
        super(GSON, "pools");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            try {
                StructurePool structurePool = GSON.fromJson(jsonElement, StructurePool.class);
                Identifier structurePoolId = structurePool.getId();

                if(!STRUCTURE_POOL.containsId(id))
                    BuiltinRegistries.add(STRUCTURE_POOL, structurePoolId, structurePool);
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

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:pools");
    }

}
