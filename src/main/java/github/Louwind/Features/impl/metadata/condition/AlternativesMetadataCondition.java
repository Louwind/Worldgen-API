package github.Louwind.Features.impl.metadata.condition;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.metadata.condition.MetadataConditionType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.MetadataConditions.ALTERNATIVES;

public class AlternativesMetadataCondition implements MetadataCondition {

    public static final Codec<AlternativesMetadataCondition> CODEC = RecordCodecBuilder.create((instance) -> instance.group(MetadataConditionType.CODEC.listOf().fieldOf("conditions").orElseGet(Lists::newArrayList).forGetter(handler -> handler.terms)).apply(instance, (AlternativesMetadataCondition::new)));

    private final List<MetadataCondition> terms;

    public AlternativesMetadataCondition(List<MetadataCondition> terms) {
        this.terms = terms;
    }

    @Override
    public MetadataConditionType<?> getType() {
        return ALTERNATIVES;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return this.terms.stream().anyMatch(condition -> condition.test(world, blockInfo, blockPos, rotation, random));
    }

}
