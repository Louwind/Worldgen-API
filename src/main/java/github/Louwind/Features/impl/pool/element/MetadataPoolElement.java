package github.Louwind.Features.impl.pool.element;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.impl.init.StructurePoolElementTypes;
import github.Louwind.Features.metadata.StructureMetadata;
import net.minecraft.structure.Structure;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElementType;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static github.Louwind.Features.Features.STRUCTURE_METADATA_MANAGER;
import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class MetadataPoolElement extends SinglePoolElement {

    public static final Codec<MetadataPoolElement> CODEC = RecordCodecBuilder.create(instance -> instance.group(method_28882(), method_28880(), method_28883()).apply(instance, MetadataPoolElement::new));

    public MetadataPoolElement(Either<Identifier, Structure> either, Supplier<StructureProcessorList> supplier, StructurePool.Projection projection) {
        super(either, supplier, projection);
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return StructurePoolElementTypes.METADATA;
    }

    @Override
    public void method_16756(WorldAccess worldAccess, Structure.StructureBlockInfo structureBlockInfo, BlockPos blockPos, BlockRotation blockRotation, Random random, BlockBox blockBox) {
        super.method_16756(worldAccess, structureBlockInfo, blockPos, blockRotation, random, blockBox);

        Identifier id = new Identifier(structureBlockInfo.tag.getString("metadata"));

        if(STRUCTURE_METADATA_MANAGER.contains(id)) {
            StructureMetadata metadata = STRUCTURE_METADATA_MANAGER.get(id);

            try {
                FeatureContext context = new FeatureContextBuilder()
                        .put(BLOCK_INFO, structureBlockInfo)
                        .put(POS, blockPos)
                        .put(RANDOM, random)
                        .put(ROTATION, blockRotation)
                        .put(WORLD_ACCESS, worldAccess)
                        .build(FeatureContextProviders.METADATA);

                List<FeatureCondition> conditions = metadata.getConditions();

                if(conditions.stream().allMatch(condition -> condition.test(context))) {
                    List<FeatureFunction> functions = metadata.getFunctions();

                    for (FeatureFunction function: functions)
                        function.accept(context);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

}
