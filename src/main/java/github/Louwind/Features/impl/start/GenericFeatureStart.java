package github.Louwind.Features.impl.start;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.start.FeatureStart;
import github.Louwind.Features.start.FeatureStartType;
import github.Louwind.Features.impl.init.FeatureStarts;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class GenericFeatureStart implements FeatureStart {

    protected final List<FeaturePool> pools;

    public GenericFeatureStart(FeaturePool[] pools) {
        this.pools = Arrays.asList(pools);
    }

    @Override
    public FeatureStartType getType() {
        return FeatureStarts.START;
    }

    @Override
    public List<FeaturePool> getPools() {
        return this.pools;
    }

    public static class Serializer implements JsonSerializer<GenericFeatureStart> {

        @Override
        public void toJson(JsonObject json, GenericFeatureStart object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public GenericFeatureStart fromJson(JsonObject json, JsonDeserializationContext context) {
            FeaturePool[] pools = FeaturesJsonHelper.getPools(json, context, "pools");

            return new GenericFeatureStart(pools);
        }

    }

}
