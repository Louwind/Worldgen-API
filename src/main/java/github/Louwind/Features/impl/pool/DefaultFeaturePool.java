package github.Louwind.Features.impl.pool;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class DefaultFeaturePool implements FeaturePool {

    protected final List<FeatureEntry> entries;
    protected final List<FeatureFunction> functions;
    protected final List<FeatureContextSetter> setters;
    protected final FeatureProperties properties;
    protected final OptionalContextParameter<StructurePool> structurePool;

    public DefaultFeaturePool(OptionalContextParameter<StructurePool> structurePool, FeatureContextSetter[] setters, FeatureFunction[] functions, FeatureEntry[] entries, FeatureProperties properties) {
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

    public static class Serializer implements JsonSerializer<DefaultFeaturePool> {

        @Override
        public void toJson(JsonObject json, DefaultFeaturePool object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public DefaultFeaturePool fromJson(JsonObject json, JsonDeserializationContext context) {
            // TODO FeaturesJsonHelper::getParameter
            StructurePool structurePool = FeaturesJsonHelper.getStructurePool(json, "pool");
            FeatureProperties props = FeaturesJsonHelper.getPoolProperties(json, context, "properties");

            FeatureContextSetter[] setters = FeaturesJsonHelper.getSetters(json, context, "setters");
            FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context, "functions");
            FeatureEntry[] entries = FeaturesJsonHelper.getEntries(json, context, "entries");

            return new DefaultFeaturePool(OptionalContextParameter.of(structurePool), setters, functions, entries, props);
        }

    }

}
