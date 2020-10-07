package github.Louwind.Features.impl.pool.element.supplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import github.Louwind.Features.pool.element.FeaturesElementSupplier;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.LegacySinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.function.Function;

public class LegacySingleElementSupplier implements FeaturesElementSupplier<LegacySinglePoolElement> {

    private final StructureProcessorList list;
    private final String structure;

    public LegacySingleElementSupplier(String structure, StructureProcessorList list)  {
        this.list = list;
        this.structure = structure;
    }

    @Override
    public FeaturePoolElementType<LegacySinglePoolElement> getType() {
        return FeaturePoolElements.LEGACY_SINGLE;
    }

    @Override
    public Function<StructurePool.Projection, LegacySinglePoolElement> get() {
        return StructurePoolElement.method_30426(this.structure, this.list);
    }

    public static class Serializer implements JsonSerializer<LegacySingleElementSupplier> {

        @Override
        public void toJson(JsonObject json, LegacySingleElementSupplier object, JsonSerializationContext context) {

        }

        @Override
        public LegacySingleElementSupplier fromJson(JsonObject json, JsonDeserializationContext context) {
            StructureProcessorList processors = FeaturesJsonHelper.getProcessors(json, context, "processors");
            String structure = JsonHelper.getString(json, "structure");

            return new LegacySingleElementSupplier(structure, processors);
        }

    }

}
