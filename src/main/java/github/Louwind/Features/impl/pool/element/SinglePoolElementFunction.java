package github.Louwind.Features.impl.pool.element;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.pool.element.FeaturesPoolElementFunction;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.function.Function;

public class SinglePoolElementFunction implements FeaturesPoolElementFunction<SinglePoolElement> {

    private final String structure;
    private final StructureProcessorList list;

    public SinglePoolElementFunction(String structure, StructureProcessorList list)  {
        this.structure = structure;
        this.list = list;
    }

    @Override
    public FeaturePoolElementType<SinglePoolElement> getType() {
        return FeaturePoolElements.SINGLE_POOL_ELEMENT;
    }

    @Override
    public Function<StructurePool.Projection, SinglePoolElement> get() {
        return StructurePoolElement.method_30435(this.structure, this.list);
    }

    public static class Serializer implements JsonSerializer<SinglePoolElementFunction> {

        @Override
        public void toJson(JsonObject json, SinglePoolElementFunction object, JsonSerializationContext context) {

        }

        @Override
        public SinglePoolElementFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            StructureProcessorList list = FeaturesJsonHelper.getProcessors(json, context, "processors");
            String structure = JsonHelper.getString(json, "structure");

            return new SinglePoolElementFunction(structure, list);
        }

    }

}
