package github.Louwind.Features.impl.pool.element;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.pool.element.FeaturesPoolElementFunction;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import net.minecraft.structure.pool.ListPoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ListPoolElementFunction implements FeaturesPoolElementFunction<ListPoolElement> {

    private final List<Function<StructurePool.Projection, ? extends StructurePoolElement>> list;

    public ListPoolElementFunction(List<Function<StructurePool.Projection, ? extends StructurePoolElement>> list)  {
        this.list = list;
    }

    @Override
    public FeaturePoolElementType<ListPoolElement> getType() {
        return FeaturePoolElements.LIST_POOL_ELEMENT;
    }

    @Override
    public Function<StructurePool.Projection, ListPoolElement> get() {
        return StructurePoolElement.method_30429(this.list);
    }

    public static class Serializer implements JsonSerializer<ListPoolElementFunction> {

        @Override
        public void toJson(JsonObject json, ListPoolElementFunction object, JsonSerializationContext context) {

        }

        @Override
        public ListPoolElementFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            List<Function<StructurePool.Projection, ? extends StructurePoolElement>> list = StreamSupport
                    .stream(json.getAsJsonArray("elements").spliterator(), false)
                    .map(JsonElement::getAsJsonObject)
                    .map(obj -> {
                        FeaturesPoolElementFunction<? extends StructurePoolElement> function = JsonHelper.deserialize(obj, "element", context, FeaturesPoolElementFunction.class);

                        return function.get();
                    }).collect(Collectors.toList());

            return new ListPoolElementFunction(list);
        }

    }

}
