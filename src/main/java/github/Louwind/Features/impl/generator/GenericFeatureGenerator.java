package github.Louwind.Features.impl.generator;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.generator.FeatureGeneratorType;
import github.Louwind.Features.impl.init.FeatureGenerators;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class GenericFeatureGenerator implements FeatureGenerator {

    protected final List<FeaturePool> pools;
    protected final FeatureProperties properties;

    public GenericFeatureGenerator(FeaturePool[] pools, FeatureProperties properties) {
        this.pools = Arrays.asList(pools);
        this.properties = properties;
    }

    @Override
    public FeatureGeneratorType getType() {
        return FeatureGenerators.GENERIC;
    }

    @Override
    public List<FeaturePool> getPools() {
        return this.pools;
    }

    @Override
    public FeatureProperties getProperties() {
        return this.properties;
    }

    public static class Serializer implements JsonSerializer<GenericFeatureGenerator> {

        @Override
        public void toJson(JsonObject json, GenericFeatureGenerator object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public GenericFeatureGenerator fromJson(JsonObject json, JsonDeserializationContext context) {
            FeaturePool[] pools = FeaturesJsonHelper.getPools(json, context, "pools");
            FeatureProperties props = FeaturesJsonHelper.getPoolProperties(json, context, "properties");

            return new GenericFeatureGenerator(pools, props);
        }

    }

}
