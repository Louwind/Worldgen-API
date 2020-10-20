package github.Louwind.Features.impl.pool.element;

import com.google.common.base.Suppliers;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.impl.init.FeaturePoolElements;
import github.Louwind.Features.impl.init.StructurePoolElementTypes;
import github.Louwind.Features.impl.procesor.ContextProcessor;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import github.Louwind.Features.pool.element.FeaturesElementSupplier;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElementType;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;

import java.util.function.Function;
import java.util.function.Supplier;

public class ContextAwarePoolElement extends SinglePoolElement {

    public static final Codec<ContextAwarePoolElement> CODEC = RecordCodecBuilder.create(instance -> instance.group(method_28882(), method_28880(), method_28883()).apply(instance, ContextAwarePoolElement::new));

    private FeatureContext context = FeatureContext.EMPTY;

    public ContextAwarePoolElement(Either<Identifier, Structure> either, Supplier<StructureProcessorList> supplier, StructurePool.Projection projection) {
        super(either, supplier, projection);
    }

    @Override
    protected StructurePlacementData createPlacementData(BlockRotation blockRotation, BlockBox blockBox, boolean keepJigsaws) {
        StructurePlacementData placementData = super.createPlacementData(blockRotation, blockBox, keepJigsaws);

        placementData.getProcessors().forEach(processor -> {

            if(processor instanceof ContextProcessor) {
                ContextProcessor ruleProcessor = (ContextProcessor) processor;

                ruleProcessor.setContext(this.context);
            }

        });

        return placementData;
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return StructurePoolElementTypes.CONTEXT_AWARE;
    }

    public void setContext(FeatureContext context) {
        this.context = context;
    }

    public static class ContextAwareSupplier implements FeaturesElementSupplier<ContextAwarePoolElement> {

        private final Identifier structure;
        private final StructureProcessorList list;

        public ContextAwareSupplier(Identifier structure, StructureProcessorList list)  {
            this.structure = structure;
            this.list = list;
        }

        @Override
        public FeaturePoolElementType<ContextAwarePoolElement> getType() {
            return FeaturePoolElements.CONTEXT_AWARE;
        }

        @Override
        public Function<StructurePool.Projection, ContextAwarePoolElement> get() {
            Either<Identifier, Structure> either = Either.left(this.structure);

            return projection -> new ContextAwarePoolElement(either, Suppliers.ofInstance(this.list), projection);
        }

    }

    public static class Serializer implements JsonSerializer<ContextAwareSupplier> {

        @Override
        public void toJson(JsonObject json, ContextAwareSupplier object, JsonSerializationContext context) {

        }

        @Override
        public ContextAwareSupplier fromJson(JsonObject json, JsonDeserializationContext context) {
            StructureProcessorList list = FeaturesJsonHelper.getProcessors(json, context, "processors");
            Identifier structure = FeaturesJsonHelper.getIdentifier(json, "structure");

            return new ContextAwareSupplier(structure, list);
        }

    }

}
