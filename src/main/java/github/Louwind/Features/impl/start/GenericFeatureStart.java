package github.Louwind.Features.impl.start;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.pool.GenericFeaturePool;
import github.Louwind.Features.start.FeatureStart;
import github.Louwind.Features.start.FeatureStartType;
import github.Louwind.Features.impl.init.FeatureStarts;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class GenericFeatureStart implements FeatureStart {

    protected final List<FeaturePool> pools;
    protected final StructurePool startPool;

    public GenericFeatureStart(StructurePool startPool, FeaturePool[] pools) {
        this.pools = Arrays.asList(pools);
        this.startPool = startPool;
    }

    @Override
    public FeatureStartType getType() {
        return FeatureStarts.START;
    }

    @Override
    public List<FeaturePool> getPools() {
        return this.pools;
    }

    @Override
    public FeaturePool getStartPool() {
        return this.pools.stream()
                .filter(Predicate.not(GenericFeaturePool.EMPTY::equals))
                .filter(pool -> this.startPool == pool.getStructurePool())
                .findFirst()
                .orElse(GenericFeaturePool.empty(this.startPool));
    }

    public static class Serializer implements JsonSerializer<GenericFeatureStart> {

        @Override
        public void toJson(JsonObject json, GenericFeatureStart object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public GenericFeatureStart fromJson(JsonObject json, JsonDeserializationContext context) {
            FeaturePool[] pools = FeaturesJsonHelper.getPools(json, context, "pools");
            StructurePool startPool = FeaturesJsonHelper.getStructurePool(json, "start_pool");

            return new GenericFeatureStart(startPool, pools);
        }

    }

}
