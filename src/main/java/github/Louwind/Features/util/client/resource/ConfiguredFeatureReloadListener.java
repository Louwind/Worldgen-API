package github.Louwind.Features.util.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Function;

public abstract class ConfiguredFeatureReloadListener<CF, F> extends SimpleJsonReloadListener<CF> {

    protected final Registry<F> featureRegistry;
    protected final Function<CF, F> function;

    public ConfiguredFeatureReloadListener(Gson gson, Type clazz, Function<CF, F> function, Registry<CF> configured, Registry<F> featureRegistry, String dataType) {
        super(gson, clazz, configured, dataType);

        this.featureRegistry = featureRegistry;
        this.function = function;
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {
        super.apply(loader, manager, profiler);

        loader.forEach((id, element) -> {

            if(this.registry.containsId(id)) {
                CF configuredFeature = this.registry.get(id);
                F feature = this.function.apply(configuredFeature);

                if(!this.featureRegistry.containsId(id))
                    BuiltinRegistries.add(this.featureRegistry, id, feature);
                else {
                    int rawId = this.featureRegistry.getRawId(feature);

                    this.featureRegistry.getKey(feature).ifPresent(key -> {
                        BuiltinRegistries.set(this.featureRegistry, rawId, key, feature);
                    });
                }
            }

        });

    }

}
