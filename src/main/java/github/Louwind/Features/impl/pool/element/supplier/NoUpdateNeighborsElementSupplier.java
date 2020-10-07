package github.Louwind.Features.impl.pool.element.supplier;

import com.google.common.base.Suppliers;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.datafixers.util.Either;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.impl.pool.element.NoUpdateNeighborsPoolElement;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import github.Louwind.Features.pool.element.FeaturesElementSupplier;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.Structure;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;

import java.util.function.Function;
import java.util.function.Supplier;

public class NoUpdateNeighborsElementSupplier implements FeaturesElementSupplier<NoUpdateNeighborsPoolElement> {

    private final StructureProcessorList list;
    private final Identifier structure;

    public NoUpdateNeighborsElementSupplier(Identifier structure, StructureProcessorList list) {
        this.list = list;
        this.structure = structure;
    }

    @Override
    public FeaturePoolElementType<github.Louwind.Features.impl.pool.element.NoUpdateNeighborsPoolElement> getType() {
        return FeaturePoolElements.NO_UPDATE_NEIGHBORS;
    }

    @Override
    public Function<StructurePool.Projection, github.Louwind.Features.impl.pool.element.NoUpdateNeighborsPoolElement> get() {
        Supplier<StructureProcessorList> supplier = Suppliers.ofInstance(this.list);
        Either<Identifier, Structure> either = Either.left(this.structure);

        return projection -> new github.Louwind.Features.impl.pool.element.NoUpdateNeighborsPoolElement(either, supplier, projection);
    }

    public static class Serializer implements JsonSerializer<NoUpdateNeighborsElementSupplier> {

        @Override
        public void toJson(JsonObject json, NoUpdateNeighborsElementSupplier object, JsonSerializationContext context) {

        }

        @Override
        public NoUpdateNeighborsElementSupplier fromJson(JsonObject json, JsonDeserializationContext context) {
            StructureProcessorList list = FeaturesJsonHelper.getProcessors(json, context, "processors");
            Identifier structure = FeaturesJsonHelper.getIdentifier(json, "structure");

            return new NoUpdateNeighborsElementSupplier(structure, list);
        }

    }

}
