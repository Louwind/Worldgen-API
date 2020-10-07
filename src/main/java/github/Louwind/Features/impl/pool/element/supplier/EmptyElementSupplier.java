package github.Louwind.Features.impl.pool.element.supplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import github.Louwind.Features.pool.element.FeaturesElementSupplier;
import net.minecraft.structure.pool.EmptyPoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.JsonSerializer;

import java.util.function.Function;

public class EmptyElementSupplier implements FeaturesElementSupplier<EmptyPoolElement> {

    @Override
    public FeaturePoolElementType<EmptyPoolElement> getType() {
        return FeaturePoolElements.EMPTY;
    }

    @Override
    public Function<StructurePool.Projection, EmptyPoolElement> get() {
        return StructurePoolElement.method_30438();
    }

    public static class Serializer implements JsonSerializer<EmptyElementSupplier> {

        @Override
        public void toJson(JsonObject json, EmptyElementSupplier object, JsonSerializationContext context) {

        }

        @Override
        public EmptyElementSupplier fromJson(JsonObject json, JsonDeserializationContext context) {
            return new EmptyElementSupplier();
        }

    }

}
