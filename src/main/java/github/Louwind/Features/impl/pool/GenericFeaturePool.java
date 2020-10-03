package github.Louwind.Features.impl.pool;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.init.FeaturePools;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.pool.FeaturePoolType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class GenericFeaturePool implements FeaturePool {

    protected final List<FeatureEntry> entries;
    protected final List<FeatureFunction> functions;
    protected final FeatureContextProvider provider;
    protected final OptionalContextParameter<StructurePool> structurePool;

    public GenericFeaturePool(OptionalContextParameter<StructurePool> structurePool, FeatureFunction[] functions, FeatureEntry[] entries, FeatureContextProvider provider) {
        this.entries = Arrays.asList(entries);
        this.functions = Arrays.asList(functions);
        this.provider = provider;
        this.structurePool = structurePool;
    }

    @Override
    public List<FeatureEntry> getEntries() {
        return this.entries;
    }

    @Override
    public FeatureContextProvider getContextProvider() {
        return this.provider;
    }

    @Override
    public List<FeatureFunction> getFunctions() {
        return this.functions;
    }

    @Override
    public OptionalContextParameter<StructurePool> getStructurePool() {
        return this.structurePool;
    }

    @Override
    public FeaturePoolType getType() {
        return FeaturePools.POOL;
    }

    public static class Serializer implements JsonSerializer<GenericFeaturePool> {

        @Override
        public void toJson(JsonObject json, GenericFeaturePool object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public GenericFeaturePool fromJson(JsonObject json, JsonDeserializationContext context) {
            StructurePool structurePool = FeaturesJsonHelper.getStructurePool(json, "pool");

            FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context, "functions");
            FeatureEntry[] entries = FeaturesJsonHelper.getEntries(json, context, "entries");

            FeatureContextProvider provider = FeaturesJsonHelper.getContextProvider(json, context, "context");

            return new GenericFeaturePool(OptionalContextParameter.of(structurePool), functions, entries, provider);
        }

    }

}
