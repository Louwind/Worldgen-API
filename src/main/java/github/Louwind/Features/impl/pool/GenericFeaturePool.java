package github.Louwind.Features.impl.pool;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.FeaturePoolTypes;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.pool.FeaturePoolType;
import github.Louwind.Features.properties.FeatureProperties;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class GenericFeaturePool implements FeaturePool {

    protected final List<FeatureEntry> entries;
    protected final List<FeatureFunction> functions;
    protected final List<FeatureContextSetter> setters;
    protected final FeatureProperties properties;
    protected final OptionalContextParameter<StructurePool> structurePool;

    public GenericFeaturePool(OptionalContextParameter<StructurePool> structurePool, FeatureContextSetter[] setters, FeatureFunction[] functions, FeatureEntry[] entries, FeatureProperties properties) {
        this.entries = Arrays.asList(entries);
        this.functions = Arrays.asList(functions);
        this.setters = Arrays.asList(setters);
        this.properties = properties;
        this.structurePool = structurePool;
    }

    @Override
    public List<FeatureEntry> getEntries() {
        return this.entries;
    }

    @Override
    public List<FeatureFunction> getFunctions() {
        return this.functions;
    }

    @Override
    public FeatureProperties getProperties() {
        return this.properties;
    }

    @Override
    public List<FeatureContextSetter> getSetters() {
        return this.setters;
    }

    @Override
    public OptionalContextParameter<StructurePool> getStructurePool() {
        return this.structurePool;
    }

    @Override
    public FeaturePoolType getType() {
        return FeaturePoolTypes.POOL;
    }

    public static class Serializer implements JsonSerializer<GenericFeaturePool> {

        @Override
        public void toJson(JsonObject json, GenericFeaturePool object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public GenericFeaturePool fromJson(JsonObject json, JsonDeserializationContext context) {
            // TODO FeaturesJsonHelper::getParameter
            StructurePool structurePool = FeaturesJsonHelper.getStructurePool(json, "pool");
            FeatureProperties props = FeaturesJsonHelper.getPoolProperties(json, context, "properties");

            FeatureContextSetter[] setters = FeaturesJsonHelper.getSetters(json, context, "setters");
            FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context, "functions");
            FeatureEntry[] entries = FeaturesJsonHelper.getEntries(json, context, "entries");

            return new GenericFeaturePool(OptionalContextParameter.of(structurePool), setters, functions, entries, props);
        }

    }

}
