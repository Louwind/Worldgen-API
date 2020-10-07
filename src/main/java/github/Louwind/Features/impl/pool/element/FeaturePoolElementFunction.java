package github.Louwind.Features.impl.pool.element;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import github.Louwind.Features.pool.element.FeaturesPoolElementFunction;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.FeaturePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Function;

public class FeaturePoolElementFunction implements FeaturesPoolElementFunction<FeaturePoolElement> {

    private final ConfiguredFeature<?, ?> configuredFeature;

    public FeaturePoolElementFunction(ConfiguredFeature<?, ?> configuredFeature)  {
        this.configuredFeature = configuredFeature;
    }

    @Override
    public FeaturePoolElementType<FeaturePoolElement> getType() {
        return FeaturePoolElements.FEATURE_POOL_ELEMENT;
    }

    @Override
    public Function<StructurePool.Projection, FeaturePoolElement> get() {
        return StructurePoolElement.method_30421(this.configuredFeature);
    }

    public static class Serializer implements JsonSerializer<FeaturePoolElementFunction> {

        @Override
        public void toJson(JsonObject json, FeaturePoolElementFunction object, JsonSerializationContext context) {

        }

        @Override
        public FeaturePoolElementFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            Identifier feature = FeaturesJsonHelper.getIdentifier(json, "feature");
            ConfiguredFeature<?, ?> configuredFeature = BuiltinRegistries.CONFIGURED_FEATURE.get(feature);

            return new FeaturePoolElementFunction(configuredFeature);
        }

    }

}
