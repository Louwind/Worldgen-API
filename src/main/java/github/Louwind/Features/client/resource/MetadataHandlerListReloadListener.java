package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Lifecycle;
import github.Louwind.Features.util.MetadataHandlerList;
import github.Louwind.Features.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.JsonReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.OptionalInt;

import static github.Louwind.Features.registry.Registries.METADATA_HANDLER_LIST;

public class MetadataHandlerListReloadListener extends JsonReloadListener {

    private static final Gson GSON = FeaturesGsons.getMetadataGsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public MetadataHandlerListReloadListener() {
        super(GSON, "worldgen/structure/metadata");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
        prepared.forEach((id, jsonElement) -> {

            try {
                MetadataHandlerList t = GSON.fromJson(jsonElement, MetadataHandlerList.class);

                if(!METADATA_HANDLER_LIST.containsId(id))
                    BuiltinRegistries.add(METADATA_HANDLER_LIST, id, t);
                else
                    METADATA_HANDLER_LIST.getKey(t).ifPresent(key -> {
                        Lifecycle lifecycle = METADATA_HANDLER_LIST.getLifecycle();
                        int rawId = METADATA_HANDLER_LIST.getRawId(t);

                        METADATA_HANDLER_LIST.replace(OptionalInt.of(rawId), key, t, lifecycle);
                    });

            } catch (Exception exception) {
                LOGGER.error("Couldn't parse json {}", id, exception);
            }

        });
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:metadata");
    }

}
