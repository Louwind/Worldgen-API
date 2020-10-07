package github.Louwind.Features.impl.pool.element.supplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.pool.element.FeaturesElementSupplier;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.function.Function;

public class SingleElementSupplier implements FeaturesElementSupplier<SinglePoolElement> {

    private final String structure;
    private final StructureProcessorList list;

    public SingleElementSupplier(String structure, StructureProcessorList list)  {
        this.structure = structure;
        this.list = list;
    }

    @Override
    public FeaturePoolElementType<SinglePoolElement> getType() {
        return FeaturePoolElements.SINGLE;
    }

    @Override
    public Function<StructurePool.Projection, SinglePoolElement> get() {
        return StructurePoolElement.method_30435(this.structure, this.list);
    }

    public static class Serializer implements JsonSerializer<SingleElementSupplier> {

        @Override
        public void toJson(JsonObject json, SingleElementSupplier object, JsonSerializationContext context) {

        }

        @Override
        public SingleElementSupplier fromJson(JsonObject json, JsonDeserializationContext context) {
            StructureProcessorList list = FeaturesJsonHelper.getProcessors(json, context, "processors");
            String structure = JsonHelper.getString(json, "structure");

            return new SingleElementSupplier(structure, list);
        }

    }

}
