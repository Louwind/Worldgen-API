package github.Louwind.Features.client.resource;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.util.FeatureGsons;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class FeatureGeneratorManager extends JsonReloadListener  {

    private static final Gson GSON = FeatureGsons.getFeatureGsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    @Deprecated
    private final Map<Identifier, FeatureGenerator> features = Maps.newHashMap();

    public FeatureGeneratorManager() {
        super(GSON, "features");
    }

    @Deprecated
    public FeatureGenerator get(Identifier id) {
        return this.features.get(id);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:features");
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
