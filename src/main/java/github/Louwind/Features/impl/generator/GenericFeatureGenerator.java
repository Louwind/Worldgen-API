package github.Louwind.Features.impl.generator;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.generator.FeatureGeneratorType;
import github.Louwind.Features.impl.init.FeatureGenerators;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class GenericFeatureGenerator implements FeatureGenerator {

    protected final List<FeaturePool> pools;

    public GenericFeatureGenerator(FeaturePool[] pools) {
        this.pools = Arrays.asList(pools);
    }

    @Override
    public FeatureGeneratorType getType() {
        return FeatureGenerators.GENERATOR;
    }

    @Override
    public List<FeaturePool> getPools() {
        return this.pools;
    }

    public static class Serializer implements JsonSerializer<GenericFeatureGenerator> {

        @Override
        public void toJson(JsonObject json, GenericFeatureGenerator object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public GenericFeatureGenerator fromJson(JsonObject json, JsonDeserializationContext context) {
            FeaturePool[] pools = FeaturesJsonHelper.getPools(json, context, "pools");

            return new GenericFeatureGenerator(pools);
        }

    }

}
