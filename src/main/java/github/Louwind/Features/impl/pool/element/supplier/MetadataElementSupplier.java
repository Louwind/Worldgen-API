package github.Louwind.Features.impl.pool.element.supplier;

import com.google.common.base.Suppliers;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.datafixers.util.Either;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.impl.pool.element.MetadataPoolElement;
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

public class MetadataElementSupplier implements FeaturesElementSupplier<MetadataPoolElement> {

    private final StructureProcessorList list;
    private final Identifier structure;

    public MetadataElementSupplier(Identifier structure, StructureProcessorList list) {
        this.list = list;
        this.structure = structure;
    }

    @Override
    public FeaturePoolElementType<MetadataPoolElement> getType() {
        return FeaturePoolElements.METADATA;
    }

    @Override
    public Function<StructurePool.Projection, MetadataPoolElement> get() {
        Supplier<StructureProcessorList> supplier = Suppliers.ofInstance(this.list);
        Either<Identifier, Structure> either = Either.left(this.structure);

        return projection -> new MetadataPoolElement(either, supplier, projection);
    }

    public static class Serializer implements JsonSerializer<MetadataElementSupplier> {

        @Override
        public void toJson(JsonObject json, MetadataElementSupplier object, JsonSerializationContext context) {

        }

        @Override
        public MetadataElementSupplier fromJson(JsonObject json, JsonDeserializationContext context) {
            StructureProcessorList list = FeaturesJsonHelper.getProcessors(json, context, "processors");
            Identifier structure = FeaturesJsonHelper.getIdentifier(json, "structure");

            return new MetadataElementSupplier(structure, list);
        }

    }

}
